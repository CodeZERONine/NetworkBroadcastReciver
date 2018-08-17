package com.ebwebtech.networkbroadcastreciver;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static ImageView mImageView;
    private BroadcastReceiver mNetworkReceiver;
   private static Snackbar mSnackbar;
   private static ConstraintLayout mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.imageView);
        mView= (ConstraintLayout)findViewById(R.id.constraintView);
        mNetworkReceiver = new NetworkReceiver();

        registerNetworkBroadcastForNougat();
    }

    public static void notifyChnage(boolean status){
         if(status)
         {
             mImageView.setImageResource(R.drawable.ic_launcher_foreground_green);
             mSnackbar =Snackbar.make(mView,"Back Online",Snackbar.LENGTH_LONG);
             mSnackbar.setActionTextColor(Color.CYAN);
             mSnackbar.show();
             }
         else{
             mImageView.setImageResource(R.drawable.ic_launcher_foreground_red);
             mSnackbar =Snackbar.make(mView,"Offline",Snackbar.LENGTH_INDEFINITE);
             mSnackbar.setActionTextColor(Color.RED);
             mSnackbar.show();

         }
    }

    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }
}
