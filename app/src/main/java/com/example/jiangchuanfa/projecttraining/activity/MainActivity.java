package com.example.jiangchuanfa.projecttraining.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseActivity;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;
import com.example.jiangchuanfa.projecttraining.fragment.expert.ExpertFragment;
import com.example.jiangchuanfa.projecttraining.fragment.magazine.MagazineFragment;
import com.example.jiangchuanfa.projecttraining.fragment.share.ShareFragment;
import com.example.jiangchuanfa.projecttraining.fragment.self.ShelfFragment;
import com.example.jiangchuanfa.projecttraining.fragment.shop.ShopFragment;

import java.util.ArrayList;

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
    private ArrayList<BaseFragment> fragments;
    //Fragment页面的下标位置
    private int position;
    //缓存的Fragment
    private Fragment tempFragment;

    @Override
    public void initView() {
        fragments = new ArrayList<>();
        fragments.add(new ShopFragment());//商店
        fragments.add(new MagazineFragment());//杂志
        fragments.add(new ExpertFragment());//达人
        fragments.add(new ShareFragment());//分享
        fragments.add(new ShelfFragment());//个人
    }

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
                Log.e("TAG", "switchFragment: " + fragment);
                position = 0;
                break;
            case R.id.rb_magazine:
                position = 1;
                Log.e("TAG", "switchFragment: " + fragment);
                break;
            case R.id.rb_expert:
                position = 2;
                Log.e("TAG", "switchFragment: " + fragment);
                break;
            case R.id.rb_share:
                position = 3;
                Log.e("TAG", "switchFragment: " + fragment);
                break;
            case R.id.rb_self:
                position = 4;
                Log.e("TAG", "switchFragment: " + fragment);
                break;
        }
        //Fragment-当前的Fragment
        Fragment currentFragment = fragments.get(position);
        selectFragment(currentFragment);
    }

    private void selectFragment(Fragment currentFragment) {
        if (tempFragment != currentFragment) {
            //开启事务
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //切换
            if (currentFragment != null) {
                //是否添加过
                if (!currentFragment.isAdded()) {
                    //把之前显示的给隐藏
                    if (tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    //如果没有添加就添加
                    ft.add(R.id.fl_main, currentFragment);
                } else {

                    //把之前的隐藏
                    if (tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    //如果添加了就直接显示
                    ft.show(currentFragment);
                }
                //最后一步，提交事务
                ft.commit();
            }
            tempFragment = currentFragment;
        }
    }

    @Override
    public void initData() {

        switchFragment(R.id.rb_shop);
        rgMain.check(R.id.rb_shop);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


}



