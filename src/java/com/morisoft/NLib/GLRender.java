/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  javax.microedition.khronos.opengles.GL10
 */
package com.morisoft.NLib;

import com.morisoft.NLib.Native;
import com.morisoft.NLib.ogl.GLSurfaceView;
import javax.microedition.khronos.opengles.GL10;
import org.cocos2dx.lib.Cocos2dxActivity;

public class GLRender
implements GLSurfaceView.Renderer {
    @Override
    public void drawFrame(GL10 gL10) {
    }

    @Override
    public int[] getConfigSpec() {
        return null;
    }

    @Override
    public void shutdown(GL10 gL10) {
    }

    @Override
    public void sizeChanged(GL10 gL10, int n, int n2) {
    }

    @Override
    public void surfaceCreated(GL10 gL10) {
        Native.initialize(Cocos2dxActivity.m_Activity.screenWidth, Cocos2dxActivity.m_Activity.screenHeight);
    }
}

