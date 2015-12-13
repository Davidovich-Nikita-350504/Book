package com.whisperarts.tales;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.BadTokenException;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;
import com.whisperarts.library.common.utils.Config;
import com.whisperarts.library.splashpromo.RandomSplashImage;
import com.whisperarts.tales.adapters.BannerAdapter;
import com.whisperarts.tales.adapters.MyTalesAdapter;
import com.whisperarts.tales.banners.BannerGenerator;
import com.whisperarts.tales.banners.BannerItem;
import com.whisperarts.tales.dao.TalesInfoDAO;
import com.whisperarts.tales.entities.AdditionalTalesInfo;
import com.whisperarts.tales.entities.FullTaleInfo;
import com.whisperarts.tales.entities.FullTaleInfo.PACK;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.acra.ACRAConstants;

public class MainTabbed extends Activity {
    private static final int EXIT_DELAY = 2000;
    private static final int EXIT_MESSAGE = 0;
    public static final int LAUNCH_COUNT_BEFORE_DIALOG = 6;
    private static final int MESSAGE_INITIALIZE = 2;
    public static final String NO_SPLASH_TAG = "no_splash";
    public static final String PACKAGE_TO_START_TAG = "package_to_start";
    private static final int SPLASH_DELAY = 6000;
    private static final int SPLASH_MESSAGE = 1;
    private MyTalesAdapter adapter;
    private final Map<String, FullTaleInfo> allTales;
    private int height;
    private volatile boolean initialized;
    private boolean isAllowedToExit;
    private boolean isAllowedToPressKeys;
    private boolean isShowAd;
    private boolean isVertical;
    private LinearLayout mainLayout;
    private PackageManager pm;
    private ImageView promo;
    private SharedPreferences settings;
    private boolean shouldCount;
    private ImageView splash;
    private final Handler splashHandler;
    private final Map<String, String> tales;
    private Toast toast;
    private GoogleAnalyticsTracker tracker;
    private int width;

