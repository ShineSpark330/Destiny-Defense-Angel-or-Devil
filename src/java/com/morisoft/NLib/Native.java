/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.morisoft.NLib;

public class Native {
    public static native void KStoUnicode(byte[] var0);

    public static native void finsh();

    public static native void initialize(int var0, int var1);

    public static native void inputText(int var0, byte[] var1);

    public static native void itemChargeCancel(int var0);

    public static native void itemChargeComplete();

    public static native void itemChargeError(int var0);

    public static native void keyBegin(int var0);

    public static native void onPause();

    public static native void onResume();

    public static native void render();

    public static native void setFullpaths(String var0, String var1);

    public static native void setVersion(int var0);

    public static native void timerResume();

    public static native void touchesBegin(int var0, int var1, float var2, float var3);

    public static native void touchesCancel(int var0, int var1, float var2, float var3);

    public static native void touchesCancelArray(int var0, float[] var1, float[] var2);

    public static native void touchesEnd(int var0, int var1, float var2, float var3);

    public static native void touchesMove(int var0, int var1, float var2, float var3);

    public static native void touchesMoveArray(int var0, float[] var1, float[] var2);
}

