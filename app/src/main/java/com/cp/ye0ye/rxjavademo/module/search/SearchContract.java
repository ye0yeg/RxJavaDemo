package com.cp.ye0ye.rxjavademo.module.search;

import com.cp.ye0ye.rxjavademo.base.BasePresenter;
import com.cp.ye0ye.rxjavademo.base.BaseView;
import com.cp.ye0ye.rxjavademo.entity.History;
import com.cp.ye0ye.rxjavademo.entity.SearchResult;

import java.util.List;

/**
 * Created by ye0ye on 2017/7/3.
 */
public class SearchContract {
    interface View extends BaseView {
        void setToolbarBackgroundColor(int color);

        void setEditTextCursorColor(int cursorColor);

        void showEditClear();

        void hideEditClear();

        void showSearchfail(String failMsg);

        void setSearchItems(SearchResult searchItems);

        void addSearchItems(SearchResult searchResult);

        void showSwipLoading();

        void hideSwipLoading();

        void showTip(String msg);

        void setLoadMoreIsLastPage();

        void setEmpty();

        void setLoading();

        void showSearchResult();

        void showSearchHistory();

        void setHistory(List<History> history);

        void startEmojiRain();

        void stopEmojiRain();

    }

    interface Presenter extends BasePresenter {
        void search(String searchText, boolean isLoadMore);

        void queryHistory();

        void deleteAllHistory();
    }
}
