package com.demo.downrefresh.zidingyiview.paint_7_shader;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * 这个类的作用是:  LinearGradient 线性渐变
 * <p>
 * Created by zhaozh on 2017/5/9.
 *
 * LinearGradient(float x0, float y0, float x1, float y1, int color0, int color1, Shader.TileMode tile)
 *
 *    这是LinearGradient最简单的一个构造方法，
 *    参数虽多其实很好理解x0和y0表示渐变的起点坐标而x1和y1则表示渐变的终点坐标，
 *    而color0和color1则表示起点的颜色和终点的颜色
 *
 * 仅仅两种颜色的渐变根本无法满足我们身体的欲望，太单调乏味！我们是不是可以定义多种颜色渐变呢？
 *      答案是必须的，LinearGradient的另一个构造方法
 *
 * LinearGradient(float x0, float y0, float x1, float y1, int[] colors, float[] positions,
 *              Shader.TileMode tile)
 *    colors是一个int型数组，我们用来定义所有渐变的颜色，positions表示的是渐变的相对区域，其取值只有0到1
 *
 */

public class LinearGradientView extends View {


    public LinearGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
