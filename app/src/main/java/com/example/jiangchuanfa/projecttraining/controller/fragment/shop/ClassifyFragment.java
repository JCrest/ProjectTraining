package com.example.jiangchuanfa.projecttraining.controller.fragment.shop;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;
import com.example.jiangchuanfa.projecttraining.config.Api;
import com.example.jiangchuanfa.projecttraining.controller.adapter.ClassifyAdapter;
import com.example.jiangchuanfa.projecttraining.modle.bean.ClassifyBean;
import com.example.jiangchuanfa.projecttraining.utils.CacheUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by crest on 2017/7/6.
 */

public class ClassifyFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;

    private String url;
    private ClassifyAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_classify, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        url = Api.SHOP_CATEGORY_URL;
        Log.e("TAG", "商店分类总的网络地址=====" + url);
        String saveJson = CacheUtils.getString(context, url);
        if (!TextUtils.isEmpty(saveJson)) {
            processData(saveJson);
        }
        getDataFromNet(url);
    }

    /**
     * OkHttpUtils联网请求数据
     * @param url
     */
    private void getDataFromNet(final String url) {
        OkHttpUtils
                .get()
                .url(url)
//                .addParams("username", "atguig")
//                .addParams("password", "123")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "okhttp商店分类数据请求失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "okhttp商店分类数据请求成功==");
                        CacheUtils.putString(context, url, response);
                        processData(response);

                    }
                });


    }

    /**
     * 数据解析
     * @param json
     */
    private void processData(String json) {
        ClassifyBean classifyBean = new Gson().fromJson(json, ClassifyBean.class);
        Log.e("TAG", "数组解析数据成功======" + classifyBean.getData().getItems().get(0).getCat_name());


        //设置布局管理器
        recyclerview.setLayoutManager(new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false));
        //设置RecyclerView的适配器
        adapter = new ClassifyAdapter(context, classifyBean.getData().getItems(), recyclerview);
        recyclerview.setAdapter(adapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
