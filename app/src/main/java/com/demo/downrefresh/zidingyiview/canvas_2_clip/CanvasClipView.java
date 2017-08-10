package com.demo.downrefresh.zidingyiview.canvas_2_clip;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/11.
 * <p>
 * clipRect(int left, int top, int right, int bottom)
 * <p>
 * clipRect(float left, float top, float right, float bottom)
 * <p>
 * clipRect(Rect rect)     Rect 中的 left , top , right , bottom 均为int 值
 * <p>
 * clipRect(RectF rect)    RectF中的 left , top , right , bottom 均为float 值
 */

public class CanvasClipView extends View {
    private Rect mRect;


    public CanvasClipView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mRect = new Rect(0, 0, 500, 500);

//        boolean intersect = mRect.intersect(250, 250, 750, 750);
//        mRect.union(250, 250, 750, 750);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);

        canvas.clipRect(mRect);

        canvas.drawColor(Color.RED);
    }
}
