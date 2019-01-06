package com.dicoding.kailani.submission.apppencarianfilm.network.response;

import com.dicoding.kailani.submission.apppencarianfilm.model.Genres;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Dicoding Academy
 * Submission 2 - Aplikasi Pencarian Film UI-UX
 *
 * Created by Kailani on 06/01/19 -> Submssion 2
 */
public class ResponseGenres {
    @SerializedName("genres")
    private List<Genres> genres = new ArrayList<>();

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }
}
