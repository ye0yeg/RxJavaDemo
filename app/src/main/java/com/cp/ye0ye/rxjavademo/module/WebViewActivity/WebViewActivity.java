package com.cp.ye0ye.rxjavademo.module.WebViewActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cp.ye0ye.rxjavademo.R;
import com.cp.ye0ye.rxjavademo.base.SwipeBackBaseActivity;
import com.cp.ye0ye.rxjavademo.entity.Favorite;

/**
 * Created by Administrator on 6/30/2017.
 */

public class WebViewActivity extends SwipeBackBaseActivity implements WebViewContract.View {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_web_view);
    }

    @Override
    public void setGankTitle(String title) {

    }

    @Override
    public void loadGankURL(String url) {

    }

    @Override
    public void setToolbarBackgroundColor(int color) {

    }

    @Override
    public String getLoadUrl() {
        return null;
    }

    @Override
    public String getGanTitle() {
        return null;
    }

    @Override
    public void setFabButtonColor(int color) {

    }

    @Override
    public Favorite getFavoriteData() {
        return null;
    }

    @Override
    public void setFavoriteState(boolean isFavorite) {

    }

    @Override
    public void hideFavoriteFab() {

    }

    @Override
    public void showTip() {

    }
}
