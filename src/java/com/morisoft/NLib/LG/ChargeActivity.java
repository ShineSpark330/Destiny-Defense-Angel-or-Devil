/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.ProgressDialog
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.ServiceConnection
 *  android.os.Bundle
 *  android.os.IBinder
 *  android.os.RemoteException
 *  android.util.Log
 *  android.view.View
 *  android.widget.LinearLayout
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.System
 *  java.lang.Thread
 */
package com.morisoft.NLib.LG;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import com.lguplus.common.bill.IBillSocket;
import com.morisoft.NLib.ConstInterface;
import com.morisoft.NLib.Device;
import com.morisoft.NLib.Native;
import com.morisoft.NLib.NativeCall;
import com.morisoft.NLib.NativePopUp;
import com.morisoft.NLib.Util;
import org.cocos2dx.lib.Cocos2dxActivity;

public class ChargeActivity
extends Activity
implements ConstInterface,
Runnable {
    static String TAG = "LGinAppBillCom";
    private ChargeActivity m_Activity;
    private String m_AppID;
    private String m_ChargeID;
    private ChargeServiceConnection m_ChargeServiceCon;
    private String m_GameID;
    private String m_IP;
    private String m_ItemName;
    private int m_PORT;
    private ProgressDialog m_pd;
    private byte[] m_recvData;
    private IBillSocket m_service;
    private String m_strMsg;

    private static void DEBUG_LOG(String string2) {
        Cocos2dxActivity.DEBUG_LOG(TAG, string2);
    }

    static /* synthetic */ void access$1(ChargeActivity chargeActivity, IBillSocket iBillSocket) {
        chargeActivity.m_service = iBillSocket;
    }

    private void close() {
        try {
            if (this.m_service != null) {
                this.m_service.close();
                this.m_service = null;
                ChargeActivity.DEBUG_LOG("LG BillCom Close Success");
                return;
            }
            ChargeActivity.DEBUG_LOG("LG BillCom Close Fail:\n not Connect Billcom");
            return;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            ChargeActivity.DEBUG_LOG("LG BillCom Close :\n" + exception.toString());
            return;
        }
    }

    private boolean connect() {
        block3 : {
            try {
                if (!this.m_service.connect(this.m_AppID, this.m_IP, this.m_PORT)) break block3;
                ChargeActivity.DEBUG_LOG("LG BillCom Connect success");
                return true;
            }
            catch (Exception exception) {
                exception.printStackTrace();
                ChargeActivity.DEBUG_LOG("LG BillCom Connect Fail : \n " + exception.toString());
                return false;
            }
        }
        ChargeActivity.DEBUG_LOG("LG BillCom Connect Fail");
        return false;
    }

    private boolean read() {
        if (this.m_service != null) {
            int n;
            block5 : {
                try {
                    n = this.m_service.readBytes(this.m_recvData);
                    if (n >= 0) break block5;
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                    ChargeActivity.DEBUG_LOG("LG BillCom read Fail :\n" + exception.toString());
                    this.close();
                    return false;
                }
                ChargeActivity.DEBUG_LOG("LG BillCom read Fail\n RecvLen:" + n);
                return false;
            }
            ChargeActivity.DEBUG_LOG("LG BillCom read Success\n RecvLen:" + n);
            return true;
        }
        ChargeActivity.DEBUG_LOG("LG BillCom write Fail :\nNot Connect");
        return false;
    }

    private void releaseService() {
        if (this.m_ChargeServiceCon != null) {
            this.close();
            this.unbindService((ServiceConnection)this.m_ChargeServiceCon);
            this.m_ChargeServiceCon = null;
        }
    }

    private boolean runService() {
        block4 : {
            try {
                if (this.m_ChargeServiceCon == null) {
                    this.m_ChargeServiceCon = new ChargeServiceConnection();
                    boolean bl = this.bindService(new Intent(IBillSocket.class.getName()), (ServiceConnection)this.m_ChargeServiceCon, 1);
                    if (bl) {
                        return true;
                    }
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
                this.m_pd.dismiss();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(this.m_ItemName);
                stringBuffer.append(" \uad6c\uc785\uc5d0  \uc2e4\ud328\ud558\uc600\uc2b5\ub2c8\ub2e4.\n");
                stringBuffer.append("\uc7a0\uc2dc\ud6c4 \ub2e4\uc2dc \uc2dc\ub3c4\ud574 \uc8fc\uc2dc\uae30 \ubc14\ub78d\ub2c8\ub2e4.");
                this.finish();
                if (this.m_strMsg == null) break block4;
                NativePopUp.createDialog(4, null, stringBuffer.toString());
            }
        }
        this.releaseService();
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean write() {
        if (this.m_service == null) {
            ChargeActivity.DEBUG_LOG("LG BillCom write Fail :\nNot Connect");
            return false;
        }
        try {
            byte[] arrby;
            if (NativeCall.sendPacketData == null) {
                byte[] arrby2 = this.m_GameID.getBytes();
                byte[] arrby3 = this.m_ChargeID.getBytes();
                byte[] arrby4 = Device.getPhoneNumber().getBytes();
                arrby = Util.intToBytes(100, Util.intToBytes(101, Util.intToBytes(32, new byte[60], 0), 4), 8);
                System.arraycopy((Object)arrby4, (int)0, (Object)arrby, (int)12, (int)arrby4.length);
                System.arraycopy((Object)arrby2, (int)0, (Object)arrby, (int)28, (int)arrby2.length);
                System.arraycopy((Object)arrby3, (int)0, (Object)arrby, (int)44, (int)arrby3.length);
            } else {
                byte[] arrby5 = NativeCall.sendPacketData;
                arrby = new byte[arrby5.length];
                System.arraycopy((Object)arrby5, (int)0, (Object)arrby, (int)0, (int)arrby5.length);
            }
            this.m_service.writeByOffset(arrby, 0, arrby.length);
            ChargeActivity.DEBUG_LOG("LG BillCom write Success");
            return true;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            ChargeActivity.DEBUG_LOG("LG BillCom write Fail:" + exception.toString());
            this.close();
            return true;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(1);
        this.m_pd = null;
        this.m_AppID = null;
        this.m_ItemName = null;
        this.m_ChargeID = null;
        this.m_recvData = new byte[1024];
        Bundle bundle2 = this.getIntent().getExtras();
        if (bundle2 != null) {
            this.m_AppID = bundle2.getString("AppID");
            this.m_ItemName = bundle2.getString("ItemName");
            this.m_IP = bundle2.getString("IP");
            this.m_PORT = bundle2.getInt("PORT");
            this.setContentView((View)new LinearLayout((Context)this));
            this.m_Activity = this;
            this.m_pd = ProgressDialog.show((Context)this, (CharSequence)this.m_ItemName, (CharSequence)"charge processing...", (boolean)true, (boolean)false);
            this.runService();
            return;
        }
        this.finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.releaseService();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void run() {
        block10 : {
            block6 : {
                block7 : {
                    block8 : {
                        block9 : {
                            if (!this.connect()) break block6;
                            if (!this.write()) break block7;
                            if (!this.read()) break block8;
                            int n = Util.bytesToInt(this.m_recvData, 4);
                            int n2 = Util.bytesToInt(this.m_recvData, 8);
                            ChargeActivity.DEBUG_LOG("packet type: " + n);
                            ChargeActivity.DEBUG_LOG("result: " + n2);
                            if (n != 101 && NativeCall.sendPacketData == null) break block9;
                            switch (n2) {
                                default: {
                                    Native.itemChargeCancel(-268369920 + n2);
                                    break block10;
                                }
                                case 1: {
                                    Native.itemChargeComplete();
                                    this.releaseService();
                                    this.m_recvData = null;
                                    this.m_pd.dismiss();
                                    this.finish();
                                    return;
                                }
                            }
                        }
                        Native.itemChargeError(-268369908);
                        break block10;
                    }
                    Native.itemChargeError(-83886077);
                    break block10;
                }
                Native.itemChargeError(-83886078);
                break block10;
            }
            Native.itemChargeError(-83886079);
        }
        try {
            this.m_strMsg = this.m_service.getLastErrorMsg();
        }
        catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }
        Log.d((String)"MorisoftDebug", (String)("run: m_strMsg: " + this.m_strMsg));
        this.releaseService();
        this.m_recvData = null;
        this.m_pd.dismiss();
        this.finish();
        if (this.m_strMsg != null) {
            NativePopUp.createDialog(4, null, this.m_strMsg);
        }
    }

    class ChargeServiceConnection
    implements ServiceConnection {
        ChargeServiceConnection() {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (ChargeActivity.this.m_service == null) {
                ChargeActivity.access$1(ChargeActivity.this, IBillSocket.Stub.asInterface(iBinder));
            }
            new Thread((Runnable)ChargeActivity.this.m_Activity).start();
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

}

