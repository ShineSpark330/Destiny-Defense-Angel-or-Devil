/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.util.Log
 *  com.samsungapps.plasma.ItemInformation
 *  com.samsungapps.plasma.Plasma
 *  com.samsungapps.plasma.PlasmaListener
 *  com.samsungapps.plasma.PurchasedItemInformation
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.util.ArrayList
 *  java.util.Date
 */
package com.morisoft.NLib.Samsung;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.morisoft.NLib.ConstInterface;
import com.morisoft.NLib.Native;
import com.samsungapps.plasma.ItemInformation;
import com.samsungapps.plasma.Plasma;
import com.samsungapps.plasma.PlasmaListener;
import com.samsungapps.plasma.PurchasedItemInformation;
import java.util.ArrayList;
import java.util.Date;
import org.cocos2dx.lib.Cocos2dxActivity;

public class Purchase
extends Activity
implements ConstInterface,
PlasmaListener {
    static String TAG = "SamsungInApp";
    private static Cocos2dxActivity m_Activity;
    private static boolean s_bDeveloperFlag;
    private static Plasma s_plasma;
    private static String s_strITEM_GROUP_ID;
    private static String s_strPurchaseID;
    private static int s_transactionId;

    static {
        s_plasma = null;
        s_transactionId = 0;
        s_bDeveloperFlag = false;
        s_strITEM_GROUP_ID = null;
        s_strPurchaseID = null;
        m_Activity = null;
    }

    private static void DEBUG_LOG(String string2) {
        Cocos2dxActivity.DEBUG_LOG(TAG, string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private String getPriceStringWithCurrencyUnit(ItemInformation itemInformation) {
        String string2 = itemInformation.getCurrencyUnitHasPenny() ? "%.2f" : "%.0f";
        Object[] arrobject = new Object[]{itemInformation.getItemPrice()};
        String string3 = String.format((String)string2, (Object[])arrobject);
        StringBuffer stringBuffer = new StringBuffer();
        if (itemInformation.getCurrencyUnitPrecedes()) {
            stringBuffer.append(itemInformation.getCurrencyUnit());
            stringBuffer.append(string3);
            return stringBuffer.toString();
        }
        stringBuffer.append(string3);
        stringBuffer.append(itemInformation.getCurrencyUnit());
        return stringBuffer.toString();
    }

    public static void purchase(String string2, String string3, String string4, boolean bl) {
        s_bDeveloperFlag = bl;
        s_strITEM_GROUP_ID = string2;
        s_strPurchaseID = string3;
        m_Activity = Cocos2dxActivity.m_Activity;
        m_Activity.startActivity(new Intent((Context)m_Activity, Purchase.class));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (s_plasma == null) {
            s_plasma = new Plasma(s_strITEM_GROUP_ID, (Activity)this);
            s_plasma.setPlasmaListener((PlasmaListener)this);
            if (s_bDeveloperFlag) {
                s_plasma.setDeveloperFlag(1);
            }
        }
        Plasma plasma = s_plasma;
        int n = s_transactionId;
        s_transactionId = n + 1;
        plasma.requestPurchaseItem(n, s_strPurchaseID);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onItemInformationListReceived(int n, int n2, ArrayList<ItemInformation> arrayList) {
        Purchase.DEBUG_LOG("onItemInformationListReceived(transactionId: " + n + ", statusCode: " + n2 + ")");
        switch (n2) {
            default: {
                Log.e((String)TAG, (String)"Failed to retrieve the item list");
                break;
            }
            case 0: {
                Purchase.DEBUG_LOG("STATUS_CODE_SUCCESS");
                for (int i = 0; i < arrayList.size(); ++i) {
                    ItemInformation itemInformation = (ItemInformation)arrayList.get(i);
                    Purchase.DEBUG_LOG(String.valueOf((int)i) + " getItemId: " + itemInformation.getItemId());
                    Purchase.DEBUG_LOG(String.valueOf((int)i) + " getItemName: " + itemInformation.getItemName());
                    Purchase.DEBUG_LOG(String.valueOf((int)i) + " getItemPrice: " + itemInformation.getItemPrice());
                    Purchase.DEBUG_LOG(String.valueOf((int)i) + " getItemPrice: " + this.getPriceStringWithCurrencyUnit(itemInformation));
                    Purchase.DEBUG_LOG(String.valueOf((int)i) + " getItemDescription: " + itemInformation.getItemDescription());
                }
            }
        }
        this.finish();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onPurchaseItemFinished(int n, int n2, PurchasedItemInformation purchasedItemInformation) {
        Purchase.DEBUG_LOG("onPurchaseItemFinished(transactionId: " + n + ", statusCode: " + n2 + ")");
        switch (n2) {
            default: {
                Native.itemChargeError(-1);
                break;
            }
            case 0: {
                Purchase.DEBUG_LOG("STATUS_CODE_SUCCESS");
                Purchase.DEBUG_LOG("getItemId: " + purchasedItemInformation.getItemId());
                Purchase.DEBUG_LOG("getItemName: " + purchasedItemInformation.getItemName());
                Purchase.DEBUG_LOG("getPaymentId: " + purchasedItemInformation.getPaymentId());
                Purchase.DEBUG_LOG("getPurchaseDate: " + (Object)purchasedItemInformation.getPurchaseDate());
                Purchase.DEBUG_LOG("getItemPrice: " + purchasedItemInformation.getItemPrice());
                Purchase.DEBUG_LOG("getItemPrice: " + this.getPriceStringWithCurrencyUnit((ItemInformation)purchasedItemInformation));
                Purchase.DEBUG_LOG("getItemDescription: " + purchasedItemInformation.getItemDescription());
                Native.itemChargeComplete();
                break;
            }
            case 100: {
                Native.itemChargeCancel(0);
            }
        }
        this.finish();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onPurchasedItemInformationListReceived(int n, int n2, ArrayList<PurchasedItemInformation> arrayList) {
        Purchase.DEBUG_LOG("onPurchasedItemInformationListReceived(transactionId: " + n + ", statusCode: " + n2 + ")");
        switch (n2) {
            default: {
                Log.e((String)TAG, (String)"Failed to retrieve the purchase list");
                break;
            }
            case 0: {
                Purchase.DEBUG_LOG("STATUS_CODE_SUCCESS");
                for (int i = 0; i < arrayList.size(); ++i) {
                    PurchasedItemInformation purchasedItemInformation = (PurchasedItemInformation)arrayList.get(i);
                    Purchase.DEBUG_LOG(String.valueOf((int)i) + " getItemId: " + purchasedItemInformation.getItemId());
                    Purchase.DEBUG_LOG(String.valueOf((int)i) + " getItemName: " + purchasedItemInformation.getItemName());
                    Purchase.DEBUG_LOG(String.valueOf((int)i) + " getPaymentId: " + purchasedItemInformation.getPaymentId());
                    Purchase.DEBUG_LOG(String.valueOf((int)i) + " getPurchaseDate: " + (Object)purchasedItemInformation.getPurchaseDate());
                    Purchase.DEBUG_LOG(String.valueOf((int)i) + " getItemPrice: " + purchasedItemInformation.getItemPrice());
                    Purchase.DEBUG_LOG(String.valueOf((int)i) + " getItemPrice: " + this.getPriceStringWithCurrencyUnit((ItemInformation)purchasedItemInformation));
                    Purchase.DEBUG_LOG(String.valueOf((int)i) + " getItemDescription: " + purchasedItemInformation.getItemDescription());
                }
            }
        }
        this.finish();
    }
}

