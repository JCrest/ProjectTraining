package com.example.jiangchuanfa.projecttraining.fragment.shop;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.adapter.MyFragmentPagerAdapter;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by crest on 2017/7/6.
 */

public class ShopFragment extends BaseFragment {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_search)
    ImageButton ibSearch;
    @BindView(R.id.ib_cart)
    ImageButton ibCart;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
//
//    private TabLayout.Tab one;
//    private TabLayout.Tab two;
//    private TabLayout.Tab three;
//    private TabLayout.Tab four;
//    private TabLayout.Tab five;

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_shop, null);
        unbinder = ButterKnife.bind(this, view);

        //使用适配器将ViewPager与Fragment绑定在一起
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getFragmentManager());
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.setCurrentItem(2);

        //将TabLayout与ViewPager绑定在一起
        tabLayout.setupWithViewPager(viewPager);


//        //指定Tab的位置
//        one = tabLayout.getTabAt(0);
//        two = tabLayout.getTabAt(1);
//        three = tabLayout.getTabAt(2);
//        four = tabLayout.getTabAt(3);
//        five = tabLayout.getTabAt(4);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }







    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ib_search, R.id.ib_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_search:
                showToast("搜索");
                break;
            case R.id.ib_cart:
                showToast("购物车");
                break;
        }
    }
}



