package com.demo.downrefresh.zidingyiview.test_1_mesure;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.orhanobut.logger.Logger;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/16.
 * <p>
 * MeasureSpec类中的三个Mode常量值的意义:
 * <p>
 * UNSPECIFIED表示未指定，爹不会对儿子作任何的束缚，儿子想要多大都可以；
 * EXACTLY表示完全的，意为儿子多大爹心里有数，爹早已算好了；
 * AT_MOST表示至多，爹已经为儿子设置好了一个最大限制，儿子你不能比这个值大，不能再多了
 */

public class ImgView extends View {
    private Bitmap mBitmap;// 位图对象

    public ImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 声明一个临时变量来存储计算出的测量值
        int resultWidth = 0;

        // 获取宽度测量规格中的mode
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);

        // 获取宽度测量规格中的size
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);

         /*
         * 如果爹心里有数
         */
        if (modeWidth == MeasureSpec.EXACTLY) {
            // 那么儿子也不要让爹难做就取爹给的大小吧
            resultWidth = sizeWidth;
        }

         /*
         * 如果爹心里没数
         */
        else {
            // 那么儿子可要自己看看自己需要多大了
            resultWidth = mBitmap.getWidth() + getPaddingLeft() + getPaddingRight();

            /*
             * 如果爹给儿子的是一个限制值
             */
            if (modeWidth == MeasureSpec.AT_MOST) {
                // 那么儿子自己的需求就要跟爹的限制比比看谁小要谁
                resultWidth = Math.min(resultWidth, sizeWidth);
            }
        }

        int resultHeight = 0;
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (modeHeight == MeasureSpec.EXACTLY) {
            resultHeight = sizeHeight;
        } else {
            resultHeight = mBitmap.getHeight() + getPaddingTop() + getPaddingBottom();
            if (modeHeight == MeasureSpec.AT_MOST) {
                resultHeight = Math.min(resultHeight, sizeHeight);
            }
        }

        // 设置测量尺寸
        setMeasuredDimension(resultWidth, resultHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Logger.i("运行到了 onDraw");
        // 绘制位图
        if (mBitmap != null)
            canvas.drawBitmap(mBitmap, getPaddingLeft(), getPaddingTop(), null);
    }

    /**
     * 设置位图
     *
     * @param bitmap 位图对象
     */
    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
        //requestLayout方法的意义在于如果你的操作有可能会让控件的尺寸或位置发生改变那么就可以调用该方法请求布局，
        // 调用该方法后framework会尝试调用measure对控件重新测量：
        requestLayout();
        invalidate();
        Logger.i("运行到了 setBitmap");
    }
}
