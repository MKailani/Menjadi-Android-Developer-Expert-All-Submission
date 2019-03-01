package com.dicoding.kailani.submission.moviecatalogue.network.response;

import com.dicoding.kailani.submission.moviecatalogue.model.Genres;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Dicoding Academy
 *
 * Submisison 4 Aplikasi Movie Catalogue UI/UX DATABASE
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 21/01/19.
 */
public class ResponseGenres {
    @SerializedName("genres")
    private List<Genres> genres = new ArrayList<>();

    public List<Genres> getGenres() {
        return genres;
    }
}
