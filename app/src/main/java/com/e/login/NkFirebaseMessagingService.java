package com.e.login;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.e.login.HomeClass.Home;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class NkFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseMessagingServic";
    Bitmap bitmap;

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d("TOKEN_UPDATE",s);
    }



    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Data Payload" + remoteMessage.getData());
            try {
                JSONObject data = new JSONObject(remoteMessage.getData());
                String jsonmessage = data.getString("extra_info");
                Log.d(TAG,"onMessageRecived:\n"+"extrainfo"+jsonmessage);

            }catch (JSONException e){
                e.printStackTrace();
            }
        }

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Data Payload" + remoteMessage.getNotification().getBody());

            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();
            String image = null;
            image= remoteMessage.getData().get("image");
            String click_action = "null";
            click_action= remoteMessage.getNotification().getClickAction();
            if (image != null){
                bitmap = getBitmapfromUrl(image);
            }

            Log.d(TAG, "title" + title);
            Log.d(TAG, "message" + message);
            Log.d(TAG, "click action" + click_action);


            sendNotification(title,message,click_action,bitmap);

        }
    }

    private void sendNotification(String title, String message,String clickaction,Bitmap bitmap ) {

//        Intent intent;
//        if (clickaction.equals("NotificationActivity")){
//            intent = new Intent(this, Home.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        }
//        else if (clickaction.equals("MAINACTIVITY")){
//            intent = new Intent(this, MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        }
//        else if (clickaction.equals(null)){
//            intent = new Intent(this,MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        }
//        else{
//            intent = new Intent(this,MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        }
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,null,PendingIntent.FLAG_ONE_SHOT);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String Notification_channel_id = "NammaKarur";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(Notification_channel_id, "NK Notification", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Namma Karur notification");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificationbuilder = new NotificationCompat.Builder(this, Notification_channel_id);

        notificationbuilder.setAutoCancel(true);
        notificationbuilder.setDefaults(Notification.DEFAULT_ALL);
        notificationbuilder.setWhen(System.currentTimeMillis());
        notificationbuilder.setSmallIcon(R.drawable.navlog);
        notificationbuilder.setContentIntent(pendingIntent);
        notificationbuilder.setContentTitle(title);
        notificationbuilder.setContentText(message);



        if(bitmap!= null){
            notificationbuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));
        }
        notificationbuilder.setContentInfo("Namma Karur");
        notificationManager.notify(1, notificationbuilder.build());
    }


    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = null;
            bitmap= BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }
}