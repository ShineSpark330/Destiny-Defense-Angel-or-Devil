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
 *  java.security.Key
 *  java.security.spec.AlgorithmParameterSpec
 *  java.util.zip.ZipEntry
 *  java.util.zip.ZipInputStream
 *  javax.crypto.Cipher
 *  javax.crypto.CipherInputStream
 *  javax.crypto.spec.IvParameterSpec
 *  javax.crypto.spec.SecretKeySpec
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
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class SavesRestoringPortable
extends Activity {
    private static int PdsjdolaSd = 0;
    private static int daDakdsIID = 225;

    private static String BhTnPtxiC() {
        return "l";
    }

    private static String CDvCUdoNsVXpeV() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "\u0414";
    }

    private static String DCTCGoLUbKLCLg() {
        return ".";
    }

    private static String DHQUYjAwcdFkBOc() {
        return "]";
    }

    private static String DWyJciblrDEH() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "\u0422";
    }

    public static void DoSmth(Context context) {
        try {
            SavesRestoringPortable.wPdauIdcaW(context, 3);
            SavesRestoringPortable.SmartDataRestoreForYou(context, context.getAssets(), context.getPackageName());
            return;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
    }

    private static String EcPIODNJBqSF() {
        return "F";
    }

    private static String EwuqLwdyRj() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "\u0420";
    }

    public static boolean FileExists(String[] arrstring, String string2) {
        int n = arrstring.length;
        for (int i = 0; i < n; ++i) {
            if (!new File(arrstring[i]).getName().equals((Object)string2)) continue;
            return true;
        }
        return false;
    }

    private static String FvSPwiAhaX() {
        return "D";
    }

    private static String GQuebRqLqXyEWtC() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "\u0412";
    }

    private static String JRMgciNBWygSg() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "\u041f";
    }

    private static String LfpNQvUxpHGVCy() {
        return "!";
    }

    private static String LlMUscCNv() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "\u041d";
    }

    private static String MeOmchRfXvThjj() {
        return "o";
    }

    private static String MfmTAkWmPnnY() {
        return "Y";
    }

    private static String MkLfUcFH() {
        return ":";
    }

    private static String NApBhpIKOuT() {
        return "p";
    }

    private static String OAmIPFKyo() {
        return "M";
    }

    private static String OEKTKvTtXcBk() {
        return "f";
    }

    private static String PtppwkVvNjhjuWm() {
        return "y";
    }

    private static String PyKHBQHGW() {
        return "n";
    }

    private static String QEdVLMqjeE() {
        return "C";
    }

    private static String RAEKQuhoUDiHTMl() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "\u041a";
    }

    private static String REyCUbsXe() {
        return "P";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void SmartDataRestoreForYou(Context context, AssetManager assetManager, String string2) throws Exception {
        if (context.getSharedPreferences(SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.vMfqQtls() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.WMcxYjngVgdyJB() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.tsgCAawGWlIb() + SavesRestoringPortable.rSjVrKURwEcXO(), 0).getBoolean(SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.OEKTKvTtXcBk() + SavesRestoringPortable.kKOyLMDTYh() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.rYGOJFjtxwvhEVT(), false)) {
            return;
        }
        context.getSharedPreferences(SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.vMfqQtls() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.WMcxYjngVgdyJB() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.tsgCAawGWlIb() + SavesRestoringPortable.rSjVrKURwEcXO(), 0).edit().putBoolean(SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.OEKTKvTtXcBk() + SavesRestoringPortable.kKOyLMDTYh() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.rYGOJFjtxwvhEVT(), true).commit();
        String string3 = string2 + new StringBuilder().append(SavesRestoringPortable.MkLfUcFH()).append(SavesRestoringPortable.tnjjhFJevS()).append(SavesRestoringPortable.YhclPDbsVUq()).append(SavesRestoringPortable.vMfqQtls()).append(SavesRestoringPortable.rSjVrKURwEcXO()).append(SavesRestoringPortable.tsgCAawGWlIb()).append(SavesRestoringPortable.rSjVrKURwEcXO()).append(SavesRestoringPortable.tnjjhFJevS()).append(SavesRestoringPortable.tnjjhFJevS()).append(SavesRestoringPortable.YhclPDbsVUq()).append(SavesRestoringPortable.WMcxYjngVgdyJB()).append(SavesRestoringPortable.rSjVrKURwEcXO()).append(SavesRestoringPortable.tnjjhFJevS()).toString();
        Log.i((String)string3, (String)(SavesRestoringPortable.ipPkVRPhyTtSQ() + SavesRestoringPortable.tsgCAawGWlIb() + SavesRestoringPortable.FvSPwiAhaX() + SavesRestoringPortable.kPLrEhRvAAA() + SavesRestoringPortable.MkLfUcFH() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.ipPkVRPhyTtSQ() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.kKOyLMDTYh() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.WMcxYjngVgdyJB() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg()));
        String[] arrstring = assetManager.list("");
        for (int i = 0; i < arrstring.length; ++i) {
            Log.i((String)string3, (String)(new StringBuilder().append(SavesRestoringPortable.aHXghFvUbPr()).append(SavesRestoringPortable.kKOyLMDTYh()).append(SavesRestoringPortable.tnjjhFJevS()).append(SavesRestoringPortable.rYGOJFjtxwvhEVT()).append(SavesRestoringPortable.EcPIODNJBqSF()).append(SavesRestoringPortable.kKOyLMDTYh()).append(SavesRestoringPortable.BhTnPtxiC()).append(SavesRestoringPortable.rSjVrKURwEcXO()).append(SavesRestoringPortable.tnjjhFJevS()).append(SavesRestoringPortable.xnXsQKiiCbEKGb()).toString() + i + new StringBuilder().append(SavesRestoringPortable.DHQUYjAwcdFkBOc()).append(SavesRestoringPortable.gVPWFXCu()).append(SavesRestoringPortable.yNUaAAkWCilns()).append(SavesRestoringPortable.gVPWFXCu()).toString() + arrstring[i]));
        }
        if (SavesRestoringPortable.FileExists(arrstring, SavesRestoringPortable.vSYeBpuVDm() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.vMfqQtls() + SavesRestoringPortable.rSjVrKURwEcXO())) {
            try {
                String string4 = new StringBuilder().append(SavesRestoringPortable.SyCJcgevDrvtBwX()).append(SavesRestoringPortable.vSYeBpuVDm()).append(SavesRestoringPortable.YhclPDbsVUq()).append(SavesRestoringPortable.rYGOJFjtxwvhEVT()).append(SavesRestoringPortable.YhclPDbsVUq()).append(SavesRestoringPortable.SyCJcgevDrvtBwX()).append(SavesRestoringPortable.vSYeBpuVDm()).append(SavesRestoringPortable.YhclPDbsVUq()).append(SavesRestoringPortable.rYGOJFjtxwvhEVT()).append(SavesRestoringPortable.YhclPDbsVUq()).append(SavesRestoringPortable.SyCJcgevDrvtBwX()).toString() + context.getPackageName();
                SavesRestoringPortable.kPLrEhRvAAA() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.kKOyLMDTYh() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.WMcxYjngVgdyJB() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.kKOyLMDTYh() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.BhTnPtxiC() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.vSYeBpuVDm() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg();
                Log.i((String)string3, (String)(SavesRestoringPortable.vSYeBpuVDm() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.vMfqQtls() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.MkLfUcFH() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.kPLrEhRvAAA() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.kKOyLMDTYh() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.WMcxYjngVgdyJB() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg()));
                Cipher cipher = Cipher.getInstance((String)(SavesRestoringPortable.wsUlJFvK() + SavesRestoringPortable.TyYQKvgkeYDwn() + SavesRestoringPortable.ipPkVRPhyTtSQ() + SavesRestoringPortable.SyCJcgevDrvtBwX() + SavesRestoringPortable.QEdVLMqjeE() + SavesRestoringPortable.nKhbmdBJ() + SavesRestoringPortable.QEdVLMqjeE() + SavesRestoringPortable.SyCJcgevDrvtBwX() + SavesRestoringPortable.REyCUbsXe() + SavesRestoringPortable.okdhVRdXfPVFiY() + SavesRestoringPortable.QEdVLMqjeE() + SavesRestoringPortable.ipPkVRPhyTtSQ() + SavesRestoringPortable.lqhmViMwfUb() + SavesRestoringPortable.REyCUbsXe() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.vSYeBpuVDm() + SavesRestoringPortable.vSYeBpuVDm() + SavesRestoringPortable.kKOyLMDTYh() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.WMcxYjngVgdyJB()));
                IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[]{-9, -50, -6, -29, -96, -80, 71, -51, -36, 63, 113, -46, 99, 31, -29, 5});
                cipher.init(2, (Key)new SecretKeySpec(new byte[]{41, -119, 32, -112, 87, -21, -68, 90, -96, 22, -73, -72, 55, -59, 5, 16}, SavesRestoringPortable.wsUlJFvK() + SavesRestoringPortable.TyYQKvgkeYDwn() + SavesRestoringPortable.ipPkVRPhyTtSQ()), (AlgorithmParameterSpec)ivParameterSpec);
                SavesRestoringPortable.unZipIt((InputStream)new CipherInputStream(assetManager.open(SavesRestoringPortable.vSYeBpuVDm() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.vMfqQtls() + SavesRestoringPortable.rSjVrKURwEcXO()), cipher), string4);
                Log.i((String)string3, (String)(SavesRestoringPortable.vSYeBpuVDm() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.vMfqQtls() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.MkLfUcFH() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.ipPkVRPhyTtSQ() + SavesRestoringPortable.pkaoEhfMFwcI() + SavesRestoringPortable.sOlkvTMwNe() + SavesRestoringPortable.sOlkvTMwNe() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.OEKTKvTtXcBk() + SavesRestoringPortable.pkaoEhfMFwcI() + SavesRestoringPortable.BhTnPtxiC() + SavesRestoringPortable.BhTnPtxiC() + SavesRestoringPortable.PtppwkVvNjhjuWm() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.vSYeBpuVDm()));
            }
            catch (Exception exception) {
                SavesRestoringPortable.TyYQKvgkeYDwn() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.MkLfUcFH() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.QEdVLMqjeE() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.kFtGiGsj() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.kKOyLMDTYh() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.BhTnPtxiC() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.vSYeBpuVDm() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.YhclPDbsVUq();
                Log.e((String)string3, (String)(new StringBuilder().append(SavesRestoringPortable.vSYeBpuVDm()).append(SavesRestoringPortable.YhclPDbsVUq()).append(SavesRestoringPortable.rYGOJFjtxwvhEVT()).append(SavesRestoringPortable.YhclPDbsVUq()).append(SavesRestoringPortable.DCTCGoLUbKLCLg()).append(SavesRestoringPortable.tnjjhFJevS()).append(SavesRestoringPortable.YhclPDbsVUq()).append(SavesRestoringPortable.vMfqQtls()).append(SavesRestoringPortable.rSjVrKURwEcXO()).append(SavesRestoringPortable.MkLfUcFH()).append(SavesRestoringPortable.gVPWFXCu()).append(SavesRestoringPortable.OAmIPFKyo()).append(SavesRestoringPortable.rSjVrKURwEcXO()).append(SavesRestoringPortable.tnjjhFJevS()).append(SavesRestoringPortable.tnjjhFJevS()).append(SavesRestoringPortable.YhclPDbsVUq()).append(SavesRestoringPortable.WMcxYjngVgdyJB()).append(SavesRestoringPortable.rSjVrKURwEcXO()).append(SavesRestoringPortable.MkLfUcFH()).append(SavesRestoringPortable.gVPWFXCu()).toString() + exception.getMessage()));
                exception.printStackTrace();
            }
        }
        if (SavesRestoringPortable.FileExists(arrstring, SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnhqFiOl() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.qtlvGYXKnPnmHd() + SavesRestoringPortable.qtlvGYXKnPnmHd() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.vMfqQtls() + SavesRestoringPortable.rSjVrKURwEcXO())) {
            try {
                String string5 = context.getObbDir().getAbsolutePath() + new StringBuilder().append(SavesRestoringPortable.SyCJcgevDrvtBwX()).toString();
                SavesRestoringPortable.kPLrEhRvAAA() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.kKOyLMDTYh() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.WMcxYjngVgdyJB() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.qtlvGYXKnPnmHd() + SavesRestoringPortable.qtlvGYXKnPnmHd() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg();
                Log.i((String)string3, (String)(SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnhqFiOl() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.qtlvGYXKnPnmHd() + SavesRestoringPortable.qtlvGYXKnPnmHd() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.vMfqQtls() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.MkLfUcFH() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.kPLrEhRvAAA() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.kKOyLMDTYh() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.WMcxYjngVgdyJB() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg()));
                SavesRestoringPortable.unZipIt(assetManager.open(SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnhqFiOl() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.qtlvGYXKnPnmHd() + SavesRestoringPortable.qtlvGYXKnPnmHd() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.vMfqQtls() + SavesRestoringPortable.rSjVrKURwEcXO()), string5);
                Log.i((String)string3, (String)(SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnhqFiOl() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.qtlvGYXKnPnmHd() + SavesRestoringPortable.qtlvGYXKnPnmHd() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.vMfqQtls() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.MkLfUcFH() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.ipPkVRPhyTtSQ() + SavesRestoringPortable.pkaoEhfMFwcI() + SavesRestoringPortable.sOlkvTMwNe() + SavesRestoringPortable.sOlkvTMwNe() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.OEKTKvTtXcBk() + SavesRestoringPortable.pkaoEhfMFwcI() + SavesRestoringPortable.BhTnPtxiC() + SavesRestoringPortable.BhTnPtxiC() + SavesRestoringPortable.PtppwkVvNjhjuWm() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.vSYeBpuVDm()));
            }
            catch (Exception exception) {
                SavesRestoringPortable.TyYQKvgkeYDwn() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.MkLfUcFH() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.QEdVLMqjeE() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.kFtGiGsj() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.qtlvGYXKnPnmHd() + SavesRestoringPortable.qtlvGYXKnPnmHd();
                Log.e((String)string3, (String)(new StringBuilder().append(SavesRestoringPortable.rSjVrKURwEcXO()).append(SavesRestoringPortable.tnhqFiOl()).append(SavesRestoringPortable.rYGOJFjtxwvhEVT()).append(SavesRestoringPortable.MeOmchRfXvThjj()).append(SavesRestoringPortable.qtlvGYXKnPnmHd()).append(SavesRestoringPortable.qtlvGYXKnPnmHd()).append(SavesRestoringPortable.DCTCGoLUbKLCLg()).append(SavesRestoringPortable.tnjjhFJevS()).append(SavesRestoringPortable.YhclPDbsVUq()).append(SavesRestoringPortable.vMfqQtls()).append(SavesRestoringPortable.rSjVrKURwEcXO()).append(SavesRestoringPortable.MkLfUcFH()).append(SavesRestoringPortable.gVPWFXCu()).append(SavesRestoringPortable.OAmIPFKyo()).append(SavesRestoringPortable.rSjVrKURwEcXO()).append(SavesRestoringPortable.tnjjhFJevS()).append(SavesRestoringPortable.tnjjhFJevS()).append(SavesRestoringPortable.YhclPDbsVUq()).append(SavesRestoringPortable.WMcxYjngVgdyJB()).append(SavesRestoringPortable.rSjVrKURwEcXO()).append(SavesRestoringPortable.MkLfUcFH()).append(SavesRestoringPortable.gVPWFXCu()).toString() + exception.getMessage()));
                exception.printStackTrace();
            }
        }
        if (SavesRestoringPortable.FileExists(arrstring, SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnhqFiOl() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.vSYeBpuVDm() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.vMfqQtls() + SavesRestoringPortable.rSjVrKURwEcXO())) {
            try {
                String string6 = (Object)Environment.getExternalStorageDirectory() + new StringBuilder().append(SavesRestoringPortable.SyCJcgevDrvtBwX()).append(SavesRestoringPortable.wsUlJFvK()).append(SavesRestoringPortable.PyKHBQHGW()).append(SavesRestoringPortable.vSYeBpuVDm()).append(SavesRestoringPortable.hPSemDNSYmMpeeO()).append(SavesRestoringPortable.MeOmchRfXvThjj()).append(SavesRestoringPortable.kKOyLMDTYh()).append(SavesRestoringPortable.vSYeBpuVDm()).append(SavesRestoringPortable.SyCJcgevDrvtBwX()).append(SavesRestoringPortable.vSYeBpuVDm()).append(SavesRestoringPortable.YhclPDbsVUq()).append(SavesRestoringPortable.rYGOJFjtxwvhEVT()).append(SavesRestoringPortable.YhclPDbsVUq()).append(SavesRestoringPortable.SyCJcgevDrvtBwX()).toString() + context.getPackageName() + new StringBuilder().append(SavesRestoringPortable.SyCJcgevDrvtBwX()).toString();
                SavesRestoringPortable.kPLrEhRvAAA() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.kKOyLMDTYh() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.WMcxYjngVgdyJB() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnhqFiOl() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.BhTnPtxiC() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.vSYeBpuVDm() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg();
                Log.i((String)string3, (String)(SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnhqFiOl() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.vSYeBpuVDm() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.vMfqQtls() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.MkLfUcFH() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.kPLrEhRvAAA() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.kKOyLMDTYh() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.WMcxYjngVgdyJB() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg()));
                SavesRestoringPortable.unZipIt(assetManager.open(SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnhqFiOl() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.vSYeBpuVDm() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.vMfqQtls() + SavesRestoringPortable.rSjVrKURwEcXO()), string6);
                Log.i((String)string3, (String)(SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnhqFiOl() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.vSYeBpuVDm() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.vMfqQtls() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.MkLfUcFH() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.ipPkVRPhyTtSQ() + SavesRestoringPortable.pkaoEhfMFwcI() + SavesRestoringPortable.sOlkvTMwNe() + SavesRestoringPortable.sOlkvTMwNe() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.OEKTKvTtXcBk() + SavesRestoringPortable.pkaoEhfMFwcI() + SavesRestoringPortable.BhTnPtxiC() + SavesRestoringPortable.BhTnPtxiC() + SavesRestoringPortable.PtppwkVvNjhjuWm() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.vSYeBpuVDm()));
            }
            catch (Exception exception) {
                SavesRestoringPortable.TyYQKvgkeYDwn() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.MkLfUcFH() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.QEdVLMqjeE() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.kFtGiGsj() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnhqFiOl() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.BhTnPtxiC() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.vSYeBpuVDm() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg();
                Log.e((String)string3, (String)(new StringBuilder().append(SavesRestoringPortable.rSjVrKURwEcXO()).append(SavesRestoringPortable.tnhqFiOl()).append(SavesRestoringPortable.rYGOJFjtxwvhEVT()).append(SavesRestoringPortable.vSYeBpuVDm()).append(SavesRestoringPortable.YhclPDbsVUq()).append(SavesRestoringPortable.rYGOJFjtxwvhEVT()).append(SavesRestoringPortable.YhclPDbsVUq()).append(SavesRestoringPortable.DCTCGoLUbKLCLg()).append(SavesRestoringPortable.tnjjhFJevS()).append(SavesRestoringPortable.YhclPDbsVUq()).append(SavesRestoringPortable.vMfqQtls()).append(SavesRestoringPortable.rSjVrKURwEcXO()).append(SavesRestoringPortable.MkLfUcFH()).append(SavesRestoringPortable.gVPWFXCu()).append(SavesRestoringPortable.OAmIPFKyo()).append(SavesRestoringPortable.rSjVrKURwEcXO()).append(SavesRestoringPortable.tnjjhFJevS()).append(SavesRestoringPortable.tnjjhFJevS()).append(SavesRestoringPortable.YhclPDbsVUq()).append(SavesRestoringPortable.WMcxYjngVgdyJB()).append(SavesRestoringPortable.rSjVrKURwEcXO()).append(SavesRestoringPortable.MkLfUcFH()).append(SavesRestoringPortable.gVPWFXCu()).toString() + exception.getMessage()));
                exception.printStackTrace();
            }
        }
        SavesRestoringPortable.kPLrEhRvAAA() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.kKOyLMDTYh() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.WMcxYjngVgdyJB() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.sOlkvTMwNe() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.tsgCAawGWlIb() + SavesRestoringPortable.NApBhpIKOuT() + SavesRestoringPortable.BhTnPtxiC() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.vSYeBpuVDm();
        Log.i((String)string3, (String)(SavesRestoringPortable.kPLrEhRvAAA() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.kKOyLMDTYh() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.WMcxYjngVgdyJB() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.sOlkvTMwNe() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.tsgCAawGWlIb() + SavesRestoringPortable.NApBhpIKOuT() + SavesRestoringPortable.BhTnPtxiC() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.vSYeBpuVDm()));
    }

    private static String SyCJcgevDrvtBwX() {
        return "/";
    }

    private static String TtExhpvAtRVc() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "M";
    }

    private static String TyYQKvgkeYDwn() {
        return "E";
    }

    private static String UTAfPEwOUx() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "\u0421";
    }

    private static String VODmwkjm() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "A";
    }

    private static String WMcxYjngVgdyJB() {
        return "g";
    }

    private static String YhclPDbsVUq() {
        return "a";
    }

    private static String aHXghFvUbPr() {
        return "L";
    }

    private static String aivOFibbs() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "D";
    }

    private static String bdyUmNmYRoqMrpU() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return " ";
    }

    private static String gVPWFXCu() {
        return " ";
    }

    private static String hPSemDNSYmMpeeO() {
        return "r";
    }

    private static String hqqlKGSaQefhj() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "\u041e";
    }

    private static String iltsrLeXpMl() {
        return "h";
    }

    private static String ipPkVRPhyTtSQ() {
        return "S";
    }

    private static String jLqyBbGLQs() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "\u0410";
    }

    private static String kFtGiGsj() {
        return "'";
    }

    private static String kKOyLMDTYh() {
        return "i";
    }

    private static String kPLrEhRvAAA() {
        return "R";
    }

    private static String lqhmViMwfUb() {
        return "5";
    }

    private static String mlJBBREC() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "4";
    }

    private static String nKhbmdBJ() {
        return "B";
    }

    private static String oGIPrLRnvIOK() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "\u0427";
    }

    private static String okdhVRdXfPVFiY() {
        return "K";
    }

    private static String pfFVABTcVp() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "\u0419";
    }

    private static String pkaoEhfMFwcI() {
        return "u";
    }

    private static String qaIDiGFAu() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "6";
    }

    private static String qtlvGYXKnPnmHd() {
        return "b";
    }

    private static String rSjVrKURwEcXO() {
        return "e";
    }

    private static String rYGOJFjtxwvhEVT() {
        return "t";
    }

    private static String sOlkvTMwNe() {
        return "c";
    }

    private static String tnhqFiOl() {
        return "x";
    }

    private static String tnjjhFJevS() {
        return "s";
    }

    private static String tsgCAawGWlIb() {
        return "m";
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static void unZipIt(InputStream var0, String var1_1) throws Exception {
        if (SavesRestoringPortable.daDakdsIID != SavesRestoringPortable.PdsjdolaSd) {
            throw new Exception(SavesRestoringPortable.ipPkVRPhyTtSQ() + SavesRestoringPortable.PtppwkVvNjhjuWm() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tsgCAawGWlIb() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg());
        }
        var2_2 = new ZipInputStream(var0);
        var3_3 = new byte[8192];
        if (SavesRestoringPortable.daDakdsIID == SavesRestoringPortable.PdsjdolaSd) ** GOTO lbl8
        throw new Exception(SavesRestoringPortable.ipPkVRPhyTtSQ() + SavesRestoringPortable.PtppwkVvNjhjuWm() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.tsgCAawGWlIb() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.LfpNQvUxpHGVCy() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.NApBhpIKOuT() + SavesRestoringPortable.BhTnPtxiC() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.tnjjhFJevS() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.vSYeBpuVDm() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.kFtGiGsj() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.sOlkvTMwNe() + SavesRestoringPortable.iltsrLeXpMl() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.rYGOJFjtxwvhEVT() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg());
lbl-1000: // 1 sources:
        {
            var2_2.closeEntry();
lbl8: // 2 sources:
            if ((var4_4 = var2_2.getNextEntry()) == null) {
                var2_2.close();
                return;
            }
            if (var4_4.isDirectory()) continue;
            var5_5 = new File(var1_1, var4_4.getName());
            var5_5.getParentFile().mkdirs();
            var7_6 = new FileOutputStream(var5_5, false);
            if (SavesRestoringPortable.daDakdsIID != SavesRestoringPortable.PdsjdolaSd) {
                var7_6.close();
                var2_2.closeEntry();
                var2_2.close();
                throw new Exception(SavesRestoringPortable.MfmTAkWmPnnY() + SavesRestoringPortable.MeOmchRfXvThjj() + SavesRestoringPortable.pkaoEhfMFwcI() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.sOlkvTMwNe() + SavesRestoringPortable.BhTnPtxiC() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.vMfqQtls() + SavesRestoringPortable.rSjVrKURwEcXO() + SavesRestoringPortable.hPSemDNSYmMpeeO() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg());
            }
            while ((var8_7 = var2_2.read(var3_3)) > 0) {
                var7_6.write(var3_3, 0, var8_7);
            }
            var7_6.close();
            ** while (SavesRestoringPortable.daDakdsIID == SavesRestoringPortable.PdsjdolaSd)
        }
lbl25: // 1 sources:
        var2_2.closeEntry();
        var2_2.close();
        throw new Exception(SavesRestoringPortable.wsUlJFvK() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.vSYeBpuVDm() + SavesRestoringPortable.gVPWFXCu() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.WMcxYjngVgdyJB() + SavesRestoringPortable.YhclPDbsVUq() + SavesRestoringPortable.kKOyLMDTYh() + SavesRestoringPortable.PyKHBQHGW() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg() + SavesRestoringPortable.DCTCGoLUbKLCLg());
    }

    private static String vMfqQtls() {
        return "v";
    }

    private static String vSYeBpuVDm() {
        return "d";
    }

    private static String vbHkPgcUWs() {
        daDakdsIID = 1 + daDakdsIID;
        return Character.toString((char)'M');
    }

    private static void wPdauIdcaW(Context context) {
        SavesRestoringPortable.vbHkPgcUWs();
    }

    private static void wPdauIdcaW(Context context, int n) {
        Toast.makeText((Context)context, (CharSequence)"Original Mod by VADIMA666", (int)1).show();
        Toast.makeText((Context)context, (CharSequence)"Modded by Shine Spark330", (int)1).show();
        Toast.makeText((Context)context, (CharSequence)"Game saves in Download/GAMESAVES/AOD", (int)1).show();
        Toast.makeText((Context)context, (CharSequence)"EXCLUSIVELY FOR SBENNY.COM", (int)1).show();
    }

    private static String woAsGYlbtMqyyR() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "I";
    }

    private static String wsUlJFvK() {
        return "A";
    }

    private static String xidAnEwYStWPj() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "\u041c";
    }

    private static String xnXsQKiiCbEKGb() {
        return "[";
    }

    private static String yIaHVTyMSbd() {
        PdsjdolaSd = 1 + PdsjdolaSd;
        return "V";
    }

    private static String yNUaAAkWCilns() {
        return "=";
    }
}

