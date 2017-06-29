package com.cp.ye0ye.rxjavademo.base.adapter;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ye0ye on 2017/6/29.
 */

public class CommonHolder4RecyclerView extends RecyclerView.ViewHolder {
    public View mView;
    public int position;
    private SparseArray<View> mViews;

    public CommonHolder4RecyclerView(View itemView) {
        super(itemView);
        this.mView = itemView;
        this.mViews = new SparseArray<>();
    }

    /**
     * 获得item上的控件
     *
     * @param viewId 控件的ID
     * @return 对应的控件
     */
    public <T extends View> T getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public CommonHolder4RecyclerView setTextViewText(int textViewText, String text) {
        TextView tv = getView(textViewText);
        if (!TextUtils.isEmpty(text)) {
            tv.setText(text);
        } else {
            tv.setText("  ");
        }
        return this;
    }

    public CommonHolder4RecyclerView setOnClickListener(ListenerWithPosition.OnClickWithPositionListener clickListener,
                                                        @IdRes int... viewIds) {
        ListenerWithPosition listenerWithPosition = new ListenerWithPosition(position, this);
        listenerWithPosition.setOnClickListener(clickListener);
        for (int id : viewIds) {
            View v = getView(id);
            v.setOnClickListener(listenerWithPosition);
        }
        return this;
    }


}
