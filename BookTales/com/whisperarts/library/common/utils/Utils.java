package com.whisperarts.library.common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.whisperarts.library.common.markets.Market;

public final class Utils {
    private Utils() {
    }

    public static void goToMarket(String marketLink, String alternativeLink, Context context) {
        try {
            openLink(context, marketLink);
        } catch (Exception e1) {
            Log.m0e("Cannot open: " + marketLink + "\n" + e1.getMessage());
            try {
                openLink(context, alternativeLink);
            } catch (Exception e2) {
                Log.m0e("Cannot open: " + alternativeLink + "\n" + e2.getMessage());
                openLink(context, Market.WEB_LINK);
            }
        }
    }

    private static void openLink(Context context, String link) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(link));
        context.startActivity(intent);
    }
}
