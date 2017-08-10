package com.demo.downrefresh.zidingyiview.paint_7_shader;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/5/10.
 *
 * ComposeShader
 *  组合Shader的意思，顾名思义就是两个Shader组合在一起作为一个新Shader
 *
 * 两个构造方法:
 *
 *  ComposeShader (Shader shaderA, Shader shaderB, Xfermode mode)
 *  {@link android.graphics.Xfermode}
 *  ComposeShader (Shader shaderA, Shader shaderB, PorterDuff.Mode mode)
 *
 *        两个都差不多的，只不过一个指定了只能用PorterDuff的混合模式而另一个只要是Xfermode下的混合模式都没问题！
 */

public class ComposeShaderView extends View {


    public ComposeShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
