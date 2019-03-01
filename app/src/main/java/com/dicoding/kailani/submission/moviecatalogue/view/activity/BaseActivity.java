package com.dicoding.kailani.submission.moviecatalogue.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Dicoding Academy
 *
 * Submisison 4 Aplikasi Movie Catalogue UI/UX DATABASE
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 21/01/19.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder mUnbinder;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    CharSequence toText(Double value) {
        return String.valueOf(value);
    }

    CharSequence toText(int value) {
        return String.valueOf(value);
    }

    CharSequence toReleaseDate(String value) {
        return TextUtils.isEmpty(value) ? "Comming Soon" : value;
    }

    void goToHomeScreen(){
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
    }

}
