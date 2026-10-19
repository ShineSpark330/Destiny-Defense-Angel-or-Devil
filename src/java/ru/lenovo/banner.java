/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.res.AssetManager
 *  android.graphics.Color
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.ImageView
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.Toast
 *  java.io.IOException
 *  java.io.InputStream
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.String
 */
package ru.lenovo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;

public class banner {
    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void lenovo(Context context) {
        Toast.makeText((Context)context, (CharSequence)"\u0424\u0430\u0439\u043b \u0441\u043aa\u0447a\u043d \u0441 www.l\u0435n\u043ev.ru.", (int)1).show();
        RelativeLayout relativeLayout = new RelativeLayout(context);
        ImageView imageView = new ImageView(context);
        try {
            imageView.setImageDrawable(Drawable.createFromStream((InputStream)context.getAssets().open("config.bin"), null));
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        imageView.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -2));
        relativeLayout.addView((View)imageView);
        relativeLayout.setBackgroundColor(Color.parseColor((String)"#000000"));
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setView((View)relativeLayout);
        alertDialog.setCancelable(false);
        alertDialog.setButton(-3, (CharSequence)"\u041e\u0442\u043a\u0440\u044b\u0442\u044c \u043d\u0430\u0448 \u0441\u0430\u0439\u0442", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                Context.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String)"http://www.lenov.ru")));
            }
        });
        alertDialog.setButton(-2, (CharSequence)"\u0417\u0430\u043a\u0440\u044b\u0442\u044c", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
            }
        });
        alertDialog.show();
    }

}

