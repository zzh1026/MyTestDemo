package com.demo.downrefresh.zidingyiview.paint_3_xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.demo.downrefresh.R;

/**
 * 这个类的作用是: 使用 paint 的 setXferMode方法 ， 有三种方法，其中两种已经丢弃了（AvoidXfermode, PixelXorXfermode）
 * ， 第三种也不是很常用了，所以这个方法可以放弃（ PorterDuffXfermode）；
 * <p>
 * Created by zhaozh on 2017/5/8.
 */

public class PorterDuffViewMyView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;

    public PorterDuffViewMyView(Context context) {
        this(context, null);
    }

    public PorterDuffViewMyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PorterDuffViewMyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();

        initRes(context);
    }

    private void initRes(Context context) {
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.a2_mask);
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

//        mPaint.setColorFilter(new PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.DARKEN));

        /**
         * 这里的添加方式拥有18 种
         *
         *  跟ColorFilter时候用到的PorterDuff.Mode是一样的
         */
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        canvas.drawBitmap(mBitmap, measuredWidth / 2 - mBitmap.getWidth() / 2,
                measuredHeight / 2 - mBitmap.getHeight() / 2, mPaint);

    }
}
