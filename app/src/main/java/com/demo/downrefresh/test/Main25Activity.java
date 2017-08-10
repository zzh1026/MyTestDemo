package com.demo.downrefresh.test;

import android.app.Activity;
import android.os.Bundle;

import com.demo.downrefresh.R;

/**
 * @author w.w
 *
 *  测试多次打开activity的时候的 onnewintent 的调用
 */
public class Main25Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main25);


    }
}
