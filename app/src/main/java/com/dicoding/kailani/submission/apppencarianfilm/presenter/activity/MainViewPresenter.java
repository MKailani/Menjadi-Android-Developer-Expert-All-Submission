package com.dicoding.kailani.submission.apppencarianfilm.presenter.activity;


import android.support.annotation.NonNull;

import com.dicoding.kailani.submission.apppencarianfilm.BuildConfig;
import com.dicoding.kailani.submission.apppencarianfilm.network.response.ResponseMovie;
import com.dicoding.kailani.submission.apppencarianfilm.network.RestClient;
import com.dicoding.kailani.submission.apppencarianfilm.view.activity.GeneralView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Dicoding Academy
 * Submission 1 - Aplikasi Pencarian Film
 *
 * Created by Kailani on 04/01/19.
 */
public class MainViewPresenter {
    private GeneralView mView;
    private RestClient mRestClient;
    private final static String API = BuildConfig.API_KEY;
    private final static String LANGUGAGE = "en-US";

    public MainViewPresenter(GeneralView view) {
        this.mView = view;
        this.mRestClient = new RestClient();
    }

    public void getAllMovies(int page) {
        mView.showLoading();
        mRestClient.getApiService().getAllMovies(API, LANGUGAGE, page).enqueue(new Callback<ResponseMovie>() {
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

    public void searchMovie(int page, String textSearch) {

        mView.showLoading();
        mRestClient.getApiService().doSearchMovies(API, LANGUGAGE, page, textSearch).enqueue(new Callback<ResponseMovie>() {
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
