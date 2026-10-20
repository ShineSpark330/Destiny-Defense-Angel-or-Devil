/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  java.io.ByteArrayInputStream
 *  java.io.InputStream
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.Thread
 *  javax.xml.parsers.DocumentBuilder
 *  javax.xml.parsers.DocumentBuilderFactory
 *  org.w3c.dom.Document
 *  org.w3c.dom.NodeList
 */
package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyFeaturedAppNotifier;
import com.tapjoy.TapjoyFeaturedAppObject;
import com.tapjoy.TapjoyFeaturedAppWebView;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyURLConnection;
import com.tapjoy.TapjoyUtil;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class TapjoyFeaturedApp {
    private static TapjoyFeaturedAppNotifier featuredAppNotifier;
    public static String featuredAppURLParams;
    private static TapjoyURLConnection tapjoyURLConnection;
    final String TAPJOY_FEATURED_APP = "Full Screen Ad";
    private Context context;
    private String currencyID;
    private int displayCount = 5;
    private TapjoyFeaturedAppObject featuredAppObject = null;

    static {
        tapjoyURLConnection = null;
    }

    public TapjoyFeaturedApp(Context context) {
        this.context = context;
        tapjoyURLConnection = new TapjoyURLConnection();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean buildResponse(String string2) {
        boolean bl;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            int n;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(string2.getBytes("UTF-8"));
            Document document = documentBuilderFactory.newDocumentBuilder().parse((InputStream)byteArrayInputStream);
            this.featuredAppObject.cost = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("Cost"));
            String string3 = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("Amount"));
            if (string3 != null) {
                this.featuredAppObject.amount = Integer.parseInt((String)string3);
            }
            this.featuredAppObject.description = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("Description"));
            this.featuredAppObject.iconURL = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("IconURL"));
            this.featuredAppObject.name = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("Name"));
            this.featuredAppObject.redirectURL = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("RedirectURL"));
            this.featuredAppObject.storeID = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("StoreID"));
            this.featuredAppObject.fullScreenAdURL = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("FullScreenAdURL"));
            TapjoyLog.i("Full Screen Ad", "cost: " + this.featuredAppObject.cost);
            TapjoyLog.i("Full Screen Ad", "amount: " + this.featuredAppObject.amount);
            TapjoyLog.i("Full Screen Ad", "description: " + this.featuredAppObject.description);
            TapjoyLog.i("Full Screen Ad", "iconURL: " + this.featuredAppObject.iconURL);
            TapjoyLog.i("Full Screen Ad", "name: " + this.featuredAppObject.name);
            TapjoyLog.i("Full Screen Ad", "redirectURL: " + this.featuredAppObject.redirectURL);
            TapjoyLog.i("Full Screen Ad", "storeID: " + this.featuredAppObject.storeID);
            TapjoyLog.i("Full Screen Ad", "fullScreenAdURL: " + this.featuredAppObject.fullScreenAdURL);
            bl = this.featuredAppObject.fullScreenAdURL != null && (n = this.featuredAppObject.fullScreenAdURL.length()) != 0;
        }
        catch (Exception exception) {
            TapjoyLog.e("Full Screen Ad", "Error parsing XML: " + exception.toString());
            bl = false;
        }
        if (!bl) {
            featuredAppNotifier.getFeaturedAppResponseFailed("Failed to parse XML file from response");
            return true;
        }
        if (this.getDisplayCountForStoreID(this.featuredAppObject.storeID) >= this.displayCount) {
            featuredAppNotifier.getFeaturedAppResponseFailed("Full Screen Ad to display has exceeded display count");
            return bl;
        }
        featuredAppNotifier.getFeaturedAppResponse(this.featuredAppObject);
        if (!TapjoyConnectCore.getAppID().equals((Object)this.featuredAppObject.storeID)) {
            this.incrementDisplayCountForStoreID(this.featuredAppObject.storeID);
        }
        return bl;
    }

    private int getDisplayCountForStoreID(String string2) {
        int n = this.context.getSharedPreferences("TapjoyFeaturedAppPrefs", 0).getInt(string2, 0);
        TapjoyLog.i("Full Screen Ad", "getDisplayCount: " + n + ", storeID: " + string2);
        return n;
    }

    private void incrementDisplayCountForStoreID(String string2) {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("TapjoyFeaturedAppPrefs", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int n = 1 + sharedPreferences.getInt(string2, 0);
        TapjoyLog.i("Full Screen Ad", "incrementDisplayCount: " + n + ", storeID: " + string2);
        editor.putInt(string2, n);
        editor.commit();
    }

    public void getFeaturedApp(TapjoyFeaturedAppNotifier tapjoyFeaturedAppNotifier) {
        TapjoyLog.i("Full Screen Ad", "Getting Full Screen Ad");
        this.getFeaturedApp(null, tapjoyFeaturedAppNotifier);
    }

    public void getFeaturedApp(String string2, TapjoyFeaturedAppNotifier tapjoyFeaturedAppNotifier) {
        this.currencyID = string2;
        TapjoyLog.i("Full Screen Ad", "Getting Full Screen Ad userID: " + TapjoyConnectCore.getUserID() + ", currencyID: " + this.currencyID);
        featuredAppNotifier = tapjoyFeaturedAppNotifier;
        this.featuredAppObject = new TapjoyFeaturedAppObject();
        featuredAppURLParams = TapjoyConnectCore.getURLParams();
        featuredAppURLParams = featuredAppURLParams + "&publisher_user_id=" + TapjoyConnectCore.getUserID();
        if (this.currencyID != null) {
            featuredAppURLParams = featuredAppURLParams + "&currency_id=" + this.currencyID;
        }
        new Thread(new Runnable(){

            public void run() {
                String string2 = tapjoyURLConnection.connectToURL("https://ws.tapjoyads.com/get_offers/featured?", TapjoyFeaturedApp.featuredAppURLParams);
                boolean bl = false;
                if (string2 != null) {
                    bl = TapjoyFeaturedApp.this.buildResponse(string2);
                }
                if (!bl) {
                    featuredAppNotifier.getFeaturedAppResponseFailed("Error retrieving full screen ad data from the server.");
                }
            }
        }).start();
    }

    public TapjoyFeaturedAppObject getFeaturedAppObject() {
        return this.featuredAppObject;
    }

    public void setDisplayCount(int n) {
        this.displayCount = n;
    }

    public void showFeaturedAppFullScreenAd() {
        String string2 = "";
        if (this.featuredAppObject != null) {
            string2 = this.featuredAppObject.fullScreenAdURL;
        }
        TapjoyLog.i("Full Screen Ad", "Displaying Full Screen AD with URL: " + string2);
        if (string2.length() != 0) {
            String string3 = TapjoyConnectCore.getURLParams();
            if (this.currencyID != null && this.currencyID.length() > 0) {
                string3 = string3 + "&currency_id=" + this.currencyID;
            }
            Intent intent = new Intent(this.context, TapjoyFeaturedAppWebView.class);
            intent.setFlags(268435456);
            intent.putExtra("USER_ID", TapjoyConnectCore.getUserID());
            intent.putExtra("URL_PARAMS", string3);
            intent.putExtra("FULLSCREEN_AD_URL", string2);
            this.context.startActivity(intent);
        }
    }

}

