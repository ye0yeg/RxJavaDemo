package com.cp.ye0ye.rxjavademo.module.search;

import android.graphics.Color;
import android.text.TextUtils;

import com.cp.ye0ye.rxjavademo.GlobalConfig;
import com.cp.ye0ye.rxjavademo.ThemeManage;
import com.cp.ye0ye.rxjavademo.entity.SearchResult;
import com.cp.ye0ye.rxjavademo.network.NetWork;
import com.cp.ye0ye.rxjavademo.utils.EmojiFilter;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ye0ye on 2017/7/3.
 */
public class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View mView;
    private CompositeSubscription mSubscriptions;
    private int mPage = 1;

    public SearchPresenter(SearchContract.View view) {
        mView = view;
    }

    @Override
    public void subscribe() {
        mSubscriptions = new CompositeSubscription();
        mView.setToolbarBackgroundColor(ThemeManage.INSTANCE.getColorPrimary());
        mView.setEditTextCursorColor(Color.WHITE);
        mView.hideEditClear();
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void search(String searchText, final boolean isLoadMore) {
        String searchTextNoEmoji = EmojiFilter.filterEmoji(searchText);
        if (TextUtils.isEmpty(searchTextNoEmoji)) {
            mView.startEmojiRain();
            //下雨
            return;
        }
        mView.showSearchResult();
        //存下该关键词
        saveOneHistory(searchText);
        if (!isLoadMore) {
            mView.showSwipLoading();
            mPage = 1;
        } else {
            mPage += 1;
        }
        Subscription subscription = NetWork.getGankApi()
                .getSearchResult(searchText, GlobalConfig.PAGE_SIZE_CATEGORY, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showSearchfail("搜索出错了。");
                        mView.hideSwipLoading();
                    }

                    @Override
                    public void onNext(SearchResult searchResult) {
                        if (!isLoadMore) {
                            if (searchResult == null || searchResult.count == 0) {
                                mView.showTip("没有搜索到结果");
                                mView.hideSwipLoading();
                                mView.showSearchHistory();
                                mView.setEmpty();
                                return;
                            }
                            mView.setSearchItems(searchResult);
                            mView.showSearchResult();
                            mView.setLoading();
                        } else {
                            mView.addSearchItems(searchResult);
                            mView.showSearchResult();
                        }
                        boolean isLastPage = searchResult.count < GlobalConfig.PAGE_SIZE_CATEGORY;
                        if (isLastPage) {
                            mView.setLoadMoreIsLastPage();
                        }

                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void queryHistory() {

    }

    private void saveOneHistory(String searchText) {

    }



    @Override
    public void deleteAllHistory() {

    }
}
