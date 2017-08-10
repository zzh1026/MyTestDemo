package com.demo.downrefresh.zidingyiview.canvas_7_matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import com.demo.downrefresh.R;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/16.
 * <p>
 * canvas 变换 ; 平移、旋转、缩放和错切
 * <p>
 * translate(float dx, float dy)方法平移画布
 * <p>
 * scale(float sx, float sy)缩放
 * <p>
 * rotate(float degrees)和重载方法rotate(float degrees, float px, float py) 旋转
 * <p>
 * skew(float sx, float sy)错切  两个参数与scale类似表示横纵向的错切比率
 */

public class LayerView extends View {

    private Bitmap mBitmap;// 位图对象

    private int mViewWidth, mViewHeight;// 控件宽高

    public LayerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 从资源中获取位图对象
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        /*
         * 获取控件宽高
         */
        mViewWidth = w;
        mViewHeight = h;

        // 缩放位图与控件一致
        mBitmap = Bitmap.createScaledBitmap(mBitmap, mViewWidth, mViewHeight, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save(Canvas.MATRIX_SAVE_FLAG);

        //当缩放比率为1时表示不缩放
//        canvas.scale(1.0F, 1.0F);
//        canvas.scale(0.8F, 0.35F,mViewWidth,0);
//        canvas.skew(0.5F, 0F);
        Matrix matrix = new Matrix();
        matrix.setScale(0.8F, 0.35F);
        matrix.postTranslate(100, 100);
        canvas.setMatrix(matrix);

        canvas.drawBitmap(mBitmap, 0, 0, null);

        canvas.restore();
    }
}
