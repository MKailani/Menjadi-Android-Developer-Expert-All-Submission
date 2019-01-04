package com.dicoding.kailani.submission.apppencarianfilm.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dicoding.kailani.submission.apppencarianfilm.R;
import com.dicoding.kailani.submission.apppencarianfilm.model.Movie;
import com.dicoding.kailani.submission.apppencarianfilm.network.response.ResponseMovie;
import com.dicoding.kailani.submission.apppencarianfilm.presenter.MainViewPresenter;
import com.dicoding.kailani.submission.apppencarianfilm.utils.Utils;
import com.dicoding.kailani.submission.apppencarianfilm.view.adapter.MoviesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Dicoding Academy
 * Submission 1 - Aplikasi Pencarian Film
 *
 * Created by Kailani on 04/01/19.
 */
public class MainActivity extends BaseActivity implements MainView {
    // TAG
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_LIST = "extra:list";
    public static final String EXTRA_COUNTER = "extra:counter";
    public static final String EXTRA_LAST_ITEM = "extra:last_item";
    public static final String EXTRA_STATE_SCROLL_POSITION = "extra:state_scroll_position";

    @BindView(R.id.tv_description)
    protected TextView tvDesc;

    @BindView(R.id.edt_search)
    protected EditText edtSearch;

    @BindView(R.id.pb_loading)
    protected ProgressBar pbLoading;

    @BindView(R.id.rv_movies)
    protected RecyclerView rvMovies;

    @BindView(R.id.btn_search)
    protected Button btnSearch;

    private LinearLayoutManager llManager;
    private MainViewPresenter mMainViewPresenter;
    private MoviesAdapter adapter;
    private Handler mHandler;
    private int page =1;
    private int counter = 1;
    private int totalItemCount;
    private int lastVisibleItem;
    private int lastItemCounter = 0;
    private boolean isLoading = false;
    private List<Movie> tempList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainViewPresenter = new MainViewPresenter(this);
        mHandler = new Handler();

