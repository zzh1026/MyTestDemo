package com.demo.downrefresh.test;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.demo.downrefresh.R;

import cn.iwgang.countdownview.CountdownView;

/**
 * @author w.w
 *
 *  代码动态注册时间变化的广播
 */
public class Main28TimeBroascastActivity extends Activity {
    private CountdownView myTripNumberTime;
    private MybroadCast sendMyBroadCast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main28);

        myTripNumberTime = (CountdownView) findViewById(R.id.my_trip_number_time);

        myTripNumberTime.start(1000 * 50);

        myTripNumberTime.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                myTripNumberTime.start(1000 * 60 * 60 * 5);
            }
        });

        sendMyBroadCast = new MybroadCast();
        IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
        registerReceiver(sendMyBroadCast, filter);

    }

    public class MybroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("hehe", "接收到了时间流逝");
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(sendMyBroadCast);
        super.onDestroy();
    }
}
