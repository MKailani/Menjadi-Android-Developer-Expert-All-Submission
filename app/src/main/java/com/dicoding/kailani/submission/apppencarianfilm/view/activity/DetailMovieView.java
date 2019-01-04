package com.dicoding.kailani.submission.apppencarianfilm.view.activity;

import com.dicoding.kailani.submission.apppencarianfilm.view.BaseView;

/**
 * Dicoding Academy
 * Submission 1 - Aplikasi Pencarian Film
 *
 * Created by Kailani on 04/01/19.
 */
public interface DetailMovieView extends BaseView {
    void initView();
    void setupRecyclerview();
    void refreshContent();
    void setupListener();
    void setupToolbar(String text);
}
