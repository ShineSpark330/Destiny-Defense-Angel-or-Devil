/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.KeyEvent
 *  android.widget.EditText
 */
package org.cocos2dx.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;

public class Cocos2dxEditText
extends EditText {
    private Cocos2dxGLSurfaceView mView;

    public Cocos2dxEditText(Context context) {
        super(context);
    }

    public Cocos2dxEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Cocos2dxEditText(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    public boolean onKeyDown(int n, KeyEvent keyEvent) {
        super.onKeyDown(n, keyEvent);
        if (n == 4) {
            this.mView.requestFocus();
        }
        return true;
    }

    public void setMainView(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView) {
        this.mView = cocos2dxGLSurfaceView;
    }
}

