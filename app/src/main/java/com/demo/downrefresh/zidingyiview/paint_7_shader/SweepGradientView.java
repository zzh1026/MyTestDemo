package com.demo.downrefresh.zidingyiview.paint_7_shader;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * 这个类的作用是:     SweepGradient  梯度渐变，也称之为扫描式渐变，因为其效果有点类似雷达的扫描效果
 *
 *  有两个构造方法：
 *
 *  SweepGradient(float cx, float cy, int color0, int color1)
 *          mPaint.setShader(new SweepGradient(screenX, screenY, Color.RED, Color.YELLOW));
 *
 *  SweepGradient(float cx, float cy, int[] colors, float[] positions)
 *          mPaint.setShader(new SweepGradient(screenX, screenY,
 *                  new int[] { Color.GREEN, Color.WHITE, Color.GREEN }, null));
 *
 * <p>
 * Created by zhaozh on 2017/5/9.
 *
 */

public class SweepGradientView extends View {



    public SweepGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
