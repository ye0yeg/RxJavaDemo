package com.cp.ye0ye.rxjavademo.module.bigimg;

import com.cp.ye0ye.rxjavademo.base.BasePresenter;
import com.cp.ye0ye.rxjavademo.base.BaseView;

/**
 * Created by Administrator on 7/4/2017.
 */
public interface BigimgContract {
    interface View extends BaseView {

        /**
        * 设置标题
        * */
        void setMeiziTitle(String title);


        /**
         * 设置图片
         * */
        void loadMeizuImg(String url);

        /**
         * 设置背景颜色
         * */
        void setToolbarBackgroundColor(int color);

        /**
         * 设置加载颜色
         * */
        void setLoadingColor(int color);

        /**
         * 设置加载
         * */
        void showLoading();

        /**
         * 获得图片
         * */
        String getMeiziImg();

        /**
         * 获得标题
         * */
        String getMeiziTitle();

    }

    interface Presenter extends BasePresenter {
        /**
         * 保存图片
         * */
        void saveImg(String url);
    }
}
