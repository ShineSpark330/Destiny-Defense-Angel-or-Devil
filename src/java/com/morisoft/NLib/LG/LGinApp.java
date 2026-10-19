/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.view.View
 *  android.widget.LinearLayout
 *  android.widget.Toast
 *  com.feelingk.lguiab.LguIAPLib
 *  com.feelingk.lguiab.LguIAPLib$OnClientListener
 *  com.feelingk.lguiab.util.Logger
 *  com.feelingk.lguiab.vo.IAPLibSetting
 *  com.feelingk.lguiab.vo.ProductItemInfo
 *  com.feelingk.lguiab.vo.UseItemInfo
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Iterator
 *  java.util.List
 */
package com.morisoft.NLib.LG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.feelingk.lguiab.LguIAPLib;
import com.feelingk.lguiab.util.Logger;
import com.feelingk.lguiab.vo.IAPLibSetting;
import com.feelingk.lguiab.vo.ProductItemInfo;
import com.feelingk.lguiab.vo.UseItemInfo;
import com.morisoft.NLib.ConstInterface;
import com.morisoft.NLib.Native;
import java.util.Iterator;
import java.util.List;
import org.cocos2dx.lib.Cocos2dxActivity;

public class LGinApp
extends LguIAPLib
implements ConstInterface {
    static String TAG = "LGinApp";
    static Cocos2dxActivity m_Activity;
    static String m_AppID;
    static String m_GameID;
    static String m_IP;
    static String m_PORT;
    static String m_PurchaseID;
    static boolean serverType;
    private LguIAPLib.OnClientListener mClientListener = new LguIAPLib.OnClientListener(){

        public void lguIABonDlgClick() {
            LGinApp.this.finish();
            LGinApp.DEBUG_LOG("lguIABonDlgClick()");
        }

        public void lguIABonDlgPurchaseCancel() {
            LGinApp.this.finish();
            LGinApp.DEBUG_LOG("lguIABonDlgPurchaseCancel()");
            Native.itemChargeCancel(-100663296);
        }

        public void lguIABonError(int n, int n2) {
            Object[] arrobject = new Object[]{n, n2};
            LGinApp.DEBUG_LOG(String.format((String)"lguIABonError: ResultCode=%d Status=%d", (Object[])arrobject));
            Native.itemChargeError(-1);
            if (n2 == 5 || n2 == 15) {
                LGinApp.this.finish();
            }
        }

        public void lguIABonItemAuthInfo(ProductItemInfo productItemInfo) {
            LGinApp.this.finish();
            LGinApp.DEBUG_LOG("lguIABonItemAuthInfo()");
            String string2 = String.valueOf((Object)"[endDate]\n") + "year : " + productItemInfo.getEndDate().substring(0, 4) + "\n" + "month : " + productItemInfo.getEndDate().substring(4, 6) + "\n" + "day : " + productItemInfo.getEndDate().substring(6, 8);
            Toast.makeText((Context)LGinApp.this, (CharSequence)string2, (int)0).show();
        }

        public void lguIABonItemPurchaseComplete() {
            LGinApp.DEBUG_LOG("lguIABonItemPurchaseComplete()");
            Native.itemChargeComplete();
        }

        public boolean lguIABonItemQueryComplete() {
            LGinApp.DEBUG_LOG("lguIABonItemQueryComplete()");
            return true;
        }

        public void lguIABonItemUseQuery(UseItemInfo useItemInfo) {
            LGinApp.this.finish();
            LGinApp.DEBUG_LOG("lguIABonItemUseQuery()");
            String string2 = String.valueOf((Object)"value : ") + "PID :" + useItemInfo.getPid() + "\n" + "NAME: " + useItemInfo.getProductName() + "\n" + "COUNT: " + useItemInfo.getCnt();
            Toast.makeText((Context)LGinApp.this, (CharSequence)string2, (int)0).show();
        }

        public void lguIABonWholeQuery(List<ProductItemInfo> list) {
            LGinApp.this.finish();
            LGinApp.DEBUG_LOG("lguIABonWholeQuery()");
            Logger.i((String)"onWholeQuery!!");
            String string2 = "value : ";
            Iterator iterator = list.iterator();
            do {
                if (!iterator.hasNext()) {
                    Toast.makeText((Context)LGinApp.this, (CharSequence)string2, (int)0).show();
                    return;
                }
                ProductItemInfo productItemInfo = (ProductItemInfo)iterator.next();
                string2 = String.valueOf((Object)string2) + productItemInfo.getPid() + " : " + productItemInfo.getProductName() + "\n";
            } while (true);
        }
    };
    private String mTestBpData = null;
    private String mTestBpUri = null;
    private IAPLibSetting setting = null;

    static {
        m_AppID = "Q01010251864";
        m_PurchaseID = "402007607472";
        m_GameID = null;
        m_IP = "211.43.220.197";
        m_PORT = String.valueOf((int)30607);
        serverType = true;
    }

    private static void DEBUG_LOG(String string2) {
        Cocos2dxActivity.DEBUG_LOG(TAG, string2);
    }

    public static void LG_Purchase(String string2, String string3, String string4) {
        m_AppID = string2;
        m_GameID = string4;
        m_PurchaseID = string3;
        m_Activity = Cocos2dxActivity.m_Activity;
        m_Activity.startActivity(new Intent((Context)m_Activity, LGinApp.class));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView((View)new LinearLayout((Context)this));
        this.setting = new IAPLibSetting((Context)this, m_AppID, null, "0", serverType, this.mClientListener);
        this.LguIAPLibInit(this.setting);
        this.lguIABpopPurchaseDlg((Context)this, m_AppID, m_PurchaseID, this.mTestBpUri, this.mTestBpData, this.setting);
    }

}

