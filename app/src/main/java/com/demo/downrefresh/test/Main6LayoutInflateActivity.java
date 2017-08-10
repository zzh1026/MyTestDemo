package com.demo.downrefresh.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.FrameLayout;

import com.aigestudio.wheelpicker.WheelPicker;
import com.demo.downrefresh.R;

import java.util.ArrayList;
import java.util.List;

import static com.demo.downrefresh.R.id.hehe;

/**
 * 主页
 *
 * @author w.w
 *
 *  测试LayoutInflater.inflate 和 view.inflate
 */
public class Main6LayoutInflateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        ViewGroup v = (ViewGroup) findViewById(R.id.fff);
        v.addView(getView());
//        v.addView(vv);
    }

    private View getView() {
        View vv = LayoutInflater.from(this).inflate(R.layout.test_6, new FrameLayout(this));
        return vv;
    }

}
