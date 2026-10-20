/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.opengl.GLSurfaceView
 *  android.opengl.GLSurfaceView$Renderer
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  javax.microedition.khronos.egl.EGLConfig
 *  javax.microedition.khronos.opengles.GL10
 */
package org.cocos2dx.lib;

import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Cocos2dxRenderer
implements GLSurfaceView.Renderer {
    private static final long NANOSECONDSPERMINISECOND = 1000000L;
    private static final long NANOSECONDSPERSECOND = 1000000000L;
    private static long animationInterval = 16666666L;
    private long last;
    private int screenHeight;
    private int screenWidth;

    private static native void nativeDeleteBackward();

    private static native String nativeGetContentText();

    private static native void nativeInit(int var0, int var1);

    private static native void nativeInsertText(String var0);

    private static native boolean nativeKeyDown(int var0);

    private static native void nativeOnPause();

    private static native void nativeOnResume();

    private static native void nativeRender();

    private static native void nativeTouchesBegin(int var0, float var1, float var2);

    private static native void nativeTouchesCancel(int[] var0, float[] var1, float[] var2);

    private static native void nativeTouchesEnd(int var0, float var1, float var2);

    private static native void nativeTouchesMove(int[] var0, float[] var1, float[] var2);

    public static void setAnimationInterval(double d) {
        animationInterval = (long)(1.0E9 * d);
    }

    public String getContentText() {
        return Cocos2dxRenderer.nativeGetContentText();
    }

    public void handleActionCancel(int[] arrn, float[] arrf, float[] arrf2) {
        Cocos2dxRenderer.nativeTouchesCancel(arrn, arrf, arrf2);
    }

    public void handleActionDown(int n, float f, float f2) {
        Cocos2dxRenderer.nativeTouchesBegin(n, f, f2);
    }

    public void handleActionMove(int[] arrn, float[] arrf, float[] arrf2) {
        Cocos2dxRenderer.nativeTouchesMove(arrn, arrf, arrf2);
    }

    public void handleActionUp(int n, float f, float f2) {
        Cocos2dxRenderer.nativeTouchesEnd(n, f, f2);
    }

    public void handleDeleteBackward() {
        Cocos2dxRenderer.nativeDeleteBackward();
    }

    public void handleInsertText(String string2) {
        Cocos2dxRenderer.nativeInsertText(string2);
    }

    public void handleKeyDown(int n) {
        Cocos2dxRenderer.nativeKeyDown(n);
    }

    public void handleOnPause() {
        Cocos2dxRenderer.nativeOnPause();
    }

    public void handleOnResume() {
        Cocos2dxRenderer.nativeOnResume();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void onDrawFrame(GL10 gL10) {
        long l;
        l = System.nanoTime();
        long l2 = l - this.last;
        Cocos2dxRenderer.nativeRender();
        if (l2 < animationInterval) {
            try {
                Thread.sleep((long)(2L * (animationInterval - l2) / 1000000L));
            }
            catch (Exception exception) {}
        }
        this.last = l;
    }

    public void onSurfaceChanged(GL10 gL10, int n, int n2) {
    }

    public void onSurfaceCreated(GL10 gL10, EGLConfig eGLConfig) {
        Cocos2dxRenderer.nativeInit(this.screenWidth, this.screenHeight);
        this.last = System.nanoTime();
    }

    public void setScreenWidthAndHeight(int n, int n2) {
        this.screenWidth = n;
        this.screenHeight = n2;
    }
}

