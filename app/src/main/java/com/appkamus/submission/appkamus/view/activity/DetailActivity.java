package com.appkamus.submission.appkamus.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.appkamus.submission.appkamus.R;

import butterknife.BindView;

/**
 * Dicoding Academy
 *
 * Submisison 3 Aplikasi Kamus
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 13/01/19.
 */
public class DetailActivity extends BaseActivity implements DetailView{

    public static final String EXTRA_TITLE = "extra:title";
    public static final String EXTRA_DESCRIPTION = "extra:description";

    @BindView(R.id.tv_title)
    protected TextView tvTitle;

    @BindView(R.id.tv_desc)
    protected TextView tvDesc;

    @BindView(R.id.top_toolbar)
    protected Toolbar toolbar;


    @BindView(R.id.tv_title_toolbar)
    protected TextView tvTitleToolbar;

    @BindView(R.id.tv_sub_title_toolbar)
    protected TextView tvSubTitleToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String title = "";
        String description;
        if(intent !=null){
            title = getIntent().getStringExtra(EXTRA_TITLE);
            description = getIntent().getStringExtra(EXTRA_DESCRIPTION);

            tvTitle.setText(title);
            tvDesc.setText(description);
        }

        setupToolbar(getString(R.string.description_title),title);
    }

    @Override
    public void setupToolbar(String title, String subTitle) {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() !=null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            tvTitleToolbar.setText(title);
            tvSubTitleToolbar.setText(subTitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;

    }
}
