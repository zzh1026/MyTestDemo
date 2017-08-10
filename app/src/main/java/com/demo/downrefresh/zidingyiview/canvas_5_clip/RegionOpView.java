package com.demo.downrefresh.zidingyiview.canvas_5_clip;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/15.
 *
 * {@link Region.Op.DIFFERENCE} 最终区域为第一个区域与第二个区域不同的区域。  difference, 另一部分
 * {@link Region.Op.INTERSECT} 最终区域为第一个区域与第二个区域相交的区域。 intersect ,相交
 * {@link Region.Op.REPLACE} 最终区域为第二个区域。。   replace ,替换
 * {@link Region.Op.REVERSE_DIFFERENCE} 最终区域为第二个区域与第一个区域不同的区域   reverse_diffence ,反向的另一部分
 * {@link Region.Op.UNION} 最终区域为第一个区域加第二个区域   union ,之和
 * {@link Region.Op.XOR} 最终区域为第一个区域加第二个区域并减去两者相交的区域。   xor ,去掉相交的
 *
 */

public class RegionOpView extends View {
    private Rect mRegionA, mRegionB;// 区域A和区域B对象
    private Paint mPaint;// 绘制边框的Paint

    public RegionOpView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 实例化画笔并设置属性
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(2);

        // 实例化区域A和区域B
        mRegionA = new Rect(100, 100, 300, 300);
        mRegionB = new Rect(200, 200, 400, 400);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 填充颜色
        canvas.drawColor(Color.BLUE);

        canvas.save();

        // 裁剪区域A
        canvas.clipRect(mRegionA);

        // 再通过组合方式裁剪区域B
//        canvas.clipRect(mRegionB, Region.Op.DIFFERENCE);
//        canvas.clipRect(mRegionB, Region.Op.INTERSECT);
//        canvas.clipRect(mRegionB, Region.Op.REPLACE);
//        canvas.clipRect(mRegionB, Region.Op.REVERSE_DIFFERENCE);
//        canvas.clipRect(mRegionB, Region.Op.UNION);
        canvas.clipRect(mRegionB, Region.Op.XOR);

        // 填充颜色
        canvas.drawColor(Color.RED);

        canvas.restore();

        // 绘制框框帮助我们观察
        canvas.drawRect(100, 100, 300, 300, mPaint);
        canvas.drawRect(200, 200, 400, 400, mPaint);
    }
}
