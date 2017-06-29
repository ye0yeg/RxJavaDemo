package com.cp.ye0ye.rxjavademo.module.category;

import com.cp.ye0ye.rxjavademo.base.BasePresenter;
import com.cp.ye0ye.rxjavademo.base.BaseView;
import com.cp.ye0ye.rxjavademo.entity.CategoryResult;

/**
 * Created by Administrator on 6/29/2017.
 */
public interface CategoryContract {

    interface View extends BaseView {
        /*
        * 设置Item
        * */
        void setCategoryItems(CategoryResult categoryResult);

        /*
        * 添加Item
        * */
        void addCategoryItem(CategoryResult categoryResult);

        /*
        * 获取条目失败
        * */
        void getCategoryItemFail(String failMessage);

        /*
        * 获得Item的名字
        * */
        String getCategoryName();

        /*
        * 显示下拉刷新
        * */
        void showSwipeLoading();

        /*
        * 隐藏下拉刷新
        * */
        void hideSwipeLoading();

        /*
        * 设置加载
        * */
        void setLoading();

    }

    interface Presenter extends BasePresenter {
        /*
        * 获取Item
        * */
        void getCategoryItems(boolean isRefresh);
    }
}
