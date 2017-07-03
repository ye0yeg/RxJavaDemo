package com.cp.ye0ye.rxjavademo.module.favorite;

import com.cp.ye0ye.rxjavademo.GlobalConfig;
import com.cp.ye0ye.rxjavademo.ThemeManage;
import com.cp.ye0ye.rxjavademo.entity.Favorite;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 7/3/2017.
 */
public class FavoritePresenter implements FavoriteContract.Presenter {

    private FavoriteContract.View mView;
    private int mPage = 0;


    public FavoritePresenter(final FavoriteContract.View view) {
        mView = view;
    }

    @Override
    public void subscribe() {
        mView.setToolbarBackgroundColor(ThemeManage.INSTANCE.getColorPrimary());
        getFavoriteItems(true);

    }

    @Override
    public void getFavoriteItems(final boolean isRefresh) {
        if (isRefresh) {
            mPage = 0;
        } else {
            mPage += 1;
        }
        List<Favorite> favoriteList = DataSupport
                .limit(GlobalConfig.PAGE_SIZE_FAVORITE)
                .offset(GlobalConfig.PAGE_SIZE_FAVORITE * mPage)
                .order("createTime desc")
                .find(Favorite.class);
        if (isRefresh) {
            mView.setFavoriteItems(favoriteList);
            mView.setLoading();
            if (favoriteList == null || favoriteList.size() < 1) {
                mView.setEmpty();
                return;
            }
        } else {
            mView.addFavoriteItems(favoriteList);
        }
        boolean isLastPage = favoriteList.size() < GlobalConfig.PAGE_SIZE_FAVORITE;
        if (isLastPage) {
            mView.setLoadMoreIsLastPage();
        }
    }


    @Override
    public void unsubscribe() {

    }
}
