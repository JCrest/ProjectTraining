package com.example.jiangchuanfa.projecttraining.activity;

import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseActivity;
import com.example.jiangchuanfa.projecttraining.config.Api;
import com.example.jiangchuanfa.projecttraining.controller.adapter.ClassifyAdapter;
import com.example.jiangchuanfa.projecttraining.modle.bean.ClassifyBean;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import okhttp3.Call;

/**
 * Created by crest on 2017/7/8.
 */

public class GoodsInfoActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.ib_cart)
    ImageButton ibCart;
    @BindView(R.id.ll_price_screening)
    LinearLayout llPriceScreening;
    @BindView(R.id.refreshable_recycle_view)
    XRecyclerView refreshableRecycleView;
    @BindView(R.id.ib_search)
    ImageButton ibSearch;

    private String url;
    private ClassifyAdapter adapter;



    @Override
    public void initView() {
        super.initView();
        ibBack.setVisibility(View.VISIBLE);
        ibSearch.setVisibility(View.GONE);

        refreshableRecycleView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);

        //设置布局管理器
        refreshableRecycleView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        //设置RecyclerView的适配器
        adapter = new ClassifyAdapter(this);
        refreshableRecycleView.setAdapter(adapter);


    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        tvTitle.setText("商店");

        url = Api.SHOP_CATEGORY_URL;
        Log.e("TAG", "商店分类总的网络地址=====" + url);
        getDataFromNet(url);


    }


    /**
     * OkHttpUtils联网请求数据
     *
     * @param url
     */
    private void getDataFromNet(final String url) {

        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "okhttp商店分类数据请求失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(final String response, int id) {
                        Log.d("aaaaa", "onResponse: " + response);
                        processData(response);
                    }
                });
    }

    /**
     * 数据解析
     *
     * @param json
     */
    private void processData(String json) {
        ClassifyBean classifyBean = new Gson().fromJson(json, ClassifyBean.class);
        Log.e("TAG", "数组解析数据成功======" + classifyBean.getData().getItems().get(0).getCat_name());
        adapter.refresh(classifyBean.getData().getItems());
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_list;
    }


}
