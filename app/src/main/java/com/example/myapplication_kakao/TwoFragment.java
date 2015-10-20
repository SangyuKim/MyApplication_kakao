package com.example.myapplication_kakao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication_kakao.com.example.myapplication_kakao.second.SecondListAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TwoFragment extends Fragment {
	ListView listView;
    SecondListAdapter secondListAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.second_fragment_layout, container, false);
		listView = (ListView)view.findViewById(R.id.listView);
        secondListAdapter = new SecondListAdapter();
        listView.setAdapter(secondListAdapter);

		if(getTag() != null)
		Log.d("TagID","Two Fragment ID : "+		getId() +"Tag : " +getTag());



		initData();
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getActivity(), Chat_Activity.class);
                Log.d("two" ,"  " +getActivity().getIntent().toString());
				if(mListener!=null)
					mListener.onSetTransitionListener();
                getActivity().startActivity(intent);

//				Toast.makeText(getContext(), "hello ", Toast.LENGTH_SHORT).show();
			}
		});

		return view;
	}
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			getActivity().setTitle("TwoFragment");
		}
	}
    public void initData(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm", Locale.KOREA);
        String ds = sdf.format(new Date());
        for(int i=0; i<20; i++)
            secondListAdapter.add(Integer.toString(R.mipmap.ic_launcher), "abc", "hello world", "10", false, ds);
    }
	public interface OnTransitionListener{
		public void onSetTransitionListener();
	}
	OnTransitionListener mListener;
	public void setTransitionListener(OnTransitionListener listener){
		mListener = listener;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mListener = (OnTransitionListener)getActivity();
	}
}
