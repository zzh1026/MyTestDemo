package com.demo.downrefresh.zidingyiview.test_2_measure_viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/17.
 * <p>
 * 重要方法:
 * measureChildren、       测量所有子控件
 * measureChild            测量子控件
 * measureChildWithMargins 测量有margin参数的子控件
 * getChildMeasureSpec     获取子控件的测量结果
 * <p>
 * ViewGroup    提供对子元素测量的方法从 measureChildren 开始
 * <p>
 * View的大小由其父容器的测量规格MeasureSpec和View本身的布局参数LayoutParams共同决定，但是即便如此，
 * 最终封装的测量规格也是一个期望值，究竟有多大还是我们调用setMeasuredDimension方法设置的。
 * <p>
 * LayoutParams中MATCH_PARENT和WRAP_CONTENT均为负数
 *
 * padding 自己获取,  margin 有父类viewgroup 的 MarginLayoutParams 处理
 * 1,需要自定义 MarginLayoutParams 的子类 , 这样才能自己处理child的 margin值
 * 2,重写关于layoutparam有关的三个方法 和一个判断是否instance 的方法;
 *
 *
 * Android对Viewgroup的测量由两方面构成：
 *
 * 一是对父容器和子元素大小尺寸的测量主要体现在onMeasure方法，
 *
 * 二是对父容器的子元素在其区域内的定位主要体现在onLayout方法。
 */

public class MeasureViewGroup extends ViewGroup {


    public MeasureViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /* test 1             */
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        /*
//         * 如果有子元素
//         */
//        if (getChildCount() > 0) {
//            // 那么对子元素进行测量
//            measureChildren(widthMeasureSpec, heightMeasureSpec);
//        }
        /* test 1             */

        // 声明临时变量存储父容器的期望值
        int parentDesireWidth = 0;
        int parentDesireHeight = 0;

        /*
         * 如果有子元素
         */
        if (getChildCount() > 0) {
            // 那么遍历子元素并对其进行测量
            for (int i = 0; i < getChildCount(); i++) {

                // 获取子元素
                View child = getChildAt(i);

                // 测量子元素并考虑外边距 ,这个方法后就可以获取到测量后的child的宽高了
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);

                // 获取子元素的布局参数
                CustomLayoutParams clp = (CustomLayoutParams) child.getLayoutParams();

                // 计算父容器的期望值
                parentDesireWidth += child.getMeasuredWidth() + clp.leftMargin + clp.rightMargin;
                parentDesireHeight += child.getMeasuredHeight() + clp.topMargin + clp.bottomMargin;
            }

            // 考虑父容器的内边距
            parentDesireWidth += getPaddingLeft() + getPaddingRight();
            parentDesireHeight += getPaddingTop() + getPaddingBottom();

            // 尝试比较建议最小值和期望值的大小并取大值
            parentDesireWidth = Math.max(parentDesireWidth, getSuggestedMinimumWidth());
            parentDesireHeight = Math.max(parentDesireHeight, getSuggestedMinimumHeight());
        }

        // 设置最终测量值O resolveSize 方法表示, 将spec中的size替换成参数所给的size
        setMeasuredDimension(resolveSize(parentDesireWidth, widthMeasureSpec),
                resolveSize(parentDesireHeight, heightMeasureSpec));
    }

    /**
     * @param changed 是否与上次的位置不同
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        // 获取父容器内边距
        int parentPaddingLeft = getPaddingLeft();
        int parentPaddingTop = getPaddingTop();

        /*
         * 如果有子元素
         */
        if (getChildCount() > 0) {

            // 声明一个临时变量存储高度倍增值
            int mutilHeight = 0;

            // 那么遍历子元素并对其进行定位布局
            for (int i = 0; i < getChildCount(); i++) {
                // 获取一个子元素
                View child = getChildAt(i);

                CustomLayoutParams clp = (CustomLayoutParams) child.getLayoutParams();

                // 通知子元素进行布局
//                child.layout(parentPaddingLeft,
//                        mutilHeight + parentPaddingTop,
//                        child.getMeasuredWidth() + parentPaddingLeft,
//                        child.getMeasuredHeight() + mutilHeight + parentPaddingTop);
                // 此时考虑父容器内边距和子元素外边距的影响
                child.layout(parentPaddingLeft + clp.leftMargin,
                        mutilHeight + parentPaddingTop + clp.topMargin,
                        child.getMeasuredWidth() + parentPaddingLeft + clp.leftMargin,
                        child.getMeasuredHeight() + mutilHeight + parentPaddingTop + clp.topMargin);

                // 改变高度倍增值
//                mutilHeight += child.getMeasuredHeight();
                mutilHeight += child.getMeasuredHeight() + clp.topMargin + clp.bottomMargin;
            }
        }
    }

    /**
     * 生成默认的布局参数
     */
    @Override
    protected CustomLayoutParams generateDefaultLayoutParams() {
        return new CustomLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**
     * 生成布局参数
     * 将布局参数包装成我们的
     */
    @Override
    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return new CustomLayoutParams(p);
    }

    /**
     * 生成布局参数
     * 从属性配置中生成我们的布局参数
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new CustomLayoutParams(getContext(), attrs);
    }

    /**
     * 检查当前布局参数是否是我们定义的类型这在code声明布局参数时常常用到
     */
    @Override
    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return p instanceof CustomLayoutParams;
    }

    /**
     * 所有继承viewgroup的自定义view 需要重写 onLayout 和 实现
     */
    public static class CustomLayoutParams extends MarginLayoutParams{

        public CustomLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public CustomLayoutParams(int width, int height) {
            super(width, height);
        }

        public CustomLayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public CustomLayoutParams(LayoutParams source) {
            super(source);
        }
    }

}
