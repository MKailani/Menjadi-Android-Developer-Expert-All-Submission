package com.dicoding.kailani.submission.moviecatalogue.view.activity.iview;


import com.dicoding.kailani.submission.moviecatalogue.view.BaseView;

/**
 * Dicoding Academy
 *
 * Final Project Aplikasi Movie Catalogue
 * Menjadi Android Developer Expert (MADE)
 *
 * Created by kheys on 28/01/19.
 */
public interface DetailMovieView extends BaseView {
    void initView();

    void setupRecyclerview();

    void refreshContent();

    void setupListener();

    void setupToolbar(String text);

    void doActionFavorite();

    void addFavoriteDb(boolean isFavorite);
}
