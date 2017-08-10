package com.demo.downrefresh.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.demo.downrefresh.R;
import com.demo.downrefresh.view.CostomMyView;

/**
 * @author w.w
 *         事件的传递
 */
public class Main38CostomViewActivity extends Activity {
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
        setContentView(R.layout.activity_main38);

        mMyNormalView = (CostomMyView) findViewById(R.id.my_normal_view);

    }

    public void click(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 界面销毁后清除Handler的引用
//        mHandler.removeMessages(1);
        mHandler.removeCallbacksAndMessages(null);
    }
}
