package com.demo.downrefresh.zidingyiview.canvas_3_path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/11.
 *
 * quadTo(float x1, float y1, float x2, float y2)  ,模拟贝塞尔曲线(二阶)
 *
 * cubicTo(float x1, float y1, float x2, float y2, float x3, float y3)贝塞尔曲线(三阶)
 *
 * arcTo (RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo)
 *      方法只是多了一个布尔值，值为true时将会把弧的起点作为Path的起点：
 *
 *
 *
         rCubicTo(float x1, float y1, float x2, float y2, float x3, float y3)
         rLineTo(float dx, float dy)
         rMoveTo(float dx, float dy)
         rQuadTo(float dx1, float dy1, float dx2, float dy2)
 *          rXXXTo方法的参考坐标是相对的而XXXTo方法的参考坐标始终是参照画布原点坐标
 *          r即relative , 是相对的意思
 *
 *   addArc(RectF oval, float startAngle, float sweepAngle)
 *
 *   addCircle(float x, float y, float radius, Path.Direction dir)
 *   addOval(float left, float top, float right, float bottom, Path.Direction dir)
 *   addRect(float left, float top, float right, float bottom, Path.Direction dir)
 *   addRoundRect(float left, float top, float right, float bottom, float rx, float ry, Path.Direction dir)
 *
 *      add表示可以在path中添加一些线
 *
 *   drawTextOnPath(String text, Path path, float hOffset, float vOffset, Paint paint)
 *    沿着Path绘制一段文字，参数也是一看就该懂得了不多说。Path.Direction只有两个常量值CCW和CW分别表示逆时针方向闭合和顺时针方向闭合
 *
 *
 *
 *
 */

public class PathView extends View {

    private Paint mPaint;// 画笔对象
    private Path mPath;// 路径对象

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();

        // 实例化路径
        mPath = new Path();
        mPath.moveTo(100, 100);
//        mPath.moveTo(500, 100);
        // 连接路径到点[100,100]
//        mPath.lineTo(100, 100);
//        mPath.arcTo(100, 100, 500, 500, -90, -90, true);

        // 连接路径到点
        mPath.quadTo(200, 200, 300, 100);
//        mPath.cubicTo(200, 200, 300, 0, 400, 100);

    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
    }
}
