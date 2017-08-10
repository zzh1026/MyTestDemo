package com.demo.downrefresh.dagger;

import dagger.Component;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/2/15.
 */

@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(Main22Activity mainActivity);
}
