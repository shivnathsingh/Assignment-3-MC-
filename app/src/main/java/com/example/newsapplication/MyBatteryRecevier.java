package com.example.newsapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBatteryRecevier extends BroadcastReceiver {
    MainActivity mA;
    Myservice ms;

    public MyBatteryRecevier(MainActivity MA) {
        mA=MA;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            Toast.makeText(context, "Charger Connected", Toast.LENGTH_SHORT).show();
            mA.stop();
            //ms.onDestroy();
        }

        if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
            Toast.makeText(context, "Charger  Disconnected ", Toast.LENGTH_SHORT).show();
            mA.start();

        }
        if(intent.getAction().equals(Intent.ACTION_BATTERY_LOW)){
            Toast.makeText(context, "BATTERY low ", Toast.LENGTH_SHORT).show();
             mA.stop();
            //ms.onDestroy();
        }
        if(intent.getAction().equals(Intent.ACTION_BATTERY_OKAY)){
            Toast.makeText(context, "BATTERY OKAY ", Toast.LENGTH_SHORT).show();
            Log.i("okay message","okay message aa gaya");
            mA.start();
        }
    }
}