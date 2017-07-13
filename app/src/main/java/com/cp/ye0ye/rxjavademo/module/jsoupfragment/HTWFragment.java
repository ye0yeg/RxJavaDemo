package com.cp.ye0ye.rxjavademo.module.jsoupfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cp.ye0ye.rxjavademo.R;
import com.cp.ye0ye.rxjavademo.entity.HireResult;
import com.cp.ye0ye.rxjavademo.widget.RecycleViewDivider;
import com.cp.ye0ye.rxjavademo.widget.recyclerviewwithfooter.OnLoadMoreListener;
import com.cp.ye0ye.rxjavademo.widget.recyclerviewwithfooter.RecyclerViewWithFooter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

/**
 * Created by Administrator on 7/7/2017.
 */

public class HTWFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener, HTWContract.View {

    @BindView(R.id.recycle_view)
    RecyclerViewWithFooter mRecyclerViewWithFooter;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private HireCatchAdapter mHireCatchAdapter;
    public static final String CATEGORY_NAME = "com.cp.ye0ye.rxjavademo.module.category.CATEGORY_NAME";
    private String mCategoryName;
    private HTWContract.Presenter mPresenter;

    public static HTWFragment newInstance(String mCategoryName) {
        HTWFragment htwFragment = new HTWFragment();

        //传递参数
        Bundle bundle = new Bundle();
        bundle.putString(CATEGORY_NAME, mCategoryName);
        htwFragment.setArguments(bundle);
        return htwFragment;
    }


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        mCategoryName = bundle.getString(CATEGORY_NAME);
        mPresenter = new HTWPresenter(this, mCategoryName);
        //设置刷新界面
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.colorSwipeRefresh1,
                R.color.colorSwipeRefresh2,
                R.color.colorSwipeRefresh3,
                R.color.colorSwipeRefresh4,
                R.color.colorSwipeRefresh5,
                R.color.colorSwipeRefresh6);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mHireCatchAdapter = new HireCatchAdapter(getContext());
        mRecyclerViewWithFooter.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewWithFooter.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL));
        mRecyclerViewWithFooter.setAdapter(mHireCatchAdapter);
        mRecyclerViewWithFooter.setOnLoadMoreListener(this);
        mRecyclerViewWithFooter.setEmpty();

        return view;

    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.subscribe();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        mPresenter.getCategoryItems(false);

    }


    @Override
    public void setCategoryItem(List<HireResult.ResultBean.DataBean> hireResult) {
        mHireCatchAdapter.mData = hireResult;
        mHireCatchAdapter.notifyDataSetChanged();
    }

    @Override
    public void addCategoryItem(List<HireResult.ResultBean.DataBean> hireResult) {
        int start = mHireCatchAdapter.getItemCount();
        mHireCatchAdapter.mData.addAll(hireResult);
        mHireCatchAdapter.notifyItemRangeInserted(start, hireResult.size());
    }

    @Override
    public void getCategoryItemFail(String failMessage) {
        Toasty.error(getContext(), "获取失败").show();
    }

    @Override
    public String itemName() {
        return null;
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
    public void setLoading() {
        mRecyclerViewWithFooter.setLoading();
    }


    @Override
    public void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }

}
