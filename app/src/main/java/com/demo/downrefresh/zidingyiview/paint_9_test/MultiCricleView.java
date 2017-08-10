package com.demo.downrefresh.zidingyiview.paint_9_test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.orhanobut.logger.Logger;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/10.
 */

public class MultiCricleView extends View {

    private static final float
            STROKE_WIDTH = 1F / 256F,   // 描边宽度占比
            SPACE = 1F / 64F,// 大圆小圆线段两端间隔占比
            LINE_LENGTH = 3F / 32F,     // 线段长度占比
            CRICLE_LARGER_RADIU = 3F / 32F, // 大圆半径
            CRICLE_SMALL_RADIU = 5F / 64F,  // 小圆半径
            ARC_RADIU = 1F / 8F,        // 弧半径
            ARC_TEXT_RADIU = 5F / 32F;  // 弧围绕文字半径

    private Paint strokePaint, textPaint;// 描边画笔和文字画笔
    private float strokeWidth;// 描边宽度

    private float ccX, ccY;// 中心圆圆心坐标

    private float largeCricleRadiu, smallCricleRadiu;// 大圆半径和小圆半径

    private int size;// 控件边长

    private float lineLength;// 线段长度

    private float space;// 大圆小圆线段两端间隔

    private float textOffsetY;// 文本的Y轴偏移值

    private enum Type {
        LARGER, SMALL
    }

    public MultiCricleView(Context context) {
        this(context, null);
    }

