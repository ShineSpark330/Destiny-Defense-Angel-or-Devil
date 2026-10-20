/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.TextView
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.String
 */
package com.morisoft.NLib;

import android.view.View;
import android.widget.TextView;
import com.morisoft.NLib.SpeedCtl;

public class SpeedClick
implements View.OnClickListener {
    public void onClick(View view) {
        String string2 = SpeedCtl.next();
        ((TextView)view).setText((CharSequence)string2);
    }
}

