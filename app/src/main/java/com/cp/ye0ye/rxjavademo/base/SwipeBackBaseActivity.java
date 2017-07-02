package com.cp.ye0ye.rxjavademo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cp.ye0ye.rxjavademo.R;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

/**
 * Created by ye0ye on 2017/7/2.
 */
public class SwipeBackBaseActivity extends AppCompatActivity implements BGASwipeBackHelper.Delegate {
    protected BGASwipeBackHelper mSwipeBackHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /**
         * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
         */
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
    }


    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {

    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {

    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();

    }

    private void initSwipeBackFinish() {
        //返回滑块是否可用用
        mSwipeBackHelper.setSwipeBackEnable(true);
        //是否只跟踪左侧边缘的滑动返回
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        //微信样式
        mSwipeBackHelper.setIsWeChatStyle(false);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        //设置是否显示滑动返回的阴影效果
        mSwipeBackHelper.setIsNeedShowShadow(true);
        //设置渐变
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
    }

}
