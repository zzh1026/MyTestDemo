package com.demo.downrefresh.test;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.demo.downrefresh.R;
import com.yanzhenjie.permission.AndPermission;

import static com.demo.downrefresh.R.id.button05;

/**
 *
 * 二维码扫描
 */
public class Main15ZXingActivity extends Activity implements View.OnClickListener {
    private int REQUEST_CODE = 1;
    private Button button05a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main15);

        button05a = (Button) findViewById(button05);

        button05a.setOnClickListener(this);

        AndPermission.send(this, 0, Manifest.permission.CAMERA);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button05:
//                Intent intent = new Intent(this, CaptureActivity.class);
//                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_CODE) {
//            //处理扫描结果（在界面上显示）
//            if (null != data) {
//                Bundle bundle = data.getExtras();
//                if (bundle == null) {
//                    return;
//                }
//                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
//                    String result = bundle.getString(CodeUtils.RESULT_STRING);
//                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
//                    Log.i("hehe", result);
//                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
//                    Toast.makeText(this, "解析二维码失败", Toast.LENGTH_LONG).show();
//                    Log.i("hehe", "失败");
//                }
//            }
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
