package com.dicoding.kailani.submission.apppencarianfilm.network.response;

import com.dicoding.kailani.submission.apppencarianfilm.model.Genres;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Dicoding Academy
 * Submission 1 - Aplikasi Pencarian Film
 *
 * Created by Kailani on 04/01/19.
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
