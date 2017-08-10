package com.demo.downrefresh.anddroid_5_0;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.demo.downrefresh.R;
import com.demo.downrefresh.anddroid_5_0.adapter.Main42PagerAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author w.w
 *         NavigationView demo
 */
public class Main43NavigationView extends Activity {
    private ViewPager viewpagers;
//    private ListView listView;
    private DrawerLayout drawerlayout;
    private TextView textview;
    private List<String> mDatas;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main43);

        initView();

        initData();

        viewpagers.setAdapter(new Main42PagerAdapter(this));

//        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mDatas));
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                textview.setText("选中的值为: " + mDatas.get(position));
//                drawerlayout.closeDrawer(listView);
//            }
//        });

        initNavigationView();

    }

    private void initNavigationView() {
        navigationView.setItemIconTintList(null);

        /**
         * 当item过多会出现滚动条，如果要隐藏滚动条，
         * 给NavigationView设置android:scrollbars="none"是不管用的，
         * 因为这个滚动条不是NavigationView的，而是菜单的·.
         * 可以在Activity中找到菜单View，然后取消滚动条
         */
        NavigationMenuView menuView  = (NavigationMenuView) navigationView.getChildAt(0);
        menuView.setVerticalScrollBarEnabled(false);
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'Z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    private void initView() {
        viewpagers = (ViewPager) findViewById(R.id.viewpagers);
//        listView = (ListView) findViewById(R.id.list_view);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        textview = (TextView) findViewById(R.id.textview);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
    }


    public void toogle(View view) {
        drawerlayout.openDrawer(GravityCompat.START);
    }
}
