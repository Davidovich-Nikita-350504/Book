package org.acra.log;

public interface ACRALog {
    int m2d(String str, String str2);

    int m3d(String str, String str2, Throwable th);

    int m4e(String str, String str2);

    int m5e(String str, String str2, Throwable th);

    String getStackTraceString(Throwable th);

    int m6i(String str, String str2);

    int m7i(String str, String str2, Throwable th);

    int m8v(String str, String str2);

    int m9v(String str, String str2, Throwable th);

    int m10w(String str, String str2);

    int m11w(String str, String str2, Throwable th);

    int m12w(String str, Throwable th);
}
