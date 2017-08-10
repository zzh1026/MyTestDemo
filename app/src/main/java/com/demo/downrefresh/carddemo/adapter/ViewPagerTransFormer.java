package com.demo.downrefresh.carddemo.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/1/21.
 */

public class ViewPagerTransFormer implements ViewPager.PageTransformer {
    Context context;
    private float elevation;

    private static final float MIN_SCALE = 0.7f;
    private static final float MIN_ALPHA = 0.5f;
    private float alpha;

    public ViewPagerTransFormer(Context context) {
        this.context = context;
        elevation = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                20, context.getResources().getDisplayMetrics());
    }

    /**
     * position取值特点：
     * 假设页面从0～1，则：
     * 第一个页面position变化为[0,-1]
     * 第二个页面position变化为[1,0]
     *
     * @param page
     * @param position
     */
    @Override
    public void transformPage(View page, float position) {
//        if (position < 0 || position > 2) {
//            page.setAlpha(MIN_ALPHA);
//            page.setScaleX(MIN_SCALE);
//            page.setScaleY(MIN_SCALE);
//        } else {
//            if (position < 0.9 || position > 1.1) {  //向右滑动 [从 0 --> -1]
//                alpha = 1 - Math.abs(position);
//                page.setAlpha(alpha > MIN_ALPHA ? alpha : MIN_ALPHA);
//                page.setScaleX(alpha > MIN_SCALE ? alpha : MIN_SCALE);
//                page.setScaleY(alpha > MIN_SCALE ? alpha : MIN_SCALE);
//            } else {
//                page.setAlpha(1f);
//                page.setScaleX(1f);
//                page.setScaleY(1f);
//            }
//        }
    }
}
