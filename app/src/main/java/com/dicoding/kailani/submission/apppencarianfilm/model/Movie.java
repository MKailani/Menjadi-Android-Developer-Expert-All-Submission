package com.dicoding.kailani.submission.apppencarianfilm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {
    @SerializedName("page")
    private int page;
    @SerializedName("total_results")
    private int totalResult;
    @SerializedName("total_pages")
    private int totalPage;
    @SerializedName("results")
    private List<MovieDetail> mMovieDetailList;

    public int getPage() {
        return page;
    }

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

    public List<MovieDetail> getMovieDetailList() {
        return mMovieDetailList;
    }

    public void setMovieDetailList(List<MovieDetail> movieDetailList) {
        mMovieDetailList = movieDetailList;
    }
}
