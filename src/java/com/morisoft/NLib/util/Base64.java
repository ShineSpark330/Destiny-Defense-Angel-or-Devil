/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.AssertionError
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package com.morisoft.NLib.util;

import com.morisoft.NLib.util.Base64DecoderException;

public class Base64 {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final byte[] ALPHABET;
    private static final byte[] DECODABET;
    public static final boolean DECODE = false;
    public static final boolean ENCODE = true;
    private static final byte EQUALS_SIGN = 61;
    private static final byte EQUALS_SIGN_ENC = -1;
    private static final byte NEW_LINE = 10;
    private static final byte[] WEBSAFE_ALPHABET;
    private static final byte[] WEBSAFE_DECODABET;
    private static final byte WHITE_SPACE_ENC = -5;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl = !Base64.class.desiredAssertionStatus();
        $assertionsDisabled = bl;
        ALPHABET = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        WEBSAFE_ALPHABET = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
        byte[] arrby = new byte[128];
        arrby[0] = -9;
        arrby[1] = -9;
        arrby[2] = -9;
        arrby[3] = -9;
        arrby[4] = -9;
        arrby[5] = -9;
        arrby[6] = -9;
        arrby[7] = -9;
        arrby[8] = -9;
        arrby[9] = -5;
        arrby[10] = -5;
        arrby[11] = -9;
        arrby[12] = -9;
        arrby[13] = -5;
        arrby[14] = -9;
        arrby[15] = -9;
        arrby[16] = -9;
        arrby[17] = -9;
        arrby[18] = -9;
        arrby[19] = -9;
        arrby[20] = -9;
        arrby[21] = -9;
        arrby[22] = -9;
        arrby[23] = -9;
        arrby[24] = -9;
        arrby[25] = -9;
        arrby[26] = -9;
        arrby[27] = -9;
        arrby[28] = -9;
        arrby[29] = -9;
        arrby[30] = -9;
        arrby[31] = -9;
        arrby[32] = -5;
        arrby[33] = -9;
        arrby[34] = -9;
        arrby[35] = -9;
        arrby[36] = -9;
        arrby[37] = -9;
        arrby[38] = -9;
        arrby[39] = -9;
        arrby[40] = -9;
        arrby[41] = -9;
        arrby[42] = -9;
        arrby[43] = 62;
        arrby[44] = -9;
        arrby[45] = -9;
        arrby[46] = -9;
        arrby[47] = 63;
        arrby[48] = 52;
        arrby[49] = 53;
        arrby[50] = 54;
        arrby[51] = 55;
        arrby[52] = 56;
        arrby[53] = 57;
        arrby[54] = 58;
        arrby[55] = 59;
        arrby[56] = 60;
        arrby[57] = 61;
        arrby[58] = -9;
        arrby[59] = -9;
        arrby[60] = -9;
        arrby[61] = -1;
        arrby[62] = -9;
        arrby[63] = -9;
        arrby[64] = -9;
        arrby[66] = 1;
        arrby[67] = 2;
        arrby[68] = 3;
        arrby[69] = 4;
        arrby[70] = 5;
        arrby[71] = 6;
        arrby[72] = 7;
        arrby[73] = 8;
        arrby[74] = 9;
        arrby[75] = 10;
        arrby[76] = 11;
        arrby[77] = 12;
        arrby[78] = 13;
        arrby[79] = 14;
        arrby[80] = 15;
        arrby[81] = 16;
        arrby[82] = 17;
        arrby[83] = 18;
        arrby[84] = 19;
        arrby[85] = 20;
        arrby[86] = 21;
        arrby[87] = 22;
        arrby[88] = 23;
        arrby[89] = 24;
        arrby[90] = 25;
        arrby[91] = -9;
        arrby[92] = -9;
        arrby[93] = -9;
        arrby[94] = -9;
        arrby[95] = -9;
        arrby[96] = -9;
        arrby[97] = 26;
        arrby[98] = 27;
        arrby[99] = 28;
        arrby[100] = 29;
        arrby[101] = 30;
        arrby[102] = 31;
        arrby[103] = 32;
        arrby[104] = 33;
        arrby[105] = 34;
        arrby[106] = 35;
        arrby[107] = 36;
        arrby[108] = 37;
        arrby[109] = 38;
        arrby[110] = 39;
        arrby[111] = 40;
        arrby[112] = 41;
        arrby[113] = 42;
        arrby[114] = 43;
        arrby[115] = 44;
        arrby[116] = 45;
        arrby[117] = 46;
        arrby[118] = 47;
        arrby[119] = 48;
        arrby[120] = 49;
        arrby[121] = 50;
        arrby[122] = 51;
        arrby[123] = -9;
        arrby[124] = -9;
        arrby[125] = -9;
        arrby[126] = -9;
        arrby[127] = -9;
        DECODABET = arrby;
        byte[] arrby2 = new byte[128];
        arrby2[0] = -9;
        arrby2[1] = -9;
        arrby2[2] = -9;
        arrby2[3] = -9;
        arrby2[4] = -9;
        arrby2[5] = -9;
        arrby2[6] = -9;
        arrby2[7] = -9;
        arrby2[8] = -9;
        arrby2[9] = -5;
        arrby2[10] = -5;
        arrby2[11] = -9;
        arrby2[12] = -9;
        arrby2[13] = -5;
        arrby2[14] = -9;
        arrby2[15] = -9;
        arrby2[16] = -9;
        arrby2[17] = -9;
        arrby2[18] = -9;
        arrby2[19] = -9;
        arrby2[20] = -9;
        arrby2[21] = -9;
        arrby2[22] = -9;
        arrby2[23] = -9;
        arrby2[24] = -9;
        arrby2[25] = -9;
        arrby2[26] = -9;
        arrby2[27] = -9;
        arrby2[28] = -9;
        arrby2[29] = -9;
        arrby2[30] = -9;
        arrby2[31] = -9;
        arrby2[32] = -5;
        arrby2[33] = -9;
        arrby2[34] = -9;
        arrby2[35] = -9;
        arrby2[36] = -9;
        arrby2[37] = -9;
        arrby2[38] = -9;
        arrby2[39] = -9;
        arrby2[40] = -9;
        arrby2[41] = -9;
        arrby2[42] = -9;
        arrby2[43] = -9;
        arrby2[44] = -9;
        arrby2[45] = 62;
        arrby2[46] = -9;
        arrby2[47] = -9;
        arrby2[48] = 52;
        arrby2[49] = 53;
        arrby2[50] = 54;
        arrby2[51] = 55;
        arrby2[52] = 56;
        arrby2[53] = 57;
        arrby2[54] = 58;
        arrby2[55] = 59;
        arrby2[56] = 60;
        arrby2[57] = 61;
        arrby2[58] = -9;
        arrby2[59] = -9;
        arrby2[60] = -9;
        arrby2[61] = -1;
        arrby2[62] = -9;
        arrby2[63] = -9;
        arrby2[64] = -9;
        arrby2[66] = 1;
        arrby2[67] = 2;
        arrby2[68] = 3;
        arrby2[69] = 4;
        arrby2[70] = 5;
        arrby2[71] = 6;
        arrby2[72] = 7;
        arrby2[73] = 8;
        arrby2[74] = 9;
        arrby2[75] = 10;
        arrby2[76] = 11;
        arrby2[77] = 12;
        arrby2[78] = 13;
        arrby2[79] = 14;
        arrby2[80] = 15;
        arrby2[81] = 16;
        arrby2[82] = 17;
        arrby2[83] = 18;
        arrby2[84] = 19;
        arrby2[85] = 20;
        arrby2[86] = 21;
        arrby2[87] = 22;
        arrby2[88] = 23;
        arrby2[89] = 24;
        arrby2[90] = 25;
        arrby2[91] = -9;
        arrby2[92] = -9;
        arrby2[93] = -9;
        arrby2[94] = -9;
        arrby2[95] = 63;
        arrby2[96] = -9;
        arrby2[97] = 26;
        arrby2[98] = 27;
        arrby2[99] = 28;
        arrby2[100] = 29;
        arrby2[101] = 30;
        arrby2[102] = 31;
        arrby2[103] = 32;
        arrby2[104] = 33;
        arrby2[105] = 34;
        arrby2[106] = 35;
        arrby2[107] = 36;
        arrby2[108] = 37;
        arrby2[109] = 38;
        arrby2[110] = 39;
        arrby2[111] = 40;
        arrby2[112] = 41;
        arrby2[113] = 42;
        arrby2[114] = 43;
        arrby2[115] = 44;
        arrby2[116] = 45;
        arrby2[117] = 46;
        arrby2[118] = 47;
        arrby2[119] = 48;
        arrby2[120] = 49;
        arrby2[121] = 50;
        arrby2[122] = 51;
        arrby2[123] = -9;
        arrby2[124] = -9;
        arrby2[125] = -9;
        arrby2[126] = -9;
        arrby2[127] = -9;
        WEBSAFE_DECODABET = arrby2;
    }

    private Base64() {
    }

    public static byte[] decode(String string2) throws Base64DecoderException {
        byte[] arrby = string2.getBytes();
        return Base64.decode(arrby, 0, arrby.length);
    }

    public static byte[] decode(byte[] arrby) throws Base64DecoderException {
        return Base64.decode(arrby, 0, arrby.length);
    }

    public static byte[] decode(byte[] arrby, int n, int n2) throws Base64DecoderException {
        return Base64.decode(arrby, n, n2, DECODABET);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static byte[] decode(byte[] var0, int var1_1, int var2_2, byte[] var3_3) throws Base64DecoderException {
        var4_4 = new byte[2 + var2_2 * 3 / 4];
        var5_5 = 0;
        var6_6 = new byte[4];
        var7_7 = 0;
        var8_8 = 0;
        do {
            block13 : {
                block9 : {
                    block11 : {
                        block12 : {
                            block10 : {
                                block8 : {
                                    if (var7_7 >= var2_2) break block8;
                                    var9_9 = (byte)(127 & var0[var7_7 + var1_1]);
                                    var10_10 = var3_3[var9_9];
                                    if (var10_10 < -5) throw new Base64DecoderException("Bad Base64 input character at " + var7_7 + ": " + var0[var7_7 + var1_1] + "(decimal)");
                                    if (var10_10 < -1) break block9;
                                    if (var9_9 == 61) {
                                        var12_12 = var2_2 - var7_7;
                                        var13_13 = (byte)(127 & var0[var1_1 + (var2_2 - 1)]);
                                        if (var8_8 == 0) throw new Base64DecoderException("invalid padding byte '=' at byte offset " + var7_7);
                                        if (var8_8 == 1) {
                                            throw new Base64DecoderException("invalid padding byte '=' at byte offset " + var7_7);
                                        }
                                        if (var8_8 == 3) {
                                            if (var12_12 > 2) throw new Base64DecoderException("padding byte '=' falsely signals end of encoded value at offset " + var7_7);
                                        }
                                        if (var8_8 == 4 && var12_12 > 1) {
                                            throw new Base64DecoderException("padding byte '=' falsely signals end of encoded value at offset " + var7_7);
                                        }
                                        if (var13_13 != 61 && var13_13 != 10) {
                                            throw new Base64DecoderException("encoded value has invalid trailing byte");
                                        } else {
                                            ** GOTO lbl25
                                        }
                                    }
                                    break block10;
                                }
                                if (var8_8 == 0) break block11;
                                if (var8_8 == 1) {
                                    throw new Base64DecoderException("single trailing character at offset " + (var2_2 - 1));
                                }
                                break block12;
                            }
                            var11_11 = var8_8 + 1;
                            var6_6[var8_8] = var9_9;
                            if (var11_11 == 4) {
                                var5_5 += Base64.decode4to3(var6_6, 0, var4_4, var5_5, var3_3);
                                var11_11 = 0;
                            }
                            break block13;
                        }
                        var8_8 + 1;
                        var6_6[var8_8] = 61;
                        var5_5 += Base64.decode4to3(var6_6, 0, var4_4, var5_5, var3_3);
                    }
                    var15_14 = new byte[var5_5];
                    System.arraycopy((Object)var4_4, (int)0, (Object)var15_14, (int)0, (int)var5_5);
                    return var15_14;
                }
                var11_11 = var8_8;
            }
            ++var7_7;
            var8_8 = var11_11;
        } while (true);
    }

    private static int decode4to3(byte[] arrby, int n, byte[] arrby2, int n2, byte[] arrby3) {
        if (arrby[n + 2] == 61) {
            arrby2[n2] = (byte)((arrby3[arrby[n]] << 24 >>> 6 | arrby3[arrby[n + 1]] << 24 >>> 12) >>> 16);
            return 1;
        }
        if (arrby[n + 3] == 61) {
            int n3 = arrby3[arrby[n]] << 24 >>> 6 | arrby3[arrby[n + 1]] << 24 >>> 12 | arrby3[arrby[n + 2]] << 24 >>> 18;
            arrby2[n2] = (byte)(n3 >>> 16);
            arrby2[n2 + 1] = (byte)(n3 >>> 8);
            return 2;
        }
        int n4 = arrby3[arrby[n]] << 24 >>> 6 | arrby3[arrby[n + 1]] << 24 >>> 12 | arrby3[arrby[n + 2]] << 24 >>> 18 | arrby3[arrby[n + 3]] << 24 >>> 24;
        arrby2[n2] = (byte)(n4 >> 16);
        arrby2[n2 + 1] = (byte)(n4 >> 8);
        arrby2[n2 + 2] = (byte)n4;
        return 3;
    }

    public static byte[] decodeWebSafe(String string2) throws Base64DecoderException {
        byte[] arrby = string2.getBytes();
        return Base64.decodeWebSafe(arrby, 0, arrby.length);
    }

    public static byte[] decodeWebSafe(byte[] arrby) throws Base64DecoderException {
        return Base64.decodeWebSafe(arrby, 0, arrby.length);
    }

    public static byte[] decodeWebSafe(byte[] arrby, int n, int n2) throws Base64DecoderException {
        return Base64.decode(arrby, n, n2, WEBSAFE_DECODABET);
    }

    public static String encode(byte[] arrby) {
        return Base64.encode(arrby, 0, arrby.length, ALPHABET, true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String encode(byte[] arrby, int n, int n2, byte[] arrby2, boolean bl) {
        byte[] arrby3 = Base64.encode(arrby, n, n2, arrby2, Integer.MAX_VALUE);
        int n3 = arrby3.length;
        while (!bl && n3 > 0 && arrby3[n3 - 1] == 61) {
            --n3;
        }
        return new String(arrby3, 0, n3);
    }

    public static byte[] encode(byte[] arrby, int n, int n2, byte[] arrby2, int n3) {
        int n4 = 4 * ((n2 + 2) / 3);
        byte[] arrby3 = new byte[n4 + n4 / n3];
        int n5 = 0;
        int n6 = 0;
        int n7 = n2 - 2;
        int n8 = 0;
        do {
            if (n5 >= n7) {
                if (n5 < n2) {
                    Base64.encode3to4(arrby, n5 + n, n2 - n5, arrby3, n6, arrby2);
                    if (n8 + 4 == n3) {
                        arrby3[n6 + 4] = 10;
                        ++n6;
                    }
                    n6 += 4;
                }
                if (!$assertionsDisabled && n6 != arrby3.length) {
                    throw new AssertionError();
                }
                break;
            }
            int n9 = arrby[n5 + n] << 24 >>> 8 | arrby[n + (n5 + 1)] << 24 >>> 16 | arrby[n + (n5 + 2)] << 24 >>> 24;
            arrby3[n6] = arrby2[n9 >>> 18];
            arrby3[n6 + 1] = arrby2[63 & n9 >>> 12];
            arrby3[n6 + 2] = arrby2[63 & n9 >>> 6];
            arrby3[n6 + 3] = arrby2[n9 & 63];
            if ((n8 += 4) == n3) {
                arrby3[n6 + 4] = 10;
                ++n6;
                n8 = 0;
            }
            n5 += 3;
            n6 += 4;
        } while (true);
        return arrby3;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static byte[] encode3to4(byte[] arrby, int n, int n2, byte[] arrby2, int n3, byte[] arrby3) {
        int n4 = n2 > 0 ? arrby[n] << 24 >>> 8 : 0;
        int n5 = n2 > 1 ? arrby[n + 1] << 24 >>> 16 : 0;
        int n6 = n5 | n4;
        int n7 = 0;
        if (n2 > 2) {
            n7 = arrby[n + 2] << 24 >>> 24;
        }
        int n8 = n6 | n7;
        switch (n2) {
            default: {
                return arrby2;
            }
            case 3: {
                arrby2[n3] = arrby3[n8 >>> 18];
                arrby2[n3 + 1] = arrby3[63 & n8 >>> 12];
                arrby2[n3 + 2] = arrby3[63 & n8 >>> 6];
                arrby2[n3 + 3] = arrby3[n8 & 63];
                return arrby2;
            }
            case 2: {
                arrby2[n3] = arrby3[n8 >>> 18];
                arrby2[n3 + 1] = arrby3[63 & n8 >>> 12];
                arrby2[n3 + 2] = arrby3[63 & n8 >>> 6];
                arrby2[n3 + 3] = 61;
                return arrby2;
            }
            case 1: 
        }
        arrby2[n3] = arrby3[n8 >>> 18];
        arrby2[n3 + 1] = arrby3[63 & n8 >>> 12];
        arrby2[n3 + 2] = 61;
        arrby2[n3 + 3] = 61;
        return arrby2;
    }

    public static String encodeWebSafe(byte[] arrby, boolean bl) {
        return Base64.encode(arrby, 0, arrby.length, WEBSAFE_ALPHABET, bl);
    }
}

