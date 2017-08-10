package com.demo.downrefresh.zidingyiview.paint_6_patheffect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/9.
 */

public class Strok_Cap_Join extends View {

    private Paint mPaint;
    private Path mPath;

    public Strok_Cap_Join(Context context) {
        this(context, null);
    }

    public Strok_Cap_Join(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Strok_Cap_Join(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();

        initPath();
    }

    public void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.RED);

        /**
         * 设置画笔的笔触风格 , 共有三种
         *
         *  就像我们现实世界中的笔，如果你用圆珠笔在纸上戳一点，那么这个点一定是个圆，即便很小，它代表了笔的笔触形状，
         *              如果我们把一支铅笔笔尖削成方形的，那么画出来的线条会是一条弯曲的“矩形”，这就是笔触的意思。
         *
         *  {@link Paint.Cap.ROUND}     圆角的笔触
         *  {@link Paint.Cap.BUTT}     矩形的笔触
         *  {@link Paint.Cap.SQUARE}    矩形的笔触
         */
        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        mPaint.setStrokeCap(Paint.Cap.SQUARE);
//        mPaint.setStrokeCap(Paint.Cap.BUTT);


        /**
         * 设置结合处的连接方式
         *
         * 这个方法用于设置结合处的形态，就像上面的代码中我们虽说是花了一条心电线，
         *          但是这条线其实是由无数条小线拼接成的，拼接处的形状就由该方法指定。
         *
         * {@link Paint.Join.ROUND}     圆角的连接
         * {@link Paint.Join.BEVEL}     在转弯处使用矩形,矩形是圆的切线连接,即把圆角切一部分变成横线
         * {@link Paint.Join.MITER}     矩形连接, 用矩形代替圆角,把圆角变成锐角
         */
//        mPaint.setStrokeJoin(Paint.Join.ROUND);
//        mPaint.setStrokeJoin(Paint.Join.BEVEL);
        mPaint.setStrokeJoin(Paint.Join.MITER);

        /**
         * setShadowLayer(float radius, float dx, float dy, int shadowColor)
         *
         * 为我们绘制的图形添加一个阴影层效果
         */
        mPaint.setShadowLayer(50, 3, 3, Color.BLACK);
    }

    private void initPath() {
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        int i = measuredHeight / 5;
        mPath.moveTo(50, i);
        mPath.lineTo(measuredWidth / 3, i * 2);
        mPath.lineTo(measuredWidth / 3 * 2, i);
        mPath.lineTo(measuredWidth - 50, i * 3);
        canvas.drawPath(mPath, mPaint);
//        canvas.drawLine(100, i, measuredWidth - 100, i, mPaint);
//
//        mPaint.setStrokeCap(Paint.Cap.SQUARE);
//        canvas.drawLine(100, i * 2, measuredWidth - 100, i * 2, mPaint);
//
//        mPaint.setStrokeCap(Paint.Cap.BUTT);
//        canvas.drawLine(100, i * 3, measuredWidth - 100, i * 3, mPaint);
    }
}
