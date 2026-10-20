/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$Config
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Paint$Align
 *  android.graphics.Paint$FontMetricsInt
 *  android.graphics.Typeface
 *  android.util.Log
 *  java.lang.Exception
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.nio.Buffer
 *  java.nio.ByteBuffer
 *  java.nio.ByteOrder
 *  java.util.Collection
 *  java.util.LinkedList
 */
package org.cocos2dx.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Collection;
import java.util.LinkedList;
import org.cocos2dx.lib.Cocos2dxTypefaces;

public class Cocos2dxBitmap {
    private static final int ALIGNCENTER = 51;
    private static final int ALIGNLEFT = 49;
    private static final int ALIGNRIGHT = 50;
    private static Context context;

    /*
     * Enabled aggressive block sorting
     */
    private static TextProperty computeTextProperty(String string2, Paint paint, int n, int n2) {
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int n3 = (int)Math.ceil((double)(fontMetricsInt.bottom - fontMetricsInt.top));
        int n4 = 0;
        String[] arrstring = Cocos2dxBitmap.splitString(string2, n2, n, paint);
        if (n != 0) {
            n4 = n;
            return new TextProperty(n4, n3, arrstring);
        } else {
            int n5 = arrstring.length;
            for (int i = 0; i < n5; ++i) {
                String string3 = arrstring[i];
                int n6 = (int)Math.ceil((double)paint.measureText(string3, 0, string3.length()));
                if (n6 <= n4) continue;
                n4 = n6;
            }
        }
        return new TextProperty(n4, n3, arrstring);
    }

