package com.demo.downrefresh.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/4/18.
 */

public class StrokeTextView extends TextView {
    private TextView storekeText = null;        // 用于描边的TextView

    public StrokeTextView(Context context) {
        super(context);
        storekeText = new TextView(context);
        init();
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        storekeText = new TextView(context, attrs);
        init();
    }

    public StrokeTextView(Context context, AttributeSet attrs,
                          int defStyle) {
        super(context, attrs, defStyle);
        storekeText = new TextView(context, attrs, defStyle);
        init();
    }

    public void init() {
        TextPaint tp1 = storekeText.getPaint();
        tp1.setStrokeWidth(10);                         // 设置描边宽度
        tp1.setStyle(Paint.Style.STROKE);               // 对文字只描边
        storekeText.setTextColor(Color.RED);             // 设置描边颜色
        storekeText.setGravity(getGravity());
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        storekeText.setLayoutParams(params);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        CharSequence tt = storekeText.getText();

        // 两个TextView上的内容必须一致
        if (tt == null || !tt.equals(this.getText())) {
            storekeText.setText(getText());
            this.postInvalidate();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        storekeText.measure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        storekeText.layout(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        storekeText.draw(canvas);
        super.onDraw(canvas);
    }
}
