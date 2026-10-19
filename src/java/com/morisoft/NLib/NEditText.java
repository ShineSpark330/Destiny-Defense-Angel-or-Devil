/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.KeyEvent
 *  android.widget.EditText
 *  java.lang.String
 */
package com.morisoft.NLib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;
import com.morisoft.NLib.TextInputWraper;
import org.cocos2dx.lib.Cocos2dxActivity;

public class NEditText
extends EditText {
    private static String TAG = "NEditText";

    public NEditText(Context context) {
        super(context);
    }

    public NEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private static void DEBUG_LOG(String string2) {
        Cocos2dxActivity.DEBUG_LOG(TAG, string2);
    }

    public boolean onKeyPreIme(int n, KeyEvent keyEvent) {
        NEditText.DEBUG_LOG("onKeyPreIme keyCode: " + n);
        if (keyEvent.getAction() == 0) {
            if (TextInputWraper.isShowIME() && n == 4) {
                TextInputWraper.closeIMEKeyboard();
                return true;
            }
            return Cocos2dxActivity.m_Activity.onKeyDown(n, keyEvent);
        }
        return super.onKeyPreIme(n, keyEvent);
    }
}

