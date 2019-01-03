package com.dicoding.kailani.submission.apppencarianfilm.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.dicoding.kailani.submission.apppencarianfilm.R;
import com.dicoding.kailani.submission.apppencarianfilm.model.MovieDetail;
import com.dicoding.kailani.submission.apppencarianfilm.utils.Utils;
import com.dicoding.kailani.submission.apppencarianfilm.view.adapter.GenreAdapter;

import butterknife.BindView;

public class DetailMoviesActivity extends BaseActivity implements DetailMovieView, SwipeRefreshLayout.OnRefreshListener {
    // TAG
    public static final String TAG = DetailMoviesActivity.class.getSimpleName();

    public static final String EXTRA_MOVIE_DETAIL = "extra:movie_detail";

    @BindView(R.id.img_item_photo)
    protected ImageView ivItemPhoto;

    @BindView(R.id.tv_title)
    protected TextView tvTitle;

    @BindView(R.id.tv_description)
    protected TextView tvDesc;

    @BindView(R.id.tv_rating)
    protected TextView tvRating;

    @BindView(R.id.tv_duration)
    protected TextView tvDuration;

    @BindView(R.id.tv_language)
    protected TextView tvLanguage;

    @BindView(R.id.tv_release)
    protected TextView tvRelease;

    @BindView(R.id.tv_overview)
    protected TextView tvOverview;

    @BindView(R.id.rv_genre)
    protected RecyclerView rvGenre;

    @BindView(R.id.srl_main_wrapper)
    protected SwipeRefreshLayout srlRefresh;

    private LinearLayoutManager mLinearLayoutManager;
    private MovieDetail mMovieDetail;
    private GenreAdapter mGenreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movies);

        setupListener();
        refreshContent();
    }

    @Override
    public void initView() {
        Log.i(TAG,"Method Name : "+Utils.getMethodName());
        Intent intent = getIntent();
        if(intent !=null){
            mMovieDetail = intent.getParcelableExtra(EXTRA_MOVIE_DETAIL);
            if(mMovieDetail !=null){

                Utils.loadImage(ivItemPhoto,mMovieDetail.getPosterPath());

                tvTitle.setText(mMovieDetail.getTitle());
                tvDesc.setText(mMovieDetail.getOriginalTitle());
                tvRating.setText(toText(mMovieDetail.getVoteAverage()));
                tvDuration.setText(toText(mMovieDetail.getVoteCount()));
                tvLanguage.setText(mMovieDetail.getOriginalLanguage());
                tvRelease.setText(toText(mMovieDetail.getReleaseDate()));
                tvOverview.setText(mMovieDetail.getOverview());
                mGenreAdapter.addList(mMovieDetail.getGenreIds());
            }
        }
    }

    @Override
    public void setupRecyclerview() {
        Log.i(TAG,"Method Name : "+Utils.getMethodName());
        if(rvGenre !=null){
            mLinearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                                        LinearLayoutManager.HORIZONTAL,
                                        false);
            mGenreAdapter = new GenreAdapter(getApplicationContext(), this);
            rvGenre.setLayoutManager(mLinearLayoutManager);
            rvGenre.setAdapter(mGenreAdapter);
        }

    }

    @Override
    public void showLoading() {
        Log.i(TAG,"show loading");
        if(srlRefresh !=null)
            srlRefresh.setRefreshing(true);
    }

    @Override
    public void dismissLoading() {
        Log.i(TAG,"dismiss loading");
        if(srlRefresh !=null)
            srlRefresh.setRefreshing(false);
    }

    @Override
    public void loadContentError() {
        Log.i(TAG,"Content Error");
    }

    @Override
    public void refreshContent() {
        Log.i(TAG,"Refresh Content");
        showLoading();

    }

    @Override
    public void setupListener() {
        if(srlRefresh !=null){
            srlRefresh.setOnRefreshListener(this);
        }
    }

    @Override
    public void memoryRelease() {
        if(mLinearLayoutManager !=null)
            mLinearLayoutManager = null;

        if(mMovieDetail !=null)
            mMovieDetail = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        memoryRelease();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissLoading();
                setupRecyclerview();
                initView();
            }
        },500);
    }
}
