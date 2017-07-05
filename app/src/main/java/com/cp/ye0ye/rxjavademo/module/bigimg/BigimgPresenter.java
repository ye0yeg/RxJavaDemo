package com.cp.ye0ye.rxjavademo.module.bigimg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.cp.ye0ye.rxjavademo.ThemeManage;
import com.cp.ye0ye.rxjavademo.utils.FileUtil;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;

import es.dmoral.toasty.Toasty;

/**
 * Created by Administrator on 7/4/2017.
 */
public class BigimgPresenter implements BigimgContract.Presenter {

    private BigimgContract.View mView;
    private Context mContext;

    public BigimgPresenter(BigimgContract.View view, Context context) {
        mView = view;
        mContext = context;
    }

    @Override
    public void saveImg(final String url) {
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, final Picasso.LoadedFrom from) {
                String imageName = System.currentTimeMillis() + ".png";
                File dcimFile =
                        FileUtil
                                .getDCIMFile(FileUtil.PATH_PHOTOGRAPH, imageName);

                FileOutputStream ostream = null;
                try {
                    ostream = new FileOutputStream(dcimFile);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
                    ostream.close();
                } catch (Exception e) {
                    Toasty.info(mContext, "问题:" + e.toString(), Toast.LENGTH_SHORT).show();

                }
                Toasty.info(mContext, "图片下载至:" + dcimFile, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBitmapFailed(final Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(final Drawable placeHolderDrawable) {

            }
        };
        //Picasso下载
        Picasso.with(mContext).load(url).into(target);
    }

    @Override
    public void subscribe() {
        mView.setToolbarBackgroundColor(ThemeManage.INSTANCE.getColorPrimary());
        mView.setLoadingColor(ThemeManage.INSTANCE.getColorPrimary());
        loadMeiziImg(mView.getMeiziImg());
        setMeiziTitle(mView.getMeiziTitle());
    }

    private void setMeiziTitle(final String title) {
        if (title == null) {
            return;
        }
        mView.setMeiziTitle("Yours" + title);
    }

    private void loadMeiziImg(final String url) {
        if (url == null) {
            return;
        }
        mView.showLoading();
        mView.loadMeizuImg(url);
    }

    @Override
    public void unsubscribe() {
        //不用销毁
    }
}
