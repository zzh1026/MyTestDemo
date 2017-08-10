package com.demo.downrefresh.dagger;

import javax.inject.Inject;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/2/15.
 */

public class Cloth {
    private String color;

    @Inject
    public Cloth() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Cloth{布料}";
    }
}
