package org.acra.log;

import android.util.Log;

public final class AndroidLogDelegate implements ACRALog {
    public int m19v(String tag, String msg) {
        return Log.v(tag, msg);
    }

    public int m20v(String tag, String msg, Throwable tr) {
        return Log.v(tag, msg, tr);
    }

    public int m13d(String tag, String msg) {
        return Log.d(tag, msg);
    }

    public int m14d(String tag, String msg, Throwable tr) {
        return Log.d(tag, msg, tr);
    }

    public int m17i(String tag, String msg) {
        return Log.i(tag, msg);
    }

    public int m18i(String tag, String msg, Throwable tr) {
        return Log.i(tag, msg, tr);
    }

    public int m21w(String tag, String msg) {
        return Log.w(tag, msg);
    }

    public int m22w(String tag, String msg, Throwable tr) {
        return Log.w(tag, msg, tr);
    }

    public int m23w(String tag, Throwable tr) {
        return Log.w(tag, tr);
    }

    public int m15e(String tag, String msg) {
        return Log.e(tag, msg);
    }

    public int m16e(String tag, String msg, Throwable tr) {
        return Log.e(tag, msg, tr);
    }

    public String getStackTraceString(Throwable tr) {
        return Log.getStackTraceString(tr);
    }
}
