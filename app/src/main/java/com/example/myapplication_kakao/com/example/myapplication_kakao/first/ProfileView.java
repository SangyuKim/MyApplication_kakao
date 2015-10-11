package com.example.myapplication_kakao.com.example.myapplication_kakao.first;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication_kakao.R;

/**
 * Created by 상유 on 2015-10-10.
 */
public class ProfileView  extends FrameLayout {
    public ProfileView(Context context) {
        super(context);
        init();
    }
    ImageView imageView;
    TextView textView;

    public void init(){
        inflate(getContext(), R.layout.myprofile_layout, this);
        imageView = (ImageView)findViewById(R.id.imageView);
        textView = (TextView)findViewById(R.id.textView_name);

    }

    public void setProfileItem(ProfileItem item){
        imageView.setImageResource(R.drawable.thm_general_default_profile_image);
        textView.setText(item.name);
    }
}


