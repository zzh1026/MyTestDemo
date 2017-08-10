package com.demo.downrefresh.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.demo.downrefresh.R;
import com.demo.downrefresh.bean.EventBusMessageBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusBuilder;

/**
 * 主页
 *
 * @author w.w
 */
public class Main11Activity extends Activity {

    public static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: //应该连接socket
                    Log.i("hehe", "接收到了handler的数据,地址是: " + mHandler.getLooper().toString());
                    mHandler.sendEmptyMessageDelayed(1, 1500);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        String hehe = getIntent().getStringExtra("hehe");
//        Log.i("hehe", "接收到的数据为: " + hehe);
        setContentView(R.layout.activity_main11);

        EventBusMessageBean stickyEvent = EventBus.getDefault().getStickyEvent(EventBusMessageBean.class);
        Log.i("hehe", "得到的字符串为 : " + stickyEvent.infos);
    }

    public void onclick(View view) {
//        startActivity(new Intent(this, Main9Activity.class));
//        mHandler.sendEmptyMessageDelayed(1, 1500);
        EventBus.getDefault().post(new EventBusMessageBean("你妹妹的腿"));

    }

    @Override
    protected void onDestroy() {
        Log.i("hehe", "销毁了: ");
        mHandler.removeMessages(1);
        super.onDestroy();
    }
}
