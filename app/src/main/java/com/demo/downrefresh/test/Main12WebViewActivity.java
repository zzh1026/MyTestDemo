package com.demo.downrefresh.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.demo.downrefresh.R;

/**
 * 主页
 *
 * @author w.w
 *
 *  网页测试
 */
public class Main12WebViewActivity extends Activity {
    private WebView webview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);

        webview = (WebView) findViewById(R.id.webview);

    }

    public void onclick(View view) {
        loadWebView();
    }

    private void loadWebView() {
//        webview.loadUrl("http://192.168.0.109:8080/webtest/zhaozehui.jsp");
//        webview.loadUrl("http://123.118.226.153:8080/webtest/zhaozehui.jsp");
//        webview.loadUrl("http://192.168.0.109:8080/webtest/zhaozehuiht.html");
        webview.loadUrl("http://192.168.3.200/nsmapi/h5/playChildhood/page");

        WebSettings webSettings = webview.getSettings();
        webSettings.setSupportZoom(true);          //支持缩放
        webSettings.setBuiltInZoomControls(true);  //启用内置缩放装置
        webSettings.setJavaScriptEnabled(true);    //启用JS脚本
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //设置可以从缓存中取回内容
    }
}
