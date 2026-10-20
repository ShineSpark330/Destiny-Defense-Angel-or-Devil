/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 */
package com.tapjoy;

import android.content.Context;
import android.util.Log;
import com.tapjoy.TJCOffers;
import com.tapjoy.TapjoyAwardPointsNotifier;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyDisplayAd;
import com.tapjoy.TapjoyDisplayAdNotifier;
import com.tapjoy.TapjoyEarnedPointsNotifier;
import com.tapjoy.TapjoyFeaturedApp;
import com.tapjoy.TapjoyFeaturedAppNotifier;
import com.tapjoy.TapjoyNotifier;
import com.tapjoy.TapjoySpendPointsNotifier;
import com.tapjoy.TapjoyVideo;
import com.tapjoy.TapjoyVideoNotifier;

public final class TapjoyConnect {
    public static final String TAPJOY_CONNECT = "TapjoyConnect";
    private static TapjoyConnect tapjoyConnectInstance = null;
    private static TapjoyDisplayAd tapjoyDisplayAd;
    private static TapjoyFeaturedApp tapjoyFeaturedApp;
    private static TJCOffers tapjoyOffers;
    private static TapjoyVideo tapjoyVideo;

    static {
        tapjoyOffers = null;
        tapjoyFeaturedApp = null;
        tapjoyDisplayAd = null;
        tapjoyVideo = null;
    }

    private TapjoyConnect(Context context, String string2, String string3) {
        TapjoyConnectCore.requestTapjoyConnect(context, string2, string3);
    }

    public static TapjoyConnect getTapjoyConnectInstance() {
        if (tapjoyConnectInstance == null) {
            Log.e((String)TAPJOY_CONNECT, (String)"----------------------------------------");
            Log.e((String)TAPJOY_CONNECT, (String)"ERROR -- call requestTapjoyConnect before any other Tapjoy methods");
            Log.e((String)TAPJOY_CONNECT, (String)"----------------------------------------");
        }
        return tapjoyConnectInstance;
    }

    public static void requestTapjoyConnect(Context context, String string2, String string3) {
        TapjoyConnectCore.setSDKType("offers");
        TapjoyConnectCore.setPlugin("native");
        tapjoyConnectInstance = new TapjoyConnect(context, string2, string3);
        tapjoyOffers = new TJCOffers(context);
        tapjoyFeaturedApp = new TapjoyFeaturedApp(context);
        tapjoyDisplayAd = new TapjoyDisplayAd(context);
        tapjoyVideo = new TapjoyVideo(context);
    }

    public void actionComplete(String string2) {
        TapjoyConnectCore.getInstance().actionComplete(string2);
    }

    public void awardTapPoints(int n, TapjoyAwardPointsNotifier tapjoyAwardPointsNotifier) {
        tapjoyOffers.awardTapPoints(n, tapjoyAwardPointsNotifier);
    }

    public void enableBannerAdAutoRefresh(boolean bl) {
        tapjoyDisplayAd.enableAutoRefresh(bl);
    }

    public void enablePaidAppWithActionID(String string2) {
        TapjoyConnectCore.getInstance().enablePaidAppWithActionID(string2);
    }

    public void enableVideoCache(boolean bl) {
        tapjoyVideo.enableVideoCache(bl);
    }

    public String getAppID() {
        return TapjoyConnectCore.getAppID();
    }

    public float getCurrencyMultiplier() {
        return TapjoyConnectCore.getInstance().getCurrencyMultiplier();
    }

    public void getDisplayAd(TapjoyDisplayAdNotifier tapjoyDisplayAdNotifier) {
        tapjoyDisplayAd.getDisplayAd(tapjoyDisplayAdNotifier);
    }

    public void getDisplayAdWithCurrencyID(String string2, TapjoyDisplayAdNotifier tapjoyDisplayAdNotifier) {
        tapjoyDisplayAd.getDisplayAd(string2, tapjoyDisplayAdNotifier);
    }

    public void getFeaturedApp(TapjoyFeaturedAppNotifier tapjoyFeaturedAppNotifier) {
        tapjoyFeaturedApp.getFeaturedApp(tapjoyFeaturedAppNotifier);
    }

    public void getFeaturedAppWithCurrencyID(String string2, TapjoyFeaturedAppNotifier tapjoyFeaturedAppNotifier) {
        tapjoyFeaturedApp.getFeaturedApp(string2, tapjoyFeaturedAppNotifier);
    }

    public void getTapPoints(TapjoyNotifier tapjoyNotifier) {
        tapjoyOffers.getTapPoints(tapjoyNotifier);
    }

    public String getUserID() {
        return TapjoyConnectCore.getUserID();
    }

    public void initVideoAd(TapjoyVideoNotifier tapjoyVideoNotifier) {
        tapjoyVideo.initVideoAd(tapjoyVideoNotifier);
    }

    public void setBannerAdSize(String string2) {
        tapjoyDisplayAd.setBannerAdSize(string2);
    }

    public void setCurrencyMultiplier(float f) {
        TapjoyConnectCore.getInstance().setCurrencyMultiplier(f);
    }

    public void setEarnedPointsNotifier(TapjoyEarnedPointsNotifier tapjoyEarnedPointsNotifier) {
        tapjoyOffers.setEarnedPointsNotifier(tapjoyEarnedPointsNotifier);
    }

    public void setFeaturedAppDisplayCount(int n) {
        tapjoyFeaturedApp.setDisplayCount(n);
    }

    public void setUserID(String string2) {
        TapjoyConnectCore.setUserID(string2);
    }

    public void setVideoCacheCount(int n) {
        tapjoyVideo.setVideoCacheCount(n);
    }

    public void showFeaturedAppFullScreenAd() {
        tapjoyFeaturedApp.showFeaturedAppFullScreenAd();
    }

    public void showOffers() {
        tapjoyOffers.showOffers();
    }

    public void showOffersWithCurrencyID(String string2, boolean bl) {
        tapjoyOffers.showOffersWithCurrencyID(string2, bl);
    }

    public void spendTapPoints(int n, TapjoySpendPointsNotifier tapjoySpendPointsNotifier) {
        tapjoyOffers.spendTapPoints(n, tapjoySpendPointsNotifier);
    }
}

