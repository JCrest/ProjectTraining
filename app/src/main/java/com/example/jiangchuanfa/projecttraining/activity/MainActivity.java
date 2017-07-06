package com.example.jiangchuanfa.projecttraining.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseActivity;
import com.example.jiangchuanfa.projecttraining.fragment.ShopFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.rb_shop)
    RadioButton rbShop;
    @BindView(R.id.rb_magazine)
    RadioButton rbMagazine;
    @BindView(R.id.rb_expert)
    RadioButton rbExpert;
    @BindView(R.id.rb_share)
    RadioButton rbShare;
    @BindView(R.id.rb_self)
    RadioButton rbSelf;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;

    private Fragment[] mFragments;

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @OnClick({R.id.rb_shop, R.id.rb_magazine, R.id.rb_expert, R.id.rb_share, R.id.rb_self})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_shop:
                break;
            case R.id.rb_magazine:
                break;
            case R.id.rb_expert:
                break;
            case R.id.rb_share:
                break;
            case R.id.rb_self:
                break;
        }
    }
}



