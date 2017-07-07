package com.cp.ye0ye.rxjavademo.module.favorite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.cp.ye0ye.rxjavademo.R;
import com.cp.ye0ye.rxjavademo.base.adapter.CommonAdapter4RecyclerView;
import com.cp.ye0ye.rxjavademo.base.adapter.CommonHolder4RecyclerView;
import com.cp.ye0ye.rxjavademo.base.adapter.ListenerWithPosition;
import com.cp.ye0ye.rxjavademo.entity.Favorite;
import com.cp.ye0ye.rxjavademo.module.webview.WebViewActivity;
import com.cp.ye0ye.rxjavademo.utils.DateUtil;

import es.dmoral.toasty.Toasty;

/**
 * Created by Administrator on 7/3/2017.
 */
public class FavoriteListAdapter extends CommonAdapter4RecyclerView<Favorite> implements ListenerWithPosition.OnClickWithPositionListener<CommonHolder4RecyclerView> {
    public FavoriteListAdapter(Context context) {
        super(context, null, R.layout.item_favorite);
    }


    @Override
    public void convert(final CommonHolder4RecyclerView holder, final Favorite favorite) {
        if (favorite != null) {
            holder.setTextViewText(R.id.tv_item_title_favorite, favorite.getTitle() == null ? "unknown" : favorite.getTitle());
            holder.setTextViewText(R.id.tv_item_type_favorite, favorite.getType() == null ? "unknown" : favorite.getType());
            holder.setTextViewText(R.id.tv_item_publisher_favorite, favorite.getAuthor() == null ? "unknown" : favorite.getAuthor());
            holder.setTextViewText(R.id.tv_item_time_favorite, DateUtil.dateFormat(favorite.getData()));
            holder.setOnClickListener(this, R.id.ll_item_favorite);
        }
    }

    @Override
    public void onClick(View v, int position, CommonHolder4RecyclerView holder) {
        // 通过 notifyRemoveItem 方法移除 item 后，不能使用这个 position
        position = holder.getAdapterPosition();
        if (mData == null || mData.get(position) == null) {
            Toasty.error(mContext, "数据异常").show();
            return;
        }
        Intent intent = new Intent();
        intent.setClass(mContext, WebViewActivity.class);
        intent.putExtra(WebViewActivity.GANK_TITLE, mData.get(position).getTitle());
        intent.putExtra(WebViewActivity.GANK_URL, mData.get(position).getUrl());
        intent.putExtra(WebViewActivity.FAVORITE_POSITION, position);
        intent.putExtra(WebViewActivity.FAVORITE_DATA, mData.get(position));
        ((Activity) mContext).startActivityForResult(intent, FavoriteActivity.REQUEST_CODE_FAVORITE);
    }
}
