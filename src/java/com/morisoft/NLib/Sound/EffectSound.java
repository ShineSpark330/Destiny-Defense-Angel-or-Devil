/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.AssetFileDescriptor
 *  android.content.res.AssetManager
 *  android.media.SoundPool
 *  android.util.Log
 *  java.io.FileDescriptor
 *  java.io.FileInputStream
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.HashMap
 *  java.util.Iterator
 *  java.util.Map
 *  java.util.Map$Entry
 *  java.util.Set
 */
package com.morisoft.NLib.Sound;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.SoundPool;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.cocos2dx.lib.Cocos2dxActivity;

public class EffectSound {
    private static final int MAX_SIMULTANEOUS_STREAMS_DEFAULT = 100;
    private static final int SOUND_PRIORITY = 1;
    private static final int SOUND_QUALITY = 5;
    private static final float SOUND_RATE = 1.0f;
    private static final String TAG = "EffectSound";
    private final int INVALID_SOUND_ID = -1;
    private final int INVALID_STREAM_ID = -1;
    private Context mContext;
    private float mLeftVolume;
    private HashMap<String, Integer> mPathSoundIDMap;
    private float mRightVolume;
    private HashMap<Integer, Integer> mSoundIdStreamIdMap;
    private SoundPool mSoundPool;

    public EffectSound(Context context) {
        this.mContext = context;
        this.initData();
    }

    private void initData() {
        this.mSoundIdStreamIdMap = new HashMap();
        this.mSoundPool = new SoundPool(100, 3, 5);
        this.mPathSoundIDMap = new HashMap();
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
    }

    private void pauseOrResumeAllEffects(boolean bl) {
        Iterator iterator = this.mSoundIdStreamIdMap.entrySet().iterator();
        while (iterator.hasNext()) {
            int n = (Integer)((Map.Entry)iterator.next()).getKey();
            if (bl) {
                this.pauseEffect(n);
                continue;
            }
            this.resumeEffect(n);
        }
        return;
    }

    public int createSoundId(String string2) {
        try {
            FileInputStream fileInputStream = new FileInputStream(String.valueOf((Object)Cocos2dxActivity.m_strFullPath) + string2);
            FileDescriptor fileDescriptor = fileInputStream.getFD();
            int n = this.mSoundPool.load(fileDescriptor, 0L, (long)fileInputStream.available(), 0);
            return n;
        }
        catch (Exception exception) {
            try {
                int n = this.mSoundPool.load(this.mContext.getAssets().openFd(string2), 0);
                return n;
            }
            catch (Exception exception2) {
                Log.e((String)TAG, (String)("sound file is not load\n" + exception.getMessage()), (Throwable)exception2);
                return -1;
            }
        }
    }

    public void end() {
        this.mSoundPool.release();
        this.mPathSoundIDMap.clear();
        this.mSoundIdStreamIdMap.clear();
        this.initData();
    }

    public float getEffectsVolume() {
        return (this.mLeftVolume + this.mRightVolume) / 2.0f;
    }

    public void pauseAllEffects() {
        this.pauseOrResumeAllEffects(true);
    }

    public void pauseEffect(int n) {
        Integer n2 = (Integer)this.mSoundIdStreamIdMap.get((Object)n);
        if (n2 != null && n2 != -1) {
            this.mSoundPool.pause(n2.intValue());
            this.mPathSoundIDMap.remove((Object)n);
        }
    }

    public int playEffect(final String string2, final boolean bl) {
        final Integer n = (Integer)this.mPathSoundIDMap.get((Object)string2);
        if (n != null) {
            new Thread(new Runnable(){

                public void run() {
                    EffectSound.this.playEffect(n, string2, bl);
                }
            }).start();
            return n;
        }
        return -1;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void playEffect(Integer n, String string2, boolean bl) {
        this.mSoundPool.stop(n.intValue());
        SoundPool soundPool = this.mSoundPool;
        int n2 = n;
        float f = this.mLeftVolume;
        float f2 = this.mRightVolume;
        int n3 = bl ? -1 : 0;
        int n4 = soundPool.play(n2, f, f2, 1, n3, 1.0f);
        this.mSoundIdStreamIdMap.put((Object)n, (Object)n4);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int preloadEffect(String string2) {
        if (this.mPathSoundIDMap.get((Object)string2) != null) {
            return (Integer)this.mPathSoundIDMap.get((Object)string2);
        }
        int n = this.createSoundId(string2);
        if (n == -1) return n;
        this.mSoundIdStreamIdMap.put((Object)n, (Object)-1);
        this.mPathSoundIDMap.put((Object)string2, (Object)n);
        return n;
    }

    public void resumeAllEffects() {
        this.pauseOrResumeAllEffects(false);
    }

    public void resumeEffect(int n) {
        Integer n2 = (Integer)this.mSoundIdStreamIdMap.get((Object)n);
        if (n2 != null && n2 != -1) {
            this.mSoundPool.resume(n2.intValue());
            this.mPathSoundIDMap.remove((Object)n);
        }
    }

    public void setEffectsVolume(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        this.mRightVolume = f;
        this.mLeftVolume = f;
        Iterator iterator = this.mSoundIdStreamIdMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            this.mSoundPool.setVolume(((Integer)entry.getValue()).intValue(), this.mLeftVolume, this.mRightVolume);
        }
        return;
    }

    public void stopAllEffects() {
        Iterator iterator = this.mSoundIdStreamIdMap.entrySet().iterator();
        while (iterator.hasNext()) {
            this.stopEffect((Integer)((Map.Entry)iterator.next()).getKey());
        }
        return;
    }

    public void stopEffect(int n) {
        Integer n2 = (Integer)this.mSoundIdStreamIdMap.get((Object)n);
        if (n2 != null && n2 != -1) {
            this.mSoundPool.stop(n2.intValue());
            this.mPathSoundIDMap.remove((Object)n);
        }
    }

    public void unloadEffect(String string2) {
        Integer n = (Integer)this.mPathSoundIDMap.remove((Object)string2);
        if (n != null) {
            this.mSoundPool.unload(n.intValue());
            this.mSoundIdStreamIdMap.remove((Object)n);
        }
    }

}

