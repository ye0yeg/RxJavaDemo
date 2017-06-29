package com.cp.ye0ye.rxjavademo.module.category;

import com.cp.ye0ye.rxjavademo.GlobalConfig;
import com.cp.ye0ye.rxjavademo.entity.CategoryResult;
import com.cp.ye0ye.rxjavademo.network.NetWork;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 6/29/2017.
 */
public class CategoryPresenter implements CategoryContract.Presenter {
    private CategoryContract.View mView;
    private int mPage = 1;

    private CompositeSubscription mSubscriptions;

    public CategoryPresenter(CategoryContract.View view) {
        mView = view;
        mSubscriptions = new CompositeSubscription();

    }


    @Override
    public void subscribe() {
        getCategoryItems(true);
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();

    }

    @Override
    public void getCategoryItems(final boolean isRefresh) {
        if (isRefresh) {
            mView.showSwipeLoading();
            mPage = 1;
        } else {
            mPage += 1;
        }
        Subscription subscription = NetWork.getGankApi()
                .getCategoryDate(mView.getCategoryName(),
                        GlobalConfig.PAGE_SIZE_CATEGORY, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideSwipeLoading();
                        mView.getCategoryItemFail(mView.getCategoryName() + "列表获取失败");
                    }

                    @Override
                    public void onNext(CategoryResult categoryResult) {
                        if (isRefresh) {
                            mView.setCategoryItems(categoryResult);
                            mView.hideSwipeLoading();
                            mView.setLoading();
                        } else {
                            mView.addCategoryItem(categoryResult);
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }
}
