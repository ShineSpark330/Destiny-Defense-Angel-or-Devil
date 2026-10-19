/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.AssetFileDescriptor
 *  android.content.res.AssetManager
 *  android.media.MediaPlayer
 *  android.util.Log
 *  java.io.File
 *  java.io.FileDescriptor
 *  java.io.FileInputStream
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.morisoft.NLib.Sound;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.util.Log;
import com.morisoft.NLib.Util;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;

public class BackGroundMusic {
    private static final String TAG = "BackgroundMusic";
    private MediaPlayer mBackgroundMediaPlayer;
    private Context mContext;
    private String mCurrentPath;
    private boolean mIsPaused;
    private float mLeftVolume;
    private float mRightVolume;

    public BackGroundMusic(Context context) {
        this.mContext = context;
        this.initData();
    }

    private MediaPlayer createMediaplayer(String string2) {
        int n = string2.indexOf("assets/");
        if (n == -1) {
            String string3 = "/" + Util.getPackageName() + "/";
            int n2 = string2.indexOf(string3);
            return this.createMediaplayerFromAppRoot(new File(string2.substring(0, -1 + (n2 + string3.length())), string2.substring(n2 + string3.length())));
        }
        return this.createMediaplayerFromAssets(string2.substring(n + "assets/".length()));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private MediaPlayer createMediaplayerFromAppRoot(File file) {
        MediaPlayer mediaPlayer;
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            mediaPlayer = new MediaPlayer();
        }
        catch (Exception exception) {}
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(fileInputStream.getFD());
            mediaPlayer.prepare();
            mediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
            fileInputStream.close();
            return mediaPlayer;
        }
        catch (Exception exception) {}
        {
            void var4_5;
            Log.e((String)TAG, (String)"createMediaplayerFromAppRoot", (Throwable)var4_5);
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private MediaPlayer createMediaplayerFromAssets(String string2) {
        MediaPlayer mediaPlayer;
        AssetFileDescriptor assetFileDescriptor;
        try {
            assetFileDescriptor = this.mContext.getAssets().openFd(string2);
            mediaPlayer = new MediaPlayer();
        }
        catch (Exception exception) {}
        try {
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            mediaPlayer.prepare();
            mediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
            return mediaPlayer;
        }
        catch (Exception exception) {}
        {
            MediaPlayer mediaPlayer2;
            AssetFileDescriptor assetFileDescriptor2;
            try {
                assetFileDescriptor2 = this.mContext.getAssets().openFd(string2);
                mediaPlayer2 = new MediaPlayer();
            }
            catch (Exception exception) {}
            try {
                mediaPlayer2.setDataSource(assetFileDescriptor2.getFileDescriptor());
                mediaPlayer2.prepare();
                mediaPlayer2.setVolume(this.mLeftVolume, this.mRightVolume);
                return mediaPlayer2;
            }
            catch (Exception exception) {}
            {
                void var3_8;
                Log.e((String)TAG, (String)"createMediaplayerFromAssets", (Throwable)var3_8);
                return null;
            }
        }
    }

    private void initData() {
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
        this.mBackgroundMediaPlayer = null;
        this.mIsPaused = false;
        this.mCurrentPath = null;
    }

    public void end() {
        if (this.mBackgroundMediaPlayer != null) {
            this.mBackgroundMediaPlayer.release();
        }
        this.initData();
    }

    public float getBackgroundVolume() {
        if (this.mBackgroundMediaPlayer != null) {
            return (this.mLeftVolume + this.mRightVolume) / 2.0f;
        }
        return 0.0f;
    }

    public boolean isBackgroundMusicPlaying() {
        if (this.mBackgroundMediaPlayer == null) {
            return false;
        }
        return this.mBackgroundMediaPlayer.isPlaying();
    }

    public void pauseBackgroundMusic() {
        if (this.mBackgroundMediaPlayer != null && this.mBackgroundMediaPlayer.isPlaying()) {
            this.mBackgroundMediaPlayer.pause();
            this.mIsPaused = true;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void playBackgroundMusic(String string2, boolean bl) {
        Log.d((String)"BGMTEST", (String)string2);
        if (!this.preloadBackgroundMusic(string2)) return;
        this.mBackgroundMediaPlayer.stop();
        this.mBackgroundMediaPlayer.setLooping(bl);
        try {
            this.mBackgroundMediaPlayer.prepare();
            this.mBackgroundMediaPlayer.seekTo(0);
            this.mBackgroundMediaPlayer.start();
            this.mIsPaused = false;
            return;
        }
        catch (Exception exception) {
            Log.e((String)TAG, (String)"playBackgroundMusic: error state");
            return;
        }
    }

    public boolean preloadBackgroundMusic(String string2) {
        if (this.mCurrentPath == null || !this.mCurrentPath.equals((Object)string2)) {
            if (this.mBackgroundMediaPlayer != null) {
                this.mBackgroundMediaPlayer.release();
            }
            this.mBackgroundMediaPlayer = this.createMediaplayer(string2);
            if (this.mBackgroundMediaPlayer == null) {
                this.mCurrentPath = null;
                Log.e((String)TAG, (String)"preloadBackgroundMusic: background media player is null");
                return false;
            }
            this.mCurrentPath = string2;
        }
        return true;
    }

    public void resumeBackgroundMusic() {
        if (this.mBackgroundMediaPlayer != null && this.mIsPaused) {
            this.mBackgroundMediaPlayer.start();
            this.mIsPaused = false;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void rewindBackgroundMusic() {
        if (this.mBackgroundMediaPlayer == null) return;
        this.mBackgroundMediaPlayer.stop();
        try {
            this.mBackgroundMediaPlayer.prepare();
            this.mBackgroundMediaPlayer.seekTo(0);
            this.mBackgroundMediaPlayer.start();
            this.mIsPaused = false;
            return;
        }
        catch (Exception exception) {
            Log.e((String)TAG, (String)"rewindBackgroundMusic: error state");
            return;
        }
    }

    public void setBackgroundVolume(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        this.mRightVolume = f;
        this.mLeftVolume = f;
        if (this.mBackgroundMediaPlayer != null) {
            this.mBackgroundMediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
        }
    }

    public void stopBackgroundMusic() {
        if (this.mBackgroundMediaPlayer != null) {
            this.mBackgroundMediaPlayer.stop();
            this.mIsPaused = false;
        }
    }
}

