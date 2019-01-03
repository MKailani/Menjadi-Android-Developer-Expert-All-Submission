package com.dicoding.kailani.submission.apppencarianfilm.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dicoding.kailani.submission.apppencarianfilm.R;
import com.dicoding.kailani.submission.apppencarianfilm.model.MovieDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Khay on 03/01/19.
 */
public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<MovieDetail> mDataset = new ArrayList<>();

    public MoviesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addList(List<MovieDetail> data){
        mDataset.addAll(data);
        this.notifyDataSetChanged();
    }

    public void clearList(){
        mDataset.clear();
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_item_movie,viewGroup,false) ;

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MovieViewHolder movieVH = (MovieViewHolder)viewHolder;
        MovieDetail movie = mDataset.get(position);


        movieVH.tvTitle.setText(movie.getTitle());
        movieVH.tvDescription.setText(movie.getOverview());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    protected class MovieViewHolder extends BaseViewHolder{

        @BindView(R.id.iv_thumbnails)
        protected ImageView ivThumbnails;

        @BindView(R.id.tv_title)
        protected TextView tvTitle;

        @BindView(R.id.tv_description)
        protected TextView tvDescription;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}