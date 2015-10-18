package com.example.myapplication_kakao;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        imageView = (ImageView)findViewById(R.id.imageView_splash);

//        Handler h = new Handler();
//        h.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity( new Intent( FullscreenActivity.this, MainActivity.class));
//                finish();
//            }
//        }, 3000);
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        ((AnimationDrawable)imageView.getDrawable()).start();
    }

    @Override
    protected void onPause() {
        ((AnimationDrawable)imageView.getDrawable()).stop();
        super.onPause();
    }

}
