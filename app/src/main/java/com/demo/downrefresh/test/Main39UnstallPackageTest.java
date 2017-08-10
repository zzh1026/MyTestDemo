package com.demo.downrefresh.test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.provider.Telephony;
import android.view.View;

import com.demo.downrefresh.R;
import com.demo.downrefresh.view.CostomMyView;

/**
 * @author w.w
 *         事件的传递
 */
public class Main39UnstallPackageTest extends Activity {
    private CostomMyView mMyNormalView;

    private int radiu;// 半径值
    private boolean isChangeBig = true;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 设置自定义View的半径值
            mMyNormalView.setRadius(radiu);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main39_3);


    }

    public void click(View view) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
//        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 界面销毁后清除Handler的引用
//        mHandler.removeMessages(1);
        mHandler.removeCallbacksAndMessages(null);
    }
}
