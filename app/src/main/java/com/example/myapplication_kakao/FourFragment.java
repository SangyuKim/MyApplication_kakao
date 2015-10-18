package com.example.myapplication_kakao;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication_kakao.com.example.myapplication_kakao.four.GridViewAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FourFragment extends Fragment {

	ImageView images;
    GridView gridView;
    GridViewAdapter gridViewAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.four_fragment_layout, container, false);
        gridView = (GridView)view.findViewById(R.id.gridView_four_fragment);
        gridViewAdapter = new GridViewAdapter();
        gridView.setAdapter(gridViewAdapter);
		initData();
		return view;
	}
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			getActivity().setTitle("FourFragement");
		}
	}
	public void initData(){
        Bundle b = getArguments();
        if(b != null)
        Log.d("hello6543","Bundle : " +b.toString());
//         Drawable drawable = getResources().getDrawable(R.drawable.grid_view_example_item,getContext().getTheme());
         Drawable drawable = getResources().getDrawable(R.drawable.grid_view_example_item);

		for(int i=0; i<12; i++)
			gridViewAdapter.add(null, drawable, "hello world");
	}




}
