package com.whisperarts.library.common.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import java.util.Locale;

public class LocaleUtils {
    public static void setLocale(String lang, Context context) {
        Locale locale;
        if (lang.equals("zh_CN")) {
            locale = Locale.SIMPLIFIED_CHINESE;
        } else if (lang.equals("zh_TW")) {
            locale = Locale.TAIWAN;
        } else {
            locale = new Locale(lang);
        }
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        Resources resources = context.getResources();
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}
