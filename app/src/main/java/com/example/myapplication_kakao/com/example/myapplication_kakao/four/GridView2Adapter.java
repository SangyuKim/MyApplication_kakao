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
        GridView2Adapter extends BaseAdapter {
    List<GridViewItem2> items = new ArrayList<>();


    public void add(Drawable drawable, String name) {
        GridViewItem2 gridViewItem = new GridViewItem2();
        gridViewItem.drwable = drawable;
        gridViewItem.name = name;

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
        GridItem2View v;
        if (convertView != null)
            v = (GridItem2View) convertView;
        else {
            v = new GridItem2View(parent.getContext());
        }
        v.setGrdiViewItem(items.get(position));

        return v;
    }


}
