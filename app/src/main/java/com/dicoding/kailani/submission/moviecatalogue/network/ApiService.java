package com.dicoding.kailani.submission.moviecatalogue.network;

import com.dicoding.kailani.submission.moviecatalogue.network.response.ResponseMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Dicoding Academy
 *
 * Submisison 4 Aplikasi Movie Catalogue UI/UX DATABASE
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 21/01/19.
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
