package com.demo.downrefresh.zidingyiview.paint_5_maskfilter;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/9.
 */

public class MaskFilterView extends View {
    private Paint mPaint;

    public MaskFilterView(Context context) {
        this(context, null);
    }

    public MaskFilterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaskFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //取消该view的 硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xFF603811);

        /**
         * 第一个参数表示阴影模糊范围 , 值越大阴影越大
         * 第二个参数表示阴影模糊类型 ,
         * {@link BlurMaskFilter.Blur.SOLID}
         *      效果就是在图像的Alpha边界外产生一层与Paint颜色一致的阴影效果而不影响图像本身
         * {@link BlurMaskFilter.Blur.NORMAL}
         *      会将整个图像模糊掉
         * {@link BlurMaskFilter.Blur.OUTER}
         *      会在Alpha边界外产生一层阴影且会将原本的图像变透明
         * {@link BlurMaskFilter.Blur.OUTER}
         *      会在图像内部产生模糊
         *      (INNER效果其实并不理想，实际应用中我们使用的也少，我们往往会使用混合模式和渐变和获得更完美的内阴影效果)
         */
        // 设置画笔遮罩滤镜
//        mPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID));
        mPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL));
//        mPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.OUTER));
//        mPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.INNER));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        // 画一个矩形
        canvas.drawRect(measuredWidth / 2 - 400, measuredHeight / 2 - 400,
                measuredWidth / 2 + 400, measuredHeight / 2 + 400, mPaint);
    }
}
