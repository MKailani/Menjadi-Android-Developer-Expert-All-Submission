package com.dicoding.kailani.submission.moviecatalogue.view.activity.iview;

import android.os.Bundle;

import com.dicoding.kailani.submission.moviecatalogue.model.Movie;
import com.dicoding.kailani.submission.moviecatalogue.network.response.ResponseMovie;
import com.dicoding.kailani.submission.moviecatalogue.view.BaseView;


/**
 * Dicoding Academy
 *
 * Final Project Aplikasi Movie Catalogue
 * Menjadi Android Developer Expert (MADE)
 *
 * Created by kheys on 28/01/19.
 */
public interface GeneralView extends BaseView {
    void setupRecyclerView(Bundle saveInstance);

    void showMovie(ResponseMovie responseMovie);

    void goToNextActivity(Movie movie);

    void setupToolbar(String text);

    void setupListener();
    void refreshContent();
}
