package com.demo.downrefresh.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;
import com.demo.downrefresh.R;
import com.demo.downrefresh.bean.MyAnimator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

import static android.view.View.Y;

/**
 * 主页
 *
 * @author w.w
 *
 *  测试动画效果 animation库 demo
 */
public class Main3AnimationActivity extends Activity {
    private TextView tv;
    private YoYo.YoYoString rope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        tv = (TextView) findViewById(R.id.tv);


    }

    public void click(View view) {
        YoYo.AnimationComposer m1AnimationComposer = YoYo
                .with(Techniques.DropOut)
                .duration(1200);
        YoYo.AnimationComposer m2AnimationComposer = YoYo
                .with(Techniques.RotateIn)
                .duration(400);
        YoYo.AnimationComposer m3AnimationComposer = YoYo
                .with(new MyAnimator())
                .duration(1200);
//        m1AnimationComposer.playOn(tv);
//        m2AnimationComposer.playOn(tv);
        m3AnimationComposer.playOn(tv);
    }

    public void haha(View view) {

        Log.i("hehe", "点击了");
    }
}
