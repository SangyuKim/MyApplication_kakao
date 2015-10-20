package com.example.myapplication_kakao;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.myapplication_kakao.com.example.myapplication_kakao.first.MyAdapter;
import com.parse.ParseObject;

import java.util.ArrayList;


public class OneFragment extends Fragment {
    public ArrayList<ParseObject> myProfile = new ArrayList<>();
    ExpandableListView listView;
    MyAdapter myAdapter;
    boolean lock;
    private ArrayList<ParseObject> talks = new ArrayList<>();

    @Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.one_fragment_layout, container, false);

        listView = (ExpandableListView)view.findViewById(R.id.expandableListView);

        Bundle b =getArguments();
        if(b!=null) {
            if(b.getSerializable("talks")!=null && b.getSerializable("myProfile")!= null){
            talks = (ArrayList<ParseObject>) b.getSerializable("talks");
            myProfile = (ArrayList<ParseObject>) b.getSerializable("myProfile");
            }
        }
        getArguments().remove("talks");
        getArguments().remove("myProfile");
        Log.d("Tag ", "tag : "+getTag());

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
        myAdapter.add("친구 ", talks);

    }

    public void removeBundle() {
        getArguments().remove("talks");
        getArguments().remove("myProfile");
    }

}
