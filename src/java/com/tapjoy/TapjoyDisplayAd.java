/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  java.io.ByteArrayInputStream
 *  java.io.InputStream
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.Timer
 *  java.util.TimerTask
 *  javax.xml.parsers.DocumentBuilder
 *  javax.xml.parsers.DocumentBuilderFactory
 *  org.w3c.dom.Document
 *  org.w3c.dom.NodeList
 */
package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import com.tapjoy.TJCOffersWebView;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyDisplayAdNotifier;
import com.tapjoy.TapjoyDisplayAdSize;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyURLConnection;
import com.tapjoy.TapjoyUtil;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class TapjoyDisplayAd {
    private static final byte[] DECODE_TABLE;
    private static final int MASK_8BITS = 255;
    private static final byte PAD = 61;
    private static String adClickURL;
    private static Bitmap bitmapImage;
    private static TapjoyDisplayAdNotifier displayAdNotifier;
    private static String displayAdSize;
    public static String displayAdURLParams;
    private static TapjoyURLConnection tapjoyURLConnection;
    final String TAPJOY_DISPLAY_AD = "Banner Ad";
    View adView;
    private boolean autoRefresh;
    private byte[] buffer;
    private Context context;
    long elapsed_time;
    private boolean eof;
    private int modulus;
    private int pos;
    Timer resumeTimer;
    Timer timer;
    private int x;

    static {
        tapjoyURLConnection = null;
        DECODE_TABLE = new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};
    }

    public TapjoyDisplayAd(Context context) {
        displayAdSize = TapjoyDisplayAdSize.TJC_AD_BANNERSIZE_320X50;
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
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(string2.getBytes("UTF-8"));
            Document document = documentBuilderFactory.newDocumentBuilder().parse((InputStream)byteArrayInputStream);
            adClickURL = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("ClickURL"));
            String string3 = TapjoyUtil.getNodeTrimValue(document.getElementsByTagName("Image"));
            TapjoyLog.i("Banner Ad", "decoding...");
            this.decodeBase64(string3.getBytes(), 0, string3.getBytes().length);
            TapjoyLog.i("Banner Ad", "pos: " + this.pos);
            TapjoyLog.i("Banner Ad", "buffer_size: " + this.buffer.length);
            bitmapImage = BitmapFactory.decodeByteArray((byte[])this.buffer, (int)0, (int)this.pos);
            TapjoyLog.i("Banner Ad", "image: " + bitmapImage.getWidth() + "x" + bitmapImage.getHeight());
            this.adView = new View(this.context);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(bitmapImage.getWidth(), bitmapImage.getHeight());
            this.adView.setLayoutParams(layoutParams);
            this.adView.setBackgroundDrawable((Drawable)new BitmapDrawable(bitmapImage));
            this.adView.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    TapjoyLog.i("Banner Ad", "Opening URL in new browser = [" + adClickURL + "]");
                    Intent intent = new Intent(TapjoyDisplayAd.this.context, TJCOffersWebView.class);
                    intent.putExtra("DISPLAY_AD_URL", adClickURL);
                    intent.setFlags(268435456);
                    TapjoyDisplayAd.this.context.startActivity(intent);
                    if (TapjoyDisplayAd.this.resumeTimer != null) {
                        TapjoyDisplayAd.this.resumeTimer.cancel();
                    }
                    TapjoyDisplayAd.this.elapsed_time = 0L;
                    TapjoyDisplayAd.this.resumeTimer = new Timer();
                    TapjoyDisplayAd.this.resumeTimer.schedule((TimerTask)new CheckForResumeTimer(), 10000L, 10000L);
                }
            });
            TapjoyLog.i("Banner Ad", "notify displayAdNotifier");
            displayAdNotifier.getDisplayAdResponse(this.adView);
            bl = true;
        }
        catch (Exception exception) {
            TapjoyLog.e("Banner Ad", "Error parsing XML: " + exception.toString());
            bl = false;
        }
        if (this.timer != null) {
            this.timer.cancel();
            this.timer = null;
        }
        if (this.autoRefresh && this.timer == null) {
            TapjoyLog.i("Banner Ad", "will refresh banner ad in 15s...");
            this.timer = new Timer();
            this.timer.schedule((TimerTask)new RefreshTimer(), 15000L);
        }
        TapjoyLog.i("Banner Ad", "return: " + bl);
        return bl;
    }

    public static Bitmap getBitmapImage() {
        return bitmapImage;
    }

    public static String getLinkURL() {
        return adClickURL;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    void decodeBase64(byte[] arrby, int n, int n2) {
        this.buffer = new byte[arrby.length];
        this.pos = 0;
        this.eof = false;
        this.modulus = 0;
        if (n2 < 0) {
            this.eof = true;
        }
        int n3 = n;
        for (int i = 0; i < n2; ++i) {
            byte by;
            int n4 = n3 + 1;
            byte by2 = arrby[n3];
            if (by2 == 61) {
                this.eof = true;
                break;
            }
            if (by2 >= 0 && by2 < DECODE_TABLE.length && (by = DECODE_TABLE[by2]) >= 0) {
                int n5;
                this.modulus = n5 = 1 + this.modulus;
                this.modulus = n5 % 4;
                this.x = by + (this.x << 6);
                if (this.modulus == 0) {
                    byte[] arrby2 = this.buffer;
                    int n6 = this.pos;
                    this.pos = n6 + 1;
                    arrby2[n6] = (byte)(255 & this.x >> 16);
                    byte[] arrby3 = this.buffer;
                    int n7 = this.pos;
                    this.pos = n7 + 1;
                    arrby3[n7] = (byte)(255 & this.x >> 8);
                    byte[] arrby4 = this.buffer;
                    int n8 = this.pos;
                    this.pos = n8 + 1;
                    arrby4[n8] = (byte)(255 & this.x);
                }
            }
            n3 = n4;
        }
        if (!this.eof) return;
        if (this.modulus == 0) return;
        this.x <<= 6;
        switch (this.modulus) {
            default: {
                return;
            }
            case 2: {
                this.x <<= 6;
                byte[] arrby5 = this.buffer;
                int n9 = this.pos;
                this.pos = n9 + 1;
                arrby5[n9] = (byte)(255 & this.x >> 16);
                return;
            }
            case 3: 
        }
        byte[] arrby6 = this.buffer;
        int n10 = this.pos;
        this.pos = n10 + 1;
        arrby6[n10] = (byte)(255 & this.x >> 16);
        byte[] arrby7 = this.buffer;
        int n11 = this.pos;
        this.pos = n11 + 1;
        arrby7[n11] = (byte)(255 & this.x >> 8);
    }

    public void enableAutoRefresh(boolean bl) {
        this.autoRefresh = bl;
    }

    public String getBannerAdSize() {
        return displayAdSize;
    }

    public void getDisplayAd(TapjoyDisplayAdNotifier tapjoyDisplayAdNotifier) {
        TapjoyLog.i("Banner Ad", "Get Banner Ad");
        this.getDisplayAd(null, tapjoyDisplayAdNotifier);
    }

    public void getDisplayAd(String string2, TapjoyDisplayAdNotifier tapjoyDisplayAdNotifier) {
        TapjoyLog.i("Banner Ad", "Get Banner Ad, currencyID: " + string2);
        displayAdNotifier = tapjoyDisplayAdNotifier;
        displayAdURLParams = TapjoyConnectCore.getURLParams();
        displayAdURLParams = displayAdURLParams + "&publisher_user_id=" + TapjoyConnectCore.getUserID();
        displayAdURLParams = displayAdURLParams + "&size=" + displayAdSize;
        if (string2 != null) {
            displayAdURLParams = displayAdURLParams + "&currency_id=" + string2;
        }
        new Thread(new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            public void run() {
                String string2 = tapjoyURLConnection.connectToURL("https://ws.tapjoyads.com/display_ad?", TapjoyDisplayAd.displayAdURLParams);
                if (string2 == null || string2.length() == 0) {
                    displayAdNotifier.getDisplayAdResponseFailed("Network error.");
                    return;
                } else {
                    if (TapjoyDisplayAd.this.buildResponse(string2)) return;
                    {
                        displayAdNotifier.getDisplayAdResponseFailed("No ad to display.");
                        return;
                    }
                }
            }
        }).start();
    }

    public void setBannerAdSize(String string2) {
        displayAdSize = string2;
    }

    private class CheckForResumeTimer
    extends TimerTask {
        private CheckForResumeTimer() {
        }

        /*
         * Enabled aggressive block sorting
         */
        public void run() {
            TapjoyDisplayAd tapjoyDisplayAd = TapjoyDisplayAd.this;
            tapjoyDisplayAd.elapsed_time = 10000L + tapjoyDisplayAd.elapsed_time;
            TapjoyLog.i("Banner Ad", "banner elapsed_time: " + TapjoyDisplayAd.this.elapsed_time + " (" + TapjoyDisplayAd.this.elapsed_time / 1000L / 60L + "m " + TapjoyDisplayAd.this.elapsed_time / 1000L % 60L + "s)");
            if (TapjoyDisplayAd.this.adView == null) {
                this.cancel();
                return;
            } else {
                TapjoyLog.i("Banner Ad", "adView.isShown: " + TapjoyDisplayAd.this.adView.isShown());
                if (TapjoyDisplayAd.this.adView.isShown() && TapjoyConnectCore.getInstance() != null) {
                    TapjoyLog.i("Banner Ad", "call connect");
                    TapjoyConnectCore.getInstance().callConnect();
                    this.cancel();
                }
                if (TapjoyDisplayAd.this.elapsed_time < 1200000L) return;
                {
                    this.cancel();
                    return;
                }
            }
        }
    }

    private class RefreshTimer
    extends TimerTask {
        private RefreshTimer() {
        }

        public void run() {
            TapjoyLog.i("Banner Ad", "refreshing banner ad...");
            TapjoyDisplayAd.this.getDisplayAd(displayAdNotifier);
            TapjoyDisplayAd.this.timer.cancel();
            TapjoyDisplayAd.this.timer = null;
        }
    }

}

