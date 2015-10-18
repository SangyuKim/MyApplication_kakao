package com.example.myapplication_kakao;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

public class RegistrationIntentService extends IntentService {
    static final private String TAG = "GCM_Example";
    static public final String REGIST_COMPLETE_BROADCAST = "REGIST_COMPLETE_BROADCAST";

    // 파라미터 없는 public 생성자 꼭 필요
    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "RegistrationIntentService Start!");
        try {
            // 발급받은 토큰
            InstanceID instanceID = InstanceID.getInstance(this);
            final String token = instanceID.getToken(getString(R.string.GCM_SenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE);
            Log.d(TAG, "Token : " + token);

            Intent completeIntent = new Intent(REGIST_COMPLETE_BROADCAST);
            completeIntent.putExtra("TOKEN", token);
            LocalBroadcastManager.getInstance(this).sendBroadcast(completeIntent);

        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Regist Exception", e);
        }
    }
}
