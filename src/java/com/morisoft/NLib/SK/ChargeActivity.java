/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.util.Log
 *  android.view.View
 *  android.widget.LinearLayout
 *  android.widget.Toast
 *  com.feelingk.iap.IAPActivity
 *  com.feelingk.iap.IAPLib
 *  com.feelingk.iap.IAPLib$OnClientListener
 *  com.feelingk.iap.IAPLibSetting
 *  com.feelingk.iap.net.ItemAuth
 *  com.feelingk.iap.net.ItemAuthInfo
 *  com.feelingk.iap.net.ItemUse
 *  java.lang.Boolean
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.String
 */
package com.morisoft.NLib.SK;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.feelingk.iap.IAPActivity;
import com.feelingk.iap.IAPLib;
import com.feelingk.iap.IAPLibSetting;
import com.feelingk.iap.net.ItemAuth;
import com.feelingk.iap.net.ItemAuthInfo;
import com.feelingk.iap.net.ItemUse;
import com.morisoft.NLib.ConstInterface;
import com.morisoft.NLib.Native;
import org.cocos2dx.lib.Cocos2dxActivity;

public class ChargeActivity
extends IAPActivity
implements ConstInterface {
    private static String TAG = "SKinApp";
    String AppID = "";
    String ChargePID = "";
    String GameID = "";
    String ItemName = "";
    byte[] content = null;
    IAPLib.OnClientListener mClientListener = new IAPLib.OnClientListener(){

        public void onDlgAutoPurchaseInfoCancel() {
            ChargeActivity.DEBUG_LOG("onDlgAutoPurchaseInfoCancel()");
        }

        public void onDlgError() {
            ChargeActivity.DEBUG_LOG("onDlgError()");
            ChargeActivity.this.finish();
        }

        public void onDlgPurchaseCancel() {
            ChargeActivity.DEBUG_LOG("onDlgPurchaseCancel()");
            ChargeActivity.this.finish();
            Native.itemChargeCancel(-100663296);
        }

        public void onError(int n, int n2) {
            ChargeActivity.DEBUG_LOG("onError(ErrorCode:" + n + ")");
            switch (n) {
                default: 
            }
            Native.itemChargeError(-100663296 + n);
        }

        public void onItemAuthInfo(ItemAuthInfo itemAuthInfo) {
            ChargeActivity.DEBUG_LOG("onItemAuthInfo()");
            String string2 = String.valueOf((Object)"") + ChargeActivity.this.ChargePID + " : " + itemAuthInfo.pCount + " : " + new String(itemAuthInfo.pExpireDate);
            ChargeActivity.this.ShowToast(ChargeActivity.this.getApplicationContext(), string2);
            if (itemAuthInfo.pToken != null) {
                Log.i((String)"Sample", (String)new String(itemAuthInfo.pToken));
            }
        }

        public void onItemPurchaseComplete() {
            ChargeActivity.DEBUG_LOG("onItemPurchaseComplete()");
            Native.itemChargeComplete();
            ChargeActivity.this.finish();
        }

        public Boolean onItemQueryComplete() {
            ChargeActivity.DEBUG_LOG("onItemQueryComplete()");
            return true;
        }

        public void onItemUseQuery(ItemUse itemUse) {
            ChargeActivity.DEBUG_LOG("onItemUseQuery()");
            String string2 = String.valueOf((Object)"") + itemUse.pId + " : " + itemUse.pName + " : " + itemUse.pCount;
            ChargeActivity.this.ShowToast(ChargeActivity.this.getApplicationContext(), string2);
        }

        public void onJoinDialogCancel() {
            ChargeActivity.DEBUG_LOG("onJoinDialogCancel()");
            ChargeActivity.this.finish();
        }

        public void onJuminNumberDlgCancel() {
            ChargeActivity.DEBUG_LOG("onJuminNumberDlgCancel()");
            ChargeActivity.this.finish();
        }

        public void onPurchaseDismiss() {
            ChargeActivity.DEBUG_LOG("onPurchaseDismiss()");
            ChargeActivity.this.finish();
        }

        public void onWholeQuery(ItemAuth[] arritemAuth) {
            ChargeActivity.DEBUG_LOG("onWholeQuery()");
            String string2 = "";
            int n = arritemAuth.length;
            int n2 = 0;
            do {
                if (n2 >= n) {
                    ChargeActivity.this.ShowToast(ChargeActivity.this.getApplicationContext(), string2);
                    return;
                }
                string2 = String.valueOf((Object)string2) + arritemAuth[n2].pId + " : " + arritemAuth[n2].pName + "\n";
                ++n2;
            } while (true);
        }
    };
    byte[] sendData = new byte[93];

    private static void DEBUG_LOG(String string2) {
        Cocos2dxActivity.DEBUG_LOG(TAG, string2);
    }

    public int ByteArrayToInt(byte[] arrby, int n) {
        return (255 & arrby[n + 0]) << 24 | (255 & arrby[n + 1]) << 16 | (255 & arrby[n + 2]) << 8 | 255 & arrby[n + 3];
    }

    public byte[] IntToByteArray(int n, byte[] arrby, int n2) {
        arrby[n2 + 0] = (byte)(255 & (-16777216 & n) >> 24);
        arrby[n2 + 1] = (byte)(255 & (16711680 & n) >> 16);
        arrby[n2 + 2] = (byte)(255 & (65280 & n) >> 8);
        arrby[n2 + 3] = (byte)(255 & (n & 255) >> 0);
        return arrby;
    }

    public void ShowToast(Context context, String string2) {
        Toast.makeText((Context)context, (CharSequence)string2, (int)1).show();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(1);
        Bundle bundle2 = this.getIntent().getExtras();
        if (bundle2 != null) {
            this.AppID = bundle2.getString("AppID");
            this.ChargePID = bundle2.getString("ChargePID");
            this.GameID = bundle2.getString("GameID");
            this.ItemName = bundle2.getString("ItemName");
            this.setContentView((View)new LinearLayout((Context)this));
            IAPLibSetting iAPLibSetting = new IAPLibSetting();
            iAPLibSetting.AppID = this.AppID;
            iAPLibSetting.BP_IP = "211.43.220.197";
            iAPLibSetting.BP_Port = 30607;
            iAPLibSetting.ClientListener = this.mClientListener;
            this.IAPLibInit(iAPLibSetting);
            this.popPurchaseDlg(this.ChargePID, this.ItemName, null, null);
            return;
        }
        this.finish();
        Native.timerResume();
    }

}