    private static int computeX(Paint paint, String string2, int n, int n2) {
        switch (n2) {
            default: {
                return 0;
            }
            case 51: {
                return n / 2;
            }
            case 50: 
        }
        return n;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void createTextBitmap(String string2, String string3, int n, int n2, int n3, int n4) {
        String string4 = Cocos2dxBitmap.refactorString(string2);
        Paint paint = Cocos2dxBitmap.newPaint(string3, n, n2);
        TextProperty textProperty = Cocos2dxBitmap.computeTextProperty(string4, paint, n3, n4);
        int n5 = n4 == 0 ? textProperty.totalHeight : n4;
        Bitmap bitmap = Bitmap.createBitmap((int)textProperty.maxWidth, (int)n5, (Bitmap.Config)Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawARGB(0, 255, 255, 255);
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int n6 = n4 == 0 ? -fontMetricsInt.top : -fontMetricsInt.top + (n4 - textProperty.totalHeight) / 2;
        String[] arrstring = textProperty.lines;
        int n7 = arrstring.length;
        int n8 = 0;
        do {
            if (n8 >= n7) {
                Cocos2dxBitmap.initNativeObject(bitmap);
                return;
            }
            String string5 = arrstring[n8];
            canvas.drawText(string5, (float)Cocos2dxBitmap.computeX(paint, string5, textProperty.maxWidth, n2), (float)n6, paint);
            n6 += textProperty.heightPerLine;
            ++n8;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static LinkedList<String> divideStringWithMaxWidth(Paint paint, String string2, int n) {
        int n2 = string2.length();
        int n3 = 0;
        LinkedList linkedList = new LinkedList();
        int n4 = 1;
        do {
            if (n4 > n2) {
                if (n3 < n2) {
                    linkedList.add((Object)string2.substring(n3));
                }
                return linkedList;
            }
            int n5 = (int)Math.ceil((double)paint.measureText(string2, n3, n4));
            if (n5 >= n) {
                int n6 = string2.substring(0, n4).lastIndexOf(" ");
                if (n6 != -1 && n6 > n3) {
                    linkedList.add((Object)string2.substring(n3, n6));
                    n4 = n6;
                } else if (n5 > n) {
                    linkedList.add((Object)string2.substring(n3, n4 - 1));
                } else {
                    linkedList.add((Object)string2.substring(n3, n4));
                }
                n3 = --n4;
            }
            ++n4;
        } while (true);
    }

    private static byte[] getPixels(Bitmap bitmap) {
        if (bitmap != null) {
            byte[] arrby = new byte[4 * (bitmap.getWidth() * bitmap.getHeight())];
            ByteBuffer byteBuffer = ByteBuffer.wrap((byte[])arrby);
            byteBuffer.order(ByteOrder.nativeOrder());
            bitmap.copyPixelsToBuffer((Buffer)byteBuffer);
            return arrby;
        }
        return null;
    }

    private static void initNativeObject(Bitmap bitmap) {
        byte[] arrby = Cocos2dxBitmap.getPixels(bitmap);
        if (arrby == null) {
            return;
        }
        Cocos2dxBitmap.nativeInitBitmapDC(bitmap.getWidth(), bitmap.getHeight(), arrby);
    }

    private static native void nativeInitBitmapDC(int var0, int var1, byte[] var2);

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static Paint newPaint(String string2, int n, int n2) {
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setTextSize((float)n);
        paint.setAntiAlias(true);
        if (string2.endsWith(".ttf")) {
            try {
                paint.setTypeface(Cocos2dxTypefaces.get(context, string2));
            }
            catch (Exception exception) {
                Log.e((String)"Cocos2dxBitmap", (String)("error to create ttf type face: " + string2));
                paint.setTypeface(Typeface.create((String)string2, (int)0));
            }
        } else {
            paint.setTypeface(Typeface.create((String)string2, (int)0));
        }
        switch (n2) {
            default: {
                paint.setTextAlign(Paint.Align.LEFT);
                return paint;
            }
            case 51: {
                paint.setTextAlign(Paint.Align.CENTER);
                return paint;
            }
            case 49: {
                paint.setTextAlign(Paint.Align.LEFT);
                return paint;
            }
            case 50: 
        }
        paint.setTextAlign(Paint.Align.RIGHT);
        return paint;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static String refactorString(String string2) {
        if (string2.compareTo("") == 0) {
            return " ";
        }
        StringBuilder stringBuilder = new StringBuilder(string2);
        int n = 0;
        int n2 = stringBuilder.indexOf("\n");
        do {
            block8 : {
                block7 : {
                    if (n2 == -1) break block7;
                    if (n2 == 0 || stringBuilder.charAt(n2 - 1) == '\n') {
                        stringBuilder.insert(n, " ");
                        n = n2 + 2;
                    } else {
                        n = n2 + 1;
                    }
                    if (n <= stringBuilder.length() && n2 != stringBuilder.length()) break block8;
                }
                return stringBuilder.toString();
            }
            n2 = stringBuilder.indexOf("\n", n);
        } while (true);
    }

    public static void setContext(Context context) {
        Cocos2dxBitmap.context = context;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static String[] splitString(String string2, int n, int n2, Paint paint) {
        String[] arrstring = string2.split("\\n");
        (String[])null;
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int n3 = n / (int)Math.ceil((double)(fontMetricsInt.bottom - fontMetricsInt.top));
        if (n2 == 0) {
            if (n == 0 || arrstring.length <= n3) {
                return arrstring;
            }
        } else {
            LinkedList linkedList = new LinkedList();
            for (String string3 : arrstring) {
                if ((int)Math.ceil((double)paint.measureText(string3)) > n2) {
                    linkedList.addAll(Cocos2dxBitmap.divideStringWithMaxWidth(paint, string3, n2));
                } else {
                    linkedList.add((Object)string3);
                }
                if (n3 > 0 && linkedList.size() >= n3) break;
            }
            if (n3 > 0 && linkedList.size() > n3) {
                while (linkedList.size() > n3) {
                    linkedList.removeLast();
                }
            }
            Object[] arrobject = new String[linkedList.size()];
            linkedList.toArray(arrobject);
            return arrobject;
        }
        LinkedList linkedList = new LinkedList();
        int n4 = 0;
        do {
            if (n4 >= n3) {
                Object[] arrobject = new String[linkedList.size()];
                linkedList.toArray(arrobject);
                return arrobject;
            }
            linkedList.add((Object)arrstring[n4]);
            ++n4;
        } while (true);
    }

    private static class TextProperty {
        int heightPerLine;
        String[] lines;
        int maxWidth;
        int totalHeight;

        TextProperty(int n, int n2, String[] arrstring) {
            this.maxWidth = n;
            this.heightPerLine = n2;
            this.totalHeight = n2 * arrstring.length;
            this.lines = arrstring;
        }
    }

}

