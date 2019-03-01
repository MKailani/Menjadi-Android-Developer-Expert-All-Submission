package com.dicoding.kailani.submission.moviecatalogue.view;

/**
 * Dicoding Academy
 *
 * Final Project Aplikasi Movie Catalogue
 * Menjadi Android Developer Expert (MADE)
 *
 * Created by kheys on 28/01/19.
 */
public interface BaseView {
    void showLoading();

    void dismissLoading();

    void loadContentError();

    void memoryRelease();
}
