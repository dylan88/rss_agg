package com.example.wautel_l.rss_reader_android;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by wautel_l on 25/02/2018.
 */

public class Connectivity {

    public static boolean isConnected(Activity act)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) act.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null)
        {
            NetworkInfo.State networkState = networkInfo.getState();
           if (networkState.compareTo(NetworkInfo.State.CONNECTED) == 0)
           {
               if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting())
                   return true;
               else
                   return false;
           }
           else return false;
        }
        else return false;
    }
}
