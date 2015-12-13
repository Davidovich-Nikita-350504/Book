package com.whisperarts.library.common.markets;

import com.whisperarts.library.common.C0005R;

public enum Market {
    GOOGLE_PLAY("GooglePlay", true, "market://details?id=", "https://play.google.com/store/apps/details?id=", "market://search?q=pub:Whisper+Arts", "https://play.google.com/store/apps/developer?id=Whisper+Arts", C0005R.drawable.badge_googleplay),
    YANDEX_STORE("YandexStore", true, "market://details?id=", "https://play.google.com/store/apps/details?id=", "market://search?q=WhisperArts", "https://play.google.com/store/apps/developer?id=Whisper+Arts", C0005R.drawable.badge_yandex),
    AMAZON("Amazon", false, "amzn://apps/android?p=", "http://www.amazon.com/gp/mas/dl/android/", "amzn://apps/android?s=com.whisperarts", "http://www.amazon.com/s/ref=bl_sr_mobile-apps?field-brandtextbin=WhisperArts&node=2350149011", C0005R.drawable.badge_amazon),
    SAMSUNG_STORE("SamsungStore", false, "samsungapps://ProductDetail/", "http://www.samsungapps.com/appquery/appDetail.as?appId=", "samsungapps://SellerDetail/vrtnfp1frj", "http://apps.samsung.com/mars/topApps/getSellerList.as?sellerId=vrtnfp1frj", C0005R.drawable.badge_samsung),
    BLACKBERRY("BlackberryWorld", false, "appworld://content/", "http://appworld.blackberry.com/webstore/content/", "https://appworld.blackberry.com/webstore/vendor/72989/", "https://appworld.blackberry.com/webstore/vendor/72989/", C0005R.drawable.badge_blackberry);
    
    public static final String WEB_LINK = "http://www.whisperarts.com";
    public final String alternativeLinkToAllApps;
    public final int badge;
    public final String linkPrefix;
    public String linkToAllApps;
    public final String name;
    public final boolean usesKey;
    public final String webLinkPrefix;

    private Market(String name, boolean usesKey, String linkPrefix, String webLinkPrefix, String linkToAllApps, String alternativeLinkToAllApps, int badge) {
        this.name = name;
        this.usesKey = usesKey;
        this.linkPrefix = linkPrefix;
        this.webLinkPrefix = webLinkPrefix;
        this.linkToAllApps = linkToAllApps;
        this.alternativeLinkToAllApps = alternativeLinkToAllApps;
        this.badge = badge;
    }

    public String toString() {
        return this.name;
    }
}
