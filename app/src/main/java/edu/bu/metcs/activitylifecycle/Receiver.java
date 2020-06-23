/**
 * Jamie Lynn Lufrano - ASL Fingerspelling Bee - Project Iteration 5
 * Receiver class that is used to create and manage the Practice Reminder Notification.
 */
package edu.bu.metcs.activitylifecycle;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

public class Receiver extends BroadcastReceiver {

    Notification reminderNotification;
    NotificationManager reminderNotificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        showNotification(context);
    }

    /**
     * Creates the pending intent and notification for the Practice Reminder feature.
     */
    public void showNotification(Context context) {
        Intent reminderIntent = new Intent(context, MainMenuActivity.class);
        reminderIntent.setAction(Intent.ACTION_MAIN);
        reminderIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                reminderIntent, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            reminderNotificationManager = (NotificationManager) context.getSystemService
                    (Context.NOTIFICATION_SERVICE);
            String channelId = "101";

            CharSequence name = "Reminder Channel";

            String description = "Channel for reminders";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel reminderChannel = new NotificationChannel(channelId, name,
                    importance);

            reminderChannel.setDescription(description);
            reminderChannel.enableLights(true);
            reminderChannel.setShowBadge(true);

            reminderChannel.setLightColor(Color.CYAN);
            reminderNotificationManager.createNotificationChannel(reminderChannel);

            reminderNotification = new Notification.Builder(context, "101")
                    .setContentTitle("Practice Reminder")
                    .setTicker("It's time to practice")
                    .setContentText("It is time to practice your ASL Fingerspelling!")
                    .setSmallIcon(R.drawable.bee)
                    .setContentIntent(pendingIntent)
                    .setOngoing(true)
                    .setAutoCancel(true)
                    .build();
        } else {
            reminderNotification = new Notification.Builder(context)
                    .setContentTitle("Practice Reminder")
                    .setTicker("It's time to practice")
                    .setContentText("It is time to practice your ASL Fingerspelling!")
                    .setSmallIcon(R.drawable.bee)
                    .setContentIntent(pendingIntent)
                    .setOngoing(true)
                    .setAutoCancel(true)
                    .build();
        }
        reminderNotificationManager.notify(0, reminderNotification);
    }
}
