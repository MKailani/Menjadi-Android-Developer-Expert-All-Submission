package com.dicoding.kailani.submission.moviecatalogue.view.adapter.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;


import com.dicoding.kailani.submission.moviecatalogue.R;

import butterknife.BindView;

/**
 * Dicoding Academy
 *
 * Submisison 4 Aplikasi Movie Catalogue UI/UX DATABASE
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 21/01/19.
 */
public class ProgressViewHolder extends BaseViewHolder {

    @BindView(R.id.pb_loading)
    public ProgressBar pbLoading;

    public ProgressViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
