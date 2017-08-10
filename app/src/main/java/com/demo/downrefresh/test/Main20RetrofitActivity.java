package com.demo.downrefresh.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.downrefresh.R;
import com.demo.downrefresh.bean.RestaurantResponse;
import com.demo.downrefresh.netapi.GetVersion;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 主页
 *
 * @author w.w
 *         <p>
 *         retrofit测试
 */
public class Main20RetrofitActivity extends Activity {
    String s = "http://192.168.3.200/nsmapi/v3/apin/service/mainpush";
    private EditText path;
    private TextView result;
    private Retrofit retrofit;

    String a = "{\"code\":1,\"message\":\"success\",\"data\":{\"mainpush\":[{\"id\":30,\"title\":\"网约女明星\",\"type\":8,\"link\":\"http://192.168.3.200/nsmapi/h5/superStar/page?id=1\",\"image\":\"http://192.168.3.99:8888/nsm/banner/q1duqydd3tj7eh3zxo055moq/source.png\",\"ord\":8,\"loginFlag\":0,\"shareId\":3},{\"id\":24,\"title\":\"原来你们年轻人是这样的\",\"type\":7,\"serviceId\":1,\"link\":\"http://192.168.3.200/nsmapi/newactivity/activity01.html\",\"image\":\"http://192.168.3.99:8888/nsm/banner/rgt888zaht096upt3aobr46a/source.jpg\",\"ord\":7,\"loginFlag\":1},{\"id\":21,\"title\":\"3\",\"type\":2,\"link\":\"http://www.neishenme.com/activity/2017chihewanle.html\",\"image\":\"http://192.168.3.99:8888/nsm/banner/6vjn87uxrrqhcrdhlcp05mrf/source.png\",\"ord\":6,\"loginFlag\":1},{\"id\":20,\"title\":\"2\",\"type\":5,\"link\":\"http://www.neishenme.com/index.html\",\"image\":\"http://192.168.3.99:8888/nsm/banner/1czjf0aeyrmk61o2knxed1yt/source.png\",\"ord\":5,\"loginFlag\":1},{\"id\":23,\"title\":\"一个用户10块钱\",\"type\":6,\"link\":\"http://192.168.3.200/nsmapi/share/share.html\",\"shareTitle\":\"这个App不推荐给你，我良心难安。\",\"shareImage\":\"nsm/banner/9guywi44ht4t5ca4ansgg2b5/source.png\",\"shareLink\":\"http://nsmapi.neishenme.com/ad/share/invite.html\",\"shareDescribe\":\"你的肾上腺素充能情况如何？还能出来混么\",\"image\":\"http://192.168.3.99:8888/nsm/banner/jf60a6o2urz15a6dud1gjzj2/source.jpg\",\"ord\":0,\"loginFlag\":1},{\"id\":26,\"title\":\"iPhone 7 免费送\",\"type\":6,\"serviceId\":2,\"link\":\"http://192.168.3.200/nsmapi/h5/cumulative/page?id=1\",\"shareTitle\":\"官方活动！红色iphone7免费领！\",\"shareImage\":\"nsm/shareimage/d00c8fye7rvxe7letmywnelc/source.jpg\",\"shareLink\":\"http://192.168.3.200/nsmapi/h5/cumulative/share?id=1\",\"shareDescribe\":\"瓜分iphone7,100%必得，来就有，不限量\",\"image\":\"http://192.168.3.99:8888/nsm/banner/sa9cyejxptjm51ujaywgbxh1/source.jpg\",\"ord\":0,\"loginFlag\":1}]}}";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main200);

        path = (EditText) findViewById(R.id.path);
        result = (TextView) findViewById(R.id.result);

        retrofit = new Retrofit.Builder()      //构造者模式
                .baseUrl("http://192.168.3.200/nsmapi/")
//                .callFactory(new okhttp3.Call.Factory() {
//                    @Override
//                    public okhttp3.Call newCall(Request request) {
//                        OkHttpClient okHttpClient = new OkHttpClient();
//                        okHttpClient.
////                        request.newBuilder().headers();
//                        return okHttpClient.newCall(request);
//                    }
//                })
                .callFactory(new okhttp3.Call.Factory() {
                    @Override
                    public okhttp3.Call newCall(Request request) {
                        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                .addInterceptor(new Interceptor() {
                                    @Override
                                    public okhttp3.Response intercept(Chain chain) throws IOException {
                                        Logger.e("运行到这里了 addInterceptor");
                                        Request request = chain.request();
                                        okhttp3.Response response = chain.proceed(request);
                                        ResponseBody responseBody = response.body();
//                                        Logger.json(responseBody.string());
                                        return chain.proceed(chain.request());
                                    }
                                })
//                                .addNetworkInterceptor(new Interceptor() {
//                                    @Override
//                                    public okhttp3.Response intercept(Chain chain) throws IOException {
//                                        Logger.e("运行到这里了 addNetworkInterceptor");
//                                        return chain.proceed(chain.request());
//                                    }
//                                })
                                .build();
                        return okHttpClient.newCall(request);
                    }
                })
//                .client()     //这个和callFactory道理是一样的
                .addConverterFactory(GsonConverterFactory.create()) //这是从原始数据转换成gson
//                .addCallAdapterFactory(new )  //这是把call的回调结果转换成其他
                .build();       //创建retrofit

    }

    public void clicks(View view) {

        //获取接口Service的代理对象
        GetVersion gitHubService = retrofit.create(GetVersion.class);

        //调用代理对象的方法获取到Call对象（Call对象是对Request的一层封装）
        Call<RestaurantResponse> version = gitHubService.getVersion();//设置参数

//        File file = new File(Environment.getExternalStorageDirectory(), "hehe.png");
//        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/png"), file);
//        MultipartBody.Part photoPart = MultipartBody.Part.createFormData("photos", "hehe.png", photoRequestBody);

        //异步调用
        version.enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                try {
                    Logger.i("成功" + response.raw().toString() + "\r\n" + (response.errorBody() == null));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    Logger.i("key[" + headers.name(i) + "] value[" + headers.value(i) + "]");
                }
                RestaurantResponse body = response.body();
                int code = body.getCode();
                result.setText(body.toString());
                String s = body.toString();
//                Logger.i(response.message());
            }

            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                Logger.i("访问失败");
            }
        });
    }
}
