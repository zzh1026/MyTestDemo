package com.demo.downrefresh.zidingyiview.canvas_3_path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/11.
 *
 *  测试 path 的 addxxx方法 的最后一个参数的 Path.Direction dir  ,共有两个值
 *  {@link Path.Direction.CW} 顺时针方向闭合  和  {@link Path.Direction.CCW} 逆时针方向闭合
 *
 *  Path.Direction只有两个常量值CCW和CW分别表示逆时针方向闭合和顺时针方向闭合
 *
 *  在实际调用 drawTextOnPath 的时候 ,使用 cw 顺时针draw , 在 oval的外部
 *    使用ccw, 逆时针draw , 在 oval 内部;
 */

public class PathDirectionView extends View {

    private Path mPath;// 路径对象
    private Paint mPaint;// 路径画笔对象
    private TextPaint mTextPaint;// 文本画笔对象

    public PathDirectionView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        mPaint.setStrokeWidth(5);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.LINEAR_TEXT_FLAG);
        mTextPaint.setColor(Color.DKGRAY);
        mTextPaint.setTextSize(20);

        // 实例化路径
        mPath = new Path();

        // 添加一条弧线到Path中
        RectF oval = new RectF(100, 100, 300, 400);
        mPath.addOval(oval, Path.Direction.CCW);
//        mPath.addOval(oval, Path.Direction.CW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制路径
        canvas.drawPath(mPath, mPaint);

        // 绘制路径上的文字
        canvas.drawTextOnPath("ad撒发射点发放士大夫斯蒂芬斯蒂芬森啊打扫打扫打扫达发达省份撒旦发射的",
                mPath, 0, 0, mTextPaint);
    }
}
