package com.demo.downrefresh.zidingyiview.paint_1_normal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/5.
 */

public class MyConstomView extends View {
    private Paint mPaint;
    private int radius;
    private boolean isChangeBig = true;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (isChangeBig) {
                if (radius > 300) {
                    isChangeBig = false;
                }
                radius += 10;
            } else {
                if (radius <= 10) {
                    isChangeBig = true;
                }
                radius -= 10;
            }
            mHandler.sendEmptyMessageDelayed(1, 30);
            invalidate();
        }
    };
    private int measuredWidth;
    private int measuredHeight;

    public MyConstomView(Context context) {
        this(context, null);
    }

    public MyConstomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyConstomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        /*
         * 设置画笔样式为描边，圆环嘛……当然不能填充不然就么意思了
         *
         * 画笔样式分三种：
         * 1.Paint.Style.STROKE：描边
         * 2.Paint.Style.FILL_AND_STROKE：描边并填充
         * 3.Paint.Style.FILL：填充
         */
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(10f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();
//        canvas.drawRoundRect(measuredWidth / 2 - radius, measuredHeight / 2 - radius,
//                measuredWidth / 2 + radius, measuredHeight / 2 + radius, 20, 20, mPaint);

//        canvas.drawArc(measuredWidth / 2 - 200, measuredHeight / 2 - 200,
//                measuredWidth / 2 + 200, measuredHeight / 2 + 200, radius, 60, true, mPaint);

        canvas.drawOval(measuredWidth / 2 - radius, measuredHeight / 2 - radius,
                measuredWidth / 2 + radius, measuredHeight / 2 + radius, mPaint);

    }

    public void start() {
        mHandler.sendEmptyMessageDelayed(1, 50);
    }
}
