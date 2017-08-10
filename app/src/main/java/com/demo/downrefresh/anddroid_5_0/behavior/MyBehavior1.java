package com.demo.downrefresh.anddroid_5_0.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.demo.downrefresh.anddroid_5_0.wedgit.MyDragTextView;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/7/18.
 */

public class MyBehavior1 extends CoordinatorLayout.Behavior {

    private int width;

    public MyBehavior1(Context context, AttributeSet attrs) {
        super(context, attrs);
        width = context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 确定依赖关系
     *
     * @param child      要执行动作的View
     * @param dependency child要依赖的View，也就是child要监听的View
     * @return 根据逻辑确定依赖关系
     */
    @Override
        public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof MyDragTextView;
    }

    /**
     * dependency状态（大小、位置、显示与否等）发生变化时该方法执行
     * 在这里我们定义child要执行的具体动作
     *
     * @return child是否要执行相应动作
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        int top = dependency.getTop();
        int left = dependency.getLeft();

        int x = width - left - child.getWidth();
        int y = top;

        setPosition(child, x, y);
        return true;
    }


    private void setPosition(View child, int x, int y) {
        CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) child.getLayoutParams();
        layoutParams.leftMargin = x;
        layoutParams.topMargin = y;
        child.setLayoutParams(layoutParams);
    }
}
