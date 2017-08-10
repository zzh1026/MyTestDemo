package com.demo.downrefresh.zidingyiview.paint_3_xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.demo.downrefresh.R;

/**
 * 这个类的作用是: 使用 抠图 的方法把需要的图抠出来
 * <p>
 * Created by zhaozh on 2017/5/8.
 */

public class PorterDuffDisInView extends View {

    //自定义, 去掉白色的地方
    float[] f8 = new float[]{
            0, 0, 0, 0, 1,
            0, 0, 0, 0, 1,
            0, 0, 0, 0, 1,
            0, 0, 0, 1, 0,
    };

    private Paint mPaint;
    private Bitmap bitmapDis, bitmapSrc;// 位图

    private PorterDuffXfermode porterDuffXfermode;// 图形混合模式

    public PorterDuffDisInView(Context context) {
        this(context, null);
    }

    public PorterDuffDisInView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PorterDuffDisInView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 实例化混合模式
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

        initPaint();

        initRes(context);

    }

    private void initRes(Context context) {
        bitmapDis = BitmapFactory.decodeResource(context.getResources(), R.drawable.a2);
        bitmapSrc = BitmapFactory.decodeResource(context.getResources(), R.drawable.a2_mask);
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

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

        /*
         * 将绘制操作保存到新的图层（更官方的说法应该是离屏缓存）我们将在1/3中学习到Canvas的全部用法这里就先follow me
         */
        int sc = canvas.saveLayer(0, 0, measuredWidth, measuredHeight, null, Canvas.ALL_SAVE_FLAG);

        /**
         * 先绘制dis的图
         */
//        mPaint.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(f8)));
        canvas.drawBitmap(bitmapDis, measuredWidth / 2 - bitmapDis.getWidth() / 2,
                measuredHeight / 2 - bitmapDis.getHeight() / 2, mPaint);

        // 设置混合模式
        mPaint.setXfermode(porterDuffXfermode);
//        mPaint.setColorFilter(null);

        // 再绘制src源图
        canvas.drawBitmap(bitmapSrc, measuredWidth / 2 - bitmapDis.getWidth() / 2,
                measuredHeight / 2 - bitmapDis.getHeight() / 2, mPaint);

        // 还原混合模式
        mPaint.setXfermode(null);

        // 还原画布
        canvas.restoreToCount(sc);

    }
}
