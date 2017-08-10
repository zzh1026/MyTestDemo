package com.demo.downrefresh.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/2/27.
 */

public class SendOrderMyBroadCast {
    public static class HignPriority extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("hehe", "高权限获接收到了广播");

            int resultCode = getResultCode();
            Log.i("hehe", "高权限code为: " + resultCode);
            String resultData = getResultData();
            Log.i("hehe", "高权限resultData为: " + resultData);

//            abortBroadcast();

            int code = 10;
            String data = "hellohigh";
            setResult(code, data, null);

        }
    }

    public static class MidPriority extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("hehe", "中等权限获接收到了广播");

            int resultCode = getResultCode();
            Log.i("hehe", "中等权限code为: " + resultCode);
            String resultData = getResultData();
            Log.i("hehe", "中等权限resultData为: " + resultData);

            int code = 100;
            String data = "hellomid";
            setResult(code, data, null);

            abortBroadcast();
        }
    }

    public static class LowPriority extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("hehe", "低权限获接收到了广播");

            int resultCode = getResultCode();
            Log.i("hehe", "低权限code为: " + resultCode);
            String resultData = getResultData();
            Log.i("hehe", "低权限resultData为: " + resultData);

            abortBroadcast();
        }
    }
}
