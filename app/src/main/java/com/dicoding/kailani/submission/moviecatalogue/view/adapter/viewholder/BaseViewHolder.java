package com.dicoding.kailani.submission.moviecatalogue.view.adapter.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Dicoding Academy
 *
 * Final Project Aplikasi Movie Catalogue
 * Menjadi Android Developer Expert (MADE)
 *
 * Created by kheys on 28/01/19.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    protected BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
