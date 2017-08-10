package com.demo.downrefresh.test;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.demo.downrefresh.R;
import com.demo.downrefresh.bean.Main7ListViewAdapter;
import com.jaeger.library.StatusBarUtil;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.BallPulseView;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.bezierlayout.BezierLayout;

import java.util.ArrayList;
import java.util.List;

import qiu.niorgai.StatusBarCompat;

import static android.R.id.list;
import static com.lzy.okgo.OkGo.getContext;

/**
 * 主页
 *
 * @author w.w
 *         <p>
 *         listview填充数据
 */
public class Main7ThinkRefreshListviewActivity extends Activity {
    private ListView listview;
    private TwinklingRefreshLayout refreshLayout;
    private List<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main7);

//        StatusBarCompat.translucentStatusBar(this);
        StatusBarUtil.setTranslucent(this, 50);
//        getWindow().setStatusBarColor(Color.parseColor("#33ff0000"));
        init();
        listview = (ListView) findViewById(R.id.listview);
        refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refreshLayout);
        Main7ListViewAdapter adapter = new Main7ListViewAdapter(this, list);
        listview.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadmore();
                    }
                }, 6000);
            }
        });

        LoadingView ballPulseView = new LoadingView(this);
        refreshLayout.setBottomView(ballPulseView);


    }

    private void init() {
        list = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            list.add("hehe" + i);
        }
    }


}
