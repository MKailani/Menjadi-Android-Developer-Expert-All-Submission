package com.dicoding.kailani.submission.apppencarianfilm.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dicoding.kailani.submission.apppencarianfilm.R;
import com.dicoding.kailani.submission.apppencarianfilm.model.Genres;
import com.dicoding.kailani.submission.apppencarianfilm.model.Movie;
import com.dicoding.kailani.submission.apppencarianfilm.utils.Utils;
import com.dicoding.kailani.submission.apppencarianfilm.view.adapter.GenreAdapter;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Dicoding Academy
 * Submission 2 - Aplikasi Pencarian Film UI-UX
 *
 * Created by Kailani on 06/01/19 -> Submssion 2
 */
public class DetailMoviesActivity extends BaseActivity implements DetailMovieView, SwipeRefreshLayout.OnRefreshListener {
    // TAG
    public static final String TAG = DetailMoviesActivity.class.getSimpleName();

    public static final String EXTRA_IS_LOADING = "extra:is_loading";
    public static final String EXTRA_MOVIE_DETAIL = "extra:movie_detail";
    public static final String EXTRA_TITLE = "extra:title";

    @BindView(R.id.img_item_photo)
    protected CircleImageView ivItemPhoto;

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

    @BindView(R.id.tv_no_genre)
    protected TextView tvNoGenre;

    @BindView(R.id.rv_genre)
    protected RecyclerView rvGenre;

    @BindView(R.id.srl_main_wrapper)
    protected SwipeRefreshLayout srlRefresh;

    @BindView(R.id.sv_wrapper)
    protected ScrollView svWrapper;

    @BindView(R.id.top_toolbar)
    protected Toolbar toolbar;

    private LinearLayoutManager mLinearLayoutManager;
    private Movie mMovie;
    private GenreAdapter mGenreAdapter;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movies);

        // setup view
        setupToolbar(getIntent().getStringExtra(EXTRA_TITLE));
        setupListener();

        // Check Orientation Change
        if (savedInstanceState == null)
            refreshContent();
        else {
            if (savedInstanceState.getBoolean(EXTRA_IS_LOADING)) {
                refreshContent();
            } else {
                setupRecyclerview();
                initView();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXTRA_MOVIE_DETAIL, mMovie);
        outState.putBoolean(EXTRA_IS_LOADING, srlRefresh.isRefreshing());
    }

    @Override
    public void initView() {
        Log.i(TAG, "Method Name : " + Utils.getMethodName());
        Intent intent = getIntent();
        if (intent != null) {
            mMovie = intent.getParcelableExtra(EXTRA_MOVIE_DETAIL);
            if (mMovie != null) {

                Utils.loadImage(ivItemPhoto, mMovie.getPosterPath());

                tvTitle.setText(mMovie.getTitle());
                tvDesc.setText(mMovie.getOriginalTitle());
                tvRating.setText(toText(mMovie.getVoteAverage()));
                tvDuration.setText(toText(mMovie.getVoteCount()));
                tvLanguage.setText(mMovie.getOriginalLanguage());
                tvRelease.setText(toReleaseDate(mMovie.getReleaseDate()));
                tvOverview.setText(TextUtils.isEmpty(mMovie.getOverview()) ?
                        getString(R.string.text_no_overview) : mMovie.getOverview());
                List<Genres> data = Genres.findGenre(mMovie.getGenreIds());
                mGenreAdapter.addList(data);
                tvNoGenre.setVisibility(data.size() == 0 ? View.VISIBLE : View.GONE);
            }
        }
    }

    @Override
    public void setupRecyclerview() {
        Log.i(TAG, "Method Name : " + Utils.getMethodName());
        if (rvGenre != null) {
            mLinearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false);
            mGenreAdapter = new GenreAdapter();
            rvGenre.setLayoutManager(mLinearLayoutManager);
            rvGenre.setAdapter(mGenreAdapter);
        }

    }

    @Override
    public void showLoading() {
        Log.i(TAG, "show loading");
        if (srlRefresh != null)
            srlRefresh.setRefreshing(true);
    }

    @Override
    public void dismissLoading() {
        Log.i(TAG, "dismiss loading");
        if (srlRefresh != null)
            srlRefresh.setRefreshing(false);
    }

    @Override
    public void loadContentError() {
        Log.i(TAG, "Content Error");
    }

    @Override
    public void refreshContent() {
        Log.i(TAG, "Refresh Content");
        showLoading();
        onRefresh();
    }

    @Override
    public void setupListener() {
        if (srlRefresh != null) {
            srlRefresh.setOnRefreshListener(this);
        }
    }

    @Override
    public void setupToolbar(String text) {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(text);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
            default:
                break;
        }

        return true;

    }

    @Override
    public void memoryRelease() {
        if (mLinearLayoutManager != null)
            mLinearLayoutManager = null;

        if (mMovie != null)
            mMovie = null;

        if (mGenreAdapter != null) {
            mGenreAdapter.clearList();
            mGenreAdapter = null;
        }

        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        memoryRelease();
    }

    @Override
    public void onRefresh() {
        svWrapper.setVisibility(View.GONE);
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                svWrapper.setVisibility(View.VISIBLE);
                dismissLoading();
                setupRecyclerview();
                initView();
            }
        }, 1500);
    }
}
