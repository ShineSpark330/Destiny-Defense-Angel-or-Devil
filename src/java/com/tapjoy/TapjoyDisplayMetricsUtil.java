/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.util.DisplayMetrics
 *  android.view.Display
 *  android.view.WindowManager
 *  java.lang.Object
 *  java.lang.String
 */
package com.tapjoy;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class TapjoyDisplayMetricsUtil {
    private Configuration configuration;
    private Context context;
    private DisplayMetrics metrics;

    public TapjoyDisplayMetricsUtil(Context context) {
        this.context = context;
        this.metrics = new DisplayMetrics();
        ((WindowManager)this.context.getSystemService("window")).getDefaultDisplay().getMetrics(this.metrics);
        this.configuration = this.context.getResources().getConfiguration();
    }

    public int getScreenDensity() {
        return this.metrics.densityDpi;
    }

    public int getScreenLayoutSize() {
        return 15 & this.configuration.screenLayout;
    }
}

