package com.example.jiangchuanfa.projecttraining.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.jiangchuanfa.projecttraining.modle.bean.HomeBean;

import java.util.List;

/**
 * Created by crest on 2017/7/7.
 */

public class HomeAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final List<HomeBean.DataBean.ItemsBean.ListBean> datas;

    public HomeAdapter(Context context, List<HomeBean.DataBean.ItemsBean.ListBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
