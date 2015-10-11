package com.example.myapplication_kakao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

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

        initData();
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
            secondListAdapter.add(Integer.toString(R.drawable.thm_general_default_profile_image), "abc", "hello world", "10", false, ds);
    }
	
}
