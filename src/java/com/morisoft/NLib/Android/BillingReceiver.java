/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 */
package com.morisoft.NLib.Android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.morisoft.NLib.Android.Consts;
import com.morisoft.NLib.Android.Purchase;
import com.morisoft.NLib.Native;

public class BillingReceiver
extends BroadcastReceiver {
    private static final String TAG = "Android_BillingReceiver";

    private void checkResponseCode(Context context, long l, int n) {
        Native.itemChargeComplete();
        Purchase.end();
    }

    private void notify(Context context, String string2) {
    }

    private void purchaseStateChanged(Context context, String string2, String string3) {
    }

    public void onReceive(Context context, Intent intent) {
        String string2 = intent.getAction();
        if ("com.android.vending.billing.PURCHASE_STATE_CHANGED".equals((Object)string2)) {
            this.purchaseStateChanged(context, intent.getStringExtra("inapp_signed_data"), intent.getStringExtra("inapp_signature"));
            return;
        }
        if ("com.android.vending.billing.IN_APP_NOTIFY".equals((Object)string2)) {
            this.notify(context, intent.getStringExtra("notification_id"));
            return;
        }
        if ("com.android.vending.billing.RESPONSE_CODE".equals((Object)string2)) {
            this.checkResponseCode(context, intent.getLongExtra("request_id", -1L), intent.getIntExtra("response_code", Consts.ResponseCode.RESULT_ERROR.ordinal()));
            return;
        }
        Log.w((String)TAG, (String)("unexpected action: " + string2));
    }
}

