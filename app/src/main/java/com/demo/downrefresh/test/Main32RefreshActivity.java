package com.demo.downrefresh.test;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ListView;

import com.demo.downrefresh.R;
import com.demo.downrefresh.bean.Main7ListViewAdapter;
import com.lcodecore.tkrefreshlayout.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.header.StoreHouseHeader;


/**
 * 主页
 *
 * @author w.w
 *         <p>
 *         listview填充数据
 */
public class Main32RefreshActivity extends Activity {
    private ListView listview;
    private PtrFrameLayout refreshLayout;
    private List<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main32);
        init();
        refreshLayout = (PtrFrameLayout) findViewById(R.id.refreshLayout);
        listview = (ListView) findViewById(R.id.listview);
        Main7ListViewAdapter adapter = new Main7ListViewAdapter(this, list);
        listview.setAdapter(adapter);

        final StoreHouseHeader header = new StoreHouseHeader(this);
        header.setTextColor(Color.parseColor("#ff0000"));
        header.initWithString("HEHANIMA");

        MaterialHeader materialHeader = new MaterialHeader(this);
        materialHeader.setPadding(0, DensityUtil.dp2px(this, 20), 0, DensityUtil.dp2px(this, 5));
        refreshLayout.setHeaderView(materialHeader);
        refreshLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                Log.i("hehe", "刷新了");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                    }
                }, 2000);
            }
        });
    }

    private void init() {
        list = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            list.add("hehe" + i);
        }
    }


}
