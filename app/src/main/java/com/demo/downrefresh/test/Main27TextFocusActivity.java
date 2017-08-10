package com.demo.downrefresh.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.downrefresh.R;

import java.util.Random;

/**
 * @author w.w
 *
 *      测试焦点问题
 */
public class Main27TextFocusActivity extends Activity {
    private TextView text;
    private EditText editText;
    private int i;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main27);

        random = new Random();

        getRandom();

        text = (TextView) findViewById(R.id.text);
        editText = (EditText) findViewById(R.id.edit_text);

        notifys();

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 1) {
                    Toast.makeText(Main27TextFocusActivity.this, "不能点击", Toast.LENGTH_SHORT).show();
                }
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Log.i("hehe", "有焦点了");
                } else {
                    Log.i("hehe", "失去了焦点");
                }
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                }
                Toast.makeText(Main27TextFocusActivity.this, "响应了配置后的按键" + actionId, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void getRandom() {
        i = random.nextInt(2);
    }

    private void notifys() {
        if (i == 0) {
            text.setText("为0 ,可以点击");
            openSoftInput(this);
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
        } else {
            text.setText("为1 ,不能点");
            closeSoftInput(this);
//            editText.setEnabled(false);
//            editText.clearFocus();
            editText.setFocusable(false);
            editText.setFocusableInTouchMode(false);
//            editText.setEnabled(true);
        }
    }

    public static void toggleSoftInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void openSoftInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void closeSoftInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public void send(View view) {
        getRandom();
        notifys();
    }

}
