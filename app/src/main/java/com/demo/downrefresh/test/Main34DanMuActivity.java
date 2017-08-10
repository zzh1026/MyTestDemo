package com.demo.downrefresh.test;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.anbetter.danmuku.DanMuView;
import com.anbetter.danmuku.model.DanMuModel;
import com.anbetter.danmuku.model.utils.DimensionUtil;
import com.demo.downrefresh.R;

import cn.iwgang.countdownview.CountdownView;

/**
 * @author w.w
 *
 */
public class Main34DanMuActivity extends Activity {
    private DanMuView mDanMuContainerBroadcast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main34);

        mDanMuContainerBroadcast = (DanMuView) findViewById(R.id.danmaku_container_broadcast);
        mDanMuContainerBroadcast.prepare();

    }

    public void click(View view) {
        mDanMuContainerBroadcast.add(getDanmu());
    }

    private DanMuModel getDanmu() {
        DanMuModel danMuView = new DanMuModel();
        danMuView.setDisplayType(DanMuModel.RIGHT_TO_LEFT);
        danMuView.setPriority(DanMuModel.NORMAL);
        danMuView.marginLeft = DimensionUtil.dpToPx(this, 30);

        danMuView.textSize = DimensionUtil.spToPx(this, 14);
        danMuView.textColor = ContextCompat.getColor(this, R.color.red);
        danMuView.textMarginLeft = DimensionUtil.dpToPx(this, 5);
        danMuView.text = "nimeide";

        danMuView.textBackground = ContextCompat.getDrawable(this, R.drawable.divider_bg);
        danMuView.textBackgroundMarginLeft = DimensionUtil.dpToPx(this, 15);
        danMuView.textBackgroundPaddingTop = DimensionUtil.dpToPx(this, 3);
        danMuView.textBackgroundPaddingBottom = DimensionUtil.dpToPx(this, 3);
        danMuView.textBackgroundPaddingRight = DimensionUtil.dpToPx(this, 15);
        return danMuView;
    }
}
