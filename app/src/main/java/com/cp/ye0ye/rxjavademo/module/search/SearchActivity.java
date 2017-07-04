package com.cp.ye0ye.rxjavademo.module.search;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cp.ye0ye.rxjavademo.R;
import com.cp.ye0ye.rxjavademo.base.SwipeBackBaseActivity;
import com.cp.ye0ye.rxjavademo.entity.History;
import com.cp.ye0ye.rxjavademo.entity.SearchResult;
import com.cp.ye0ye.rxjavademo.utils.DisplayUtils;
import com.cp.ye0ye.rxjavademo.utils.KeyboardUtils;
import com.cp.ye0ye.rxjavademo.utils.MDTintUtil;
import com.cp.ye0ye.rxjavademo.widget.RecycleViewDivider;
import com.cp.ye0ye.rxjavademo.widget.recyclerviewwithfooter.OnLoadMoreListener;
import com.cp.ye0ye.rxjavademo.widget.recyclerviewwithfooter.RecyclerViewWithFooter;
import com.luolc.emojirain.EmojiRainLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * Created by ye0ye on 2017/7/3.
 */
public class SearchActivity extends SwipeBackBaseActivity implements SearchContract.View, TextWatcher, TextView.OnEditorActionListener, OnLoadMoreListener {

    @BindView(R.id.toolbar_search)
    Toolbar mToolbarSearch;
    @BindView(R.id.ed_search)
    AppCompatEditText mEdSearch;
    @BindView(R.id.iv_edit_clear)
    AppCompatImageView mIvEditClear;
    @BindView(R.id.iv_search)
    AppCompatImageView mIvSearch;
    @BindView(R.id.appbar_search)
    AppBarLayout mAppBarSearch;
    @BindView(R.id.recycler_view_search)
    RecyclerViewWithFooter mRecyclerViewWithSearch;
    @BindView(R.id.swipe_refresh_layout_search)
    SwipeRefreshLayout mSwipeRefreshLayoutSearch;
    @BindView(R.id.ll_search_history)
    LinearLayout mLlHistory;
    @BindView(R.id.recycler_search_history)
    RecyclerView mRecyclerViewHistory;
    @BindView(R.id.emoji_rainLayout)
    EmojiRainLayout mEmojiRainLayout;

    private SearchContract.Presenter mPresenter = new SearchPresenter(this);
    //两个适配器。一个Search，一个History
    private SearchListAdapter mSearchListAdapter;
    private HistoryListAdapter mHistoryListAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initView();
        //发布
        mPresenter.subscribe();
        //查询历史 TODO
    }

    private void initView() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mAppBarSearch.setPadding(
                    mAppBarSearch.getPaddingLeft(),
                    mAppBarSearch.getPaddingTop() + DisplayUtils.getStatusBarHeight(this),
                    mAppBarSearch.getPaddingRight(),
                    mAppBarSearch.getPaddingBottom());
        }

        setSupportActionBar(mToolbarSearch);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToolbarSearch.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                finish();
            }
        });
        mEdSearch.addTextChangedListener(this);
        mEdSearch.setOnEditorActionListener(this);

        mSwipeRefreshLayoutSearch.setColorSchemeResources(
                R.color.colorSwipeRefresh1,
                R.color.colorSwipeRefresh2,
                R.color.colorSwipeRefresh3,
                R.color.colorSwipeRefresh4,
                R.color.colorSwipeRefresh5,
                R.color.colorSwipeRefresh6
        );
        mSwipeRefreshLayoutSearch.setRefreshing(false);
        mSwipeRefreshLayoutSearch.setEnabled(false);

        mSearchListAdapter = new SearchListAdapter(this);
        mRecyclerViewWithSearch.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewWithSearch.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL));
        mRecyclerViewWithSearch.setAdapter(mSearchListAdapter);
        mRecyclerViewWithSearch.setOnLoadMoreListener(this);
        mRecyclerViewWithSearch.setEmpty();

        mEmojiRainLayout.addEmoji(R.mipmap.emoji1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void setToolbarBackgroundColor(final int color) {
        mAppBarSearch.setBackgroundColor(color);
    }

    @Override
    public void setEditTextCursorColor(final int cursorColor) {
        MDTintUtil.setCursorTint(mEdSearch, cursorColor);
    }

    @Override
    public void showEditClear() {
        mIvEditClear.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEditClear() {
        mIvEditClear.setVisibility(View.GONE);
    }

    @Override
    public void showSearchfail(final String failMsg) {
        Toasty.error(this, failMsg).show();
    }

    @Override
    public void setSearchItems(final SearchResult searchItems) {
        mSearchListAdapter.mData = searchItems.results;
        mSearchListAdapter.notifyDataSetChanged();
        mSwipeRefreshLayoutSearch.setRefreshing(false);
    }

    @Override
    public void addSearchItems(final SearchResult searchResult) {
        int start = mSearchListAdapter.getItemCount();
        mSearchListAdapter.mData.addAll(searchResult.results);
        mSearchListAdapter.notifyItemRangeInserted(start, searchResult.results.size());
    }

    @Override
    public void showSwipLoading() {
        mSwipeRefreshLayoutSearch.setRefreshing(true);
    }

    @Override
    public void hideSwipLoading() {
        mSwipeRefreshLayoutSearch.setRefreshing(false);
    }

    @Override
    public void showTip(final String msg) {
        Toasty.warning(this, msg).show();
    }

    @Override
    public void setLoadMoreIsLastPage() {
        mRecyclerViewWithSearch.setEnd("NO MORE DATA");
    }

    @Override
    public void setEmpty() {
        mRecyclerViewWithSearch.setEmpty();
    }

    @Override
    public void setLoading() {
        mRecyclerViewWithSearch.setLoading();
    }

    @Override
    public void showSearchResult() {
        mLlHistory.setVisibility(View.GONE);
        mSwipeRefreshLayoutSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSearchHistory() {
        mLlHistory.setVisibility(View.VISIBLE);
        mSwipeRefreshLayoutSearch.setVisibility(View.GONE);
    }

    @Override
    public void setHistory(final List<History> history) {
        //Search History.
    }

    @Override
    public void startEmojiRain() {
        mEmojiRainLayout.startDropping();
    }

    @Override
    public void stopEmojiRain() {
        mEmojiRainLayout.stopDropping();
    }

    @Override
    public void onLoadMore() {
        mPresenter.search(mEdSearch.getText().toString().trim(), true);
    }

    @OnClick(R.id.iv_edit_clear)
    public void editClear() {
        mRecyclerViewWithSearch.setEmpty();
        mEdSearch.setText("");
        KeyboardUtils.showSoftInput(this, mEdSearch);
        hideSwipLoading();
        showSearchHistory();
        mPresenter.unsubscribe();
        mPresenter.queryHistory();
    }

    @OnClick(R.id.iv_search)
    public void search() {
        KeyboardUtils.hideSoftInput(this);
        mPresenter.search(mEdSearch.getText().toString().trim(), false);
    }

    @Override
    public void beforeTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {

    }

    @Override
    public void onTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {

    }

    @Override
    public void afterTextChanged(final Editable editable) {

    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, final KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            search();
        }
        return false;
    }

}
