package com.dicoding.kailani.submission.apppencarianfilm.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Dicoding Academy
 * Submission 1 - Aplikasi Pencarian Film
 *
 * Created by Kailani on 04/01/19.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder mUnbinder;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    protected CharSequence toText(Double value){
        return String.valueOf(value);
    }

    protected CharSequence toText(int value){
        return String.valueOf(value);
    }

    protected CharSequence toText(Date value){
        SimpleDateFormat sdfFormater = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdfFormater.format(value);
    }

    protected  CharSequence toReleaseDate(String value){
        return TextUtils.isEmpty(value) ? "Comming Soon" : value;
    }


}
