package com.cp.ye0ye.rxjavademo.module.category;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cp.ye0ye.rxjavademo.R;
import com.cp.ye0ye.rxjavademo.entity.CategoryResult;
import com.cp.ye0ye.rxjavademo.module.home.HomeActivity;
import com.cp.ye0ye.rxjavademo.widget.recyclerviewwithfooter.RecyclerViewWithFooter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 6/29/2017.
 */

public class CategoryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,CategoryContract.View{

    @BindView(R.id.recycle_view)
    RecyclerViewWithFooter recyclerViewWithFooter;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

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
        if(resultCode == Activity.RESULT_OK && requestCode == HomeActivity.SETTING_REQUEST_CODE){
            mCategoryListAdapter.notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment,container,false);
        ButterKnife.bind(this,view);
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.colorSwipeRefresh1,
                R.color.colorSwipeRefresh2,
                R.color.colorSwipeRefresh3,
                R.color.colorSwipeRefresh4,
                R.color.colorSwipeRefresh5,
                R.color.colorSwipeRefresh6);
        mSwipeRefreshLayout.setOnRefreshListener(this);


        return view;
    }

    @Override
    public void setCategoryItems(CategoryResult categoryResult) {

    }

    @Override
    public void addCategoryItem(CategoryResult categoryResult) {

    }

    @Override
    public void getCategoryItemFail(String failMessage) {

    }

    @Override
    public String getCategoryName() {
        return null;
    }

    @Override
    public void showSwipeLoading() {

    }

    @Override
    public void hideSwipeLoading() {

    }

    @Override
    public void setLoading() {

    }

    @Override
    public void onRefresh() {

    }
}
