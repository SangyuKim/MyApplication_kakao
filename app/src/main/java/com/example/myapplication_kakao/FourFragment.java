package com.example.myapplication_kakao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FourFragment extends Fragment {

	ImageView images;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.four_grid_layout, container, false);
        boolean lock=true;
        for(int i=1; i<12; i++){

            String resID = "imageView_grid"+i;
            int temp = getResources().getIdentifier(resID,"id", "com.example.myapplication_kakao");
             images = (ImageView)view.findViewById(temp);
            if(images== null) lock= false;
            if(lock) images.setImageResource(R.mipmap.ic_launcher);
        }
        lock=true;
        for(int i=1; i<5; i++){

            String resID = "imageView_app"+i;
            int temp = getResources().getIdentifier(resID,"id", "com.example.myapplication_kakao");
            images = (ImageView)view.findViewById(temp);
            if(images== null) lock= false;
            if(lock) images.setImageResource(R.mipmap.ic_launcher);
        }


		return view;
	}
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			getActivity().setTitle("FourFragement");
		}
	}
	
}
