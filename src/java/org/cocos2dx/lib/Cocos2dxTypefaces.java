/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.AssetManager
 *  android.graphics.Typeface
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Hashtable
 */
package org.cocos2dx.lib;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import java.util.Hashtable;

public class Cocos2dxTypefaces {
    private static final Hashtable<String, Typeface> cache = new Hashtable();

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static Typeface get(Context context, String string2) {
        Hashtable<String, Typeface> hashtable;
        Hashtable<String, Typeface> hashtable2 = hashtable = cache;
        synchronized (hashtable2) {
            if (cache.containsKey((Object)string2)) return (Typeface)cache.get((Object)string2);
            Typeface typeface = Typeface.createFromAsset((AssetManager)context.getAssets(), (String)string2);
            cache.put((Object)string2, (Object)typeface);
            return (Typeface)cache.get((Object)string2);
        }
    }
}

