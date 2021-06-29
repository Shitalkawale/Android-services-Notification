package com.example.androidservices;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.provider.MediaStore;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class Task extends Service
{

    NotificationManager notificationManager;
    //MediaStore mediaStore;
    int notification_id=1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {

        return null;
    }

    @Override
    public void onCreate()
    {
        notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        super.onCreate();
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId)
//    {
//        Notification notification=new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setWhen(System.currentTimeMillis())
//                .setContentTitle("Service is Running")
//                .setContentText("Un Bound Service is Running")
//                .setOngoing(true)
//                .build();
//        //startForeground(Notification_id,notification);
//        startForeground(Notification_id,notification);
//        return super.onStartCommand(intent, flags, startId);
//    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        String NOTIFICATION_CHANNEL_ID = "com.example.MyApp";
        String channelName = "My Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("App is running in background")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(notification_id, notification);
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy()
    {
        notificationManager.cancel(notification_id);
        super.onDestroy();
    }
}
