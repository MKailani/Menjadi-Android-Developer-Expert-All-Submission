package com.moviecatalogue.submission.myfavoriteapp.presenter;


import com.moviecatalogue.submission.myfavoriteapp.view.activity.MainView;

/**
 * Dicoding Academy
 *
 * Final Project Aplikasi Movie Catalogue
 * Menjadi Android Developer Expert (MADE)
 *
 * Created by kheys on 28/01/19.
 */
public class MainViewPresenter {
    private final MainView mView;

    public MainViewPresenter(MainView view) {
        mView = view;
    }
    public void loadData(){
        mView.load();
    }
}
