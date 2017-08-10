package com.demo.downrefresh.zidingyiview.test_3_squarelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/18.
 */

public class SquareLayout extends ViewGroup {
    private static final int ORIENTATION_HORIZONTAL = 0, ORIENTATION_VERTICAL = 1;// 排列方向的常量标识值
    private static final int DEFAULT_MAX_ROW = Integer.MAX_VALUE, DEFAULT_MAX_COLUMN = Integer.MAX_VALUE;// 最大行列默认值

    private int mMaxRow = DEFAULT_MAX_ROW;// 最大行数
    private int mMaxColumn = DEFAULT_MAX_COLUMN;// 最大列数

    private int mOrientation = ORIENTATION_VERTICAL;// 排列方向

    public SquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 初始化最大行列数
        mMaxRow = mMaxColumn = 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 声明临时变量储存父容器的期望值,(改值最后需要和 getSuggestSize()的推荐大小比较得到最终值)
         * 该期望值等于 父容器的内边距加上所有子元素的测量宽高和外边距
         *  getPadding +　child.getMeasure + child.getLayoutParams.getMargin
         */
        int parentDesireWidth = 0;
        int parentDesireHeight = 0;

        // 声明临时变量存储子元素的测量状态
        int childMeasureState = 0;

        if (getChildCount() > 0) {
            for (int i = 0; i < getChildCount(); i++) {
                // 获取对应遍历下标的子元素
                View child = getChildAt(i);

                /*
                 * 如果该子元素没有以“不占用空间”的方式隐藏则表示其需要被测量计算
                 */
                if (child.getVisibility() != View.GONE) {

                    // 测量子元素并考量其外边距
                    measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);

                    // 比较子元素测量宽高并比较取其较大值(这是为了取方形的子View)
                    int childMeasureSize = Math.max(child.getMeasuredWidth(), child.getMeasuredHeight());

                    // 重新封装子元素测量规格
                    int childMeasureSpec = MeasureSpec.makeMeasureSpec(childMeasureSize, MeasureSpec.EXACTLY);

                    // 重新测量子元素
                    child.measure(childMeasureSpec, childMeasureSpec);

                    // 获取子元素布局参数
                    MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();

                    /*
                     * 考量外边距计算子元素实际宽高
                     */
                    int childActualWidth = child.getMeasuredWidth() + mlp.leftMargin + mlp.rightMargin;
                    int childActualHeight = child.getMeasuredHeight() + mlp.topMargin + mlp.bottomMargin;

                    /*
                     * 如果为横向排列
                     */
                    if (mOrientation == ORIENTATION_HORIZONTAL) {
                        // 累加子元素的实际宽度
                        parentDesireWidth += childActualWidth;

                        // 获取子元素中高度最大值
                        parentDesireHeight = Math.max(parentDesireHeight, childActualHeight);
                    }

                    /*
                     * 如果为竖向排列
                     */
                    else if (mOrientation == ORIENTATION_VERTICAL) {
                        // 累加子元素的实际高度
                        parentDesireHeight += childActualHeight;

                        // 获取子元素中宽度最大值
                        parentDesireWidth = Math.max(parentDesireWidth, childActualWidth);
                    }

                    // 合并子元素的测量状态
                    childMeasureState = combineMeasuredStates(childMeasureState, child.getMeasuredState());
                }
            }
            /*
             * 考量父容器内边距将其累加到期望值
             */
            parentDesireWidth += getPaddingLeft() + getPaddingRight();
            parentDesireHeight += getPaddingTop() + getPaddingBottom();
//LinearLayout
            /*
             * 尝试比较父容器期望值与Android建议的最小值大小并取较大值
             */
            parentDesireWidth = Math.max(parentDesireWidth, getSuggestedMinimumWidth());
            parentDesireHeight = Math.max(parentDesireHeight, getSuggestedMinimumHeight());
        }

        /**
         * resolveSize方法作用
         * 这个resolveSize方法其实是View提供给我们解算尺寸大小的一个工具方法，其具体实现在API 11
         *      后交由另一个方法resolveSizeAndState也就是我们这一节例子所用到的去处理
         *
         *  public static int resolveSize(int size, int measureSpec) {
         *      return resolveSizeAndState(size, measureSpec, 0) & MEASURED_SIZE_MASK;
         *  }
         */
        // 确定父容器的测量宽高
        setMeasuredDimension(resolveSizeAndState(parentDesireWidth, widthMeasureSpec, childMeasureState),
                resolveSizeAndState(parentDesireHeight, heightMeasureSpec,
                        childMeasureState << MEASURED_HEIGHT_STATE_SHIFT));
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
        return new MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
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
