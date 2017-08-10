package com.demo.downrefresh.zidingyiview.test_3_squarelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.demo.downrefresh.R;

/**
 * 这个类的作用是:     SquareLayout的基础上增加了 最大行数和最大列数
 * <p>
 * Created by zhaozh on 2017/5/18.
 */

public class SquareLayoutAddRowColum extends ViewGroup {
    private static final int ORIENTATION_HORIZONTAL = 0, ORIENTATION_VERTICAL = 1;// 排列方向的常量标识值
    private static final int DEFAULT_MAX_ROW = Integer.MAX_VALUE, DEFAULT_MAX_COLUMN = Integer.MAX_VALUE;// 最大行列默认值

    private int mMaxRow = DEFAULT_MAX_ROW;// 最大行数
    private int mMaxColumn = DEFAULT_MAX_COLUMN;// 最大列数

    private int mOrientation = ORIENTATION_VERTICAL;// 排列方向

    public SquareLayoutAddRowColum(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 初始化最大行列数
        mMaxRow = mMaxColumn = 2;

        /*
         * 获取xml对应属性
         */
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ActionBar);
//        mGravity = a.getInt(R.styleable.SquareLayout_my_gravity, 0);
//        a.getf
        a.recycle();
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

            // 声明两个一维数组存储子元素宽高数据
            int[] childWidths = new int[getChildCount()];
            int[] childHeights = new int[getChildCount()];

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
                     * 考量外边距计算子元素实际宽高并将数据存入数组
                     */
                    childWidths[i] = child.getMeasuredWidth() + mlp.leftMargin + mlp.rightMargin;
                    childHeights[i] = child.getMeasuredHeight() + mlp.topMargin + mlp.bottomMargin;

