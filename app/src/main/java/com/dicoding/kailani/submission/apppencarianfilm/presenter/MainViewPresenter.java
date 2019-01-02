package com.dicoding.kailani.submission.apppencarianfilm.presenter;


import com.dicoding.kailani.submission.apppencarianfilm.BuildConfig;
import com.dicoding.kailani.submission.apppencarianfilm.model.Movie;
import com.dicoding.kailani.submission.apppencarianfilm.network.RestClient;
import com.dicoding.kailani.submission.apppencarianfilm.network.response.BaseResponse;
import com.dicoding.kailani.submission.apppencarianfilm.view.activity.MainView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewPresenter {
    private MainView mView;
    private RestClient mRestClient;
    private final static String API = BuildConfig.API_KEY;
    private final static String LANGUGAGE = "en-US";


    public MainViewPresenter(MainView view) {
        this.mView = view;
        this.mRestClient = new RestClient();

    }

    public void getAllMovies(){
        mView.showLoading();
        mRestClient.getApiService().getAllMovies(API,LANGUGAGE).enqueue(new Callback<BaseResponse<Movie>>() {
            @Override
            public void onResponse(Call<BaseResponse<Movie>> call, Response<BaseResponse<Movie>> response) {
                mView.dismissLoading();
                if (response.isSuccessful()) {
                    if(response.body() != null &&
                            response.body().getCode() == 200){
                        mView.showMovie(response.body().getContent());

                    }
                }else{
                    mView.loadContentError();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Movie>> call, Throwable t) {
                mView.dismissLoading();
                mView.loadContentError();
            }
        });

    }

    public void searchMovie(String textSearch){
        mView.showLoading();
        mRestClient.getApiService().doSearchMovies(API,LANGUGAGE,textSearch).enqueue(new Callback<BaseResponse<Movie>>() {
            @Override
            public void onResponse(Call<BaseResponse<Movie>> call, Response<BaseResponse<Movie>> response) {
                mView.dismissLoading();
                if (response.isSuccessful()) {
                    if(response.body() != null &&
                            response.body().getCode() == 200){
                        mView.showMovie(response.body().getContent());

                    }
                }else{
                    mView.loadContentError();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Movie>> call, Throwable t) {
                mView.dismissLoading();
                mView.loadContentError();
            }
        });

    }
}
