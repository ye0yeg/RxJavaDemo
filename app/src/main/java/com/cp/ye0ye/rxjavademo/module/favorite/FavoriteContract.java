package com.cp.ye0ye.rxjavademo.module.favorite;

import com.cp.ye0ye.rxjavademo.base.BasePresenter;
import com.cp.ye0ye.rxjavademo.base.BaseView;
import com.cp.ye0ye.rxjavademo.entity.Favorite;

import java.util.List;

/**
 * Created by Administrator on 7/3/2017.
 */
public class FavoriteContract {
    interface View extends BaseView {
        /**
         * 设置tb颜色
         */
        void setToolbarBackgroundColor(int color);

        /**
         * 增加favorite的条目
         */
        void addFavoriteItems(List<Favorite> favorites);

        /**
         * 设置favorite条目
         */
        void setFavoriteItems(List<Favorite> favorites);

        /**
         * 设置加载
         */
        void setLoading();

        /**
         * 没有条目的时候设置为空
         */
        void setEmpty();

        /**
         * 已经是最后一页了
         */
        void setLoadMoreIsLastPage();
    }

    interface Presenter extends BasePresenter {

        /**
         * 刷新
         */
        void getFavoriteItems(boolean isRefresh);
    }
}
