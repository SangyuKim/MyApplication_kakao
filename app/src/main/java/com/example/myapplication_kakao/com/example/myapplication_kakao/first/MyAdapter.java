package com.example.myapplication_kakao.com.example.myapplication_kakao.first;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.example.myapplication_kakao.com.example.myapplication_kakao.model.ParcelableParseObject;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongja94 on 2015-10-06.
 */
public class MyAdapter extends BaseExpandableListAdapter {
    List<GroupItem> items = new ArrayList<GroupItem>();
    static final String TAG = "MyAdapter";

    public void add(String groupName, List<ParseObject> child) {
        GroupItem g = null;
        for (GroupItem item : items) {
            if (item.groupName.equals(groupName)) {
                g = item;
                break;
            }
        }
        if (g == null) {
            g = new GroupItem();
            g.groupName = groupName;
            items.add(g);
        }

        if (child != null) {
            ProfileItem pItem;
            for(int i=0; i<child.size(); i++) {
                ParseObject item = (ParseObject) child.get(i);
                pItem = new ProfileItem();
                ParseFile imageFile = item.getParseFile("image");
                String name = item.getString("name");
                String statusMsg = item.getString("statusMsg");
                if (imageFile != null) {
                    Log.d(TAG, "image url : " + imageFile.getUrl());
                    String url = imageFile.getUrl();
                    pItem.url = url;

                    // 부하가 크다.
    //                imageFile.getDataInBackground(new GetDataCallback() {
    //                    @Override
    //                    public void done(byte[] bytes, ParseException e) {
    //                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    //                        imageView.setImageBitmap(bitmap);
    //                    }
    //                });
                }

                if (name != null) {
                    pItem.name = name;
                }
                if (statusMsg != null) {
                    pItem.statusMsg =statusMsg;
                }
                g.children.add(pItem);
            }//for
        }//if
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return items.get(groupPosition).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return items.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return items.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return (long)groupPosition << 32 | 0xFFFFFFFF;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return (long)groupPosition << 32 | childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupItemView v;
        if (convertView != null) {
            v = (GroupItemView)convertView;
        } else {
            v = new GroupItemView(parent.getContext());
        }
        v.setGroupItem(items.get(groupPosition));
        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ProfileView v;
        if (convertView != null) {
            v = (ProfileView)convertView;
        } else {
            v = new ProfileView(parent.getContext());
        }
        v.setProfileItem(items.get(groupPosition).children.get(childPosition));

        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
