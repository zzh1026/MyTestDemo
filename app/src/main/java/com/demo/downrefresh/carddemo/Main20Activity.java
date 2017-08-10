package com.demo.downrefresh.carddemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.demo.downrefresh.R;
import com.demo.downrefresh.carddemo.adapter.ViewPagerAdapter;
import com.demo.downrefresh.carddemo.adapter.ViewPagerTransFormer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页
 *
 * @author w.w
 */
public class Main20Activity extends Activity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @BindDimen(R.dimen.demen15)
    float dimen;

    List<Integer> list;
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPagerTransFormer mViewPagerTransFormer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main20);
        ButterKnife.bind(this);

        initList();
        viewpager.setPageMargin((int) dimen);

        mViewPagerAdapter = new ViewPagerAdapter(list, this);
        viewpager.setAdapter(mViewPagerAdapter);
        viewpager.setPageTransformer(false, mViewPagerTransFormer);
        viewpager.setOffscreenPageLimit(2);

    }

    private void initList() {
        list = new ArrayList<>();
        list.add(R.drawable.vip_center_item_1);
        list.add(R.drawable.vip_center_item_2);
        list.add(R.drawable.vip_center_item_3);
        list.add(R.drawable.vip_center_item_4);
        list.add(R.drawable.vip_center_item_5);
        list.add(R.drawable.vip_center_item_6);
        list.add(R.drawable.vip_center_item_7);
        list.add(R.drawable.vip_center_item_8);
    }
}