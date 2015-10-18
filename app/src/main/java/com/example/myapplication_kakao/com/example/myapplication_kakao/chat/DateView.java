package com.example.myapplication_kakao.com.example.myapplication_kakao.chat;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.myapplication_kakao.R;


public class DateView extends FrameLayout {

    TextView dateView;

    public DateView(Context context) {
        super(context);
    }

    private void init() {
        inflate(getContext(), R.layout.chat_date_layout, this);
        dateView = (TextView)findViewById(R.id.textView_date);
    }

    public void setData(DateData data) {
        dateView.setText(data.getDate());
    }
}
