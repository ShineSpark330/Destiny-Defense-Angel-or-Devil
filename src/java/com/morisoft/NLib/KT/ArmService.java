/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  com.kt.olleh.protection.ProtectionService
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.morisoft.NLib.KT;

import android.content.Context;
import com.kt.olleh.protection.ProtectionService;
import com.morisoft.NLib.NativePopUp;
import org.cocos2dx.lib.Cocos2dxActivity;

public class ArmService {
    private static String TAG = "KT ArmService";
    private static Cocos2dxActivity m_Activity = null;

    private static void DEBUG_LOG(String string2) {
        Cocos2dxActivity.DEBUG_LOG(TAG, string2);
    }

    private static String getReslutText(int n) {
        switch (n) {
            default: {
                return "";
            }
            case 0: {
                return "\uc815\uc0c1";
            }
            case -1: {
                return "\ubd88\ubc95 \ubcf5\uc81c \uc571";
            }
            case -2: {
                return "\uc798\ubabb\ub41c Context";
            }
            case -3: {
                return "\uc62c\ub808 \ub9c8\ucf13 \uc124\uce58 \uc548\ub428";
            }
            case -4: {
                return "\ubd88\ubc95 \ubcf5\uc81c \uae30\ub2a5 \ucd08\uae30\ud654 \uc911";
            }
            case -5: 
        }
        return "\uc54c \uc218 \uc5c6\ub294 \uc624\ub958";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void runArmService() {
        int n;
        m_Activity = Cocos2dxActivity.m_Activity;
        ArmService.DEBUG_LOG("runArmService");
        ProtectionService protectionService = ProtectionService.getProtection();
        try {
            int n2;
            n = n2 = protectionService.verifyApp((Context)m_Activity);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            n = -5;
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
            n = -5;
        }
        if (n != 0) {
            NativePopUp.showArmDialog(TAG, "\uacb0\uacfc\uac12 : " + n + "\n\uba54\uc2dc\uc9c0 : " + ArmService.getReslutText(n));
        }
    }
}

