package com.example.myapplication_kakao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.myapplication_kakao.com.example.myapplication_kakao.first.MyAdapter;
import com.example.myapplication_kakao.com.example.myapplication_kakao.first.SearchView;

public class OneFragment extends Fragment {
	ExpandableListView listView;
	MyAdapter myAdapter;

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

        for(int i=0; i <myAdapter.getGroupCount(); i++){
            listView.expandGroup(i);
        }

		return view;
	}
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			getActivity().setTitle("OneFragment");
		}
	}
    private void initData(){

        myAdapter.add("내 프로필 ", "group : "  +"/ child : " );
        for(int i=0; i<3; i++)
            myAdapter.add("새로운 친구 ", "group : "  +"/ child : "+i );
        myAdapter.add("그룹 ", "group : "  +"/ child : " );
        for(int i=0; i<10; i++ )
             myAdapter.add("친구 ", "group : "  +"/ child : "+i );

    }

}
