package com.demo.downrefresh.anddroid_5_0.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/7/18.
 * <p>
 * 继承自 FloatingActionButton.Behavior
 */

public class MyBehavior2 extends FloatingActionButton.Behavior {

    public MyBehavior2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 嵌套滑动事件开始
     *
     * @param coordinatorLayout 总布局
     * @param child             布局中的直接子 FloatingActionButton view
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes
     * @return 根据返回值确定我们关心哪个方向的滑动（x轴/y轴)，这里我们关心的是y轴方向
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    /**
     * 嵌套滑动正在进行中
     * 参数有点多，由于这里我们只关心y轴方向的滑动，所以简单测试了dyConsumed、dyUnconsumed
     * dyConsumed > 0 && dyUnconsumed == 0 上滑中
     * dyConsumed == 0 && dyUnconsumed > 0 到边界了还在上滑
     * <p>
     * dyConsumed < 0 && dyUnconsumed == 0 下滑中
     * dyConsumed == 0 && dyUnconsumed < 0 到边界了还在下滑
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (((dyConsumed > 0 && dyUnconsumed == 0)
                || (dyConsumed == 0 && dyUnconsumed > 0))
                && child.getVisibility() != View.INVISIBLE) {// 上滑隐藏
            child.setVisibility(View.INVISIBLE);
        } else if (((dyConsumed < 0 && dyUnconsumed == 0)
                || (dyConsumed == 0 && dyUnconsumed < 0))
                && child.getVisibility() != View.VISIBLE) {//下滑显示
            child.setVisibility(View.VISIBLE);
        }
    }
}
