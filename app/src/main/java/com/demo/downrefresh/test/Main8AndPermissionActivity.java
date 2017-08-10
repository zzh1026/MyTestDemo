package com.demo.downrefresh.test;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.demo.downrefresh.R;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

/**
 * 主页
 *
 * @author w.w
 *
 *  andpermission 测试请求权限
 */
public class Main8AndPermissionActivity extends Activity {
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
    }

    public void btn_calendar(View view) {
        AndPermission.with(this)
                .requestCode(101)
                .permission(Manifest.permission.WRITE_CALENDAR)
                .rationale(mRationaleListener)
                .send();
    }

    public void btn_camera(View view) {
        AndPermission.with(this)
                .requestCode(102)
                .permission(Manifest.permission.CAMERA)
                .rationale(mRationaleListener)
                .send();
    }

    public void btn_contacts(View view) {
        AndPermission.with(this)
                .requestCode(103)
                .permission(Manifest.permission.WRITE_CONTACTS)
                .rationale(mRationaleListener)
                .send();
    }

    public void btn_location(View view) {
        AndPermission.with(this)
                .requestCode(104)
                .permission(Manifest.permission.ACCESS_FINE_LOCATION)
                .permission(Manifest.permission.ACCESS_COARSE_LOCATION)
                .permission(Manifest.permission.ACCESS_COARSE_LOCATION)
                .rationale(mRationaleListener)
                .send();
    }

    public void btn_microphone(View view) {
        AndPermission.with(this)
                .requestCode(105)
                .permission(Manifest.permission.RECORD_AUDIO)
                .rationale(mRationaleListener)
                .send();
    }

    public void btn_storage(View view) {
        AndPermission.with(this)
                .requestCode(106)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .rationale(mRationaleListener)
                .send();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults, listener);
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode) {
            switch (requestCode) {
                case 101:
                    Log.i("hehe", "日历" + "成功");
                    break;
                case 102:
                    Log.i("hehe", "摄像机" + "成功");
                    break;
                case 103:
                    Log.i("hehe", "联系人" + "成功");
                    break;
                case 104:
                    Log.i("hehe", "位置" + "成功");
                    break;
                case 105:
                    Log.i("hehe", "麦克风" + "成功");
                    break;
                case 106:
                    Log.i("hehe", "sd卡" + "成功");
                    break;
            }
        }

        @Override
        public void onFailed(int requestCode) {
            switch (requestCode) {
                case 101:
                    Log.i("hehe", "日历" + "失败");
                    showToSetting("日历");
                    break;
                case 102:
                    Log.i("hehe", "摄像机" + "失败");
                    showToSetting("摄像机");
                    break;
                case 103:
                    Log.i("hehe", "联系人" + "失败");
                    showToSetting("联系人");
                    break;
                case 104:
                    Log.i("hehe", "位置" + "失败");
                    showToSetting("位置");
                    break;
                case 105:
                    Log.i("hehe", "麦克风" + "失败");
                    showToSetting("麦克风");
                    break;
                case 106:
                    Log.i("hehe", "sd卡" + "失败");
                    showToSetting("sd卡");
                    break;
            }
        }
    };

    private void showToSetting(String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setCancelable(false)
                .setTitle("提示信息")
                .setMessage("当前应用缺少" + message + "权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                }).show();
    }

    /**
     * 启动当前应用设置页面
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    private RationaleListener mRationaleListener = new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
            switch (requestCode) {
                case 101:
                    message = "申请日历读写权限";
                    break;
                case 102:
                    message = "申请摄像机权限";
                    break;
                case 103:
                    message = "申请联系人权限";
                    break;
                case 104:
                    message = "申请位置权限";
                    break;
                case 105:
                    message = "申请麦克风权限";
                    break;
                case 106:
                    message = "申请sd卡读写权限";
                    break;
            }
            new AlertDialog.Builder(Main8AndPermissionActivity.this)
                    .setTitle("友好提醒")
                    .setMessage(message)
                    .setPositiveButton("好，给你", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.resume();// 用户同意继续申请。
                        }
                    })
                    .setNegativeButton("我拒绝", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.cancel(); // 用户拒绝申请。
                        }
                    }).show();
        }
    };

}
