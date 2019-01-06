package com.dicoding.kailani.submission.apppencarianfilm.view.activity;

import com.dicoding.kailani.submission.apppencarianfilm.view.BaseView;

/**
 * Dicoding Academy
 * Submission 2 - Aplikasi Pencarian Film UI-UX
 *
 * Created by Kailani on 06/01/19 -> Submssion 2
 */
public interface DetailMovieView extends BaseView {
    void initView();

    void setupRecyclerview();

    void refreshContent();

    void setupListener();

    void setupToolbar(String text);
}
