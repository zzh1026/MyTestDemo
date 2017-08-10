package com.demo.downrefresh.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toolbar;

import com.demo.downrefresh.R;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/4.
 */

public class CostomMyView extends View {

    private Paint mPaint;
    private int mStrokeWidth = 10;

    private int mRadiu; //半径

    private Bitmap bitmap;// 位图

    // 生成色彩矩阵
    ColorMatrix colorMatrix = new ColorMatrix(new float[]{
            0.393F, 0.769F, 0.189F, 0, 0,
            0.349F, 0.686F, 0.168F, 0, 0,
            0.272F, 0.534F, 0.131F, 0, 0,
            0, 0, 0, 1, 0,
    });

    public CostomMyView(Context context) {
        this(context, null);
    }

    public CostomMyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CostomMyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();

        initResource(context);
    }

    private void initResource(Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.vip_center_item_3);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
//        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setStrokeWidth(mStrokeWidth);
////        mPaint.setColor(Color.RED);
//        mPaint.setColor(Color.argb(255, 255, 128, 103));
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mPaint.setPathEffect(new PathEffect());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(Color.rgb(80, 80, 80));
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadiu / 3 * 2, mPaint);
//        mRadiu = getWidth() / 2;
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadiu - mStrokeWidth, mPaint);

        canvas.drawBitmap(bitmap, 0, 0, mPaint);
    }

    public synchronized void setRadius(int radiu) {
        this.mRadiu = radiu;
        invalidate();
    }
}
