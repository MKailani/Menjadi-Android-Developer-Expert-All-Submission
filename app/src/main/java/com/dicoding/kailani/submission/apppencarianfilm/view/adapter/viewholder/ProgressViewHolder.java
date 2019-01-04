package com.dicoding.kailani.submission.apppencarianfilm.view.adapter.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;

import com.dicoding.kailani.submission.apppencarianfilm.R;

import butterknife.BindView;

/**
 * Dicoding Academy
 * Submission 1 - Aplikasi Pencarian Film
 *
 * Created by Kailani on 04/01/19.
 */
public class ProgressViewHolder extends BaseViewHolder {

    @BindView(R.id.pb_loading)
    public ProgressBar pbLoading;

    public ProgressViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
