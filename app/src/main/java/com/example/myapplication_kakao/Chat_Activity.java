package com.example.myapplication_kakao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.myapplication_kakao.com.example.myapplication_kakao.chat.ChattingAdapter;
import com.example.myapplication_kakao.com.example.myapplication_kakao.chat.SendData;

public class Chat_Activity extends AppCompatActivity {

    ListView listView;
    ChattingAdapter chattingAdapter;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_);
        listView =(ListView)findViewById(R.id.listView_chat);
        chattingAdapter = new ChattingAdapter();
        listView.setAdapter(chattingAdapter);
        editText =(EditText)findViewById(R.id.editText_input);
        Button btn = (Button)findViewById(R.id.button_send);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editText.getText().toString();
                if (!TextUtils.isEmpty(message)) {
                    SendData data = new SendData();
                    data.setMessage(message);
                    chattingAdapter.add(data);
                }
            }
        });

    }


}
