/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.os.Environment
 *  android.util.Log
 *  java.io.BufferedInputStream
 *  java.io.ByteArrayInputStream
 *  java.io.File
 *  java.io.FileOutputStream
 *  java.io.InputStream
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.net.SocketTimeoutException
 *  java.net.URL
 *  java.net.URLConnection
 *  java.util.Enumeration
 *  java.util.Hashtable
 *  java.util.Set
 *  java.util.Vector
 *  javax.xml.parsers.DocumentBuilder
 *  javax.xml.parsers.DocumentBuilderFactory
 *  org.w3c.dom.Document
 *  org.w3c.dom.Element
 *  org.w3c.dom.Node
 *  org.w3c.dom.NodeList
 */
package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyURLConnection;
import com.tapjoy.TapjoyUtil;
import com.tapjoy.TapjoyVideoNotifier;
import com.tapjoy.TapjoyVideoObject;
import com.tapjoy.TapjoyVideoView;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TapjoyVideo {
    private static final String BACKGROUND_WATERMARK_NAME = "watermark";
    public static final String TAPJOY_VIDEO = "TapjoyVideo";
    public static String imageBackgroundLocation;
    public static String imagePlaceholderLocation;
    public static String imageTapjoyLocation;
    private static final String imageTapjoyUrl = "https://s3.amazonaws.com/tapjoy/videos/assets/watermark.png";
    private static TapjoyVideo tapjoyVideo;
    private static TapjoyVideoNotifier tapjoyVideoNotifier;
    public static Bitmap watermark;
    private Hashtable<String, TapjoyVideoObject> cachedVideos;
    Context context;
    private boolean enableVideoCache = true;
    private String imageCacheDir = null;
    private Vector<String> imageQueue;
    private Hashtable<String, String> uncachedImages;
    private Hashtable<String, TapjoyVideoObject> uncachedVideos;
    private String videoCacheDir = null;
    private int videoCacheLimit = 5;
    private Vector<String> videoQueue;
    public TapjoyVideoObject videoToPlay;

    static {
        tapjoyVideo = null;
    }

    public TapjoyVideo(Context context) {
        this.context = context;
        tapjoyVideo = this;
    }

    private void cacheImage(final String string2) {
        TapjoyLog.i(TAPJOY_VIDEO, "cacheImage: " + string2);
        new Thread(new Runnable(){

            /*
             * Unable to fully structure code
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Lifted jumps to return sites
             */
            public void run() {
                block23 : {
                    var1_1 = System.currentTimeMillis();
                    var3_2 = null;
                    var4_3 = null;
                    var10_4 = new URL(string2).openConnection();
                    var10_4.setConnectTimeout(15000);
                    var10_4.setReadTimeout(30000);
                    var10_4.connect();
                    var11_5 = new BufferedInputStream(var10_4.getInputStream());
                    var12_6 = new File(TapjoyVideo.access$800(TapjoyVideo.this));
                    var13_7 = string2.substring(0, 1 + string2.lastIndexOf("/"));
                    var14_8 = string2.substring(1 + string2.lastIndexOf("/"));
                    var15_9 = var14_8.substring(0, var14_8.indexOf(46));
                    TapjoyLog.i("TapjoyVideo", "fileDir: " + (Object)var12_6);
                    TapjoyLog.i("TapjoyVideo", "path: " + var13_7);
                    TapjoyLog.i("TapjoyVideo", "file name: " + var15_9);
                    if (var12_6.mkdirs()) {
                        TapjoyLog.i("TapjoyVideo", "created directory at: " + var12_6.getPath());
                    }
                    var16_10 = new File(TapjoyVideo.access$800(TapjoyVideo.this), var15_9);
                    var17_11 = new FileOutputStream(var16_10);
                    try {
                        TapjoyLog.i("TapjoyVideo", "downloading image file to: " + var16_10.toString());
                        var18_12 = new byte[1024];
                        while ((var19_13 = var11_5.read(var18_12)) != -1) {
                            var17_11.write(var18_12, 0, var19_13);
                        }
                        var17_11.close();
                        var11_5.close();
                        TapjoyLog.i("TapjoyVideo", "FILE SIZE: " + var16_10.length());
                        var20_21 = var16_10.length();
                        ** GOTO lbl38
                    }
                    catch (SocketTimeoutException var5_14) {
                        block20 : {
                            block21 : {
                                var4_3 = var17_11;
                                var3_2 = var11_5;
                                break block21;
lbl38: // 1 sources:
                                var22_22 = var20_21 LCMP 0L;
                                var6_19 = false;
                                if (var22_22 == false) {
                                    var6_19 = true;
                                }
                                var4_3 = var17_11;
                                var3_2 = var11_5;
                                var7_20 = false;
                                break block20;
                                catch (Exception var9_23) {}
                                ** GOTO lbl-1000
                                catch (Exception var9_25) {
                                    var3_2 = var11_5;
                                    var4_3 = null;
                                    ** GOTO lbl-1000
                                }
                                catch (Exception var9_26) {
                                    var3_2 = var11_5;
                                    var4_3 = null;
                                    ** GOTO lbl-1000
                                }
                                catch (Exception var9_27) {
                                    var4_3 = var17_11;
                                    var3_2 = var11_5;
                                }
lbl-1000: // 4 sources:
                                {
                                    TapjoyLog.e("TapjoyVideo", "Error caching image file: " + var9_24.toString());
                                    var7_20 = true;
                                    var6_19 = false;
                                    break block20;
                                }
                                catch (SocketTimeoutException var5_16) {
                                    var3_2 = null;
                                    var4_3 = null;
                                    break block21;
                                }
                                catch (SocketTimeoutException var5_17) {
                                    var3_2 = var11_5;
                                    var4_3 = null;
                                    break block21;
                                }
                                catch (SocketTimeoutException var5_18) {
                                    var3_2 = var11_5;
                                    var4_3 = null;
                                }
                            }
                            TapjoyLog.e("TapjoyVideo", "Network timeout: " + var5_15.toString());
                            var6_19 = true;
                            var7_20 = true;
                        }
                        if (var6_19) {
                            TapjoyLog.i("TapjoyVideo", "Network timeout");
                            try {
                                var3_2.close();
                                var4_3.close();
                            }
                            catch (Exception var8_28) {}
                        }
                        if (var6_19 || var7_20) break block23;
                        TapjoyVideo.access$1000(TapjoyVideo.this).removeElementAt(0);
                        if (TapjoyVideo.access$1000(TapjoyVideo.this).size() > 0) {
                            TapjoyVideo.access$1100(TapjoyVideo.this, (String)TapjoyVideo.access$1000(TapjoyVideo.this).elementAt(0));
                        }
                        TapjoyLog.i("TapjoyVideo", "image cached in: " + (System.currentTimeMillis() - var1_1) + "ms");
                        return;
                    }
                }
                if (TapjoyVideo.access$1000(TapjoyVideo.this).size() <= 0) return;
                TapjoyVideo.access$1100(TapjoyVideo.this, (String)TapjoyVideo.access$1000(TapjoyVideo.this).elementAt(0));
            }
        }).start();
    }

    private void cacheVideo(final String string2) {
        TapjoyLog.i(TAPJOY_VIDEO, "download and cache video from: " + string2);
        new Thread(new Runnable(){

            /*
             * Unable to fully structure code
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Lifted jumps to return sites
             */
            public void run() {
                block22 : {
                    var1_1 = System.currentTimeMillis();
                    var3_2 = null;
                    var4_3 = null;
                    var5_4 = null;
                    var18_5 = new URL(string2).openConnection();
                    var18_5.setConnectTimeout(15000);
                    var18_5.setReadTimeout(30000);
                    var18_5.connect();
                    var19_6 = new BufferedInputStream(var18_5.getInputStream());
                    var20_7 = new File(TapjoyVideo.access$300(TapjoyVideo.this));
                    var21_8 = string2.substring(0, 1 + string2.lastIndexOf("/"));
                    var22_9 = string2.substring(1 + string2.lastIndexOf("/"));
                    var23_10 = var22_9.substring(0, var22_9.indexOf(46));
                    TapjoyLog.i("TapjoyVideo", "fileDir: " + (Object)var20_7);
                    TapjoyLog.i("TapjoyVideo", "path: " + var21_8);
                    TapjoyLog.i("TapjoyVideo", "file name: " + var23_10);
                    if (var20_7.mkdirs()) {
                        TapjoyLog.i("TapjoyVideo", "created directory at: " + var20_7.getPath());
                    }
                    var24_11 = new File(TapjoyVideo.access$300(TapjoyVideo.this), var23_10);
                    var25_12 = new FileOutputStream(var24_11);
                    try {
                        TapjoyLog.i("TapjoyVideo", "downloading video file to: " + var24_11.toString());
                        var26_13 = new byte[1024];
                        while ((var27_14 = var19_6.read(var26_13)) != -1) {
                            var25_12.write(var26_13, 0, var27_14);
                        }
                        var25_12.close();
                        var19_6.close();
                        TapjoyLog.i("TapjoyVideo", "FILE SIZE: " + var24_11.length());
                        var28_25 = var24_11.length();
                        ** GOTO lbl40
                    }
                    catch (SocketTimeoutException var6_15) {
                        block19 : {
                            block20 : {
                                var5_4 = var24_11;
                                var4_3 = var25_12;
                                var3_2 = var19_6;
                                break block20;
lbl40: // 1 sources:
                                var30_26 = var28_25 LCMP 0L;
                                var7_20 = false;
                                if (var30_26 == false) {
                                    var7_20 = true;
                                }
                                var5_4 = var24_11;
                                var4_3 = var25_12;
                                var3_2 = var19_6;
                                var8_21 = false;
                                break block19;
                                catch (Exception var17_27) {}
                                ** GOTO lbl-1000
                                catch (Exception var17_29) {
                                    var3_2 = var19_6;
                                    var4_3 = null;
                                    var5_4 = null;
                                    ** GOTO lbl-1000
                                }
                                catch (Exception var17_30) {
                                    var5_4 = var24_11;
                                    var3_2 = var19_6;
                                    var4_3 = null;
                                    ** GOTO lbl-1000
                                }
                                catch (Exception var17_31) {
                                    var5_4 = var24_11;
                                    var4_3 = var25_12;
                                    var3_2 = var19_6;
                                }
lbl-1000: // 4 sources:
                                {
                                    TapjoyLog.e("TapjoyVideo", "Error caching video file: " + var17_28.toString());
                                    var8_21 = true;
                                    var7_20 = false;
                                    break block19;
                                }
                                catch (SocketTimeoutException var6_17) {
                                    var3_2 = null;
                                    var4_3 = null;
                                    var5_4 = null;
                                    break block20;
                                }
                                catch (SocketTimeoutException var6_18) {
                                    var3_2 = var19_6;
                                    var4_3 = null;
                                    var5_4 = null;
                                    break block20;
                                }
                                catch (SocketTimeoutException var6_19) {
                                    var5_4 = var24_11;
                                    var3_2 = var19_6;
                                    var4_3 = null;
                                }
                            }
                            TapjoyLog.e("TapjoyVideo", "Network timeout: " + var6_16.toString());
                            var7_20 = true;
                            var8_21 = true;
                        }
                        if (var7_20) {
                            TapjoyLog.i("TapjoyVideo", "Network timeout");
                            try {
                                var3_2.close();
                                var4_3.close();
                            }
                            catch (Exception var16_32) {}
                        }
                        if (var7_20 || var8_21) break block22;
                        var9_22 = (String)TapjoyVideo.access$600(TapjoyVideo.this).elementAt(0);
                        var10_23 = (TapjoyVideoObject)TapjoyVideo.access$400(TapjoyVideo.this).get((Object)var9_22);
                        var10_23.dataLocation = var5_4.getAbsolutePath();
                        var11_24 = TapjoyVideo.this.context.getSharedPreferences("TapjoyVideoPrefs", 0).edit();
                        var11_24.putString(var9_22, var9_22);
                        var11_24.commit();
                        TapjoyVideo.access$500(TapjoyVideo.this).put((Object)var9_22, (Object)var10_23);
                        TapjoyVideo.access$400(TapjoyVideo.this).remove((Object)var9_22);
                        TapjoyVideo.access$600(TapjoyVideo.this).removeElementAt(0);
                        TapjoyVideo.access$700(TapjoyVideo.this);
                        TapjoyVideo.this.loadNextVideo();
                        TapjoyLog.i("TapjoyVideo", "video cached in: " + (System.currentTimeMillis() - var1_1) + "ms");
                        TapjoyVideo.access$200().videoReady();
                        return;
                    }
                }
                TapjoyVideo.access$200().videoError(2);
            }
        }).start();
    }

    public static TapjoyVideo getInstance() {
        return tapjoyVideo;
    }

    public static TapjoyVideoNotifier getVideoNotifier() {
        return tapjoyVideoNotifier;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private boolean handleGetVideosResponse(String var1_1) {
        block22 : {
            var2_2 = DocumentBuilderFactory.newInstance();
            TapjoyLog.i("TapjoyVideo", "========================================");
            try {
                var3_3 = new ByteArrayInputStream(var1_1.getBytes("UTF-8"));
                var5_4 = var2_2.newDocumentBuilder().parse((InputStream)var3_3);
                var5_4.getDocumentElement().normalize();
                var6_5 = var5_4.getElementsByTagName("TapjoyVideos");
                var7_6 = var6_5.item(0).getChildNodes();
                TapjoyLog.i("TapjoyVideo", "nodelistParent length: " + var6_5.getLength());
                TapjoyLog.i("TapjoyVideo", "nodelist length: " + var7_6.getLength());
                var8_7 = 0;
lbl12: // 2 sources:
                do {
                    if (var8_7 < var7_6.getLength()) {
                        var9_8 = var7_6.item(var8_7);
                        var10_9 = new TapjoyVideoObject();
                        if (var9_8 == null || var9_8.getNodeType() != 1) break;
                        var11_10 = (Element)var9_8;
                        var12_11 = TapjoyUtil.getNodeTrimValue(var11_10.getElementsByTagName("ClickURL"));
                        if (var12_11 != null && !var12_11.equals((Object)"")) {
                            var10_9.clickURL = var12_11;
                        }
                        if ((var13_12 = TapjoyUtil.getNodeTrimValue(var11_10.getElementsByTagName("OfferID"))) != null && !var13_12.equals((Object)"")) {
                            var10_9.offerID = var13_12;
                        }
                        if ((var14_13 = TapjoyUtil.getNodeTrimValue(var11_10.getElementsByTagName("Name"))) != null && !var14_13.equals((Object)"")) {
                            var10_9.videoAdName = var14_13;
                        }
                        if ((var15_14 = TapjoyUtil.getNodeTrimValue(var11_10.getElementsByTagName("Amount"))) != null && !var15_14.equals((Object)"")) {
                            var10_9.currencyAmount = var15_14;
                        }
                        if ((var16_15 = TapjoyUtil.getNodeTrimValue(var11_10.getElementsByTagName("CurrencyName"))) != null && !var16_15.equals((Object)"")) {
                            var10_9.currencyName = var16_15;
                        }
                        if ((var17_16 = TapjoyUtil.getNodeTrimValue(var11_10.getElementsByTagName("VideoURL"))) != null && !var17_16.equals((Object)"")) {
                            var10_9.videoURL = var17_16;
                        }
                        if ((var18_17 = TapjoyUtil.getNodeTrimValue(var11_10.getElementsByTagName("IconURL"))) != null && !var18_17.equals((Object)"")) {
                            var10_9.iconURL = var18_17;
                        }
                        TapjoyLog.i("TapjoyVideo", "-----");
                        TapjoyLog.i("TapjoyVideo", "videoObject.clickURL: " + var10_9.clickURL);
                        TapjoyLog.i("TapjoyVideo", "videoObject.offerID: " + var10_9.offerID);
                        TapjoyLog.i("TapjoyVideo", "videoObject.videoAdName: " + var10_9.videoAdName);
                        TapjoyLog.i("TapjoyVideo", "videoObject.currencyAmount: " + var10_9.currencyAmount);
                        TapjoyLog.i("TapjoyVideo", "videoObject.currencyName: " + var10_9.currencyName);
                        TapjoyLog.i("TapjoyVideo", "videoObject.videoURL: " + var10_9.videoURL);
                        TapjoyLog.i("TapjoyVideo", "videoObject.iconURL: " + var10_9.iconURL);
                        var19_18 = var11_10.getElementsByTagName("Buttons").item(0).getChildNodes();
                        TapjoyLog.i("TapjoyVideo", "buttons node length: " + var19_18.getLength());
                        var20_19 = 0;
                    } else {
                        TapjoyLog.i("TapjoyVideo", "========================================");
                        return true;
                    }
lbl47: // 2 sources:
                    do {
                        block23 : {
                            if (var20_19 >= var19_18.getLength()) break block23;
                            var22_20 = var19_18.item(var20_19).getChildNodes();
                            TapjoyLog.i("TapjoyVideo", "button length: " + var22_20.getLength());
                            if (var22_20.getLength() != 0) break block22;
                            ** GOTO lbl81
                        }
                        this.videoQueue.addElement((Object)var10_9.offerID);
                        this.uncachedVideos.put((Object)var10_9.offerID, (Object)var10_9);
                        this.setVideoIDs();
                        break;
                    } while (true);
                    break;
                } while (true);
            }
            catch (Exception var4_25) {
                TapjoyLog.e("TapjoyVideo", "Error parsing XML: " + var4_25.toString());
                return false;
            }
            ++var8_7;
            ** while (true)
        }
        var23_21 = "";
        var24_22 = "";
        var25_23 = 0;
        do {
            if (var25_23 < var22_20.getLength()) {
                if ((Element)var22_20.item(var25_23) != null) {
                    var26_24 = ((Element)var22_20.item(var25_23)).getTagName();
                    if (var26_24.equals((Object)"Name") && var22_20.item(var25_23).getFirstChild() != null) {
                        var23_21 = var22_20.item(var25_23).getFirstChild().getNodeValue();
                    } else if (var26_24.equals((Object)"URL") && var22_20.item(var25_23).getFirstChild() != null) {
                        var24_22 = var22_20.item(var25_23).getFirstChild().getNodeValue();
                    }
                }
            } else {
                TapjoyLog.i("TapjoyVideo", "name: " + var23_21);
                TapjoyLog.i("TapjoyVideo", "url: " + var24_22);
                var10_9.addButton(var23_21, var24_22);
lbl81: // 2 sources:
                ++var20_19;
                ** continue;
            }
            ++var25_23;
        } while (true);
    }

    private void setVideoIDs() {
        String string2 = "";
        if (this.cachedVideos != null && this.cachedVideos.size() > 0) {
            Enumeration enumeration = this.cachedVideos.keys();
            while (enumeration.hasMoreElements()) {
                String string3 = (String)enumeration.nextElement();
                string2 = string2 + string3;
                if (!enumeration.hasMoreElements()) continue;
                string2 = string2 + ",";
            }
            TapjoyLog.i(TAPJOY_VIDEO, "cachedVideos size: " + this.cachedVideos.size());
        }
        TapjoyLog.i(TAPJOY_VIDEO, "videoIDs: [" + string2 + "]");
        TapjoyConnectCore.setVideoIDs(string2);
    }

    public void enableVideoCache(boolean bl) {
        this.enableVideoCache = bl;
        TapjoyConnectCore.enableVideoCache(bl);
    }

    public TapjoyVideoObject getCurrentVideoData() {
        return this.videoToPlay;
    }

    public void initVideoAd(TapjoyVideoNotifier tapjoyVideoNotifier) {
        TapjoyLog.i(TAPJOY_VIDEO, "initVideoAd");
        if (tapjoyVideoNotifier == null) {
            Log.e((String)TAPJOY_VIDEO, (String)"Error during initVideoAd -- TapjoyVideoNotifier is null");
            return;
        }
        TapjoyVideo.tapjoyVideoNotifier = tapjoyVideoNotifier;
        this.videoCacheDir = Environment.getExternalStorageDirectory().toString() + "/tjcache/data/";
        this.imageCacheDir = Environment.getExternalStorageDirectory().toString() + "/tjcache/tmp/";
        TapjoyUtil.deleteFileOrDirectory(new File(Environment.getExternalStorageDirectory().toString() + "/tapjoy/"));
        this.videoQueue = new Vector();
        this.uncachedVideos = new Hashtable();
        this.cachedVideos = new Hashtable();
        this.imageQueue = new Vector();
        this.imageQueue.addElement((Object)imageTapjoyUrl);
        this.uncachedImages = new Hashtable();
        this.uncachedImages.put((Object)BACKGROUND_WATERMARK_NAME, (Object)imageTapjoyUrl);
        imageTapjoyLocation = this.imageCacheDir + BACKGROUND_WATERMARK_NAME;
        this.setVideoIDs();
        if (!"mounted".equals((Object)Environment.getExternalStorageState())) {
            TapjoyLog.i(TAPJOY_VIDEO, "Media storage unavailable.");
            TapjoyVideo.tapjoyVideoNotifier.videoError(1);
            return;
        }
        new Thread(new Runnable(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public void run() {
                block18 : {
                    File[] arrfile;
                    String string2 = TapjoyConnectCore.getURLParams();
                    String string3 = string2 + "&publisher_user_id=" + TapjoyConnectCore.getUserID();
                    String string4 = new TapjoyURLConnection().connectToURL("https://ws.tapjoyads.com/videos?", string3);
                    boolean bl = false;
                    if (string4 != null) {
                        int n = string4.length();
                        bl = false;
                        if (n > 0) {
                            bl = TapjoyVideo.this.handleGetVideosResponse(string4);
                        }
                    }
                    if (!TapjoyVideo.this.enableVideoCache) {
                        if (!bl) {
                            tapjoyVideoNotifier.videoError(2);
                            return;
                        }
                        if (TapjoyVideo.imageTapjoyUrl != null && TapjoyVideo.imageTapjoyUrl.length() > 0) {
                            try {
                                URL uRL = new URL(TapjoyVideo.imageTapjoyUrl);
                                URLConnection uRLConnection = uRL.openConnection();
                                uRLConnection.setConnectTimeout(15000);
                                uRLConnection.setReadTimeout(25000);
                                uRLConnection.connect();
                                TapjoyVideo.watermark = BitmapFactory.decodeStream((InputStream)uRL.openConnection().getInputStream());
                            }
                            catch (Exception exception) {
                                TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "e: " + exception.toString());
                            }
                        }
                        tapjoyVideoNotifier.videoReady();
                        return;
                    }
                    if (!bl) return;
                    arrfile = new File(TapjoyVideo.this.videoCacheDir).listFiles();
                    if (arrfile == null) break block18;
                    for (int i = 0; i < arrfile.length; ++i) {
                        String string5 = arrfile[i].getName();
                        TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "file[" + i + "]: " + arrfile[i].getAbsolutePath() + " --- " + arrfile[i].getName());
                        TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "key: " + string5 + ", " + string5);
                        TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "uncachedVideos: " + TapjoyVideo.this.uncachedVideos.keySet().toString());
                        if (TapjoyVideo.this.uncachedVideos.containsKey((Object)string5)) {
                            TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "*** Match found, comparing file sizes... ***");
                            TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "local file size: " + arrfile[i].length());
                            TapjoyVideoObject tapjoyVideoObject = (TapjoyVideoObject)TapjoyVideo.this.uncachedVideos.get((Object)string5);
                            String string6 = new TapjoyURLConnection().getContentLength(tapjoyVideoObject.videoURL);
                            if (string6 != null && (long)Integer.parseInt((String)string6) == arrfile[i].length()) {
                                TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "*** VIDEO ALREADY DOWNLOAD!!! " + string5 + " ***");
                                tapjoyVideoObject.dataLocation = arrfile[i].getAbsolutePath();
                                TapjoyVideo.this.cachedVideos.put((Object)string5, TapjoyVideo.this.uncachedVideos.get((Object)string5));
                                TapjoyVideo.this.uncachedVideos.remove((Object)string5);
                                TapjoyVideo.this.videoQueue.remove((Object)string5);
                                TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "setting video location to: " + tapjoyVideoObject.dataLocation);
                                continue;
                            }
                            TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "file size mismatch --- deleting video: " + arrfile[i].getAbsolutePath());
                            TapjoyUtil.deleteFileOrDirectory(arrfile[i]);
                            continue;
                        }
                        TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "VIDEO EXPIRED? removing video from cache: " + string5 + " --- " + arrfile[i].getAbsolutePath());
                        TapjoyUtil.deleteFileOrDirectory(arrfile[i]);
                    }
                }
                TapjoyVideo.this.setVideoIDs();
                if (TapjoyVideo.this.cachedVideos.size() > 0) {
                    tapjoyVideoNotifier.videoReady();
                }
                TapjoyVideo.this.loadNextVideo();
                File[] arrfile = new File(TapjoyVideo.this.imageCacheDir).listFiles();
                TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "------------------------------");
                TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "checking image cache...");
                if (arrfile != null) {
                    for (int i = 0; i < arrfile.length; ++i) {
                        String string7 = arrfile[i].getName();
                        TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "file[" + i + "]: " + arrfile[i].getAbsolutePath() + " --- " + arrfile[i].getName());
                        TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "key: " + string7);
                        TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "uncachedVideos: " + TapjoyVideo.this.uncachedVideos.keySet().toString());
                        if (TapjoyVideo.this.uncachedImages.containsKey((Object)string7)) {
                            TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "*** Match found, comparing file sizes... ***");
                            TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "local file size: " + arrfile[i].length());
                            String string8 = new TapjoyURLConnection().getContentLength((String)TapjoyVideo.this.uncachedImages.get((Object)string7));
                            if (string8 != null && (long)Integer.parseInt((String)string8) == arrfile[i].length()) {
                                TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "*** image already downloaded, removing from queue: " + (String)TapjoyVideo.this.uncachedImages.get((Object)string7));
                                TapjoyVideo.this.imageQueue.remove(TapjoyVideo.this.uncachedImages.get((Object)string7));
                                continue;
                            }
                            TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "file size mismatch --- deleting image");
                            TapjoyUtil.deleteFileOrDirectory(arrfile[i]);
                            continue;
                        }
                        TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "invalid image");
                        TapjoyUtil.deleteFileOrDirectory(arrfile[i]);
                    }
                }
                if (TapjoyVideo.this.uncachedVideos.size() == 0 && TapjoyVideo.this.cachedVideos.size() == 0) {
                    TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "*** no videos returned ***");
                    TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "*** delete cache directory ***");
                    TapjoyUtil.deleteFileOrDirectory(new File(Environment.getExternalStorageDirectory().toString() + "/tjcache/"));
                } else if (TapjoyVideo.this.imageQueue.size() > 0) {
                    TapjoyVideo.this.cacheImage((String)TapjoyVideo.this.imageQueue.elementAt(0));
                }
                TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "------------------------------");
                TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "------------------------------");
                TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "INIT DONE!");
                TapjoyLog.i(TapjoyVideo.TAPJOY_VIDEO, "------------------------------");
            }
        }).start();
        TapjoyConnectCore.setVideoEnabled(true);
    }

    public void loadNextVideo() {
        TapjoyLog.i(TAPJOY_VIDEO, "++++++++++++++++++++++++++++++++++++++++");
        TapjoyLog.i(TAPJOY_VIDEO, "LOAD NEXT VIDEO");
        TapjoyLog.i(TAPJOY_VIDEO, "videoQueue size: " + this.videoQueue.size());
        TapjoyLog.i(TAPJOY_VIDEO, "uncachedVideos size: " + this.uncachedVideos.size());
        TapjoyLog.i(TAPJOY_VIDEO, "cachedVideos size: " + this.cachedVideos.size());
        if (this.cachedVideos.size() < this.videoCacheLimit && this.videoQueue.size() > 0) {
            this.cacheVideo(((TapjoyVideoObject)this.uncachedVideos.get((Object)this.videoQueue.elementAt((int)0))).videoURL);
        }
        TapjoyLog.i(TAPJOY_VIDEO, "++++++++++++++++++++++++++++++++++++++++");
    }

    public void setVideoCacheCount(int n) {
        this.videoCacheLimit = n;
    }

    public boolean startVideo(String string2, String string3, String string4, String string5, String string6) {
        File file;
        boolean bl = true;
        TapjoyLog.i(TAPJOY_VIDEO, "Starting video activity with video: " + string2);
        if (string2 == null || string5 == null || string6 == null || string2.length() == 0 || string5.length() == 0 || string6.length() == 0) {
            TapjoyLog.i(TAPJOY_VIDEO, "aborting video playback... invalid or missing parameter");
            return false;
        }
        this.videoToPlay = (TapjoyVideoObject)this.cachedVideos.get((Object)string2);
        if (!"mounted".equals((Object)Environment.getExternalStorageState())) {
            TapjoyLog.e(TAPJOY_VIDEO, "Cannot access external storage");
            tapjoyVideoNotifier.videoError(1);
            return false;
        }
        if (this.videoToPlay == null) {
            TapjoyLog.i(TAPJOY_VIDEO, "video not cached... checking uncached videos");
            this.videoToPlay = (TapjoyVideoObject)this.uncachedVideos.get((Object)string2);
            if (this.videoToPlay == null) {
                TapjoyLog.e(TAPJOY_VIDEO, "null video object? aborting.");
                return false;
            }
            bl = false;
        }
        this.videoToPlay.currencyName = string3;
        this.videoToPlay.currencyAmount = string4;
        this.videoToPlay.clickURL = string5;
        this.videoToPlay.webviewURL = string6;
        TapjoyLog.i(TAPJOY_VIDEO, "videoToPlay: " + this.videoToPlay.offerID);
        TapjoyLog.i(TAPJOY_VIDEO, "amount: " + this.videoToPlay.currencyAmount);
        TapjoyLog.i(TAPJOY_VIDEO, "currency: " + this.videoToPlay.currencyName);
        TapjoyLog.i(TAPJOY_VIDEO, "clickURL: " + this.videoToPlay.clickURL);
        TapjoyLog.i(TAPJOY_VIDEO, "location: " + this.videoToPlay.dataLocation);
        TapjoyLog.i(TAPJOY_VIDEO, "webviewURL: " + this.videoToPlay.webviewURL);
        if (bl && this.videoToPlay.dataLocation != null && ((file = new File(this.videoToPlay.dataLocation)) == null || !file.exists())) {
            TapjoyLog.e(TAPJOY_VIDEO, "video file does not exist.");
            return false;
        }
        Intent intent = new Intent(this.context, TapjoyVideoView.class);
        intent.setFlags(268435456);
        intent.putExtra("VIDEO_PATH", string2);
        this.context.startActivity(intent);
        return true;
    }

}

