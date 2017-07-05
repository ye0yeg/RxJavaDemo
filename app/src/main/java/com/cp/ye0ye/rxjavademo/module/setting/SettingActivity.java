package com.cp.ye0ye.rxjavademo.module.setting;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.cp.ye0ye.rxjavademo.R;
import com.cp.ye0ye.rxjavademo.base.SwipeBackBaseActivity;
import com.cp.ye0ye.rxjavademo.utils.DisplayUtils;
import com.cp.ye0ye.rxjavademo.utils.MDTintUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * Created by Administrator on 7/5/2017.
 */
public class SettingActivity extends SwipeBackBaseActivity implements SettingContract.View, CompoundButton.OnCheckedChangeListener {

    private SettingContract.Presenter mPresenter = new SettingPresenter(this);

    @BindView(R.id.toolbar_setting)
    Toolbar mToolbarSetting;
    @BindView(R.id.switch_setting)
    SwitchCompat mSwitchSetting;
    @BindView(R.id.appbar_setting)
    AppBarLayout mAppbarSetting;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mAppbarSetting.setPadding(
                    mAppbarSetting.getPaddingLeft(),
                    mAppbarSetting.getPaddingTop() + DisplayUtils.getStatusBarHeight(this),
                    mAppbarSetting.getPaddingRight(),
                    mAppbarSetting.getPaddingBottom());

        }
        setSupportActionBar(mToolbarSetting);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToolbarSetting.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mSwitchSetting.setOnCheckedChangeListener(this);
        mSwitchSettingShowLauncherImg.setOnCheckedChangeListener(this);
        mSwitchSettingAlwaysShowLauncherImg.setOnCheckedChangeListener(this);
        mPresenter.subscribe();
    }

    @Override
    public void onBackPressed() {
        if (mPresenter.isThumbnailSettingChanged()) {
            setResult(RESULT_OK);
        }
        super.onBackPressed();
    }

    @Override
    public void setToolbarBackgroundColor(final int color) {
        mAppbarSetting.setBackgroundColor(color);

    }

    @Override
    public void changeSwitchState(final boolean isChecked) {
        mSwitchSetting.setChecked(isChecked);
    }

    @Override
    public void changeIsShowLauncherImgSwitchState(final boolean isChecked) {
        mSwitchSettingShowLauncherImg.setChecked(isChecked);
    }

    @Override
    public void changeIsAlwaysShowLauncherImgSwitchState(final boolean isChecked) {
        mSwitchSettingAlwaysShowLauncherImg.setChecked(isChecked);
    }

    @Override
    public void setSwitchCompatsColor(final int color) {
        MDTintUtil.setTint(mSwitchSetting, color);
        MDTintUtil.setTint(mSwitchSettingShowLauncherImg, color);
        MDTintUtil.setTint(mSwitchSettingAlwaysShowLauncherImg, color);
    }

    @Override
    public void setAppVersionNameInTv(final String versionName) {
        mTvSettingVersionName.setText("版本: " + versionName);
    }

    @Override
    public void setImageQualityChooseUnEnable() {
        mLlImageQuality.setClickable(false);
        mTvImageQualityTitle.setTextColor(getResources().getColor(R.color.colorTextUnEnable));
        mTvImageQualityContent.setTextColor(getResources().getColor(R.color.colorTextUnEnable));
        mTvImageQualityTip.setTextColor(getResources().getColor(R.color.colorTextUnEnable));

    }

    @Override
    public void setImageQualityChooseEnable() {
        mLlImageQuality.setClickable(true);
        mTvImageQualityTitle.setTextColor(getResources().getColor(R.color.colorTextEnable));
        mTvImageQualityContent.setTextColor(getResources().getColor(R.color.colorTextEnableGary));
        mTvImageQualityTip.setTextColor(getResources().getColor(R.color.colorTextEnableGary));

    }

    @Override
    public void setLauncherImgProbabilityUnEnable() {
        mLlAlwaysShowLauncherImg.setClickable(false);
        mSwitchSettingAlwaysShowLauncherImg.setClickable(false);
        mTvAlwaysShowLauncherImgTitle.setTextColor(getResources().getColor(R.color.colorTextUnEnable));
        mTvAlwaysShowLauncherImgContent.setTextColor(getResources().getColor(R.color.colorTextUnEnable));
    }

    @Override
    public void setLauncherImgProbabilityEnable() {
        mLlAlwaysShowLauncherImg.setClickable(true);
        mSwitchSettingAlwaysShowLauncherImg.setClickable(true);
        mTvAlwaysShowLauncherImgTitle.setTextColor(getResources().getColor(R.color.colorTextEnable));
        mTvAlwaysShowLauncherImgContent.setTextColor(getResources().getColor(R.color.colorTextEnableGary));
    }

    @Override
    public void setThumbnailQualityInfo(final int quality) {
        String thumbnailQuality = "";
        switch (quality) {
            case 0:
                thumbnailQuality = "原图";
                break;
            case 1:
                thumbnailQuality = "默认";
                break;
            case 2:
                thumbnailQuality = "省流";
                break;
        }
        mTvImageQualityContent.setText(thumbnailQuality);
    }

    @Override
    public void showCacheSize(final String cache) {
        mTvCleanCache.setText(cache);
    }

    @Override
    public void showSuccessTip(final String msg) {
        Toasty.success(this, msg).show();
    }

    @Override
    public void showFailTip(final String msg) {
        Toasty.error(this, msg).show();
    }

    @Override
    public void setShowLauncherTip(final String tip) {
        mTvShowLauncherImgContent.setText(tip);
    }

    @Override
    public void setAlwaysShowLauncherTip(final String tip) {
        mTvAlwaysShowLauncherImgContent.setText(tip);
    }
    @OnClick(R.id.ll_setting_image_quality)
    public void chooseThumbnailQualty(){
        new MaterialDialog.Builder(this)
                .title("缩略图质量")
                .items("ORANGE","TAKE IT","SAVE IT")
                .widgetColor(mPresenter.getColorPrimary())
                .alwaysCallSingleChoiceCallback()
                .itemsCallbackSingleChoice(mPresenter.getThumbnailQuality(), new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        mPresenter.setThumbnailQuality(which);
                        dialog.dismiss();
                        return true;
                    }
                })
                .positiveText("取消")
                .positiveColor(mPresenter.getColorPrimary())
                .show();
    }

    @OnClick(R.id.ll_setting_about)
    public void about() {
//        new AboutDialog(this, mSettingPresenter.getColorPrimary()).show();
    }
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

    }
    @OnClick(R.id.ll_setting_clean_cache)
    public void cleanCache() {
        mPresenter.cleanCache();
    }

}
