package com.example.eosorio1.project;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

public class NotifService extends Service {
    public static final String NOTIFICATION_CHANNEL_ID = "4655";
    int eventId;
    Context context;
    public NotifService() {
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int id){
        context = this;
// HERE IS THE SERVICE AND NOTIFICATION
         Thread thread = new Thread(new Runnable() {
             @Override
             public void run() {
                 while(true){
                     LocalDateTime localDateTime = LocalDateTime.now();
                     Log.v("Here is the times", localDateTime.toString()+" id " + eventId);
                     eventId = intent.getIntExtra("eventId", -1);
                     if(ReadEvents.events.stream().anyMatch(event -> event.getId() == eventId
                             && event.getDateTime().getYear() == localDateTime.getYear()
                             && event.getDateTime().getMonth() == localDateTime.getMonth()
                             && event.getDateTime().getDayOfMonth() == localDateTime.getDayOfMonth())){

//                       In API 26, you need to make a Notification Channel where the app will post the notification
                         String channelName = context.getString(R.string.app_name);
                         int importance = NotificationManager.IMPORTANCE_LOW;
                         NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance);
                         notificationChannel.setDescription("Something");
                         notificationChannel.enableVibration(true);
                         notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                         NotificationManager notificationManager = (NotificationManager) NotifService.this.getSystemService(context.NOTIFICATION_SERVICE);
                         notificationManager.createNotificationChannel(notificationChannel);
                         // Make the actual notification now
                         Notification.Builder builder = new Notification.Builder(NotifService.this, channelName)
                         .setChannelId(NOTIFICATION_CHANNEL_ID)
                         .setSmallIcon(R.drawable.clock1);
                         Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.clock1);
                         builder.setLargeIcon(bm);
                         builder.setOnlyAlertOnce(true);
                         for(int i = 0 ; i < ReadEvents.events.size(); i++){
                            if(ReadEvents.events.get(i).getId() == eventId)
                                builder.setContentTitle(ReadEvents.events.get(i).getEventName());
                                builder.setContentText("Your event " + ReadEvents.events.get(i).getEventName() + " is starting soon");
                         }
                         notificationManager.notify(eventId, builder.build());
                         return;
                     }
                     try {
                         Thread.sleep(5000); // Run every 5 seconds
                         Log.v("Running ","Every 5 seconds");
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
             }
         });
         thread.start();
        this.stopSelf();
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
