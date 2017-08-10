package com.demo.downrefresh.zidingyiview.paint_6_patheffect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/4.
 *
 * 设个类的方法是设置路径效果, 共有 6 种
 *
 * 1, {@link CornerPathEffect} 可以将路径的转角变得圆滑,参数 radius , 表示圆滑的程度, 越大越圆滑
 * 2, {@link DiscretePathEffect} 离散路径效果
 *      会在路径上绘制很多“杂点”的突出来模拟一种类似生锈铁丝的效果
 *      其构造方法有两个参数:
 *      第一个参数指定这些突出的“杂点”的密度，值越小杂点越密集
 *      第二个参数呢则是“杂点”突出的大小，值越大突出的距离越大
 * 3, {@link DashPathEffect}
 *      两个参数:
 *      第一个参数: 在定义该参数的时候只要浮点型数组中元素个数大于等于2即可,代表有多少个数据参与循环, 每个长度为定义的item
 *      第二个参数: 偏移值 ,动态改变其值会让路径产生动画的效果
 * 4, {@link PathDashPathEffect}
 *      和DashPathEffect是类似的，不同的是PathDashPathEffect可以让我们自己定义路径虚线的样式,
 *      比如我们将其换成一个个小圆组成的虚线：
             Path path = new Path();
             path.addCircle(0, 0, 3, Direction.CCW);
             mEffects[4] = new PathDashPathEffect(path, 12, mPhase, PathDashPathEffect.Style.ROTATE);
 * 5, {@link ComposePathEffect}
 *   和
 * 6, {@link SumPathEffect}
 *      都可以用来组合两种路径效果
 *
 *      不同的是组合的方式:
 *      ComposePathEffect(PathEffect outerpe, PathEffect innerpe)
 *          会先将路径变成innerpe的效果，再去复合outerpe的路径效果
 *      SumPathEffect(PathEffect first, PathEffect second)
 *          则会把两种路径效果加起来再作用于路径
 */

public class PathEffectView extends View {

    private float mPhase;// 偏移值

    private Paint mPaint;// 画笔对象
    private Path mPath;// 路径对象

    private PathEffect[] mEffects;// 路径效果数组

    public PathEffectView(Context context, AttributeSet attrs) {
        super(context, attrs);

        /*
         * 实例化画笔并设置属性
         */
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.DKGRAY);

        // 实例化路径
        mPath = new Path();

        // 定义路径的起点
        mPath.moveTo(0, 0);

        // 定义路径的各个点
        for (int i = 0; i <= 30; i++) {
            mPath.lineTo(i * 35, (float) (Math.random() * 100));
        }

        // 创建路径效果数组
        mEffects = new PathEffect[7];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
         * 实例化各类特效
         */
        mEffects[0] = null;
        mEffects[1] = new CornerPathEffect(100);
        mEffects[2] = new DiscretePathEffect(3.0F, 5.0F);
        mEffects[3] = new DashPathEffect(new float[]{20, 10, 50, 10}, mPhase);
        Path path = new Path();
        path.addRect(0, 0, 8, 8, Path.Direction.CCW);
        mEffects[4] = new PathDashPathEffect(path, 12, mPhase, PathDashPathEffect.Style.ROTATE);
        mEffects[5] = new ComposePathEffect(mEffects[2], mEffects[4]);
        mEffects[6] = new SumPathEffect(mEffects[4], mEffects[3]);

        /*
         * 绘制路径
         */
        for (int i = 0; i < mEffects.length; i++) {
            mPaint.setPathEffect(mEffects[i]);
            canvas.drawPath(mPath, mPaint);

            // 每绘制一条将画布向下平移250个像素
            canvas.translate(0, 250);
        }

        // 刷新偏移值并重绘视图实现动画效果
        mPhase += 1;
        invalidate();
    }
}
