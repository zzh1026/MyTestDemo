package com.demo.downrefresh.zidingyiview.canvas_5_clip;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/15.
 *
 *
 * clipPath(Path path, Region.Op op)
   clipRect(Rect rect, Region.Op op)
   clipRect(RectF rect, Region.Op op)
   clipRect(float left, float top, float right, float bottom, Region.Op op)
   clipRegion(Region region, Region.Op op)
 *
 *  {@link Region.Op} 共有六个常量 ,表示组合模式
 *
 *
 */

public class CanvasClipView extends View {
    private Path mPath;
    private static final float X1 = 50 / 200F, X2 = 75 / 200F, X3 = 150 / 200F, X4 = 80 / 200F;
    private static final float Y1 = 50 / 200F, Y2 = 23 / 200F, Y3 = 100 / 200F, Y4 = 110 / 200F;

    public CanvasClipView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mPath.moveTo(w * X1, h * Y1);
        mPath.lineTo(w * X2, h * Y2);
        mPath.lineTo(w * X3, h * Y3);
        mPath.lineTo(w * X4, h * Y4);
        mPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);

        canvas.clipPath(mPath, Region.Op.DIFFERENCE);

        canvas.drawColor(Color.RED);
    }
}
