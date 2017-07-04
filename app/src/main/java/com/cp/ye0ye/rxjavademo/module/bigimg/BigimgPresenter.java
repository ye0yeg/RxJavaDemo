package com.cp.ye0ye.rxjavademo.module.bigimg;

import com.cp.ye0ye.rxjavademo.ThemeManage;

/**
 * Created by Administrator on 7/4/2017.
 */
public class BigimgPresenter implements BigimgContract.Presenter {

    private BigimgContract.View mView;

    public BigimgPresenter(BigimgContract.View view) {
        mView = view;
    }

    @Override
    public void saveImg(final String url) {

    }

    @Override
    public void subscribe() {
        mView.setToolbarBackgroundColor(ThemeManage.INSTANCE.getColorPrimary());
        mView.setLoadingColor(ThemeManage.INSTANCE.getColorPrimary());
        loadMeiziImg(mView.getMeiziImg());
        setMeiziTitle(mView.getMeiziTitle());
    }

    private void setMeiziTitle(final String title) {
        if (title == null) {
            return;
        }
        mView.setMeiziTitle("Yours" + title);
    }

    private void loadMeiziImg(final String url) {
        if (url == null) {
            return;
        }
        mView.showLoading();
        mView.loadMeizuImg(url);
    }

    @Override
    public void unsubscribe() {
        //不用销毁
    }
}
