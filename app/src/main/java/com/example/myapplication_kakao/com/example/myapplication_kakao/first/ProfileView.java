package com.example.myapplication_kakao.com.example.myapplication_kakao.first;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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

/**
 * Created by 상유 on 2015-10-10.
 */
public class ProfileView  extends FrameLayout {
    private ImageLoader imageLoader;
    public ProfileView(Context context) {
        super(context);
        init();
    }

    NetworkImageView imageView;
    String url;
    TextView textView;
    TextView textView_status;

    public void init(){
        inflate(getContext(), R.layout.myprofile_layout, this);
        imageView = (NetworkImageView)findViewById(R.id.imageView);
        textView = (TextView)findViewById(R.id.textView_name);
        textView_status =(TextView)findViewById(R.id.textView_statusMsg);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        ImageCache cache = new ImageCache(10 * 1000 * 1000);
        imageLoader = new ImageLoader(queue, cache);

    }

    public void setProfileItem(ProfileItem item){

        imageView.setImageUrl(item.url, imageLoader);
        textView.setText(item.name);
        textView_status.setText(item.statusMsg);
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


