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
 * Created by zhaozh on 2017/5/15.
 * <p>
 * 除了saveLayer，Canvas还提供了一个saveLayerAlpha方法，顾名思义，该方法可以在我们保存画布时设置画布的透明度：
 *
 * save(int Flag) Flag共有六个常量 ,这六个常量值分别标识了我们在调用restore方法后还原什么
 *
 * 六个标识位除了  CLIP_SAVE_FLAG、 MATRIX_SAVE_FLAG和   ALL_SAVE_FLAG是save和saveLayerXXX方法都通用外
 *      其余三个只能使saveLayerXXX方法有效
 *
 * {@link Canvas.ALL_SAVE_FLAG} 保存所有    save和saveLayerXXX方法都通用
 * {@link Canvas.CLIP_SAVE_FLAG} 裁剪的标识位    save和saveLayerXXX方法都通用
 * {@link Canvas.MATRIX_SAVE_FLAG} 变换的标识位    save和saveLayerXXX方法都通用
 *
 * {@link Canvas.CLIP_TO_LAYER_SAVE_FLAG} 表示对当前图层执行裁剪操作需要对齐图层边界    saveLayerXXX方法有效
 * {@link Canvas.FULL_COLOR_LAYER_SAVE_FLAG} 表示当前图层的色彩模式至少需要是8位色    saveLayerXXX方法有效
 * {@link Canvas.HAS_ALPHA_LAYER_SAVE_FLAG} 表示在当前图层中将需要使用逐像素Alpha混合模式  saveLayerXXX方法有效
 *
 * 所有的save、saveLayer和saveLayerAlpha方法都有一个int型的返回值，
 *          该返回值作为一个标识给与了一个你当前保存操作的唯一ID编号，
 *          我们可以利用restoreToCount(int saveCount)方法来指定在还原的时候还原哪一个保存操作：
 *
 *
 */

public class LayerView extends View {
    private Paint mPaint;// 画笔对象

    private int mViewWidth, mViewHeight;// 控件宽高

    public LayerView(Context context, AttributeSet attrs) {
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

        /*
         * 绘制一个红色矩形
         */
        mPaint.setColor(Color.RED);
        canvas.drawRect(mViewWidth / 2F - 200, mViewHeight / 2F - 200,
                mViewWidth / 2F + 200, mViewHeight / 2F + 200, mPaint);

        /*
         * 保存画布并绘制一个蓝色的矩形
         */
//        canvas.saveLayer(0, 0, mViewWidth, mViewHeight, null, Canvas.ALL_SAVE_FLAG);
//        canvas.save();
//        canvas.saveLayer(mViewWidth / 2F - 100, mViewHeight / 2F - 100,
//                mViewWidth / 2F + 100, mViewHeight / 2F + 100, null, Canvas.ALL_SAVE_FLAG);
        canvas.saveLayerAlpha(mViewWidth / 2F - 100, mViewHeight / 2F - 100,
                mViewWidth / 2F + 100, mViewHeight / 2F + 100, 112, Canvas.ALL_SAVE_FLAG);
        mPaint.setColor(Color.BLUE);

//        canvas.translate(mViewWidth / 2F, mViewHeight / 2F);
        // 旋转画布
        canvas.rotate(5);
        canvas.drawRect(mViewWidth / 2F - 100, mViewHeight / 2F - 100,
                mViewWidth / 2F + 100, mViewHeight / 2F + 100, mPaint);
        canvas.restore();

    }
}
