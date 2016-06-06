package com.wonkim.backgroundoptimizations;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by wonil on 6/6/16.
 */
public class Util {
    public static void logNetworkInfo(String tag, Context context) {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null) {
            Log.d(tag, ni.toString());
        }
    }
}
