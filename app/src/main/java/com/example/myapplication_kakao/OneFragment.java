package com.example.myapplication_kakao;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;



import com.example.myapplication_kakao.com.example.myapplication_kakao.first.MyAdapter;

import com.parse.DeleteCallback;
import com.parse.FindCallback;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.media.MediaDescriptionCompatApi21.Builder.setTitle;

public class OneFragment extends Fragment {
	ExpandableListView listView;
	MyAdapter myAdapter;

    static final String TAG = "SmallTalk_Example";
    private static final int NEW_TALK_ACTIVITY = 1;
    private List<ParseObject> talks = new ArrayList<>();
    private List<ParseObject> myProfile = new ArrayList<>();



    @Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.one_fragment_layout, container, false);

        listView = (ExpandableListView)view.findViewById(R.id.expandableListView);
        myAdapter = new MyAdapter();
        View searchView = getLayoutInflater(savedInstanceState).inflate(R.layout.search_layout, null);
        listView.addHeaderView(searchView);
        listView.setAdapter(myAdapter);
        listView.setGroupIndicator(null);
        listView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                listView.expandGroup(groupPosition);
            }
        });

        initData();
        expandGroup();


		return view;
	}
    public void expandGroup(){
        for(int i=0; i <myAdapter.getGroupCount(); i++){
            listView.expandGroup(i);
        }
    }
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			getActivity().setTitle("OneFragment");
		}
	}
    private void initData(){
        myAdapter.add("내 프로필 ", myProfile );

//        myAdapter.add("새로운 친구 ", "group : "  +"/ child : "+i );
        myAdapter.add("그룹 ", talks );
        myAdapter.add("친구 ", talks );

    }
    public void addFriend(){
        ParseObject talk = new ParseObject("talk");
        talk.put("name", "테스트");
        talk.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                //저장완료
            }
        });
    }
    public void getFriendProfile(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("talks");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                //데이터 얻어오기
            }
        });
    }
    public void deleteFriend(){
        ParseObject parseObject = new ParseObject("talks");
        parseObject.deleteInBackground(new DeleteCallback() {
            @Override
            public void done(ParseException e) {
                //delete
            }
        });
    }
    public void updateFriend(){
        ParseObject parseObject = new ParseObject("talks");
        parseObject.put("score", 1338);
        parseObject.put("cheatMode", true);
        parseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                //데이터 수정하기
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        refreshTalks(null);
    }
    Handler handler = new Handler();
    // Refresh 버튼 리스너
    public void refreshTalks(View v) {
        // 이전 목록 지우기
        myProfile.clear();
        talks.clear();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("talks");
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("myProfile");
        // talks 얻어오기
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> results, ParseException e) {
                if (e == null) {
                    Log.d(TAG, "Retrieved " + results.size() + " results");
                    talks = results;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myAdapter.notifyDataSetChanged();
                        }
                    });
                } else {
                    Log.d(TAG, "Error: " + e.getMessage());
                }
            }
        });
        query2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    Log.d(TAG, "Retrieved " + list.size() + " list");
                    myProfile = list;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myAdapter.notifyDataSetChanged();
                        }
                    });
                } else {
                    Log.d(TAG, "Error: " + e.getMessage());
                }
            }
        });
    }
}
