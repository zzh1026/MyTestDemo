package com.demo.downrefresh.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.aigestudio.wheelpicker.widgets.WheelDatePicker;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.demo.downrefresh.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.demo.downrefresh.R.id.tvOptions;
import static com.demo.downrefresh.R.id.tvTime;
import static com.demo.downrefresh.R.id.vMasker;

/**
 * 主页
 *
 * @author w.w
 *
 * WheelDatePicker 控件
 */
public class Main5WheelPickerActivity extends Activity {
    List<String> arr = new ArrayList<>();
    private WheelDatePicker hehe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        for (int i = 100000; i <= 100050; i++) {
            arr.add("" + i);
        }
        hehe = (WheelDatePicker) findViewById(R.id.hehe);
        hehe.setOnDateSelectedListener(new WheelDatePicker.OnDateSelectedListener() {
            @Override
            public void onDateSelected(WheelDatePicker picker, Date date) {
                Log.i("heheh", "date 为: " + date.toString());

            }
        });

//        hehe.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(WheelPicker picker, Object data, int position) {
//                Log.i("heheh", "position" + position);
//            }
//        });
    }

}
