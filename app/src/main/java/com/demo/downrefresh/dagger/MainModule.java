package com.demo.downrefresh.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/2/15.
 */

@Module
public class MainModule {

    @Provides
    public Cloth getCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("红色");
        return cloth;
    }
}
