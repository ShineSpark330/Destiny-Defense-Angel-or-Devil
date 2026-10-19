/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.view.View
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 */
package com.morisoft.NLib;

import android.os.Handler;
import android.view.View;
import com.tapjoy.TapjoyAwardPointsNotifier;
import com.tapjoy.TapjoyDisplayAdNotifier;
import com.tapjoy.TapjoyEarnedPointsNotifier;
import com.tapjoy.TapjoyFeaturedAppNotifier;
import com.tapjoy.TapjoyFeaturedAppObject;
import com.tapjoy.TapjoyNotifier;
import com.tapjoy.TapjoySpendPointsNotifier;
import com.tapjoy.TapjoyVideoNotifier;
import org.cocos2dx.lib.Cocos2dxActivity;

public class TapJoy
implements TapjoyAwardPointsNotifier,
TapjoyDisplayAdNotifier,
TapjoyEarnedPointsNotifier,
TapjoyFeaturedAppNotifier,
TapjoyNotifier,
TapjoySpendPointsNotifier,
TapjoyVideoNotifier {
    private static Cocos2dxActivity m_activyty;
    private static TapJoy m_instance;

    static {
        m_instance = new TapJoy();
        m_activyty = null;
    }

    public static void awardPoints(int n) {
    }

    public static native void awardPointsResult(int var0, int var1);

    public static void getPoints() {
    }

    public static native void getPointsResult(int var0, int var1);

    public static void initialize(String string2, String string3) {
    }

    public static void showFullScreenAd() {
    }

    public static void showMarketPlace() {
    }

    public static void spendPoints(int n) {
    }

    public static native void spendPointsResult(int var0, int var1);

    @Override
    public void earnedTapPoints(int n) {
    }

    @Override
    public void getAwardPointsResponse(String string2, int n) {
        TapJoy.awardPointsResult(1, n);
    }

    @Override
    public void getAwardPointsResponseFailed(final String string2) {
        TapJoy.getPointsResult(-1, 0);
        TapJoy.m_activyty.m_Handler.post(new Runnable(){

            public void run() {
                m_activyty.createToast(string2, false);
            }
        });
    }

    @Override
    public void getDisplayAdResponse(View view) {
    }

    @Override
    public void getDisplayAdResponseFailed(String string2) {
    }

    @Override
    public void getFeaturedAppResponse(TapjoyFeaturedAppObject tapjoyFeaturedAppObject) {
    }

    @Override
    public void getFeaturedAppResponseFailed(String string2) {
    }

    @Override
    public void getSpendPointsResponse(String string2, int n) {
        TapJoy.spendPointsResult(1, n);
    }

    @Override
    public void getSpendPointsResponseFailed(final String string2) {
        TapJoy.getPointsResult(-1, 0);
        TapJoy.m_activyty.m_Handler.post(new Runnable(){

            public void run() {
                m_activyty.createToast(string2, false);
            }
        });
    }

    @Override
    public void getUpdatePoints(String string2, int n) {
        TapJoy.getPointsResult(1, n);
    }

    @Override
    public void getUpdatePointsFailed(final String string2) {
        TapJoy.getPointsResult(-1, 0);
        TapJoy.m_activyty.m_Handler.post(new Runnable(){

            public void run() {
                m_activyty.createToast(string2, false);
            }
        });
    }

    @Override
    public void videoComplete() {
    }

    @Override
    public void videoError(int n) {
    }

    @Override
    public void videoReady() {
    }

}

