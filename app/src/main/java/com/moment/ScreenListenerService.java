package com.moment;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ScreenListenerService extends Service {

    BroadcastReceiver mReceiver=null;
    String RUNNING_SCREEN_MESSAGE = "running";
    String STOPPED_SCREEN_MESSAGE = "stopped";
    DatabaseHelper myDb;
    Integer timeOn = (int) (new Date().getTime());;
    Integer timeOff = (int) (new Date().getTime());;
    boolean recordInserted;
    public ScreenListenerService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();

        // Toast.makeText(getBaseContext(), "Service on create", Toast.LENGTH_SHORT).show();

        // Register receiver that handles screen on and screen off logic

    }

    @Override
    public void onStart(Intent intent, int startId) {

        boolean screenOn = false;

        try{
            screenOn = intent.getBooleanExtra("screen_state", false);

        }catch(Exception e){}

        Log.i("ScreenServiceListener", "Screen state changed");

        if (screenOn) {

            // your code here
            // Some time required to start any service

            updateDatabase(RUNNING_SCREEN_MESSAGE);
            Toast.makeText(getBaseContext(), "Screen is on", Toast.LENGTH_SHORT).show();
            Log.i("ScreenServiceListener", "Screen is on");

        } else {

            // your code here
            // Some time required to stop any service to save battery consumption
            updateDatabase(STOPPED_SCREEN_MESSAGE);
            Toast.makeText(getBaseContext(), "Screen is off", Toast.LENGTH_SHORT).show();
            Log.i("ScreenServiceListener", "Screen is off");
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void updateDatabase(String screenState){
        myDb = new DatabaseHelper(this);
        String dateString;
        Date date = new Date();
        SimpleDateFormat dateTableFormat = new SimpleDateFormat("y-D");
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-DDD-HH-mm-ss");

        if (screenState.equals(RUNNING_SCREEN_MESSAGE)){
            timeOn = (int) (new Date().getTime());
        }
        if (screenState.equals(STOPPED_SCREEN_MESSAGE)){
            timeOff = (int) (new Date().getTime());
        }

        if (timeOn < timeOff) {
            dateString = dateTableFormat.format(date);
            recordInserted = myDb.insertData(timeOn, timeOff, dateString);
            if(!recordInserted){
                //fail the fuck everything
            }
            if(recordInserted){
                Toast.makeText(getBaseContext(), "Data inserted", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onDestroy() {
        //right down data into database, then destroy service
        Log.i("ScreenServiceListener", "service destroyed");
        if(mReceiver!=null)
            unregisterReceiver(mReceiver);
    }
}
