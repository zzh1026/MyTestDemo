package com.demo.downrefresh;

import android.app.Application;

import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/1/13.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        ZXingLibrary.initDisplayOpinion(this);

//        QbSdk.initX5Environment(this, null);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

        Logger.init("hehe").hideThreadInfo();
    }
}
