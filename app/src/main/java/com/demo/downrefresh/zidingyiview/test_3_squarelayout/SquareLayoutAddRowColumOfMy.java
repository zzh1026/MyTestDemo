package com.demo.downrefresh.zidingyiview.test_3_squarelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 这个类的作用是:     SquareLayout的基础上增加了 最大行数和最大列数
 * <p>
 * Created by zhaozh on 2017/5/18.
 */

public class SquareLayoutAddRowColumOfMy extends ViewGroup {
    private static final int ORIENTATION_HORIZONTAL = 0, ORIENTATION_VERTICAL = 1;// 排列方向的常量标识值
    private static final int DEFAULT_MAX_ROW = Integer.MAX_VALUE, DEFAULT_MAX_COLUMN = Integer.MAX_VALUE;// 最大行列默认值

    private int mMaxRow = DEFAULT_MAX_ROW;// 最大行数
    private int mMaxColumn = DEFAULT_MAX_COLUMN;// 最大列数

    private int mOrientation = ORIENTATION_VERTICAL;// 排列方向

    public SquareLayoutAddRowColumOfMy(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 初始化最大行列数
        mMaxRow = mMaxColumn = 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 声明临时变量储存父容器的期望值,(改值最后需要和 getSuggestSize()的推荐大小比较得到最终值)
         */
        int parentDesireWidth = 0;
        int parentDesireHeight = 0;

        // 声明临时变量存储子元素的测量状态
        int childMeasureState = 0;



    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        LinearLayout
        if (getChildCount() > 0) {
            // 声明临时变量存储宽高倍增值
            int multi = 0;

            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);

                //如果为gone则不用摆放,gone则不显示并且不占用空间
                if (child.getVisibility() != View.GONE) {
                    // 获取控件尺寸 ,宽高一致,获取一个即可
                    int childActualSize = child.getMeasuredWidth();

                    // 获取子元素布局参数
                    MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();

                    //margin不需要自己处理, padding 需要自己处理, margin是父容器处理的
                    if (mOrientation == ORIENTATION_HORIZONTAL) {
                        // 确定子元素左上、右下坐标
                        child.layout(
                                getPaddingLeft() + mlp.leftMargin + multi,  //左
                                getPaddingTop() + mlp.topMargin,  //上
                                getPaddingLeft() + mlp.leftMargin + multi + childActualSize,//右
                                getPaddingTop() + mlp.topMargin + childActualSize
                        );

                        // 累加倍增值
                        multi += mlp.leftMargin + childActualSize + mlp.rightMargin;
                    } else if (mOrientation == ORIENTATION_VERTICAL) {  //纵向,宽确定,高不定
                        child.layout(
                                getPaddingLeft() + mlp.leftMargin,
                                getPaddingTop() + mlp.topMargin + multi,
                                getPaddingLeft() + mlp.leftMargin + childActualSize,
                                getPaddingTop() + mlp.topMargin + multi + childActualSize
                        );
                        multi += mlp.topMargin + childActualSize + mlp.bottomMargin;
                    }
                }
            }
        }
    }


    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MarginLayoutParams;
    }
}
