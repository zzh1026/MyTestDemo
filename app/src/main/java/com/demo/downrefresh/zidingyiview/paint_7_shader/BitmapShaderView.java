package com.demo.downrefresh.zidingyiview.paint_7_shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.demo.downrefresh.R;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/9.
 *
 * @see BrickView 是对bitmapsharder的应用
 */

public class BitmapShaderView extends View {

    private static final int RECT_SIZE = 400;// 矩形尺寸的一半
    private Paint mPaint;// 画笔

    public BitmapShaderView(Context context) {
        this(context, null);
    }

    public BitmapShaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 实例化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        // 获取位图
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);

        /**
         *  {@link Shader.TileMode.CLAMP} 拉伸
         *  {@link Shader.TileMode.MIRROR} 镜像
         *  {@link Shader.TileMode.REPEAT} 重复
         */
        // 设置着色器
        /**
         * 后两个参数的意思是,   X轴的处理方法 和 Y轴的处理方法
         */
        mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.CLAMP));
//        mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
//        mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR));
//        mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));

        int measuredWidth = getMeasuredWidth();
        Log.i("hehe", "宽为: " + measuredWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        // 绘制矩形
        canvas.drawRect(measuredWidth / 2 - RECT_SIZE, measuredHeight / 2 - RECT_SIZE,
                measuredWidth / 2 + RECT_SIZE, measuredHeight / 2 + RECT_SIZE, mPaint);
    }
}
