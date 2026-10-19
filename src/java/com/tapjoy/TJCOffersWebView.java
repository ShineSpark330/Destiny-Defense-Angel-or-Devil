/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.Dialog
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.graphics.Bitmap
 *  android.net.Uri
 *  android.os.Bundle
 *  android.util.AttributeSet
 *  android.view.KeyEvent
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.webkit.WebSettings
 *  android.webkit.WebView
 *  android.webkit.WebViewClient
 *  android.widget.ProgressBar
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Hashtable
 */
package com.tapjoy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyVideo;
import java.util.Hashtable;

public class TJCOffersWebView
extends Activity {
    final String TAPJOY_OFFERS = "Offers";
    private String clientPackage = "";
    private Dialog dialog = null;
    private String offersURL = null;
    private ProgressBar progressBar;
    private boolean resumeCalled = false;
    private boolean skipOfferWall = false;
    private String urlParams = "";
    private String userID = "";
    private WebView webView = null;

    /*
     * Enabled aggressive block sorting
     */
    protected void onCreate(Bundle bundle) {
        Bundle bundle2 = this.getIntent().getExtras();
        if (bundle2 != null) {
            if (bundle2.getString("DISPLAY_AD_URL") != null) {
                this.skipOfferWall = true;
                this.offersURL = bundle2.getString("DISPLAY_AD_URL");
            } else {
                this.skipOfferWall = false;
                this.urlParams = bundle2.getString("URL_PARAMS");
                this.userID = bundle2.getString("USER_ID");
                this.urlParams = this.urlParams + "&publisher_user_id=" + this.userID;
                if (TapjoyConnectCore.isVideoEnabled()) {
                    this.urlParams = TapjoyConnectCore.getConnectionType().equals((Object)"wifi") || !TapjoyConnectCore.isVideoCacheEnabled() ? this.urlParams + "&all_videos=true" : this.urlParams + "&video_offer_ids=" + TapjoyConnectCore.getVideoIDs();
                    TapjoyLog.i("Offers", "getVideoIDs: [" + TapjoyConnectCore.getVideoIDs() + "]");
                }
                TapjoyLog.i("Offers", "urlParams: [" + this.urlParams + "]");
                this.offersURL = "https://ws.tapjoyads.com/get_offers/webpage?" + this.urlParams;
            }
        } else {
            TapjoyLog.e("Offers", "Tapjoy offers meta data initialization fail.");
        }
        this.offersURL = this.offersURL.replaceAll(" ", "%20");
        this.clientPackage = TapjoyConnectCore.getClientPackage();
        TapjoyLog.i("Offers", "clientPackage: [" + this.clientPackage + "]");
        super.onCreate(bundle);
        this.requestWindowFeature(1);
        RelativeLayout relativeLayout = new RelativeLayout((Context)this);
        this.webView = new WebView((Context)this);
        this.webView.setWebViewClient((WebViewClient)new TapjoyWebViewClient());
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.progressBar = new ProgressBar((Context)this, null, 16842874);
        this.progressBar.setVisibility(0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.progressBar.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        relativeLayout.addView((View)this.webView, -1, -1);
        relativeLayout.addView((View)this.progressBar);
        this.setContentView((View)relativeLayout);
        this.webView.loadUrl(this.offersURL);
        TapjoyLog.i("Offers", "Opening URL = [" + this.offersURL + "]");
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.webView != null) {
            this.webView.clearCache(true);
            this.webView.destroyDrawingCache();
            this.webView.destroy();
        }
    }

    public boolean onKeyDown(int n, KeyEvent keyEvent) {
        if (n == 4 && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }

    protected void onResume() {
        super.onResume();
        if (this.offersURL != null && this.webView != null) {
            this.webView.loadUrl(this.offersURL);
        }
        if (this.resumeCalled && TapjoyConnectCore.getInstance() != null) {
            TapjoyLog.i("Offers", "call connect");
            TapjoyConnectCore.getInstance().callConnect();
        }
        this.resumeCalled = true;
    }

    private class TapjoyWebViewClient
    extends WebViewClient {
        private TapjoyWebViewClient() {
        }

        public void onPageFinished(WebView webView, String string2) {
            TJCOffersWebView.this.progressBar.setVisibility(8);
        }

        public void onPageStarted(WebView webView, String string2, Bitmap bitmap) {
            TJCOffersWebView.this.progressBar.setVisibility(0);
            TJCOffersWebView.this.progressBar.bringToFront();
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public boolean shouldOverrideUrlLoading(WebView webView, String string2) {
            String string3;
            Hashtable hashtable;
            boolean bl;
            String string4;
            TapjoyLog.i("Offers", "URL = [" + string2 + "]");
            if (string2.startsWith("tjvideo://")) {
                hashtable = new Hashtable();
                bl = false;
                string3 = "";
                string4 = "";
            } else {
                if (string2.contains((CharSequence)"ws.tapjoyads.com")) {
                    TapjoyLog.i("Offers", "Open redirecting URL = [" + string2 + "]");
                    webView.loadUrl(string2);
                    return true;
                }
                TapjoyLog.i("Offers", "Opening URL in new browser = [" + string2 + "]");
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((String)string2));
                TJCOffersWebView.this.startActivity(intent);
                TapjoyLog.i("Offers", "skipOfferWall: " + TJCOffersWebView.this.skipOfferWall);
                if (!TJCOffersWebView.this.skipOfferWall) return true;
                TJCOffersWebView.this.finish();
                return true;
            }
            for (int i = string2.indexOf((String)"://") + "://".length(); i < string2.length() && i != -1; ++i) {
                char c = string2.charAt(i);
                if (!bl) {
                    if (c == '=') {
                        bl = true;
                        string4 = Uri.decode((String)string3);
                        string3 = "";
                        continue;
                    }
                    string3 = string3 + c;
                    continue;
                }
                if (!bl) continue;
                if (c == '&') {
                    String string5 = Uri.decode((String)string3);
                    string3 = "";
                    TapjoyLog.i("Offers", "k:v: " + string4 + ", " + string5);
                    hashtable.put((Object)string4, (Object)string5);
                    bl = false;
                    continue;
                }
                string3 = string3 + c;
            }
            if (bl && string3.length() > 0) {
                String string6 = Uri.decode((String)string3);
                TapjoyLog.i("Offers", "k:v: " + string4 + ", " + string6);
                hashtable.put((Object)string4, (Object)string6);
            }
            String string7 = (String)hashtable.get((Object)"video_id");
            String string8 = (String)hashtable.get((Object)"amount");
            String string9 = (String)hashtable.get((Object)"currency_name");
            String string10 = (String)hashtable.get((Object)"click_url");
            String string11 = (String)hashtable.get((Object)"video_complete_url");
            TapjoyLog.i("Offers", "videoID: " + string7);
            TapjoyLog.i("Offers", "currencyAmount: " + string8);
            TapjoyLog.i("Offers", "currencyName: " + string9);
            TapjoyLog.i("Offers", "clickURL: " + string10);
            TapjoyLog.i("Offers", "webviewURL: " + string11);
            if (TapjoyVideo.getInstance().startVideo(string7, string9, string8, string10, string11)) {
                TapjoyLog.i("Offers", "VIDEO");
                return true;
            }
            TapjoyLog.e("Offers", "Unable to play video: " + string7);
            TJCOffersWebView.this.dialog = (Dialog)new AlertDialog.Builder((Context)TJCOffersWebView.this).setTitle((CharSequence)"").setMessage((CharSequence)"Unable to play video.").setPositiveButton((CharSequence)"OK", new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialogInterface, int n) {
                    dialogInterface.dismiss();
                }
            }).create();
            try {
                TJCOffersWebView.this.dialog.show();
                return true;
            }
            catch (Exception exception) {
                TapjoyLog.e("Offers", "e: " + exception.toString());
                return true;
            }
        }

    }

}

