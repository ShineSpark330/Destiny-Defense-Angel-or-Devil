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
 *  com.kt.olleh.inapp.KTInAppActivity
 *  com.kt.olleh.inapp.OnInAppListener
 *  com.kt.olleh.inapp.net.Response
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 */
package com.morisoft.NLib.KT;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import com.kt.olleh.inapp.KTInAppActivity;
import com.kt.olleh.inapp.OnInAppListener;
import com.kt.olleh.inapp.net.Response;
import com.morisoft.NLib.ConstInterface;
import com.morisoft.NLib.Native;
import com.morisoft.NLib.NativePopUp;

public class ChargeActivity
extends KTInAppActivity
implements ConstInterface {
    public static ChargeActivity m_Activity;
    private static boolean m_bSetSoIP;
    private String m_AppID;
    private String m_ChargeID;
    OnInAppListener m_InAppListener = new OnInAppListener(){

        /*
         * Enabled aggressive block sorting
         */
        public void OnError(String string2, String string3) {
            if (string2.equals((Object)"I001") || string2.equals((Object)"I002")) {
                Native.itemChargeCancel(0);
            } else {
                int n = -83886080;
                if (string2.startsWith("A")) {
                    n += 65536;
                } else if (string2.startsWith("B")) {
                    n += 131072;
                } else if (string2.startsWith("C")) {
                    n += 196608;
                } else if (string2.startsWith("D")) {
                    n += 262144;
                } else if (string2.startsWith("E")) {
                    n += 327680;
                }
                Native.itemChargeError(n + Integer.parseInt((String)string2.substring(1)));
                NativePopUp.createDialog(3, null, string3);
            }
            ChargeActivity.this.finish();
        }

        public void OnResultAPI(String string2, Response response) {
            this.resultAPI(string2, response);
        }

        public void OnResultFileURL(String string2, String string3) {
        }

        public void OnResultOLDAPI(String string2, String string3) {
            Log.d((String)"KT InApp OnResultOLDAPI", (String)("errorCode: " + string2 + ", message: " + string3));
        }

        public void OnResultPurchase(String string2) {
            Native.itemChargeComplete();
            ChargeActivity.this.finish();
        }

        void resultAPI(String string2, Response response) {
            if (!(string2.equalsIgnoreCase("getUseDiList") || string2.equalsIgnoreCase("getBuyDiList") || string2.equalsIgnoreCase("getAllDiList") || string2.equalsIgnoreCase("getGiftDiList") || string2.equalsIgnoreCase("getReceiveDiList") || string2.equalsIgnoreCase("getDiDetail") || string2.equalsIgnoreCase("getDownDiList") || string2.equalsIgnoreCase("buyDi") || string2.equalsIgnoreCase("buyCancelDi") || string2.equalsIgnoreCase("esBuyDi") || string2.equalsIgnoreCase("giftDi") || string2.equalsIgnoreCase("reGiftDi") || string2.equalsIgnoreCase("approvedUseDi") || string2.equalsIgnoreCase("approvedDownDi") || string2.equalsIgnoreCase("getfile") || string2.equalsIgnoreCase("checkShowId"))) {
                Log.e((String)"KT InApp Result", (String)string2);
            }
        }
    };

    static {
        m_bSetSoIP = false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(1);
        this.m_AppID = null;
        this.m_ChargeID = null;
        Bundle bundle2 = this.getIntent().getExtras();
        if (bundle2 != null) {
            this.m_AppID = bundle2.getString("AppID");
            this.m_ChargeID = bundle2.getString("ChargeID");
            this.setContentView((View)new LinearLayout((Context)this));
            m_Activity = this;
            if (!m_bSetSoIP) {
                this.settingSoIPNumber();
                m_bSetSoIP = true;
            }
            this.init(this.m_InAppListener);
            this.purchase(this.m_AppID, this.m_ChargeID);
            return;
        }
        this.finish();
        Native.timerResume();
    }

    public void onDestroy() {
        super.onDestroy();
    }

}

