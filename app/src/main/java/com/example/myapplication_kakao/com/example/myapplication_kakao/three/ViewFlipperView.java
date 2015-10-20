package com.example.myapplication_kakao.com.example.myapplication_kakao.three;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ViewFlipper;

import com.example.myapplication_kakao.R;

/**
 * Created by Tacademy on 2015-10-20.
 */
public class ViewFlipperView extends FrameLayout {
    public ViewFlipperView(Context context) {
        super(context);
        init();
    }
    ViewFlipper viewFlipper;
    void init(){
        inflate(getContext(), R.layout.three_viewflipper_layout, this);
        viewFlipper= (ViewFlipper)findViewById(R.id.viewFlipper);
        viewFlipper.startFlipping();
        viewFlipper.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

    }
}
