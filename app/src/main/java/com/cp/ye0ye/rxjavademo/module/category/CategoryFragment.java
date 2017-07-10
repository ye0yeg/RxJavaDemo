package com.cp.ye0ye.rxjavademo.module.category;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cp.ye0ye.rxjavademo.GlobalConfig;
import com.cp.ye0ye.rxjavademo.R;
import com.cp.ye0ye.rxjavademo.entity.CategoryResult;
import com.cp.ye0ye.rxjavademo.module.home.HomeActivity;
import com.cp.ye0ye.rxjavademo.widget.RecycleViewDivider;
import com.cp.ye0ye.rxjavademo.widget.recyclerviewwithfooter.OnLoadMoreListener;
import com.cp.ye0ye.rxjavademo.widget.recyclerviewwithfooter.RecyclerViewWithFooter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE;

/**
 * Created by Administrator on 6/29/2017.
 */

public class CategoryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, CategoryContract.View, OnLoadMoreListener {

    @BindView(R.id.recycle_view)
    RecyclerViewWithFooter mRecyclerViewWithFooter;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static CategoryFragment newInstance(String mCategoryName) {
        CategoryFragment categoryFragment = new CategoryFragment();

        //传递参数
        Bundle bundle = new Bundle();
        bundle.putString(CATEGORY_NAME, mCategoryName);
        categoryFragment.setArguments(bundle);
        return categoryFragment;
    }

    public static final String CATEGORY_NAME = "com.cp.ye0ye.rxjavademo.module.category.CATEGORY_NAME";
    private CategoryContract.Presenter mPresenter = new CategoryPresenter(this);
    private CategoryListAdapter mCategoryListAdapter;
    private String mCategoryName;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle bundle = getArguments();
            mCategoryName = bundle.getString(CATEGORY_NAME);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == HomeActivity.SETTING_REQUEST_CODE) {
            mCategoryListAdapter.notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        ButterKnife.bind(this, view);
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.colorSwipeRefresh1,
                R.color.colorSwipeRefresh2,
                R.color.colorSwipeRefresh3,
                R.color.colorSwipeRefresh4,
                R.color.colorSwipeRefresh5,
                R.color.colorSwipeRefresh6);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mCategoryListAdapter = new CategoryListAdapter(getContext());

        mRecyclerViewWithFooter.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewWithFooter.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL));
        mRecyclerViewWithFooter.setAdapter(mCategoryListAdapter);
        mRecyclerViewWithFooter.setOnLoadMoreListener(this);
        mRecyclerViewWithFooter.setEmpty();
        mRecyclerViewWithFooter.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                final Picasso picasso = Picasso.with(CategoryFragment.this.getContext());
                if (newState == SCROLL_STATE_IDLE) {
                    picasso.resumeTag(GlobalConfig.PICASSO_TAG_THUMBNAILS_CATEGORY_LIST_ITEM);
                } else {
                    picasso.pauseTag(GlobalConfig.PICASSO_TAG_THUMBNAILS_CATEGORY_LIST_ITEM);
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.subscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Picasso.with(CategoryFragment.this.getContext()).cancelTag(GlobalConfig.PICASSO_TAG_THUMBNAILS_CATEGORY_LIST_ITEM);
        mPresenter.unsubscribe();
    }


    @Override
    public String getCategoryName() {
        return this.mCategoryName;
    }

    @Override
    public void showSwipeLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideSwipeLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.getCategoryItems(true);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getCategoryItems(false);
    }

    @Override
    public void setLoading() {
        mRecyclerViewWithFooter.setLoading();
    }

    @Override
    public void getCategoryItemFail(String failMessage) {
        if (getUserVisibleHint()) {
            Toasty.error(this.getContext(), failMessage).show();
        }
    }

    @Override
    public void setCategoryItems(CategoryResult categoryResult) {
        mCategoryListAdapter.mData = categoryResult.results;
        mCategoryListAdapter.notifyDataSetChanged();
    }


    @Override
    public void addCategoryItem(CategoryResult categoryResult) {
        int start = mCategoryListAdapter.getItemCount();
        mCategoryListAdapter.mData.addAll(categoryResult.results);
        mCategoryListAdapter.notifyItemRangeInserted(start, categoryResult.results.size());
    }
}
