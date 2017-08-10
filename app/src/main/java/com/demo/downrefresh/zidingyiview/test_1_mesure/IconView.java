package com.demo.downrefresh.zidingyiview.test_1_mesure;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.demo.downrefresh.R;
import com.demo.downrefresh.utils.DimensUtils;
import com.demo.downrefresh.utils.ScreenSizeUtils;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/16.
 */

public class IconView extends View {

    private Bitmap mBitmap;// 位图
    private TextPaint mPaint;// 绘制文本的画笔
    private String mStr;// 绘制的文本

    private float mTextSize;// 画笔的文本尺寸
    private int mTextMarginTop;//字体距离bitmap的距离

    /**
     * 宽高枚举类
     */
    private enum Ratio {
        WIDTH, HEIGHT
    }

    public IconView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 计算参数
        calArgs(context);

        // 初始化
        init();
    }

    /**
     * 参数计算
     *
     * @param context
     */
    private void calArgs(Context context) {
        // 获取屏幕宽
        int widthPixels = ScreenSizeUtils.getScreenSize(context).widthPixels;
        //计算文本宽度
        mTextSize = widthPixels * 1 / 10F;

        mTextMarginTop = (int) (widthPixels * 1 / 20F);
    }

    /**
     * 初始化
     */
    private void init() {
        /*
         * 获取Bitmap
         */
        if (null == mBitmap) {
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hehe);
        }

        /*
         * 为mStr赋值
         */
        if (null == mStr || mStr.trim().length() == 0) {
            mStr = "新浪微博";
        }

        /*
         * 初始化画笔并设置参数
         */
        mPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.LINEAR_TEXT_FLAG);
        mPaint.setColor(Color.LTGRAY);
        mPaint.setTextSize(mTextSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 设置测量后的尺寸
        setMeasuredDimension(getMeasureSize(widthMeasureSpec, Ratio.WIDTH),
                getMeasureSize(heightMeasureSpec, Ratio.HEIGHT));
    }

    /**
     * 获取测量后的尺寸
     *
     * @param measureSpec 测量规格
     * @param ratio       宽高标识
     * @return 宽或高的测量值
     */
    private int getMeasureSize(int measureSpec, Ratio ratio) {
        // 声明临时变量保存测量值
        int result = 0;

        //获取mode和size
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.EXACTLY: //父类已经计算好了, 直接赋值
                result = size;
                break;
            default:    // 默认情况下将UNSPECIFIED和AT_MOST一并处理
                if (ratio == Ratio.WIDTH) {
                    int paddintWidth = getPaddingLeft() + getPaddingRight();
                    float textWidth = mPaint.measureText(mStr);
                    result = textWidth >= mBitmap.getWidth() ? (int) textWidth + paddintWidth :
                            mBitmap.getWidth() + paddintWidth;
                } else if (ratio == Ratio.HEIGHT) {
                    result = (int) ((mPaint.descent() - mPaint.ascent()) * 2 +
                            mBitmap.getHeight() + getPaddingTop() + getPaddingBottom() + mTextMarginTop * 2);
                }

                if (mode == MeasureSpec.AT_MOST) {
                    result = Math.min(result, size);
                }
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /*
         * 绘制
         * 参数就不做单独处理了因为只会Draw一次不会频繁调用
         */
        canvas.drawBitmap(mBitmap, getWidth() / 2 - mBitmap.getWidth() / 2,
                getHeight() / 2 - mBitmap.getHeight() / 2, null);

        //float x 和 float y表示字体基线的位置
        canvas.drawText(mStr, getWidth() / 2,
                getHeight() / 2 + mBitmap.getHeight() / 2 - mPaint.ascent() + mTextMarginTop, mPaint);
    }
}
