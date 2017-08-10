package com.demo.downrefresh.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.demo.downrefresh.R;
import com.demo.downrefresh.utils.GlideImageLoaderImagePicker;
import com.demo.downrefresh.view.MyRelativeLayout;
import com.demo.downrefresh.view.MyTextview;
import com.demo.downrefresh.zxing.QRCode;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.view.CropImageView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import static com.demo.downrefresh.R.id.imageview;

/**
 * @author w.w
 *         事件的传递
 */
public class Main37MotionEventActivity extends Activity {

    private static final int IMAGE_PICKER = 1;

    private MyRelativeLayout mRelativelayout;
    private MyTextview mTextview;
    private ImageView imageviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main37);

        mRelativelayout = (MyRelativeLayout) findViewById(R.id.relativelayout);
        mTextview = (MyTextview) findViewById(R.id.textview);
        imageviews = (ImageView) findViewById(imageview);


        mTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Logger.i("点击了text");
//                Intent intent = new Intent(Main37MotionEventActivity.this, ImageGridActivity.class);
//                startActivityForResult(intent, IMAGE_PICKER);
                imageviews.setImageBitmap(QRCode.createQRCodeWithLogo6("桂林山水甲天下", 250,
                        BitmapFactory.decodeResource(getResources(), R.drawable.vip_center_item_1)));

            }
        });

        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoaderImagePicker());   //设置图片加载器
        imagePicker.setMultiMode(false);
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(false); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.CIRCLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素

    }

    public void click(View view) {
//
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                Logger.i("共有" + images.size() + "-- 个数据");
                imageviews.setImageURI(Uri.parse(images.get(0).path));
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
