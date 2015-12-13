package com.whisperarts.tales;

import android.app.Application;
import com.whisperarts.library.common.markets.Market;
import com.whisperarts.tales.entities.FullTaleInfo.PACK;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.annotation.ReportsCrashes;

@ReportsCrashes(customReportContent = {ReportField.PACKAGE_NAME, ReportField.APP_VERSION_CODE, ReportField.ANDROID_VERSION, ReportField.PHONE_MODEL, ReportField.DISPLAY, ReportField.ENVIRONMENT, ReportField.BUILD, ReportField.CRASH_CONFIGURATION, ReportField.CUSTOM_DATA, ReportField.STACK_TRACE}, formKey = "dE05akNJbWVYd0J5OFRYU3VIRUpaZ2c6MQ")
public class TalesApplication extends Application {
    public static final Market MARKET;

    static {
        MARKET = Market.GOOGLE_PLAY;
    }

    public void onCreate() {
        ACRA.init(this);
        ACRA.getErrorReporter().putCustomData("Market", MARKET.toString());
        super.onCreate();
        PACK.packs.values();
    }
}
