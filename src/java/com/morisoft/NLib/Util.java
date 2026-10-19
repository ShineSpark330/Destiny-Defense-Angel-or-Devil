/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Application
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.content.res.AssetManager
 *  java.io.IOException
 *  java.io.InputStream
 *  java.lang.Object
 *  java.lang.String
 */
package com.morisoft.NLib;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;
import org.cocos2dx.lib.Cocos2dxActivity;

public class Util {
    public static int bytesToInt(byte[] arrby, int n) {
        return (255 & arrby[n + 0]) << 24 | (255 & arrby[n + 1]) << 16 | (255 & arrby[n + 2]) << 8 | 255 & arrby[n + 3];
    }

    public static String getApkFilepath() {
        Cocos2dxActivity cocos2dxActivity = Cocos2dxActivity.m_Activity;
        String string2 = cocos2dxActivity.getApplication().getPackageName();
        PackageManager packageManager = cocos2dxActivity.getApplication().getPackageManager();
        try {
            String string3 = packageManager.getApplicationInfo((String)string2, (int)0).sourceDir;
            return string3;
        }
        catch (PackageManager.NameNotFoundException nameNotFoundException) {
            nameNotFoundException.printStackTrace();
            return null;
        }
    }

    public static String getAppRootDirectory() {
        return "/sdcard/Download/GAMESAVES/AOD";
    }

    public static String getAppVersion() {
        Cocos2dxActivity cocos2dxActivity = Cocos2dxActivity.m_Activity;
        String string2 = cocos2dxActivity.getApplication().getPackageName();
        PackageManager packageManager = cocos2dxActivity.getApplication().getPackageManager();
        try {
            String string3 = packageManager.getPackageInfo((String)string2, (int)0).versionName;
            return string3;
        }
        catch (PackageManager.NameNotFoundException nameNotFoundException) {
            nameNotFoundException.printStackTrace();
            return null;
        }
    }

    static byte[] getPackageFileData(String string2) {
        try {
            InputStream inputStream = Cocos2dxActivity.m_Activity.getAssets().open(string2);
            byte[] arrby = new byte[inputStream.available()];
            inputStream.read(arrby);
            return arrby;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return null;
        }
    }

    public static String getPackageName() {
        return Cocos2dxActivity.m_Activity.getApplication().getPackageName();
    }

    public static String getResourcePath(String string2) {
        int n = string2.indexOf("assets/");
        if (n == -1) {
            String string3 = "/" + Util.getPackageName() + "/";
            return string2.substring(string2.indexOf(string3) + string3.length());
        }
        return string2.substring(n + "assets/".length());
    }

    public static byte[] intToBytes(int n, byte[] arrby, int n2) {
        arrby[n2 + 0] = (byte)((-16777216 & n) >> 24);
        arrby[n2 + 1] = (byte)((16711680 & n) >> 16);
        arrby[n2 + 2] = (byte)((65280 & n) >> 8);
        arrby[n2 + 3] = (byte)(n & 255);
        return arrby;
    }
}

