package com.dicoding.kailani.submission.apppencarianfilm.network;

import com.dicoding.kailani.submission.apppencarianfilm.model.Movie;
import com.dicoding.kailani.submission.apppencarianfilm.network.response.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search/movie")
    Call<BaseResponse<Movie>> doSearchMovies(@Query("api_key") String  apiKey,
                                        @Query("language") String language,
                                        @Query("query") String searchText);

    @GET("discover/movie")
    Call<BaseResponse<Movie>> getAllMovies(@Query("api_key") String  apiKey,
                                        @Query("language") String language);
}
