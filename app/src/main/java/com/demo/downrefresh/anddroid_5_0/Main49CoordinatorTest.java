package com.demo.downrefresh.anddroid_5_0;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.demo.downrefresh.R;
import com.demo.downrefresh.anddroid_5_0.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author w.w
 *         CoordinatorLayout测试
 */
public class Main49CoordinatorTest extends Activity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main49);

        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        initRecycler();
    }

    private void initRecycler() {
        mRecyclerView.setAdapter(new RecyclerAdapter(mDatas, this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                mDatas.add("i = " + i);
            }
        }
    }
}
