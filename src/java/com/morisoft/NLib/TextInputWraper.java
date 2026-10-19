/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.os.Message
 *  android.text.Editable
 *  android.text.TextWatcher
 *  android.view.KeyEvent
 *  android.view.View
 *  android.view.View$OnFocusChangeListener
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  java.io.UnsupportedEncodingException
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.String
 */
package com.morisoft.NLib;

import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.morisoft.NLib.ConstInterface;
import com.morisoft.NLib.GLSurfaceView;
import com.morisoft.NLib.Native;
import com.morisoft.NLib.TextFiledOption;
import java.io.UnsupportedEncodingException;
import org.cocos2dx.lib.Cocos2dxActivity;

public class TextInputWraper
implements TextWatcher,
View.OnFocusChangeListener,
TextView.OnEditorActionListener,
ConstInterface {
    public static final int HANDLER_CLOSE_KEYBOARD = 3;
    public static final int HANDLER_OPEN_KEYBOARD = 2;
    public static final String TAG = "TextInputWraper";
    private static boolean m_bShowIME = false;
    String m_charsetName;
    int m_imeOption;
    int m_maxLength;

    public static void DEBUG_LOG(String string2) {
        Cocos2dxActivity.DEBUG_LOG(TAG, string2);
    }

    public static void closeIMEKeyboard() {
        Message message = new Message();
        message.what = 3;
        GLSurfaceView.sendMessage(message);
    }

    public static boolean isShowIME() {
        return m_bShowIME;
    }

    public static void openIMEKeyboard(String string2, int n, int n2, int n3, int n4, int n5, String string3) {
        Message message = new Message();
        message.what = 2;
        message.obj = new TextFiledOption(string2, n, n2, n3, n4, n5, string3);
        GLSurfaceView.sendMessage(message);
    }

    public static void setShowIME(boolean bl) {
        m_bShowIME = bl;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void afterTextChanged(Editable editable) {
        TextInputWraper.DEBUG_LOG("afterTextChanged");
        byte[] arrby = null;
        String string2 = editable.toString();
        try {
            arrby = string2.getBytes(this.m_charsetName);
            if (arrby.length >= this.m_maxLength) {
                string2 = string2.substring(0, -1 + string2.length());
                arrby = null;
                arrby = string2.getBytes(this.m_charsetName);
                GLSurfaceView.getTextField().removeTextChangedListener((TextWatcher)this);
                GLSurfaceView.getTextField().setText((CharSequence)"");
                GLSurfaceView.getTextField().append((CharSequence)string2);
                GLSurfaceView.getTextField().addTextChangedListener((TextWatcher)this);
            }
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {
            TextInputWraper.DEBUG_LOG("Exception: " + unsupportedEncodingException.getMessage());
            unsupportedEncodingException.printStackTrace();
        }
        TextInputWraper.DEBUG_LOG("mOpt.maxLength: " + this.m_maxLength);
        TextInputWraper.DEBUG_LOG("mText: " + string2);
        Native.inputText(arrby.length, arrby);
        (byte[])null;
    }

    public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
        TextInputWraper.DEBUG_LOG("beforeTextChanged");
    }

    public void initialize(int n, int n2, String string2) {
        this.m_maxLength = n;
        this.m_imeOption = n2;
        this.m_charsetName = string2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onEditorAction(TextView textView, int n, KeyEvent keyEvent) {
        TextInputWraper.DEBUG_LOG("onEditorAction actionId: " + n);
        TextInputWraper.DEBUG_LOG("onEditorAction getImeOptions: " + GLSurfaceView.getTextField().getImeOptions());
        int n2 = n & 255;
        if (n2 != this.m_imeOption) {
            GLSurfaceView.getTextField().setImeOptions(this.m_imeOption);
            return false;
        }
        if (n2 != 6) return false;
        TextInputWraper.closeIMEKeyboard();
        Native.keyBegin(4095);
        return false;
    }

    public void onFocusChange(View view, boolean bl) {
        TextInputWraper.DEBUG_LOG("onFocusChange hasFocus: " + bl);
    }

    public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
        TextInputWraper.DEBUG_LOG("onTextChanged");
        GLSurfaceView.getTextField().setImeOptions(this.m_imeOption);
    }
}

