package com.whisperarts.tales;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebView;
import android.widget.TextView;
import com.whisperarts.library.common.utils.billing.IabHelper;
import org.acra.ACRAConstants;

public class About extends Activity {

    /* renamed from: com.whisperarts.tales.About.1 */
    class C00131 implements OnTouchListener {
        private final /* synthetic */ TextView val$share;

        C00131(TextView textView) {
            this.val$share = textView;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case ACRAConstants.DEFAULT_SHARED_PREFERENCES_MODE /*0*/:
                    this.val$share.setTextColor(-16777216);
                    break;
                case IabHelper.BILLING_RESPONSE_RESULT_USER_CANCELED /*1*/:
                    this.val$share.setTextColor(-1);
                    Intent emailIntent = new Intent("android.intent.action.SEND");
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra("android.intent.extra.SUBJECT", About.this.getResources().getString(C0026R.string.recommendation_subject));
                    emailIntent.putExtra("android.intent.extra.TEXT", About.this.getResources().getString(C0026R.string.recommendation_body));
                    About.this.startActivity(emailIntent);
                    break;
            }
            return true;
        }
    }

    /* renamed from: com.whisperarts.tales.About.2 */
    class C00142 implements OnClickListener {
        private final /* synthetic */ String val$link;

        C00142(String str) {
            this.val$link = str;
        }

        public void onClick(View arg0) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(this.val$link));
            About.this.startActivity(intent);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0026R.layout.about);
        TextView share = (TextView) findViewById(C0026R.id.share);
        ((WebView) findViewById(C0026R.id.text)).loadDataWithBaseURL(null, getString(C0026R.string.about_text), "text/html", "utf-8", null);
        share.setOnTouchListener(new C00131(share));
        addShareListener(findViewById(C0026R.id.share_googleplay), "https://play.google.com/store/search?q=com.whisperarts");
        addShareListener(findViewById(C0026R.id.share_facebook), "http://www.facebook.com/pages/Prodvinutyj-papa-territoria-pap/128269243874171");
        addShareListener(findViewById(C0026R.id.share_vkontakte), "http://vkontakte.ru/club15209761");
        addShareListener(findViewById(C0026R.id.share_twitter), "http://www.twitter.com/geekdadru");
        addShareListener(findViewById(C0026R.id.share_gplus), "http://plus.google.com/110928640886979103404?prsrc=3");
        addShareListener(findViewById(C0026R.id.share_livejournal), "http://community.livejournal.com/propapa/");
    }

    private void addShareListener(View view, String link) {
        view.setOnClickListener(new C00142(link));
    }
}
