package com.cp.ye0ye.rxjavademo.module.jsoupfragment;

import com.cp.ye0ye.rxjavademo.entity.HireResult;
import com.cp.ye0ye.rxjavademo.network.NetWork;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 7/7/2017.
 */
public class HTWPresenter implements HTWContract.Presenter {
    private CompositeSubscription mSubscriptions;
    private HTWContract.View mView;
    private int mPage;
    private String BASE_URL = "http://xyzp.haitou.cc";
    private String mCategory;


    private HireResult.ResultBean.DataBean hireResult;

    private List<HireResult.ResultBean.DataBean> listData = new ArrayList<>();

    public HTWPresenter(HTWContract.View view, String category) {
        mCategory = category;
        mView = view;
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void getCategoryItems(final boolean isRefresh) {
        if (isRefresh) {
            mView.showSwipeLoading();
            mPage = 1;
        } else {
            mPage += 1;
        }
        Subscription subscription = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(final Subscriber<? super Integer> subscriber) {
                if (mCategory.equals("海淘网")) {
                    listData = NetWork.getHireData(mPage);
                }
                if (mCategory.equals("实习")) {
                    listData = NetWork.getInter(mPage);
                }
                subscriber.onNext(1);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(final Throwable e) {
                System.out.println("onError::失败和失败原因：" + e.toString());
            }

            @Override
            public void onNext(final Integer integer) {
                if (isRefresh) {
                    mView.setCategoryItem(listData);
                    mView.hideSwipeLoading();
                    mView.setLoading();
                } else {
                    mView.addCategoryItem(listData);
                }
            }
        });
        mSubscriptions.add(subscription);


    }

    @Override
    public void subscribe() {
        getCategoryItems(true);
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
        listData = null;
    }
}
