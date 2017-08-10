package com.demo.downrefresh.zidingyiview.paint_2_colorfilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.demo.downrefresh.R;

/**
 * 这个类的作用是: 使用 paint的setColorFilter方法 ，有三种方法， 较常用的是 MatrixView的子类， Lignt还好，第三种不好用
 * <p>
 * Created by zhaozh on 2017/5/5.
 */

public class ColorFilterMatrixView extends View {

    private Paint mPaint;
    private Bitmap bitmap;// 位图

    float[] f1 = new float[]{
            0.5F, 0, 0, 0, 0,
            0, 0.5F, 0, 0, 0,
            0, 0, 0.5F, 0, 0,
            0, 0, 0, 1, 0,
    };

    float[] f2 = new float[]{
            0.33F, 0.59F, 0.11F, 0, 0,
            0.33F, 0.59F, 0.11F, 0, 0,
            0.33F, 0.59F, 0.11F, 0, 0,
            0, 0, 0, 1, 0,
    };

    float[] f3 = new float[]{
            -1, 0, 0, 1, 1,
            0, -1, 0, 1, 1,
            0, 0, -1, 1, 1,
            0, 0, 0, 1, 0,
    };

    float[] f4 = new float[]{
            0, 0, 1, 0, 0,
            0, 1, 0, 0, 0,
            1, 0, 0, 0, 0,
            0, 0, 0, 1, 0,
    };

    float[] f5 = new float[]{
            0.393F, 0.769F, 0.189F, 0, 0,
            0.349F, 0.686F, 0.168F, 0, 0,
            0.272F, 0.534F, 0.131F, 0, 0,
            0, 0, 0, 1, 0,
    };

    float[] f6 = new float[]{
            1.5F, 1.5F, 1.5F, 0, -1,
            1.5F, 1.5F, 1.5F, 0, -1,
            1.5F, 1.5F, 1.5F, 0, -1,
            0, 0, 0, 1, 0,
    };

    float[] f7 = new float[]{
            1.438F, -0.122F, -0.016F, 0, -0.03F,
            -0.062F, 1.378F, -0.016F, 0, 0.05F,
            -0.062F, -0.122F, 1.483F, 0, -0.02F,
            0, 0, 0, 1, 0,
    };


    ColorMatrix colorMatrix = new ColorMatrix(f7);

    public ColorFilterMatrixView(Context context) {
        this(context, null);
    }

    public ColorFilterMatrixView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorFilterMatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();

        //初始化资源
        initRes(context);
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
//        mPaint.setColorFilter(new LightingColorFilter(0xffff00ff,0x00000000));
//        mPaint.setColorFilter(new PorterDuffColorFilter(Color.red(112), PorterDuff.Mode.ADD));
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.ADD));
    }

    /**
     * 初始化资源
     */
    private void initRes(Context context) {
        // 获取位图
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.a);

        /*
         * 计算位图绘制时左上角的坐标使其位于屏幕中心
         * 屏幕坐标x轴向左偏移位图一半的宽度
         * 屏幕坐标y轴向上偏移位图一半的高度
         */
//        x = MeasureUtil.getScreenSize((Activity) mContext)[0] / 2 - bitmap.getWidth() / 2;
//        y = MeasureUtil.getScreenSize((Activity) mContext)[1] / 2 - bitmap.getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        canvas.drawBitmap(bitmap, measuredWidth / 2 - bitmap.getWidth() / 2,
                measuredHeight / 2 - bitmap.getHeight(), null);

        canvas.drawBitmap(bitmap, measuredWidth / 2 - bitmap.getWidth() / 2,
                measuredHeight / 2, mPaint);
    }
}
