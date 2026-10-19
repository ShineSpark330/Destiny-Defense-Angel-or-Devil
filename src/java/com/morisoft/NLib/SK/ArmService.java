/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.ProgressDialog
 *  android.content.Context
 *  android.os.Handler
 *  android.os.Process
 *  com.skt.arm.ArmListener
 *  com.skt.arm.ArmManager
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 */
package com.morisoft.NLib.SK;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Process;
import com.skt.arm.ArmListener;
import com.skt.arm.ArmManager;
import org.cocos2dx.lib.Cocos2dxActivity;

public class ArmService
implements ArmListener {
    private static String TAG = "TStore ArmService";
    private static String m_AID;
    private static ArmManager m_arm;
    private static ArmService m_service;
    private ProgressDialog pDlg;

    static {
        m_service = null;
        m_arm = null;
        m_AID = null;
    }

    private static void DEBUG_LOG(String string2) {
        Cocos2dxActivity.DEBUG_LOG(TAG, string2);
    }

    static /* synthetic */ void access$2(ArmService armService, ProgressDialog progressDialog) {
        armService.pDlg = progressDialog;
    }

    public static void runArmService(String string2) {
        try {
            m_AID = string2;
            m_service = new ArmService();
            Cocos2dxActivity.m_Activity.m_Handler.post(new Runnable(){

                public void run() {
                    ArmService.access$2(m_service, ProgressDialog.show((Context)Cocos2dxActivity.m_Activity, (CharSequence)TAG, (CharSequence)"Application \uc778\uc99d \uc911 "));
                }
            });
            ArmService.DEBUG_LOG("m_AID: " + m_AID);
            m_arm = new ArmManager((Context)Cocos2dxActivity.m_Activity);
            m_arm.setArmListener((ArmListener)m_service);
            m_arm.ARM_Plugin_ExecuteARM(m_AID);
            return;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
    }

    public void onArmResult() {
        Cocos2dxActivity.m_Activity.m_Handler.post(new Runnable(){

            public void run() {
                ArmService.this.pDlg.dismiss();
            }
        });
        ArmService.DEBUG_LOG("SERVICE_CONNECT: 1");
        ArmService.DEBUG_LOG("SERVICE_FAIL: 20");
        ArmService.DEBUG_LOG("SERVICE_NOT_EXIST: 3");
        ArmService.DEBUG_LOG("onArmResult: " + ArmService.m_arm.nNetState);
        switch (ArmService.m_arm.nNetState) {
            default: {
                return;
            }
            case 3: 
            case 20: 
        }
        Process.killProcess((int)Process.myPid());
    }

}

