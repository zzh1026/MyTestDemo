package com.demo.downrefresh.carddemo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/1/21.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<Integer> list;
    private Context mContext;

    public ViewPagerAdapter(List<Integer> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(mContext);
        iv.setImageResource(list.get(position));
        container.addView(iv);
        return iv;
    }
}
