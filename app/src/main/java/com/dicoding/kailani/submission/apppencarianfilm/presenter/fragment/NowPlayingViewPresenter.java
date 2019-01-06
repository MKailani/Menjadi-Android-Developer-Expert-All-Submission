package com.dicoding.kailani.submission.apppencarianfilm.presenter.fragment;

import android.support.annotation.NonNull;

import com.dicoding.kailani.submission.apppencarianfilm.BuildConfig;
import com.dicoding.kailani.submission.apppencarianfilm.network.RestClient;
import com.dicoding.kailani.submission.apppencarianfilm.network.response.ResponseMovie;
import com.dicoding.kailani.submission.apppencarianfilm.view.activity.GeneralView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Dicoding Academy
 * Submission 2 - Aplikasi Pencarian Film UI-UX
 *
 * Created by Kailani on 06/01/19 -> Submssion 2
 */
public class NowPlayingViewPresenter {
    private GeneralView mView;
    private RestClient mRestClient;
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
