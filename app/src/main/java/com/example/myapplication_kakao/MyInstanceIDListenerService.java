package com.example.myapplication_kakao;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.android.gms.iid.InstanceIDListenerService;

public class MyInstanceIDListenerService extends InstanceIDListenerService {
    @Override
    public void onTokenRefresh() {
        // 토큰 변경 - 앱 서버에 반영하는 코드 필요
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}
