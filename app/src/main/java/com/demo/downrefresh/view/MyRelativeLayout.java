package com.demo.downrefresh.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.orhanobut.logger.Logger;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/4/26.
 */

public class MyRelativeLayout extends RelativeLayout {
    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Logger.i("运行到了 MyRelativeLayout.dispatchTouchEvent  --- 接收 -- 1");
        boolean b = super.dispatchTouchEvent(ev);
        try {
            Thread.sleep(100);
            return b;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return b;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        Logger.i("运行到了 MyRelativeLayout.onInterceptTouchEvent  --- 拦截 -- 2" );
        boolean b = super.onInterceptTouchEvent(ev);
        try {
            Thread.sleep(100);
            return b;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return b;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Logger.i("运行到了 MyRelativeLayout.onTouchEvent  --- 处理 -- 3");

        boolean b = super.onTouchEvent(event);
        try {
            Thread.sleep(100);
            return b;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return b;
        }
    }
}
