package com.dicoding.kailani.submission.apppencarianfilm.view;

/**
 * Dicoding Academy
 * Submission 1 - Aplikasi Pencarian Film
 *
 * Created by Kailani on 04/01/19.
 */
public interface BaseView {
    void showLoading();
    void dismissLoading();
    void loadContentError();
    void memoryRelease();
}
