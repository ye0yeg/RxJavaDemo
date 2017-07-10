package com.cp.ye0ye.rxjavademo.module.jsoupfragment;

import com.cp.ye0ye.rxjavademo.base.BasePresenter;
import com.cp.ye0ye.rxjavademo.base.BaseView;
import com.cp.ye0ye.rxjavademo.entity.HireResult;

import java.util.List;

/**
 * Created by Administrator on 7/7/2017.
 */
public interface HTWContract {
    interface View extends BaseView {

        void setCategoryItem(List<HireResult.ResultBean.DataBean> hireResult);

        void addCategoryItem(List<HireResult.ResultBean.DataBean> hireResult);

        void getCategoryItemFail(String failMessage);

        String itemName();

        void showSwipeLoading();

        void hideSwipeLoading();

        void setLoading();

    }

    interface Presenter extends BasePresenter {

        void getCategoryItems(boolean isRefresh);
    }

}
