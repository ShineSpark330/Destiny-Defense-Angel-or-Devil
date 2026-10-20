/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.os.Binder
 *  android.os.IBinder
 *  android.os.IInterface
 *  android.os.Parcel
 *  android.os.RemoteException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.lguplus.common.bill;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBillSocket
extends IInterface {
    public void close() throws RemoteException;

    public boolean connect(String var1, String var2, int var3) throws RemoteException;

    public String getLastErrorMsg() throws RemoteException;

    public int readByOffset(byte[] var1, int var2, int var3) throws RemoteException;

    public int readBytes(byte[] var1) throws RemoteException;

    public boolean writeByOffset(byte[] var1, int var2, int var3) throws RemoteException;

    public boolean writeBytes(byte[] var1) throws RemoteException;

    public static abstract class Stub
    extends Binder
    implements IBillSocket {
        private static final String DESCRIPTOR = "com.lguplus.common.bill.IBillSocket";
        static final int TRANSACTION_close = 2;
        static final int TRANSACTION_connect = 1;
        static final int TRANSACTION_getLastErrorMsg = 7;
        static final int TRANSACTION_readByOffset = 4;
        static final int TRANSACTION_readBytes = 3;
        static final int TRANSACTION_writeByOffset = 6;
        static final int TRANSACTION_writeBytes = 5;

        public Stub() {
            this.attachInterface((IInterface)this, DESCRIPTOR);
        }

        public static IBillSocket asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterface != null && iInterface instanceof IBillSocket) {
                return (IBillSocket)iInterface;
            }
            return new Proxy(iBinder);
        }

        public IBinder asBinder() {
            return this;
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean onTransact(int n, Parcel parcel, Parcel parcel2, int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                case 1: {
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean bl = this.connect(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    int n3 = 0;
                    if (bl) {
                        n3 = 1;
                    }
                    parcel2.writeInt(n3);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface(DESCRIPTOR);
                    this.close();
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface(DESCRIPTOR);
                    int n4 = parcel.readInt();
                    byte[] arrby = n4 < 0 ? (byte[])null : new byte[n4];
                    int n5 = this.readBytes(arrby);
                    parcel2.writeNoException();
                    parcel2.writeInt(n5);
                    parcel2.writeByteArray(arrby);
                    return true;
                }
                case 4: {
                    parcel.enforceInterface(DESCRIPTOR);
                    int n6 = parcel.readInt();
                    byte[] arrby = n6 < 0 ? (byte[])null : new byte[n6];
                    int n7 = this.readByOffset(arrby, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(n7);
                    parcel2.writeByteArray(arrby);
                    return true;
                }
                case 5: {
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean bl = this.writeBytes(parcel.createByteArray());
                    parcel2.writeNoException();
                    int n8 = 0;
                    if (bl) {
                        n8 = 1;
                    }
                    parcel2.writeInt(n8);
                    return true;
                }
                case 6: {
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean bl = this.writeByOffset(parcel.createByteArray(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    int n9 = 0;
                    if (bl) {
                        n9 = 1;
                    }
                    parcel2.writeInt(n9);
                    return true;
                }
                case 7: 
            }
            parcel.enforceInterface(DESCRIPTOR);
            String string2 = this.getLastErrorMsg();
            parcel2.writeNoException();
            parcel2.writeString(string2);
            return true;
        }

        private static class Proxy
        implements IBillSocket {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override
            public void close() throws RemoteException {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public boolean connect(String string2, String string3, int n) throws RemoteException {
                boolean bl = true;
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcel.writeString(string2);
                    parcel.writeString(string3);
                    parcel.writeInt(n);
                    this.mRemote.transact(1, parcel, parcel2, 0);
                    parcel2.readException();
                    int n2 = parcel2.readInt();
                    if (n2 == 0) {
                        bl = false;
                    }
                    return bl;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override
            public String getLastErrorMsg() throws RemoteException {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, parcel, parcel2, 0);
                    parcel2.readException();
                    String string2 = parcel2.readString();
                    return string2;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public int readByOffset(byte[] arrby, int n, int n2) throws RemoteException {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (arrby == null) {
                        parcel.writeInt(-1);
                    } else {
                        parcel.writeInt(arrby.length);
                    }
                    parcel.writeInt(n);
                    parcel.writeInt(n2);
                    this.mRemote.transact(4, parcel, parcel2, 0);
                    parcel2.readException();
                    int n3 = parcel2.readInt();
                    parcel2.readByteArray(arrby);
                    return n3;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public int readBytes(byte[] arrby) throws RemoteException {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (arrby == null) {
                        parcel.writeInt(-1);
                    } else {
                        parcel.writeInt(arrby.length);
                    }
                    this.mRemote.transact(3, parcel, parcel2, 0);
                    parcel2.readException();
                    int n = parcel2.readInt();
                    parcel2.readByteArray(arrby);
                    return n;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            @Override
            public boolean writeByOffset(byte[] arrby, int n, int n2) throws RemoteException {
                boolean bl;
                Parcel parcel;
                Parcel parcel2;
                block2 : {
                    parcel = Parcel.obtain();
                    parcel2 = Parcel.obtain();
                    try {
                        parcel.writeInterfaceToken(Stub.DESCRIPTOR);
                        parcel.writeByteArray(arrby);
                        parcel.writeInt(n);
                        parcel.writeInt(n2);
                        this.mRemote.transact(6, parcel, parcel2, 0);
                        parcel2.readException();
                        int n3 = parcel2.readInt();
                        bl = false;
                        if (n3 == 0) break block2;
                        bl = true;
                    }
                    catch (Throwable throwable) {
                        parcel2.recycle();
                        parcel.recycle();
                        throw throwable;
                    }
                }
                parcel2.recycle();
                parcel.recycle();
                return bl;
            }

            @Override
            public boolean writeBytes(byte[] arrby) throws RemoteException {
                boolean bl;
                Parcel parcel;
                Parcel parcel2;
                block2 : {
                    parcel = Parcel.obtain();
                    parcel2 = Parcel.obtain();
                    try {
                        parcel.writeInterfaceToken(Stub.DESCRIPTOR);
                        parcel.writeByteArray(arrby);
                        this.mRemote.transact(5, parcel, parcel2, 0);
                        parcel2.readException();
                        int n = parcel2.readInt();
                        bl = false;
                        if (n == 0) break block2;
                        bl = true;
                    }
                    catch (Throwable throwable) {
                        parcel2.recycle();
                        parcel.recycle();
                        throw throwable;
                    }
                }
                parcel2.recycle();
                parcel.recycle();
                return bl;
            }
        }

    }

}

