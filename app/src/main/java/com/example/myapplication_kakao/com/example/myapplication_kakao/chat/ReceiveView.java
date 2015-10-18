package com.example.myapplication_kakao.com.example.myapplication_kakao.chat;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication_kakao.R;
import com.example.myapplication_kakao.com.example.myapplication_kakao.model.CustomViewCircle;

public class ReceiveView extends FrameLayout {
    public ReceiveView(Context context) {
        super(context);
        init();
    }

    TextView messageView;
    CustomViewCircle iconView;

    private void init() {
        inflate(getContext(), R.layout.chat_receive_layout, this);
        messageView = (TextView)findViewById(R.id.textView_receive);
        iconView = (CustomViewCircle)findViewById(R.id.imageView_you_picture);
    }

    public void setData(ReceiveData data) {

        messageView.setText(data.getMessage());
        iconView.setImageResource(data.getResId());
    }
}
