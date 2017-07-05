package com.cp.ye0ye.rxjavademo.module.setting;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cp.ye0ye.rxjavademo.R;
import com.cp.ye0ye.rxjavademo.base.SwipeBackBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 7/5/2017.
 */
public class SettingActivity extends SwipeBackBaseActivity implements SettingContract.View {

    private SettingContract.Presenter mPresenter = new SettingPresenter(this);

    @BindView(R.id.toolbar_setting)
    Toolbar mToolbarSetting;
    //拨片开关
    @BindView(R.id.switch_setting)
    SwitchCompat mSwitchCompat;
    @BindView(R.id.appbar_setting)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.tv_setting_version_name)
    AppCompatTextView mTvSettingVersionName;
    @BindView(R.id.ll_setting_image_quality)
    LinearLayout mLlImageQuality;
    @BindView(R.id.tv_setting_image_quality_title)
    TextView mTvImageQualityTitle;
    @BindView(R.id.tv_setting_image_quality_content)
    TextView mTvImageQualityContent;
    @BindView(R.id.tv_setting_image_quality_tip)
    TextView mTvImageQualityTip;
    @BindView(R.id.tv_setting_clean_cache)
    TextView mTvCleanCache;
    @BindView(R.id.switch_setting_show_launcher_img)
    SwitchCompat mSwitchSettingShowLauncherImg;
    @BindView(R.id.switch_setting_always_show_launcher_img)
    SwitchCompat mSwitchSettingAlwaysShowLauncherImg;
    @BindView(R.id.ll_is_always_show_launcher_img)
    LinearLayout mLlAlwaysShowLauncherImg;
    @BindView(R.id.tv_is_always_show_launcher_img_title)
    AppCompatTextView mTvAlwaysShowLauncherImgTitle;
    @BindView(R.id.tv_is_always_show_launcher_img_content)
    AppCompatTextView mTvAlwaysShowLauncherImgContent;
    @BindView(R.id.tv_is_show_launcher_img_content)
    AppCompatTextView mTvShowLauncherImgContent;


    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }


    @Override
    public void setToolbarBackgroundColor(final int color) {

    }

    @Override
    public void changeSwitchState(final boolean isChecked) {

    }

    @Override
    public void changeIsShowLauncherImgSwitchState(final boolean isChecked) {

    }

    @Override
    public void changeIsAlwaysShowLauncherImgSwitchState(final boolean isChecked) {

    }

    @Override
    public void setSwitchCompatsColor(final int color) {

    }

    @Override
    public void setAppVersionNameInTv(final String versionName) {

    }

    @Override
    public void setImageQualityChooseUnEnable() {

    }

    @Override
    public void setImageQualityChooseEnable() {

    }

    @Override
    public void setLauncherImgProbabilityUnEnable() {

    }

    @Override
    public void setLauncherImgProbabilityEnable() {

    }

    @Override
    public void setThumbnailQualityInfo(final int quality) {

    }

    @Override
    public void showCacheSize(final String cache) {

    }

    @Override
    public void showSuccessTip(final String msg) {

    }

    @Override
    public void showFailTip(final String msg) {

    }

    @Override
    public void setShowLauncherTip(final String tip) {

    }

    @Override
    public void setAlwaysShowLauncherTip(final String tip) {

    }
}
