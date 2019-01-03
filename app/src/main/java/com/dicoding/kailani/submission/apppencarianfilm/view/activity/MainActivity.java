package com.dicoding.kailani.submission.apppencarianfilm.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.dicoding.kailani.submission.apppencarianfilm.R;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.edt_search)
    protected EditText edtSearch;

    @BindView(R.id.pb_loading)
    protected ProgressBar pbLoading;

    @BindView(R.id.rv_movies)
    protected RecyclerView rvMovies;

    private LinearLayoutManager llManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false);




    }

}
