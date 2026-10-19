/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.util.Log
 *  java.io.File
 *  java.io.FileNotFoundException
 *  java.io.FileOutputStream
 *  java.io.IOException
 *  java.io.InputStream
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.net.MalformedURLException
 *  java.net.URL
 *  java.net.URLConnection
 */
package com.morisoft.NLib.Streaming;

import android.os.Handler;
import android.util.Log;
import com.morisoft.NLib.NativePopUp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.cocos2dx.lib.Cocos2dxActivity;

public class StreamingDownload {
    private static final String StreamingCacheFilename = "streamingFileData.dat";
    private static final String TAG = "StreamingDownload";
    private Cocos2dxActivity context = Cocos2dxActivity.m_Activity;
    private File downloadFile = null;
    private Handler handler = null;

    public StreamingDownload() {
        this.handler = this.context.m_Handler;
    }

    static /* synthetic */ File access$0(StreamingDownload streamingDownload) {
        return streamingDownload.downloadFile;
    }

    static /* synthetic */ Cocos2dxActivity access$1(StreamingDownload streamingDownload) {
        return streamingDownload.context;
    }

    static /* synthetic */ void access$2(StreamingDownload streamingDownload, File file) {
        streamingDownload.downloadFile = file;
    }

    public void download(final String string2, final String string3, final String string4, int n) {
        Runnable runnable = new Runnable(){

            /*
             * Unable to fully structure code
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Lifted jumps to return sites
             */
            public void run() {
                block44 : {
                    var1_1 = null;
                    var2_2 = null;
                    var3_3 = 0;
                    var4_4 = new byte[16384];
                    try {
                        var24_5 = new URL(String.valueOf((Object)string2) + "/" + string4).openConnection();
                        var24_5.connect();
                        var1_1 = var24_5.getInputStream();
                        if (var1_1 == null) {
                            Log.e((String)"StreamingDownload", (String)("Unable to create InputStream for downloadUrl:" + string2 + "/" + string4));
                        }
                        StreamingDownload.access$2(StreamingDownload.this, new File(StreamingDownload.access$1(StreamingDownload.this).getCacheDir(), "streamingFileData.dat"));
                        var26_6 = StreamingDownload.access$0(StreamingDownload.this).exists();
                        var2_2 = null;
                        if (var26_6) {
                            StreamingDownload.access$0(StreamingDownload.this).delete();
                        }
                        var28_7 = new FileOutputStream(StreamingDownload.access$0(StreamingDownload.this));
                        break block44;
                    }
                    catch (FileNotFoundException var20_14) {}
                    ** GOTO lbl-1000
                    catch (IOException var16_18) {}
                    ** GOTO lbl-1000
                    catch (InterruptedException var12_22) {}
                    ** GOTO lbl-1000
                }
                do {
                    block45 : {
                        var29_8 = var1_1.read(var4_4);
                        if (var29_8 > 0) break block45;
                        var30_9 = new File(String.valueOf((Object)Cocos2dxActivity.m_strFullPath) + string3, string4);
                        StreamingDownload.this.moveFile(StreamingDownload.access$0(StreamingDownload.this), var30_9);
                        if (var28_7 == null) ** GOTO lbl34
                        var28_7.close();
lbl34: // 2 sources:
                        if (var1_1 != null) {
                            var1_1.close();
                        }
                        if (StreamingDownload.access$0(StreamingDownload.this) != null && StreamingDownload.access$0(StreamingDownload.this).exists()) {
                            StreamingDownload.access$0(StreamingDownload.this).delete();
                        }
                        ** GOTO lbl106
                    }
                    Thread.sleep((long)10L);
                    var3_3 += var29_8;
                    var28_7.write(var4_4, 0, var29_8);
                    Thread.sleep((long)10L);
                    continue;
                    break;
                } while (true);
                catch (MalformedURLException var5_10) {
                    var2_2 = var28_7;
                    ** GOTO lbl109
                    catch (IOException var31_30) {
                        var31_30.printStackTrace();
                    }
                }
                ** GOTO lbl106
                {
                    catch (Throwable var6_28) {
                        var2_2 = var28_7;
                        ** GOTO lbl-1000
                    }
                    catch (InterruptedException var12_24) {
                        var2_2 = var28_7;
                    }
lbl-1000: // 2 sources:
                    {
                        Log.e((String)"StreamingDownload", (String)"InterruptedException:", (Throwable)var12_23);
                        var12_23.printStackTrace();
                        if (var2_2 == null) ** GOTO lbl64
                        try {
                            var2_2.close();
lbl64: // 2 sources:
                            if (var1_1 != null) {
                                var1_1.close();
                            }
                            if (StreamingDownload.access$0(StreamingDownload.this) != null && StreamingDownload.access$0(StreamingDownload.this).exists()) {
                                StreamingDownload.access$0(StreamingDownload.this).delete();
                            }
                            ** GOTO lbl106
                        }
                        catch (IOException var14_25) {
                            var14_25.printStackTrace();
                        }
                        ** GOTO lbl106
                        catch (IOException var16_20) {
                            var2_2 = var28_7;
                        }
lbl-1000: // 2 sources:
                        {
                            Log.e((String)"StreamingDownload", (String)"IOException:", (Throwable)var16_19);
                            var16_19.printStackTrace();
                            if (var2_2 == null) ** GOTO lbl81
                            try {
                                var2_2.close();
lbl81: // 2 sources:
                                if (var1_1 != null) {
                                    var1_1.close();
                                }
                                if (StreamingDownload.access$0(StreamingDownload.this) != null && StreamingDownload.access$0(StreamingDownload.this).exists()) {
                                    StreamingDownload.access$0(StreamingDownload.this).delete();
                                }
                                ** GOTO lbl106
                            }
                            catch (IOException var18_21) {
                                var18_21.printStackTrace();
                            }
                            ** GOTO lbl106
                            catch (FileNotFoundException var20_16) {
                                var2_2 = var28_7;
                            }
lbl-1000: // 2 sources:
                            {
                                Log.e((String)"StreamingDownload", (String)"FileNotFoundException:", (Throwable)var20_15);
                                var20_15.printStackTrace();
                                if (var2_2 == null) ** GOTO lbl98
                                try {
                                    var2_2.close();
lbl98: // 2 sources:
                                    if (var1_1 != null) {
                                        var1_1.close();
                                    }
                                    if (StreamingDownload.access$0(StreamingDownload.this) != null && StreamingDownload.access$0(StreamingDownload.this).exists()) {
                                        StreamingDownload.access$0(StreamingDownload.this).delete();
                                    }
                                }
                                catch (IOException var22_17) {
                                    var22_17.printStackTrace();
                                }
lbl106: // 15 sources:
                                do {
                                    NativePopUp.hideProgressBar();
                                    return;
                                    break;
                                } while (true);
lbl109: // 2 sources:
                                do {
                                    try {
                                        Log.e((String)"StreamingDownload", (String)"MalformedURLException:", (Throwable)var5_11);
                                        if (var2_2 == null) ** GOTO lbl128
                                    }
                                    catch (Throwable var6_26) lbl-1000: // 2 sources:
                                    {
                                        if (var2_2 == null) ** GOTO lbl117
                                        try {
                                            var2_2.close();
lbl117: // 2 sources:
                                            if (var1_1 != null) {
                                                var1_1.close();
                                            }
                                            if (StreamingDownload.access$0(StreamingDownload.this) == null) throw var6_27;
                                            if (StreamingDownload.access$0(StreamingDownload.this).exists() == false) throw var6_27;
                                            StreamingDownload.access$0(StreamingDownload.this).delete();
                                        }
                                        catch (IOException var7_29) {
                                            var7_29.printStackTrace();
                                            throw var6_27;
                                        }
                                        throw var6_27;
                                    }
                                    try {
                                        var2_2.close();
lbl128: // 2 sources:
                                        if (var1_1 != null) {
                                            var1_1.close();
                                        }
                                        if (StreamingDownload.access$0(StreamingDownload.this) == null || !StreamingDownload.access$0(StreamingDownload.this).exists()) ** GOTO lbl106
                                        StreamingDownload.access$0(StreamingDownload.this).delete();
                                    }
                                    catch (IOException var10_13) {
                                        var10_13.printStackTrace();
                                    }
                                    ** continue;
                                    break;
                                } while (true);
                            }
                        }
                    }
                }
                catch (MalformedURLException var5_12) {
                    var2_2 = null;
                    ** continue;
                }
            }
        };
        this.handler.post(runnable);
    }

