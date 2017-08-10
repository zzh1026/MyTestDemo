package com.demo.downrefresh.zidingyiview.paint_4_textview;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Locale;
import java.util.Random;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/8.
 */

public class StaticLayoutView extends View {
    private static final String TEXT = "设置字体类型，上面我们也使用过，Android中字体有四种样式：" +
            "BOLD（加粗）,BOLD_ITALIC（加粗并倾斜）,ITALIC（倾斜）,NORMAL（正常）；而其为我们提供" +
            "的字体有五种：DEFAULT,DEFAULT_BOLD,MONOSPACE,SANS_SERIF和SERIF，这些什么类型啊、字" +
            "体啊之类的都很简单大家自己去试试就知道就不多说了。但是系统给我们的字体有限我们可不可以使用自" +
            "己的字体呢？答案是肯定的！Typeface这个类中给我们提供了多个方法去个性化我们的字体";

    private TextPaint mTextPaint;// 文本的画笔
    private StaticLayout mStaticLayout;// 文本布局
    private Context context;

    String[] arr = {"zzh.ttf", "a.ttf"};

    public StaticLayoutView(Context context) {
        this(context, null);
    }

    public StaticLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        // 初始化画笔
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        // 实例化画笔
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(50);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setUnderlineText(true);      //下划线
        mTextPaint.setTypeface(Typeface.createFromAsset(context.getAssets(), arr[0]));
        mTextPaint.setTextLocale(Locale.US);    //位置
        mTextPaint.setStrikeThruText(true);     //删除线
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mStaticLayout = new StaticLayout(TEXT, mTextPaint, canvas.getWidth(),
                Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false);
        mStaticLayout.draw(canvas);
//        canvas.restore();
    }

    public void changeText() {
        mTextPaint.setTypeface(Typeface.createFromAsset(context.getAssets(), arr[new Random().nextInt(arr.length)]));
        invalidate();
    }
}
