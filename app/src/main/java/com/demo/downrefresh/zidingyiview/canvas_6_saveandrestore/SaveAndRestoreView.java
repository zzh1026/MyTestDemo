package com.demo.downrefresh.zidingyiview.canvas_6_saveandrestore;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/16.
 */

public class SaveAndRestoreView extends View {
    private Paint mPaint;// 画笔对象

    private int mViewWidth, mViewHeight;// 控件宽高

    public SaveAndRestoreView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 实例化画笔对象并设置其标识值
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        /*
         * 获取控件宽高
         */
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        /*
//         * 绘制一个红色矩形
//         */
//        mPaint.setColor(Color.RED);
//        canvas.drawRect(mViewWidth / 2F - 200, mViewHeight / 2F - 200,
//                mViewWidth / 2F + 200, mViewHeight / 2F + 200, mPaint);
//
//         /*
//         * 保存并裁剪画布填充绿色
//         */
//        int saveID1 = canvas.save(Canvas.CLIP_SAVE_FLAG);
//        canvas.clipRect(mViewWidth / 2F - 200, mViewHeight / 2F - 200,
//                mViewWidth / 2F + 200, mViewHeight / 2F + 200);
//        canvas.drawColor(Color.GREEN);
//
//        /*
//         * 保存画布并旋转后绘制一个蓝色的矩形
//         */
//        int saveID2 = canvas.save(Canvas.MATRIX_SAVE_FLAG);
//
//        // 旋转画布
//        canvas.rotate(5);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(mViewWidth / 2F - 100, mViewHeight / 2F - 100,
//                mViewWidth / 2F + 100, mViewHeight / 2F + 100, mPaint);
//
//        canvas.restoreToCount(saveID2);
//        mPaint.setColor(Color.YELLOW);
//        canvas.drawRect(mViewWidth / 2F - 200, mViewHeight / 2F - 200,
//                mViewWidth / 2F + 200, mViewHeight / 2F + 200, mPaint);

        /*
         * 保存并裁剪画布填充绿色
         */
        int saveID1 = canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(mViewWidth / 2F - 300, mViewHeight / 2F - 300,
                mViewWidth / 2F + 300, mViewHeight / 2F + 300);
        canvas.drawColor(Color.YELLOW);
        canvas.restore();

        /*
         * 保存并裁剪画布填充绿色
         */
        int saveID2 = canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(mViewWidth / 2F - 200, mViewHeight / 2F - 200,
                mViewWidth / 2F + 200, mViewHeight / 2F + 200);
        canvas.drawColor(Color.GREEN);
        canvas.restore();

        /*
         * 保存画布并旋转后绘制一个蓝色的矩形
         */
        int saveID3 = canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.rotate(5);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(mViewWidth / 2F - 100, mViewHeight / 2F - 100,
                mViewWidth / 2F + 100, mViewHeight / 2F + 100, mPaint);

        mPaint.setColor(Color.CYAN);
        canvas.drawRect(mViewWidth / 2F, mViewHeight / 2F,
                mViewWidth / 2F + 200, mViewHeight / 2F + 200, mPaint);

    }
}
