package com.dicoding.kailani.submission.moviecatalogue.presenter.fragment;

import android.support.annotation.NonNull;

import com.dicoding.kailani.submission.moviecatalogue.BuildConfig;
import com.dicoding.kailani.submission.moviecatalogue.network.RestClient;
import com.dicoding.kailani.submission.moviecatalogue.network.response.ResponseMovie;
import com.dicoding.kailani.submission.moviecatalogue.view.activity.iview.GeneralView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Dicoding Academy
 *
 * Final Project Aplikasi Movie Catalogue
 * Menjadi Android Developer Expert (MADE)
 *
 * Created by kheys on 28/01/19.
 */
public class NowPlayingViewPresenter {
    private final GeneralView mView;
    private final RestClient mRestClient;
    private final static String API = BuildConfig.API_KEY;
    private final static String LANGUGAGE = "en-US";

    public NowPlayingViewPresenter(GeneralView view) {
        this.mView = view;
        this.mRestClient = new RestClient();
    }

    public void getAllNowPlayingMovies(int page) {
        mView.showLoading();
        mRestClient.getApiService().getNowPlayingMovies(API, LANGUGAGE, page).enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(@NonNull Call<ResponseMovie> call, @NonNull Response<ResponseMovie> response) {
                System.gc();
                mView.dismissLoading();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        mView.showMovie(response.body());

                    }
                } else {
                    mView.loadContentError();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseMovie> call, @NonNull Throwable t) {
                mView.dismissLoading();
                mView.loadContentError();
            }
        });
    }
}