    public MultiCricleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiCricleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
    }

    private void initPaint() {
        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(Color.WHITE);
        strokePaint.setStrokeCap(Paint.Cap.ROUND);  //设置笔触

        /*
         * 初始化文字画笔
         */
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(30);
        textPaint.setTextAlign(Paint.Align.CENTER);

        textOffsetY = (textPaint.descent() + textPaint.ascent()) / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 强制长宽一致
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // 获取控件边长
        size = w;
        Logger.i("运行到了 onSizeChanged" + "--" + w + "--" + h + "--" + oldw + "--" + oldh);
        // 参数计算
        calculation();
    }

    /*
     * 参数计算
     */
    private void calculation() {
        // 计算描边宽度
        strokeWidth = STROKE_WIDTH * size;

        // 计算大圆半径
        largeCricleRadiu = size * CRICLE_LARGER_RADIU;

        // 计算小圆半径
        smallCricleRadiu = size * CRICLE_SMALL_RADIU;

        // 计算线段长度
        lineLength = size * LINE_LENGTH;

        // 计算大圆小圆线段两端间隔
        space = size * SPACE;

        // 计算中心圆圆心坐标
        ccX = size / 2;
        ccY = size / 2 + size * CRICLE_LARGER_RADIU;

        // 设置参数
        setPara();
    }

    /**
     * 设置参数
     */
    private void setPara() {
        // 设置描边宽度
        strokePaint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制背景
        canvas.drawColor(0xFFF29B76);

        // 绘制中心圆
        canvas.drawCircle(ccX, ccY, largeCricleRadiu, strokePaint);
        canvas.drawText("啊哈哈", ccX, ccY - textOffsetY, textPaint);

        // 绘制左上方图形
        drawTopLeft(canvas);

        // 绘制右上方图形
        drawTopRight(canvas);

        // 绘制左下方图形
        drawBottomLeft(canvas);

        // 绘制下方图形
        drawBottom(canvas);

        // 绘制右下方图形
        drawBottomRight(canvas);
    }

    private void drawBottomRight(Canvas canvas) {
        float
                lineYS = -largeCricleRadiu - space,
                lineYE = -lineLength * 2 - space,
                cricleY = -lineLength * 2 - smallCricleRadiu - space * 2;

        // 锁定画布
        canvas.save();

        // 平移和旋转画布
        canvas.translate(ccX, ccY);
        canvas.rotate(100);

        // 依次画：(间隔)线(间隔)-圈
        canvas.drawLine(0, lineYS, 0, lineYE, strokePaint);
        canvas.drawCircle(0, cricleY, smallCricleRadiu, strokePaint);
        canvas.drawText("Vibrators", 0, cricleY - textOffsetY, textPaint);

        // 释放画布
        canvas.restore();
    }

    private void drawBottom(Canvas canvas) {
        float
                lineYS = -largeCricleRadiu - space,
                lineYE = -lineLength * 2 - space,
                cricleY = -lineLength * 2 - smallCricleRadiu - space * 2;

        // 锁定画布
        canvas.save();

        // 平移和旋转画布
        canvas.translate(ccX, ccY);
        canvas.rotate(180);     //这里向下走正着画也可以

        // 依次画：(间隔)线(间隔)-圈
        canvas.drawLine(0, lineYS, 0, lineYE, strokePaint);
        canvas.drawCircle(0, cricleY, smallCricleRadiu, strokePaint);
        canvas.drawText("Cucumber", 0, cricleY - textOffsetY, textPaint);

//        canvas.drawLine(0, largeCricleRadiu + space, 0, lineLength * 2 + space, strokePaint);
//        canvas.drawCircle(0, lineLength * 2 + smallCricleRadiu + space * 2, smallCricleRadiu, strokePaint);

        // 释放画布
        canvas.restore();
    }

    private void drawBottomLeft(Canvas canvas) {
        float
                lineYS = -largeCricleRadiu - space,
                lineYE = -lineLength * 2 - space,
                cricleY = -lineLength * 2 - smallCricleRadiu - space * 2;
        // 锁定画布
        canvas.save();

        // 平移和旋转画布
        canvas.translate(ccX, ccY);
        canvas.rotate(-100);

        // 依次画：(间隔)线(间隔)-圈
        canvas.drawLine(0, lineYS, 0, lineYE, strokePaint);
        canvas.drawCircle(0, -lineLength * 2 - smallCricleRadiu - space * 2, smallCricleRadiu, strokePaint);
        canvas.drawText("Banana", 0, cricleY - textOffsetY, textPaint);

        // 释放画布
        canvas.restore();
    }

    /**
     * 绘制右上方图形
     *
     * @param canvas
     */
    private void drawTopRight(Canvas canvas) {
        float cricleY = -lineLength * 3;
        // 锁定画布
        canvas.save();

        // 平移和旋转画布
        canvas.translate(ccX, ccY);
        canvas.rotate(30);

        // 依次画：线-圈
        canvas.drawLine(0, -largeCricleRadiu, 0, -lineLength * 2, strokePaint);
        canvas.drawCircle(0, cricleY, largeCricleRadiu, strokePaint);
        canvas.drawText("Tropical", 0, cricleY - textOffsetY, textPaint);

        // 画弧形
        drawTopRightArc(canvas, cricleY);

        // 释放画布
        canvas.restore();
    }

    private void drawTopLeft(Canvas canvas) {
        //锁定画布
        /**
         * 通过save和restore来把canvas释放回save之前的状态, 这样方便其他画,不然此时切换了中心点以后就已切换之后的
         * 中心点进行后面的绘制了, canvas是唯一的, 就会对其他控件造成影响
         */
        canvas.save();

        // 平移和旋转画布
        canvas.translate(ccX, ccY);
        /*
         *<0 ,画布逆时针旋转,出来的内容逆时针 . >0 ,画布顺时针, 内容顺时针
         * 代表这之后是已内容是左转的, 不能在画完毕以后才调用, 否则表示之后的内容左转,之前的内容是不变的
         *
         * 要理解这个就要把  canvas画布 和 draw的图像区分开, 图像是 draw在window上的而非canvas上,所以这样
         * 旋转的时候表面上是 draw到了canvas外部,实际上是 canvas 和 draw是两回事 , canvas可以设置draw的中心
         * 点, 但是并不影响draw的 实际效果
         *
         */
        canvas.rotate(-30);

        // 依次画：线-圈-线-圈
        canvas.drawLine(0, -largeCricleRadiu, 0, -lineLength * 2, strokePaint);
        canvas.drawCircle(0, -lineLength * 3, largeCricleRadiu, strokePaint);
        canvas.drawText("Apple", 0, -lineLength * 3 - textOffsetY, textPaint);

        canvas.drawLine(0, -largeCricleRadiu * 4, 0, -lineLength * 5, strokePaint);
        canvas.drawCircle(0, -lineLength * 6, largeCricleRadiu, strokePaint);
        canvas.drawText("Orange", 0, -lineLength * 6 - textOffsetY, textPaint);

        // 释放画布
        canvas.restore();
    }

    /**
     * 绘制右上角画弧形
     *
     * @param canvas
     * @param cricleY
     */
    private void drawTopRightArc(Canvas canvas, float cricleY) {
        canvas.save();

        canvas.translate(0, cricleY);
        canvas.rotate(-30);

        float arcRadiu = size * ARC_RADIU;
        RectF oval = new RectF(-arcRadiu, -arcRadiu, arcRadiu, arcRadiu);
        Paint arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        arcPaint.setStyle(Paint.Style.FILL);
        arcPaint.setColor(0x55EC6941);
        canvas.drawArc(oval, -22.5F, -135, true, arcPaint);

        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setColor(Color.WHITE);
        canvas.drawArc(oval, -22.5F, -135, false, arcPaint);

        float arcTextRadiu = size * ARC_TEXT_RADIU;

        canvas.save();

        // 把画布旋转到扇形左端的方向
        canvas.rotate(-135F / 2F);
        /*
         * 每隔33.75度角画一次文本
         */
        for (float i = 0; i < 5 * 33.75F; i += 33.75F) {
            canvas.save();
            canvas.rotate(i);
            char a = (char) ('A' + i / 33.75F);
            canvas.drawText(String.valueOf(a), 0, -arcTextRadiu, textPaint);
            canvas.restore();
        }

        canvas.restore();

        canvas.restore();
    }
}
