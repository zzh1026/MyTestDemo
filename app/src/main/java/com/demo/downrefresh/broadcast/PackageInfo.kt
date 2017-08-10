package com.demo.downrefresh.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 *
 * 这个类的作用是:
 *
 * Created by zhaozh on 2017/6/7.
 */
class PackageInfo : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var str = ""
        intent?.let {
            when (intent.action) {
                Intent.ACTION_PACKAGE_ADDED -> str = "安装"
                Intent.ACTION_PACKAGE_REMOVED -> str = "卸载"
            }
            Log.i("hehe", "PackageInfo接收到了${str}广播, 其值为: ${intent.dataString}")
        }
    }
}