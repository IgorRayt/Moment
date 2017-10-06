package com.moment;

import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import static android.R.attr.filter;

public class MaintainScreenListenerService extends Service {
    public MaintainScreenListenerService() {
    }
    BroadcastReceiver mReceiver=null;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        createDatabase();
        // Toast.makeText(getBaseContext(), "Service on create", Toast.LENGTH_SHORT).show();

        // Register receiver that handles screen on and screen off logic
    }

    private void createDatabase(){

    }

    @Override
    public void onStart(Intent intent, int startId) {
        //check if service already running

        if(!isServiceRunning(ScreenListenerService.class)){
            IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            mReceiver = new ScreenListenerReceiver();
            registerReceiver(mReceiver, filter);
        }


    }

    private boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDestroy() {

        Log.i("ScreenServiceListener", "Maintain Service for the Screen Listener is destroyed");
        if(mReceiver!=null)
            unregisterReceiver(mReceiver);

    }
}
