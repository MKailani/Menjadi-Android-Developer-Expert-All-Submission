package com.dicoding.kailani.submission.moviecatalogue.view;

/**
 * Dicoding Academy
 *
 * Submisison 4 Aplikasi Movie Catalogue UI/UX DATABASE
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 21/01/19.
 */
public interface BaseView {
    void showLoading();

    void dismissLoading();

    void loadContentError();

    void memoryRelease();
}
