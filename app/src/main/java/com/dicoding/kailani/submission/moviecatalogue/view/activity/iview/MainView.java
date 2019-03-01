package com.dicoding.kailani.submission.moviecatalogue.view.activity.iview;

import android.support.v4.app.Fragment;

/**
 * Dicoding Academy
 *
 * Final Project Aplikasi Movie Catalogue
 * Menjadi Android Developer Expert (MADE)
 *
 * Created by kheys on 28/01/19.
 */
public interface MainView {
    void setupToolbar();
    void changeTitleBar(String text);
    void replaceFragment(Fragment view, String tag);
    void setupListener();
    void applySetting();
}
