package com.moment;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Date;

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
        String CHANNEL_ID = "my_channel";
        Integer id = (int) (new Date().getTime());
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.tempus_logo)
                        .setContentTitle("Tempus")
                        .setContentText(message);
        Intent mainActivity = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(mainActivity);

        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager  mNotificationManager =
                (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);

        mNotificationManager.notify(id, mBuilder.build());

    }

}
