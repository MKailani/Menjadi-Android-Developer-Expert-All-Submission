package com.dicoding.kailani.submission.moviecatalogue.view.activity;


import com.dicoding.kailani.submission.moviecatalogue.view.BaseView;

/**
 * Dicoding Academy
 *
 * Submisison 4 Aplikasi Movie Catalogue UI/UX DATABASE
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 21/01/19.
 */
interface DetailMovieView extends BaseView {
    void initView();

    void setupRecyclerview();

    void refreshContent();

    void setupListener();

    void setupToolbar(String text);

    void doActionFavorite();

    void addFavoriteDb(boolean isFavorite);
}
