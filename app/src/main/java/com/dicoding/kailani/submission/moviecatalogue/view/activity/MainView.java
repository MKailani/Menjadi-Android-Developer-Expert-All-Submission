package com.dicoding.kailani.submission.moviecatalogue.view.activity;

import android.support.v4.app.Fragment;

/**
 * Dicoding Academy
 *
 * Submisison 4 Aplikasi Movie Catalogue UI/UX DATABASE
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 21/01/19.
 */
interface MainView {
    void setupToolbar();
    void changeTitleBar(String text);
    void replaceFragment(Fragment view, String tag);
    void setupListener();
}
