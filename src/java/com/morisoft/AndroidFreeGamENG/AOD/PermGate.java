/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  java.lang.Class
 *  java.lang.String
 */
package com.morisoft.AndroidFreeGamENG.AOD;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.morisoft.AndroidFreeGamENG.AOD.AOD;

public class PermGate
extends Activity {
    private void go() {
        this.startActivity(new Intent((Context)this, AOD.class));
        this.finish();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 23 && this.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            this.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 100);
            return;
        }
        this.go();
    }

    public void onRequestPermissionsResult(int n, String[] arrstring, int[] arrn) {
        this.go();
    }
}

