/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Handler
 *  android.os.Looper
 *  android.util.AttributeSet
 *  android.view.SurfaceHolder
 *  android.view.SurfaceHolder$Callback
 *  android.view.SurfaceView
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.util.concurrent.Semaphore
 *  javax.microedition.khronos.egl.EGL10
 *  javax.microedition.khronos.egl.EGLConfig
 *  javax.microedition.khronos.egl.EGLContext
 *  javax.microedition.khronos.egl.EGLDisplay
 *  javax.microedition.khronos.egl.EGLSurface
 *  javax.microedition.khronos.opengles.GL
 *  javax.microedition.khronos.opengles.GL10
 */
package com.morisoft.NLib.ogl;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.morisoft.NLib.Native;
import com.morisoft.NLib.SpeedCtl;
import java.util.concurrent.Semaphore;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

public class GLSurfaceView
extends SurfaceView
implements SurfaceHolder.Callback {
    private static final Semaphore sEglSemaphore = new Semaphore(1);
    private GLThread mGLThread;
    private GLWrapper mGLWrapper;
    private SurfaceHolder mHolder;
    private boolean mSizeChanged = true;

    public GLSurfaceView(Context context) {
        super(context);
        this.init();
    }

    public GLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.init();
    }

    static /* synthetic */ boolean access$2(GLSurfaceView gLSurfaceView) {
        return gLSurfaceView.mSizeChanged;
    }

    static /* synthetic */ void access$3(GLSurfaceView gLSurfaceView, boolean bl) {
        gLSurfaceView.mSizeChanged = bl;
    }

    static /* synthetic */ SurfaceHolder access$4(GLSurfaceView gLSurfaceView) {
        return gLSurfaceView.mHolder;
    }

    private void init() {
        this.mHolder = this.getHolder();
        this.mHolder.addCallback((SurfaceHolder.Callback)this);
        this.mHolder.setType(2);
    }

    public SurfaceHolder getSurfaceHolder() {
        return this.mHolder;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mGLThread.requestExitAndWait();
    }

    public void onPause() {
        this.mGLThread.onPause();
    }

    public void onResume() {
        this.mGLThread.onResume();
    }

    public void onWindowFocusChanged(boolean bl) {
        super.onWindowFocusChanged(bl);
        this.mGLThread.onWindowFocusChanged(bl);
    }

    public void setEvent(Runnable runnable) {
        this.mGLThread.setEvent(runnable);
    }

    public void setGLWrapper(GLWrapper gLWrapper) {
        this.mGLWrapper = gLWrapper;
    }

    public void setHandler(Handler handler) {
        this.mGLThread.setHandler(handler);
    }

    public void setRenderer(Renderer renderer) {
        this.mGLThread = new GLThread(renderer);
        this.mGLThread.start();
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int n, int n2, int n3) {
        this.mGLThread.onWindowResize(n2, n3);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mGLThread.surfaceCreated();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mGLThread.surfaceDestroyed();
    }

    public void swap() {
        this.mGLThread.swap();
    }

    private class EglHelper {
        EGL10 mEgl;
        EGLConfig mEglConfig;
        EGLContext mEglContext;
        EGLDisplay mEglDisplay;
        EGLSurface mEglSurface;

        public GL createSurface(SurfaceHolder surfaceHolder) {
            if (this.mEglSurface != null) {
                this.mEgl.eglMakeCurrent(this.mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                this.mEgl.eglDestroySurface(this.mEglDisplay, this.mEglSurface);
            }
            this.mEglSurface = this.mEgl.eglCreateWindowSurface(this.mEglDisplay, this.mEglConfig, (Object)surfaceHolder, null);
            this.mEgl.eglMakeCurrent(this.mEglDisplay, this.mEglSurface, this.mEglSurface, this.mEglContext);
            GL gL = this.mEglContext.getGL();
            if (GLSurfaceView.this.mGLWrapper != null) {
                gL = GLSurfaceView.this.mGLWrapper.wrap(gL);
            }
            return gL;
        }

        public void finish() {
            if (this.mEglSurface != null) {
                this.mEgl.eglMakeCurrent(this.mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                this.mEgl.eglDestroySurface(this.mEglDisplay, this.mEglSurface);
                this.mEglSurface = null;
            }
            if (this.mEglContext != null) {
                this.mEgl.eglDestroyContext(this.mEglDisplay, this.mEglContext);
                this.mEglContext = null;
            }
            if (this.mEglDisplay != null) {
                this.mEgl.eglTerminate(this.mEglDisplay);
                this.mEglDisplay = null;
            }
        }

        public void start(int[] arrn) {
            this.mEgl = (EGL10)EGLContext.getEGL();
            this.mEglDisplay = this.mEgl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            int[] arrn2 = new int[2];
            this.mEgl.eglInitialize(this.mEglDisplay, arrn2);
            EGLConfig[] arreGLConfig = new EGLConfig[1];
            int[] arrn3 = new int[1];
            this.mEgl.eglChooseConfig(this.mEglDisplay, arrn, arreGLConfig, 1, arrn3);
            this.mEglConfig = arreGLConfig[0];
            this.mEglContext = this.mEgl.eglCreateContext(this.mEglDisplay, this.mEglConfig, EGL10.EGL_NO_CONTEXT, null);
            this.mEglSurface = null;
        }

        public boolean swap() {
            this.mEgl.eglSwapBuffers(this.mEglDisplay, this.mEglSurface);
            return this.mEgl.eglGetError() != 12302;
        }
    }

    class GLThread
    extends Thread {
        private boolean mContextLost;
        private boolean mDone = false;
        private EglHelper mEglHelper;
        private Runnable mEvent;
        private Handler mHandler;
        private boolean mHasFocus;
        private boolean mHasSurface;
        private int mHeight = 0;
        private boolean mPaused;
        private Renderer mRenderer;
        private int mWidth = 0;

        GLThread(Renderer renderer) {
            this.mRenderer = renderer;
            this.setName("GLThread");
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Converted monitor instructions to comments
         * Lifted jumps to return sites
         */
        private void guardedRun() throws InterruptedException {
            this.mEglHelper = new EglHelper();
            var1_1 = this.mRenderer.getConfigSpec();
            this.mEglHelper.start(var1_1);
            var2_2 = null;
            var3_3 = true;
            var4_4 = true;
            System.nanoTime();
            do {
                block20 : {
                    block19 : {
                        if (this.mDone) break block19;
                        var7_6 = System.nanoTime();
                        var18_5 = this;
                        // MONITORENTER : var18_5
                        if (this.mEvent != null) {
                            this.mEvent.run();
                            this.mEvent = null;
                        }
                        if (this.mHandler != null) {
                            this.mHandler.getLooper().getThread().run();
                            this.mHandler = null;
                        }
                        if (this.mPaused) {
                            Native.onPause();
                        }
                        if (!this.needToWait()) ** GOTO lbl32
                        break block20;
                    }
lbl24: // 2 sources:
                    do {
                        if (var2_2 != null) {
                            this.mRenderer.shutdown(var2_2);
                        }
                        this.mEglHelper.finish();
                        return;
                        break;
                    } while (true);
                }
                do {
                    block21 : {
                        if (this.needToWait()) break block21;
lbl32: // 2 sources:
                        if (!this.mDone) break;
                        // MONITOREXIT : var18_5
                        ** continue;
                    }
                    this.wait();
                } while (true);
                var10_7 = GLSurfaceView.access$2(GLSurfaceView.this);
                var11_8 = this.mWidth;
                var12_9 = this.mHeight;
                GLSurfaceView.access$3(GLSurfaceView.this, false);
                // MONITOREXIT : var18_5
                if (false) {
                    this.mEglHelper.start(var1_1);
                    var3_3 = true;
                    var10_7 = true;
                }
                if (var10_7) {
                    var2_2 = (GL10)this.mEglHelper.createSurface(GLSurfaceView.access$4(GLSurfaceView.this));
                    var4_4 = true;
                }
                if (var3_3) {
                    this.mRenderer.surfaceCreated(var2_2);
                    var3_3 = false;
                }
                if (var4_4) {
                    this.mRenderer.sizeChanged(var2_2, var11_8, var12_9);
                    var4_4 = false;
                }
                if (var11_8 > 0 && var12_9 > 0) {
                    SpeedCtl.renderTick();
                    this.mEglHelper.swap();
                }
                var13_10 = System.nanoTime() - var7_6;
                var15_11 = (SpeedCtl.interval() - var13_10) / 1000000L;
                if (var15_11 < 10L) {
                    var15_11 = 10L;
                }
                Thread.sleep((long)var15_11);
            } while (true);
        }

        private boolean needToWait() {
            return (this.mPaused || !this.mHasFocus || !this.mHasSurface || this.mContextLost) && !this.mDone;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void clearEvent() {
            GLThread gLThread = this;
            synchronized (gLThread) {
                this.mEvent = null;
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void onPause() {
            GLThread gLThread = this;
            synchronized (gLThread) {
                this.mPaused = true;
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void onResume() {
            GLThread gLThread = this;
            synchronized (gLThread) {
                this.mPaused = false;
                this.notify();
                Native.onResume();
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void onWindowFocusChanged(boolean bl) {
            GLThread gLThread = this;
            synchronized (gLThread) {
                this.mHasFocus = bl;
                if (this.mHasFocus) {
                    this.notify();
                }
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void onWindowResize(int n, int n2) {
            GLThread gLThread = this;
            synchronized (gLThread) {
                this.mWidth = n;
                this.mHeight = n2;
                GLSurfaceView.access$3(GLSurfaceView.this, true);
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void requestExitAndWait() {
            GLThread gLThread = this;
            synchronized (gLThread) {
                this.mDone = true;
                this.notify();
            }
            try {
                this.join();
                return;
            }
            catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        public void run() {
            try {
                try {
                    sEglSemaphore.acquire();
                }
                catch (InterruptedException interruptedException) {
                    sEglSemaphore.release();
                    return;
                }
                try {
                    this.guardedRun();
                    return;
                }
                catch (InterruptedException interruptedException) {
                    sEglSemaphore.release();
                    return;
                }
            }
            finally {
                sEglSemaphore.release();
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void setEvent(Runnable runnable) {
            GLThread gLThread = this;
            synchronized (gLThread) {
                this.mEvent = runnable;
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void setHandler(Handler handler) {
            GLThread gLThread = this;
            synchronized (gLThread) {
                this.mHandler = handler;
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void surfaceCreated() {
            GLThread gLThread = this;
            synchronized (gLThread) {
                this.mHasSurface = true;
                this.mContextLost = false;
                this.notify();
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void surfaceDestroyed() {
            GLThread gLThread = this;
            synchronized (gLThread) {
                this.mHasSurface = false;
                this.notify();
                return;
            }
        }

        public void swap() {
            this.mEglHelper.swap();
        }
    }

    public static interface GLWrapper {
        public GL wrap(GL var1);
    }

    public static interface Renderer {
        public void drawFrame(GL10 var1);

        public int[] getConfigSpec();

        public void shutdown(GL10 var1);

        public void sizeChanged(GL10 var1, int var2, int var3);

        public void surfaceCreated(GL10 var1);
    }

}

