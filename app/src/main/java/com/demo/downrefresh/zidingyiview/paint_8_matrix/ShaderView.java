package com.demo.downrefresh.zidingyiview.paint_8_matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.demo.downrefresh.R;
import com.demo.downrefresh.utils.ScreenSizeUtils;

/**
 * 这个类的作用是:  使用 Mxtrix 来控制缩放变换
 * <p>
 * Created by zhaozh on 2017/5/10.
 *
 *  setXXX、preXXX和postXXX   共有三种方法簇
 *
 *      而preXXX和postXXX就是分别表示矩阵的左右乘，也有前后乘的说法
 */

public class ShaderView extends View {

    private static final int RECT_SIZE = 400;// 矩形尺寸的一半

    private Paint mPaint;// 画笔

    private int left, top, right, bottom;// 矩形坐上右下坐标
    private int screenX, screenY;


    public ShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 获取屏幕中点坐标
        DisplayMetrics screenSize = ScreenSizeUtils.getScreenSize(context);
        screenX = screenSize.widthPixels / 2;
        screenY = screenSize.heightPixels / 2;

        // 计算矩形左上右下坐标值
        left = screenX - RECT_SIZE;
        top = screenY - RECT_SIZE;
        right = screenX + RECT_SIZE;
        bottom = screenY + RECT_SIZE;

        // 实例化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // 获取位图
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);

        // 实例化一个Shader
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        // 实例一个矩阵对象
        Matrix matrix = new Matrix();

        matrix.setTranslate(500, 500); //平移 X, Y
//        matrix.preRotate(5);
        matrix.postRotate(5);

        // 设置矩阵变换
//        matrix.setTranslate(left, top); //平移 X, Y
//        matrix.setScale();            //缩放 X, Y
//        matrix.setRotate();           //旋转 X 的角度


        /**
         *  preXXX先起作用 , postXXX后期作用
         *  set重置数据表示开始
         *
         *  pre表示之前,  如果有两个先执行后面的.      之前表示在这个前面的前面
         *  post表示之后, 如果有两个先执行前面的.      之后表示在这个前面的后面
         *
         *   matrix.preScale(0.5f, 1);
         *   matrix.setScale(1, 0.6f);
         *   matrix.postScale(0.7f, 1);
         *   matrix.preTranslate(15, 0);
         *
         *   那么Matrix的计算过程即为：translate (15, 0) -> scale (1, 0.6f) -> scale (0.7f, 1)，
         *     set会重置数据，所以最开始的matrix.preScale(0.5f, 1);  也就不起作用了
         *
         *
         *   matrix.preScale(0.5f, 1);
         *   matrix.preTranslate(10, 0);
         *   matrix.postScale(0.7f, 1);
         *   matrix.postTranslate(15, 0);
         *
         *   其计算过程为：translate (10, 0) -> scale (0.5f, 1) -> scale (0.7f, 1) -> translate (15, 0)
         */

        // 设置Shader的变换矩阵
        bitmapShader.setLocalMatrix(matrix);

        // 设置着色器
        mPaint.setShader(bitmapShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制矩形
//         canvas.drawRect(left, top, right, bottom, mPaint);
        canvas.drawRect(0, 0, screenX * 2, screenY * 2, mPaint);
    }
}
