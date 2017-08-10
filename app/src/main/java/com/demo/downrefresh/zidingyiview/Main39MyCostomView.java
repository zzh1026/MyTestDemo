package com.demo.downrefresh.zidingyiview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.LinearLayout;

import com.demo.downrefresh.R;
import com.demo.downrefresh.zidingyiview.test_1_mesure.ImgView;

/**
 * 自定义view
 */
public class Main39MyCostomView extends Activity {
    private ImgView consTomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main39_2);

//        LinearLayout

//        consTomView = (ImgView) findViewById(R.id.cons_tom_view);
//
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
//        consTomView.setBitmap(bitmap);
    }

    public void click(View view) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
