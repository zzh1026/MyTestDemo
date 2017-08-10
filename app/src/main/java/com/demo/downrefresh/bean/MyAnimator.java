package com.demo.downrefresh.bean;

import android.view.View;
import android.view.ViewGroup;

import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2016/12/21.
 */

public class MyAnimator extends BaseViewAnimator {
    @Override
    protected void prepare(View target) {
        ViewGroup parent = (ViewGroup) target.getParent();
        int distance = parent.getHeight() - target.getTop() - 30;
        int Xdistance = parent.getHeight() / 2;
        getAnimatorAgent().playTogether(
                ObjectAnimator.ofFloat(target, "alpha", 0, 0.8f, 1),
                ObjectAnimator.ofFloat(target, "scaleX", 0.1f, 0.75f, 1),
                ObjectAnimator.ofFloat(target, "scaleY", 0.1f, 0.75f, 1),
                ObjectAnimator.ofFloat(target, "rotation", -200, -50, 0),
                Glider.glide(Skill.BackEaseOut, getDuration(), ObjectAnimator.ofFloat(target, "translationY", distance, 0)),
                Glider.glide(Skill.BackEaseOut, getDuration(), ObjectAnimator.ofFloat(target, "translationX", Xdistance, 0))
        );
    }
}
