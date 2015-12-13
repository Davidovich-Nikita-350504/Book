package com.whisperarts.library.common.utils;

public class Log {
    public static void m0e(String message) {
        if (Config.DEBUG) {
            android.util.Log.e("WhisperArts", message);
        }
    }

    public static void m1e(Throwable e) {
        if (Config.DEBUG) {
            e.printStackTrace();
        }
    }
}
