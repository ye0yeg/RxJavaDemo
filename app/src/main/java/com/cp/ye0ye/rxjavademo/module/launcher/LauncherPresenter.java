package com.cp.ye0ye.rxjavademo.module.launcher;

import android.text.TextUtils;

import com.cp.ye0ye.rxjavademo.ConfigManage;

/**
 * Created by ye0ye on 2017/6/27.
 */
public class LauncherPresenter implements LauncherContract.Presenter {
    private LauncherContract.View mView;

    //需要绑定 VIEW 到当前契约当中
    public LauncherPresenter(LauncherContract.View view) {
        mView = view;
    }

    @Override
    public void subscribe() {
        if(!ConfigManage.INSTANCE.isShowLauncherImg()){
            mView.goHomeActivity();
            return;
        }
        String imgCacheUrl = ConfigManage.INSTANCE.getBannerURL();
        if(!TextUtils.isEmpty(imgCacheUrl)){
            mView.loadImg(imgCacheUrl);
        }else{
            mView.goHomeActivity();
        }
    }

    @Override
    public void unsubscribe() {

    }
}
