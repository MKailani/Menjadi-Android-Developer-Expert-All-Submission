package com.dicoding.kailani.submission.apppencarianfilm.network;

import com.dicoding.kailani.submission.apppencarianfilm.network.response.ResponseMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Dicoding Academy
 * Submission 2 - Aplikasi Pencarian Film UI-UX
 *
 * Created by Kailani on 06/01/19 -> Submssion 2
 */
public interface ApiService {
    @GET("search/movie")
    Call<ResponseMovie> doSearchMovies(@Query("api_key") String apiKey,
                                       @Query("language") String language,
                                       @Query("page") int page,
                                       @Query("query") String searchText);

    @GET("discover/movie")
    Call<ResponseMovie> getAllMovies(@Query("api_key") String apiKey,
                                     @Query("language") String language,
                                     @Query("page") int page);

    @GET("movie/now_playing")
    Call<ResponseMovie> getNowPlayingMovies(@Query("api_key") String apiKey,
                                     @Query("language") String language,
                                     @Query("page") int page);

    @GET("movie/upcoming")
    Call<ResponseMovie> getUpCommingMovies(@Query("api_key") String apiKey,
                                     @Query("language") String language,
                                     @Query("page") int page);
}
