package com.cp.ye0ye.rxjavademo.module.WebViewActivity;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cp.ye0ye.rxjavademo.R;
import com.cp.ye0ye.rxjavademo.base.SwipeBackBaseActivity;
import com.cp.ye0ye.rxjavademo.entity.Favorite;
import com.cp.ye0ye.rxjavademo.utils.DisplayUtils;
import com.cp.ye0ye.rxjavademo.utils.MDTintUtil;
import com.cp.ye0ye.rxjavademo.widget.ObservableWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

/**
 * Created by Administrator on 6/30/2017.
 */

public class WebViewActivity extends SwipeBackBaseActivity implements WebViewContract.View {
    public static final String GANK_URL = "com.cp.ye0ye.rxjavademo.module.WebViewActivity.gank_url";
    public static final String GANK_TITLE = "com.cp.ye0ye.rxjavademo.module.WebViewActivity.gank_title";
    public static final String FAVORITE_DATA = "com.cp.ye0ye.rxjavademo.module.WebViewActivity.favorite_data";
    public static final String FAVORITE_POSITION = "com.cp.ye0ye.rxjavademo.module.WebViewActivity.favorite_position";


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.web_view)
    ObservableWebView mWebView;
    @BindView(R.id.progress_webview)
    ProgressBar mProgressBar;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.fab_web_favorite)
    FloatingActionButton mFloatingActionButton;

    private WebViewContract.Presenter mPresenter = new WebViewPresenter(this);
    private boolean isForResult;//是否有结果回传

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mAppbar.setPadding(
                    mAppbar.getPaddingLeft(),
                    mAppbar.getPaddingTop() + DisplayUtils.getStatusBarHeight(this),
                    mAppbar.getPaddingRight(),
                    mAppbar.getPaddingBottom());
        }
        closeFromActionBar();
        initWebView();
        mPresenter.subscribe();
    }

    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);

        mWebView.setWebChromeClient(new MyWebChrome());
        mWebView.setWebViewClient(new MyWebClient());
        mWebView.setOnScrollChangedCallback(new ObservableWebView.OnScrollChangedCallback() {
            @Override
            public void onScroll(int dx, int dy) {
                if (dy > 0) {
                    mFloatingActionButton.hide();
                } else {
                    mFloatingActionButton.show();
                }
            }
        });
    }

    private void closeFromActionBar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                finish();
            }
        });
    }

    @Override
    public void setGankTitle(String title) {
        mTvTitle.setText(title);
    }

    @Override
    public void loadGankURL(String url) {
        mWebView.loadUrl(url);
    }

    @Override
    public void setToolbarBackgroundColor(int color) {
        mAppbar.setBackgroundColor(color);

    }

    @Override
    public String getLoadUrl() {
        return getIntent().getStringExtra(WebViewActivity.GANK_URL);
    }

    @Override
    public String getGanTitle() {
        return getIntent().getStringExtra(WebViewActivity.GANK_TITLE);
    }

    @Override
    public void setFabButtonColor(int color) {
        MDTintUtil.setTint(mFloatingActionButton, color);
    }

    @Override
    public Favorite getFavoriteData() {
        return (Favorite) getIntent().getSerializableExtra(WebViewActivity.FAVORITE_DATA);
    }

    @Override
    public void setFavoriteState(boolean isFavorite) {
        if (isFavorite) {
            mFloatingActionButton.setImageResource(R.drawable.ic_favorite);
        } else {
            mFloatingActionButton.setImageResource(R.drawable.ic_unfavorite);
        }
        isForResult = !isFavorite;
    }

    @Override
    public void hideFavoriteFab() {
        mFloatingActionButton.setVisibility(View.GONE);
        mWebView.setOnScrollChangedCallback(null);
    }

    @Override
    public void showTip(String tip) {
        Toasty.error(this, tip).show();
    }

    private class MyWebChrome extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.setProgress(newProgress);
        }
    }

    private class MyWebClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void finish() {
        if (isForResult) {
        }
        super.finish();
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }
    }
}
