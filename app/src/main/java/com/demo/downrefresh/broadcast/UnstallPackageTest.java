package com.demo.downrefresh.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static android.R.attr.action;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/2/27.
 */

public class UnstallPackageTest extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String packageName = intent.getDataString();
        if (Intent.ACTION_PACKAGE_ADDED.equals(action)) {
            Log.i("hehe", "接收到了安装广播, 其值为: " + packageName);
        }
        if (Intent.ACTION_PACKAGE_REMOVED.equals(action)) {
            Log.i("hehe", "接收到了卸载广播, 其值为: " + packageName);
        }

    }

}
