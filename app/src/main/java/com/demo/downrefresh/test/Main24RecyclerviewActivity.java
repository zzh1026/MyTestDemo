package com.demo.downrefresh.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.demo.downrefresh.R;
import com.demo.downrefresh.bean.DividerGridItemDecoration;
import com.demo.downrefresh.bean.RecycleViewListAdapter;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;
import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import jp.wasabeef.recyclerview.animators.FadeInRightAnimator;
import jp.wasabeef.recyclerview.animators.FlipInRightYAnimator;
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;

import static com.demo.downrefresh.R.id.gridRv;

/**
 * 这个界面是来实现recyclerview
 *
 * @author w.w
 */
public class Main24RecyclerviewActivity extends Activity {
    //    @BindView(gridRv)
    RecyclerView recyclerView;

    private List<String> mDatas;

    private RecycleViewListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main24);
//        ButterKnife.bind(this);

        recyclerView = (RecyclerView) findViewById(gridRv);

        initData();
        //设置布局适配器
        //默认的linearlayout布局
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //默认的gridlayout布局
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        //默认的瀑布流布局
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));


        //设置adapter
        mAdapter = new RecycleViewListAdapter(this, mDatas);
//        recyclerView.setAdapter(mAdapter);
        SlideInRightAnimationAdapter alphaInAnimationAdapter =
                new SlideInRightAnimationAdapter(mAdapter);
//        alphaInAnimationAdapter.setDuration(500);
        alphaInAnimationAdapter.setFirstOnly(true);
        recyclerView.setAdapter(alphaInAnimationAdapter);

        //linearlayout的分割线
//        recyclerView.addItemDecoration(new DividerListItemDecoration(this,
//                DividerListItemDecoration.VERTICAL_LIST));
        //grid的分割线
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));

        //添加条目动画
        recyclerView.setItemAnimator(new FadeInAnimator());
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    public void clicks(View view) {
        mDatas.add(1, "添加一个");
//        mAdapter.notifyDataSetChanged();
        mAdapter.notifyItemInserted(1);
    }

    public void delete(View view) {
        mDatas.remove(1);
//        mAdapter.notifyDataSetChanged();
        mAdapter.notifyItemRemoved(1);
    }
}