                    // 合并子元素的测量状态
                    childMeasureState = combineMeasuredStates(childMeasureState, child.getMeasuredState());
                }
            }

            // 声明临时变量存储行/列宽高
            int indexMultiWidth = 0, indexMultiHeight = 0;

            /*
             * 如果为横向排列
             */
            if (mOrientation == ORIENTATION_HORIZONTAL) {
                /*
                 * 如果子元素数量大于限定值则进行折行计算
                 */
                if (getChildCount() > mMaxColumn) {

                    // 计算产生的行数
                    int row = getChildCount() / mMaxColumn;

                    // 计算余数
                    int remainder = getChildCount() % mMaxColumn;

                    // 声明临时变量存储子元素宽高数组下标值
                    int index = 0;

                    /*
                     * 遍历数组计算父容器期望宽高值
                     */
                    for (int x = 0; x < row; x++) {
                        for (int y = 0; y < mMaxColumn; y++) {
                            // 单行宽度累加
                            indexMultiWidth += childWidths[index];

                            // 单行高度取最大值
                            indexMultiHeight = Math.max(indexMultiHeight, childHeights[index++]);
                        }
                        // 每一行遍历完后将该行宽度与上一行宽度比较取最大值
                        parentDesireWidth = Math.max(parentDesireWidth, indexMultiWidth);

                        // 每一行遍历完后累加各行高度
                        parentDesireHeight += indexMultiHeight;

                        // 重置参数
                        indexMultiWidth = indexMultiHeight = 0;
                    }

                     /*
                     * 如果有余数表示有子元素未能占据一行
                     */
                    if (remainder != 0) {
                        /*
                         * 遍历剩下的这些子元素将其宽高计算到父容器期望值
                         */
                        for (int i = getChildCount() - remainder; i < getChildCount(); i++) {
                            indexMultiWidth += childWidths[i];
                            indexMultiHeight = Math.max(indexMultiHeight, childHeights[i]);
                        }
                        parentDesireWidth = Math.max(parentDesireWidth, indexMultiWidth);
                        parentDesireHeight += indexMultiHeight;
                        indexMultiWidth = indexMultiHeight = 0;
                    }
                }

                /*
                 * 如果子元素数量还没有限制值大那么直接计算即可不须折行
                 */
                else {
                    for (int i = 0; i < getChildCount(); i++) {
                        // 累加子元素的实际高度
                        parentDesireHeight += childHeights[i];

                        // 获取子元素中宽度最大值
                        parentDesireWidth = Math.max(parentDesireWidth, childWidths[i]);
                    }
                }
            }

            /*
             * 如果为竖向排列
             */
            else if (mOrientation == ORIENTATION_VERTICAL) {
                if (getChildCount() > mMaxRow) {
                    int column = getChildCount() / mMaxRow;
                    int remainder = getChildCount() % mMaxRow;
                    int index = 0;

                    for (int x = 0; x < column; x++) {
                        for (int y = 0; y < mMaxRow; y++) {
                            indexMultiHeight += childHeights[index];
                            indexMultiWidth = Math.max(indexMultiWidth, childWidths[index++]);
                        }
                        parentDesireHeight = Math.max(parentDesireHeight, indexMultiHeight);
                        parentDesireWidth += indexMultiWidth;
                        indexMultiWidth = indexMultiHeight = 0;
                    }

                    if (remainder != 0) {
                        for (int i = getChildCount() - remainder; i < getChildCount(); i++) {
                            indexMultiHeight += childHeights[i];
                            indexMultiWidth = Math.max(indexMultiHeight, childWidths[i]);
                        }
                        parentDesireHeight = Math.max(parentDesireHeight, indexMultiHeight);
                        parentDesireWidth += indexMultiWidth;
                        indexMultiWidth = indexMultiHeight = 0;
                    }
                } else {
                    for (int i = 0; i < getChildCount(); i++) {
                        // 累加子元素的实际宽度
                        parentDesireWidth += childWidths[i];

                        // 获取子元素中高度最大值
                        parentDesireHeight = Math.max(parentDesireHeight, childHeights[i]);
                    }
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

            // 指数倍增值
            int indexMulti = 1;

            // 声明临时变量存储行/列宽高
            int indexMultiWidth = 0, indexMultiHeight = 0;

            // 声明临时变量存储行/列临时宽高
            int tempHeight = 0, tempWidth = 0;

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
                       /*
                         * 如果子元素数量比限定值大
                         */
                        if (getChildCount() > mMaxColumn) {
                            /*
                             * 根据当前子元素进行布局
                             */
                            if (i < mMaxColumn * indexMulti) {
                                child.layout(getPaddingLeft() + mlp.leftMargin + indexMultiWidth,
                                        getPaddingTop() + mlp.topMargin + indexMultiHeight,
                                        getPaddingLeft() + mlp.leftMargin + indexMultiWidth + childActualSize,
                                        getPaddingTop() + mlp.topMargin + indexMultiHeight + childActualSize);

                                indexMultiWidth += mlp.leftMargin + childActualSize + mlp.rightMargin;
                                tempHeight = Math.max(tempHeight, childActualSize) + mlp.topMargin + mlp.bottomMargin;

                                 /*
                                 * 如果下一次遍历到的子元素下标值大于限定值,说明该下一行了
                                 */
                                if (i + 1 >= mMaxColumn * indexMulti) {
                                    // 那么累加高度到高度倍增值
                                    indexMultiHeight += tempHeight;

                                    // 重置宽度倍增值
                                    indexMultiWidth = 0;

                                    //增加指数倍增值
                                    indexMulti++;
                                }
                            }
                        } else {    //getChildCount() <= mMaxColumn 说明只需要一行就行了
                            // 确定子元素左上、右下坐标
                            child.layout(getPaddingLeft() + mlp.leftMargin + multi,
                                    getPaddingTop() + mlp.topMargin,
                                    getPaddingLeft() + mlp.leftMargin + multi + childActualSize,
                                    getPaddingTop() + mlp.topMargin + childActualSize);

                            // 累加倍增值
                            multi += childActualSize + mlp.leftMargin + mlp.rightMargin;
                        }
                    } else if (mOrientation == ORIENTATION_VERTICAL) {  //纵向,宽确定,高不定
                        if (getChildCount() > mMaxRow) {
                            if (i < mMaxRow * indexMulti) {
                                child.layout(getPaddingLeft() + mlp.leftMargin + indexMultiWidth,
                                        getPaddingTop() + mlp.topMargin + indexMultiHeight,
                                        getPaddingLeft() + mlp.leftMargin + indexMultiWidth + childActualSize,
                                        getPaddingTop() + mlp.topMargin + indexMultiHeight + childActualSize);

                                indexMultiHeight += childActualSize + mlp.topMargin + mlp.bottomMargin;

                                tempWidth = Math.max(tempWidth, childActualSize) + mlp.leftMargin + mlp.rightMargin;

                                if (i + 1 >= mMaxRow * indexMulti) {
                                    indexMultiWidth += tempWidth;
                                    indexMultiHeight = 0;
                                    indexMulti++;
                                }
                            }
                        } else { //getChildCount() <= mMaxRow
                            child.layout(
                                    getPaddingLeft() + mlp.leftMargin,
                                    getPaddingTop() + mlp.topMargin + multi,
                                    getPaddingLeft() + mlp.leftMargin + childActualSize,
                                    getPaddingTop() + mlp.topMargin + multi + childActualSize);
                            multi += mlp.topMargin + childActualSize + mlp.bottomMargin;
                        }
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

    /**
     * 其作用是告诉framework我们当前的布局不是一个滚动的布局
     *
     * Android很多的布局控件中都会重写这么一个方法,这个是有点必要的.不需要滚动
     *
     * @return
     */
    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
