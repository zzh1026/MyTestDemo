package com.demo.downrefresh.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/1/13.
 */

public class MyBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("hehe", "接收到了 nfc" + intent.getAction());
    }
}
