package com.demo.downrefresh.test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.demo.downrefresh.R;
import com.demo.downrefresh.zxing.QRCode;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

/**
 * 主页
 *
 * @author w.w
 *
 *  二维码
 *  二维码
 */
public class Main17ZxingCodeActivity extends Activity {

    private String s = "每天都是星期日";

    private ImageView erweima;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);

        erweima = (ImageView) findViewById(R.id.erweima);

//        // 二维码中间图标
        final Bitmap centerImage = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
//        // 生成的二维码图案
//        Bitmap qrCodeImage = new QRCodeEncoder.Builder()
//                .width(AppInfoUtil.dip2px(this,150)) // 二维码图案的宽度
//                .height(AppInfoUtil.dip2px(this,150))
//                .paddingPx(0) // 二维码的内边距
//                .marginPt(0) // 二维码的外边距
//                .centerImage(centerImage) // 二维码中间图标
//                .build()
//                .encode(s);
//
//        erweima.setImageBitmap(qrCodeImage);

        int i = AppInfoUtil.dip2px(this, 150);
        Log.i("hehe", "长度为: " + i);
        erweima.setImageBitmap(QRCode.createQRCode("http://www.tmtpost.com/2536837.html", i));
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

}
