package com.cp.ye0ye.rxjavademo.module.jsoupfragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.cp.ye0ye.rxjavademo.R;
import com.cp.ye0ye.rxjavademo.base.adapter.CommonAdapter4RecyclerView;
import com.cp.ye0ye.rxjavademo.base.adapter.CommonHolder4RecyclerView;
import com.cp.ye0ye.rxjavademo.base.adapter.ListenerWithPosition;
import com.cp.ye0ye.rxjavademo.entity.HireResult;
import com.cp.ye0ye.rxjavademo.module.webview.WebViewActivity;

import es.dmoral.toasty.Toasty;

/**
 * Created by Administrator on 7/7/2017.
 */
public class HireCatchAdapter extends CommonAdapter4RecyclerView<HireResult.ResultBean.DataBean> implements ListenerWithPosition.OnClickWithPositionListener<CommonHolder4RecyclerView> {


    public HireCatchAdapter(Context context) {
        super(context, null, R.layout.item_hire);
    }


    @Override
    public void convert(CommonHolder4RecyclerView holder, HireResult.ResultBean.DataBean hireResult) {
        //设置信息
        holder.setTextViewText(R.id.tv_item_title, hireResult.getTitle() == null ? "unknown" : hireResult.getTitle());
        holder.setTextViewText(R.id.tv_item_publisher, hireResult.getCity() == null ? "unknown" : hireResult.getCity());
        holder.setTextViewText(R.id.tv_item_time, hireResult.getHireTime() == null ? "unknown" : hireResult.getHireTime());
        holder.setOnClickListener(this, R.id.ll_item);
    }

    @Override
    public void onClick(View v, int position, CommonHolder4RecyclerView holder) {
        if (mData == null || mData.get(position) == null) {
            Toasty.error(mContext, "数据异常").show();
        }
        Intent intent = new Intent(mContext, WebViewActivity.class);
        intent.putExtra(WebViewActivity.GANK_TITLE, mData.get(position).getTitle());
        intent.putExtra(WebViewActivity.GANK_URL, mData.get(position).getUrl());
        mContext.startActivity(intent);
    }

}
