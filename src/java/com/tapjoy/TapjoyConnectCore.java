/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageManager
 *  android.net.Uri
 *  java.lang.Exception
 *  java.lang.Float
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.util.List
 *  java.util.Timer
 *  java.util.TimerTask
 *  java.util.Vector
 *  org.w3c.dom.Document
 *  org.w3c.dom.NodeList
 */
package com.tapjoy;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.tapjoy.TapjoyHttpURLResponse;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyURLConnection;
import com.tapjoy.TapjoyUtil;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class TapjoyConnectCore {
    public static final String TAPJOY_CONNECT = "TapjoyConnect";
    private static String androidID;
    private static String appID;
    private static String appVersion;
    private static String carrierCountryCode;
    private static String carrierName;
    private static String clientPackage;
    private static String connectionType;
    private static Context context;
    private static float currencyMultiplier;
    private static String deviceCountryCode;
    private static String deviceID;
    private static String deviceLanguage;
    private static String deviceManufacturer;
    private static String deviceModel;
    private static String deviceOSVersion;
    private static String deviceScreenDensity;
    private static String deviceScreenLayoutSize;
    private static String deviceType;
    private static boolean enableVideoCache;
    private static String libraryVersion;
    private static String matchingPackageNames;
    private static String mobileCountryCode;
    private static String mobileNetworkCode;
    private static String paidAppActionID;
    private static String platformName;
    private static String plugin;
    private static String referralURL;
    private static String sdkType;
    private static String secretKey;
    private static TapjoyConnectCore tapjoyConnectCore;
    private static TapjoyURLConnection tapjoyURLConnection;
    private static String userID;
    private static boolean videoEnabled;
    private static String videoIDs;
    private long elapsed_time = 0L;
    private Timer timer = null;

    static {
        context = null;
        tapjoyConnectCore = null;
        tapjoyURLConnection = null;
        androidID = "";
        deviceID = "";
        deviceModel = "";
        deviceManufacturer = "";
        deviceType = "";
        deviceOSVersion = "";
        deviceCountryCode = "";
        deviceLanguage = "";
        appID = "";
        appVersion = "";
        libraryVersion = "";
        deviceScreenDensity = "";
        deviceScreenLayoutSize = "";
        userID = "";
        platformName = "";
        carrierName = "";
        carrierCountryCode = "";
        mobileCountryCode = "";
        mobileNetworkCode = "";
        connectionType = "";
        secretKey = "";
        clientPackage = "";
        referralURL = "";
        plugin = "native";
        sdkType = "";
        videoEnabled = false;
        enableVideoCache = true;
        videoIDs = "";
        currencyMultiplier = 1.0f;
        paidAppActionID = null;
        matchingPackageNames = "";
    }

    public TapjoyConnectCore(Context context) {
        TapjoyConnectCore.context = context;
        tapjoyURLConnection = new TapjoyURLConnection();
        this.init();
        TapjoyLog.i(TAPJOY_CONNECT, "URL parameters: " + TapjoyConnectCore.getURLParams());
        new Thread((Runnable)new ConnectThread()).start();
    }

    static /* synthetic */ long access$014(TapjoyConnectCore tapjoyConnectCore, long l) {
        long l2;
        tapjoyConnectCore.elapsed_time = l2 = l + tapjoyConnectCore.elapsed_time;
        return l2;
    }

    public static void enableVideoCache(boolean bl) {
        enableVideoCache = bl;
    }

    public static String getAppID() {
        return appID;
    }

    public static String getAwardPointsVerifier(long l, int n, String string2) {
        try {
            String string3 = TapjoyUtil.SHA256(appID + ":" + deviceID + ":" + l + ":" + secretKey + ":" + n + ":" + string2);
            return string3;
        }
        catch (Exception exception) {
            TapjoyLog.e(TAPJOY_CONNECT, "getAwardPointsVerifier ERROR: " + exception.toString());
            return "";
        }
    }

    public static String getCarrierName() {
        return carrierName;
    }

    public static String getClientPackage() {
        return clientPackage;
    }

    /*
     * Exception decompiling
     */
    public static String getConnectionType() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: First case is not immediately after switch.
        // org.benf.cfr.reader.b.a.a.b.as.a(SwitchReplacer.java:358)
        // org.benf.cfr.reader.b.a.a.b.as.a(SwitchReplacer.java:61)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:372)
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

    public static Context getContext() {
        return context;
    }

    public static String getDeviceID() {
        return deviceID;
    }

    public static String getGenericURLParams() {
        String string2 = "" + "app_id=" + Uri.encode((String)appID) + "&";
        return string2 + TapjoyConnectCore.getParamsWithoutAppID();
    }

    public static TapjoyConnectCore getInstance() {
        return tapjoyConnectCore;
    }

    public static int getLocalTapPointsTotal() {
        return context.getSharedPreferences("tjcPrefrences", 0).getInt("last_tap_points", -9999);
    }

    public static String getPackageNamesVerifier(long l, String string2) {
        try {
            String string3 = TapjoyUtil.SHA256(appID + ":" + deviceID + ":" + l + ":" + secretKey + ":" + string2);
            return string3;
        }
        catch (Exception exception) {
            TapjoyLog.e(TAPJOY_CONNECT, "getVerifier ERROR: " + exception.toString());
            return "";
        }
    }

    private static String getParamsWithoutAppID() {
        String string2 = "" + "android_id=" + androidID + "&";
        String string3 = string2 + "udid=" + Uri.encode((String)deviceID) + "&";
        String string4 = string3 + "device_name=" + Uri.encode((String)deviceModel) + "&";
        String string5 = string4 + "device_manufacturer=" + Uri.encode((String)deviceManufacturer) + "&";
        String string6 = string5 + "device_type=" + Uri.encode((String)deviceType) + "&";
        String string7 = string6 + "os_version=" + Uri.encode((String)deviceOSVersion) + "&";
        String string8 = string7 + "country_code=" + Uri.encode((String)deviceCountryCode) + "&";
        String string9 = string8 + "language_code=" + Uri.encode((String)deviceLanguage) + "&";
        String string10 = string9 + "app_version=" + Uri.encode((String)appVersion) + "&";
        String string11 = string10 + "library_version=" + Uri.encode((String)libraryVersion) + "&";
        String string12 = string11 + "platform=" + Uri.encode((String)platformName) + "&";
        String string13 = string12 + "display_multiplier=" + Uri.encode((String)Float.toString((float)currencyMultiplier));
        if (carrierName.length() > 0) {
            String string14 = string13 + "&";
            string13 = string14 + "carrier_name=" + Uri.encode((String)carrierName);
        }
        if (carrierCountryCode.length() > 0) {
            String string15 = string13 + "&";
            string13 = string15 + "carrier_country_code=" + Uri.encode((String)carrierCountryCode);
        }
        if (mobileCountryCode.length() > 0) {
            String string16 = string13 + "&";
            string13 = string16 + "mobile_country_code=" + Uri.encode((String)mobileCountryCode);
        }
        if (mobileNetworkCode.length() > 0) {
            String string17 = string13 + "&";
            string13 = string17 + "mobile_network_code=" + Uri.encode((String)mobileNetworkCode);
        }
        if (deviceScreenDensity.length() > 0 && deviceScreenLayoutSize.length() > 0) {
            String string18 = string13 + "&";
            String string19 = string18 + "screen_density=" + Uri.encode((String)deviceScreenDensity) + "&";
            string13 = string19 + "screen_layout_size=" + Uri.encode((String)deviceScreenLayoutSize);
        }
        if ((connectionType = TapjoyConnectCore.getConnectionType()).length() > 0) {
            String string20 = string13 + "&";
            string13 = string20 + "connection_type=" + Uri.encode((String)connectionType);
        }
        if (plugin.length() > 0) {
            String string21 = string13 + "&";
            string13 = string21 + "plugin=" + Uri.encode((String)plugin);
        }
        if (sdkType.length() > 0) {
            String string22 = string13 + "&";
            string13 = string22 + "sdk_type=" + Uri.encode((String)sdkType);
        }
        return string13;
    }

    public static String getURLParams() {
        String string2 = TapjoyConnectCore.getGenericURLParams() + "&";
        long l = System.currentTimeMillis() / 1000L;
        String string3 = TapjoyConnectCore.getVerifier(l);
        String string4 = string2 + "timestamp=" + l + "&";
        return string4 + "verifier=" + string3;
    }

    public static String getUserID() {
        return userID;
    }

    public static String getVerifier(long l) {
        try {
            String string2 = TapjoyUtil.SHA256(appID + ":" + deviceID + ":" + l + ":" + secretKey);
            return string2;
        }
        catch (Exception exception) {
            TapjoyLog.e(TAPJOY_CONNECT, "getVerifier ERROR: " + exception.toString());
            return "";
        }
    }

    public static String getVideoIDs() {
        return videoIDs;
    }

    private static boolean handleConnectResponse(String string2) {
        Document document = TapjoyUtil.buildDocument(string2);
        if (document != null) {
            String string3;
            String string4 = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("PackageNames"));
            if (string4 != null && string4.length() > 0) {
                Vector vector = new Vector();
                int n = 0;
                do {
                    int n2;
                    if ((n2 = string4.indexOf(44, n)) == -1) {
                        TapjoyLog.i(TAPJOY_CONNECT, "parse: " + string4.substring(n).trim());
                        vector.add((Object)string4.substring(n).trim());
                        matchingPackageNames = "";
                        for (ApplicationInfo applicationInfo : context.getPackageManager().getInstalledApplications(0)) {
                            if ((1 & applicationInfo.flags) == 1 || !vector.contains((Object)applicationInfo.packageName)) continue;
                            TapjoyLog.i(TAPJOY_CONNECT, "MATCH: installed packageName: " + applicationInfo.packageName);
                            if (matchingPackageNames.length() > 0) {
                                matchingPackageNames = matchingPackageNames + ",";
                            }
                            matchingPackageNames = matchingPackageNames + applicationInfo.packageName;
                        }
                        break;
                    }
                    TapjoyLog.i(TAPJOY_CONNECT, "parse: " + string4.substring(n, n2).trim());
                    vector.add((Object)string4.substring(n, n2).trim());
                    n = n2 + 1;
                } while (true);
            }
            if ((string3 = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("Success"))) == null || string3.equals((Object)"true")) {
                // empty if block
            }
        }
        return true;
    }

    private boolean handlePayPerActionResponse(String string2) {
        Document document = TapjoyUtil.buildDocument(string2);
        if (document != null) {
            String string3 = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("Success"));
            if (string3 != null && string3.equals((Object)"true")) {
                TapjoyLog.i(TAPJOY_CONNECT, "Successfully sent completed Pay-Per-Action to Tapjoy server.");
                return true;
            }
            TapjoyLog.e(TAPJOY_CONNECT, "Completed Pay-Per-Action call failed.");
        }
        return false;
    }

    /*
     * Exception decompiling
     */
    private void init() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
        // org.benf.cfr.reader.b.a.a.j.b(Op04StructuredStatement.java:409)
        // org.benf.cfr.reader.b.a.a.j.d(Op04StructuredStatement.java:487)
        // org.benf.cfr.reader.b.a.a.i.a(Op03SimpleStatement.java:607)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:692)
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

    public static boolean isVideoCacheEnabled() {
        return enableVideoCache;
    }

    public static boolean isVideoEnabled() {
        return videoEnabled;
    }

    public static void requestTapjoyConnect(Context context, String string2, String string3) {
        appID = string2;
        secretKey = string3;
        tapjoyConnectCore = new TapjoyConnectCore(context);
    }

    public static void saveTapPointsTotal(int n) {
        SharedPreferences.Editor editor = context.getSharedPreferences("tjcPrefrences", 0).edit();
        editor.putInt("last_tap_points", n);
        editor.commit();
    }

    public static void setDebugDeviceID(String string2) {
        deviceID = string2;
        SharedPreferences.Editor editor = context.getSharedPreferences("tjcPrefrences", 0).edit();
        editor.putString("emulatorDeviceId", deviceID);
        editor.commit();
    }

    public static void setPlugin(String string2) {
        plugin = string2;
    }

    public static void setSDKType(String string2) {
        sdkType = string2;
    }

    public static void setUserID(String string2) {
        userID = string2;
        TapjoyLog.i(TAPJOY_CONNECT, "URL parameters: " + TapjoyConnectCore.getURLParams());
        new Thread(new Runnable(){

            public void run() {
                String string2;
                TapjoyLog.i(TapjoyConnectCore.TAPJOY_CONNECT, "setUserID...");
                String string3 = TapjoyConnectCore.getURLParams();
                String string4 = string3 + "&publisher_user_id=" + TapjoyConnectCore.getUserID();
                if (!referralURL.equals((Object)"")) {
                    string4 = string4 + "&" + referralURL;
                }
                if ((string2 = tapjoyURLConnection.connectToURL("https://ws.tapjoyads.com/set_publisher_user_id?", string4)) != null) {
                    if (TapjoyConnectCore.handleConnectResponse(string2)) {
                        // empty if block
                    }
                    TapjoyLog.i(TapjoyConnectCore.TAPJOY_CONNECT, "setUserID successful...");
                }
            }
        }).start();
    }

    public static void setVideoEnabled(boolean bl) {
        videoEnabled = bl;
    }

    public static void setVideoIDs(String string2) {
        videoIDs = string2;
    }

    public void actionComplete(String string2) {
        TapjoyLog.i(TAPJOY_CONNECT, "actionComplete: " + string2);
        String string3 = "app_id=" + string2 + "&";
        String string4 = string3 + TapjoyConnectCore.getParamsWithoutAppID();
        String string5 = string4 + "&publisher_user_id=" + TapjoyConnectCore.getUserID();
        String string6 = string5 + "&";
        long l = System.currentTimeMillis() / 1000L;
        String string7 = string6 + "timestamp=" + l + "&";
        String string8 = string7 + "verifier=" + TapjoyConnectCore.getVerifier(l);
        TapjoyLog.i(TAPJOY_CONNECT, "PPA URL parameters: " + string8);
        new Thread((Runnable)new PPAThread(string8)).start();
    }

    public void callConnect() {
        new Thread((Runnable)new ConnectThread()).start();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void enablePaidAppWithActionID(String string2) {
        TapjoyLog.i(TAPJOY_CONNECT, "enablePaidAppWithActionID: " + string2);
        paidAppActionID = string2;
        this.elapsed_time = context.getSharedPreferences("tjcPrefrences", 0).getLong("tapjoy_elapsed_time", 0L);
        TapjoyLog.i(TAPJOY_CONNECT, "paidApp elapsed: " + this.elapsed_time);
        if (this.elapsed_time >= 900000L) {
            if (paidAppActionID == null || paidAppActionID.length() <= 0) return;
            {
                TapjoyLog.i(TAPJOY_CONNECT, "Calling PPA actionComplete...");
                this.actionComplete(paidAppActionID);
                return;
            }
        } else {
            if (this.timer != null) return;
            {
                this.timer = new Timer();
                this.timer.schedule((TimerTask)new PaidAppTimerTask(), 10000L, 10000L);
                return;
            }
        }
    }

    public float getCurrencyMultiplier() {
        return currencyMultiplier;
    }

    public void release() {
        tapjoyConnectCore = null;
        tapjoyURLConnection = null;
        TapjoyLog.i(TAPJOY_CONNECT, "Releasing core static instance.");
    }

    public void setCurrencyMultiplier(float f) {
        TapjoyLog.i(TAPJOY_CONNECT, "setVirtualCurrencyMultiplier: " + f);
        currencyMultiplier = f;
    }

    public class ConnectThread
    implements Runnable {
        public void run() {
            TapjoyLog.i(TapjoyConnectCore.TAPJOY_CONNECT, "starting connect call...");
            String string2 = TapjoyConnectCore.getURLParams();
            TapjoyHttpURLResponse tapjoyHttpURLResponse = tapjoyURLConnection.getResponseFromURL("https://ws.tapjoyads.com/connect?", string2);
            if (tapjoyHttpURLResponse != null && tapjoyHttpURLResponse.statusCode == 200) {
                if (TapjoyConnectCore.handleConnectResponse(tapjoyHttpURLResponse.response)) {
                    TapjoyLog.i(TapjoyConnectCore.TAPJOY_CONNECT, "Successfully connected to tapjoy site.");
                }
                if (matchingPackageNames.length() > 0) {
                    String string3 = TapjoyConnectCore.getGenericURLParams() + "&" + "package_names" + "=" + matchingPackageNames + "&";
                    long l = System.currentTimeMillis() / 1000L;
                    String string4 = TapjoyConnectCore.getPackageNamesVerifier(l, matchingPackageNames);
                    String string5 = string3 + "timestamp=" + l + "&";
                    String string6 = string5 + "verifier=" + string4;
                    TapjoyHttpURLResponse tapjoyHttpURLResponse2 = tapjoyURLConnection.getResponseFromURL("https://ws.tapjoyads.com/apps_installed?", string6);
                    if (tapjoyHttpURLResponse2 != null && tapjoyHttpURLResponse2.statusCode == 200) {
                        TapjoyLog.i(TapjoyConnectCore.TAPJOY_CONNECT, "Successfully pinged sdkless api.");
                    }
                }
            }
        }
    }

    public class PPAThread
    implements Runnable {
        private String params;

        public PPAThread(String string2) {
            this.params = string2;
        }

        public void run() {
            String string2 = tapjoyURLConnection.connectToURL("https://ws.tapjoyads.com/connect?", this.params);
            if (string2 != null) {
                TapjoyConnectCore.this.handlePayPerActionResponse(string2);
            }
        }
    }

    private class PaidAppTimerTask
    extends TimerTask {
        private PaidAppTimerTask() {
        }

        public void run() {
            TapjoyConnectCore.access$014(TapjoyConnectCore.this, 10000L);
            TapjoyLog.i(TapjoyConnectCore.TAPJOY_CONNECT, "elapsed_time: " + TapjoyConnectCore.this.elapsed_time + " (" + TapjoyConnectCore.this.elapsed_time / 1000L / 60L + "m " + TapjoyConnectCore.this.elapsed_time / 1000L % 60L + "s)");
            SharedPreferences.Editor editor = context.getSharedPreferences("tjcPrefrences", 0).edit();
            editor.putLong("tapjoy_elapsed_time", TapjoyConnectCore.this.elapsed_time);
            editor.commit();
            if (TapjoyConnectCore.this.elapsed_time >= 900000L) {
                TapjoyLog.i(TapjoyConnectCore.TAPJOY_CONNECT, "timer done...");
                if (paidAppActionID != null && paidAppActionID.length() > 0) {
                    TapjoyLog.i(TapjoyConnectCore.TAPJOY_CONNECT, "Calling PPA actionComplete...");
                    TapjoyConnectCore.this.actionComplete(paidAppActionID);
                }
                this.cancel();
            }
        }
    }

}

