package com.cp.ye0ye.rxjavademo.module.setting;

import com.cp.ye0ye.rxjavademo.App;
import com.cp.ye0ye.rxjavademo.ConfigManage;
import com.cp.ye0ye.rxjavademo.ThemeManage;
import com.cp.ye0ye.rxjavademo.utils.DataCleanManager;
import com.cp.ye0ye.rxjavademo.utils.PackageUtil;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 7/5/2017.
 */
public class SettingPresenter implements SettingContract.Presenter {
    private SettingContract.View mView;

    private CompositeSubscription mSubscriptions;

    private boolean mSwitchSettingInitState;

    private int mTvImageQualityContentInitState;

    public SettingPresenter(SettingContract.View view) {
        mView = view;
    }

    @Override
    public boolean isThumbnailSettingChanged() {
        return mSwitchSettingInitState != ConfigManage.INSTANCE.isListShowImg()
                || mTvImageQualityContentInitState > ConfigManage.INSTANCE.getThumbnailQuality();
    }

    @Override
    public void saveIsListShowImg(final boolean isListShowImg) {
        ConfigManage.INSTANCE.setListShowImg(isListShowImg);
        setImageQualityChooseIsEnable(isListShowImg);
    }

    @Override
    public void saveIsLauncherShowImg(final boolean isLauncherShowImg) {
        ConfigManage.INSTANCE.setShowLauncherImg(isLauncherShowImg);
        setIsLauncherAlwaysShowImgEnable(isLauncherShowImg);
        if (isLauncherShowImg) {
            mView.setShowLauncherTip("TURN ON");
        } else {
            mView.setShowLauncherTip("TURN OFF");
        }

    }


    private void setIsLauncherAlwaysShowImgEnable(boolean isEnable) {
        if (isEnable) {
            mView.setLauncherImgProbabilityEnable();
        } else {
            mView.setLauncherImgProbabilityUnEnable();
        }
    }


    @Override
    public void saveIsLauncherAlwaysShowImg(final boolean isLauncherAlwaysShowImg) {
        ConfigManage.INSTANCE.setProbabilityShowLauncherImg(isLauncherAlwaysShowImg);
        if (isLauncherAlwaysShowImg) {
            mView.setAlwaysShowLauncherTip("JUST SOME");
        } else {
            mView.setAlwaysShowLauncherTip("WANT IT");
        }
    }

    @Override
    public int getColorPrimary() {
        return ThemeManage.INSTANCE.getColorPrimary();
    }

    @Override
    public int getThumbnailQuality() {
        return ConfigManage.INSTANCE.getThumbnailQuality();
    }

    @Override
    public void setThumbnailQuality(int quality) {
        ConfigManage.INSTANCE.setThumbnailQuality(quality);
        mView.setThumbnailQualityInfo(quality);
    }

    @Override
    public void cleanCache() {
        if (DataCleanManager.clearAllCache()) {
            ConfigManage.INSTANCE.setBannerURL("");
            mView.showSuccessTip("清理成功");
        } else {
            mView.showFailTip("清理失败");
        }


        try {
            mView.showCacheSize(DataCleanManager.getTotalCacheSize());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void subscribe() {
        mSubscriptions = new CompositeSubscription();
        // 设置 View 界面的主题色
        mView.setSwitchCompatsColor(ThemeManage.INSTANCE.getColorPrimary());
        mView.setToolbarBackgroundColor(ThemeManage.INSTANCE.getColorPrimary());
        //初始化开关显示状态
        mView.changeSwitchState(ConfigManage.INSTANCE.isListShowImg());
        mView.changeIsShowLauncherImgSwitchState(ConfigManage.INSTANCE.isShowLauncherImg());
        mView.changeIsAlwaysShowLauncherImgSwitchState(ConfigManage.INSTANCE.isProbabilityShowLauncherImg());

        setImageQualityChooseIsEnable(ConfigManage.INSTANCE.isListShowImg());
        setIsLauncherAlwaysShowImagEnable(ConfigManage.INSTANCE.isShowLauncherImg());

        mView.setAppVersionNameInTv(PackageUtil.getVersionName(App.getInstance()));
        setThumbnailQuality(ConfigManage.INSTANCE.getThumbnailQuality());
        mView.showCacheSize(DataCleanManager.getTotalCacheSize());

        mSwitchSettingInitState = ConfigManage.INSTANCE.isListShowImg();
        mTvImageQualityContentInitState = ConfigManage.INSTANCE.getThumbnailQuality();
    }

    private void setIsLauncherAlwaysShowImagEnable(final boolean isEnable) {
        if (isEnable) {
            mView.setLauncherImgProbabilityEnable();
        } else {
            mView.setLauncherImgProbabilityUnEnable();
        }
    }

    private void setImageQualityChooseIsEnable(boolean isEnable) {
        if (isEnable) {
            mView.setImageQualityChooseEnable();
        } else {
            mView.setImageQualityChooseUnEnable();
        }
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }


}
