package com.cp.ye0ye.rxjavademo.module.jsoupfragment;

import com.cp.ye0ye.rxjavademo.entity.HireResult;
import com.cp.ye0ye.rxjavademo.network.JsoupNewWork;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
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

    private HireResult.ResultBean.DataBean hireResult;

    private List<HireResult.ResultBean.DataBean> listData = new ArrayList<>();

    public HTWPresenter(HTWContract.View view) {
        mView = view;
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void getCategoryItems(boolean isRefresh) {
        if (isRefresh) {
            mView.showSwipeLoading();
            mPage = 1;
        } else {
            mPage += 1;
        }
        Subscription subscription = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(final Subscriber<? super Integer> subscriber) {
                try {
                    if (listData != null){
                        listData.clear();
                    }
                    Document document = JsoupNewWork.getConnect("http://xyzp.haitou.cc/").get();
                    Elements elements = document.select("tbody tr");
                    for (Element element :
                            elements) {
                        hireResult = new HireResult.ResultBean.DataBean();
                        String name = element.getElementsByClass("text-success company").text();
                        String time = element.getElementsByClass("cxxt-time").text();
                        String citi = element.getElementsByClass("text-ellipsis").text();

                        System.out.println(name + time + citi);

                        hireResult.setTitle(name);
                        hireResult.setHireTime(time);
                        hireResult.setCity(citi);

                        listData.add(hireResult);
                        System.out.println(listData.size());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("IOException::失败和失败原因：" + e.toString());
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
                mView.setCategoryItem(listData);
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
