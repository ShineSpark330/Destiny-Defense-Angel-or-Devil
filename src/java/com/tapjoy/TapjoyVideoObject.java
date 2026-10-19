/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Array
 */
package com.tapjoy;

import java.lang.reflect.Array;

public class TapjoyVideoObject {
    public static final int BUTTON_MAX = 10;
    public int buttonCount;
    public String[][] buttonData = (String[][])Array.newInstance(String.class, (int[])new int[]{10, 2});
    public String clickURL;
    public String currencyAmount;
    public String currencyName;
    public String dataLocation;
    public String iconURL;
    public String offerID;
    public String videoAdName;
    public String videoURL;
    public String webviewURL;

    public void addButton(String string2, String string3) {
        this.buttonData[this.buttonCount][0] = string2;
        this.buttonData[this.buttonCount][1] = string3;
        this.buttonCount = 1 + this.buttonCount;
    }
}

