package com.example.myapplication_kakao.com.example.myapplication_kakao.second;

import android.content.Context;
import android.media.Image;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication_kakao.R;

/**
 * Created by 상유 on 2015-10-11.
 */
public class TalkView extends FrameLayout {
    public TalkView(Context context) {
        super(context);
        init();

    }

    ImageView imageView;
    TextView textViewPname;
    TextView textViewRecentTalk;
    TextView textViewPcount;
    ImageView alarm;
    TextView textViewDate;

    public void init(){
        inflate(getContext(), R.layout.view_list_layout, this);
        imageView = (ImageView)findViewById(R.id.imageView_p_picture);
        textViewPname = (TextView)findViewById(R.id.textView_p_name);
        textViewRecentTalk= (TextView)findViewById(R.id.textView_recent_talk);
        textViewPcount = (TextView)findViewById(R.id.textView_p_count);
        alarm = (ImageView)findViewById(R.id.imageView_boolean_alarm);
        textViewDate = (TextView)findViewById(R.id.textView_date);

    }
    public void setTalkItem(TalkItem item){
        imageView.setImageResource(R.mipmap.ic_launcher);
        textViewPname.setText(item.p_name);
        textViewRecentTalk.setText(item.recent_talk);
        textViewPcount.setText(item.p_count);
        if(!item.alarm)
            alarm.setImageResource(R.mipmap.ic_launcher);
        textViewDate.setText(item.date);
    }

}
