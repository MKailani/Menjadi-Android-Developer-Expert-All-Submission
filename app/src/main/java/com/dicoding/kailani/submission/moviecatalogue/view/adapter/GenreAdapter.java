package com.dicoding.kailani.submission.moviecatalogue.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dicoding.kailani.submission.moviecatalogue.R;
import com.dicoding.kailani.submission.moviecatalogue.model.Genres;
import com.dicoding.kailani.submission.moviecatalogue.view.adapter.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Dicoding Academy
 *
 * Final Project Aplikasi Movie Catalogue
 * Menjadi Android Developer Expert (MADE)
 *
 * Created by kheys on 28/01/19.
 */
public class GenreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Genres> mGenreList = new ArrayList<>();

    public void addList(List<Genres> genres) {
        this.mGenreList.addAll(genres);
        this.notifyDataSetChanged();
    }

    public void clearList() {
        this.mGenreList.clear();
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.column_item_genre, viewGroup, false);
        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        GenreViewHolder genreVH = (GenreViewHolder) viewHolder;
        Genres genre = mGenreList.get(position);

        genreVH.mTvGenre.setText(genre.getName());

    }

    @Override
    public int getItemCount() {
        return mGenreList.size();
    }

    class GenreViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_genre)
        TextView mTvGenre;

        GenreViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
