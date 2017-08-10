package com.demo.downrefresh.zidingyiview.test_5_linearlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.downrefresh.R;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/25.
 */

public class ComplexView extends FrameLayout {
    private ImageView ivIcon;// 复合控件中的ImageView
    private TextView tvTitle;// 复合控件中的TextView

    private LayoutInflater mInflater;

    public ComplexView(Context context) {
        this(context, null);
    }

    public ComplexView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ComplexView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mInflater = LayoutInflater.from(context);
        mInflater.inflate(R.layout.zidingyi_complex, this);

        // 获取控件
        ivIcon = (ImageView) findViewById(R.id.view_complex_image_iv);
        tvTitle = (TextView) findViewById(R.id.view_complex_title_tv);
    }

}
