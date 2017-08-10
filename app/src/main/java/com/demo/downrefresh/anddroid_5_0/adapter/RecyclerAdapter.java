package com.demo.downrefresh.anddroid_5_0.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.downrefresh.R;

import java.util.List;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/7/20.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {
    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;

    public RecyclerAdapter(List<String> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_list_recycleview, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.recyTv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView recyTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            recyTv = (TextView) itemView.findViewById(R.id.recy_tv);
        }
    }
}
