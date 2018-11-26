package com.benjamin.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int notificationID = PointerIconCompat.TYPE_CONTEXT_MENU;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
    }

    public void showNotification(View view) {

        NotificationManager manager = (NotificationManager) getSystemService("notification");
        String channel = "channel_1";
        if (VERSION.SDK_INT >= 26) {
            manager.createNotificationChannel(new NotificationChannel(channel, "channel_1", 3));
        }

        if (manager != null) {
            int i = this.notificationID;
            this.notificationID = i + 1;

            Builder builder = new Builder(this, channel);
            builder.setTicker("World");
            builder.setSmallIcon(R.drawable.ic_launcher_background);
            builder.setWhen(System.currentTimeMillis());
            builder.setContentTitle("标题栏" + i);
            builder.setContentText("这个是显示出来的内容部分"  + i);
            builder.setDefaults(-1);
            Notification notification = builder.build();
            notification.flags |= 16;
            notification.contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 134217728);

            // Toast 提示
            Toast.makeText(getApplicationContext(), "CreatNotification Success" + i , Toast.LENGTH_SHORT).show();
            manager.notify(i, notification);
        }

    }


}
