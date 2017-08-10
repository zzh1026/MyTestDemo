package com.demo.downrefresh.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/9.
 */

public class ScreenSizeUtils {
    public static DisplayMetrics getScreenSize(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics;
    }
}
