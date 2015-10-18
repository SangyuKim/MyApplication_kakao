package com.example.myapplication_kakao.com.example.myapplication_kakao.chat;

/**
 * Created by SeungJin on 2015-10-11.
 */
public class ReceiveData implements ChattingData {
    private int resId;
    private String message;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
