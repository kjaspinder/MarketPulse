package com.jaspinder.marketpulse.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class Helper {

    public static boolean isNetworkConnected(Context c) {
        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
