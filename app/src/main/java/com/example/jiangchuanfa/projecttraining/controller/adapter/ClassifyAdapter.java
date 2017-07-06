package com.example.jiangchuanfa.projecttraining.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.modle.bean.ClassifyBean;
import com.example.jiangchuanfa.projecttraining.utils.CornersTransform;

import java.util.List;

/**
 * Created by crest on 2017/7/7.
 */

public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.ViewHolder> {


    private final Context context;
    private final List<ClassifyBean.DataBean.ItemsBean> datas;
    private final RecyclerView recyclerview;


    public ClassifyAdapter(Context context,
                           List<ClassifyBean.DataBean.ItemsBean> items,
                           RecyclerView recyclerview) {
        this.context = context;
        this.datas = items;
        this.recyclerview = recyclerview;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.item_classify_fragment, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //1.根据位置得到数据
        ClassifyBean.DataBean.ItemsBean itemsBean = datas.get(position);
//        ImageView ivIcon = (ImageView) recyclerview.findViewWithTag(position);
        Glide.with(context).load(itemsBean.getCover_new_img())

                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new CornersTransform(context,15))
                .placeholder(R.color.white)
                .error(R.drawable.news_pic_default)
                .into(holder.ivIcon);


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
//            if(ivIcon == null) {
//                ivIcon = ButterKnife.findById((Activity) context, R.id.iv_icon);
//            }

//            //设置点击事件
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(context, PicassoSampleActivity.class);
//                    intent.putExtra("url", Constants.BASE_URL + datas.get(getLayoutPosition()).getListimage());
//                    context.startActivity(intent);
//                }
//            });
        }
    }
}
