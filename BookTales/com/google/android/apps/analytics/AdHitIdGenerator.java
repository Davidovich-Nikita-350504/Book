package com.google.android.apps.analytics;

public class AdHitIdGenerator {
    private boolean adMobSdkInstalled;

    public AdHitIdGenerator() {
        try {
            this.adMobSdkInstalled = Class.forName("com.google.ads.AdRequest") != null;
        } catch (ClassNotFoundException e) {
            this.adMobSdkInstalled = false;
        }
    }

    AdHitIdGenerator(boolean z) {
        this.adMobSdkInstalled = z;
    }

    int getAdHitId() {
        return !this.adMobSdkInstalled ? 0 : AdMobInfo.getInstance().generateAdHitId();
    }
}
