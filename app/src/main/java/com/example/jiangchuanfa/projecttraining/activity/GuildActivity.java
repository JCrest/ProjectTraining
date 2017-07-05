package com.example.jiangchuanfa.projecttraining.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.fragment.GuideFragemt;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuildActivity extends AppCompatActivity {

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.btn_start_main)
    Button btnStartMain;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guild);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    /**
     * 初始化数据,添加三个Fragment
     */
    private void initData() {
        fragments = new ArrayList<>();

        Fragment fragment1 = new GuideFragemt();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("index", 1);
        fragment1.setArguments(bundle1);
        fragments.add(fragment1);

        Fragment fragment2 = new GuideFragemt();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("index", 2);
        fragment2.setArguments(bundle2);
        fragments.add(fragment2);

        Fragment fragment3 = new GuideFragemt();
        Bundle bundle3 = new Bundle();
        bundle3.putInt("index", 3);
        fragment3.setArguments(bundle3);
        fragments.add(fragment3);

        Fragment fragment4 = new GuideFragemt();
        Bundle bundle4 = new Bundle();
        bundle4.putInt("index", 4);
        fragment4.setArguments(bundle4);
        fragments.add(fragment4);

        Fragment fragment5 = new GuideFragemt();
        Bundle bundle5 = new Bundle();
        bundle5.putInt("index", 5);
        fragment5.setArguments(bundle5);
        fragments.add(fragment5);
    }

    /**
     * 设置ViewPager的适配器和滑动监听
     */
    private void initView() {
        vp.setOffscreenPageLimit(5);
        vp.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
        vp.addOnPageChangeListener(new MyPageChangeListener());
    }

    /**
     * ViewPager适配器
     */
    private class MyPageAdapter extends FragmentPagerAdapter {




        public MyPageAdapter(FragmentManager fm) {
            super(fm);

        }

//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            return super.instantiateItem(container, position);
//        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view==object;
//        }
    }

    /**
     * ViewPager滑动页面监听器
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * 根据页面不同动态改变红点和在最后一页显示立即体验按钮
         *
         * @param position
         */
        @Override
        public void onPageSelected(int position) {
//            bt_start.setVisibility(View.GONE);
//            iv1.setImageResource(R.mipmap.dot_normal);
//            iv2.setImageResource(R.mipmap.dot_normal);
//            iv3.setImageResource(R.mipmap.dot_normal);
//            if (position == 0) {
//                iv1.setImageResource(R.mipmap.dot_focus);
//            } else if (position == 1) {
//                iv2.setImageResource(R.mipmap.dot_focus);
//            } else {
//                iv3.setImageResource(R.mipmap.dot_focus);
//                bt_start.setVisibility(View.VISIBLE);
//            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @OnClick(R.id.btn_start_main)
    public void onViewClicked() {
    }


}
