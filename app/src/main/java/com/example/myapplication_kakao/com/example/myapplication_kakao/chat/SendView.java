package com.example.myapplication_kakao.com.example.myapplication_kakao.chat;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.myapplication_kakao.R;


public class SendView extends FrameLayout {
    public SendView(Context context) {
        super(context);
        init();
    }

    TextView messageView;

    private void init() {
        inflate(getContext(), R.layout.chat_send_layout, this);
        messageView = (TextView)findViewById(R.id.textView_send);
    }

    public void setData(SendData data) {
        messageView.setText(data.getMessage());
    }

}