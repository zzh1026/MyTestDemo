package com.demo.downrefresh.test;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import com.demo.downrefresh.R;
import com.demo.downrefresh.broadcast.SendMyBroadCast;
import com.demo.downrefresh.broadcast.SendOrderMyBroadCast;

/**
 * @author w.w
 *
 *  广播测试
 */
public class Main26BroadCastActivity extends Activity {

    private SendMyBroadCast sendMyBroadCast;
    private IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main26);

//        sendMyBroadCast = new SendMyBroadCast();
//        filter = new IntentFilter("con.neishenme.sendmyself");
//        registerReceiver(sendMyBroadCast, filter);


    }

    public void send(View view) {
        Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
//        sendOrderedBroadcast(intent, null, new SendOrderMyBroadCast.LowPriority(), new Handler(), 0, "hehe", null);

//        //1,获取本地广播的管理器
//        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this);
////        //2,动态注册广播
////        instance.registerReceiver(sendMyBroadCast, filter);
//        //3,发送广播
//        instance.sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(sendMyBroadCast);
        super.onDestroy();
    }
}
