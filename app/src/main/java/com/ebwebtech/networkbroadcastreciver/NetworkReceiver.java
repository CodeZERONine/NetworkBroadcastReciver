package com.ebwebtech.networkbroadcastreciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.net.ConnectivityManagerCompat;
import android.util.Log;
import android.widget.Toast;

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("chiller", "onReceive: " );
        if(onlineStatus(context)){
            Log.d("chiller", "onReceive:  online" );
            MainActivity.notifyChnage(true);
        }
        else
        {
            Log.d("chiller", "onReceive:  offline" );
            MainActivity.notifyChnage(false);
        }

    }
    public boolean onlineStatus(Context mContext) {
        try {
            ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            Log.d("chiller", "onlineStatus: "+netInfo.isConnected());
            return (netInfo != null && netInfo.isConnected());

        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
    }
