package com.demo.downrefresh.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 作者：zhaozh create on 2016/4/5 16:02
 * .
 * 版权: 内什么（北京）信息技术有限公司
 * .
 * =====================================
 * .
 * 这是一个软键盘操作的类
 * .
 * 其作用是 :
 */
public class SoftInputUtils {
    public static void UpSoftInputUtils(Context context) {
        EditText view = new EditText(context);
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
