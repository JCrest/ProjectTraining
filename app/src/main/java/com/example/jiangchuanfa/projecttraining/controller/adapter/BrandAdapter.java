package com.example.jiangchuanfa.projecttraining.controller.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.modle.bean.BrandBean;
import com.example.jiangchuanfa.projecttraining.utils.BitmapCacheUtils;
import com.example.jiangchuanfa.projecttraining.utils.NetCacheUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by crest on 2017/7/7.
 */

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {

    private final Context context;
    private final List<BrandBean.DataBean.ItemsBean> datas;
    private final RecyclerView recyclerview;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NetCacheUtils.SECUSS://图片请求成功
                    //位置
                    int position = msg.arg1;
                    Bitmap bitmap = (Bitmap) msg.obj;
                    if (recyclerview.isShown()) {
                        ImageView ivIcon = (ImageView) recyclerview.findViewWithTag(position);
                        if (ivIcon != null && bitmap != null) {
                            Log.e("TAG", "网络缓存图片显示成功" + position);
                            ivIcon.setImageBitmap(bitmap);
                        }
                    }
                    break;

                case NetCacheUtils.FAIL://图片请求失败
                    position = msg.arg1;
                    Log.e("TAG", "网络缓存失败" + position);
                    break;
            }
        }
    };
    private BitmapCacheUtils bitmapCacheUtils;
    private DisplayImageOptions options;

    public BrandAdapter(Context context, List<BrandBean.DataBean.ItemsBean> items, RecyclerView recyclerview) {
        this.context=context;
        this.datas = items;
        this.recyclerview = recyclerview;
        bitmapCacheUtils = new BitmapCacheUtils(handler);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.news_pic_default)
                .showImageForEmptyUri(R.drawable.news_pic_default)
                .showImageOnFail(R.drawable.news_pic_default)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                //设置矩形圆角图片
//                .displayer(new RoundedBitmapDisplayer(15))
                .build();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.item_classify_fragment, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//1.根据位置得到数据
        BrandBean.DataBean.ItemsBean itemsBean = datas.get(position);

        ImageLoader.getInstance().displayImage(itemsBean.getBrand_logo(), holder.ivIcon, options);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivIcon;

        public ViewHolder(View view) {
            super(view);
            ivIcon = view.findViewById(R.id.iv_icon);
            ivIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "在这还崩！", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
