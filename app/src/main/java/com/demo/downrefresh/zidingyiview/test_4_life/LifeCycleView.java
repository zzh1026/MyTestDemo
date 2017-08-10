package com.demo.downrefresh.zidingyiview.test_4_life;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ViewFlipper;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/24.
 *
 * 参考{@link http://blog.csdn.net/aigestudio/article/details/43907299};
 */

public class LifeCycleView extends View {
    private static final String TAG = "hehe";

    public LifeCycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "创建了LifeCycleView");
//        ViewFlipper
    }

    @Override
    protected void onFinishInflate() {
        Log.d(TAG, "创建完毕onFinishInflate");
        super.onFinishInflate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "测量onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(TAG, "布局onLayout");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d(TAG, "大小改变onSizeChanged");
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "画onDraw");
        super.onDraw(canvas);
    }

    @Override
    protected void onAttachedToWindow() {
        Log.d(TAG, "添加到了窗口window中onAttachedToWindow");
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        Log.d(TAG, "分发屏幕onDetachedFromWindow");
        super.onDetachedFromWindow();
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        Log.d(TAG, "屏幕可见状态改变,View已经显示了onWindowVisibilityChanged");
        super.onWindowVisibilityChanged(visibility);
    }
}
