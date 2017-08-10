package com.demo.downrefresh.anddroid_5_0.wedgit;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/7/18.
 */

/**
 * 随着手指移动的TextView
 */
public class MyDragTextView extends TextView {
    private int lastX;
    private int lastY;

    private CoordinatorLayout.MarginLayoutParams layoutParams;

    public MyDragTextView(Context context) {
        super(context);
    }

    public MyDragTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDragTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                layoutParams = (CoordinatorLayout.MarginLayoutParams) getLayoutParams();

                int left = layoutParams.leftMargin + x - lastX;
                int top = layoutParams.topMargin + y - lastY;
                layoutParams.leftMargin = left;
                layoutParams.topMargin = top;

                setLayoutParams(layoutParams);
                invalidate();
//                requestLayout();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        lastX = x;
        lastY = y;
        return true;
    }
}
