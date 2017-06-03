package com.example.wmz.photoviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 查看图片
 */
public class ZoomImageViewActivity extends Activity {

    ViewPager mZoomViewPager;

    TextView mZoomTv;
    private ImageView[] mImageViews;
    private int mIndex;
    private ArrayList<Integer> mUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);
        Bundle extras = getIntent().getExtras();
        mUrl = extras.getIntegerArrayList("list");
        mIndex = Integer.parseInt(extras.getString("position"));
        initData();
        initViewPager();
        initTextView();
        initVpListener();

    }

    private void initViewPager() {
        mZoomViewPager= (ViewPager)findViewById(R.id.zoom_view_pager);
        mZoomTv=(TextView)findViewById(R.id.zoom_tv);
        mZoomViewPager.setAdapter(mAdapter);
        mZoomViewPager.setCurrentItem(mIndex);
    }

    private void initData() {
        mImageViews = new ImageView[mUrl.size()];
    }

    private void initVpListener() {
        mZoomViewPager.addOnPageChangeListener( new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mZoomTv.setText(++position + "/" + mUrl.size());
            }
        });
    }

    private void initTextView() {
        mZoomTv.setText(++mIndex + "/" + mUrl.size());
    }

    private PagerAdapter mAdapter = new PagerAdapter() {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ZoomImageView imageView = new ZoomImageView(ZoomImageViewActivity.this);
            imageView.setImageResource(mUrl.get(position));
            setIvListener(imageView);
            container.addView(imageView);
            mImageViews[position] = imageView;
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImageViews[position]);
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return mUrl.size();
        }
    };

    public void setIvListener(ZoomImageView iv) {
        iv.setShortClickListener(new ZoomImageView.IvOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
