/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.graphics.Bitmap
 *  android.os.Bundle
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.webkit.WebChromeClient
 *  android.webkit.WebSettings
 *  android.webkit.WebView
 *  android.webkit.WebViewClient
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.ProgressBar
 *  java.lang.Class
 *  java.lang.InterruptedException
 *  java.lang.String
 *  java.lang.Thread
 */
package com.morisoft.NLib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.morisoft.NLib.Native;
import com.morisoft.NLib.NativeCall;
import org.cocos2dx.lib.Cocos2dxActivity;

public class WebPopUpDialog
extends Activity {
    public static String TAG = "WebPopUpDialog";

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
        Intent intent = new Intent((Context)Cocos2dxActivity.m_Activity, WebPopUpDialog.class);
        intent.putExtra("URL", string2);
        intent.putExtra("WIDTH", n);
        intent.putExtra("HEIGHT", n2);
        Cocos2dxActivity.m_Activity.startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle bundle2 = this.getIntent().getExtras();
        if (bundle2 != null) {
            int n = bundle2.getInt("WIDTH");
            int n2 = bundle2.getInt("HEIGHT");
            String string2 = bundle2.getString("URL");
            this.requestWindowFeature(1);
            final LinearLayout linearLayout = new LinearLayout((Context)this);
            linearLayout.setOrientation(1);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(n, n2);
            final LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, 5);
            final ProgressBar progressBar = new ProgressBar((Context)Cocos2dxActivity.m_Activity, null, 16842872);
            progressBar.setProgress(0);
            progressBar.setMax(100);
            WebView webView = new WebView((Context)Cocos2dxActivity.m_Activity);
            webView.setWebChromeClient(new WebChromeClient(){

                /*
                 * Enabled aggressive block sorting
                 * Enabled unnecessary exception pruning
                 * Enabled aggressive exception aggregation
                 */
                public void onProgressChanged(WebView webView, int n) {
                    progressBar.setProgress(n);
                    if (n >= 100) {
                        try {
                            Thread.sleep((long)100L);
                        }
                        catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        linearLayout.removeView((View)progressBar);
                        progressBar.setProgress(0);
                    }
                }
            });
            webView.setWebViewClient(new WebViewClient(){

                public void onPageStarted(WebView webView, String string2, Bitmap bitmap) {
                    if (progressBar.getProgress() == 0 || !progressBar.isShown()) {
                        progressBar.setProgress(0);
                        linearLayout.addView((View)progressBar, 0, (ViewGroup.LayoutParams)layoutParams2);
                    }
                }

                public boolean shouldOverrideUrlLoading(WebView webView, String string2) {
                    Native.timerResume();
                    WebPopUpDialog.this.finish();
                    NativeCall.WebLink(string2);
                    return true;
                }
            });
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(string2);
            linearLayout.addView((View)webView);
            this.setContentView((View)linearLayout, (ViewGroup.LayoutParams)layoutParams);
            return;
        }
        this.finish();
        Native.timerResume();
    }

}

