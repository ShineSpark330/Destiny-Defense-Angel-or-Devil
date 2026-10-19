/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.hardware.Sensor
 *  android.hardware.SensorEvent
 *  android.hardware.SensorEventListener
 *  android.hardware.SensorManager
 *  android.view.Display
 *  android.view.WindowManager
 *  java.lang.Object
 *  java.lang.String
 */
package org.cocos2dx.lib;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.WindowManager;

public class Cocos2dxAccelerometer
implements SensorEventListener {
    private static final String TAG = "Cocos2dxAccelerometer";
    private Sensor mAccelerometer;
    private Context mContext;
    private int mNaturalOrientation;
    private SensorManager mSensorManager;

    public Cocos2dxAccelerometer(Context context) {
        this.mContext = context;
        this.mSensorManager = (SensorManager)this.mContext.getSystemService("sensor");
        this.mAccelerometer = this.mSensorManager.getDefaultSensor(1);
        this.mNaturalOrientation = ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getOrientation();
    }

    private static native void onSensorChanged(float var0, float var1, float var2, long var3);

    public void disable() {
        this.mSensorManager.unregisterListener((SensorEventListener)this);
    }

    public void enable() {
        this.mSensorManager.registerListener((SensorEventListener)this, this.mAccelerometer, 1);
    }

    public void onAccuracyChanged(Sensor sensor, int n) {
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() != 1) {
            return;
        }
        float f = sensorEvent.values[0];
        float f2 = sensorEvent.values[1];
        float f3 = sensorEvent.values[2];
        int n = this.mContext.getResources().getConfiguration().orientation;
        if (n == 2 && this.mNaturalOrientation != 0) {
            float f4 = f;
            f = -f2;
            f2 = f4;
        } else if (n == 1 && this.mNaturalOrientation != 0) {
            float f5 = f;
            f = f2;
            f2 = -f5;
        }
        Cocos2dxAccelerometer.onSensorChanged(f, f2, f3, sensorEvent.timestamp);
    }
}

