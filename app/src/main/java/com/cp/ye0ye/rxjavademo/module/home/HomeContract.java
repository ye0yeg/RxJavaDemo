package com.cp.ye0ye.rxjavademo.module.home;

import android.support.v7.graphics.Palette;

import com.cp.ye0ye.rxjavademo.base.BasePresenter;
import com.cp.ye0ye.rxjavademo.base.BaseView;

/**
 * Created by ye0ye on 2017/6/28.
 */
public interface HomeContract {
    interface View extends BaseView {
        void showBannerFail(String failMessage);

        void setBanner(String imgUrl);

        void cacheImg(String imgUrl);

        void startBannerLoadingAnim();

        void stopBannerLoadingAnim();

        void enableFabButton();

        void disEnableFabButton();

        void setAppBarLayoutColor(int appBarLayoutColor);

        void setFabButtonColor(int color);

    }

    interface Presenter extends BasePresenter {
        void getRandomBanner();

        void setThemeColor(Palette palette);

        void getBanner(final boolean isRandom);

        void saveCacheImgUrl(String url);
    }
}
