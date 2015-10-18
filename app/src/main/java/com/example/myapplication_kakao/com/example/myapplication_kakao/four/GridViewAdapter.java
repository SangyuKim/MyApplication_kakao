package com.example.myapplication_kakao.com.example.myapplication_kakao.four;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 상유 on 2015-10-11.
 */
public class
        GridViewAdapter extends BaseAdapter {
    List<GridViewItem> items = new ArrayList<>();


    public void add(String url, Drawable drawable, String name) {
        GridViewItem gridViewItem = new GridViewItem();
        gridViewItem.drwable =drawable;
        gridViewItem.name = name;
        gridViewItem.url = url;

        items.add(gridViewItem);
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
        GridItemView v;
        if(convertView !=null)
            v= (GridItemView)convertView;
        else{
            v= new GridItemView(parent.getContext());
        }
        v.setGrdiViewItem(items.get(position));

        return v;
    }


}
