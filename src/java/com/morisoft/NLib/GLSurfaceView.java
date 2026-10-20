/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Handler
 *  android.os.IBinder
 *  android.os.Message
 *  android.text.InputFilter
 *  android.text.Spanned
 *  android.text.TextWatcher
 *  android.util.AttributeSet
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnFocusChangeListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.EditText
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
package com.morisoft.NLib;

import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.morisoft.NLib.GLRender;
import com.morisoft.NLib.NEditText;
import com.morisoft.NLib.Native;
import com.morisoft.NLib.NativePopUp;
import com.morisoft.NLib.TextFiledOption;
import com.morisoft.NLib.TextInputWraper;
import com.morisoft.NLib.ogl.GLSurfaceView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.cocos2dx.lib.Cocos2dxActivity;

public class GLSurfaceView
extends com.morisoft.NLib.ogl.GLSurfaceView {
    private static final int IME_FILTER_ASCII = 2;
    private static final int IME_FILTER_KR = 4;
    private static final int IME_FILTER_NONE = 0;
    private static final int IME_FILTER_NUMBER = 1;
    private static Cocos2dxActivity m_Activity;
    private static NEditText m_TextField;
    private static String m_fillter;
    private static InputFilter[] m_filters;
    private static GLSurfaceView surfaceView;
    private static TextInputWraper textInputWraper;
    private Handler handler;
    GLRender mRenderer;

    static {
        m_fillter = null;
        m_filters = new InputFilter[1];
    }

    public GLSurfaceView(Context context) {
        super(context);
        this.initView(context);
    }

    public GLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.initView(context);
    }

    static /* synthetic */ void access$2(String string2) {
        m_fillter = string2;
    }

    public static GLSurfaceView getInstance() {
        return surfaceView;
    }

    public static EditText getTextField() {
        return m_TextField;
    }

    public static void sendMessage(Message message) {
        GLSurfaceView gLSurfaceView = surfaceView;
        if (gLSurfaceView != null) {
            gLSurfaceView.handler.sendMessage(message);
        }
    }

    public void initView(Context context) {
        this.mRenderer = new GLRender();
        this.setRenderer(this.mRenderer);
        surfaceView = this;
        textInputWraper = new TextInputWraper();
        m_Activity = Cocos2dxActivity.m_Activity;
        surfaceView.requestFocus();
        this.handler = new Handler(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public void handleMessage(Message message) {
                switch (message.what) {
                    default: {
                        return;
                    }
                    case 2: {
                        Cocos2dxActivity.DEBUG_LOG("TextInputWraper", "HANDLER_OPEN_KEYBOARD");
                        TextFiledOption textFiledOption = (TextFiledOption)message.obj;
                        m_TextField.removeTextChangedListener((TextWatcher)textInputWraper);
                        if ((1 & textFiledOption.inputType) != 0) {
                            switch (1 ^ textFiledOption.inputType) {
                                default: {
                                    break;
                                }
                                case 16: 
                                case 32: {
                                    m_TextField.setPrivateImeOptions("defaultInputmdoe=english;");
                                    textFiledOption.filterType = 3;
                                    break;
                                }
                            }
                        } else if ((2 & textFiledOption.inputType) != 0) {
                            textFiledOption.filterType = 1;
                        }
                        GLSurfaceView.access$2("");
                        if ((2 & textFiledOption.filterType) != 0) {
                            GLSurfaceView.access$2(String.valueOf((Object)m_fillter) + "!-/:-~");
                        }
                        if ((1 & textFiledOption.filterType) != 0) {
                            GLSurfaceView.access$2(String.valueOf((Object)m_fillter) + "0-9");
                        }
                        if ((4 & textFiledOption.filterType) != 0) {
                            GLSurfaceView.access$2(String.valueOf((Object)m_fillter) + "\u3131-\u3163\uac00-\ud7a3");
                        }
                        if (textFiledOption.filterType != 0) {
                            GLSurfaceView.access$2("^[" + m_fillter + "]*$");
                            GLSurfaceView.access$4()[0] = new InputFilter(){

                                public CharSequence filter(CharSequence charSequence, int n, int n2, Spanned spanned, int n3, int n4) {
                                    if (!Pattern.compile((String)m_fillter).matcher(charSequence).matches()) {
                                        return "";
                                    }
                                    return null;
                                }
                            };
                            m_TextField.setFilters(m_filters);
                        }
                        m_TextField.setText((CharSequence)"");
                        m_TextField.setInputType(textFiledOption.inputType);
                        m_TextField.setImeOptions(textFiledOption.imeOption);
                        m_TextField.setMaxLines(textFiledOption.maxLine);
                        m_TextField.append((CharSequence)textFiledOption.text);
                        textInputWraper.initialize(textFiledOption.bufferSize, textFiledOption.imeOption, textFiledOption.charsetName);
                        m_TextField.addTextChangedListener((TextWatcher)textInputWraper);
                        m_TextField.setOnEditorActionListener((TextView.OnEditorActionListener)textInputWraper);
                        m_TextField.setOnFocusChangeListener((View.OnFocusChangeListener)textInputWraper);
                        ((InputMethodManager)m_Activity.getSystemService("input_method")).showSoftInput((View)m_TextField, 2);
                        m_TextField.requestFocus();
                        TextInputWraper.setShowIME(true);
                        return;
                    }
                    case 3: 
                }
                m_TextField.removeTextChangedListener((TextWatcher)textInputWraper);
                try {
                    ((InputMethodManager)surfaceView.getContext().getSystemService("input_method")).hideSoftInputFromWindow(m_TextField.getWindowToken(), 0);
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
                surfaceView.requestFocus();
                TextInputWraper.setShowIME(false);
                m_TextField.setVisibility(0);
                Cocos2dxActivity.DEBUG_LOG("TextInputWraper", "HANDLER_CLOSE_KEYBOARD");
            }

        };
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (NativePopUp.isShowProgressBar()) {
            return false;
        }
        int n = motionEvent.getPointerCount();
        if (n > 5) return false;
        switch (255 & motionEvent.getAction()) {
            case 3: {
                float[] arrf = new float[n];
                float[] arrf2 = new float[n];
                int n2 = 0;
                do {
                    if (n2 >= n) {
                        Native.touchesCancelArray(n, arrf, arrf2);
                        return true;
                    }
                    arrf[n2] = motionEvent.getX(n2);
                    arrf2[n2] = motionEvent.getY(n2) + (float)Cocos2dxActivity.touchScreenHeight;
                    ++n2;
                } while (true);
            }
            case 2: {
                float[] arrf = new float[n];
                float[] arrf3 = new float[n];
                int n3 = 0;
                do {
                    if (n3 >= n) {
                        Native.touchesMoveArray(n, arrf, arrf3);
                        return true;
                    }
                    arrf[n3] = motionEvent.getX(n3);
                    arrf3[n3] = motionEvent.getY(n3) + (float)Cocos2dxActivity.touchScreenHeight;
                    ++n3;
                } while (true);
            }
            case 0: 
            case 5: {
                int n4 = motionEvent.getAction() >> 8;
                Native.touchesBegin(n, n4, motionEvent.getX(n4), motionEvent.getY(n4) + (float)Cocos2dxActivity.touchScreenHeight);
                return true;
            }
            case 1: 
            case 6: {
                int n5 = motionEvent.getAction() >> 8;
                Native.touchesEnd(n, n5, motionEvent.getX(n5), motionEvent.getY(n5) + (float)Cocos2dxActivity.touchScreenHeight);
                break;
            }
        }
        return true;
    }

    public void setTextField(EditText editText) {
        m_TextField = (NEditText)editText;
        m_TextField.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, 0, 0.0f));
        surfaceView.requestFocus();
        TextInputWraper.openIMEKeyboard("", 10, 1, 2, 6, 1, "utf-8");
        m_TextField.setVisibility(4);
        TextInputWraper.closeIMEKeyboard();
    }

}

