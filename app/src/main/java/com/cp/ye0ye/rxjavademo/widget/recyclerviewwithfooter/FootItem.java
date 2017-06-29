package com.cp.ye0ye.rxjavademo.widget.recyclerviewwithfooter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Footer item
 *
 * @author cjj on 2016/1/30.
 */
public abstract class FootItem {

    public CharSequence loadingText;
    public CharSequence endText;
    public CharSequence pullToLoadText;

    public abstract View onCreateView(ViewGroup parent);

    public abstract void onBindData(View view, int state);

}
