package com.whisperarts.library.splashpromo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomSplashImage {
    private static final String ALPHABET_PACKAGE = "com.whisperarts.alphabetfull";
    private static final String COLORS_PACKAGE = "com.whisperarts.kids.colors";
    private static final String FORMS_PACKAGE = "com.whisperarts.kids.forms";
    private static final String JOURNAL_PACKAGE = "com.whisperarts.kids.journal";
    private static final String KIDSSHELL_PACKAGE = "com.whisperarts.kidsshell";
    private static final String KINDER_LINK_1 = "http://addinapp.ru/lp/cref/index.php?banner=1";
    private static final String KINDER_LINK_2 = "http://addinapp.ru/lp/cref/index.php?banner=2";
    private static final String KINDER_LINK_3 = "http://addinapp.ru/lp/cref/index.php?banner=3";
    private static final String KINDER_LINK_4 = "http://addinapp.ru/lp/cref/index.php?banner=4";
    private static final String MARKET_LINK_PREFIX = "market://details?id=";
    private static final String MATH_PACKAGE = "com.whisperarts.kids.math";
    private static final String TALES_PACKAGE = "com.whisperarts.tales";
    private static final String WEB_LINK_PREFIX = "https://play.google.com/store/apps/details?id=";
    private static final Random f0r;
    private static final List<SplashResource> resources;
    private final int img;
    private final String link;
    private final String name;
    private final String webLink;

    private enum SplashResource {
        ALPHABET_SPLASH(RandomSplashImage.ALPHABET_PACKAGE, C0012R.drawable.splash_promo_apps01, "alphabet", false),
        TALES_SPLASH(RandomSplashImage.TALES_PACKAGE, C0012R.drawable.splash_promo_apps02, "tales", false),
        MATH_SPLASH(RandomSplashImage.MATH_PACKAGE, C0012R.drawable.splash_promo_apps03, "math", false),
        KIDSSHELL_SPLASH(RandomSplashImage.KIDSSHELL_PACKAGE, C0012R.drawable.splash_promo_apps06, "kidsshell", false),
        COLORS_SPLASH(RandomSplashImage.COLORS_PACKAGE, C0012R.drawable.splash_promo_apps07, "colors", false),
        FORMS_SPLASH(RandomSplashImage.FORMS_PACKAGE, C0012R.drawable.splash_promo_apps08, "forms", false),
        JOURNAL(RandomSplashImage.JOURNAL_PACKAGE, C0012R.drawable.splash_promo_apps09, "journal", false),
        KINDER_1(RandomSplashImage.KINDER_LINK_1, C0012R.drawable.kinder_1, "Cref1", true),
        KINDER_2(RandomSplashImage.KINDER_LINK_2, C0012R.drawable.kinder_2, "Cref2", true),
        KINDER_3(RandomSplashImage.KINDER_LINK_3, C0012R.drawable.kinder_3, "Cref3", true),
        KINDER_4(RandomSplashImage.KINDER_LINK_4, C0012R.drawable.kinder_4, "Cref4", true);
        
        private final int img;
        private final String name;
        private final String packageName;
        private final boolean webLink;

        private SplashResource(String packageName, int img, String name, boolean webLink) {
            this.packageName = packageName;
            this.img = img;
            this.name = name;
            this.webLink = webLink;
        }
    }

    static {
        f0r = new Random();
        resources = new ArrayList();
    }

    public static RandomSplashImage getSplash(Context context) {
        resources.clear();
        resources.addAll(Arrays.asList(SplashResource.values()));
        Calendar limitDate = Calendar.getInstance();
        limitDate.set(2014, 3, 8, 0, 0);
        if (Calendar.getInstance().after(limitDate)) {
            resources.remove(SplashResource.KINDER_1);
            resources.remove(SplashResource.KINDER_2);
            resources.remove(SplashResource.KINDER_3);
            resources.remove(SplashResource.KINDER_4);
        }
        Collections.shuffle(resources);
        for (ApplicationInfo packageInfo : context.getPackageManager().getInstalledApplications(128)) {
            if (packageInfo.packageName.startsWith("com.whisperarts")) {
                for (SplashResource resource : resources) {
                    if (!resource.packageName.startsWith(packageInfo.packageName)) {
                        if (packageInfo.packageName.startsWith(resource.packageName)) {
                        }
                    }
                    resources.remove(resource);
                    break;
                }
            }
        }
        if (resources.isEmpty()) {
            return null;
        }
        return new RandomSplashImage((SplashResource) resources.get(f0r.nextInt(resources.size())));
    }

    private RandomSplashImage(SplashResource res) {
        if (res.webLink) {
            this.link = null;
            this.webLink = res.packageName;
        } else {
            this.link = new StringBuilder(MARKET_LINK_PREFIX).append(res.packageName).toString();
            this.webLink = new StringBuilder(WEB_LINK_PREFIX).append(res.packageName).toString();
        }
        this.name = res.name;
        this.img = res.img;
    }

    public String link() {
        return this.link;
    }

    public String webLink() {
        return this.webLink;
    }

    public int image() {
        return this.img;
    }

    public String name() {
        return this.name;
    }
}
