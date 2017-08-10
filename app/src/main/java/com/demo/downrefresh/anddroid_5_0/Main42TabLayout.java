package com.demo.downrefresh.anddroid_5_0;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toolbar;

import com.demo.downrefresh.R;
import com.demo.downrefresh.anddroid_5_0.adapter.Main42PagerAdapter;
import com.orhanobut.logger.Logger;

/**
 * @author w.w
 *         ConstraintLayout demo
 */
public class Main42TabLayout extends Activity {
    private TabLayout mTablayout;
    private ViewPager mViewpager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main42);

        mTablayout = (TabLayout) findViewById(R.id.tablayout);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);

        mViewpager.setAdapter(new Main42PagerAdapter(this));

        mTablayout.setupWithViewPager(mViewpager, false);

//        Logger.e("tab 的数量为:" + mTablayout.getTabCount());
//
//        TabLayout.Tab tab = null;
//        for (int i = 0; i < mViewpager.getAdapter().getCount(); i++) {
//            tab = mTablayout.newTab();
//            tab.setText("第" + i + "个");
//            mTablayout.addTab(tab);
//        }
//
//        Logger.e("tab 的数量为:" + mTablayout.getTabCount());

        /** 这种是解决tablayout关联viewpager后tab不显示的第二种方法,将所有的tab进行修改 */
        TabLayout.Tab tab = null;
        for (int i = 0; i < mTablayout.getTabCount(); i++) {
            tab = mTablayout.getTabAt(i);
            tab.setText("第" + i + "个");
        }

        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Snackbar.make(mViewpager, "选中了" + tab.getText(), Snackbar.LENGTH_SHORT).show();
                Logger.e("被选中的 tab 为 " + tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Logger.e("未被选中的 tab 为 " + tab.getText());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //再次选中tab的逻辑
                Logger.e("再次选中 tab 为 " + tab.getText());
            }
        });

    }
}
