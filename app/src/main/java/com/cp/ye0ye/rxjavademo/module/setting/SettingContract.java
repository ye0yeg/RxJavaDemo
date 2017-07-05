package com.cp.ye0ye.rxjavademo.module.setting;

import com.cp.ye0ye.rxjavademo.base.BasePresenter;
import com.cp.ye0ye.rxjavademo.base.BaseView;

/**
 * Created by Administrator on 7/5/2017.
 */
public interface SettingContract {
    interface View extends BaseView {
        /**
         * 设置tb的bg
         */
        void setToolbarBackgroundColor(int color);

        /**
         * 设置开关状态
         */
        void changeSwitchState(boolean isChecked);

        /**
         * 改变开关（启动界面）
         */
        void changeIsShowLauncherImgSwitchState(boolean isChecked);

        /**
         * 改变开关（是否一直开启启动界面）
         */
        void changeIsAlwaysShowLauncherImgSwitchState(boolean isChecked);

        /**
         * 设置开关颜色
         */
        void setSwitchCompatsColor(int color);

        /**
         * 设置版本号
         */
        void setAppVersionNameInTv(String versionName);

        /**
         * 设置栏目ImageQualityUnable
         */
        void setImageQualityChooseUnEnable();

        /**
         * 设置栏目ImageQualityEnable
         */
        void setImageQualityChooseEnable();

        /**
         * 设置随机启动页面Unable
         */
        void setLauncherImgProbabilityUnEnable();

        /**
         * 设置随机启动界面Enable
         */
        void setLauncherImgProbabilityEnable();

        /**
         * 设置小图质量
         */
        void setThumbnailQualityInfo(int quality);

        /**
         * 显示缓存大小
         */
        void showCacheSize(String cache);

        /**
         * 显示成功Tip
         */
        void showSuccessTip(String msg);

        /**
         * 显示失败Tip
         */
        void showFailTip(String msg);

        /**
         * 设置开启时的Tip
         */
        void setShowLauncherTip(String tip);

        /**
         * 设置永久显示开启时的tip
         */
        void setAlwaysShowLauncherTip(String tip);
    }

    interface Presenter extends BasePresenter {

        /**
         * 监听是否开启图片模式改变
         */
        boolean isThumbnailSettingChanged();

        /**
         * 存储显示的img
         */
        void saveIsListShowImg(boolean isListShowImg);

        /**
         * 存储开启界面
         */
        void saveIsLauncherShowImg(boolean isLauncherShowImg);

        /**
         * 存储永久开启界面
         */
        void saveIsLauncherAlwaysShowImg(boolean isLauncherAlwaysShowImg);

        /**
         * 获得当前颜色
         */
        int getColorPrimary();

        /**
         * 获取质量
         */
        int getThumbnailQuality();

        /**
         * 设置质量
         */
        void setThumbnailQuality(int quality);

        /**
         * 清除缓存
         */
        void cleanCache();
    }
}
