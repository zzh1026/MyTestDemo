package com.demo.downrefresh.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.downrefresh.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 主页
 *
 * @author w.w
 *         <p>
 *         okhttp测试
 */
public class Main18OkHttpActivity extends Activity {
    private EditText path;
    private TextView result;
    private OkHttpClient client;

    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main18);

        path = (EditText) findViewById(R.id.path);
        result = (TextView) findViewById(R.id.result);


        client = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    public void clicks(View view) {
        try {
            Request request = new Request.Builder()
                    .url(path.getText().toString().trim())
//                    .post(new FormBody.Builder().build())
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("hehe", "连接失败");
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    if (response.isSuccessful()) {
                        Headers responseHeaders = response.headers();
                        for (int i = 0; i < responseHeaders.size(); i++) {
                            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                        }
                        String set_cookie = response.header("Set-Cookie");
                        final String s = set_cookie + "\r\n ---" + response.toString() +
                                "\r\n\r\n" + response.body().string();

                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                result.setText(s);
                            }
                        });
                    } else {
                        Log.i("hehe", "连接成功但是显示失败");
                    }
                }
            });

        } catch (Exception e) {
            result.setText("异常失败了");
        }
    }

}
