package com.demo.downrefresh.zidingyiview.canvas_2_clip;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 这个类的作用是:
 * <p>
 * Path的一些方法
 * <p>
 * quadTo(float x1, float y1, float x2, float y2)  ,模拟贝塞尔曲线
 */

public class CanvasClipPathView extends View {
    private static final int WIDTH = 400;

    private Rect mRect;
    private Path mPath;


    public CanvasClipPathView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPath = new Path();
        mPath.moveTo(100, 100);
//        mPath.arcTo(0, 0, 80, 80, 0, 90, true);
//        mPath.arcTo(320, 0, 400, 80, 0, 90, true);

        mPath.quadTo(200, 200, 300, 100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);

        canvas.clipRect(100, 100, 100 + WIDTH, 100 + WIDTH);

        canvas.drawColor(Color.RED);

        canvas.clipPath(mPath);

        canvas.drawColor(Color.WHITE);

    }
}
