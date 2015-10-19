package com.example.myapplication_kakao;

        import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.multidex.MultiDex;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    TabHost tabHost;
    ViewPager pager;
    TabsAdapter mAdapter;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    RemoteService remoteService = null;

    private static final int REQUEST_SERVICE = -1;
    private static final String TAG ="MainActivity";
    public ArrayList<ParseObject> talks = new ArrayList<>();
    public ArrayList<ParseObject> myProfile = new ArrayList<>();
    public static final int MESSAGE_GCM =-11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initConnection();
//        checkPlayService(null);

//        tokenLabel = (TextView)findViewById(R.id.tokenLabel);
        registDevice(null);

        Intent intent_bind = new Intent();
        //패키지명, 리스너명
        intent_bind.setClassName("com.example.myapplication_kakao", "com.example.myapplication_kakao.MyGcmListenerService");

        Intent intent = new Intent(this, FullscreenActivity.class);
        startActivity(intent);

        tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
        pager = (ViewPager)findViewById(R.id.pager);
        mAdapter = new TabsAdapter(this, getSupportFragmentManager(), tabHost, pager);


//        Drawable d = getResources().getDrawable(R.drawable.ic_launcher, this.getTheme());
        Drawable d = getResources().getDrawable(R.drawable.ic_launcher);
        b2 = new Bundle();



        mAdapter.addTab(tabHost.newTabSpec("tab1").setIndicator(null, d), OneFragment.class, b2);
        mAdapter.addTab(tabHost.newTabSpec("tab2").setIndicator(null,d), TwoFragment.class, null);
        mAdapter.addTab(tabHost.newTabSpec("tab3").setIndicator(null, d), ThreeFragment.class, null);
        mAdapter.addTab(tabHost.newTabSpec("tab4").setIndicator(null, d), FourFragment.class, null);

        if (savedInstanceState != null) {
            tabHost.setCurrentTab(savedInstanceState.getInt("tabIndex"));
            mAdapter.onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("tabIndex", tabHost.getCurrentTab());
        mAdapter.onSaveInstanceState(outState);
    }


    Bundle b2;

    public void initConnection(){

            Parse.enableLocalDatastore(this);
            // Parse.com에서 앱 콘솔 -> Setting -> Application ID와 Client Key
            Parse.initialize(this, "Z6l1vov6rp3S8E6wdCZjcbkqxaqHy8TC5mOtijzW", "blbaVZveJj6vSXTMQbxiSjx0w2yYKZ6VrM1MjzE6");
            refreshTalks(null);

    }
    // Refresh 버튼 리스너
    public void refreshTalks(View v) {
        // 이전 목록 지우기
//        myProfile.clear();
//        talks.clear();

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("talks");
        final ParseQuery<ParseObject> query2 = ParseQuery.getQuery("myProfile");

        new Thread(new Runnable() {
            @Override
            public void run() {
                // talks 얻어오기
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> results, ParseException e) {
                        if (e == null) {
                            Log.d(TAG, "Retrieved " + results.size() + " results");
                            talks = (ArrayList<ParseObject>) results;
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    b2.putSerializable("talks", talks);
                                    Log.d("MyService", "talks done");
                                }
                            });
//                            Message msg = mHandler.obtainMessage(MESSAGE_TALKS, talks);
//                            mHandler.sendMessage(msg);

                        } else {
                            Log.d(TAG, "Error: " + e.getMessage());
                        }

                    }
                });

                query2.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, ParseException e) {
                        if (e == null) {
                            Log.d(TAG, "Retrieved " + list.size() + " list");
                            myProfile =(ArrayList<ParseObject>)  list;
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    b2.putSerializable("myProfile", myProfile);
                                    Log.d("MyService", "myProfile done");
                                }
                            });
//                            Message msg =mHandler.obtainMessage(MESSAGE_MY_PROFILE, myProfile);
//                            mHandler.sendMessage(msg);
                        } else {
                            Log.d(TAG, "Error: " + e.getMessage());
                        }
                    }

                });
            }
        }).start();


    }//refresh
     // 플레이 서비스 사용 가능 여부 체크
    public void checkPlayService(View v) {
        int resultCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        Log.d(TAG, "isGooglePlayServicesAvailable : " + resultCode);
        if (ConnectionResult.SUCCESS == resultCode) {
            // 구글 플레이 서비스 가능
            Toast.makeText(this, "플레이 서비스 사용 가능", Toast.LENGTH_SHORT).show();
        } else {
            // 구글 플레이 서비스 불가능 - 설치/업데이트 다이얼로그 출력
            GoogleApiAvailability.getInstance().getErrorDialog(this, resultCode, 0).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        checkPlayService(null);
    }

    public void registDevice(View v) {
        // RegistrationIntentService에서 토큰 발급 브로드캐스트 리시버
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String token = intent.getExtras().getString("TOKEN");
            }
        }, new IntentFilter(RegistrationIntentService.REGIST_COMPLETE_BROADCAST));

        Log.d(TAG, "start registration service");

        // 토큰 발급 서비스 시작
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);

        MultiDex.install(this);

    }

}


