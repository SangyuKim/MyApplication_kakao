package com.example.myapplication_kakao.com.example.myapplication_kakao.second;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 상유 on 2015-10-11.
 */
public class SecondListAdapter extends BaseAdapter {
    List<TalkItem> items = new ArrayList<>();


    public void add(String image, String p_name, String recent_talk, String p_count, boolean alarm, String date) {
        TalkItem talkItem = new TalkItem();
        talkItem.image =image;
        talkItem.p_name = p_name;
        talkItem.recent_talk = recent_talk;
        talkItem.p_count = p_count;
        talkItem.alarm = alarm;
        talkItem.date =date;

        items.add(talkItem);


        notifyDataSetChanged();
    }


   @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TalkView v;
        if(convertView !=null)
            v= (TalkView)convertView;
        else{
            v= new TalkView(parent.getContext());
        }
        v.setTalkItem(items.get(position));

        return v;
    }


}
