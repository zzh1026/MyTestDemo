package com.demo.downrefresh.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import com.demo.downrefresh.R;

/**
 * 主页
 *
 * @author w.w
 *         <p>
 *         main9 ,10  ,11 ,测试不同的task的退出
 */
public class Main9Activity extends Activity {
    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
    }

    public void onclick(View view) {
//        startActivity(new Intent(this, Main10EventBusActivity.class));
        time = new TimeCount(60000, 1000);
        time.start();

    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            Log.i("hehe", "完毕了");
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            Log.i("hehe", millisUntilFinished / 1000 + " 秒");
        }
    }


}
