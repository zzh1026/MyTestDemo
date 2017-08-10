package com.demo.downrefresh.utils;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.demo.downrefresh.R;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/3.
 */

public class GlideImageLoaderImagePicker implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide
                .with(activity)
                .load(Uri.fromFile(new File(path)))
                .placeholder(R.drawable.bg_overlay_gradient)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .bitmapTransform(new RoundedCornersTransformation(activity, 5, 1))
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
