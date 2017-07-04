package com.cp.ye0ye.rxjavademo.module.search;

import android.content.Context;
import android.view.View;

import com.cp.ye0ye.rxjavademo.R;
import com.cp.ye0ye.rxjavademo.base.adapter.CommonAdapter4RecyclerView;
import com.cp.ye0ye.rxjavademo.base.adapter.CommonHolder4RecyclerView;
import com.cp.ye0ye.rxjavademo.base.adapter.ListenerWithPosition;
import com.cp.ye0ye.rxjavademo.entity.History;

import java.util.List;

/**
 * Created by Administrator on 7/4/2017.
 */
public class HistoryListAdapter extends CommonAdapter4RecyclerView<History> implements ListenerWithPosition.OnClickWithPositionListener<CommonHolder4RecyclerView> {
    public HistoryListAdapter(Context context) {
        super(context, null, R.layout.item_history);
    }

    @Override
    public void convert(CommonHolder4RecyclerView holder, History history) {
        if (history != null) {
            holder.setTextViewText(R.id.tv_item_content_history, history.getContent());
            holder.setOnClickListener(this, R.id.tv_item_content_history);
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(History history);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;

    @Override
    public void onClick(View v, int position, CommonHolder4RecyclerView holder) {
        if (mData.get(position) == null) {
            return;
        }
        if (mOnItemClickListener != null) {
            mOnItemClickListener.OnItemClick(mData.get(position));
        }
    }
}
