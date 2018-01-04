package com.moment;


import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Date;
import java.util.UUID;

import static android.content.ContentValues.TAG;

public class NotificationFirebaseMessagingService extends FirebaseMessagingService {

    public NotificationFirebaseMessagingService(){

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getNotification() != null) {
            pushNotification(remoteMessage.getNotification().getBody());
        }
    }

    private void pushNotification(String message){
        Integer id = (int) (new Date().getTime());
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.tempus_logo)
                        .setContentTitle("Tempus")
                        .setContentText(message);

        NotificationManager  mNotificationManager =
                (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);

        mNotificationManager.notify(id, mBuilder.build());



    }

}
