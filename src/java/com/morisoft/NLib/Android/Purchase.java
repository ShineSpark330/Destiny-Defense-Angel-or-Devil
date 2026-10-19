/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.app.PendingIntent$CanceledException
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentSender
 *  android.content.ServiceConnection
 *  android.os.Bundle
 *  android.os.IBinder
 *  android.os.Parcelable
 *  android.os.RemoteException
 *  android.util.Log
 *  com.android.vending.billing.IMarketBillingService
 *  com.android.vending.billing.IMarketBillingService$Stub
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.NoSuchMethodException
 *  java.lang.Object
 *  java.lang.SecurityException
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.reflect.Method
 */
package com.morisoft.NLib.Android;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.android.vending.billing.IMarketBillingService;
import com.morisoft.NLib.Android.Consts;
import com.morisoft.NLib.NativePopUp;
import java.lang.reflect.Method;
import org.cocos2dx.lib.Cocos2dxActivity;

public class Purchase {
    private static final Class[] START_INTENT_SENDER_SIG;
    private static final String TAG = "Android_Purchase";
    private static Cocos2dxActivity m_Activity;
    private static Purchase m_instance;
    private PurchaseServiceConnection m_purchaseCon;
    private IMarketBillingService m_service;
    private Method m_startIntentSender;
    private Object[] m_startIntentSenderArgs;
    private String m_strBtn1;
    private String m_strBtn2;
    private String m_strMsg;
    private String m_strProductId;
    private String m_strTitle;

    static {
        Class[] arrclass = new Class[]{IntentSender.class, Intent.class, Integer.TYPE, Integer.TYPE, Integer.TYPE};
        START_INTENT_SENDER_SIG = arrclass;
    }

    static /* synthetic */ void access$1(Purchase purchase, IMarketBillingService iMarketBillingService) {
        purchase.m_service = iMarketBillingService;
    }

    public static void end() {
        m_instance.releaseService();
    }

    private void initCompatibilityLayer() {
        try {
            this.m_startIntentSender = m_Activity.getClass().getMethod("startIntentSender", START_INTENT_SENDER_SIG);
            return;
        }
        catch (SecurityException securityException) {
            this.m_startIntentSender = null;
            return;
        }
        catch (NoSuchMethodException noSuchMethodException) {
            this.m_startIntentSender = null;
            return;
        }
    }

    public static void run() {
        m_instance.buy(null);
    }

