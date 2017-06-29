package com.cp.ye0ye.rxjavademo.module.home;

import android.support.v7.graphics.Palette;

import com.cp.ye0ye.rxjavademo.App;
import com.cp.ye0ye.rxjavademo.ConfigManage;
import com.cp.ye0ye.rxjavademo.R;
import com.cp.ye0ye.rxjavademo.ThemeManage;
import com.cp.ye0ye.rxjavademo.entity.CategoryResult;
import com.cp.ye0ye.rxjavademo.network.NetWork;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 6/28/2017.
 */
public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mView;

    private CompositeSubscription mSubscription;

    public HomePresenter(HomeContract.View view) {
        mView = view;
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void getRandomBanner() {
        getBanner(true);
    }

    @Override
    public void setThemeColor(Palette palette) {
        if (palette != null) {
            int colorPrimary = App.getInstance().getResources()
                    .getColor(R.color.colorPrimary);
            // 把从调色板上获取的主题色保存在内存中
            ThemeManage.INSTANCE.setColorPrimary(palette
                    .getDarkVibrantColor(colorPrimary));
            // 设置 AppBarLayout 的背景色
            mView.setAppBarLayoutColor(ThemeManage
                    .INSTANCE.getColorPrimary());
            // 设置 FabButton 的背景色
            mView.setFabButtonColor(ThemeManage.INSTANCE
                    .getColorPrimary());
            // 停止 FabButton 加载中动画
            mView.enableFabButton();
            mView.stopBannerLoadingAnim();
        }

    }

    /*
    * 获得Banner
    * */
    @Override
    public void getBanner(boolean isRandom) {
        mView.startBannerLoadingAnim();
        mView.disEnableFabButton();
        Observable<CategoryResult> observable;
        if (isRandom) {
            observable = NetWork.getGankApi().getRandomBeauties(1);
        } else {
            observable = NetWork.getGankApi().getRandomBeauties(1);
            //让第一张图也随机吧
//            observable = NetWork.getGankApi().getCategoryDate("福利", 1, 1);
        }
        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showBannerFail("Banner Load Faild"+e.toString());
                        mView.enableFabButton();
                        mView.stopBannerLoadingAnim();
                    }

                    @Override
                    public void onNext(CategoryResult categoryResult) {
                        if (categoryResult != null && categoryResult.results != null
                                && categoryResult.results.size() > 0
                                && categoryResult.results.get(0).url != null) {
                            mView.setBanner(categoryResult.results.get(0).url);
                        } else {
                            mView.showBannerFail("Banner Load Faild, OnNext");
                        }
                    }
                });
        mSubscription.add(subscription);

    }

    @Override
    public void saveCacheImgUrl(String url) {
        ConfigManage.INSTANCE.setBannerURL(url);

    }

    @Override
    public void subscribe() {
        getBanner(false);
        cacheRandomImg();
    }

    /*
    * 缓存随机图片
    * */
    private void cacheRandomImg() {
        if (!ConfigManage.INSTANCE.isShowLauncherImg()) {
            return;
        }
        if (ConfigManage.INSTANCE.isProbabilityShowLauncherImg()) {
            if (Math.random() < 0.75) {
                ConfigManage.INSTANCE.setBannerURL("");
                return;
            }
        }
        Observable<CategoryResult> observable;
        observable = NetWork.getGankApi().getRandomBeauties(1);
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CategoryResult categoryResult) {
                        if (categoryResult != null && categoryResult.results != null
                                && categoryResult.results.size() > 0
                                && categoryResult.results.get(0).url != null) {
                            mView.cacheImg(categoryResult.results.get(0).url);
                        }
                    }
                });
        mSubscription.add(subscription);
    }


    @Override
    public void unsubscribe() {
        mSubscription.unsubscribe();
    }
}
