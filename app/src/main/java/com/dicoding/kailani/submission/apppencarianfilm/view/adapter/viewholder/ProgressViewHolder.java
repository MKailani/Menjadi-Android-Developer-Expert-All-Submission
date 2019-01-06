package com.dicoding.kailani.submission.apppencarianfilm.view.adapter.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;

import com.dicoding.kailani.submission.apppencarianfilm.R;

import butterknife.BindView;

/**
 * Dicoding Academy
 * Submission 2 - Aplikasi Pencarian Film UI-UX
 *
 * Created by Kailani on 06/01/19 -> Submssion 2
 */
public class ProgressViewHolder extends BaseViewHolder {

    @BindView(R.id.pb_loading)
    public ProgressBar pbLoading;

    public ProgressViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
