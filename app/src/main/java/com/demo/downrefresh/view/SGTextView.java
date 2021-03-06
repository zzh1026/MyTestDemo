package com.demo.downrefresh.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

import com.demo.downrefresh.utils.DimensUtils;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/4/17.
 */

public class SGTextView extends TextView {
    private Paint strokPaint = new Paint();
    private Paint gradientPaint = new Paint();

    public SGTextView(Context context) {
        this(context, null);
    }

    public SGTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置字体的风格
     * @param strokeColor   描边的外部颜色
     * @param startColor    线性渐变的开始颜色
     * @param endColor      线性渐变的结束颜色
     * @param strokewidthDp     描边颜色的宽度,dp单位
     * @param gradientHeighDp   线性渐变的宽度,dp单位
     */
    public void setStyle(String strokeColor, String startColor,
                         String endColor, float strokewidthDp, int gradientHeighDp) {
        setStyle(
                Color.parseColor(strokeColor),
                DimensUtils.dip2px(getContext(), strokewidthDp),
                new LinearGradient(0, 0, 0, DimensUtils.dip2px(getContext(),
                        gradientHeighDp), new int[]{
                        Color.parseColor(startColor),
                        Color.parseColor(endColor)}, null, Shader.TileMode.CLAMP));
    }

    /**
     * 最终的颜色设置
     * @param strokeColor   外部描边颜色
     * @param strokewidth   描边的宽
     * @param shader    线性渐变的渲染模式
     */
    public void setStyle(int strokeColor, float strokewidth, Shader shader) {

        strokPaint.setAntiAlias(true);
        // 设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        strokPaint.setDither(true);
        // 如果该项设置为true，则图像在动画进行中会滤掉对Bitmap图像的优化操作，加快显示
        // 速度，本设置项依赖于dither和xfermode的设置
        strokPaint.setFilterBitmap(true);

        strokPaint.setStrokeWidth(strokewidth);
        strokPaint.setColor(strokeColor);
        // 设置绘制时各图形的结合方式，如平滑效果等
        strokPaint.setStrokeJoin(Paint.Join.ROUND);
        // 当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的图形样式，如圆形样式
        // Cap.ROUND,或方形样式Cap.SQUARE
        strokPaint.setStrokeCap(Paint.Cap.ROUND);
        //设置风格对文字只描边
        strokPaint.setStyle(Paint.Style.STROKE);

        gradientPaint.setAntiAlias(true);
        gradientPaint.setDither(true);
        gradientPaint.setFilterBitmap(true);
        gradientPaint.setShader(shader);
        gradientPaint.setStrokeJoin(Paint.Join.ROUND);
        gradientPaint.setStrokeCap(Paint.Cap.ROUND);
        gradientPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        float textsize = getTextSize();
        strokPaint.setTextSize(textsize);
        gradientPaint.setTextSize(textsize);

        strokPaint.setTypeface(getTypeface());
        gradientPaint.setTypeface(getTypeface());


        strokPaint.setFakeBoldText(true);
        gradientPaint.setFakeBoldText(true);

    }

    /**
     * 设置 字体的阴影便宜
     * @param radius    圆角度数
     * @param dx    y轴偏移量
     * @param dy    x轴偏移量
     * @param color     阴影颜色
     */
    public void setShadowLayer(float radius, float dx, float dy, String color) {
        strokPaint.setShadowLayer(radius, dx, dy, Color.parseColor(color));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        String text = getText().toString();
        int width = getMeasuredWidth();
        if (width == 0) {
            measure(0, 0);
            width = (int) (getMeasuredWidth() + strokPaint.getStrokeWidth() * 2);
            setWidth(width);
        }

        float y = getBaseline();
        float x = (width - strokPaint.measureText(text)) / 2;


        canvas.drawText(text, x, y, strokPaint);
        canvas.drawText(text, x, y, gradientPaint);
    }
}
