package com.demo.downrefresh.dagger;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.downrefresh.R;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 这个界面是来  测试 dagger2的
 *
 * @author w.w
 */
public class Main22Activity extends Activity {
    private EditText path;
    private TextView result;

    @Inject
    Cloth cloth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main21);


        path = (EditText) findViewById(R.id.path);
        result = (TextView) findViewById(R.id.result);


    }

    public void clicks(View view) {
        result.setText("我现在有" + cloth.toString());
    }
}
