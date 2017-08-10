package com.demo.downrefresh.zidingyiview.paint_3_xfermode;

/**
 * 这个类的作用是:  对 PorterDuff 的情况的解释
 * <p>
 * Created by zhaozh on 2017/5/8.
 */

public class CostentDescribe {

    /**
     * 1,PorterDuff.Mode.ADD
     * {@link android.graphics.PorterDuff.Mode#ADD}
     *
     * 计算方式：Saturate(S + D)；Chinese：饱和相加
     *
     * 从计算方式和显示的结果我们可以看到，ADD模式简单来说就是对图像饱和度进行相加，
     * 这个模式在应用中不常用。
     */

    /**
     * 2,PorterDuff.Mode.CLEAR
     * {@link android.graphics.PorterDuff.Mode#CLEAR}
     *
     * 清除图像
     */

    /**
     * 3,PorterDuff.Mode.DARKEN
     * {@link android.graphics.PorterDuff.Mode#DARKEN}
     *
     * 变暗
     */

    /**
     * 4,PorterDuff.Mode.DST
     * {@link android.graphics.PorterDuff.Mode#DST}
     *
     * 只绘制目标图像
     */

    /**
     * 5,PorterDuff.Mode.DST_ATOP
     * {@link android.graphics.PorterDuff.Mode#DST_ATOP}
     *
     * 在源图像和目标图像相交的地方绘制目标图像而在不相交的地方绘制源图像
     */

    /**
     * 6,PorterDuff.Mode.DST_IN
     * {@link android.graphics.PorterDuff.Mode#DST_IN}
     *
     * 只在源图像和目标图像相交的地方绘制目标图像
     */

    /**
     * 7,PorterDuff.Mode.DST_OUT
     * {@link android.graphics.PorterDuff.Mode#DST_OUT}
     *
     * 只在源图像和目标图像不相交的地方绘制目标图像
     */

    /**
     * 8,PorterDuff.Mode.DST_OVER
     * {@link android.graphics.PorterDuff.Mode#DST_OVER}
     *
     * 在源图像的上方绘制目标图像
     */

    /**
     * 9,PorterDuff.Mode.LIGHTEN
     * {@link android.graphics.PorterDuff.Mode#LIGHTEN}
     *
     * 变亮
     */

    /**
     * 10,PorterDuff.Mode.MULTIPLY
     * {@link android.graphics.PorterDuff.Mode#MULTIPLY}
     *
     * 正片叠底
     */

    /**
     * 11,PorterDuff.Mode.OVERLAY
     * {@link android.graphics.PorterDuff.Mode#OVERLAY}
     *
     * 叠加
     */

    /**
     * 12,PorterDuff.Mode.SCREEN
     * {@link android.graphics.PorterDuff.Mode#SCREEN}
     *
     * 滤色
     */

    /**
     * 13,PorterDuff.Mode.SRC
     * {@link android.graphics.PorterDuff.Mode#SRC}
     *
     * 显示源图
     *
     * 只绘制源图，SRC类的模式跟DIS的其实差不多就不多说了
     */

    /**
     * 14,PorterDuff.Mode.SRC_ATOP
     * {@link android.graphics.PorterDuff.Mode#SRC_ATOP}
     *
     * 在源图像和目标图像相交的地方绘制源图像，在不相交的地方绘制目标图像
     */

    /**
     * 15,PorterDuff.Mode.SRC_IN
     * {@link android.graphics.PorterDuff.Mode#SRC_IN}
     *
     * 只在源图像和目标图像相交的地方绘制源图像
     */

    /**
     * 16,PorterDuff.Mode.SRC_OUT
     * {@link android.graphics.PorterDuff.Mode#SRC_OUT}
     *
     * 只在源图像和目标图像不相交的地方绘制源图像
     */

    /**
     * 17,PorterDuff.Mode.SRC_OVER
     * {@link android.graphics.PorterDuff.Mode#SRC_OVER}
     *
     * 在目标图像的顶部绘制源图像
     */

    /**
     * 18,PorterDuff.Mode.XOR
     * {@link android.graphics.PorterDuff.Mode#XOR}
     *
     * 在源图像和目标图像重叠之外的任何地方绘制他们，而在重叠的地方不绘制任何内容
     */
}
