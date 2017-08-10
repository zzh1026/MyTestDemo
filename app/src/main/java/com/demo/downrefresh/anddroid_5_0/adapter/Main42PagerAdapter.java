package com.demo.downrefresh.anddroid_5_0.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.demo.downrefresh.R;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/7/17.
 */

public class Main42PagerAdapter extends PagerAdapter {
    private Context mContext;

    public Main42PagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(mContext);
        iv.setImageResource(R.drawable.girl);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    /**
     * 这种是解决tablayout关联viewpager后tab不显示的第一种方法,重写getPageTitle
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return "第" + position + "0 个";
    }
}
