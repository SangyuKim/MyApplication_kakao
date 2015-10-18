package com.example.myapplication_kakao.com.example.myapplication_kakao.four;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.myapplication_kakao.R;
import com.example.myapplication_kakao.com.example.myapplication_kakao.first.ProfileItem;
import com.example.myapplication_kakao.com.example.myapplication_kakao.model.CustomViewCircle;

/**
 * Created by 상유 on 2015-10-11.
 */
public class GridItemView extends FrameLayout {
    private ImageLoader imageLoader;
    public GridItemView(Context context) {
        super(context);
        init();

    }

    ImageView imageView;
    TextView textView;
    CustomViewCircle customViewCircle;

    public void init(){
        inflate(getContext(), R.layout.item_four_fragment_grid_layout, this);
        imageView = (ImageView)findViewById(R.id.imageView_four_fragment);
        textView =(TextView)findViewById(R.id.textView_four_fragment_name);
        customViewCircle = (CustomViewCircle)findViewById(R.id.customViewCircleView);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        ImageCache cache = new ImageCache(10 * 1000 * 1000);
        imageLoader = new ImageLoader(queue, cache);
    }
    public void setGrdiViewItem(GridViewItem item){
        Log.d("hello12345", "drawable : " + item.drwable.toString());
        if(item.drwable != null) imageView.setImageDrawable(item.drwable);
        else{
            Log.d("hello1234", "null");
            customViewCircle.setVisibility(VISIBLE);
            imageView.setVisibility(GONE);
        }
        textView.setText(item.name);
        if(item.url != null)
             customViewCircle.setImageUrl(item.url, imageLoader);
        else{

            customViewCircle.setVisibility(GONE);
            imageView.setVisibility(VISIBLE);
        }

    }
    // Memory Cache
    class ImageCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {

        public ImageCache(int maxSize) {
            super(maxSize);
        }

        @Override
        public Bitmap getBitmap(String url) {
            return get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            put(url, bitmap);
        }
    }

}