    /*
     * Exception decompiling
     */
    public void moveFile(File var1_1, File var2_2) throws IOException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 9[UNCONDITIONALDOLOOP]
        // org.benf.cfr.reader.b.a.a.j.a(Op04StructuredStatement.java:432)
        // org.benf.cfr.reader.b.a.a.j.d(Op04StructuredStatement.java:484)
        // org.benf.cfr.reader.b.a.a.i.a(Op03SimpleStatement.java:607)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:692)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:182)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:127)
        // org.benf.cfr.reader.entities.attributes.f.c(AttributeCode.java:96)
        // org.benf.cfr.reader.entities.g.p(Method.java:396)
        // org.benf.cfr.reader.entities.d.e(ClassFile.java:890)
        // org.benf.cfr.reader.entities.d.b(ClassFile.java:792)
        // org.benf.cfr.reader.b.a(Driver.java:128)
        // org.benf.cfr.reader.a.a(CfrDriverImpl.java:63)
        // com.njlabs.showjava.decompilers.JavaExtractionWorker.decompileWithCFR(JavaExtractionWorker.kt:61)
        // com.njlabs.showjava.decompilers.JavaExtractionWorker.doWork(JavaExtractionWorker.kt:130)
        // com.njlabs.showjava.decompilers.BaseDecompiler.withAttempt(BaseDecompiler.kt:108)
        // com.njlabs.showjava.workers.DecompilerWorker$b.run(DecompilerWorker.kt:118)
        // java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1100)
        // java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
        // java.lang.Thread.run(Thread.java:1572)
        throw new IllegalStateException("Decompilation failed");
    }

}

