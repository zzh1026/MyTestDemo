package com.demo.downrefresh.zidingyiview.canvas_1_normal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/11.
 */


/**
 * Canvas的本质:Canvas是画布，但是我们真的是在Canvas上画东西吗?  不是 , canvas是用来在 bitmap上作图的
 *
 * 获得canvas 的方法 :
 *      共有三种: 一般常用的有两种
 *
 *      1,new一个Canvas  Canvas canvas1 = new Canvas(Bitmap); ,其中canvas也可以通过setBitmap来设置操作的bitmap
 *      2,在View的onDraw方法里获取到的canvas参数
 *      3,在performTraversals方法中ViewRootImpl就会去创建Surface，
 *              而此后的渲染则可以通过Surface的lockCanvas方法获取Surface的Canvas来进行
 *
 *    canvas共有四类方法
 *      1, drawXXX为主的绘制方法
 *      2, clipXXX为主的裁剪方法
 *      3, scale、skew、translate和rotate组成的Canvas变换方法
 *      4, saveXXX和restoreXXX构成的画布锁定和还原
 *      5, 其他方法
 */
public class Contest  extends View{
    private Paint mPaint;

    public Contest(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        canvas.clipRect(0, 0, 500, 500);
        canvas.drawColor(Color.RED);
        canvas.drawCircle(500, 600, 100, mPaint);
    }
}
