/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.os.Vibrator
 *  android.util.Log
 *  java.io.UnsupportedEncodingException
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 */
package com.morisoft.NLib;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;
import com.morisoft.NLib.INIPay.Purchase;
import com.morisoft.NLib.KT.ChargeActivity;
import com.morisoft.NLib.LG.ArmService;
import com.morisoft.NLib.Native;
import com.morisoft.NLib.Streaming.StreamingDownload;
import com.morisoft.NLib.Streaming.StreamingMediaPlayer;
import com.morisoft.NLib.WebPopUpDialog;
import java.io.UnsupportedEncodingException;
import org.cocos2dx.lib.Cocos2dxActivity;

public class NativeCall {
    public static final long NANOSECONDSPERMINISECOND = 1000000L;
    public static final long NANOSECONDSPERSECOND = 1000000000L;
    static final String TAG = "NativeCall";
    public static long animationInterval;
    private static Cocos2dxActivity m_Activity;
    public static boolean s_bGC;
    public static byte[] sendPacketData;
    private static StreamingDownload streamingDownload;
    private static StreamingMediaPlayer streamingPlayer;
    private static Vibrator vibrator;

    static {
        s_bGC = true;
        animationInterval = 16666666L;
        vibrator = null;
        streamingDownload = null;
        streamingPlayer = null;
        sendPacketData = null;
    }

    public static void AD_Purchase(byte[] arrby, byte[] arrby2) {
        String string2 = NativeCall.toUnicode(arrby2);
        String string3 = NativeCall.toUnicode(arrby);
        Log.d((String)TAG, (String)("PurchaseID: " + string3));
        com.morisoft.NLib.Android.Purchase.runPurchaseService(string2, "\uc774\uc0c1\ud488\uc744 \uad6c\uc785\ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?", "\ud655\uc778", "\ucde8\uc18c", string3);
    }

    static void DEBUG_LOG(String string2) {
        Cocos2dxActivity.DEBUG_LOG(TAG, string2);
    }

    public static void INICIS_Purchase(boolean bl, String string2, String string3, int n) {
        Intent intent = new Intent((Context)m_Activity, Purchase.class);
        intent.putExtra("Test", bl);
        intent.putExtra("GAME_ID", string2);
        intent.putExtra("P_GOODS", string3);
        intent.putExtra("P_AMT", "" + n);
        m_Activity.startActivity(intent);
    }

    public static void KStoUnicode(byte[] arrby) {
        String string2 = NativeCall.toUnicode(arrby);
        if (string2 == null) {
            Native.KStoUnicode(null);
        }
        try {
            Native.KStoUnicode(string2.getBytes("UTF-16BE"));
            return;
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {
            Log.e((String)"KStoUnicode", (String)"UnsupportedEncodingException2", (Throwable)unsupportedEncodingException);
            unsupportedEncodingException.printStackTrace();
            Native.KStoUnicode(null);
            return;
        }
    }

    public static void KT_KAFinitialize(boolean bl) {
        com.morisoft.NLib.KT.ArmService.runArmService();
    }

    public static void KT_Link(String string2, String string3, String string4, boolean bl) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setType("vnd.kt.olleh.storefront/detail.kt.olleh.storefront");
        intent.putExtra("CONTENT_TYPE", string2);
        intent.putExtra("P_TYPE", string3);
        intent.putExtra("P_ID", string4);
        intent.putExtra("N_ID", "");
        if (bl) {
            intent.putExtra("IS_ADULT", "IS_ADULT");
        }
        m_Activity.startActivity(intent);
    }

    public static void KT_Purchase(String string2, String string3, String string4) {
        Intent intent = new Intent((Context)m_Activity, ChargeActivity.class);
        intent.putExtra("AppID", string2);
        intent.putExtra("ChargeID", string3);
        m_Activity.startActivity(intent);
    }

    public static void LG_Purchase(String string2, int n, String string3, String string4, byte[] arrby) {
        sendPacketData = null;
        sendPacketData = new byte[arrby.length];
        System.arraycopy((Object)arrby, (int)0, (Object)sendPacketData, (int)0, (int)arrby.length);
        Intent intent = new Intent((Context)m_Activity, com.morisoft.NLib.LG.ChargeActivity.class);
        intent.putExtra("AppID", string3);
        intent.putExtra("ItemName", string4);
        intent.putExtra("IP", string2);
        intent.putExtra("PORT", n);
        m_Activity.startActivity(intent);
    }

    public static void LG_checkCopyRight(String string2) {
        ArmService.runArmService(string2);
    }

