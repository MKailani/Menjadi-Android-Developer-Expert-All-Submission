package com.appkamus.submission.appkamus.view.activity;

import android.support.v4.app.Fragment;

/**
 * Dicoding Academy
 *
 * Submisison 3 Aplikasi Kamus
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 13/01/19.
 */
interface MainView {
    void setupToolbar();
    void changeTitleBar(String text);
    void changeSubTitleBar(String text);
    void replaceFragment(Fragment view, String tag);
    void setupListener();
}
