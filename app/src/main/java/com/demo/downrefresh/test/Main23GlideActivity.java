package com.demo.downrefresh.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.demo.downrefresh.R;

/**
 * 这个界面是测试glide
 *
 * @author w.w
 */
public class Main23GlideActivity extends Activity {
    private ImageView result;
    public static String url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main23);

        result = (ImageView) findViewById(R.id.imageview);


    }

    public void clicks(View view) {

//        Glide
//                .with(this)
//                .load(url)
//                .placeholder(R.drawable.vip_center_item_1)
//                .crossFade()
//                .bitmapTransform(new GlideRoundTransform(this))
//                .centerCrop()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(result);

        boolean b = true;
//                .asGif()
        Glide
                .with(this)
                .load(R.drawable.gif2)
                .asGif()
                .diskCacheStrategy(b ? DiskCacheStrategy.NONE : DiskCacheStrategy.SOURCE)
                .into(result);
//                .into(new GlideDrawableImageViewTarget(result, 1));


    }
}
