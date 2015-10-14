package com.example.myapplication_kakao;

        import android.content.res.Resources;
        import android.graphics.drawable.Drawable;
        import android.os.Bundle;
        import android.os.PersistableBundle;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.AppCompatActivity;
        import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;
    ViewPager pager;
    TabsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
        pager = (ViewPager)findViewById(R.id.pager);
        mAdapter = new TabsAdapter(this, getSupportFragmentManager(), tabHost, pager);

        Drawable d = getResources().getDrawable(R.drawable.ic_launcher, this.getTheme());
        mAdapter.addTab(tabHost.newTabSpec("tab1").setIndicator(null,d), OneFragment.class, null);
        mAdapter.addTab(tabHost.newTabSpec("tab2").setIndicator(null,d), TwoFragment.class, null);
        mAdapter.addTab(tabHost.newTabSpec("tab3").setIndicator(null,d), ThreeFragment.class, null);
        mAdapter.addTab(tabHost.newTabSpec("tab4").setIndicator(null,d), FourFragment.class, null);

        if (savedInstanceState != null) {
            tabHost.setCurrentTab(savedInstanceState.getInt("tabIndex"));
            mAdapter.onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("tabIndex", tabHost.getCurrentTab());
        mAdapter.onSaveInstanceState(outState);
    }
}