    public static void runPurchaseService(String string2, String string3, String string4, String string5, String string6) {
        if (m_instance == null) {
            m_instance = new Purchase();
            m_Activity = Cocos2dxActivity.m_Activity;
        }
        m_instance.releaseService();
        m_instance.runService(string2, string3, string4, string5, string6);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean runService(String string2, String string3, String string4, String string5, String string6) {
        this.m_strMsg = string3;
        this.m_strTitle = string2;
        this.m_strBtn1 = string4;
        this.m_strBtn2 = string5;
        this.m_strProductId = string6;
        this.m_startIntentSenderArgs = new Object[5];
        try {
            if (this.m_purchaseCon == null) {
                this.m_purchaseCon = new PurchaseServiceConnection();
                if (m_Activity.bindService(new Intent("com.android.vending.billing.MarketBillingService.BIND"), (ServiceConnection)this.m_purchaseCon, 1)) {
                    Log.i((String)TAG, (String)"Service bind successful.");
                    return true;
                }
                Log.e((String)TAG, (String)"Could not bind to the MarketBillingService");
            }
        }
        catch (Exception exception) {
            Log.e((String)TAG, (String)"Could not bind to the MarketBillingService", (Throwable)exception);
        }
        this.releaseService();
        return false;
    }

    private void startBuyPageActivity(PendingIntent pendingIntent, Intent intent) {
        if (this.m_startIntentSender != null) {
            try {
                this.m_startIntentSenderArgs[0] = pendingIntent.getIntentSender();
                this.m_startIntentSenderArgs[1] = intent;
                this.m_startIntentSenderArgs[2] = 0;
                this.m_startIntentSenderArgs[3] = 0;
                this.m_startIntentSenderArgs[4] = 0;
                this.m_startIntentSender.invoke((Object)m_Activity, this.m_startIntentSenderArgs);
                return;
            }
            catch (Exception exception) {
                Log.e((String)TAG, (String)"error starting activity", (Throwable)exception);
                return;
            }
        }
        try {
            pendingIntent.send((Context)m_Activity, 0, intent);
            return;
        }
        catch (PendingIntent.CanceledException canceledException) {
            Log.e((String)TAG, (String)"error starting activity", (Throwable)canceledException);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected long buy(String string2) {
        try {
            PendingIntent pendingIntent;
            Bundle bundle;
            Bundle bundle2 = this.makeRequestBundle("REQUEST_PURCHASE");
            bundle2.putString("ITEM_ID", this.m_strProductId);
            if (string2 != null) {
                bundle2.putString("DEVELOPER_PAYLOAD", string2);
            }
            if ((pendingIntent = (PendingIntent)(bundle = this.m_service.sendBillingRequest(bundle2)).getParcelable("PURCHASE_INTENT")) == null) {
                Log.e((String)TAG, (String)"Error with requestPurchase");
                return Consts.BILLING_RESPONSE_INVALID_REQUEST_ID;
            }
            this.startBuyPageActivity(pendingIntent, new Intent());
            return bundle.getLong("REQUEST_ID", Consts.BILLING_RESPONSE_INVALID_REQUEST_ID);
        }
        catch (RemoteException remoteException) {
            remoteException.printStackTrace();
            return Consts.BILLING_RESPONSE_INVALID_REQUEST_ID;
        }
    }

    protected boolean checkBillingSupported() {
        Consts.ResponseCode.RESULT_ERROR.ordinal();
        try {
            Bundle bundle = this.makeRequestBundle("CHECK_BILLING_SUPPORTED");
            int n = this.m_service.sendBillingRequest(bundle).getInt("RESPONSE_CODE");
            Log.i((String)TAG, (String)("CheckBillingSupported response code: " + (Object)((Object)Consts.ResponseCode.valueOf(n))));
            int n2 = Consts.ResponseCode.RESULT_OK.ordinal();
            boolean bl = false;
            if (n == n2) {
                bl = true;
            }
            return bl;
        }
        catch (RemoteException remoteException) {
            remoteException.printStackTrace();
            Log.e((String)TAG, (String)"checkBillingSupported error: ", (Throwable)remoteException);
            return false;
        }
    }

    protected Bundle makeRequestBundle(String string2) {
        Log.i((String)TAG, (String)("m_Activity.getPackageName(): " + m_Activity.getPackageName()));
        Bundle bundle = new Bundle();
        bundle.putString("BILLING_REQUEST", string2);
        bundle.putInt("API_VERSION", 1);
        bundle.putString("PACKAGE_NAME", m_Activity.getPackageName());
        return bundle;
    }

    public void releaseService() {
        this.m_strMsg = null;
        this.m_strBtn1 = null;
        this.m_strBtn2 = null;
        this.m_strTitle = null;
        this.m_strProductId = null;
        this.m_startIntentSenderArgs = null;
        if (this.m_purchaseCon != null) {
            Log.i((String)TAG, (String)"releaseService");
            m_Activity.unbindService((ServiceConnection)this.m_purchaseCon);
            this.m_service = null;
            this.m_purchaseCon = null;
            this.m_startIntentSender = null;
            this.m_startIntentSenderArgs = null;
            return;
        }
        Log.e((String)TAG, (String)"releaseService");
    }

    class PurchaseServiceConnection
    implements ServiceConnection {
        PurchaseServiceConnection() {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            block3 : {
                block2 : {
                    if (Purchase.this.m_service != null) break block2;
                    Log.i((String)Purchase.TAG, (String)"MarketBillingService connected");
                    Purchase.access$1(Purchase.this, IMarketBillingService.Stub.asInterface((IBinder)iBinder));
                    if (!Purchase.this.checkBillingSupported()) break block3;
                    Purchase.this.initCompatibilityLayer();
                    NativePopUp.createDialog(7, Purchase.this.m_strTitle, Purchase.this.m_strMsg, Purchase.this.m_strBtn1, Purchase.this.m_strBtn2, null, false, -1);
                }
                return;
            }
            Purchase.this.releaseService();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Purchase.access$1(Purchase.this, null);
        }
    }

}