    /* renamed from: com.whisperarts.tales.MainTabbed.1 */
    class C00151 extends Handler {
        C00151() {
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MainTabbed.EXIT_MESSAGE /*0*/:
                    MainTabbed.this.isAllowedToExit = false;
                    break;
                case MainTabbed.SPLASH_MESSAGE /*1*/:
                    MainTabbed.this.splash.setVisibility(8);
                    MainTabbed.this.splash.setImageBitmap(null);
                    MainTabbed.this.promo.setVisibility(8);
                    MainTabbed.this.isAllowedToPressKeys = true;
                    if (MainTabbed.this.shouldCount) {
                        MainTabbed.this.shouldCount = false;
                        int launchCount = MainTabbed.this.settings.getInt("launch_num_1", MainTabbed.EXIT_MESSAGE);
                        if (launchCount <= MainTabbed.LAUNCH_COUNT_BEFORE_DIALOG) {
                            launchCount += MainTabbed.SPLASH_MESSAGE;
                            MainTabbed.this.settings.edit().putInt("launch_num_1", launchCount).commit();
                            if (launchCount == MainTabbed.LAUNCH_COUNT_BEFORE_DIALOG) {
                                MainTabbed.this.showMarketDialog(MainTabbed.this.getString(C0026R.string.rate_app_text), MainTabbed.this.getString(C0026R.string.go_to_market_button_text), MainTabbed.this.getString(C0026R.string.cancel_dialog_text), "market://details?id=com.whisperarts.tales");
                                break;
                            }
                        }
                    }
                    break;
                case MainTabbed.MESSAGE_INITIALIZE /*2*/:
                    MainTabbed.this.setContent();
                    MainTabbed.this.initialized = true;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    /* renamed from: com.whisperarts.tales.MainTabbed.2 */
    class C00162 implements OnClickListener {
        C00162() {
        }

        public void onClick(View arg0) {
            if (MainTabbed.this.initialized) {
                MainTabbed.this.sendMessage(MainTabbed.SPLASH_MESSAGE, MainTabbed.EXIT_MESSAGE);
            }
        }
    }

    /* renamed from: com.whisperarts.tales.MainTabbed.3 */
    class C00193 implements Runnable {

        /* renamed from: com.whisperarts.tales.MainTabbed.3.1 */
        class C00181 implements Runnable {
            private final /* synthetic */ RandomSplashImage val$activeSplash;

            /* renamed from: com.whisperarts.tales.MainTabbed.3.1.1 */
            class C00171 implements OnClickListener {
                private final /* synthetic */ RandomSplashImage val$activeSplash;

                C00171(RandomSplashImage randomSplashImage) {
                    this.val$activeSplash = randomSplashImage;
                }

                public void onClick(View v) {
                    if (!Config.DEBUG) {
                        MainTabbed.this.tracker.trackEvent("promo", "promo_clicked", this.val$activeSplash.name(), MainTabbed.SPLASH_MESSAGE);
                        MainTabbed.this.tracker.dispatch();
                    }
                    if (this.val$activeSplash.link() != null) {
                        try {
                            MainTabbed.this.openMarket(this.val$activeSplash.link());
                            return;
                        } catch (Exception e) {
                            MainTabbed.this.openMarket(this.val$activeSplash.webLink());
                            return;
                        }
                    }
                    MainTabbed.this.openMarket(this.val$activeSplash.webLink());
                }
            }

            C00181(RandomSplashImage randomSplashImage) {
                this.val$activeSplash = randomSplashImage;
            }

            public void run() {
                if (this.val$activeSplash != null) {
                    MainTabbed.this.promo.setImageResource(this.val$activeSplash.image());
                    MainTabbed.this.promo.setOnClickListener(new C00171(this.val$activeSplash));
                }
            }
        }

        C00193() {
        }

        public void run() {
            RandomSplashImage activeSplash = RandomSplashImage.getSplash(MainTabbed.this);
            if (!Config.DEBUG) {
                MainTabbed.this.tracker.trackEvent("promo", "promo_shown", activeSplash.name(), MainTabbed.SPLASH_MESSAGE);
                MainTabbed.this.tracker.dispatch();
            }
            MainTabbed.this.runOnUiThread(new C00181(activeSplash));
        }
    }

    /* renamed from: com.whisperarts.tales.MainTabbed.4 */
    class C00204 implements OnItemClickListener {
        private final /* synthetic */ ArrayList val$tales;

        C00204(ArrayList arrayList) {
            this.val$tales = arrayList;
        }

        public void onItemClick(AdapterView<?> adapterView, View arg1, int arg2, long arg3) {
            FullTaleInfo tale = (FullTaleInfo) this.val$tales.get(arg2);
            if (tale.isPaid()) {
                if (!Config.DEBUG) {
                    MainTabbed.this.tracker.trackEvent(tale.getFullPackage(), "launched", ACRAConstants.DEFAULT_STRING_VALUE, MainTabbed.EXIT_MESSAGE);
                    MainTabbed.this.tracker.dispatch();
                }
                MainTabbed.this.loadTale(tale.getFullPackage());
                return;
            }
            MainTabbed.this.showMarketDialog(MainTabbed.this.getString(C0026R.string.go_to_market_for_app_text), "\u0423\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442\u044c", "\u0417\u0430\u043a\u0440\u044b\u0442\u044c", tale.getFullPackage());
        }
    }

    /* renamed from: com.whisperarts.tales.MainTabbed.5 */
    class C00215 implements OnItemClickListener {
        private final /* synthetic */ List val$banners;

        C00215(List list) {
            this.val$banners = list;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            if (!Config.DEBUG) {
                MainTabbed.this.tracker.trackEvent("banner", "bannerClicked", ACRAConstants.DEFAULT_STRING_VALUE, MainTabbed.EXIT_MESSAGE);
                MainTabbed.this.tracker.dispatch();
            }
            MainTabbed.this.showMarketDialog(MainTabbed.this.getString(C0026R.string.go_to_market_banner_text), "\u0423\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442\u044c", "\u0417\u0430\u043a\u0440\u044b\u0442\u044c", ((BannerItem) this.val$banners.get(position)).packageName);
        }
    }

    /* renamed from: com.whisperarts.tales.MainTabbed.6 */
    class C00226 implements Runnable {
        private final /* synthetic */ String val$message;

        C00226(String str) {
            this.val$message = str;
        }

        public void run() {
            Toast.makeText(MainTabbed.this, this.val$message, MainTabbed.SPLASH_MESSAGE).show();
        }
    }

    /* renamed from: com.whisperarts.tales.MainTabbed.7 */
    class C00237 implements Runnable {
        C00237() {
        }

        public void run() {
            if (MainTabbed.this.organizeTales()) {
                MainTabbed.this.sendMessage(MainTabbed.MESSAGE_INITIALIZE, MainTabbed.EXIT_MESSAGE);
            }
        }
    }

    /* renamed from: com.whisperarts.tales.MainTabbed.8 */
    class C00248 implements DialogInterface.OnClickListener {
        private final /* synthetic */ String val$talePackage;

        C00248(String str) {
            this.val$talePackage = str;
        }

        public void onClick(DialogInterface dialog, int which) {
            try {
                MainTabbed.this.goToMarket("market://details?id=" + this.val$talePackage);
            } catch (Exception e) {
                MainTabbed.this.goToMarket("https://play.google.com/store/apps/details?id=" + this.val$talePackage);
            }
        }
    }

    /* renamed from: com.whisperarts.tales.MainTabbed.9 */
    class C00259 implements DialogInterface.OnClickListener {
        C00259() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    }

    public MainTabbed() {
        this.allTales = Collections.synchronizedMap(new HashMap());
        this.isAllowedToExit = false;
        this.isAllowedToPressKeys = false;
        this.initialized = false;
        this.tales = new HashMap();
        this.splashHandler = new C00151();
        this.isVertical = true;
        this.shouldCount = true;
    }

    @SuppressLint({"ShowToast"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0026R.layout.tales_main);
        if (!Config.DEBUG) {
            this.tracker = GoogleAnalyticsTracker.getInstance();
            this.tracker.startNewSession("UA-30857818-1", this);
            this.tracker.trackPageView("/TalesCatalogLaunched");
            this.tracker.dispatch();
        }
        this.settings = PreferenceManager.getDefaultSharedPreferences(this);
        if (!this.settings.contains("autoplay")) {
            boolean z;
            Editor edit = this.settings.edit();
            String str = "autoplay";
            if (this.settings.contains("launch_num_1")) {
                z = false;
            } else {
                z = true;
            }
            edit.putBoolean(str, z).commit();
        }
        Display display = getWindowManager().getDefaultDisplay();
        this.width = display.getWidth();
        this.height = display.getHeight();
        if (this.width > this.height) {
            this.isVertical = false;
        }
        TalesInfoDAO.setContext(this);
        this.splash = (ImageView) findViewById(C0026R.id.splash);
        this.promo = (ImageView) findViewById(C0026R.id.promo);
        this.splash.setOnClickListener(new C00162());
        setProperSplash();
        this.toast = Toast.makeText(this, getString(C0026R.string.exit_warning), EXIT_DELAY);
        this.pm = getPackageManager();
        this.mainLayout = (LinearLayout) findViewById(C0026R.id.tales_list_layout);
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean(NO_SPLASH_TAG)) {
            this.shouldCount = false;
            sendMessage(SPLASH_MESSAGE, EXIT_MESSAGE);
            String packageToStart = extras.getString(PACKAGE_TO_START_TAG);
            if (packageToStart != null) {
                loadTale(packageToStart);
            }
        }
        sendMessage(SPLASH_MESSAGE, SPLASH_DELAY);
        new Thread(new C00193()).start();
    }

