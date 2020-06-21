package edu.bu.metcs.activitylifecycle;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class Receiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        showNotification(context);
    }

    public void showNotification(Context context) {
        Intent intent = new Intent(context, MainMenuActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        Notification.Builder remindBuilder = new Notification.Builder(context)
                .setSmallIcon(R.drawable.bee)
                .setContentTitle("Practice Reminder!")
                .setContentText("It has been 2 days since you practiced your ASL Fingerspelling. " +
                        "Practice now to help build your skills!")
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, remindBuilder.build());
    }
}
