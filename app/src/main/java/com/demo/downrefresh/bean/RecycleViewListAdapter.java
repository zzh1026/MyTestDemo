package com.demo.downrefresh.bean;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.downrefresh.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/2/17.
 */

public class RecycleViewListAdapter extends RecyclerView.Adapter {
    private List<String> mDatas;
    private Context mContext;

    public RecycleViewListAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    public void setImages(List<String> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_recycleview, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        //        @BindView(R.id.recy_tv)
        TextView tv;

        public MyViewHolder(View view) {
            super(view);
//            ButterKnife.bind(this, view);
            tv = (TextView) view.findViewById(R.id.recy_tv);
        }
    }
}
