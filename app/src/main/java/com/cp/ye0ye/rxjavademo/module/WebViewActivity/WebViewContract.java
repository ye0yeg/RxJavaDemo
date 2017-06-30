package com.cp.ye0ye.rxjavademo.module.WebViewActivity;

import com.cp.ye0ye.rxjavademo.base.BasePresenter;
import com.cp.ye0ye.rxjavademo.base.BaseView;

/**
 * Created by Administrator on 6/30/2017.
 */
public interface WebViewContract {

    interface View extends BaseView{

        /**
        * 设置标题
        * */
        void setGankTitle(String title);

        /**
         * 加载URL中
         * */
        void loadGankURL(String url);
        /**
         * 设置BackgroundColor
         * */
        void setToolbarBackgroundColor(int color);

        /**
         * 获得Load的Url
         * */
        String getLoadUrl();

    }

    interface Presenter extends BasePresenter{

    }
}
