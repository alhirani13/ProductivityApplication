package ee461lgroup10.productivityapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by AL Hirani.
 */

public class Notification_reciever extends BroadcastReceiver {

    private static final int NOTIFY_ID = 100;

    @Override
    public void onReceive(Context context, Intent intent) {

        // create the NotificationCompat Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        // Create the intent that will start the ResultActivity when the user
        // taps the notification or chooses an action button
        Intent repeating_intent = new Intent(context, CalendarActivity.class);
        //Intent intent = new Intent(this, NotificationResultActivity.class);

        // Store the notification ID so we can cancel it later in the ResultActivity
        intent.putExtra("notifyID", NOTIFY_ID);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //PendingIntent pendingIntent = PendingIntent.getActivity(this, NOTIFY_ID, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        // Set the three required items all notifications must have
        builder.setSmallIcon(R.drawable.ic_stat_sample_notification);
        builder.setContentTitle("Get 'Er Done Tasks");
        builder.setContentText("You have tasks today! Get 'em Done!");

        // Set the notification to cancel when the user taps on it
        builder.setAutoCancel(true);

        // Set the large icon to be our app's launcher icon
       // builder.setLargeIcon(BitmapFactory.decodeResource(Context.getResources(), R.mipmap.ic_launcher));

        // Set the small subtext message
        builder.setSubText("Tap to view");

        // Set the content intent to launch our result activity
        builder.setContentIntent(pendingIntent);

        // Build the finished notification and then display it to the user
        Notification notification = builder.build();
        NotificationManager mgr = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        mgr.notify(NOTIFY_ID, notification);


    }
}
