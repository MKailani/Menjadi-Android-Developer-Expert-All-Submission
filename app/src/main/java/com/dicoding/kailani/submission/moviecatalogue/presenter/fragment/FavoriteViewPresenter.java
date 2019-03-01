package com.dicoding.kailani.submission.moviecatalogue.presenter.fragment;

import com.dicoding.kailani.submission.moviecatalogue.App;
import com.dicoding.kailani.submission.moviecatalogue.model.Movie;
import com.dicoding.kailani.submission.moviecatalogue.network.response.ResponseMovie;
import com.dicoding.kailani.submission.moviecatalogue.view.activity.GeneralView;

import java.util.List;

/**
 * Dicoding Academy
 *
 * Submisison 4 Aplikasi Movie Catalogue UI/UX DATABASE
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 21/01/19.
 */
public class FavoriteViewPresenter {
    private final GeneralView mView;

    public FavoriteViewPresenter(GeneralView view) {
        this.mView = view;
    }

    public void getAllUpFavoriteMovie(int page) {
        mView.showLoading();
        try {
            // Pagging Movies
            int offset = (page - 1) * 20;

            List<Movie> allMovieList = App.getDatabaseOpen().getFavoriteMovie().getAllData(20,offset);

            ResponseMovie responseMovie = new ResponseMovie();
            responseMovie.setPage(page);
            responseMovie.setMovieDetailList(allMovieList);
            responseMovie.setTotalResult(allMovieList.size());
            responseMovie.setTotalPage(page);

            mView.dismissLoading();

            mView.showMovie(responseMovie);

        } catch (Exception e) {
            mView.dismissLoading();
            e.printStackTrace();
        }

    }
}
