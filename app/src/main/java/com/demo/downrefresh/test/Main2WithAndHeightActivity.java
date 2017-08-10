package com.demo.downrefresh.test;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import com.demo.downrefresh.R;

import static com.demo.downrefresh.R.id.hehe;

/**
 * 主页
 *
 * @author w.w
 *
 * 获取屏幕宽高地 demo
 */
public class Main2WithAndHeightActivity extends Activity {

    private TextView size;
    private TextView size2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        size = (TextView) findViewById(R.id.size);
        size2 = (TextView) findViewById(R.id.size2);

//        size.setText("dp  " + AppInfoUtil.dip2px(this, 210));

        size2.setText("屏幕的宽度为:" + AppInfoUtil.getScreenWidth(this)
                + "\n屏幕的高度为:" + AppInfoUtil.getScreenHeight(this)
                + "\n 屏幕的密度为:" + getResources().getDisplayMetrics().density);

        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        DisplayMetrics outMetricss = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        manager.getDefaultDisplay().getRealMetrics(outMetricss);
        int width2 = outMetrics.widthPixels;
        int height2 = outMetrics.heightPixels;
        int width4 = outMetricss.widthPixels;
        int height4 = outMetricss.heightPixels;
        Log.i("hehe", "第二种方法的 宽 :" + width2 + " ,高 :" + height2 + " ,xdip="
                + outMetrics.xdpi + " , ydip=" + outMetrics.ydpi);
        Log.i("hehe", "第二种方法的real 宽 :" + width4 + " ,高 :"
                + height4 + " ,xdip=" + outMetricss.xdpi + " , ydip=" + outMetricss.ydpi + ", dpi=" + outMetricss.density);

        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width3 = dm.widthPixels;
        int height3 = dm.heightPixels;
        Log.i("hehe", "第一种方法的 宽 :" + width3 + " ,高 :" + height3);


    }

}
