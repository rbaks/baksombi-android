package com.example.baksombi.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.baksombi.R;
import com.example.baksombi.view.activity.MainActivity;

public class AppReminderAlarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent paramIntent) {
        System.out.println("********************broadcast entered********");
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.logo_mini)
                .setContentTitle(context.getResources().getString(R.string.reminder_title))
                .setContentText(context.getResources().getString(R.string.reminder_content))
                .setContentIntent(pendingIntent);// Create the notification

        // Fire the notification
        notificationManager.notify(1, builder.build());
    }
}
