package com.dicoding.kailani.submission.moviecatalogue.network.response;

import com.dicoding.kailani.submission.moviecatalogue.model.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Dicoding Academy
 *
 * Submisison 4 Aplikasi Movie Catalogue UI/UX DATABASE
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 21/01/19.
 */
public class ResponseMovie {
    @SerializedName("page")
    protected int page;
    @SerializedName("total_results")
    private int totalResult;
    @SerializedName("total_pages")
    private int totalPage;
    @SerializedName("results")
    private List<Movie> mMovieList;

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Movie> getMovieDetailList() {
        return mMovieList;
    }

    public void setMovieDetailList(List<Movie> movieList) {
        mMovieList = movieList;
    }
}
