package com.dicoding.kailani.submission.apppencarianfilm.view.activity;

import android.os.Bundle;

import com.dicoding.kailani.submission.apppencarianfilm.model.Movie;
import com.dicoding.kailani.submission.apppencarianfilm.model.MovieDetail;
import com.dicoding.kailani.submission.apppencarianfilm.view.BaseView;

public interface MainView extends BaseView {
    void setupRecyclerView(Bundle saveInstance);
    void showMovie(Movie movie);
    void goToNextActivity(MovieDetail movieDetail);
}
