/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  com.lgt.arm.LGUArmListener
 *  com.lgt.arm.LGUArmManager
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 */
package com.morisoft.NLib.LG;

import android.content.Context;
import com.lgt.arm.LGUArmListener;
import com.lgt.arm.LGUArmManager;
import com.morisoft.NLib.NativePopUp;
import org.cocos2dx.lib.Cocos2dxActivity;

public class ArmService
implements LGUArmListener {
    private static String TAG = "LG ArmService";
    private static String m_AID;
    private static LGUArmManager m_arm;
    private static ArmService m_service;

    static {
        m_service = null;
        m_arm = null;
        m_AID = null;
    }

    private static void DEBUG_LOG(String string2) {
        Cocos2dxActivity.DEBUG_LOG(TAG, string2);
    }

    public static void runArmService(String string2) {
        ArmService.DEBUG_LOG("runArmService: " + string2);
        try {
            m_AID = string2;
            m_service = new ArmService();
            m_arm = new LGUArmManager((Context)Cocos2dxActivity.m_Activity);
            m_arm.setArmListener((LGUArmListener)m_service);
            m_arm.ARM_Plugin_ExecuteARM(m_AID);
            return;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
    }

    public void onArmResult() {
        ArmService.DEBUG_LOG("SERVICE_CONNECT: 1");
        ArmService.DEBUG_LOG("SERVICE_FAIL: 2");
        ArmService.DEBUG_LOG("SERVICE_NOT_EXIST: 3");
        ArmService.DEBUG_LOG("onArmResult: " + ArmService.m_arm.netState);
        switch (ArmService.m_arm.netState) {
            default: {
                return;
            }
            case 1: {
                if (ArmService.m_arm.resCode != 1) {
                    NativePopUp.showArmDialog(TAG, ArmService.m_arm.resMsg);
                    return;
                }
                m_service = null;
                return;
            }
            case 2: 
            case 3: 
        }
        NativePopUp.showArmDialog(TAG, ArmService.m_arm.resMsg);
    }
}

