package com.cp.ye0ye.rxjavademo.module.jsoupfragment;

import com.cp.ye0ye.rxjavademo.entity.HireResult;
import com.cp.ye0ye.rxjavademo.network.JsoupNewWork;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 7/7/2017.
 */
public class HTWPresenter implements HTWContract.Presenter {

    private HTWContract.View mView;
    private int mPage;

    private List<HireResult> listData;

    public HTWPresenter(HTWContract.View view) {
        mView = view;
    }

    @Override
    public void getCategoryItems(boolean isRefresh) {
        if (isRefresh) {
            mView.showSwipeLoading();
            mPage = 1;
        } else {
            mPage += 1;
        }
        Observable.create(new Observable.OnSubscribe<HireResult>() {
            @Override
            public void call(final Subscriber<? super HireResult> subscriber) {

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HireResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(final Throwable e) {

                    }

                    @Override
                    public void onNext(HireResult hireResult) {
                        try {
                            Document document = JsoupNewWork.getConnect("http://xyzp.haitou.cc/").get();
                            Elements elements = document.select("tbody tr");
                            for (Element element :
                                    elements) {
                                hireResult = new HireResult();
                                String name = element.getElementsByClass("text-success company").text();
                                String time = element.getElementsByClass("cxxt-time").text();
                                String citi = element.getElementsByClass("text-ellipsis").text();
                                hireResult.setTitle(name);
                                hireResult.setHireTime(time);
                                hireResult.setCity(citi);
                                listData.add(hireResult);
                                mView.addCategoryItem(listData);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });


    }

    @Override
    public void subscribe() {
        getCategoryItems(true);
    }

    @Override
    public void unsubscribe() {

    }
}
