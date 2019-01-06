package com.dicoding.kailani.submission.apppencarianfilm.view;

/**
 * Dicoding Academy
 * Submission 2 - Aplikasi Pencarian Film UI-UX
 *
 * Created by Kailani on 06/01/19 -> Submssion 2
 */
public interface BaseView {
    void showLoading();

    void dismissLoading();

    void loadContentError();

    void memoryRelease();
}
