/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.Editable
 *  android.text.TextWatcher
 *  android.util.Log
 *  android.view.KeyEvent
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  java.lang.Boolean
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.String
 */
package org.cocos2dx.lib;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;

class TextInputWraper
implements TextWatcher,
TextView.OnEditorActionListener {
    private static final Boolean debug = false;
    private Cocos2dxGLSurfaceView mMainView;
    private String mOriginText;
    private String mText;

    public TextInputWraper(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView) {
        this.mMainView = cocos2dxGLSurfaceView;
    }

    private void LogD(String string2) {
        if (debug.booleanValue()) {
            Log.d((String)"TextInputFilter", (String)string2);
        }
    }

    private Boolean isFullScreenEdit() {
        return ((InputMethodManager)this.mMainView.getTextField().getContext().getSystemService("input_method")).isFullscreenMode();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void afterTextChanged(Editable var1_1) {
        if (this.isFullScreenEdit().booleanValue()) {
            return;
        }
        this.LogD("afterTextChanged: " + (Object)var1_1);
        var2_2 = var1_1.length() - this.mText.length();
        if (var2_2 <= 0) ** GOTO lbl15
        var3_3 = var1_1.subSequence(this.mText.length(), var1_1.length()).toString();
        this.mMainView.insertText(var3_3);
        this.LogD("insertText(" + var3_3 + ")");
lbl9: // 2 sources:
        do {
            this.mText = var1_1.toString();
            return;
            break;
        } while (true);
lbl-1000: // 1 sources:
        {
            this.mMainView.deleteBackward();
            this.LogD("deleteBackward");
            ++var2_2;
lbl15: // 2 sources:
            ** while (var2_2 < 0)
        }
lbl16: // 1 sources:
        ** while (true)
    }

    public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
        this.LogD("beforeTextChanged(" + (Object)charSequence + ")start: " + n + ",count: " + n2 + ",after: " + n3);
        this.mText = charSequence.toString();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean onEditorAction(TextView textView, int n, KeyEvent keyEvent) {
        if (this.mMainView.getTextField() != textView || !this.isFullScreenEdit().booleanValue()) return false;
        int n2 = this.mOriginText.length();
        do {
            if (n2 <= 0) {
                String string2 = textView.getText().toString();
                if (string2.compareTo("") == 0) {
                    string2 = "\n";
                }
                if ('\n' != string2.charAt(-1 + string2.length())) {
                    string2 = String.valueOf((Object)string2) + '\n';
                }
                String string3 = string2;
                this.mMainView.insertText(string3);
                this.LogD("insertText(" + string3 + ")");
                return false;
            }
            this.mMainView.deleteBackward();
            this.LogD("deleteBackward");
            --n2;
        } while (true);
    }

    public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
    }

    public void setOriginText(String string2) {
        this.mOriginText = string2;
    }
}

