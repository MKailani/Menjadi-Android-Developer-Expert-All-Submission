package com.dicoding.kailani.submission.apppencarianfilm.network.response;

import com.dicoding.kailani.submission.apppencarianfilm.model.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Dicoding Academy
 * Submission 2 - Aplikasi Pencarian Film UI-UX
 *
 * Created by Kailani on 06/01/19 -> Submssion 2
 */
public class ResponseMovie {
    @SerializedName("page")
    private int page;
    @SerializedName("total_results")
    private int totalResult;
    @SerializedName("total_pages")
    private int totalPage;
    @SerializedName("results")
    private List<Movie> mMovieList;

    public int getTotalResult() {
        return totalResult;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public List<Movie> getMovieDetailList() {
        return mMovieList;
    }

}
