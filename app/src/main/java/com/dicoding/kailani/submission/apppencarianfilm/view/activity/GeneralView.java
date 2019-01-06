package com.dicoding.kailani.submission.apppencarianfilm.view.activity;

import android.os.Bundle;

import com.dicoding.kailani.submission.apppencarianfilm.network.response.ResponseMovie;
import com.dicoding.kailani.submission.apppencarianfilm.model.Movie;
import com.dicoding.kailani.submission.apppencarianfilm.view.BaseView;

/**
 * Dicoding Academy
 * Submission 2 - Aplikasi Pencarian Film UI-UX
 *
 * Created by Kailani on 06/01/19 -> Submssion 2
 */
public interface GeneralView extends BaseView {
    void setupRecyclerView(Bundle saveInstance);

    void showMovie(ResponseMovie responseMovie);

    void goToNextActivity(Movie movie);

    void setupToolbar(String text);

    void setupListener();
    void refreshContent();
}
