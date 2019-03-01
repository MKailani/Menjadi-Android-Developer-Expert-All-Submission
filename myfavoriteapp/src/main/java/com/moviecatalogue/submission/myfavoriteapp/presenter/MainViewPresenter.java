package com.moviecatalogue.submission.myfavoriteapp.presenter;

import com.moviecatalogue.submission.myfavoriteapp.view.activity.MainView;

/**
 * Dicoding Academy
 *
 * Submisison 4 Aplikasi Movie Catalogue UI/UX DATABASE
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 21/01/19.
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