        // Setup View
        setupToolbar(getString(R.string.title_bar_search_movie));
        setupRecyclerView(savedInstanceState);
        setupListener();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(adapter.getItemCount() > 0){
            adapter.removeProgressBar();
            outState.putParcelableArrayList(EXTRA_LIST, new ArrayList<>(adapter.getDataset()));
            outState.putInt(EXTRA_COUNTER, counter);
            outState.putInt(EXTRA_LAST_ITEM, lastItemCounter);
            outState.putInt(EXTRA_STATE_SCROLL_POSITION, llManager.findFirstCompletelyVisibleItemPosition());
        }else{
            if(tempList !=null)
                outState.putParcelableArrayList(EXTRA_LIST, new ArrayList<>(tempList));

            outState.putInt(EXTRA_COUNTER, 1);
            outState.putInt(EXTRA_LAST_ITEM, lastItemCounter);
            outState.putInt(EXTRA_STATE_SCROLL_POSITION, llManager.findFirstCompletelyVisibleItemPosition());
        }

    }

    @Override
    public void setupRecyclerView(Bundle saveInstance) {
        if (rvMovies != null) {
            llManager = new LinearLayoutManager(getApplicationContext(),
                    LinearLayoutManager.VERTICAL,
                    false);
            adapter = new MoviesAdapter(this);
            rvMovies.setLayoutManager(llManager);
            rvMovies.setAdapter(adapter);

            if (saveInstance == null) {
                mMainViewPresenter.getAllMovies(page);
            } else {
                ArrayList<Movie> list = saveInstance.getParcelableArrayList(EXTRA_LIST);
                counter = saveInstance.getInt(EXTRA_COUNTER);
                lastItemCounter = saveInstance.getInt(EXTRA_LAST_ITEM);
                if(list !=null)
                    adapter.addList(list);
                tvDesc.setVisibility(adapter.getItemCount() == 0 ?
                        View.VISIBLE : View.GONE);
                llManager.scrollToPosition(saveInstance.getInt(EXTRA_STATE_SCROLL_POSITION));
            }

        }
    }




    @Override
    public void showMovie(ResponseMovie responseMovie) {
        if (adapter != null) {

            this.tempList = responseMovie.getMovieDetailList();

            // Check Maximum Item Count
            if( adapter.getItemCount() <= responseMovie.getTotalResult()){
                adapter.addList(responseMovie.getMovieDetailList());

                if(adapter.getItemCount() >= responseMovie.getTotalResult()){
                    adapter.removeProgressBar();

                    if(adapter.getItemCount() > 0)
                        Toast.makeText(this, R.string.message_warning_all_movie_loaded, Toast.LENGTH_LONG).show();
                }
            }

            // Check No Data
            tvDesc.setVisibility(responseMovie.getMovieDetailList().size() == 0 ?
                    View.VISIBLE : View.GONE);

            // Check Maximum Counter Page
            if(counter <= responseMovie.getTotalPage())
                counter = (adapter.getItemCount() / 20) + 1;

            lastItemCounter = adapter.getItemCount();

            // First Load Scroll top
            if(page == 1){
                mHandler.removeCallbacksAndMessages(null);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(rvMovies !=null)
                            rvMovies.smoothScrollToPosition(0);
                    }
                }, 500);
            }
        }
    }

    @Override
    public void goToNextActivity(Movie movie) {
        Log.i(TAG, "Method Name : " + Utils.getMethodName());

        Intent intent = new Intent(MainActivity.this, DetailMoviesActivity.class);
        intent.putExtra(DetailMoviesActivity.EXTRA_MOVIE_DETAIL, movie);
        intent.putExtra(DetailMoviesActivity.EXTRA_TITLE, movie.getTitle());
        startActivity(intent);
    }

    @Override
    public void setupToolbar(String text) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(text);
    }

    @Override
    public void setupListener() {
        // Button Search
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(edtSearch.getText().toString())) {
                    resetList();
                    mMainViewPresenter.searchMovie(page,edtSearch.getText().toString());
                } else {
                    Toast.makeText(MainActivity.this, R.string.warning_message_movie_empty_search, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Edit text Search
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
                                    if (TextUtils.isEmpty(s.toString())) {
                                        resetList();
                                        mMainViewPresenter.getAllMovies(page);
                                    }
                                }
                            }, 1000);

                        }
                    }
                });
            }
        });

        // Infinite Scrolling Recyclerview
        rvMovies.post(new Runnable() {
            @Override
            public void run() {
                rvMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                        totalItemCount = llManager.getItemCount();
                        lastVisibleItem = llManager.findLastVisibleItemPosition();
                        int visibleThreshold = 1;
                        if(lastItemCounter > 19 && !isLoading() && totalItemCount <= lastVisibleItem +visibleThreshold){
                            page = counter;
                            if (!TextUtils.isEmpty(edtSearch.getText().toString())) {
                                mMainViewPresenter.searchMovie(page,edtSearch.getText().toString());
                            } else {
                                mMainViewPresenter.getAllMovies(page);
                            }
                        }
                    }
                });
            }
        });

    }

    @Override
    public void showLoading() {
        Log.i(TAG, "show loading");
        if (pbLoading != null && page == 1)
            pbLoading.setVisibility(View.VISIBLE);

        this.isLoading = true;
    }

    @Override
    public void dismissLoading() {
        Log.i(TAG, "dismiss loading");
        if (pbLoading != null && page == 1)
            pbLoading.setVisibility(View.GONE);


        this.isLoading = false;
    }

    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public void loadContentError() {
        Log.i(TAG, "load content error");
        tvDesc.setVisibility(adapter.getItemCount() == 0 ? View.VISIBLE
                : View.GONE);
    }

    private void resetList(){
        this.page =1;
        this.counter =1;
        this.lastItemCounter = 0;
        this.totalItemCount = 0;
        this.lastVisibleItem = 0;
        if(adapter !=null){
            adapter.clearList();
        }
    }

    @Override
    public void memoryRelease() {
        if (llManager != null) llManager = null;
        if (mHandler != null) mHandler.removeCallbacksAndMessages(null);
        if (adapter != null) adapter = null;
        if (llManager != null) llManager = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        memoryRelease();
    }
}