    private void openMarket(String link) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(link));
        startActivity(intent);
    }

    private void setContent() {
        this.mainLayout.removeAllViews();
        if (this.isShowAd) {
            createPackBanner();
        }
        ArrayList<FullTaleInfo> tales = new ArrayList(this.allTales.values());
        Collections.sort(tales);
        ListView lv = new ListView(this);
        lv.setCacheColorHint(EXIT_MESSAGE);
        lv.setBackgroundDrawable(getResources().getDrawable(C0026R.drawable.all_list_shape));
        this.adapter = new MyTalesAdapter(this, tales);
        lv.setAdapter(this.adapter);
        lv.setOnItemClickListener(new C00204(tales));
        LinearLayout layout = new LinearLayout(this);
        layout.setPadding(10, 10, 10, 10);
        layout.addView(lv, new LayoutParams(-1, -1));
        this.mainLayout.addView(layout, new LayoutParams(-1, -1));
    }

    private void createPackBanner() {
        List<BannerItem> banners = getBannerItems();
        if (banners.size() >= 3) {
            Gallery gallery = new Gallery(this);
            BannerAdapter bannerAdapter = new BannerAdapter(this, banners);
            if (bannerAdapter.getCount() >= 3) {
                gallery.setAdapter(bannerAdapter);
                gallery.setSelection(bannerAdapter.getCount() == 3 ? SPLASH_MESSAGE : MESSAGE_INITIALIZE, false);
                gallery.setOnItemClickListener(new C00215(banners));
                this.mainLayout.addView(gallery, new LayoutParams(-1, -2));
            }
        }
    }

    private List<BannerItem> getBannerItems() {
        return new BannerGenerator(this.allTales).doGenerate();
    }

    private boolean organizeTales() {
        boolean smthChanged;
        this.allTales.clear();
        this.tales.clear();
        boolean wasShowAd = this.isShowAd;
        this.isShowAd = this.settings.getBoolean("show_ad", true);
        if (false || this.isShowAd != wasShowAd) {
            smthChanged = true;
        } else {
            smthChanged = false;
        }
        Map<String, Boolean> previuosTalesState = new HashMap();
        for (FullTaleInfo taleInfo : this.allTales.values()) {
            previuosTalesState.put(taleInfo.getFullPackage(), Boolean.valueOf(taleInfo.isPaid()));
        }
        try {
            this.allTales.putAll(TalesInfoDAO.getTalesCatalog());
        } catch (IOException e) {
            smthChanged = true;
            showToast("\u041e\u0448\u0438\u0431\u043a\u0430 \u0432\u043e \u0432\u0440\u0435\u043c\u044f \u0437\u0430\u0433\u0440\u0443\u0437\u043a\u0438\n\u041f\u0435\u0440\u0435\u0437\u0430\u043f\u0443\u0441\u0442\u0438\u0442\u0435 \u043f\u0440\u0438\u043b\u043e\u0436\u0435\u043d\u0438\u0435");
            this.allTales.clear();
        }
        initializeTales();
        checkAndRemovePacks();
        if (previuosTalesState.size() != this.allTales.size()) {
            return true;
        }
        if (smthChanged) {
            return smthChanged;
        }
        return isNewTaleInstalled(previuosTalesState);
    }

    private boolean isNewTaleInstalled(Map<String, Boolean> previuosTalesState) {
        for (FullTaleInfo taleInfo : this.allTales.values()) {
            Boolean isPaid = (Boolean) previuosTalesState.get(taleInfo.getFullPackage());
            if (isPaid != null) {
                if (isPaid.booleanValue() != taleInfo.isPaid()) {
                }
            }
            showToast("\u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u0435 \u043a\u0430\u0442\u0430\u043b\u043e\u0433\u0430");
            return true;
        }
        return false;
    }

    private void showToast(String message) {
        this.splashHandler.post(new C00226(message));
    }

    private void checkAndRemovePacks() {
        Set<String> installedPackages = this.tales.keySet();
        for (PACK pack : PACK.packs.values()) {
            int counter = EXIT_MESSAGE;
            for (String packPackage : pack.getTales()) {
                if (installedPackages.contains(packPackage)) {
                    counter += SPLASH_MESSAGE;
                    if (counter >= pack.maxNumberOfInstalledTalesToIgnorePack) {
                        pack.isInstalledOrNotNeeded = true;
                        this.allTales.remove(pack.packageName);
                        break;
                    }
                }
            }
        }
    }

    private void initializeTales() {
        PACK.uninstall();
        for (ApplicationInfo packageInfo : this.pm.getInstalledApplications(512)) {
            String packageName = packageInfo.packageName;
            if (packageName.startsWith("com.whisperarts.tales.")) {
                PACK pack = (PACK) PACK.packs.get(packageName);
                FullTaleInfo tale;
                if (pack != null) {
                    this.allTales.remove(pack.packageName);
                    pack.isInstalledOrNotNeeded = true;
                    for (String packPackageName : pack.getTales()) {
                        tale = (FullTaleInfo) this.allTales.get(packPackageName);
                        if (tale != null) {
                            tale.setPaid(true);
                            this.tales.put(packPackageName, pack.packageName);
                        }
                    }
                } else {
                    tale = (FullTaleInfo) this.allTales.get(packageName);
                    if (tale != null && this.tales.get(packageName) == null) {
                        tale.setPaid(true);
                        this.tales.put(packageName, null);
                        try {
                            if (isOldVersion(packageName, this.pm.getPackageInfo(packageName, EXIT_MESSAGE).versionName)) {
                                tale.setNeedUpgrade(true);
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
    }

    private boolean isOldVersion(String packageName, String versionName) {
        String[] taleVersion = versionName.split("\\.");
        AdditionalTalesInfo talesInfo = AdditionalTalesInfo.byPackageName(packageName);
        if (talesInfo == null) {
            return false;
        }
        String[] currentVersion = talesInfo.versionName.split("\\.");
        if (taleVersion.length != 3 || currentVersion.length != 3) {
            return false;
        }
        if (Integer.parseInt(taleVersion[EXIT_MESSAGE]) < Integer.parseInt(currentVersion[EXIT_MESSAGE]) || Integer.parseInt(taleVersion[SPLASH_MESSAGE]) < Integer.parseInt(currentVersion[SPLASH_MESSAGE]) || Integer.parseInt(taleVersion[MESSAGE_INITIALIZE]) < Integer.parseInt(currentVersion[MESSAGE_INITIALIZE])) {
            return true;
        }
        return false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!this.isAllowedToPressKeys) {
            return true;
        }
        if (keyCode == 4) {
            return performExit();
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean performExit() {
        if (this.isAllowedToExit) {
            this.toast.cancel();
            finish();
        } else {
            this.toast.show();
            this.isAllowedToExit = true;
            sendMessage(EXIT_MESSAGE, EXIT_DELAY);
        }
        return true;
    }

    @SuppressLint({"HandlerLeak"})
    private void sendMessage(int what, int delay) {
        this.splashHandler.removeMessages(what);
        Message msg = new Message();
        msg.what = what;
        this.splashHandler.sendMessageDelayed(msg, (long) delay);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        initMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initMenu(Menu menu) {
        menu.add(EXIT_MESSAGE, EXIT_MESSAGE, EXIT_MESSAGE, "\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438");
        menu.add(EXIT_MESSAGE, SPLASH_MESSAGE, EXIT_MESSAGE, "\u041d\u0430\u0448\u0438 \u043f\u0440\u0438\u043b\u043e\u0436\u0435\u043d\u0438\u044f");
        menu.add(EXIT_MESSAGE, MESSAGE_INITIALIZE, EXIT_MESSAGE, "\u041e \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u043c\u0435");
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return applyMenuChoice(item) || super.onOptionsItemSelected(item);
    }

    private boolean applyMenuChoice(MenuItem item) {
        switch (item.getItemId()) {
            case EXIT_MESSAGE /*0*/:
                startActivity(new Intent(this, Settings.class));
                break;
            case SPLASH_MESSAGE /*1*/:
                goToMarket("https://play.google.com/store/apps/search?q=com.whisperarts");
                break;
            case MESSAGE_INITIALIZE /*2*/:
                startActivity(new Intent(this, About.class));
                break;
            default:
                return false;
        }
        return true;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.isVertical = !this.isVertical;
        setProperSplash();
    }

    private void setProperSplash() {
        try {
            doSetProperSplash();
        } catch (OutOfMemoryError e) {
            System.gc();
            try {
                doSetProperSplash();
            } catch (OutOfMemoryError e2) {
                sendMessage(SPLASH_MESSAGE, EXIT_MESSAGE);
            }
        } catch (NullPointerException e3) {
            sendMessage(SPLASH_MESSAGE, EXIT_MESSAGE);
        }
    }

    private void doSetProperSplash() {
        int style = 9;
        if ((this.width > 480 && this.height > 800) || (this.width > 800 && this.height > 480)) {
            style = 9 | 4;
        }
        if (this.isVertical) {
            findViewById(C0026R.id.left_logo_part).setVisibility(8);
        } else {
            findViewById(C0026R.id.left_logo_part).setVisibility(EXIT_MESSAGE);
            style |= MESSAGE_INITIALIZE;
        }
        switch (style) {
            case 9:
                this.splash.setImageDrawable(getResources().getDrawable(C0026R.drawable.splash_vert));
            case 11:
                this.splash.setImageDrawable(getResources().getDrawable(C0026R.drawable.splash));
            case 13:
                this.splash.setImageDrawable(getResources().getDrawable(C0026R.drawable.splash_vert_big));
            case 15:
                this.splash.setImageDrawable(getResources().getDrawable(C0026R.drawable.splash_big));
            default:
        }
    }

    protected void loadTale(String packageName) {
        boolean z = false;
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.putExtra("no_sound", !this.settings.getBoolean("no_sound", true));
        String str = "hide_text";
        if (!this.settings.getBoolean("hide_text", true)) {
            z = true;
        }
        intent.putExtra(str, z);
        intent.putExtra("autoplay", this.settings.getBoolean("autoplay", true));
        if (this.tales.get(packageName) == null) {
            intent.setComponent(new ComponentName(packageName, "com.whisperarts.tales.lib.activities.ShowTale"));
        } else {
            intent.putExtra("package_name", packageName);
            intent.setComponent(new ComponentName((String) this.tales.get(packageName), "com.whisperarts.tales.lib.activities.PackShowTale"));
        }
        startActivity(intent);
    }

    protected void onStart() {
        new Thread(new C00237()).start();
        super.onStart();
    }

    private void showMarketDialog(String text, String positiveText, String cancelText, String talePackage) {
        Builder builder = new Builder(this);
        builder.setTitle("\u0421\u043a\u0430\u0437\u043a\u0438");
        builder.setMessage(text);
        builder.setPositiveButton(positiveText, new C00248(talePackage));
        builder.setNegativeButton(cancelText, new C00259());
        try {
            builder.create().show();
        } catch (BadTokenException e) {
        }
    }

    private void goToMarket(String link) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(link));
        startActivity(intent);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (!Config.DEBUG) {
            this.tracker.stopSession();
        }
    }
}
