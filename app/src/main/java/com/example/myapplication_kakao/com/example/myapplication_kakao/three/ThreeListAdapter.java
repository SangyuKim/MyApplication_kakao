package com.example.myapplication_kakao.com.example.myapplication_kakao.three;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.myapplication_kakao.com.example.myapplication_kakao.second.TalkItem;
import com.example.myapplication_kakao.com.example.myapplication_kakao.second.TalkView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 상유 on 2015-10-11.
 */
public class ThreeListAdapter extends BaseAdapter {
    List<TalkItem> items = new ArrayList<>();
    List<GridViewThreeFragment> gridItems = new ArrayList<>();


    public void addItem(String image, String p_name, String recent_talk, String p_count, boolean alarm, String date) {
        TalkItem talkItem = new TalkItem();
        talkItem.image = image;
        talkItem.p_name = p_name;
        talkItem.recent_talk = recent_talk;
        talkItem.p_count = p_count;
        talkItem.alarm = alarm;
        talkItem.date = date;

        items.add(talkItem);


        notifyDataSetChanged();
    }
    public void addGridView(GridViewThreeFragment gridView){

        gridItems.add(gridView);
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return items.size() + 1 + gridItems.size();
    }

    @Override
    public Object getItem(int position) {
        if (position == 0) {
            return null;
        } else if(position >0 && position <1+ items.size()){
            return items.get(position -1);
        }else{
            return gridItems.get(position - 1 - items.size());
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewFlipperView flipperView;
        TalkView v;
        GridViewThreeFragment gridViewThreeFragment = new GridViewThreeFragment(parent.getContext());

        flipperView = new ViewFlipperView(parent.getContext());
        if (position == 0) {
            return flipperView;
        }
        else if(position >0 && position <1+ items.size()){
        if (convertView != null && convertView instanceof TalkView)
            v = (TalkView) convertView;
        else {
            v = new TalkView(parent.getContext());
        }
        v.setTalkItem(items.get(position - 1));

        return v;
        }else{

//            return gridItems.get(position-1-items.size());
            return gridViewThreeFragment;
        }
    }


}
