package com.demo.downrefresh.test;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.demo.downrefresh.R;

import java.util.Random;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

/**
 * @author w.w
 *         <p>
 *         二维码 https://github.com/bingoogolapple/BGAQRCode-Android
 *         集成的扫描和生成
 */
public class Main30ZxingActivity extends Activity {
    private static final int NOTIFICATION_FLAG = 1;
    private Context mContext = this;

    private ImageView image;

    String url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
    String url1 = "http://testfile.neishenme.com/users/201702/07/554108/logo/vpycop3y9rat448kfz89injl/scale_300.jpg";
    String url2 = "http://testfile.neishenme.com/users/201702/07/554243/logo/d9pvw1ofps477yrtcf5qyc70/scale_300.jpg";
    String url4 = "http://testfile.neishenme.com/users/201702/07/553389/logo/ihr86lqvmuba9x9ffyr5qepd/scale_300.jpg";

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
        setContentView(R.layout.activity_main30);

        initView();

        arr = new String[]{url, url1, url2, url4};


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final Bitmap bitmap = QRCodeEncoder.syncEncodeQRCode("大家都是这样的呢",
//                        100);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        image.setImageBitmap(bitmap);
//                    }
//                });
//            }
//        }).start();
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                return QRCodeEncoder.syncEncodeQRCode("大家都是这样的呢", BGAQRCodeUtil.dp2px(Main30ZxingActivity.this, 200));
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    image.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(Main30ZxingActivity.this, "生成中文二维码失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();

        svProgressHUD = new SVProgressHUD(this);

    }

    private void initView() {
        image = (ImageView) findViewById(R.id.image);
    }

    public void click(View view) {
        Glide.with(this)
                .load(arr[random.nextInt(4)])
                .asBitmap()
                .centerCrop()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        // 在Android进行通知处理，首先需要重系统哪里获得通知管理器NotificationManager，它是一个系统Service。
                        NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
                        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0,
                                new Intent(mContext, Main30ZxingActivity.class), 0);
                        Notification notify = new Notification.Builder(mContext)
                                .setSmallIcon(R.drawable.hehehe)
                                .setTicker("内什么 : 下线通知")
                                .setContentTitle("内什么")
                                .setContentText("您的帐号在其他设备登录,您被迫下线")
                                .setContentIntent(pendingIntent)
                                .setStyle(new Notification.BigPictureStyle().bigPicture(
                                        Bitmap.createBitmap(resource)))
                                .setDefaults(Notification.DEFAULT_ALL)
                                .build();
                        notify.flags |= Notification.FLAG_AUTO_CANCEL;
                        manager.notify(NOTIFICATION_FLAG, notify);
                    }
                });
    }

    public void click1(View view) {
        svProgressHUD.show();
        mHandler.sendEmptyMessageDelayed(1, 3000);

    }

    public void click2(View view) {
//        svProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.Black);
//        svProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.None);
        svProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.BlackCancel);
//        svProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.Clear);
//        svProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.ClearCancel);
//        svProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.Gradient);
//        svProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.GradientCancel);
        mHandler.sendEmptyMessageDelayed(1, 3000);
    }

    public void click3(View view) {
        svProgressHUD.showWithStatus("加载中...", SVProgressHUD.SVProgressHUDMaskType.GradientCancel);
        mHandler.sendEmptyMessageDelayed(1, 3000);
    }

    public void click4(View view) {
        svProgressHUD.showInfoWithStatus("提示", SVProgressHUD.SVProgressHUDMaskType.BlackCancel);
        mHandler.sendEmptyMessageDelayed(1, 3000);
    }

    public void click5(View view) {
        svProgressHUD.showSuccessWithStatus("成功", SVProgressHUD.SVProgressHUDMaskType.Black);
        mHandler.sendEmptyMessageDelayed(1, 3000);
    }

    public void click6(View view) {
        svProgressHUD.showErrorWithStatus("失败", SVProgressHUD.SVProgressHUDMaskType.Black);
        mHandler.sendEmptyMessageDelayed(1, 3000);
    }

    public void dismiss() {
        if (svProgressHUD != null && svProgressHUD.isShowing()) {
            svProgressHUD.dismiss();
        }
    }
}
