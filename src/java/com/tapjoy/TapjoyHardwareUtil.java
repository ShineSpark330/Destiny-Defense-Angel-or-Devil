/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  java.lang.Object
 *  java.lang.String
 */
package com.tapjoy;

import android.os.Build;

public class TapjoyHardwareUtil {
    public String getSerial() {
        return Build.SERIAL;
    }
}

