package com.demo.downrefresh.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.downrefresh.R;
import com.demo.downrefresh.view.SGTextView;
import com.demo.downrefresh.view.SGTextView2;


/**
 * @author w.w
 *         <p>
 *         webview中 js与android的交互
 */
public class Main29AndroidAndJsActivity extends Activity {
    private Context mContext = this;

    private WebView mWebView;
    private TextView mTvShow;
    private Button mBtnNoArgs, mBtnWithArgs;

    private int i = 100;


    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main29);

        initView();

        //支持javascript
        mWebView.getSettings().setJavaScriptEnabled(true);
        //打开从本地assets中的html文件
        mWebView.loadUrl("file:///android_asset/androidAndJs.html");

//        Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("file:///android_asset/androidAndJs.html"));
//        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(it);

//        mWebView.loadUrl("file:///android_asset/test2.html");
//        mWebView.loadUrl("http://www.baidu.com");
//        mWebView.loadUrl("http://192.168.3.200/nsmapi/iphone7/show.html?id=4?token=a3cdd26e-4a02-4448-85d6-5df6577f7c83");
        //提供给js的接口名称
        mWebView.addJavascriptInterface(mContext, "android");

        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            sb.append(i + " ");
        }

        SGTextView2 tv = (SGTextView2) findViewById(R.id.textview);
        SGTextView tvs = (SGTextView) findViewById(R.id.textviews);
//        tv.setText(sb.toString());
//
        tv.setStyle("#ff0000", "#ffffff", "#ffffff", 5, 0);
        tvs.setStyle("#ff0000", "#ffffff", "#ffffff", 5, 0);
//        tv.setShadowLayer(2, 2, 2, "#000000");

    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.webview);
        mTvShow = (TextView) findViewById(R.id.tv_show);
        mBtnNoArgs = (Button) findViewById(R.id.btn_no_args);
        mBtnWithArgs = (Button) findViewById(R.id.btn_with_args);

        mBtnNoArgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("javascript:javaCallJs()");
            }
        });

        mBtnWithArgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("javascript:javaCallJsWithArgs('呵呵反正我是不相信')");
            }
        });
    }

    //供JS调用的方法
    @JavascriptInterface
    public void jsCallJava() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext, "js调用java的代码", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //供JS调用的方法
    @JavascriptInterface
    public void jsCallJavaWithArgs(final String args) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(mContext, "js调用了java的代码,并传入以下参数：\n" + args, Toast.LENGTH_SHORT).show();

                i += 100;
                String s = "已经投了 " + i + " 票了";
                SpannableStringBuilder builder = new SpannableStringBuilder(String.valueOf(i));
                ForegroundColorSpan whiteSpan = new ForegroundColorSpan(Color.BLACK);
                builder.setSpan(whiteSpan, 0, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.insert(0, "已经投了 ");
                builder.append(" 票了");
                mTvShow.setText(builder);

//                String.format("已经投 %s 票了", builder.toString())
            }
        });
    }

}
