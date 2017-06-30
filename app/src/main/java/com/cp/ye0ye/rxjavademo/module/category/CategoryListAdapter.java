package com.cp.ye0ye.rxjavademo.module.category;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.cp.ye0ye.rxjavademo.ConfigManage;
import com.cp.ye0ye.rxjavademo.GlobalConfig;
import com.cp.ye0ye.rxjavademo.R;
import com.cp.ye0ye.rxjavademo.base.adapter.CommonAdapter4RecyclerView;
import com.cp.ye0ye.rxjavademo.base.adapter.CommonHolder4RecyclerView;
import com.cp.ye0ye.rxjavademo.base.adapter.ListenerWithPosition;
import com.cp.ye0ye.rxjavademo.entity.CategoryResult;
import com.cp.ye0ye.rxjavademo.utils.DateUtil;
import com.squareup.picasso.Picasso;

import es.dmoral.toasty.Toasty;

/**
 * Created by Administrator on 6/29/2017.
 */
public class CategoryListAdapter extends CommonAdapter4RecyclerView<CategoryResult.ResultsBean> implements ListenerWithPosition.OnClickWithPositionListener<CommonHolder4RecyclerView> {

    public CategoryListAdapter(Context context) {
        super(context, null, R.layout.item);
    }

    @Override
    public void convert(CommonHolder4RecyclerView holder, CategoryResult.ResultsBean androidResult) {
        if (androidResult != null) {
            AppCompatImageView imageView = holder.getView(R.id.iv_item_img);
            if (ConfigManage.INSTANCE.isListShowImg()) { // 列表显示图片
                imageView.setVisibility(View.VISIBLE);
                String quality = "";
                if (androidResult.images != null && androidResult.images.size() > 0) {
                    switch (ConfigManage.INSTANCE.getThumbnailQuality()) {
                        case 0: // 原图
                            quality = "?imageView2/0/w/400";
                            break;
                        case 1: // 默认
                            quality = "?imageView2/0/w/280";
                            break;
                        case 2: // 省流
                            quality = "?imageView2/0/w/190";
                            break;
                    }
                    imageView.setVisibility(View.VISIBLE);
//                    Picasso.with(mContext).setIndicatorsEnabled(true);//显示指示器
                    Picasso.with(mContext)
                            .load(androidResult.images.get(0) + quality)
                            .placeholder(R.mipmap.image_default)
                            .tag(GlobalConfig.PICASSO_TAG_THUMBNAILS_CATEGORY_LIST_ITEM)
                            .centerCrop()
                            .fit()
                            .config(Bitmap.Config.RGB_565)
                            .into(imageView);
                } else { // 图片 URL 不存在
                    imageView.setVisibility(View.GONE);
                }
            } else { // 列表不显示图片
                imageView.setVisibility(View.GONE);
            }
            holder.setTextViewText(R.id.tv_item_title, androidResult.desc == null ? "unknown" : androidResult.desc);
            holder.setTextViewText(R.id.tv_item_publisher, androidResult.who == null ? "unknown" : androidResult.who);
            holder.setTextViewText(R.id.tv_item_time, DateUtil.dateFormat(androidResult.publishedAt));
            holder.setOnClickListener(this, R.id.ll_item);
        }
    }

    @Override
    public void onClick(View v, int position, CommonHolder4RecyclerView holder) {
        if(mData == null || mData.get(position)==null){
            Toasty.error(mContext,"数据异常").show();
        }
    }
}
