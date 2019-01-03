package com.dicoding.kailani.submission.apppencarianfilm.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dicoding.kailani.submission.apppencarianfilm.R;
import com.dicoding.kailani.submission.apppencarianfilm.model.Movie;
import com.dicoding.kailani.submission.apppencarianfilm.model.MovieDetail;
import com.dicoding.kailani.submission.apppencarianfilm.presenter.MainViewPresenter;
import com.dicoding.kailani.submission.apppencarianfilm.utils.Utils;
import com.dicoding.kailani.submission.apppencarianfilm.view.adapter.MoviesAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnTextChanged;

public class MainActivity extends BaseActivity implements MainView {
    // TAG
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_LIST = "extra:list";

    @BindView(R.id.tv_description)
    TextView tvDesc;

    @BindView(R.id.edt_search)
    EditText edtSearch;

    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;

    private LinearLayoutManager llManager;
    private MainViewPresenter mMainViewPresenter;
    private MoviesAdapter adapter;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainViewPresenter = new MainViewPresenter(this);
        mHandler = new Handler();

        // Setup Recyclerview
        setupRecyclerView(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_LIST, new ArrayList<>(adapter.getDataset()));

    }

    @Override
    public void setupRecyclerView(Bundle saveInstance) {
        if (rvMovies != null) {
            llManager = new LinearLayoutManager(getApplicationContext(),
                    LinearLayoutManager.VERTICAL,
                    false);
            adapter = new MoviesAdapter(getApplicationContext(), this);
            rvMovies.setLayoutManager(llManager);
            rvMovies.setAdapter(adapter);

            if (saveInstance == null) {
                mMainViewPresenter.getAllMovies();
            } else {
                ArrayList<MovieDetail> list = saveInstance.getParcelableArrayList(EXTRA_LIST);
                adapter.addList(list);
                tvDesc.setVisibility(adapter.getItemCount() == 0 ?
                        View.VISIBLE : View.GONE);
            }

            edtSearch.post(new Runnable() {
                @Override
                public void run() {
                    edtSearch.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(final Editable s) {
                            if (mHandler != null) {
                                mHandler.removeCallbacksAndMessages(null);

                                mHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (!TextUtils.isEmpty(s.toString())) {
                                            mMainViewPresenter.searchMovie(s.toString());
                                        } else {
                                            mMainViewPresenter.getAllMovies();
                                        }
                                    }
                                }, 1000);

                            }
                        }
                    });
                }
            });
        }
    }


    @Override
    public void showMovie(Movie movie) {
        if (adapter != null) {

            if (adapter.getItemCount() > 0)
                adapter.clearList();

            adapter.addList(movie.getMovieDetailList());
            tvDesc.setVisibility(movie.getMovieDetailList().size() == 0 ?
                    View.VISIBLE : View.GONE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    rvMovies.smoothScrollToPosition(0);
                }
            },500);
        }
    }

    @Override
    public void goToNextActivity(MovieDetail movieDetail) {
        Log.i(TAG, "Method Name : " + Utils.getMethodName());

        Intent intent = new Intent(MainActivity.this, DetailMoviesActivity.class);
        intent.putExtra(DetailMoviesActivity.EXTRA_MOVIE_DETAIL, movieDetail);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        Log.i(TAG, "show loading");
        if (pbLoading != null)
            pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoading() {
        Log.i(TAG, "dismiss loading");
        if (pbLoading != null)
            pbLoading.setVisibility(View.GONE);

    }

    @Override
    public void loadContentError() {
        Log.i(TAG, "load content error");
        tvDesc.setVisibility(adapter.getItemCount() == 0 ? View.VISIBLE
                : View.GONE);
    }

    @Override
    public void memoryRelease() {
        if (llManager != null) llManager = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        memoryRelease();
    }
}
