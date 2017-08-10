package com.demo.downrefresh.netapi;


import com.demo.downrefresh.bean.RestaurantResponse;
import com.demo.downrefresh.bean.UpdateVersionResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/2/6.
 */

public interface GetVersion {

//    @GET("users/{user}/repos")
//    Call listRepos(@Path("user") String user);

    @GET("v3/apin/service/mainpush")
    Call<RestaurantResponse> getVersion();
}
