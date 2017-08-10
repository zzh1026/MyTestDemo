package com.demo.downrefresh.test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.demo.downrefresh.R;
import com.demo.downrefresh.bean.Toasty;
import com.github.pierry.simpletoast.SimpleToast;

import java.util.Random;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @author w.w
 *         <p>
 *         二维码 https://github.com/bingoogolapple/BGAQRCode-Android
 *         集成的扫描和生成
 */
public class Main31ToastActivity extends Activity {
    private static final int NOTIFICATION_FLAG = 1;
    private Context mContext = this;

    Random random = new Random();
    private String[] arr;
    private SVProgressHUD svProgressHUD;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            dismiss();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main31);

        initView();


    }

    private void initView() {

    }

    public void click(View view) {
//        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
//        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//        pDialog.setTitleText("Loading");
//        pDialog.setCancelable(false);
//        pDialog.show();

        SimpleToast.ok(this, "成功");

    }

    public void click1(View view) {
        SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
        SimpleToast.error(this, "失败");

    }

    public void click2(View view) {
//        Toasty.error(this, "错误").show();
        SimpleToast.info(this, "信息");

    }

    public void click3(View view) {
//        Toasty.success(this, "成功").show();
        SimpleToast.muted(this, "其他");
    }

    public void click4(View view) {
//        Toasty.warning(this, "警告").show();
        SimpleToast.warning(this, "警告");
    }

    public void click5(View view) {
        Toast toast = Toasty.error(this, "警告有icon", Toast.LENGTH_SHORT, true);
    }

    public void click6(View view) {
        Toasty.info(this, "信息有icon", Toast.LENGTH_SHORT, true).show();
    }

    public void dismiss() {

    }
}
