package com.dicoding.kailani.submission.moviecatalogue.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Dicoding Academy
 *
 * Final Project Aplikasi Movie Catalogue
 * Menjadi Android Developer Expert (MADE)
 *
 * Created by kheys on 28/01/19.
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder mUnbinder;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this,view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
