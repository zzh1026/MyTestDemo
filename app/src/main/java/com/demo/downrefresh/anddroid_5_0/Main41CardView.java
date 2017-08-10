package com.demo.downrefresh.anddroid_5_0;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.demo.downrefresh.R;
import com.demo.downrefresh.test.MainActivity;

/**
 * @author w.w
 *         ConstraintLayout demo
 */
public class Main41CardView extends Activity {
    private Toolbar mMToolBar;
    private TextInputLayout mTextinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main41);
        mMToolBar = (Toolbar) findViewById(R.id.m_tool_bar);
        mTextinput = (TextInputLayout) findViewById(R.id.textinput);

        //调试版测试
        testPatelle();

        //设置工具栏
        initToolBar();

        //设置输入框
        initTextInputLayout();

    }

    private void initTextInputLayout() {

    }

    private void initToolBar() {
        // 设置Logo
        mMToolBar.setLogo(R.drawable.girl);

        //设置标题
        mMToolBar.setTitle("android 5.0");

        //设置子标题
        mMToolBar.setSubtitle("新控件 toolbar");

        //设置ActionBar，之后就可以获取ActionBar并进行操作，操作的结果就会反应在toolbar上面
        setActionBar(mMToolBar);

        //设置了返回箭头,，相当于设置了toolbar的导航按钮
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void testPatelle() {
        // 获取应用程序图标的Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
        // 通过bitmap生成调色板palette
//        Palette palette=Palette.from(bitmap).generate();
        // 获取palette充满活力色颜色
//        int vibrantColor=palette.getVibrantColor(Color.WHITE);
    }

    public void clicks(View view) {
        if (mTextinput.isErrorEnabled()) {
            mTextinput.setErrorEnabled(false);
        } else {
            mTextinput.setErrorEnabled(true);
            mTextinput.setError("出现了错误");
        }

        Snackbar.make(mMToolBar, "toast", 0).setAction("交互", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main41CardView.this, "白云外", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }

}
