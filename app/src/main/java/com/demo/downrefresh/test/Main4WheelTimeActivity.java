package com.demo.downrefresh.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.model.IPickerViewData;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.demo.downrefresh.R;
import com.demo.downrefresh.bean.PickerViewData;
import com.demo.downrefresh.bean.ProvinceBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.demo.downrefresh.R.id.tv;

/**
 * 主页
 *
 * @author w.w
 *
 *  一些时间选择器
 */
public class Main4WheelTimeActivity extends Activity {
    private ArrayList<String> options1Items = new ArrayList<>();

    private TextView tvTime, tvOptions;
    TimePickerView pvTime;
    OptionsPickerView pvOptions;
    View vMasker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        vMasker = findViewById(R.id.vMasker);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvOptions = (TextView) findViewById(R.id.tvOptions);

        //时间选择器
        pvTime = new TimePickerView(this, TimePickerView.Type.HOURS_MINS);

        //控制时间范围
        Calendar calendar = Calendar.getInstance();
        pvTime.setRange(calendar.get(Calendar.HOUR_OF_DAY) > 10 ? Calendar.HOUR_OF_DAY : 10, calendar.get(Calendar.YEAR));//要在setTime 之前才有效果哦

        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);

        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                tvTime.setText(getTime(date));
            }
        });
        //弹出时间选择器
        tvTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pvTime.show();
            }
        });


        //选项选择器
        pvOptions = new OptionsPickerView(this);

        //选项1
        options1Items.add("1");
        for (int i = 1; i < 20; i++) {
            options1Items.add("" + i);
        }
        //三级联动效果
        pvOptions.setPicker(options1Items);
        pvOptions.setLabels("diyige", "dierge");
        //设置选择的三级单位
        pvOptions.setTitle("选择");
//        pvOptions.setCyclic(false, true, true);
        pvOptions.setCyclic(false);
        //设置默认选中的三级项目
        //监听确定选择按钮
//        pvOptions.setSelectOptions(1, 1, 1);
        pvOptions.setSelectOptions(1);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1);
                tvOptions.setText(tx);
                //vMasker.setVisibility(View.GONE);
            }
        });
        //点击弹出选项选择器
        tvOptions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (pvOptions.isShowing() || pvTime.isShowing()) {
                pvOptions.dismiss();
                pvTime.dismiss();
                return true;
            }
            if (pvTime.isShowing()) {
                pvTime.dismiss();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}
