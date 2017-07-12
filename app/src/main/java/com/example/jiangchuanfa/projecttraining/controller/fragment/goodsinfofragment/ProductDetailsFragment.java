package com.example.jiangchuanfa.projecttraining.controller.fragment.goodsinfofragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.activity.GoodsInfoActivity;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;
import com.example.jiangchuanfa.projecttraining.config.Api;
import com.example.jiangchuanfa.projecttraining.modle.bean.GoodsInfoBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by crest on 2017/7/11.
 */

public class ProductDetailsFragment extends BaseFragment {

    private static final String TAG = ProductDetailsFragment.class.getSimpleName();
    @BindView(R.id.tv_goods_desc)
    TextView tvGoodsDesc;
    @BindView(R.id.tv_brand_name)
    TextView tvBrandName;
    @BindView(R.id.tv_brand_desc)
    TextView tvBrandDesc;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_rec_reason)
    TextView tvRecReason;
    Unbinder unbinder;
//    @BindView(R.id.gv_product_details)
//    RecyclerView gvProductDetails;
//    @BindView(R.id.lv_product_details)
//    ListView lvProductDetails;
    @BindView(R.id.ll_guess_you_like)
    LinearLayout llGuessYouLike;
    private String url;
    private GuessYouLikeFragment guessYouLikeFragment;
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_product_details, null);
        unbinder = ButterKnife.bind(this, view);
        //获取随机一个分类网址

        initGuessYouLikeFragment();
        return view;
    }

    private void initGuessYouLikeFragment() {
        GoodsInfoActivity goodsInfoActivity = (GoodsInfoActivity) context;
        FragmentTransaction transaction = goodsInfoActivity.getSupportFragmentManager().beginTransaction();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
        guessYouLikeFragment = new GuessYouLikeFragment();
        transaction.add(R.id.ll_guess_you_like, this.guessYouLikeFragment,"guessYouLikeFragment");
//                transaction.add(R.id.fl_main, fragment2);
        transaction.addToBackStack(null);
//                transaction.addToBackStack(null);
        transaction.show(this.guessYouLikeFragment);
        transaction.commit();
//                transaction.commit();
    }


    @Override
    public void initData() {
        super.initData();
        GoodsInfoActivity goodsInfoActivity = (GoodsInfoActivity) context;
        Bundle bundle = goodsInfoActivity.getIntent().getExtras();
        String goods_id = bundle.getString("goods_id");
        url = Api.GOODS_DETAILS_HEAD + goods_id + Api.GOOD_DETAILS_TAIL;
        Log.e(TAG, "第一次---》加载数据应用到的网址：" + url);

        getDataFromNet(url);

    }

    private void getDataFromNet(String url) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "okhttp商品详情数据请求失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(final String response, int id) {
                        Log.d(TAG, "第一次-----》这是getDataFromNet的联网数据: " + response);
                        processData(response);
                    }
                });

    }

    private void processData(String json) {
        GoodsInfoBean goodsInfoBean = new Gson().fromJson(json, GoodsInfoBean.class);
        tvGoodsDesc.setText(goodsInfoBean.getData().getItems().getGoods_desc());
        tvBrandName.setText(goodsInfoBean.getData().getItems().getBrand_info().getBrand_name());
        tvBrandDesc.setText(goodsInfoBean.getData().getItems().getBrand_info().getBrand_desc());
        tvRecReason.setText(goodsInfoBean.getData().getItems().getRec_reason());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
