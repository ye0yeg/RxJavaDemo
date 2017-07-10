package com.cp.ye0ye.rxjavademo.module.jsoupfragment;

import android.content.Context;
import android.view.View;

import com.cp.ye0ye.rxjavademo.R;
import com.cp.ye0ye.rxjavademo.base.adapter.CommonAdapter4RecyclerView;
import com.cp.ye0ye.rxjavademo.base.adapter.CommonHolder4RecyclerView;
import com.cp.ye0ye.rxjavademo.base.adapter.ListenerWithPosition;
import com.cp.ye0ye.rxjavademo.entity.HireResult;

/**
 * Created by Administrator on 7/7/2017.
 */
public class HireCatchAdapter extends CommonAdapter4RecyclerView<HireResult.ResultBean.DataBean> implements ListenerWithPosition.OnClickWithPositionListener<CommonHolder4RecyclerView> {


    public HireCatchAdapter(Context context) {
        super(context, null, R.layout.item);
    }


    @Override
    public void convert(CommonHolder4RecyclerView holder, HireResult.ResultBean.DataBean hireResult) {
        //设置信息
        holder.setTextViewText(R.id.tv_item_title, hireResult.getTitle() == null ? "unknown" : hireResult.getTitle());
        holder.setTextViewText(R.id.tv_item_publisher, hireResult.getCity() == null ? "unknown" : hireResult.getCity());
        holder.setTextViewText(R.id.tv_item_time, hireResult.getHireTime() == null ? "unknown" : hireResult.getHireTime());
    }

    @Override
    public void onClick(final View v, final int position, final CommonHolder4RecyclerView holder) {

    }

}
