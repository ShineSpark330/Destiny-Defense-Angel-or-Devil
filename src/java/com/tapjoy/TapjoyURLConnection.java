/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.net.Uri
 *  java.io.BufferedReader
 *  java.io.InputStream
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.net.HttpURLConnection
 *  java.net.URL
 *  java.net.URLConnection
 *  java.util.ArrayList
 *  java.util.Hashtable
 *  java.util.Iterator
 *  java.util.List
 *  java.util.Map
 *  java.util.Map$Entry
 *  java.util.Set
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpResponse
 *  org.apache.http.StatusLine
 *  org.apache.http.client.entity.UrlEncodedFormEntity
 *  org.apache.http.client.methods.HttpPost
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.impl.client.DefaultHttpClient
 *  org.apache.http.message.BasicNameValuePair
 *  org.apache.http.params.BasicHttpParams
 *  org.apache.http.params.HttpConnectionParams
 *  org.apache.http.params.HttpParams
 *  org.apache.http.util.EntityUtils
 */
package com.tapjoy;

import android.net.Uri;
import com.tapjoy.TapjoyHttpURLResponse;
import com.tapjoy.TapjoyLog;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class TapjoyURLConnection {
    private static final String TAPJOY_URL_CONNECTION = "TapjoyURLConnection";

    public String connectToURL(String string2) {
        return this.connectToURL(string2, "");
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public String connectToURL(String var1_1, String var2_2) {
        var3_3 = null;
        var5_4 = (var1_1 + var2_2).replaceAll(" ", "%20");
        TapjoyLog.i("TapjoyURLConnection", "baseURL: " + var1_1);
        TapjoyLog.i("TapjoyURLConnection", "requestURL: " + var5_4);
        var6_5 = (HttpURLConnection)new URL(var5_4).openConnection();
        var6_5.setConnectTimeout(15000);
        var6_5.setReadTimeout(30000);
        var3_3 = var6_5.getResponseMessage();
        var6_5.connect();
        var7_6 = new BufferedReader((Reader)new InputStreamReader(var6_5.getInputStream()));
        var8_7 = new StringBuilder();
        try {
            while ((var9_8 = var7_6.readLine()) != null) {
                var8_7.append(var9_8 + '\n');
            }
            var3_3 = var8_7.toString();
            TapjoyLog.i("TapjoyURLConnection", "--------------------");
            TapjoyLog.i("TapjoyURLConnection", "response size: " + var3_3.length());
            TapjoyLog.i("TapjoyURLConnection", "response: ");
            TapjoyLog.i("TapjoyURLConnection", "" + var3_3);
            TapjoyLog.i("TapjoyURLConnection", "--------------------");
            return var3_3;
        }
        catch (Exception var4_9) {}
        ** GOTO lbl-1000
        catch (Exception var4_11) {
            ** GOTO lbl-1000
        }
        catch (Exception var4_12) {}
lbl-1000: // 3 sources:
        {
            TapjoyLog.e("TapjoyURLConnection", "Exception: " + var4_10.toString());
            return var3_3;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public String connectToURLwithPOST(String string2, Hashtable<String, String> hashtable, Hashtable<String, String> hashtable2) {
        HttpPost httpPost;
        ArrayList arrayList;
        String string3 = null;
        try {
            String string4 = string2.replaceAll(" ", "%20");
            TapjoyLog.i(TAPJOY_URL_CONNECTION, "baseURL: " + string2);
            TapjoyLog.i(TAPJOY_URL_CONNECTION, "requestURL: " + string4);
            httpPost = new HttpPost(string4);
            arrayList = new ArrayList();
            Iterator iterator = hashtable.entrySet().iterator();
            do {
                boolean bl = iterator.hasNext();
                string3 = null;
                if (!bl) break;
                Map.Entry entry = (Map.Entry)iterator.next();
                arrayList.add((Object)new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue()));
                TapjoyLog.i(TAPJOY_URL_CONNECTION, "key: " + (String)entry.getKey() + ", value: " + Uri.encode((String)((String)entry.getValue())));
            } while (true);
            if (hashtable2 != null) {
                int n = hashtable2.size();
                string3 = null;
                if (n > 0) {
                    Iterator iterator2 = hashtable2.entrySet().iterator();
                    do {
                        boolean bl = iterator2.hasNext();
                        string3 = null;
                        if (bl) {
                            Map.Entry entry = (Map.Entry)iterator2.next();
                            arrayList.add((Object)new BasicNameValuePair("data[" + (String)entry.getKey() + "]", (String)entry.getValue()));
                            TapjoyLog.i(TAPJOY_URL_CONNECTION, "key: " + (String)entry.getKey() + ", value: " + Uri.encode((String)((String)entry.getValue())));
                            continue;
                        }
                        break;
                    } while (true);
                }
            }
        }
        catch (Exception exception) {
            TapjoyLog.e(TAPJOY_URL_CONNECTION, "Exception: " + exception.toString());
            return string3;
        }
        httpPost.setEntity((HttpEntity)new UrlEncodedFormEntity((List)arrayList));
        TapjoyLog.i(TAPJOY_URL_CONNECTION, "HTTP POST: " + httpPost.toString());
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout((HttpParams)basicHttpParams, (int)15000);
        HttpConnectionParams.setSoTimeout((HttpParams)basicHttpParams, (int)30000);
        HttpResponse httpResponse = new DefaultHttpClient((HttpParams)basicHttpParams).execute((HttpUriRequest)httpPost);
        string3 = EntityUtils.toString((HttpEntity)httpResponse.getEntity());
        TapjoyLog.i(TAPJOY_URL_CONNECTION, "--------------------");
        TapjoyLog.i(TAPJOY_URL_CONNECTION, "response status: " + httpResponse.getStatusLine().getStatusCode());
        TapjoyLog.i(TAPJOY_URL_CONNECTION, "response size: " + string3.length());
        TapjoyLog.i(TAPJOY_URL_CONNECTION, "response: ");
        TapjoyLog.i(TAPJOY_URL_CONNECTION, "" + string3);
        TapjoyLog.i(TAPJOY_URL_CONNECTION, "--------------------");
        return string3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public String getContentLength(String string2) {
        String string3;
        try {
            String string4;
            String string5 = string2.replaceAll(" ", "%20");
            TapjoyLog.i(TAPJOY_URL_CONNECTION, "requestURL: " + string5);
            HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(string5).openConnection();
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(30000);
            string3 = string4 = httpURLConnection.getHeaderField("content-length");
        }
        catch (Exception exception) {
            TapjoyLog.e(TAPJOY_URL_CONNECTION, "Exception: " + exception.toString());
            string3 = null;
        }
        TapjoyLog.i(TAPJOY_URL_CONNECTION, "content-length: " + string3);
        return string3;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public TapjoyHttpURLResponse getResponseFromURL(String var1_1, String var2_2) {
        block11 : {
            var3_3 = new TapjoyHttpURLResponse();
            var5_4 = (var1_1 + var2_2).replaceAll(" ", "%20");
            TapjoyLog.i("TapjoyURLConnection", "baseURL: " + var1_1);
            TapjoyLog.i("TapjoyURLConnection", "requestURL: " + var5_4);
            var6_5 = (HttpURLConnection)new URL(var5_4).openConnection();
            var6_5.setConnectTimeout(15000);
            var6_5.setReadTimeout(30000);
            var6_5.connect();
            var7_6 = new BufferedReader((Reader)new InputStreamReader(var6_5.getInputStream()));
            var8_7 = new StringBuilder();
            try {
                while ((var9_8 = var7_6.readLine()) != null) {
                    var8_7.append(var9_8 + '\n');
                }
                var3_3.response = var8_7.toString();
                var3_3.statusCode = var6_5.getResponseCode();
                var11_13 = var6_5.getHeaderField("content-length");
                if (var11_13 == null) break block11;
            }
lbl21: // 2 sources:
            catch (Exception var4_9) {}
            var3_3.contentLength = Integer.valueOf((String)var11_13);
        }
        ** try [egrp 5[TRYBLOCK] [6 : 269->404)] { 
lbl26: // 1 sources:
        ** GOTO lbl29
        {
            catch (Exception var12_14) {
                TapjoyLog.e("TapjoyURLConnection", "Exception: " + var12_14.toString());
            }
lbl29: // 2 sources:
            TapjoyLog.i("TapjoyURLConnection", "--------------------");
            TapjoyLog.i("TapjoyURLConnection", "response status: " + var3_3.statusCode);
            TapjoyLog.i("TapjoyURLConnection", "response size: " + var11_13);
            TapjoyLog.i("TapjoyURLConnection", "response: ");
            TapjoyLog.i("TapjoyURLConnection", "" + var3_3.response);
            TapjoyLog.i("TapjoyURLConnection", "--------------------");
            return var3_3;
        }
        ** GOTO lbl-1000
        catch (Exception var4_11) {
            ** GOTO lbl-1000
        }
        catch (Exception var4_12) {}
lbl-1000: // 3 sources:
        {
            TapjoyLog.e("TapjoyURLConnection", "Exception: " + var4_10.toString());
            return var3_3;
        }
    }
}

