package com.example.myapplication_kakao;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication_kakao.com.example.myapplication_kakao.three.GridItemViewThreeFragment;
import com.example.myapplication_kakao.com.example.myapplication_kakao.three.GridViewThreeFragment;
import com.example.myapplication_kakao.com.example.myapplication_kakao.three.ThreeListAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ThreeFragment extends Fragment {
	ListView listView;
	ThreeListAdapter threeListAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.three_fragment_layout, container, false);
		listView = (ListView)view.findViewById(R.id.listView_three_fragment);

		threeListAdapter = new ThreeListAdapter();
		listView.setAdapter(threeListAdapter);
		initData();
//		initData3();
		return view;
	}
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			getActivity().setTitle("ThreeFragment");
		}
	}
	public void initData(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm", Locale.KOREA);
		String ds = sdf.format(new Date());
		for(int i=0; i<20; i++)
			threeListAdapter.addItem(Integer.toString(R.mipmap.ic_launcher), "abc", "hello world", "10", false, ds);
		for(int i=0; i<5; i++){
			GridViewThreeFragment gridViewThreeFragment = new GridViewThreeFragment(getContext());
			threeListAdapter.addGridView(gridViewThreeFragment);
		}
	}

}
