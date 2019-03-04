package com.appkamus.submission.appkamus.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appkamus.submission.appkamus.R;
import com.appkamus.submission.appkamus.presenter.SplashScreenPresenter;

import butterknife.BindView;

/**
 * Dicoding Academy
 *
 * Submisison 3 Aplikasi Kamus
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 13/01/19.
 */
public class SplashScreenActivity extends BaseActivity implements SplashScreenView{

    @BindView(R.id.prg_loading)
    protected ProgressBar prgLoading;

    @BindView(R.id.tv_description_load)
    protected TextView tvDesc;

    private SplashScreenPresenter mSplashScreenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mSplashScreenPresenter = new SplashScreenPresenter(getApplicationContext(),this);

        loadData();
    }

    @Override
    public void loadData() {
        // First Load Data
        mSplashScreenPresenter.preparingDataLoad();

    }

    @Override
    public void updateProgressBar(int value) {
        prgLoading.setProgress(value);

    }

    @Override
    public void infoProgressBar(final String text) {
        tvDesc.post(new Runnable() {
            @Override
            public void run() {
                tvDesc.setText(text);
            }
        });
    }

    @Override
    public void goToMainMenu() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },1500);

    }


}
