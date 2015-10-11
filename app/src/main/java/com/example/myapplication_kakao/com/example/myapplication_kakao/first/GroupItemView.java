package com.example.myapplication_kakao.com.example.myapplication_kakao.first;

import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.myapplication_kakao.R;

/**
 * Created by dongja94 on 2015-10-06.
 */
public class GroupItemView extends FrameLayout {
    public GroupItemView(Context context) {
        super(context);
        init();
    }
    TextView nameView;
    private void init() {
        inflate(getContext(), R.layout.view_group_item, this);
        nameView = (TextView)findViewById(R.id.text_name);
    }

    public void setGroupItem(GroupItem item) {
        nameView.setText(item.groupName);
    }


}
