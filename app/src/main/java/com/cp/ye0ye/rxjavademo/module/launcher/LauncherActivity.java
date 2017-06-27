package com.cp.ye0ye.rxjavademo.module.launcher;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.cp.ye0ye.rxjavademo.R;
import com.cp.ye0ye.rxjavademo.module.home.HomeActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LauncherActivity extends AppCompatActivity implements LauncherContract.View {

    @BindView(R.id.img_launcher_welcome)
    AppCompatImageView mImageView;

    //记录是否存在前台显示
    private boolean isResume;

    private LauncherContract.Presenter mPresenter = new LauncherPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);
        mPresenter.subscribe();
    }

    @Override
    public void loadImg(String url) {
        try {
            Picasso.with(this)
                    .load(url)
                    .into(mImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (!isResume) {
                                        finish();
                                        return;
                                    }
                                    goHomeActivity();
                                }
                            }, 1200);
                        }

                        @Override
                        public void onError() {
                            goHomeActivity();
                        }
                    });
        }catch (Exception e){
            goHomeActivity();
        }
    }

    @Override
    public void goHomeActivity() {
        Intent intent = new Intent(LauncherActivity.this,HomeActivity.class);
        startActivity(intent);
        //Activity
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        finish();
        Toast.makeText(this,"显示了goHome",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        isResume = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isResume = false;
    }
}
