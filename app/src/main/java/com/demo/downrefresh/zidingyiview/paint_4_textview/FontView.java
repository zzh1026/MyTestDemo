package com.demo.downrefresh.zidingyiview.paint_4_textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/8.
 */

public class FontView extends View {

    private static final String TEXT = "ap爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓";
    private Paint mPaint, linePaint;// 画笔

    private Paint.FontMetrics mFontMetrics;// 文本测量对象

    private int baseX, baseY;// Baseline绘制的XY坐标

    public FontView(Context context) {
        this(context, null);
    }

    public FontView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FontView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 初始化画笔
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        // 实例化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(50);
        mPaint.setColor(Color.BLACK);

        mFontMetrics = mPaint.getFontMetrics();

//        Log.d("hehe", "ascent：" + mFontMetrics.ascent);
//        Log.d("hehe", "top：" + mFontMetrics.top);
//        Log.d("hehe", "leading：" + mFontMetrics.leading);
//        Log.d("hehe", "descent：" + mFontMetrics.descent);
//        Log.d("hehe", "bottom：" + mFontMetrics.bottom);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(1);
        linePaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 计算Baseline绘制的起点X轴坐标
        baseX = (int) (canvas.getWidth() / 2 - mPaint.measureText(TEXT) / 2);

        // 计算Baseline绘制的Y坐标
        baseY = (int) ((canvas.getHeight() / 2) - ((mPaint.descent() + mPaint.ascent()) / 2));
//        baseY = canvas.getHeight() / 2;

        canvas.drawText(TEXT, baseX, baseY, mPaint);

        // 为了便于理解我们在画布中心处绘制一条中线
        canvas.drawLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2, linePaint);
    }
}
