package com.cp.ye0ye.rxjavademo.module.home;

import android.support.v7.graphics.Palette;

import com.cp.ye0ye.rxjavademo.base.BasePresenter;
import com.cp.ye0ye.rxjavademo.base.BaseView;

/**
 * Created by ye0ye on 2017/6/28.
 */

/*
* MVP预设的功能在这里显示出
* */
public interface HomeContract {
    interface View extends BaseView {
        //展示Banner失败
        void showBannerFail(String failMessage);
       //设置Banner
        void setBanner(String imgUrl);
        //缓存图片
        void cacheImg(String imgUrl);
        //开始加载Banner动画
        void startBannerLoadingAnim();
        //停止Banner动画
        void stopBannerLoadingAnim();
        //fab按钮
        void enableFabButton();
        //取消fab按钮
        void disEnableFabButton();
        //设置AppBar的颜色
        void setAppBarLayoutColor(int appBarLayoutColor);
        //设置Fab按钮的颜色
        void setFabButtonColor(int color);

    }

    interface Presenter extends BasePresenter {
//      获得随机的Banner
        void getRandomBanner();
//设置主题颜色
        void setThemeColor(Palette palette);
//获取Banner
        void getBanner(final boolean isRandom);
//存储缓存图片
        void saveCacheImgUrl(String url);
    }
}
