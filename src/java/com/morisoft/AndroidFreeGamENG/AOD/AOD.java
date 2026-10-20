/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.AssetManager
 *  android.os.Bundle
 *  android.os.StrictMode
 *  android.os.StrictMode$ThreadPolicy
 *  android.os.StrictMode$ThreadPolicy$Builder
 *  android.os.StrictMode$VmPolicy
 *  android.os.StrictMode$VmPolicy$Builder
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  java.io.File
 *  java.io.FileOutputStream
 *  java.io.InputStream
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.Throwable
 */
package com.morisoft.AndroidFreeGamENG.AOD;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.morisoft.NLib.GLSurfaceView;
import com.morisoft.NLib.NEditText;
import com.morisoft.NLib.NativeCall;
import com.morisoft.NLib.SpeedClick;
import com.savegame.SavesRestoringPortable;
import com.tapjoy.TapjoyConnect;
import com.tapjoy.TapjoyFeaturedAppNotifier;
import com.tapjoy.TapjoyFeaturedAppObject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.cocos2dx.lib.Cocos2dxActivity;

public class AOD
extends Cocos2dxActivity
implements TapjoyFeaturedAppNotifier {
    private GLSurfaceView mGLView;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void copyGameSaves() {
        File file = new File("/sdcard/Download/GAMESAVES/AOD");
        file.mkdirs();
        String[] arrstring = new String[]{"saveuserid.dat", "saveCommon.dat", "saveAngel.dat"};
        int n = 0;
        do {
            if (n >= arrstring.length) {
                return;
            }
            String string2 = arrstring[n];
            File file2 = new File(file, string2);
            if (!file2.exists()) {
                try {
                    int n2;
                    AssetManager assetManager = this.getAssets();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("gamesaves/");
                    stringBuilder.append(string2);
                    InputStream inputStream = assetManager.open(stringBuilder.toString());
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    byte[] arrby = new byte[1024];
                    while ((n2 = inputStream.read(arrby)) > 0) {
                        fileOutputStream.write(arrby, 0, n2);
                    }
                    fileOutputStream.close();
                    inputStream.close();
                }
                catch (Exception exception) {}
            }
            ++n;
        } while (true);
    }

    public static void getFeatureApp() {
        TapjoyConnect.getTapjoyConnectInstance().getFeaturedApp((TapjoyFeaturedAppNotifier)((Object)Cocos2dxActivity.m_Activity));
    }

    private void legacyCompat() {
        try {
            StrictMode.setThreadPolicy((StrictMode.ThreadPolicy)new StrictMode.ThreadPolicy.Builder().permitAll().build());
            StrictMode.setVmPolicy((StrictMode.VmPolicy)new StrictMode.VmPolicy.Builder().build());
            return;
        }
        catch (Throwable throwable) {
            return;
        }
    }

    @Override
    public void getFeaturedAppResponse(TapjoyFeaturedAppObject tapjoyFeaturedAppObject) {
        Log.i((String)"EASY_APP", (String)"Displaying Featured App..");
        TapjoyConnect.getTapjoyConnectInstance().showFeaturedAppFullScreenAd();
    }

    @Override
    public void getFeaturedAppResponseFailed(String string2) {
        Log.i((String)"EASY_APP", (String)("No Featured App to display: " + string2));
    }

    @Override
    protected void onCreate(Bundle bundle) {
        this.legacyCompat();
        this.copyGameSaves();
        super.onCreate(bundle);
        SavesRestoringPortable.DoSmth((Context)this);
        this.setContentView(2130903040);
        this.mGLView = (GLSurfaceView)this.findViewById(2131034113);
        this.mGLView.setTextField((NEditText)this.findViewById(2131034112));
        m_Activity = this;
        NativeCall.initialize(m_Activity);
        this.addContentView((View)this.m_linearLayout, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -1));
        Button button = new Button((Context)this);
        button.setText((CharSequence)"1x");
        button.setTextColor(-1);
        button.setBackgroundColor(-1728053248);
        button.setOnClickListener((View.OnClickListener)new SpeedClick());
        this.addContentView((View)button, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2, 53));
    }

    @Override
    public void onPause() {
        super.onPause();
        this.mGLView.onPause();
    }

    public void onResume() {
        super.onResume();
        this.mGLView.onResume();
    }
}

