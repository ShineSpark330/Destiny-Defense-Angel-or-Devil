/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.Application
 *  android.app.Dialog
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 *  android.content.DialogInterface$OnClickListener
 *  android.content.DialogInterface$OnKeyListener
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.media.AudioManager
 *  android.os.Bundle
 *  android.os.Environment
 *  android.os.Handler
 *  android.os.Process
 *  android.util.DisplayMetrics
 *  android.util.Log
 *  android.view.Display
 *  android.view.KeyEvent
 *  android.view.WindowManager
 *  android.widget.LinearLayout
 *  android.widget.Toast
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.RuntimeException
 *  java.lang.String
 *  java.lang.System
 */
package org.cocos2dx.lib;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Process;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.morisoft.NLib.Android.Purchase;
import com.morisoft.NLib.Device;
import com.morisoft.NLib.Native;
import com.morisoft.NLib.NativeCall;
import com.morisoft.NLib.NativePopUp;
import com.morisoft.NLib.Sound.BackGroundMusic;
import com.morisoft.NLib.Sound.EffectSound;
import com.morisoft.NLib.TextInputWraper;
import org.cocos2dx.lib.Cocos2dxBitmap;

public class Cocos2dxActivity
extends Activity {
    protected static String TAG = "Cocos2dxActivity";
    private static BackGroundMusic backgroundMusicPlayer;
    public static Cocos2dxActivity m_Activity;
    public static final boolean m_bDebug;
    public static String m_strFullPath;
    private static EffectSound soundPlayer;
    public static int touchScreenHeight;
    public Handler m_Handler = new Handler();
    public LinearLayout m_linearLayout = null;
    public int screenHeight;
    public int screenWidth;

    static {
        touchScreenHeight = 0;
        m_strFullPath = null;
    }

    public static void DEBUG_LOG(String string2, String string3) {
    }

    public static void end() {
        backgroundMusicPlayer.end();
        soundPlayer.end();
    }

    public static float getBackgroundMusicVolume() {
        return backgroundMusicPlayer.getBackgroundVolume();
    }

    public static float getEffectsVolume() {
        return soundPlayer.getEffectsVolume();
    }

    public static boolean isBackgroundMusicPlaying() {
        return backgroundMusicPlayer.isBackgroundMusicPlaying();
    }

    static native void nativeSetPaths(String var0);

    public static void pauseAllEffects() {
        soundPlayer.pauseAllEffects();
    }

    public static void pauseBackgroundMusic() {
        backgroundMusicPlayer.pauseBackgroundMusic();
    }

    public static void pauseEffect(int n) {
        soundPlayer.pauseEffect(n);
    }

    public static void playBackgroundMusic(String string2, boolean bl) {
        backgroundMusicPlayer.playBackgroundMusic(string2, bl);
    }

    public static int playEffect(String string2, boolean bl) {
        return soundPlayer.playEffect(string2, bl);
    }

    public static void preloadBackgroundMusic(String string2) {
        backgroundMusicPlayer.preloadBackgroundMusic(string2);
    }

    public static void preloadEffect(String string2) {
        soundPlayer.preloadEffect(string2);
    }

    public static void resumeAllEffects() {
        soundPlayer.resumeAllEffects();
    }

    public static void resumeBackgroundMusic() {
        backgroundMusicPlayer.resumeBackgroundMusic();
    }

    public static void resumeEffect(int n) {
        soundPlayer.resumeEffect(n);
    }

    public static void rewindBackgroundMusic() {
        backgroundMusicPlayer.rewindBackgroundMusic();
    }

    public static void setBackgroundMusicVolume(float f) {
        backgroundMusicPlayer.setBackgroundVolume(f);
    }

    public static void setEffectsVolume(float f) {
        soundPlayer.setEffectsVolume(f);
    }

    public static void stopAllEffects() {
        soundPlayer.stopAllEffects();
    }

    public static void stopBackgroundMusic() {
        backgroundMusicPlayer.stopBackgroundMusic();
    }

    public static void stopEffect(int n) {
        soundPlayer.stopEffect(n);
    }

    public static void terminateProcess() {
        Process.killProcess((int)Process.myPid());
    }

    public static void unloadEffect(String string2) {
        soundPlayer.unloadEffect(string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void createToast(String string2, boolean bl) {
        int n = bl ? 1 : 0;
        Toast.makeText((Context)this, (CharSequence)string2, (int)n).show();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m_Activity = this;
        soundPlayer = new EffectSound((Context)this);
        backgroundMusicPlayer = new BackGroundMusic((Context)this);
        Device.initialize(this);
        NativePopUp.initialize(this);
        Cocos2dxBitmap.setContext((Context)this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.screenWidth = displayMetrics.widthPixels;
        this.screenHeight = displayMetrics.heightPixels;
        touchScreenHeight = 0;
        this.m_linearLayout = new LinearLayout((Context)this);
        this.m_linearLayout.setOrientation(1);
        this.m_linearLayout.setGravity(17);
        String string2 = this.getApplication().getPackageName();
        String string3 = (Object)Environment.getDataDirectory() + "/data/" + string2 + "/";
        Log.d((String)TAG, (String)"library loading......");
        System.loadLibrary((String)"cocosdenshion");
        System.loadLibrary((String)string2.substring(1 + string2.lastIndexOf(".")));
        m_Activity = this;
        this.setPackgeName(string3, string2);
        NativeCall.initialize(m_Activity);
    }

    protected Dialog onCreateDialog(int n) {
        Log.i((String)"createDialog", (String)("onCreateDialog id: " + n));
        switch (n) {
            default: {
                return null;
            }
            case 7: {
                return new AlertDialog.Builder((Context)this).setTitle((CharSequence)NativePopUp.strDialogTitle).setMessage((CharSequence)NativePopUp.strDialogMsg).setOnKeyListener(new DialogInterface.OnKeyListener(){

                    public boolean onKey(DialogInterface dialogInterface, int n, KeyEvent keyEvent) {
                        if (n != 4) {
                            NativePopUp.releaseDialog(7);
                        }
                        return false;
                    }
                }).setPositiveButton((CharSequence)NativePopUp.strDialogBtnOK, new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialogInterface, int n) {
                        Purchase.run();
                        NativePopUp.releaseDialog(7);
                    }
                }).setNegativeButton((CharSequence)NativePopUp.strDialogBtnCancel, new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialogInterface, int n) {
                        NativePopUp.releaseDialog(7);
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener(){

                    public void onCancel(DialogInterface dialogInterface) {
                        NativePopUp.releaseDialog(7);
                    }
                }).show();
            }
            case 2: {
                return new AlertDialog.Builder((Context)this).setIcon(17301543).setTitle((CharSequence)"\uc54c \ub9bc").setMessage((CharSequence)NativePopUp.strDialogMsg).setOnKeyListener(new DialogInterface.OnKeyListener(){

                    public boolean onKey(DialogInterface dialogInterface, int n, KeyEvent keyEvent) {
                        if (n != 4) {
                            NativeCall.appExit();
                        }
                        return false;
                    }
                }).setPositiveButton((CharSequence)NativePopUp.strDialogBtnOK, new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialogInterface, int n) {
                        NativeCall.appExit();
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener(){

                    public void onCancel(DialogInterface dialogInterface) {
                        NativeCall.appExit();
                    }
                }).show();
            }
            case 3: {
                return new AlertDialog.Builder((Context)this).setIcon(17301543).setTitle((CharSequence)"\uc54c \ub9bc").setMessage((CharSequence)NativePopUp.strDialogMsg).setPositiveButton((CharSequence)NativePopUp.strDialogBtnOK, new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialogInterface, int n) {
                    }
                }).show();
            }
            case 4: {
                return new AlertDialog.Builder((Context)this).setIcon(17301543).setTitle((CharSequence)"\uc54c \ub9bc").setMessage((CharSequence)NativePopUp.strDialogMsg).setPositiveButton((CharSequence)NativePopUp.strDialogBtnOK, new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialogInterface, int n) {
                    }
                }).show();
            }
            case 0: {
                return NativePopUp.createPopUp();
            }
            case -16777216: 
        }
        return NativePopUp.onCreateExit();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onKeyDown(int n, KeyEvent keyEvent) {
        if (NativePopUp.isShowProgressBar()) {
            return false;
        }
        if (n == 4 || n == 82) {
            Native.keyBegin(n);
            return true;
        }
        if (n == 24) {
            ((AudioManager)this.getSystemService("audio")).adjustStreamVolume(3, 1, 1);
            return super.onKeyDown(n, keyEvent);
        }
        if (n != 25) return super.onKeyDown(n, keyEvent);
        ((AudioManager)this.getSystemService("audio")).adjustStreamVolume(3, -1, 1);
        return super.onKeyDown(n, keyEvent);
    }

    public void onPause() {
        super.onPause();
        TextInputWraper.closeIMEKeyboard();
    }

    protected void setPackgeName(String string2, String string3) {
        ApplicationInfo applicationInfo;
        PackageInfo packageInfo;
        PackageManager packageManager = this.getApplication().getPackageManager();
        try {
            packageInfo = packageManager.getPackageInfo(string3, 0);
            applicationInfo = packageManager.getApplicationInfo(string3, 0);
        }
        catch (PackageManager.NameNotFoundException nameNotFoundException) {
            nameNotFoundException.printStackTrace();
            throw new RuntimeException("Unable to locate assets, aborting...");
        }
        m_strFullPath = string2;
        Cocos2dxActivity.nativeSetPaths(applicationInfo.sourceDir);
        Native.setVersion(packageInfo.versionCode);
        Native.setFullpaths(applicationInfo.sourceDir, string2);
    }

}

