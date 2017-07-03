package com.cp.ye0ye.rxjavademo.module.WebViewActivity;

import com.cp.ye0ye.rxjavademo.ThemeManage;
import com.cp.ye0ye.rxjavademo.entity.Favorite;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 7/3/2017.
 */
public class WebViewPresenter implements WebViewContract.Presenter {

    private WebViewContract.View mWebView;
    private String mGankUrl;
    private boolean mIsFavorite;
    private Favorite mFavoriteData;

    public WebViewPresenter(WebViewContract.View view) {
        mWebView = view;
    }


    @Override
    public String getGankUrl() {
        return mGankUrl;
    }

    @Override
    public void favoriteGank() {
        if (mIsFavorite) {
            //改变状态
            unFavorite();
        } else {
            favorite();
        }
    }

    private void favorite() {
        mFavoriteData.setCreatetime(System.currentTimeMillis());
        mIsFavorite = mFavoriteData.save();
        mWebView.setFavoriteState(mIsFavorite);
        if (!mIsFavorite) {
            mWebView.showTip("收藏失败,请重试");
        }
    }

    private void unFavorite() {
        int cows = DataSupport.deleteAll(Favorite.class, "gankID = ?", mFavoriteData.getGankID());
        //不调用这句保存，会保存失败，并且返回true
        mFavoriteData.clearSavedState();
        mIsFavorite = cows < 1;
        mWebView.setFavoriteState(mIsFavorite);
        if (mIsFavorite) {
            mWebView.showTip("Fail to cancel Favorite!Try Again");
        }
    }

    @Override
    public void subscribe() {
        mWebView.setToolbarBackgroundColor(ThemeManage.INSTANCE.getColorPrimary());
        mWebView.setGankTitle(mWebView.getGanTitle());
        //设置fabButton的背景颜色
        mWebView.setFabButtonColor(ThemeManage.INSTANCE.getColorPrimary());
        mFavoriteData = mWebView.getFavoriteData();
        findHasFavoriteGank();
        loadData();
    }

    private void loadData() {
        mGankUrl = mWebView.getLoadUrl();
        mWebView.loadGankURL(mGankUrl);
    }

    private void findHasFavoriteGank() {
        if (mFavoriteData == null) {
            mWebView.hideFavoriteFab();
            return;
        }
        List<Favorite> favorites = DataSupport.where("gankID = ?", mFavoriteData.getGankID()).find(Favorite.class);
        mIsFavorite = favorites.size() > 0;
        mWebView.setFavoriteState(mIsFavorite);
    }

    @Override
    public void unsubscribe() {
    
    }
}
