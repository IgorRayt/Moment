package com.moment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScreenListenerReceiver extends BroadcastReceiver {

    private boolean screenOn;

    @Override
    public void onReceive(Context context, Intent intent) {

        //Toast.makeText(context, "BroadcastReceiver", Toast.LENGTH_SHORT).show();

        Log.i("ScreenServiceListener" , "receiver: " + intent.getAction());

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

            screenOn = false;

        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {

            screenOn = true;

        }

        // Toast.makeText(context, "BroadcastReceiver :"+screenOff, Toast.LENGTH_SHORT).show();

        // Send Current screen ON/OFF value to service

        Intent screenListenerIntentService;
        screenListenerIntentService = new Intent(context, ScreenListenerService.class);

        screenListenerIntentService.putExtra("screen_state", screenOn);
        context.startService(screenListenerIntentService);

    }

}