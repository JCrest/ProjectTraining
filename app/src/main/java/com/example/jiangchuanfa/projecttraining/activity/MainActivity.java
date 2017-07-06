package com.example.jiangchuanfa.projecttraining.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseActivity;
import com.example.jiangchuanfa.projecttraining.fragment.ExpertFragment;
import com.example.jiangchuanfa.projecttraining.fragment.MagazineFragment;
import com.example.jiangchuanfa.projecttraining.fragment.ShareFragment;
import com.example.jiangchuanfa.projecttraining.fragment.ShelfFragment;
import com.example.jiangchuanfa.projecttraining.fragment.ShopFragment;

import butterknife.BindView;

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

    @Override
    public void initListener() {

        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switchFragment(checkedId);
            }
        });
    }

    private void switchFragment(int checkedId) {
        Fragment fragment = null;
        switch (checkedId) {
            case R.id.rb_shop:
                fragment = new ShopFragment();
                Log.e("TAG", "switchFragment: " + fragment);

                break;
            case R.id.rb_magazine:
                fragment = new MagazineFragment();
                Log.e("TAG", "switchFragment: " + fragment);
                break;
            case R.id.rb_expert:
                fragment = new ExpertFragment();
                Log.e("TAG", "switchFragment: " + fragment);
                break;
            case R.id.rb_share:
                fragment = new ShareFragment();
                Log.e("TAG", "switchFragment: " + fragment);
                break;
            case R.id.rb_self:
                fragment = new ShelfFragment();
                Log.e("TAG", "switchFragment: " + fragment);
                break;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_main, fragment).commit();
    }


    @Override
    public void initData() {
        switchFragment(R.id.rb_shop);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


}



