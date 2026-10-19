/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.morisoft.NLib;

class TextFiledOption {
    public int bufferSize;
    public String charsetName;
    public int filterType;
    public int imeOption;
    public int inputType;
    public int maxLine;
    public String text;

    public TextFiledOption(String string2, int n, int n2, int n3, int n4, int n5, String string3) {
        if (string2 == null) {
            string2 = "";
        }
        this.text = string2;
        this.maxLine = n2;
        this.filterType = n3;
        this.imeOption = n4;
        this.inputType = n5;
        this.bufferSize = n;
        this.charsetName = string3;
    }
}

