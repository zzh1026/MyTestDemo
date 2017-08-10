package com.demo.downrefresh.view;

import android.content.Context;
import android.util.AttributeSet;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/3/27.
 */

public class MyFefreshLayout extends TwinklingRefreshLayout {
    public MyFefreshLayout(Context context) {
        super(context);
    }

    public MyFefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (mHeadLayout != null) {
            if (enableRefresh) {
                mHeadLayout.setVisibility(VISIBLE);
            } else {
                mHeadLayout.setVisibility(INVISIBLE);
            }
        }
    }



    @Override
    public void setEnableRefresh(boolean enableRefresh1) {
        super.setEnableRefresh(enableRefresh1);
        if (mHeadLayout != null) {
            if (enableRefresh1) {
                mHeadLayout.setVisibility(VISIBLE);
            } else {
                mHeadLayout.setVisibility(INVISIBLE);
            }
        }
    }
}
