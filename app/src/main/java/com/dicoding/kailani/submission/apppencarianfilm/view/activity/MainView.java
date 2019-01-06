package com.dicoding.kailani.submission.apppencarianfilm.view.activity;

import android.support.v4.app.Fragment;

/**
 * Dicoding Academy
 * Submission 2 - Aplikasi Pencarian Film UI-UX
 *
 * Created by Kailani on 06/01/19 -> Submssion 2
 */
public interface MainView {
    void setupToolbar();
    void changeTitleBar(String text);
    void replaceFragment(Fragment view);
    void replaceFragment(Fragment view, String tag);
    void setupListener();
}
