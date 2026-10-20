/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.media.MediaPlayer
 *  android.media.MediaPlayer$OnErrorListener
 *  android.util.Log
 *  java.io.File
 *  java.io.FileDescriptor
 *  java.io.FileInputStream
 *  java.io.FileOutputStream
 *  java.io.IOException
 *  java.io.InputStream
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.net.URL
 *  java.net.URLConnection
 */
package com.morisoft.NLib.Streaming;

import android.media.MediaPlayer;
import android.util.Log;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import org.cocos2dx.lib.Cocos2dxActivity;

public class StreamingMediaPlayer
implements Runnable {
    private static int INTIAL_KB_BUFFER = 16;
    private static String StreamingCacheFilename = "streamingData.dat";
    final String TAG = "StreamingMediaPlayer";
    private boolean bStreamingStart = false;
    private boolean bThreadLife = false;
    private Cocos2dxActivity context = Cocos2dxActivity.m_Activity;
    private File downloadingMediaFile;
    private boolean isPlaying = false;
    private String mFilename;
    private String mPath;
    private String mUrl;
    private MediaPlayer mediaPlayer;
    private Thread thread = null;
    private int totalKbRead = 0;

    private MediaPlayer createMediaPlayer(File file) throws IOException {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener(){

            public boolean onError(MediaPlayer mediaPlayer, int n, int n2) {
                Log.e((String)"StreamingMediaPlayer", (String)("Error in MediaPlayer: (" + n + ") with extra (" + n2 + ")"));
                return false;
            }
        });
        mediaPlayer.setDataSource(new FileInputStream(file).getFD());
        mediaPlayer.prepare();
        mediaPlayer.seekTo(0);
        mediaPlayer.setVolume(1.0f, 1.0f);
        mediaPlayer.setLooping(true);
        return mediaPlayer;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void fireDataFullyLoaded() {
        if (this.bThreadLife) {
            try {
                if (this.mPath != null) {
                    File file = new File(String.valueOf((Object)Cocos2dxActivity.m_strFullPath) + this.mPath, this.mFilename);
                    this.moveFile(this.downloadingMediaFile, file);
                }
            }
            catch (IOException iOException) {
                Log.e((String)"StreamingMediaPlayer", (String)"Error move file", (Throwable)iOException);
            }
            this.transferBufferToMediaPlayer();
            this.downloadingMediaFile.delete();
            this.bThreadLife = false;
            return;
        }
        this.bThreadLife = false;
        if (this.mediaPlayer != null) {
            this.mediaPlayer.stop();
        }
        this.downloadingMediaFile.delete();
        if (!this.bStreamingStart) return;
        {
            this.start(this.mUrl, this.mPath, this.mFilename, INTIAL_KB_BUFFER);
            return;
        }
    }

    private void startMediaPlayer(File file) {
        try {
            if (this.mediaPlayer != null) {
                this.mediaPlayer.stop();
                this.mediaPlayer.release();
            }
            this.mediaPlayer = null;
            this.mediaPlayer = this.createMediaPlayer(file);
            this.mediaPlayer.start();
            this.isPlaying = this.mediaPlayer.isPlaying();
            return;
        }
        catch (IOException iOException) {
            Log.e((String)"StreamingMediaPlayer", (String)"Error initializing the MediaPlayer.", (Throwable)iOException);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void testMediaBuffer() {
        if (this.mediaPlayer == null) {
            if (this.totalKbRead < INTIAL_KB_BUFFER || !this.bThreadLife) return;
            {
                try {
                    this.startMediaPlayer(this.downloadingMediaFile);
                    return;
                }
                catch (Exception exception) {
                    Log.e((String)"StreamingMediaPlayer", (String)"Error copying buffered conent.", (Throwable)exception);
                    return;
                }
            }
        } else {
            if (this.mediaPlayer.getDuration() - this.mediaPlayer.getCurrentPosition() > 1000) return;
            {
                this.transferBufferToMediaPlayer();
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void transferBufferToMediaPlayer() {
        boolean bl;
        int n;
        int n2;
        try {
            bl = this.mediaPlayer.isPlaying();
            n2 = this.mediaPlayer.getDuration();
            n = this.mediaPlayer.getCurrentPosition();
            this.mediaPlayer.pause();
            MediaPlayer mediaPlayer = this.createMediaPlayer(this.downloadingMediaFile);
            mediaPlayer.seekTo(n);
            if (this.mediaPlayer != null) {
                this.mediaPlayer.release();
            }
            this.mediaPlayer = mediaPlayer;
        }
        catch (Exception exception) {
            Log.e((String)"StreamingMediaPlayer", (String)"Error updating to newly loaded content.", (Throwable)exception);
            this.bThreadLife = false;
            this.bStreamingStart = true;
            return;
        }
        boolean bl2 = n2 - n <= 1000;
        if (!bl) {
            if (!bl2) return;
        }
        this.mediaPlayer.start();
        this.isPlaying = this.mediaPlayer.isPlaying();
    }

    public boolean isPlaying() {
        return this.isPlaying;
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

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void run() {
        StreamingMediaPlayer streamingMediaPlayer = this;
        synchronized (streamingMediaPlayer) {
            try {
                boolean bl;
                int n;
                URLConnection uRLConnection = new URL(String.valueOf((Object)this.mUrl) + "/" + this.mFilename).openConnection();
                uRLConnection.connect();
                InputStream inputStream = uRLConnection.getInputStream();
                if (inputStream == null) {
                    Log.e((String)"StreamingMediaPlayer", (String)("Unable to create InputStream for mediaUrl:" + this.mUrl + "/" + this.mFilename));
                }
                this.downloadingMediaFile = new File(this.context.getCacheDir(), StreamingCacheFilename);
                if (this.downloadingMediaFile.exists()) {
                    this.downloadingMediaFile.delete();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(this.downloadingMediaFile);
                byte[] arrby = new byte[1024 * INTIAL_KB_BUFFER];
                this.totalKbRead = 0;
                MediaPlayer mediaPlayer = this.mediaPlayer;
                int n2 = 0;
                int n3 = 0;
                if (mediaPlayer != null) {
                    this.mediaPlayer.stop();
                    this.mediaPlayer.release();
                    this.mediaPlayer = null;
                    this.isPlaying = false;
                }
                while ((n = inputStream.read(arrby)) > 0) {
                    fileOutputStream.write(arrby, 0, n);
                    n2 += n;
                    this.totalKbRead = (n3 += n) / 1024;
                    this.testMediaBuffer();
                    if (this.mediaPlayer != null) {
                        this.isPlaying = this.mediaPlayer.isPlaying();
                    }
                    if (this.bThreadLife) continue;
                }
                if (this.mediaPlayer == null && (bl = this.bThreadLife)) {
                    try {
                        this.startMediaPlayer(this.downloadingMediaFile);
                    }
                    catch (Exception exception) {
                        Log.e((String)"StreamingMediaPlayer", (String)"Error copying buffered conent.", (Throwable)exception);
                    }
                }
                inputStream.close();
                this.fireDataFullyLoaded();
            }
            catch (IOException iOException) {
                Log.e((String)"StreamingMediaPlayer", (String)("Unable to initialize the MediaPlayer for fileUrl=" + this.mUrl + "/" + this.mFilename), (Throwable)iOException);
            }
            return;
        }
    }

    public void start(String string2, String string3, String string4, int n) {
        this.mPath = string3;
        this.mFilename = string4;
        this.mUrl = string2;
        this.bStreamingStart = false;
        INTIAL_KB_BUFFER = n;
        File file = new File(String.valueOf((Object)Cocos2dxActivity.m_strFullPath) + this.mPath, this.mFilename);
        if (file.exists()) {
            this.stop();
            this.startMediaPlayer(file);
            return;
        }
        if (!this.bThreadLife) {
            this.stop();
            this.thread = new Thread((Runnable)this);
            this.thread.start();
            this.bThreadLife = true;
            this.bStreamingStart = false;
            return;
        }
        this.stop();
        this.bThreadLife = false;
        this.bStreamingStart = true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void stop() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.stop();
        }
        this.isPlaying = this.mediaPlayer != null ? this.mediaPlayer.isPlaying() : false;
        this.bThreadLife = false;
        this.bStreamingStart = false;
    }

}

