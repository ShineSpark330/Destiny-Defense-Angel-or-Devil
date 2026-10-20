/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 *  android.content.DialogInterface$OnClickListener
 *  android.content.DialogInterface$OnKeyListener
 *  android.os.Handler
 *  android.text.Html
 *  android.util.AttributeSet
 *  android.view.KeyEvent
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  java.lang.CharSequence
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.Thread
 */
package com.morisoft.NLib;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.text.Html;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.morisoft.NLib.Native;
import com.morisoft.NLib.NativeCall;
import org.cocos2dx.lib.Cocos2dxActivity;

public class NativePopUp {
    public static final int AD_PURCHASE = 7;
    public static final int AD_PURCHASE_CANNOT_CONNECT = 5;
    public static final int AD_PURCHASE_NOT_SUPPORTED = 6;
    public static final int DEFAULT = 0;
    public static final int EXIT = -16777216;
    public static final int KAF_INITIALIZE = 2;
    public static final int KT_CHARGE_MSG = 3;
    public static final int LG_BILLCOM_MSG = 4;
    private static String TAG;
    private static Cocos2dxActivity m_Activity;
    public static boolean m_bDialogTransparent;
    public static int m_idDialogBackground;
    static ProgressBar m_progress;
    static TextView m_progressTextview;
    public static boolean s_bArmCheck;
    public static String strDialogBtnAssist;
    public static String strDialogBtnCancel;
    public static String strDialogBtnOK;
    public static String strDialogMsg;
    public static String strDialogTitle;

    static {
        s_bArmCheck = true;
        TAG = "NativePopUp";
        m_Activity = null;
        strDialogTitle = null;
        strDialogMsg = null;
        strDialogBtnOK = null;
        strDialogBtnCancel = null;
        strDialogBtnAssist = null;
        m_progress = null;
        m_progressTextview = null;
    }

    private static void DEBUG_LOG(String string2) {
        Cocos2dxActivity.DEBUG_LOG(TAG, string2);
    }

    public static native void clickPopUpButton(int var0);

    public static void createDialog(int n, String string2, String string3) {
        NativePopUp.createDialog(n, string2, string3, "\ud655\uc778", "\ucde8\uc18c", null, false, -1);
    }

    public static void createDialog(int n, String string2, String string3, String string4, String string5, String string6, boolean bl, int n2) {
        if (!s_bArmCheck) {
            return;
        }
        strDialogTitle = string2;
        strDialogMsg = string3;
        strDialogBtnOK = string4;
        strDialogBtnCancel = string5;
        strDialogBtnAssist = string6;
        m_bDialogTransparent = bl;
        m_idDialogBackground = n2;
        if (m_idDialogBackground != -1) {
            m_bDialogTransparent = true;
        }
        NativePopUp.m_Activity.m_Handler.post(new Runnable(){

            public void run() {
                m_Activity.showDialog(val$id);
            }
        });
    }

