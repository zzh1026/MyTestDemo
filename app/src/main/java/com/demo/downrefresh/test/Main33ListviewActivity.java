package com.demo.downrefresh.test;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.demo.downrefresh.R;
import com.demo.downrefresh.bean.Main7ListViewAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

import static com.demo.downrefresh.R.id.refreshLayout;


/**
 * 主页
 *
 * @author w.w
 *         <p>
 *         listview填充数据
 */
public class Main33ListviewActivity extends Activity {
    private ListView listview;
    private TwinklingRefreshLayout refreshLayout;
    private List<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main33);
        init();
        listview = (ListView) findViewById(R.id.listview);
        Main7ListViewAdapter adapter = new Main7ListViewAdapter(this, list);
        listview.setAdapter(adapter);
        listview.setAdapter(adapter);
        listview.setNestedScrollingEnabled(true);

//        refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refreshLayout);

//        initRecyclerView();
//        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
//            @Override
//            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        refreshLayout.finishRefreshing();
//                    }
//                }, 2000);
//            }
//
//            @Override
//            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        refreshLayout.finishLoadmore();
//                    }
//                }, 2000);
//            }
//        });
//
//        refreshLayout.setNestedScrollingEnabled(true);
    }

    private void init() {
        list = new ArrayList<>();
        for (int i = 0; i <= 30; i++) {
            list.add("hehe" + i);
        }
    }


    private void initRecyclerView() {
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        if (recyclerView != null) {
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            recyclerView.setAdapter(new MyAdapter());
//        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView tv = new TextView(parent.getContext());
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(50, 50, 50, 50);
            return new MyViewHolder(tv);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(String.valueOf(list.get(position)));
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            MyViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView;
            }
        }
    }

}
