package com.cp.ye0ye.rxjavademo.module.WebViewActivity;

import com.cp.ye0ye.rxjavademo.base.BasePresenter;
import com.cp.ye0ye.rxjavademo.base.BaseView;
import com.cp.ye0ye.rxjavademo.entity.Favorite;

/**
 * Created by Administrator on 6/30/2017.
 */
public interface WebViewContract {

    interface View extends BaseView {

        /**
         * 设置标题
         */
        void setGankTitle(String title);

        /**
         * 加载URL中
         */
        void loadGankURL(String url);

        /**
         * 设置BackgroundColor
         */
        void setToolbarBackgroundColor(int color);

        /**
         * 获得Load的Url
         */
        String getLoadUrl();

        /**
         * 获得gan的抬头
         */
        String getGanTitle();


        /**
         * 设置fab的颜色
         */
        void setFabButtonColor(int color);

        /**
         * private String title;
         * private String type;
         * private String author;
         * private String data;
         * private String url;
         * private String gankID;
         * private long createtime;
         */
        Favorite getFavoriteData();

        /**
         * 显示是否喜欢
         */
        void setFavoriteState(boolean isFavorite);

        /**
         * 隐藏按钮
         */
        void hideFavoriteFab();

        /**
         * 显示tip
         */
        void showTip();
    }

    interface Presenter extends BasePresenter {

        String getGankUrl();

        void favoriteGank();

    }
}
