package com.dicoding.kailani.submission.apppencarianfilm.view.activity;

import android.os.Bundle;

import com.dicoding.kailani.submission.apppencarianfilm.network.response.ResponseMovie;
import com.dicoding.kailani.submission.apppencarianfilm.model.Movie;
import com.dicoding.kailani.submission.apppencarianfilm.view.BaseView;

/**
 * Dicoding Academy
 * Submission 1 - Aplikasi Pencarian Film
 *
 * Created by Kailani on 04/01/19.
 */
public interface MainView extends BaseView {
    void setupRecyclerView(Bundle saveInstance);

    void showMovie(ResponseMovie responseMovie);

    void goToNextActivity(Movie movie);

    void setupToolbar(String text);

    void setupListener();
}
