package com.demo.downrefresh.zidingyiview.paint_2_colorfilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.demo.downrefresh.R;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/8.
 */

public class ColorFilterLigntView extends View {
    private Paint mPaint;
    private Bitmap mBitmap;

    private boolean isClick = false;

    public ColorFilterLigntView(Context context) {
        this(context, null);
    }

    public ColorFilterLigntView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorFilterLigntView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();

        initRes(context);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick) {
                    mPaint.setColorFilter(null);
                    isClick = false;
                } else {
                    mPaint.setColorFilter(new LightingColorFilter(0xffffffff, 0x00ffff00));
                    isClick = true;
                }
                invalidate();
            }
        });
    }

    private void initPaint() {
        mPaint = new Paint();
    }

    private void initRes(Context context) {
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.a);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        canvas.drawBitmap(mBitmap, measuredWidth / 2 - mBitmap.getWidth() / 2,
                measuredHeight / 2 - mBitmap.getHeight(), null);

        canvas.drawBitmap(mBitmap, measuredWidth / 2 - mBitmap.getWidth() / 2,
                measuredHeight / 2, mPaint);
    }
}
