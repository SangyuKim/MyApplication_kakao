package com.example.myapplication_kakao;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;



import com.example.myapplication_kakao.com.example.myapplication_kakao.first.MyAdapter;

import com.example.myapplication_kakao.com.example.myapplication_kakao.model.ParcelableParseObject;
import com.parse.DeleteCallback;
import com.parse.FindCallback;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;


public class OneFragment extends Fragment {
	ExpandableListView listView;
	MyAdapter myAdapter;
    private ArrayList<ParseObject> talks = new ArrayList<>();
    public ArrayList<ParseObject> myProfile = new ArrayList<>();
    boolean lock;



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


    @Override
    public void onStart() {
        super.onStart();

    }
    public interface OnFragmentFirstTranstitionListener{
        void setOnFragmentFirstTransitionListener();
    }
    OnFragmentFirstTranstitionListener mListener;
    public void setOnFragmentFirstTransitionListener(OnFragmentFirstTranstitionListener listener){
        mListener = listener;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnFragmentFirstTranstitionListener)getActivity();
    }
    public void removeBundle(){
        getArguments().remove("talks");
        getArguments().remove("myProfile");
    }
}
