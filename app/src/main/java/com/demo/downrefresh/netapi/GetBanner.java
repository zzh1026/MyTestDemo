package com.demo.downrefresh.netapi;

import com.demo.downrefresh.bean.BannerResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/4/11.
 */

public interface GetBanner {
    @GET("v3/apin/service/mainpush")
    Call<BannerResponse> getBanner();
}
