package com.demo.downrefresh.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.demo.downrefresh.R;
import com.demo.downrefresh.bean.EventBusMessageBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Random;

/**
 * 主页
 *
 * @author w.w
 */
public class Main10EventBusActivity extends Activity {
    private String[] arr = {"春眠不觉晓", "处处闻啼鸟", "夜来风雨声", "花落知多少", "呵呵呵呵呵呵"};
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

//        EventBus.getDefault().register(this);
//        Log.i("hehe", "发送了粘性event事件");
//        EventBus.getDefault().postSticky(new EventBusMessageBean("打野拉伸"));
    }

    public void onclick(View view) {

        Log.i("hehe", "发送了粘性event事件");
        EventBus.getDefault().postSticky(new EventBusMessageBean("打野拉伸"));

        Intent intent = new Intent(this, Main11Activity.class);
        String s = arr[random.nextInt(5)];
//        Log.i("hehe", "发送的数据为: " + s);
        intent.putExtra("hehe", s);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventBusMessageBean event) {
        Log.i("hehe", "eventbus传递的数据为:  " + event.infos);
//        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        Log.i("hehe", "关闭 main10: ");
        super.onDestroy();
    }
}
