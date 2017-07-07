package com.example.jiangchuanfa.projecttraining.controller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by crest on 2017/7/7.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        textView.setText("aaa");
        return new MyHolder(textView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

}

class MyHolder extends RecyclerView.ViewHolder{

    public MyHolder(View itemView) {
        super(itemView);
    }
}
