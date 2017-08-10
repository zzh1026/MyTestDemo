package com.demo.downrefresh.anddroid_5_0;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.demo.downrefresh.R;

/**
 * @author w.w
 *         NavigationView demo
 */
public class Main48BottomSheet extends Activity {
    private NestedScrollView nsv;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main48);

        nsv = (NestedScrollView) findViewById(R.id.nsv);
        bottomSheetBehavior = BottomSheetBehavior.from(nsv);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.i("hehe", "BottomSheet关闭时");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.i("hehe", "拖拽BottomSheet时");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.i("hehe", "拖拽松开手指时");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.i("hehe", "完全展开的状态");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.i("hehe", "完全隐藏时的状态");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    public void click(View view) {
        if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        } else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    public void click2(View view) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);

        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_bottom_sheet,
                new FrameLayout(this), false);

        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.show();
    }
}
