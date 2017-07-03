package com.cp.ye0ye.rxjavademo.module.search;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cp.ye0ye.rxjavademo.R;
import com.cp.ye0ye.rxjavademo.base.SwipeBackBaseActivity;

import butterknife.ButterKnife;

/**
 * Created by ye0ye on 2017/7/3.
 */
public class SearchActivity extends SwipeBackBaseActivity implements SearchContract.View {

    private SearchContract.Presenter mPresenter = new SearchPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);


    }
}
