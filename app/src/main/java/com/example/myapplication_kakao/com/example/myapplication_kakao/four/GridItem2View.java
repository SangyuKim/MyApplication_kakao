package com.example.myapplication_kakao.com.example.myapplication_kakao.four;

import android.content.Context;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.myapplication_kakao.R;

/**
 * Created by 상유 on 2015-10-11.
 */
public class GridItem2View extends FrameLayout {
    ImageView imageView;
    TextView textView;
    private ImageLoader imageLoader;

    public GridItem2View(Context context) {
        super(context);
        init();

    }

    public void init() {
        inflate(getContext(), R.layout.item2_four_fragment_grid_layout, this);
        imageView = (ImageView) findViewById(R.id.imageView_four_fragment2);
        textView = (TextView) findViewById(R.id.textView_four_fragment_name2);

    }

    public void setGrdiViewItem(GridViewItem2 item) {
        Log.d("hello12345", "drawable : " + item.drwable.toString());
        imageView.setImageDrawable(item.drwable);
        textView.setText(item.name);

    }
}
