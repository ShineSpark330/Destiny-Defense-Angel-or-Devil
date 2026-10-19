/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.Dialog
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.graphics.Typeface
 *  android.media.MediaPlayer
 *  android.media.MediaPlayer$OnCompletionListener
 *  android.media.MediaPlayer$OnErrorListener
 *  android.media.MediaPlayer$OnPreparedListener
 *  android.net.Uri
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Handler
 *  android.view.KeyEvent
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.webkit.WebSettings
 *  android.webkit.WebView
 *  android.webkit.WebViewClient
 *  android.widget.ImageView
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.TextView
 *  android.widget.VideoView
 *  java.lang.CharSequence
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.Timer
 *  java.util.TimerTask
 */
package com.tapjoy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyDisplayMetricsUtil;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyURLConnection;
import com.tapjoy.TapjoyVideo;
import com.tapjoy.TapjoyVideoObject;
import java.util.Timer;
import java.util.TimerTask;

public class TapjoyVideoView
extends Activity
implements MediaPlayer.OnCompletionListener,
MediaPlayer.OnErrorListener,
MediaPlayer.OnPreparedListener {
    private static final String BUNDLE_DIALOG_SHOWING = "dialog_showing";
    private static final String BUNDLE_SEEK_TIME = "seek_time";
    private static final int DIALOG_WARNING_ID = 0;
    private static boolean streamingVideo = false;
    static int textSize = 0;
    private static TapjoyVideoObject videoData;
    private static boolean videoError = false;
    private static final String videoSecondsText = " seconds";
    private static final String videoWillResumeText = "";
    final String TAPJOY_VIDEO = "VIDEO";
    private boolean allowBackKey = false;
    private boolean clickRequestSuccess = false;
    int deviceScreenDensity;
    int deviceScreenLayoutSize;
    Dialog dialog;
    private boolean dialogShowing = false;
    final Handler mHandler = new Handler();
    final Runnable mUpdateResults = new Runnable(){

        public void run() {
            TapjoyVideoView.this.overlayText.setText((CharSequence)(TapjoyVideoView.videoWillResumeText + TapjoyVideoView.this.getRemainingVideoTime() + TapjoyVideoView.videoSecondsText));
        }
    };
    private TextView overlayText = null;
    private RelativeLayout relativeLayout;
    private int seekTime = 0;
    private boolean sendClick = false;
    private ImageView tapjoyImage;
    private int timeRemaining = 0;
    Timer timer = null;
    private String videoPath = null;
    private VideoView videoView = null;
    private Bitmap watermark;
    private WebView webView;
    private String webviewURL = null;

    static {
        videoError = false;
        streamingVideo = false;
        textSize = 16;
    }

    static /* synthetic */ TapjoyVideoObject access$200() {
        return videoData;
    }

    private int getRemainingVideoTime() {
        int n = (this.videoView.getDuration() - this.videoView.getCurrentPosition()) / 1000;
        if (n < 0) {
            n = 0;
        }
        return n;
    }

    private void initVideoCompletionScreen() {
        this.webView = new WebView((Context)this);
        this.webView.setWebViewClient(new WebViewClient(){

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public boolean shouldOverrideUrlLoading(WebView webView, String string2) {
                TapjoyLog.i("VIDEO", "URL = [" + string2 + "]");
                if (string2.contains((CharSequence)"offer_wall")) {
                    TapjoyLog.i("VIDEO", "back to offers");
                    TapjoyVideoView.this.finish();
                    do {
                        return true;
                        break;
                    } while (true);
                }
                if (string2.contains((CharSequence)"tjvideo")) {
                    TapjoyLog.i("VIDEO", "replay");
                    TapjoyVideoView.this.initVideoView();
                    return true;
                }
                if (string2.contains((CharSequence)"ws.tapjoyads.com")) {
                    TapjoyLog.i("VIDEO", "Open redirecting URL = [" + string2 + "]");
                    webView.loadUrl(string2);
                    return true;
                }
                TapjoyLog.i("VIDEO", "Opening URL in new browser = [" + string2 + "]");
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((String)string2));
                TapjoyVideoView.this.startActivity(intent);
                return true;
            }
        });
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.loadUrl(this.webviewURL);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void initVideoView() {
        this.relativeLayout.removeAllViews();
        this.relativeLayout.setBackgroundColor(-16777216);
        if (this.videoView == null && this.overlayText == null) {
            this.tapjoyImage = new ImageView((Context)this);
            if (this.watermark == null) {
                this.watermark = TapjoyConnectCore.isVideoCacheEnabled() ? BitmapFactory.decodeFile((String)TapjoyVideo.imageTapjoyLocation) : TapjoyVideo.watermark;
            }
            if (this.watermark != null) {
                this.tapjoyImage.setImageBitmap(this.watermark);
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(12);
            layoutParams.addRule(11);
            this.tapjoyImage.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
            this.videoView = new VideoView((Context)this);
            this.videoView.setOnCompletionListener((MediaPlayer.OnCompletionListener)this);
            this.videoView.setOnErrorListener((MediaPlayer.OnErrorListener)this);
            this.videoView.setOnPreparedListener((MediaPlayer.OnPreparedListener)this);
            if (streamingVideo) {
                TapjoyLog.i("VIDEO", "streaming video: " + this.videoPath);
                this.videoView.setVideoURI(Uri.parse((String)this.videoPath));
            } else {
                TapjoyLog.i("VIDEO", "cached video: " + this.videoPath);
                this.videoView.setVideoPath(this.videoPath);
            }
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(13);
            this.videoView.setLayoutParams((ViewGroup.LayoutParams)layoutParams2);
            this.timeRemaining = this.videoView.getDuration() / 1000;
            TapjoyLog.i("VIDEO", "videoView.getDuration(): " + this.videoView.getDuration());
            TapjoyLog.i("VIDEO", "timeRemaining: " + this.timeRemaining);
            this.overlayText = new TextView((Context)this);
            this.overlayText.setTextSize((float)textSize);
            this.overlayText.setTypeface(Typeface.create((String)"default", (int)1), 1);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams3.addRule(12);
            this.overlayText.setLayoutParams((ViewGroup.LayoutParams)layoutParams3);
        }
        this.startVideo();
        this.relativeLayout.addView((View)this.videoView);
        this.relativeLayout.addView((View)this.tapjoyImage);
        this.relativeLayout.addView((View)this.overlayText);
    }

    private void showVideoCompletionScreen() {
        this.relativeLayout.removeAllViews();
        this.relativeLayout.addView((View)this.webView, -1, -1);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void startVideo() {
        this.videoView.requestFocus();
        if (this.dialogShowing) {
            this.videoView.seekTo(this.seekTime);
            TapjoyLog.i("VIDEO", "dialog is showing -- don't start");
        } else {
            TapjoyLog.i("VIDEO", "start");
            this.videoView.seekTo(0);
            this.videoView.start();
        }
        if (this.timer != null) {
            this.timer.cancel();
        }
        this.timer = new Timer();
        this.timer.schedule((TimerTask)new RemainingTime(), 500L, 100L);
        this.initVideoCompletionScreen();
        this.clickRequestSuccess = false;
        if (this.sendClick) {
            new Thread(new Runnable(){

                public void run() {
                    TapjoyLog.i("VIDEO", "SENDING CLICK...");
                    String string2 = new TapjoyURLConnection().connectToURL(TapjoyVideoView.access$200().clickURL);
                    if (string2 != null && string2.contains((CharSequence)"OK")) {
                        TapjoyLog.i("VIDEO", "CLICK REQUEST SUCCESS!");
                        TapjoyVideoView.this.clickRequestSuccess = true;
                    }
                }
            }).start();
            this.sendClick = false;
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        TapjoyLog.i("VIDEO", "onCompletion");
        if (this.timer != null) {
            this.timer.cancel();
        }
        this.showVideoCompletionScreen();
        if (!videoError) {
            TapjoyVideo.getVideoNotifier().videoComplete();
            new Thread(new Runnable(){

                public void run() {
                    if (TapjoyVideoView.this.clickRequestSuccess) {
                        TapjoyConnectCore.getInstance().actionComplete(TapjoyVideoView.access$200().offerID);
                    }
                }
            }).start();
        }
        videoError = false;
        this.allowBackKey = true;
    }

    protected void onCreate(Bundle bundle) {
        TapjoyLog.i("VIDEO", "onCreate");
        super.onCreate(bundle);
        if (bundle != null) {
            TapjoyLog.i("VIDEO", "*** Loading saved data from bundle ***");
            this.seekTime = bundle.getInt(BUNDLE_SEEK_TIME);
            this.dialogShowing = bundle.getBoolean(BUNDLE_DIALOG_SHOWING);
        }
        TapjoyLog.i("VIDEO", "dialogShowing: " + this.dialogShowing + ", seekTime: " + this.seekTime);
        this.sendClick = true;
        streamingVideo = false;
        if (TapjoyVideo.getInstance() == null) {
            TapjoyLog.i("VIDEO", "null video");
            this.finish();
            return;
        }
        videoData = TapjoyVideo.getInstance().getCurrentVideoData();
        this.videoPath = TapjoyVideoView.videoData.dataLocation;
        this.webviewURL = TapjoyVideoView.videoData.webviewURL;
        if (this.videoPath == null || this.videoPath.length() == 0) {
            TapjoyLog.i("VIDEO", "no cached video, try streaming video at location: " + TapjoyVideoView.videoData.videoURL);
            this.videoPath = TapjoyVideoView.videoData.videoURL;
            streamingVideo = true;
        }
        TapjoyLog.i("VIDEO", "videoPath: " + this.videoPath);
        this.requestWindowFeature(1);
        this.relativeLayout = new RelativeLayout((Context)this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.relativeLayout.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        this.setContentView((View)this.relativeLayout);
        if (Integer.parseInt((String)Build.VERSION.SDK) > 3) {
            this.deviceScreenLayoutSize = new TapjoyDisplayMetricsUtil((Context)this).getScreenLayoutSize();
            TapjoyLog.i("VIDEO", "deviceScreenLayoutSize: " + this.deviceScreenLayoutSize);
            if (this.deviceScreenLayoutSize == 4) {
                textSize = 32;
            }
        }
        TapjoyLog.i("VIDEO", "textSize: " + textSize);
        this.initVideoView();
        TapjoyLog.i("VIDEO", "onCreate DONE");
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected Dialog onCreateDialog(int n) {
        TapjoyLog.i("VIDEO", "dialog onCreateDialog");
        if (!this.dialogShowing) {
            return this.dialog;
        }
        switch (n) {
            default: {
                this.dialog = null;
                do {
                    return this.dialog;
                    break;
                } while (true);
            }
            case 0: 
        }
        this.dialog = new AlertDialog.Builder((Context)this).setTitle((CharSequence)"Cancel Video?").setMessage((CharSequence)"Currency will not be awarded, are you sure you want to cancel the video?").setNegativeButton((CharSequence)"End", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                TapjoyVideoView.this.finish();
            }
        }).setPositiveButton((CharSequence)"Resume", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                dialogInterface.dismiss();
                TapjoyVideoView.this.videoView.seekTo(TapjoyVideoView.this.seekTime);
                TapjoyVideoView.this.videoView.start();
                TapjoyVideoView.this.dialogShowing = false;
                TapjoyLog.i("VIDEO", "RESUME VIDEO time: " + TapjoyVideoView.this.seekTime);
                TapjoyLog.i("VIDEO", "currentPosition: " + TapjoyVideoView.this.videoView.getCurrentPosition());
                TapjoyLog.i("VIDEO", "duration: " + TapjoyVideoView.this.videoView.getDuration() + ", elapsed: " + (TapjoyVideoView.this.videoView.getDuration() - TapjoyVideoView.this.videoView.getCurrentPosition()));
            }
        }).create();
        this.dialog.setOnCancelListener(new DialogInterface.OnCancelListener(){

            public void onCancel(DialogInterface dialogInterface) {
                TapjoyLog.i("VIDEO", "dialog onCancel");
                dialogInterface.dismiss();
                TapjoyVideoView.this.videoView.seekTo(TapjoyVideoView.this.seekTime);
                TapjoyVideoView.this.videoView.start();
                TapjoyVideoView.this.dialogShowing = false;
            }
        });
        this.dialog.show();
        this.dialogShowing = true;
        return this.dialog;
    }

    public boolean onError(MediaPlayer mediaPlayer, int n, int n2) {
        videoError = true;
        TapjoyLog.i("VIDEO", "onError");
        TapjoyVideo.getVideoNotifier().videoError(3);
        this.allowBackKey = true;
        if (this.timer != null) {
            this.timer.cancel();
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onKeyDown(int n, KeyEvent keyEvent) {
        if (n != 4) return super.onKeyDown(n, keyEvent);
        if (!this.allowBackKey) {
            this.seekTime = this.videoView.getCurrentPosition();
            this.videoView.pause();
            this.dialogShowing = true;
            this.showDialog(0);
            TapjoyLog.i("VIDEO", "PAUSE VIDEO time: " + this.seekTime);
            TapjoyLog.i("VIDEO", "currentPosition: " + this.videoView.getCurrentPosition());
            TapjoyLog.i("VIDEO", "duration: " + this.videoView.getDuration() + ", elapsed: " + (this.videoView.getDuration() - this.videoView.getCurrentPosition()));
            return true;
        }
        if (!this.videoView.isPlaying()) return super.onKeyDown(n, keyEvent);
        {
            this.videoView.stopPlayback();
            this.showVideoCompletionScreen();
            if (this.timer == null) return true;
            {
                this.timer.cancel();
                return true;
            }
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.videoView.isPlaying()) {
            TapjoyLog.i("VIDEO", "onPause");
            this.seekTime = this.videoView.getCurrentPosition();
            TapjoyLog.i("VIDEO", "seekTime: " + this.seekTime);
        }
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        TapjoyLog.i("VIDEO", "onPrepared");
    }

    protected void onResume() {
        TapjoyLog.i("VIDEO", "onResume");
        super.onResume();
        this.setRequestedOrientation(0);
        if (this.seekTime > 0) {
            TapjoyLog.i("VIDEO", "seekTime: " + this.seekTime);
            this.videoView.seekTo(this.seekTime);
            if (!this.dialogShowing || this.dialog == null || !this.dialog.isShowing()) {
                this.videoView.start();
            }
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        TapjoyLog.i("VIDEO", "*** onSaveInstanceState ***");
        TapjoyLog.i("VIDEO", "dialogShowing: " + this.dialogShowing + ", seekTime: " + this.seekTime);
        bundle.putBoolean(BUNDLE_DIALOG_SHOWING, this.dialogShowing);
        bundle.putInt(BUNDLE_SEEK_TIME, this.seekTime);
    }

    public void onWindowFocusChanged(boolean bl) {
        TapjoyLog.i("VIDEO", "onWindowFocusChanged");
        super.onWindowFocusChanged(bl);
    }

    private class RemainingTime
    extends TimerTask {
        private RemainingTime() {
        }

        public void run() {
            TapjoyVideoView.this.mHandler.post(TapjoyVideoView.this.mUpdateResults);
        }
    }

}

