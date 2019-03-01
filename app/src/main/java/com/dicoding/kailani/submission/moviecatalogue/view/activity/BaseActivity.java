package com.dicoding.kailani.submission.moviecatalogue.view.activity;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;


import com.dicoding.kailani.submission.moviecatalogue.view.widget.FavoriteMovieWidget;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Dicoding Academy
 *
 * Final Project Aplikasi Movie Catalogue
 * Menjadi Android Developer Expert (MADE)
 *
 * Created by kheys on 28/01/19.
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


    void goToHomeScreen(){
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
    }

    void doNotifyWidgetFavMovie(){
        Intent updateWidget = new Intent(getApplicationContext(), FavoriteMovieWidget.class);
        updateWidget.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        sendBroadcast(updateWidget);
    }

}
