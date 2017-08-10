package com.demo.downrefresh.zidingyiview.paint_3_xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
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

public class PorterDuffView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;

    public PorterDuffView(Context context) {
        this(context, null);
    }

    public PorterDuffView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PorterDuffView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();

        initRes(context);
    }

    private void initRes(Context context) {
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.a);
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.RED);

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

        // 设置画布颜色为黑色以便我们更好地观察
        canvas.drawColor(Color.BLACK);

/*
         * 画出左右上方两个正方形
         * 其中左边的的为src右边的为dis
         */
        canvas.drawBitmap(Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888),
                measuredWidth / 2 - 200, measuredHeight / 2 - 200, mPaint);
        canvas.drawBitmap(Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888),
                measuredWidth / 2, measuredHeight / 2 - 200, mPaint);

        /*
         * 将绘制操作保存到新的图层（更官方的说法应该是离屏缓存）我们将在1/3中学习到Canvas的全部用法这里就先follow me
         */
        int sc = canvas.saveLayer(0, 0, measuredWidth, measuredHeight, null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888),
                measuredWidth / 2 - 200, measuredHeight / 2 - 200, mPaint);

        // 设置混合模式
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.ADD));

        canvas.drawBitmap(Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888),
                measuredWidth / 2, measuredHeight / 2 - 200, mPaint);

        // 还原混合模式
        mPaint.setXfermode(null);

        // 还原画布
        canvas.restoreToCount(sc);

    }
}
