/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.opengl.GLSurfaceView
 *  android.opengl.GLSurfaceView$Renderer
 *  android.os.Handler
 *  android.os.IBinder
 *  android.os.Message
 *  android.text.TextWatcher
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.KeyEvent
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.StringBuilder
 */
package org.cocos2dx.lib;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import org.cocos2dx.lib.Cocos2dxEditText;
import org.cocos2dx.lib.Cocos2dxRenderer;
import org.cocos2dx.lib.TextInputWraper;

public class Cocos2dxGLSurfaceView
extends GLSurfaceView {
    private static final int HANDLER_CLOSE_IME_KEYBOARD = 3;
    private static final int HANDLER_OPEN_IME_KEYBOARD = 2;
    private static final String TAG = Cocos2dxGLSurfaceView.class.getCanonicalName();
    private static final boolean debug;
    private static Handler handler;
    private static Cocos2dxGLSurfaceView mainView;
    private static TextInputWraper textInputWraper;
    private Cocos2dxRenderer mRenderer;
    private Cocos2dxEditText mTextField;

    public Cocos2dxGLSurfaceView(Context context) {
        super(context);
        this.initView();
    }

    public Cocos2dxGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.initView();
    }

    public static void closeIMEKeyboard() {
        Message message = new Message();
        message.what = 3;
        handler.sendMessage(message);
    }

    private void dumpEvent(MotionEvent motionEvent) {
        String[] arrstring = new String[]{"DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?"};
        StringBuilder stringBuilder = new StringBuilder();
        int n = motionEvent.getAction();
        int n2 = n & 255;
        stringBuilder.append("event ACTION_").append(arrstring[n2]);
        if (n2 == 5 || n2 == 6) {
            stringBuilder.append("(pid ").append(n >> 8);
            stringBuilder.append(")");
        }
        stringBuilder.append("[");
        int n3 = 0;
        do {
            if (n3 >= motionEvent.getPointerCount()) {
                stringBuilder.append("]");
                Log.d((String)TAG, (String)stringBuilder.toString());
                return;
            }
            stringBuilder.append("#").append(n3);
            stringBuilder.append("(pid ").append(motionEvent.getPointerId(n3));
            stringBuilder.append(")=").append((int)motionEvent.getX(n3));
            stringBuilder.append(",").append((int)motionEvent.getY(n3));
            if (n3 + 1 < motionEvent.getPointerCount()) {
                stringBuilder.append(";");
            }
            ++n3;
        } while (true);
    }

    private String getContentText() {
        return this.mRenderer.getContentText();
    }

    public static void openIMEKeyboard() {
        Message message = new Message();
        message.what = 2;
        message.obj = mainView.getContentText();
        handler.sendMessage(message);
    }

    public void deleteBackward() {
        this.queueEvent(new Runnable(){

            public void run() {
                Cocos2dxGLSurfaceView.this.mRenderer.handleDeleteBackward();
            }
        });
    }

    public TextView getTextField() {
        return this.mTextField;
    }

    protected void initView() {
        this.mRenderer = new Cocos2dxRenderer();
        this.setFocusableInTouchMode(true);
        this.setRenderer((GLSurfaceView.Renderer)this.mRenderer);
        textInputWraper = new TextInputWraper(this);
        handler = new Handler(){

            /*
             * Enabled aggressive block sorting
             */
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 2: {
                        if (Cocos2dxGLSurfaceView.this.mTextField == null || !Cocos2dxGLSurfaceView.this.mTextField.requestFocus()) return;
                        {
                            Cocos2dxGLSurfaceView.this.mTextField.removeTextChangedListener((TextWatcher)textInputWraper);
                            Cocos2dxGLSurfaceView.this.mTextField.setText((CharSequence)"");
                            String string2 = (String)message.obj;
                            Cocos2dxGLSurfaceView.this.mTextField.append((CharSequence)string2);
                            textInputWraper.setOriginText(string2);
                            Cocos2dxGLSurfaceView.this.mTextField.addTextChangedListener((TextWatcher)textInputWraper);
                            ((InputMethodManager)mainView.getContext().getSystemService("input_method")).showSoftInput((View)Cocos2dxGLSurfaceView.this.mTextField, 0);
                            Log.d((String)"GLSurfaceView", (String)"showSoftInput");
                            return;
                        }
                    }
                    default: {
                        return;
                    }
                    case 3: {
                        if (Cocos2dxGLSurfaceView.this.mTextField == null) return;
                        Cocos2dxGLSurfaceView.this.mTextField.removeTextChangedListener((TextWatcher)textInputWraper);
                        ((InputMethodManager)mainView.getContext().getSystemService("input_method")).hideSoftInputFromWindow(Cocos2dxGLSurfaceView.this.mTextField.getWindowToken(), 0);
                        Log.d((String)"GLSurfaceView", (String)"HideSoftInput");
                        return;
                    }
                }
            }
        };
        mainView = this;
    }

    public void insertText(final String string2) {
        this.queueEvent(new Runnable(){

            public void run() {
                Cocos2dxGLSurfaceView.this.mRenderer.handleInsertText(string2);
            }
        });
    }

    public boolean onKeyDown(final int n, KeyEvent keyEvent) {
        if (n == 4 || n == 82) {
            this.queueEvent(new Runnable(){

                public void run() {
                    Cocos2dxGLSurfaceView.this.mRenderer.handleKeyDown(n);
                }
            });
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }

    public void onPause() {
        this.queueEvent(new Runnable(){

            public void run() {
                Cocos2dxGLSurfaceView.this.mRenderer.handleOnPause();
            }
        });
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.queueEvent(new Runnable(){

            public void run() {
                Cocos2dxGLSurfaceView.this.mRenderer.handleOnResume();
            }
        });
    }

    protected void onSizeChanged(int n, int n2, int n3, int n4) {
        this.mRenderer.setScreenWidthAndHeight(n, n2);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     */
    public boolean onTouchEvent(MotionEvent var1_1) {
        var2_2 = var1_1.getPointerCount();
        var3_3 = new int[var2_2];
        var4_4 = new float[var2_2];
        var5_5 = new float[var2_2];
        var6_6 = 0;
        block8 : do {
            if (var6_6 < var2_2) ** GOTO lbl15
            switch (255 & var1_1.getAction()) {
                case 3: {
                    var7_26 = new Runnable(){

                        public void run() {
                            Cocos2dxGLSurfaceView.this.mRenderer.handleActionCancel(var3_3, var4_4, var5_5);
                        }
                    };
                    this.queueEvent(var7_26);
                    ** break;
                }
lbl13: // 2 sources:
                default: {
                    return true;
                }
lbl15: // 1 sources:
                var3_3[var6_6] = var1_1.getPointerId(var6_6);
                var4_4[var6_6] = var1_1.getX(var6_6);
                var5_5[var6_6] = var1_1.getY(var6_6);
                ++var6_6;
                continue block8;
                case 5: {
                    var22_7 = var1_1.getAction() >> 8;
                    var23_8 = var1_1.getPointerId(var22_7);
                    var24_9 = var1_1.getX(var22_7);
                    var25_10 = var1_1.getY(var22_7);
                    var26_11 = new Runnable(){

                        public void run() {
                            Cocos2dxGLSurfaceView.this.mRenderer.handleActionDown(var23_8, var24_9, var25_10);
                        }
                    };
                    this.queueEvent(var26_11);
                    return true;
                }
                case 0: {
                    var18_12 = var1_1.getPointerId(0);
                    var19_13 = var4_4[0];
                    var20_14 = var5_5[0];
                    var21_15 = new Runnable(){

                        public void run() {
                            Cocos2dxGLSurfaceView.this.mRenderer.handleActionDown(var18_12, var19_13, var20_14);
                        }
                    };
                    this.queueEvent(var21_15);
                    return true;
                }
                case 2: {
                    var17_16 = new Runnable(){

                        public void run() {
                            Cocos2dxGLSurfaceView.this.mRenderer.handleActionMove(var3_3, var4_4, var5_5);
                        }
                    };
                    this.queueEvent(var17_16);
                    return true;
                }
                case 6: {
                    var12_17 = var1_1.getAction() >> 8;
                    var13_18 = var1_1.getPointerId(var12_17);
                    var14_19 = var1_1.getX(var12_17);
                    var15_20 = var1_1.getY(var12_17);
                    var16_21 = new Runnable(){

                        public void run() {
                            Cocos2dxGLSurfaceView.this.mRenderer.handleActionUp(var13_18, var14_19, var15_20);
                        }
                    };
                    this.queueEvent(var16_21);
                    return true;
                }
                case 1: 
            }
            break;
        } while (true);
        var8_22 = var1_1.getPointerId(0);
        var9_23 = var4_4[0];
        var10_24 = var5_5[0];
        var11_25 = new Runnable(){

            public void run() {
                Cocos2dxGLSurfaceView.this.mRenderer.handleActionUp(var8_22, var9_23, var10_24);
            }
        };
        this.queueEvent(var11_25);
        return true;
    }

    public void setTextField(Cocos2dxEditText cocos2dxEditText) {
        this.mTextField = cocos2dxEditText;
        if (this.mTextField != null && textInputWraper != null) {
            this.mTextField.setOnEditorActionListener((TextView.OnEditorActionListener)textInputWraper);
            this.mTextField.setMainView(this);
            this.requestFocus();
        }
    }

}

