package com.dicoding.kailani.submission.apppencarianfilm.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dicoding.kailani.submission.apppencarianfilm.R;
import com.dicoding.kailani.submission.apppencarianfilm.model.Genres;
import com.dicoding.kailani.submission.apppencarianfilm.view.adapter.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Dicoding Academy
 * Submission 1 - Aplikasi Pencarian Film
 *
 * Created by Kailani on 04/01/19.
 */
public class GenreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Genres> mGenreList = new ArrayList<>();

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
                .inflate(R.layout.row_item_genre, viewGroup, false);
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
