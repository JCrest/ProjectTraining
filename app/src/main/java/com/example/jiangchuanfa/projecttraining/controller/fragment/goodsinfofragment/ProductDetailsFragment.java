package com.example.jiangchuanfa.projecttraining.controller.fragment.goodsinfofragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.jiangchuanfa.projecttraining.base.BaseFragment;

/**
 * Created by crest on 2017/7/11.
 */

public class ProductDetailsFragment extends BaseFragment {
    TextView textView;
    @Override
    public View initView() {
        textView =new TextView(context);
        textView.setText("你好啊！");
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();


    }
}
