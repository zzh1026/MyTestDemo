package com.demo.downrefresh.anddroid_5_0;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.view.View;

import com.demo.downrefresh.R;

/**
 * @author w.w
 *         NavigationView demo
 */
public class Main45CoordinatorLayout2 extends Activity {
    private NestedScrollView mNsv;
    private FloatingActionButton mFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main45);

        mFAB = (FloatingActionButton) findViewById(R.id.fab);
        mNsv = (NestedScrollView) findViewById(R.id.nsv);

        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mNsv, "Snackbar", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
