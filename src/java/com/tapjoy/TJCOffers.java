/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.util.UUID
 *  org.w3c.dom.Document
 *  org.w3c.dom.NodeList
 */
package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import com.tapjoy.TJCOffersWebView;
import com.tapjoy.TapjoyAwardPointsNotifier;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyEarnedPointsNotifier;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyNotifier;
import com.tapjoy.TapjoySpendPointsNotifier;
import com.tapjoy.TapjoyURLConnection;
import com.tapjoy.TapjoyUtil;
import java.util.UUID;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class TJCOffers {
    public static final String TAPJOY_OFFERS = "TapjoyOffers";
    public static final String TAPJOY_POINTS = "TapjoyPoints";
    private static TapjoyAwardPointsNotifier tapjoyAwardPointsNotifier;
    private static TapjoyEarnedPointsNotifier tapjoyEarnedPointsNotifier;
    private static TapjoyNotifier tapjoyNotifier;
    private static TapjoySpendPointsNotifier tapjoySpendPointsNotifier;
    int awardTapPoints = 0;
    Context context;
    private String multipleCurrencyID = "";
    private String multipleCurrencySelector = "";
    String spendTapPoints = null;

    public TJCOffers(Context context) {
        this.context = context;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean handleAwardPointsResponse(String string2) {
        Document document;
        String string3;
        block7 : {
            block6 : {
                document = TapjoyUtil.buildDocument(string2);
                if (document == null) break block6;
                string3 = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("Success"));
                if (string3 == null || !string3.equals((Object)"true")) break block7;
                String string4 = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("TapPoints"));
                String string5 = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("CurrencyName"));
                if (string4 != null && string5 != null) {
                    TapjoyConnectCore.saveTapPointsTotal(Integer.parseInt((String)string4));
                    tapjoyAwardPointsNotifier.getAwardPointsResponse(string5, Integer.parseInt((String)string4));
                    return true;
                }
                TapjoyLog.e(TAPJOY_POINTS, "Invalid XML: Missing tags.");
            }
            do {
                return false;
                break;
            } while (true);
        }
        if (string3 != null && string3.endsWith("false")) {
            String string6 = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("Message"));
            TapjoyLog.i(TAPJOY_POINTS, string6);
            tapjoyAwardPointsNotifier.getAwardPointsResponseFailed(string6);
            return true;
        }
        TapjoyLog.e(TAPJOY_POINTS, "Invalid XML: Missing <Success> tag.");
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean handleGetPointsResponse(String string2) {
        TJCOffers tJCOffers = this;
        synchronized (tJCOffers) {
            Document document = TapjoyUtil.buildDocument(string2);
            if (document == null) return false;
            String string3 = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("Success"));
            if (string3 != null && string3.equals((Object)"true")) {
                String string4 = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("TapPoints"));
                String string5 = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("CurrencyName"));
                if (string4 != null && string5 != null) {
                    try {
                        int n = Integer.parseInt((String)string4);
                        int n2 = TapjoyConnectCore.getLocalTapPointsTotal();
                        if (tapjoyEarnedPointsNotifier != null && n2 != -9999 && n > n2) {
                            TapjoyLog.i(TAPJOY_POINTS, "earned: " + (n - n2));
                            tapjoyEarnedPointsNotifier.earnedTapPoints(n - n2);
                        }
                        TapjoyConnectCore.saveTapPointsTotal(Integer.parseInt((String)string4));
                        tapjoyNotifier.getUpdatePoints(string5, Integer.parseInt((String)string4));
                        return true;
                    }
                    catch (Exception exception) {
                        TapjoyLog.e(TAPJOY_POINTS, "Error parsing XML.");
                        return false;
                    }
                } else {
                    TapjoyLog.e(TAPJOY_POINTS, "Invalid XML: Missing tags.");
                }
                return false;
            } else {
                TapjoyLog.e(TAPJOY_POINTS, "Invalid XML: Missing <Success> tag.");
            }
            return false;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean handleSpendPointsResponse(String string2) {
        Document document;
        String string3;
        block7 : {
            block6 : {
                document = TapjoyUtil.buildDocument(string2);
                if (document == null) break block6;
                string3 = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("Success"));
                if (string3 == null || !string3.equals((Object)"true")) break block7;
                String string4 = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("TapPoints"));
                String string5 = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("CurrencyName"));
                if (string4 != null && string5 != null) {
                    TapjoyConnectCore.saveTapPointsTotal(Integer.parseInt((String)string4));
                    tapjoySpendPointsNotifier.getSpendPointsResponse(string5, Integer.parseInt((String)string4));
                    return true;
                }
                TapjoyLog.e(TAPJOY_POINTS, "Invalid XML: Missing tags.");
            }
            do {
                return false;
                break;
            } while (true);
        }
        if (string3 != null && string3.endsWith("false")) {
            String string6 = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("Message"));
            TapjoyLog.i(TAPJOY_POINTS, string6);
            tapjoySpendPointsNotifier.getSpendPointsResponseFailed(string6);
            return true;
        }
        TapjoyLog.e(TAPJOY_POINTS, "Invalid XML: Missing <Success> tag.");
        return false;
    }

    public void awardTapPoints(int n, TapjoyAwardPointsNotifier tapjoyAwardPointsNotifier) {
        if (n < 0) {
            TapjoyLog.e(TAPJOY_POINTS, "spendTapPoints error: amount must be a positive number");
            return;
        }
        this.awardTapPoints = n;
        TJCOffers.tapjoyAwardPointsNotifier = tapjoyAwardPointsNotifier;
        new Thread(new Runnable(){

            public void run() {
                String string2 = UUID.randomUUID().toString();
                long l = System.currentTimeMillis() / 1000L;
                String string3 = TapjoyConnectCore.getURLParams();
                String string4 = string3 + "&tap_points=" + TJCOffers.this.awardTapPoints;
                String string5 = string4 + "&publisher_user_id=" + TapjoyConnectCore.getUserID();
                String string6 = string5 + "&guid=" + string2;
                String string7 = string6 + "&timestamp=" + l;
                String string8 = string7 + "&verifier=" + TapjoyConnectCore.getAwardPointsVerifier(l, TJCOffers.this.awardTapPoints, string2);
                String string9 = new TapjoyURLConnection().connectToURL("https://ws.tapjoyads.com/points/award?", string8);
                boolean bl = false;
                if (string9 != null) {
                    bl = TJCOffers.this.handleAwardPointsResponse(string9);
                }
                if (!bl) {
                    tapjoyAwardPointsNotifier.getAwardPointsResponseFailed("Failed to award points.");
                }
            }
        }).start();
    }

    public void getTapPoints(TapjoyNotifier tapjoyNotifier) {
        TJCOffers.tapjoyNotifier = tapjoyNotifier;
        new Thread(new Runnable(){

            public void run() {
                String string2 = TapjoyConnectCore.getURLParams();
                String string3 = string2 + "&publisher_user_id=" + TapjoyConnectCore.getUserID();
                String string4 = new TapjoyURLConnection().connectToURL("https://ws.tapjoyads.com/get_vg_store_items/user_account?", string3);
                boolean bl = false;
                if (string4 != null) {
                    bl = TJCOffers.this.handleGetPointsResponse(string4);
                }
                if (!bl) {
                    tapjoyNotifier.getUpdatePointsFailed("Failed to retrieve points from server");
                }
            }
        }).start();
    }

    public void setEarnedPointsNotifier(TapjoyEarnedPointsNotifier tapjoyEarnedPointsNotifier) {
        TJCOffers.tapjoyEarnedPointsNotifier = tapjoyEarnedPointsNotifier;
    }

    public void showOffers() {
        TapjoyLog.i(TAPJOY_OFFERS, "Showing offers with userID: " + TapjoyConnectCore.getUserID());
        Intent intent = new Intent(this.context, TJCOffersWebView.class);
        intent.setFlags(268435456);
        intent.putExtra("USER_ID", TapjoyConnectCore.getUserID());
        intent.putExtra("URL_PARAMS", TapjoyConnectCore.getURLParams());
        this.context.startActivity(intent);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void showOffersWithCurrencyID(String string2, boolean bl) {
        TapjoyLog.i(TAPJOY_OFFERS, "Showing offers with currencyID: " + string2 + ", selector: " + bl + " (userID = " + TapjoyConnectCore.getUserID() + ")");
        this.multipleCurrencyID = string2;
        String string3 = bl ? "1" : "0";
        this.multipleCurrencySelector = string3;
        String string4 = TapjoyConnectCore.getURLParams();
        String string5 = string4 + "&currency_id=" + this.multipleCurrencyID;
        String string6 = string5 + "&currency_selector=" + this.multipleCurrencySelector;
        Intent intent = new Intent(this.context, TJCOffersWebView.class);
        intent.setFlags(268435456);
        intent.putExtra("USER_ID", TapjoyConnectCore.getUserID());
        intent.putExtra("URL_PARAMS", string6);
        this.context.startActivity(intent);
    }

    public void spendTapPoints(int n, TapjoySpendPointsNotifier tapjoySpendPointsNotifier) {
        if (n < 0) {
            TapjoyLog.e(TAPJOY_POINTS, "spendTapPoints error: amount must be a positive number");
            return;
        }
        this.spendTapPoints = "" + n;
        TJCOffers.tapjoySpendPointsNotifier = tapjoySpendPointsNotifier;
        new Thread(new Runnable(){

            public void run() {
                String string2 = TapjoyConnectCore.getURLParams();
                String string3 = string2 + "&tap_points=" + TJCOffers.this.spendTapPoints;
                String string4 = string3 + "&publisher_user_id=" + TapjoyConnectCore.getUserID();
                String string5 = new TapjoyURLConnection().connectToURL("https://ws.tapjoyads.com/points/spend?", string4);
                boolean bl = false;
                if (string5 != null) {
                    bl = TJCOffers.this.handleSpendPointsResponse(string5);
                }
                if (!bl) {
                    tapjoySpendPointsNotifier.getSpendPointsResponseFailed("Failed to spend points.");
                }
            }
        }).start();
    }

}

