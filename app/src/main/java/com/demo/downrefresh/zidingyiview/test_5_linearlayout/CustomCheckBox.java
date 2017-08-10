package com.demo.downrefresh.zidingyiview.test_5_linearlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.demo.downrefresh.R;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/25.
 */

public class CustomCheckBox extends FrameLayout {
    private ImageView ivCheckOn, ivCheckOff;// 两种状态的ImageView
    private CustomCheckBoxChangeListener customCheckBoxChangeListener;// 切换的监听器

    private boolean isCheck;// 是否被选中的标志值

    public CustomCheckBox(Context context) {
        this(context, null);
    }

    public CustomCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 设置布局文件
        LayoutInflater.from(context).inflate(R.layout.my_costom_checkbox, this);

        // 获取控件元素
        ivCheckOn = (ImageView) findViewById(R.id.view_custom_check_box_on);
        ivCheckOff = (ImageView) findViewById(R.id.view_custom_check_box_off);

        // 设置两个ImageView的点击事件
        ivCheckOn.setOnClickListener(new ClickListener());
        ivCheckOff.setOnClickListener(new ClickListener());

        // 读取xml中设置的资源属性ID
//        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomCheckBox);
//        int imageOnResId = array.getResourceId(R.styleable.CustomCheckBox_imageOn, -1);
//        int imageOffResId = array.getResourceId(R.styleable.CustomCheckBox_imageOff, -1);

        // 设置显示资源
//        setOnImage(imageOnResId);
//        setOffImage(imageOffResId);

        // 对象回收
//        array.recycle();

        // 默认显示的是没被选中的状态
        setCheckOff();
    }

    /**
     * 设置开启状态时CustomCheckBox的图片
     *
     * @param resId 图片资源ID
     */
    public void setOnImage(int resId) {
        ivCheckOn.setImageResource(resId);
    }

    /**
     * 设置关闭状态时CustomCheckBox的图片
     *
     * @param resId 图片资源ID
     */
    public void setOffImage(int resId) {
        ivCheckOff.setImageResource(resId);
    }

    /**
     * 设置CustomCheckBox为关闭状态
     */
    public void setCheckOff() {
        isCheck = false;
        ivCheckOn.setVisibility(GONE);
        ivCheckOff.setVisibility(VISIBLE);
    }

    /**
     * 设置CustomCheckBox为开启状态
     */
    public void setCheckOn() {
        isCheck = true;
        ivCheckOn.setVisibility(VISIBLE);
        ivCheckOff.setVisibility(GONE);
    }

    /**
     * 获取CustomCheckBox的选择状态
     *
     * @return false CustomCheckBox未被选择
     */
    public boolean isCheck() {
        return isCheck;
    }


    /**
     * 为CustomCheckBox设置监听器
     *
     * @param customCheckBoxChangeListener 监听器接口对象
     */
    public void setCustomCheckBoxChangeListener(
            CustomCheckBoxChangeListener customCheckBoxChangeListener) {
        this.customCheckBoxChangeListener = customCheckBoxChangeListener;
    }

    /**
     * 状态改变监听接口
     */
    public interface CustomCheckBoxChangeListener {
        void customCheckBoxOn();

        void customCheckBoxOff();
    }


    /**
     * 自定义CustomCheckBox中控件的事件监听器
     */
    private class ClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.view_custom_check_box_on:
                    setCheckOff();
                    if (customCheckBoxChangeListener != null)
                        customCheckBoxChangeListener.customCheckBoxOff();
                    break;
                case R.id.view_custom_check_box_off:
                    setCheckOn();
                    if (customCheckBoxChangeListener != null)
                        customCheckBoxChangeListener.customCheckBoxOn();
                    break;
            }
        }
    }
}
