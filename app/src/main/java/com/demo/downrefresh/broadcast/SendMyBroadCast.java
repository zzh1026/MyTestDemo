package com.demo.downrefresh.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/2/27.
 */

public class SendMyBroadCast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("hehe", "接收到了广播, 其值为: " + intent.getAction());
    }
}
