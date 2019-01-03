package com.dicoding.kailani.submission.apppencarianfilm.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dicoding.kailani.submission.apppencarianfilm.R;
import com.dicoding.kailani.submission.apppencarianfilm.view.activity.DetailMovieView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by kheys on 04/01/19.
 */
public class GenreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int[] mGenreList;
    private Context mContext;
    private DetailMovieView mView;

    public GenreAdapter(Context context, DetailMovieView view) {
        mContext = context;
        mView = view;
    }

    public void addList(int[] mGenreList){
        this.mGenreList = mGenreList;
        this.notifyDataSetChanged();
    }

    public void clearList(){
        this.mGenreList = null;
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
        GenreViewHolder genreVH =  (GenreViewHolder) viewHolder;
        int genre = mGenreList[position];

    }

    @Override
    public int getItemCount() {
        return mGenreList.length;
    }

    public class GenreViewHolder extends BaseViewHolder{
        @BindView(R.id.tv_description)
        TextView mTextView;

        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
