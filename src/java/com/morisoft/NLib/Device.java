/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.net.wifi.WifiInfo
 *  android.net.wifi.WifiManager
 *  android.net.wifi.WifiManager$WifiLock
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.PowerManager
 *  android.os.PowerManager$WakeLock
 *  android.provider.Settings
 *  android.provider.Settings$Secure
 *  android.telephony.TelephonyManager
 *  android.view.Display
 *  android.view.WindowManager
 *  java.io.UnsupportedEncodingException
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.RuntimeException
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.Locale
 *  java.util.UUID
 */
package com.morisoft.NLib;

import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;
import com.morisoft.NLib.ConstInterface;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.UUID;
import org.cocos2dx.lib.Cocos2dxActivity;

public class Device
implements ConstInterface {
    public static final int SCREENMODE_LANDSCAPE = 0;
    public static final int SCREENMODE_PORTRAIT = 1;
    private static String TAG = "Device";
    private static Cocos2dxActivity m_Activity;
    private static PowerManager powerManager;
    private static Locale systemLocale;
    private static TelephonyManager telephony;
    private static PowerManager.WakeLock wakeLock;
    private static WifiManager.WifiLock wifiLock;
    private static WifiManager wifiManager;

    static {
        wifiLock = null;
        wakeLock = null;
        powerManager = null;
        systemLocale = null;
        wifiManager = null;
        telephony = null;
        m_Activity = null;
    }

    private static void DEBUG_LOG(String string2) {
        Cocos2dxActivity.DEBUG_LOG(TAG, string2);
    }

    public static String getCountry() {
        return systemLocale.getCountry().toLowerCase();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static String getID() {
        block7 : {
            uUID = UUID.randomUUID();
            sharedPreferences = Device.m_Activity.getSharedPreferences("device_id", 0);
            string2 = sharedPreferences.getString("device_id", null);
            if (string2 != null) {
                uUID = UUID.fromString((String)string2);
lbl6: // 2 sources:
                do {
                    return uUID.toString();
                    break;
                } while (true);
            }
            string3 = Settings.Secure.getString((ContentResolver)Device.m_Activity.getContentResolver(), (String)"android_id");
            if ("9774d56d682e549c".equals((Object)string3)) break block7;
            uUID = uUID3 = UUID.nameUUIDFromBytes((byte[])string3.getBytes("utf8"));
lbl12: // 4 sources:
            do {
                sharedPreferences.edit().putString("device_id", uUID.toString()).commit();
                ** continue;
                break;
            } while (true);
        }
        if (!false) ** GOTO lbl20
        try {
            uUID = UUID.nameUUIDFromBytes((byte[])null.getBytes("utf8"));
            ** GOTO lbl12
lbl20: // 1 sources:
            if (!Device.wifiManager.isWifiEnabled()) {
                Device.wifiManager.setWifiEnabled(true);
            }
            string4 = Device.wifiManager.getConnectionInfo().getMacAddress();
            Device.wifiManager.setWifiEnabled(false);
            Device.DEBUG_LOG("WiFi Mac Address: " + string4);
            if (string4 == null) ** GOTO lbl12
            uUID = uUID2 = UUID.nameUUIDFromBytes((byte[])string4.getBytes("utf8"));
            ** continue;
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new RuntimeException((Throwable)unsupportedEncodingException);
        }
    }

    public static String getLanguage() {
        return systemLocale.getLanguage().toLowerCase();
    }

    public static int getMobileCountryCode() {
        int n = -1;
        String string2 = telephony.getSimOperator().trim();
        if (string2 == null || string2.length() == 0) {
            string2 = telephony.getNetworkOperator().trim();
        }
        if (string2 != null && string2.length() > 3) {
            n = Integer.parseInt((String)string2.substring(0, 2));
        }
        return n;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int getMobileNetworkCode() {
        int n = -1;
        int n2 = telephony.getSimState();
        if (n2 == 1) return -1;
        if (n2 == 0) {
            return -1;
        }
        String string2 = telephony.getSimOperator().trim();
        if (string2 == null) return n;
        if (string2.length() <= 0) return n;
        return Integer.parseInt((String)string2);
    }

    public static String getModel() {
        return Build.MODEL;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean getNetworkInfo(int n) {
        boolean bl;
        if (n == 0) {
            bl = Device.isMobileConnected();
        } else {
            bl = false;
            if (n == 1) {
                bl = Device.isWifiConnected();
            }
        }
        Device.DEBUG_LOG("getNetworkInfo: " + bl);
        return bl;
    }

    public static String getOSVersion() {
        return "Android " + Build.VERSION.SDK;
    }

    public static int getOrientation() {
        return ((WindowManager)m_Activity.getSystemService("window")).getDefaultDisplay().getOrientation();
    }

    /*
     * Exception decompiling
     */
    public static String getPhoneNumber() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.NullPointerException: Attempt to invoke interface method 'org.benf.cfr.reader.b.a.e.u org.benf.cfr.reader.b.a.e.q.m()' on a null object reference
        // org.benf.cfr.reader.b.a.e.b.b$d.a(InferredJavaType.java:471)
        // org.benf.cfr.reader.b.a.e.b.b.g(InferredJavaType.java:1173)
        // org.benf.cfr.reader.b.a.b.a.t.<init>(CastExpression.java:29)
        // org.benf.cfr.reader.b.a.a.c.c.h.a(NakedNullCaster.java:44)
        // org.benf.cfr.reader.b.a.b.a.e.a(AbstractMemberFunctionInvokation.java:68)
        // org.benf.cfr.reader.b.a.a.c.c.h.a(NakedNullCaster.java:49)
        // org.benf.cfr.reader.b.a.d.b.u.a(StructuredReturn.java:90)
        // org.benf.cfr.reader.b.a.a.c.c.h.a(NakedNullCaster.java:31)
        // org.benf.cfr.reader.b.a.a.j.a(Op04StructuredStatement.java:680)
        // org.benf.cfr.reader.b.a.d.b.e.a(Block.java:407)
        // org.benf.cfr.reader.b.a.a.c.c.h.a(NakedNullCaster.java:30)
        // org.benf.cfr.reader.b.a.a.j.a(Op04StructuredStatement.java:680)
        // org.benf.cfr.reader.b.a.d.b.s.a(StructuredIf.java:85)
        // org.benf.cfr.reader.b.a.a.c.c.h.a(NakedNullCaster.java:30)
        // org.benf.cfr.reader.b.a.a.j.a(Op04StructuredStatement.java:680)
        // org.benf.cfr.reader.b.a.d.b.e.a(Block.java:407)
        // org.benf.cfr.reader.b.a.a.c.c.h.a(NakedNullCaster.java:30)
        // org.benf.cfr.reader.b.a.a.j.a(Op04StructuredStatement.java:680)
        // org.benf.cfr.reader.b.a.a.c.c.h.a(NakedNullCaster.java:25)
        // org.benf.cfr.reader.b.a.a.j.l(Op04StructuredStatement.java:763)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:772)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:182)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:127)
        // org.benf.cfr.reader.entities.attributes.f.c(AttributeCode.java:96)
        // org.benf.cfr.reader.entities.g.p(Method.java:396)
        // org.benf.cfr.reader.entities.d.e(ClassFile.java:890)
        // org.benf.cfr.reader.entities.d.b(ClassFile.java:792)
        // org.benf.cfr.reader.b.a(Driver.java:128)
        // org.benf.cfr.reader.a.a(CfrDriverImpl.java:63)
        // com.njlabs.showjava.decompilers.JavaExtractionWorker.decompileWithCFR(JavaExtractionWorker.kt:61)
        // com.njlabs.showjava.decompilers.JavaExtractionWorker.doWork(JavaExtractionWorker.kt:130)
        // com.njlabs.showjava.decompilers.BaseDecompiler.withAttempt(BaseDecompiler.kt:108)
        // com.njlabs.showjava.workers.DecompilerWorker$b.run(DecompilerWorker.kt:118)
        // java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1100)
        // java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
        // java.lang.Thread.run(Thread.java:1572)
        throw new IllegalStateException("Decompilation failed");
    }

    public static void getSimState() {
    }

    public static void initialize(Cocos2dxActivity cocos2dxActivity) {
        m_Activity = cocos2dxActivity;
        systemLocale = Device.m_Activity.getResources().getConfiguration().locale;
        wifiManager = (WifiManager)m_Activity.getSystemService("wifi");
        powerManager = (PowerManager)m_Activity.getSystemService("power");
        telephony = (TelephonyManager)m_Activity.getSystemService("phone");
        wifiLock = wifiManager.createWifiLock("wifilock");
        wifiLock.setReferenceCounted(true);
        wifiLock.acquire();
        wakeLock = powerManager.newWakeLock(10, "wakelock");
        wakeLock.acquire();
        Device.getSimState();
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean isMobileConnected() {
        NetworkInfo networkInfo = ((ConnectivityManager)m_Activity.getSystemService("connectivity")).getNetworkInfo(0);
        boolean bl = networkInfo.isConnectedOrConnecting() && networkInfo.isAvailable();
        Device.DEBUG_LOG("isMobileConnected: " + bl);
        return bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean isWifiConnected() {
        boolean bl = Device.isWifiOn();
        boolean bl2 = false;
        if (bl) {
            NetworkInfo networkInfo = ((ConnectivityManager)m_Activity.getSystemService("connectivity")).getNetworkInfo(1);
            bl2 = networkInfo.isConnectedOrConnecting() && networkInfo.isAvailable();
        }
        Device.DEBUG_LOG("isWifiConnected: " + bl2);
        return bl2;
    }

    public static boolean isWifiOn() {
        Device.DEBUG_LOG("isWifiOn: " + wifiManager.isWifiEnabled());
        return wifiManager.isWifiEnabled();
    }

    public static void setWifiEnabled(boolean bl) {
        Device.DEBUG_LOG("setWifiEnabled: " + bl);
        wifiManager.setWifiEnabled(bl);
    }
}

