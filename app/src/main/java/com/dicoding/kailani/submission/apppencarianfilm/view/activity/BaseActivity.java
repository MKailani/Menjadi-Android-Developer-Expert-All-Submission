package com.dicoding.kailani.submission.apppencarianfilm.view.activity;

import android.support.v7.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Khay on 03/01/19.
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
        SimpleDateFormat sdfFormater = new SimpleDateFormat("dd-mm-yy", Locale.getDefault());
        return sdfFormater.format(value);
    }
}