    public static void SK_Link(String string2) {
        Intent intent = new Intent();
        intent.addFlags(536870912);
        intent.setClassName("com.skt.skaf.A000Z00040", "com.skt.skaf.A000Z00040.A000Z00040");
        intent.setAction("COLLAB_ACTION");
        intent.putExtra("com.skt.skaf.COL.URI", ("PRODUCT_VIEW/" + string2 + "/0").getBytes());
        intent.putExtra("com.skt.skaf.COL.REQUESTER", "A00Z00040");
        m_Activity.startActivity(intent);
    }

    public static void SK_Purchase(String string2, String string3, String string4) {
        NativeCall.SK_Purchase(string2, string3, string4, null);
    }

    public static void SK_Purchase(String string2, String string3, String string4, String string5) {
        Intent intent = new Intent((Context)m_Activity, com.morisoft.NLib.SK.ChargeActivity.class);
        intent.putExtra("AppID", string2);
        intent.putExtra("ChargePID", string3);
        intent.putExtra("GameID", string4);
        intent.putExtra("ItemName", string5);
        m_Activity.startActivity(intent);
    }

    public static void SK_checkCopyRight(String string2) {
        com.morisoft.NLib.SK.ArmService.runArmService(string2);
    }

    public static void WebLink(String string2) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse((String)string2));
        m_Activity.startActivity(intent);
    }

    public static void appExit() {
        System.gc();
        s_bGC = false;
        Native.finsh();
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void createDialogWebview(String string2, int n, int n2) {
        String string3 = string2.toLowerCase();
        if (string2.toLowerCase().startsWith("file:///")) {
            string2 = "file:///android_asset/" + string2.substring(8);
        } else if (!string3.startsWith("http://")) {
            string2 = "http://" + string2;
        }
        Intent intent = new Intent((Context)m_Activity, WebPopUpDialog.class);
        intent.putExtra("URL", string2);
        intent.putExtra("WIDTH", n);
        intent.putExtra("HEIGHT", n2);
        m_Activity.startActivity(intent);
    }

    public static void gc() {
        if (s_bGC) {
            System.gc();
        }
        Log.e((String)TAG, (String)"GC");
    }

    public static void initialize(Cocos2dxActivity cocos2dxActivity) {
        s_bGC = true;
        m_Activity = cocos2dxActivity;
        vibrator = (Vibrator)cocos2dxActivity.getSystemService("vibrator");
        System.gc();
    }

    public static void playVibrator(int n) {
        long[] arrl = new long[2];
        arrl[1] = n;
        vibrator.vibrate(arrl, -1);
    }

    public static void setAnimationInterval(double d) {
        animationInterval = (long)(1.0E9 * d);
    }

    public static void stopVibrator() {
        vibrator.cancel();
    }

    public static void streamingDownload(String string2, String string3, String string4, int n) {
        if (streamingDownload == null) {
            streamingDownload = new StreamingDownload();
        }
        streamingDownload.download(string2, string3, string4, n);
    }

    public static boolean streamingIsPlay() {
        if (streamingPlayer == null) {
            streamingPlayer = new StreamingMediaPlayer();
        }
        return streamingPlayer.isPlaying();
    }

    public static void streamingPlay(String string2, String string3, String string4, int n) {
        if (streamingPlayer == null) {
            streamingPlayer = new StreamingMediaPlayer();
        }
        streamingPlayer.start(string2, string3, string4, n);
    }

    public static void streamingStop() {
        if (streamingPlayer == null) {
            streamingPlayer = new StreamingMediaPlayer();
        }
        streamingPlayer.stop();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static byte[] string2string(byte[] arrby, String string2, String string3) {
        String string4;
        if (arrby == null) {
            Log.e((String)TAG, (String)"string2string: text data is null");
            return null;
        }
        try {
            string4 = new String(arrby, string2);
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {}
        try {
            return string4.getBytes(string3);
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {}
        {
            void var4_6;
            Log.e((String)TAG, (String)"UnsupportedEncodingException1", (Throwable)var4_6);
            var4_6.printStackTrace();
            return null;
        }
    }

    public static String toUnicode(byte[] arrby) {
        if (arrby == null) {
            return null;
        }
        try {
            String string2 = new String(arrby, "KSC5601");
            return string2;
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {
            Log.e((String)"toUnicode", (String)"UnsupportedEncodingException1", (Throwable)unsupportedEncodingException);
            unsupportedEncodingException.printStackTrace();
            return null;
        }
    }
}

