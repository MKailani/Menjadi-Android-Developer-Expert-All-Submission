package com.dicoding.kailani.submission.apppencarianfilm.view.activity;

import android.support.v4.app.Fragment;

/**
 * Created by kheys on 06/01/19.
 */
public interface MainView {
    void setupToolbar();
    void changeTitleBar(String text);
    void replaceFragment(Fragment view);
    void replaceFragment(Fragment view, String tag);
    void setupListener();
}
