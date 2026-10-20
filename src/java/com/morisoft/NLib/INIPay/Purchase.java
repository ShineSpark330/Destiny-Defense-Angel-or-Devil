/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.Dialog
 *  android.app.ProgressDialog
 *  android.content.ActivityNotFoundException
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.graphics.Bitmap
 *  android.net.Uri
 *  android.os.Bundle
 *  android.util.Log
 *  android.view.View
 *  android.webkit.WebChromeClient
 *  android.webkit.WebSettings
 *  android.webkit.WebView
 *  android.webkit.WebViewClient
 *  android.widget.Toast
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.net.URLEncoder
 */
package com.morisoft.NLib.INIPay;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.morisoft.NLib.Device;
import java.net.URLEncoder;

public class Purchase
extends Activity {
    private static final int DIALOG_ISP = 2;
    private static final int DIALOG_PROGRESS_WEBVIEW;
    WebView webview;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String string2 = "morisoft00";
        String string3 = Device.getPhoneNumber();
        Bundle bundle2 = this.getIntent().getExtras();
        if (bundle2 != null) {
            boolean bl = bundle2.getBoolean("Test");
            String string4 = bundle2.getString("GAME_ID");
            String string5 = bundle2.getString("P_GOODS");
            String string6 = bundle2.getString("P_AMT");
            if (bl) {
                string2 = "INIpayTest";
            }
            this.webview = new WebView((Context)this);
            this.webview.setWebChromeClient(new WebChromeClient());
            this.webview.setWebViewClient((WebViewClient)new PurchaseWebViewClient());
            this.webview.getSettings().setJavaScriptEnabled(true);
            String string7 = "http://www.morisoft.net:8080/INICIS/INIPayWeb.html?P_MID=" + URLEncoder.encode((String)string2) + "&P_MNAME=" + URLEncoder.encode((String)"(\uc8fc)\ubaa8\ub9ac\uc18c\ud504\ud2b8") + "&P_MOBILE=" + URLEncoder.encode((String)string3) + "&P_GOODS=" + URLEncoder.encode((String)string5) + "&P_AMT=" + URLEncoder.encode((String)string6) + "&GAME_ID=" + URLEncoder.encode((String)string4);
            this.webview.loadUrl(string7);
            this.setContentView((View)this.webview);
            return;
        }
        this.finish();
    }

    protected Dialog onCreateDialog(int n) {
        switch (n) {
            default: {
                return super.onCreateDialog(n);
            }
            case 0: {
                ProgressDialog progressDialog = new ProgressDialog((Context)this);
                progressDialog.setMessage((CharSequence)"Web page loading...\nPlease wait.");
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(true);
                return progressDialog;
            }
            case 2: 
        }
        return new AlertDialog.Builder((Context)this).setIcon(17301543).setTitle((CharSequence)"\uc54c\ub9bc").setMessage((CharSequence)"\ubaa8\ubc14\uc77c ISP \uc5b4\ud50c\ub9ac\ucf00\uc774\uc158\uc774 \uc124\uce58\ub418\uc5b4 \uc788\uc9c0 \uc54a\uc2b5\ub2c8\ub2e4. \n\uc124\uce58\ub97c \ub20c\ub7ec \uc9c4\ud589 \ud574 \uc8fc\uc2ed\uc2dc\uc694.\n\ucde8\uc18c\ub97c \ub204\ub974\uba74 \uacb0\uc81c\uac00 \ucde8\uc18c \ub429\ub2c8\ub2e4.").setPositiveButton((CharSequence)"\uc124\uce58", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                Purchase.this.webview.loadUrl("http://mobile.vpay.co.kr/jsp/MISP/andown.jsp");
            }
        }).setNegativeButton((CharSequence)"\ucde8\uc18c", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                Toast.makeText((Context)Purchase.this, (CharSequence)"(-1)\uacb0\uc81c\ub97c \ucde8\uc18c \ud558\uc168\uc2b5\ub2c8\ub2e4.", (int)0).show();
                Purchase.this.finish();
            }
        }).create();
    }

    public class PurchaseWebViewClient
    extends WebViewClient {
        public void onPageFinished(WebView webView, String string2) {
            try {
                Purchase.this.dismissDialog(0);
                Purchase.this.removeDialog(0);
                return;
            }
            catch (Exception exception) {
                return;
            }
        }

        public void onPageStarted(WebView webView, String string2, Bitmap bitmap) {
            try {
                Purchase.this.showDialog(0);
                return;
            }
            catch (Exception exception) {
                return;
            }
        }

        public void onReceivedError(WebView webView, int n, String string2, String string3) {
            webView.loadData("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/></head><body>\uc694\uccad\uc2e4\ud328 : (" + n + ")" + string2 + "</body></html>", "text/html", "utf-8");
        }

        /*
         * Loose catch block
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        public boolean shouldOverrideUrlLoading(WebView webView, String string2) {
            Log.d((String)"<sonnbk>", (String)("####URL:" + string2));
            if (!(string2.startsWith("http://") || string2.startsWith("https://") || string2.startsWith("javascript:"))) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((String)string2));
                Purchase.this.startActivity(intent);
                if (!string2.startsWith("ispmobile://")) {
                    if (!string2.startsWith("INIPayResultActivity://")) return true;
                }
                Purchase.this.finish();
                return true;
            }
            webView.loadUrl(string2);
            return false;
            catch (ActivityNotFoundException activityNotFoundException) {
                return true;
            }
        }
    }

}

