package com.demo.downrefresh.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.demo.downrefresh.R;
import com.youth.banner.loader.ImageLoader;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/4/11.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide
                .with(context)
                .load(path)
                .placeholder(R.drawable.vip_center_item_1)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .bitmapTransform(new RoundedCornersTransformation(context, 5, 1))
                .centerCrop()
                .into(imageView);
    }
}
