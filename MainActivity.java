package com.example.wmz.photoviewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ViewPager mViewPager;
    private ArrayList<Integer> listData =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = ((ViewPager) findViewById(R.id.vp));
        initListData();
        initViewPager();
    }

    private void initListData() {
        listData.add(R.mipmap.a);
        listData.add(R.mipmap.b);
        listData.add(R.mipmap.c);
        listData.add(R.mipmap.d);
    }

    private void initViewPager() {

        mViewPager.setAdapter(adapter);
    }
    PagerAdapter adapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return listData==null?0:listData.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(((View) object));
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView = new ImageView(container.getContext());
            imageView.setImageResource(listData.get(position));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(MainActivity.this, ZoomImageViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("position", position + "");
                    bundle.putIntegerArrayList("list", listData);
                    it.putExtras(bundle);
                    startActivity(it);
                }
            });
            container.addView(imageView);
            return  imageView;
        }
    };
}
