package us.nickromano.notificationsounds;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String channelId = "test-channel";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel newIncidentChannel = new NotificationChannel(channelId,
                    "Test Channel",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(newIncidentChannel);
        }

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("Test")
                        .setContentText("Text")
                        .setSound(RingtoneManager
                                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setAutoCancel(true);

        int NOTIFICATION_ID = (int) (System.currentTimeMillis()%10000);
        notificationManager.notify("test", NOTIFICATION_ID, notificationBuilder.build());
    }
}
