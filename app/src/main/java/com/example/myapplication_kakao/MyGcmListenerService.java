package com.example.myapplication_kakao;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.gcm.GcmListenerService;


public class MyGcmListenerService extends GcmListenerService   {
    private static final String TAG = "GCM_Example";



    Handler handler= new Handler(Looper.getMainLooper());

    public void onMessageReceived(String from, Bundle data) {
        String title = data.getString("title");
        String message = data.getString("message");
        Log.d(TAG, "From :" + from);
        // 도착한 메세지를 사용자에게 알린다.
        sendNotification(title, message);
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(getApplicationContext(), "received", Toast.LENGTH_SHORT).show();
//
//            }
//        });

        Message msg = handler.obtainMessage(MainActivity.MESSAGE_GCM, message);
        handler.sendMessage(msg);

//        setMessageReceivedListener(mListener);

    }

    private void sendNotification(String title, String message) {
        Log.d(TAG, "Title: " + title);
        Log.d(TAG, "Message: " + message);

        // 알림 터치시 - MainActivity가 나타나도록
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        // 알림 효과음
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // 알림 객체
        Notification noti = new Notification.Builder(this)
                .setSmallIcon(R.drawable.foot)      // 아이콘
                .setContentTitle(title)             // 제목
                .setContentText(message)            // 내용
                .setContentIntent(pendingIntent)    // 알림 선택시
                .setSound(soundUri)                 // 알림음
                .setAutoCancel(true)                // 센터에서 자동 삭제
                .build();

        // 알림 매니저를 통해서 발송
        NotificationManager notiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notiManager.notify(0, noti);

    }
//    public IBinder onBind(Intent intent){
//        return mBinder;
//    }

}
