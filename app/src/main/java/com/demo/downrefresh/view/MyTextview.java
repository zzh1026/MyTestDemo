package com.demo.downrefresh.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/4/26.
 */

public class MyTextview extends TextView {
    public MyTextview(Context context) {
        super(context);
    }

    public MyTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 分发 touchEvent 主要是分发 click等事件的响应
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Logger.i("运行到了 MyTextview.dispatchTouchEvent  --- 接收 -- 1");
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
    public boolean onTouchEvent(MotionEvent event) {
//        Logger.i("运行到了 MyTextview.onTouchEvent  --- 处理 -- 3" + "  -- "+event.getAction());
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