    public static AlertDialog createPopUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)m_Activity).setTitle((CharSequence)strDialogTitle).setMessage((CharSequence)strDialogMsg);
        builder.setOnKeyListener(new DialogInterface.OnKeyListener(){

            public boolean onKey(DialogInterface dialogInterface, int n, KeyEvent keyEvent) {
                if (n != 4) {
                    NativePopUp.clickPopUpButton(0);
                    NativePopUp.releaseDialog(0);
                }
                return false;
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener(){

            public void onCancel(DialogInterface dialogInterface) {
                NativePopUp.clickPopUpButton(0);
                NativePopUp.releaseDialog(0);
            }
        });
        if (strDialogBtnOK != null) {
            builder.setPositiveButton((CharSequence)strDialogBtnOK, new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialogInterface, int n) {
                    NativePopUp.clickPopUpButton(1);
                    NativePopUp.releaseDialog(0);
                }
            });
        }
        if (strDialogBtnCancel != null) {
            builder.setNegativeButton((CharSequence)strDialogBtnCancel, new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialogInterface, int n) {
                    NativePopUp.clickPopUpButton(2);
                    NativePopUp.releaseDialog(0);
                }
            });
        }
        if (strDialogBtnAssist != null) {
            builder.setNeutralButton((CharSequence)strDialogBtnAssist, new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialogInterface, int n) {
                    NativePopUp.clickPopUpButton(3);
                    NativePopUp.releaseDialog(0);
                }
            });
        }
        return builder.show();
    }

    public static void hideProgressBar() {
        if (m_progress != null) {
            NativePopUp.m_Activity.m_Handler.post(new Runnable(){

                public void run() {
                    NativePopUp.access$0().m_linearLayout.removeView((View)NativePopUp.m_progress);
                    NativePopUp.access$0().m_linearLayout.removeView((View)NativePopUp.m_progressTextview);
                    NativePopUp.access$0().m_linearLayout.setBackgroundColor(0);
                    NativePopUp.m_progress = null;
                    NativePopUp.m_progressTextview = null;
                    NativePopUp.nativeHidePregressBar();
                }
            });
        }
    }

    public static void initialize(Cocos2dxActivity cocos2dxActivity) {
        m_Activity = cocos2dxActivity;
    }

    public static boolean isShowProgressBar() {
        return m_progress != null;
    }

    public static native void nativeHidePregressBar();

    public static AlertDialog onCreateExit() {
        NativePopUp.DEBUG_LOG("showExit()");
        return new AlertDialog.Builder((Context)m_Activity).setTitle((CharSequence)Html.fromHtml((String)strDialogTitle)).setMessage((CharSequence)strDialogMsg).setPositiveButton((CharSequence)strDialogBtnOK, new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                NativeCall.appExit();
            }
        }).setNegativeButton((CharSequence)strDialogBtnCancel, new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                NativePopUp.releaseDialog(-16777216);
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener(){

            public void onCancel(DialogInterface dialogInterface) {
                NativePopUp.releaseDialog(-16777216);
            }
        }).setOnKeyListener(new DialogInterface.OnKeyListener(){

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public boolean onKey(DialogInterface dialogInterface, int n, KeyEvent keyEvent) {
                if (n != 4) {
                    NativePopUp.releaseDialog(-16777216);
                    do {
                        return false;
                        break;
                    } while (true);
                }
                Native.timerResume();
                return false;
            }
        }).show();
    }

    public static void releaseDialog(int n) {
        NativePopUp.DEBUG_LOG("hide()");
        m_Activity.dismissDialog(n);
        m_Activity.removeDialog(n);
        Native.timerResume();
    }

    public static void showArmDialog(final String string2, String string3) {
        s_bArmCheck = false;
        Cocos2dxActivity.DEBUG_LOG(string2, "showArmDialog: " + string3);
        Cocos2dxActivity.m_Activity.m_Handler.post(new Runnable(){

            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder((Context)m_Activity);
                builder.setTitle((CharSequence)"Protection Result");
                builder.setMessage((CharSequence)String.this);
                builder.setPositiveButton(17039370, new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialogInterface, int n) {
                        Cocos2dxActivity.DEBUG_LOG(string2, "onClick");
                        dialogInterface.dismiss();
                        NativeCall.appExit();
                    }
                });
                builder.setOnKeyListener(new DialogInterface.OnKeyListener(){

                    public boolean onKey(DialogInterface dialogInterface, int n, KeyEvent keyEvent) {
                        Cocos2dxActivity.DEBUG_LOG(string2, "onKey keyCode: " + n);
                        if (n != 4) {
                            dialogInterface.dismiss();
                            NativeCall.appExit();
                        }
                        return false;
                    }
                });
                builder.setOnCancelListener(new DialogInterface.OnCancelListener(){

                    public void onCancel(DialogInterface dialogInterface) {
                        Cocos2dxActivity.DEBUG_LOG(string2, "onCancel");
                        dialogInterface.dismiss();
                        NativeCall.appExit();
                    }
                });
                builder.create().show();
            }

        });
    }

    public static void showExit(String string2, String string3, String string4, String string5) {
        NativePopUp.createDialog(-16777216, "<STRONG><FONT color=#ff0000> " + string2 + "</FONT></STRONG>", string3, string4, string5, null, false, -1);
    }

    public static void showPopUp(String string2, String string3, String string4, String string5, String string6, boolean bl, int n) {
        NativePopUp.createDialog(0, string2, string3, string4, string5, string6, bl, n);
    }

    public static boolean showProgressBar(final String string2, int n, final int n2, final int n3) {
        if (m_progress != null) {
            return true;
        }
        NativePopUp.m_Activity.m_Handler.post(new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            public void run() {
                if (val$barSize == 0) {
                    NativePopUp.m_progress = new ProgressBar((Context)m_Activity, null, 16842873);
                } else if (val$barSize == 1) {
                    NativePopUp.m_progress = new ProgressBar((Context)m_Activity, null, 16842871);
                } else if (val$barSize == 2) {
                    NativePopUp.m_progress = new ProgressBar((Context)m_Activity, null, 16842874);
                }
                NativePopUp.m_progress.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-2, -2));
                NativePopUp.access$0().m_linearLayout.setBackgroundColor(Integer.MIN_VALUE);
                NativePopUp.access$0().m_linearLayout.addView((View)NativePopUp.m_progress);
                if (string2 != null) {
                    NativePopUp.m_progressTextview = new TextView((Context)m_Activity);
                    NativePopUp.m_progressTextview.setText((CharSequence)string2);
                    NativePopUp.m_progressTextview.setTextSize((float)n2);
                    NativePopUp.m_progressTextview.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-2, -2));
                    NativePopUp.access$0().m_linearLayout.addView((View)NativePopUp.m_progressTextview);
                }
                if (n3 > 0) {
                    new Thread(new Runnable(){

                        public void run() {
                            try {
                                Thread.sleep((long)(1000 * n3));
                                NativePopUp.hideProgressBar();
                                return;
                            }
                            catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                                return;
                            }
                        }
                    }).start();
                }
            }

        });
        return true;
    }

}

