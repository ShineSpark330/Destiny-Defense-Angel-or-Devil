/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.util.Log
 *  android.view.View
 *  android.widget.LinearLayout
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
package com.morisoft.NLib.INIPay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import com.morisoft.NLib.Native;
import java.util.HashMap;

public class INIPayResultActivity
extends Activity {
    /*
     * Enabled aggressive block sorting
     */
    private HashMap getData(String string2) {
        HashMap hashMap = new HashMap();
        String[] arrstring = string2.split("&");
        int n = 0;
        while (n < arrstring.length) {
            int n2;
            int n3;
            if (arrstring[n] != null && (n2 = arrstring[n].length()) > 0 && (n3 = arrstring[n].indexOf("=")) > -1) {
                String string3 = arrstring[n].substring(0, n3).trim();
                String string4 = n2 == n3 + 1 ? "" : arrstring[n].substring(n3 + 1).trim();
                hashMap.put((Object)string3, (Object)string4);
            }
            ++n;
        }
        return hashMap;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView((View)new LinearLayout((Context)this));
        HashMap hashMap = this.getData(this.getIntent().getDataString().substring(23));
        String string2 = (String)hashMap.get((Object)"param1");
        String string3 = (String)hashMap.get((Object)"param2");
        Log.i((String)"INIPayResultActivity", (String)("INIPayResultActivity param1: " + string2));
        Log.i((String)"INIPayResultActivity", (String)("INIPayResultActivity param2: " + string3));
        if (string3.equals((Object)"SUCCESS")) {
            Native.itemChargeComplete();
        } else {
            Native.itemChargeError(-1);
        }
        this.finish();
    }
}

