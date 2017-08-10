package com.demo.downrefresh.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.anbetter.danmuku.DanMuView;
import com.anbetter.danmuku.model.DanMuModel;
import com.anbetter.danmuku.model.utils.DimensionUtil;
import com.demo.downrefresh.R;
import com.demo.downrefresh.bean.BannerResponse;
import com.demo.downrefresh.netapi.GetBanner;
import com.demo.downrefresh.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.type;

/**
 * @author w.w
 *         banner图构造
 */
public class Main35BannerActivity extends Activity implements Callback<BannerResponse> {

    private Banner mBanner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main35);

        initRetrofit();

        mBanner = (Banner) findViewById(R.id.banner);
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.3.200/nsmapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetBanner getBanner = retrofit.create(GetBanner.class);
        Call<BannerResponse> banner = getBanner.getBanner();
        banner.enqueue(this);
    }

    @Override
    public void onResponse(Call<BannerResponse> call, Response<BannerResponse> response) {
        if (response.isSuccessful()) {
            Log.i("hehe", "成功");
            setBanner(response.body().getData().getMainpush());
        } else {
            Log.i("hehe", "失败");
        }
    }

    private void setBanner(List<BannerResponse.DataBean.MainpushBean> mainpush) {
        mBanner
                .setImageLoader(new GlideImageLoader())     //加载方式
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR) //banner图的样式,6种
                .setViewPagerIsScroll(true)                 //是否支持手动滑动
                .setBannerAnimation(Transformer.Accordion)    //设置banner图的动画效果,17种
                .setOffscreenPageLimit(3)                   //设置缓存多少个
                .setOnBannerListener(new OnBannerListener() {   //点击事件
                    @Override
                    public void OnBannerClick(int position) {
                        Log.i("hehe", "点击了banner的 " + position);
                    }
                })
                .setDelayTime(3000)
                .setImages(getImages(mainpush))
                .isAutoPlay(true)                           //是否自动刷新
                .setIndicatorGravity(BannerConfig.RIGHT)   //设置指示器的位置
                .start();

        Class<Banner> aClass = (Class<Banner>) mBanner.getClass();
        Log.i("hehe", "类名称:  " + aClass.getName());

        try {
            Field indicator = aClass.getDeclaredField("indicator");
            try {
                indicator.setAccessible(true);
                LinearLayout o = (LinearLayout) indicator.get(mBanner);
                o.setPadding(0, 0, 0, 0);
                indicator.setAccessible(false);
            } catch (IllegalAccessException e) {
                Log.i("hehe", "转换失败");
            }
        } catch (NoSuchFieldException e) {
            Log.i("hehe", "没有找到该方法");
        }

    }

    private List<String> getImages(List<BannerResponse.DataBean.MainpushBean> mainpush) {
        if (mainpush == null || mainpush.size() == 0) {
            return null;
        }
        List<String> bannerList = new ArrayList<>();
        for (BannerResponse.DataBean.MainpushBean banner : mainpush) {
            bannerList.add(banner.getImage());
        }
        return bannerList;
    }

    @Override
    public void onFailure(Call<BannerResponse> call, Throwable t) {
        Log.i("hehe", "访问网络失败");
    }
}
