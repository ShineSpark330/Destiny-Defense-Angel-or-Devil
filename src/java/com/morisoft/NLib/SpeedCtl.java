/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.morisoft.NLib;

import com.morisoft.NLib.Native;
import com.morisoft.NLib.NativeCall;

public class SpeedCtl {
    public static float acc;
    public static float boost;
    public static int idx;
    public static float slow;

    static {
        idx = 1;
        boost = 1.0f;
        slow = 1.0f;
        acc = 0.0f;
    }

    public static long interval() {
        long l = NativeCall.animationInterval;
        float f = slow;
        if (f != 1.0f) {
            l = (long)((double)l * (double)f);
        }
        return l;
    }

    public static String next() {
        String string2;
        float f;
        int n = 1 + idx;
        if (n >= 4) {
            n = 0;
        }
        idx = n;
        if (n == 0) {
            f = 0.75f;
            string2 = "0.75x";
        } else if (n == 1) {
            f = 1.0f;
            string2 = "1x";
        } else if (n == 2) {
            f = 3.0f;
            string2 = "3x";
        } else {
            f = 5.0f;
            string2 = "5x";
        }
        if (f < 1.0f) {
            boost = 1.0f;
            slow = 1.0f / f;
            return string2;
        }
        boost = f;
        slow = 1.0f;
        return string2;
    }

    public static void renderTick() {
        int n;
        float f = acc + boost;
        acc = f - (float)n;
        for (n = (int)f; n > 0; --n) {
            Native.render();
        }
    }
}

