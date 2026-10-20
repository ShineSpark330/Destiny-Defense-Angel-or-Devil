/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.AssetManager
 *  android.os.Environment
 *  android.util.Log
 *  android.widget.Toast
 *  java.io.File
 *  java.io.FileOutputStream
 *  java.io.InputStream
 *  java.lang.CharSequence
 *  java.lang.Character
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.util.zip.ZipEntry
 *  java.util.zip.ZipInputStream
 */
package com.savegame;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class SavesRestoring
extends Activity {
    private static int PdsjdolaSd = 0;
    private static int daDakdsIID = 0;

    public static void DoSmth(Context context) {
        try {
            SavesRestoring.SmartDataRestoreForYou(context, context.getAssets(), context.getPackageName());
            return;
        }
        catch (Exception exception) {
            StringBuilder stringBuilder = new StringBuilder();
            String string2 = stringBuilder.append(context.getPackageName()).append(":savemessages").toString();
            StringBuilder stringBuilder2 = new StringBuilder();
            Log.e((String)string2, (String)stringBuilder2.append("Message: ").append(exception.getMessage()).toString());
            exception.printStackTrace();
            return;
        }
    }

    public static boolean ExistsInArray(String[] arrstring, String string2) {
        for (int i = 0; i < arrstring.length; ++i) {
            if (!arrstring[i].contains((CharSequence)string2)) continue;
            return true;
        }
        return false;
    }

    private static String IcvaeLIJAFL() {
        daDakdsIID = 1 + daDakdsIID;
        return Character.toString((char)'b');
    }

    private static String JaEBhFLsj() {
        daDakdsIID = 1 + daDakdsIID;
        return Character.toString((char)'s');
    }

    private static String MNTRGWO() {
        daDakdsIID = 1 + daDakdsIID;
        return Character.toString((char)'o');
    }

    private static String SWoFpOGUOR() {
        daDakdsIID = 1 + daDakdsIID;
        return Character.toString((char)'m');
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void SmartDataRestoreForYou(Context context, AssetManager assetManager, String string2) throws Exception {
        if (context.getSharedPreferences("savegame", 0).getBoolean("notfirst", false)) {
            return;
        }
        context.getSharedPreferences("savegame", 0).edit().putBoolean("notfirst", true).commit();
        StringBuilder stringBuilder = new StringBuilder();
        String string3 = stringBuilder.append(string2).append(":savemessages").toString();
        Log.i((String)string3, (String)"SmDR: Starting...");
        context.getSharedPreferences("savegame", 0).edit().putBoolean("notfirst", true).commit();
        String[] arrstring = assetManager.list("");
        for (int i = 0; i < arrstring.length; ++i) {
            StringBuilder stringBuilder2 = new StringBuilder();
            Log.i((String)string3, (String)stringBuilder2.append("ListFiles[").append(i).append("] = ").append(arrstring[i]).toString());
        }
        if (SavesRestoring.ExistsInArray(arrstring, "data.save")) {
            Toast.makeText((Context)context, (CharSequence)"Restoring save...", (int)0);
            try {
                Log.i((String)string3, (String)"data.save : Restoring...");
                InputStream inputStream = assetManager.open("data.save");
                StringBuilder stringBuilder3 = new StringBuilder();
                SavesRestoring.unZipIt(inputStream, stringBuilder3.append("/data/data/").append(context.getPackageName()).toString());
                Log.i((String)string3, (String)"data.save: Successfully restored");
            }
            catch (Exception exception) {
                StringBuilder stringBuilder4 = new StringBuilder();
                Log.e((String)string3, (String)stringBuilder4.append("data.save: Message: ").append(exception.getMessage()).toString());
                Toast.makeText((Context)context, (CharSequence)"Can't restore save", (int)1);
            }
        }
        if (SavesRestoring.ExistsInArray(arrstring, "extobb.save")) {
            Toast.makeText((Context)context, (CharSequence)"Restoring cache...", (int)0);
            try {
                Log.i((String)string3, (String)"extobb.save: Restoring...");
                StringBuilder stringBuilder5 = new StringBuilder();
                String string4 = stringBuilder5.append(context.getObbDir().getAbsolutePath()).append("/").toString();
                SavesRestoring.unZipIt(assetManager.open("extobb.save"), string4);
                Log.i((String)string3, (String)"extobb.save: Successfully restored");
            }
            catch (Exception exception) {
                StringBuilder stringBuilder6 = new StringBuilder();
                Log.e((String)string3, (String)stringBuilder6.append("extobb.save: Message: ").append(exception.getMessage()).toString());
                Toast.makeText((Context)context, (CharSequence)"Can't restore external cache", (int)1);
            }
        }
        if (SavesRestoring.ExistsInArray(arrstring, "extdata.save")) {
            Toast.makeText((Context)context, (CharSequence)"Restoring external data...", (int)0);
            try {
                Log.i((String)string3, (String)"extdata.save: Restoring...");
                StringBuilder stringBuilder7 = new StringBuilder();
                String string5 = stringBuilder7.append((Object)Environment.getExternalStorageDirectory()).append("/Android/data/").append(context.getPackageName()).append("/").toString();
                File file = new File(string5);
                file.mkdirs();
                SavesRestoring.unZipIt(assetManager.open("extdata.save"), string5);
                Log.i((String)string3, (String)"extdata.save: Successfully restored");
            }
            catch (Exception exception) {
                StringBuilder stringBuilder8 = new StringBuilder();
                Log.e((String)string3, (String)stringBuilder8.append("extdata.save: Message: ").append(exception.getMessage()).toString());
                Toast.makeText((Context)context, (CharSequence)"Can't restore external data", (int)1);
            }
        }
        Log.i((String)string3, (String)"Restoring completed");
        Toast.makeText((Context)context, (CharSequence)"Restoring completed", (int)1);
    }

    private static String VNoYQilb() {
        daDakdsIID = 1 + daDakdsIID;
        return Character.toString((char)' ');
    }

    private static String cTDeCJR() {
        daDakdsIID = 1 + daDakdsIID;
        return Character.toString((char)'s');
    }

    private static String gwlVJD() {
        daDakdsIID = 1 + daDakdsIID;
        return Character.toString((char)'y');
    }

    private static String iiAqKeUvKgqUd() {
        daDakdsIID = 1 + daDakdsIID;
        return Character.toString((char)'M');
    }

    private static String ldpSDcTKVdWbP() {
        daDakdsIID = 1 + daDakdsIID;
        return Character.toString((char)'o');
    }

    private static String plKpPgCYGa() {
        daDakdsIID = 1 + daDakdsIID;
        return Character.toString((char)' ');
    }

    private static String tIdtgslWSDS() {
        daDakdsIID = 1 + daDakdsIID;
        return Character.toString((char)'d');
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static void unZipIt(InputStream var0, String var1_1) throws Exception {
        var2_2 = new ZipInputStream(var0);
        if (SavesRestoring.daDakdsIID != SavesRestoring.PdsjdolaSd) {
            var3_3 = new Exception("System error...");
            throw var3_3;
        }
        var4_4 = new byte[1024];
        var5_5 = new File(var1_1);
        var5_5.mkdirs();
        var7_6 = var2_2.getNextEntry();
        if (SavesRestoring.daDakdsIID != SavesRestoring.PdsjdolaSd) {
            var8_7 = new Exception("System error! please don't cheat...");
            throw var8_7;
        }
        do {
            block8 : {
                if (var7_6 == null) break block8;
                if (var7_6.isDirectory()) {
                    var7_6 = var2_2.getNextEntry();
                    continue;
                }
                var10_8 = var7_6.getName().lastIndexOf(47);
                if (var10_8 < 0) {
                    var10_8 = 0;
                }
                var11_9 = new StringBuilder();
                var12_10 = new File(var11_9.append(var1_1).append("/").append(var7_6.getName().substring(0, var10_8)).toString());
                var12_10.mkdirs();
                var14_11 = new StringBuilder();
                var15_12 = new File(var14_11.append(var1_1).append("/").append(var7_6.getName()).toString());
                var16_13 = new FileOutputStream(var15_12, false);
                if (SavesRestoring.daDakdsIID != SavesRestoring.PdsjdolaSd) {
                    var16_13.close();
                    var18_15 = new Exception("You are clever...");
                    throw var18_15;
                }
                ** GOTO lbl39
            }
            if (SavesRestoring.daDakdsIID != SavesRestoring.PdsjdolaSd) {
                var9_16 = new Exception("And again...");
                throw var9_16;
            }
            var2_2.closeEntry();
            var2_2.close();
            return;
lbl-1000: // 1 sources:
            {
                var16_13.write(var4_4, 0, var17_14);
lbl39: // 2 sources:
                ** while ((var17_14 = var2_2.read((byte[])var4_4)) > 0)
            }
lbl40: // 1 sources:
            var16_13.close();
            var7_6 = var2_2.getNextEntry();
        } while (true);
    }

    private static String wrEBJneYLx() {
        daDakdsIID = 1 + daDakdsIID;
        return Character.toString((char)'y');
    }
}

