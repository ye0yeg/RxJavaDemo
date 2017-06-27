package com.cp.ye0ye.rxjavademo.module.launcher;

import com.cp.ye0ye.rxjavademo.base.BasePresenter;
import com.cp.ye0ye.rxjavademo.base.BaseView;

/**
 * Created by ye0ye on 2017/6/27.
 */

public class LauncherContract {

    interface View extends BaseView {

        void goHomeActivity();

        void loadImg(String url);

    }

    interface Presenter extends BasePresenter {

    }

}
