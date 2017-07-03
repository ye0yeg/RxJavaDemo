package com.cp.ye0ye.rxjavademo.module.favorite;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cp.ye0ye.rxjavademo.R;
import com.cp.ye0ye.rxjavademo.base.SwipeBackBaseActivity;
import com.cp.ye0ye.rxjavademo.entity.Favorite;
import com.cp.ye0ye.rxjavademo.utils.DisplayUtils;
import com.cp.ye0ye.rxjavademo.widget.RecycleViewDivider;
import com.cp.ye0ye.rxjavademo.widget.recyclerviewwithfooter.OnLoadMoreListener;
import com.cp.ye0ye.rxjavademo.widget.recyclerviewwithfooter.RecyclerViewWithFooter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 7/3/2017.
 */
public class FavoriteActivity extends SwipeBackBaseActivity implements FavoriteContract.View, OnLoadMoreListener {

    @BindView(R.id.toolbar_favorite)
    Toolbar mToolbarFavorite;
    @BindView(R.id.appbar_favorite)
    AppBarLayout mAppbarFavorite;
    @BindView(R.id.recycle_view_favorite)
    RecyclerViewWithFooter mRecyclerView;

    private FavoriteContract.Presenter mPresenter = new FavoritePresenter(this);
    private FavoriteListAdapter mAdapter;
    public static final int REQUEST_CODE_FAVORITE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_favorite);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mAppbarFavorite.setPadding(
                    mAppbarFavorite.getPaddingLeft(),
                    mAppbarFavorite.getPaddingTop() + DisplayUtils.getStatusBarHeight(this),
                    mAppbarFavorite.getPaddingRight(),
                    mAppbarFavorite.getPaddingBottom());
        }
        setSupportActionBar(mToolbarFavorite);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initView();
        mPresenter.subscribe();

    }

    private void initView() {
        //关闭
        mToolbarFavorite.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                finish();
            }
        });

        mAdapter = new FavoriteListAdapter(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnLoadMoreListener(this);
        mRecyclerView.setEmpty();
    }

    @Override
    public void setToolbarBackgroundColor(final int color) {
        mAppbarFavorite.setBackgroundColor(color);
    }

    @Override
    public void addFavoriteItems(final List<Favorite> favorites) {
        int start = mAdapter.getItemCount();
        mAdapter.mData.addAll(favorites);
        mAdapter.notifyItemRangeInserted(start,favorites.size());
    }

    @Override
    public void setFavoriteItems(final List<Favorite> favorites) {
        mAdapter.mData = favorites;
    }

    @Override
    public void setLoading() {

    }

    @Override
    public void setEmpty() {

    }

    @Override
    public void setLoadMoreIsLastPage() {

    }

    @Override
    public void onLoadMore() {

    }
}
