package com.example.jiangchuanfa.projecttraining.controller.fragment.goodsinfofragment;

import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;
import com.example.jiangchuanfa.projecttraining.config.Api;
import com.example.jiangchuanfa.projecttraining.controller.adapter.GuessYouLikeAdapter;
import com.example.jiangchuanfa.projecttraining.modle.bean.NewBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

import static com.example.jiangchuanfa.projecttraining.config.Api.SHOP_ALL_URL;

/**
 * Created by crest on 2017/7/12.
 */

public class GuessYouLikeFragment extends BaseFragment {


    private static final String TAG = GuessYouLikeFragment.class.getSimpleName();

    @BindView(R.id.refreshable_recycle_view)
    XRecyclerView refreshableRecycleView;
    @BindView(R.id.ll_guess_you_like)
    LinearLayout llGuessYouLike;
    Unbinder unbinder;
    private GuessYouLikeAdapter adapter;
    private String url;

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_guess_you_like, null);

//        refreshableRecycleView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        unbinder = ButterKnife.bind(this, view);
        url = Api.SHOP_ALL_URL[(int) (Math.random() * 19)];
        getMyDataFromNet(url);
        Log.e(TAG, "在这里获取随机数准备为图片加载数据 " + SHOP_ALL_URL[(int) (Math.random() * 19)]);
        return view;
    }

    private void getMyDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "请求失败" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e(TAG, "onResponse: 请求成功，" + response);
                processMyData(response);
            }
        });
    }
    /*goods_image : "http://imgs-qn.iliangcang.com/ware/goods/big/2/257/257295.jpg?t=1492079721"
    goods_name : "小花曲奇礼盒 | 法国日本进口品质原料 舒软香脆 入口酥化【多款可选】"
    like_count : "785"
    price : "165.00"
    discount_price : "98.00"
    promotion_imgurl : "http://imgs-qn.iliangcang.com/ware/promotion/icon/19.jpg"
    discount_price : "98.00"
    brand_name : "AKOKO"*/

    private void processMyData(String json) {
        //创建目标对象
        NewBean newBean = new NewBean();
        try {
            //将json转换成json对象
            JSONObject jsonObject = new JSONObject(json);
            //从刚转化的json对象中获取名字为data的json对象1
            JSONObject jsonObject1 = jsonObject.optJSONObject("data");
            //创建NewBean.DataBean类型的对象用于存放数据
            NewBean.DataBean dataBean = new NewBean.DataBean();
            //将带有数据的dataBean放入到目标对象中
            newBean.setData(dataBean);
            JSONArray jsonArray = jsonObject1.optJSONArray("items");
            List<NewBean.DataBean.ItemsBean> items = new ArrayList<>();
            dataBean.setItems(items);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = (JSONObject)jsonArray.get(i);
                if (jsonObject2 != null) {
                    NewBean.DataBean.ItemsBean itemsBean = new NewBean.DataBean.ItemsBean();

                    String goods_name = jsonObject2.optString("goods_name");
                    itemsBean.setGoods_name(goods_name);

                    String goods_image = jsonObject2.optString("goods_image");
                    itemsBean.setGoods_image(goods_image);

                    String like_count = jsonObject2.optString("like_count");
                    itemsBean.setLike_count(like_count);

                    String price = jsonObject2.optString("price");
                    itemsBean.setPrice(price);

                    String discount_price = jsonObject2.optString("discount_price");
                    itemsBean.setDiscount_price(discount_price);

                    String promotion_imgurl = jsonObject2.optString("promotion_imgurl");
                    itemsBean.setPromotion_imgurl(promotion_imgurl);

                    String goods_id = jsonObject2.optString("goods_id");
                    itemsBean.setGoods_id(goods_id);

                    JSONObject brand_info = jsonObject2.optJSONObject("brand_info");

                    NewBean.DataBean.ItemsBean.BrandInfoBean brandInfoBean = new NewBean.DataBean.ItemsBean.BrandInfoBean();
                    String brand_name = brand_info.optString("brand_name");
                    brandInfoBean.setBrand_name(brand_name);
                    itemsBean.setBrand_info(brandInfoBean);
                    items.add(itemsBean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        NewBean newBean = new Gson().fromJson(json, NewBean.class);
        refreshableRecycleView.setLayoutManager(new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false));
        adapter = new GuessYouLikeAdapter(context, newBean.getData().getItems());
        refreshableRecycleView.setAdapter(adapter);
//        Log.e(TAG, "打个看看解析结果："+newBean.getData().getItems().get(0));
//
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
