package com.demo.downrefresh.bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.downrefresh.R;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

/**
 * ListView适配器
 *
 * @author w.w
 */
public class Main7ListViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    List<String> list;

    public Main7ListViewAdapter(Context context, List<String> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_listview, parent, false);
        TextView textview = (TextView) view.findViewById(R.id.item_name);
        final SwipeMenuLayout layout = (SwipeMenuLayout) view.findViewById(R.id.layout);
        TextView tv = (TextView) view.findViewById(R.id.tv);

        RelativeLayout container = (RelativeLayout) view.findViewById(R.id.container);
        if (position == 2) {
            container.setVisibility(View.GONE);
        }

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.quickClose();
            }
        });

        String item = getItem(position);
        textview.setText(item);
        return view;
    }
}
