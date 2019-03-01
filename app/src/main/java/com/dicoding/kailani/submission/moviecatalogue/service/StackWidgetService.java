package com.dicoding.kailani.submission.moviecatalogue.service;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.dicoding.kailani.submission.moviecatalogue.view.widget.StackRemoteViewsFactory;


/**
 * Dicoding Academy
 *
 * Final Project Aplikasi Movie Catalogue
 * Menjadi Android Developer Expert (MADE)
 *
 * Created by kheys on 28/01/19.
 */
public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext());
    }
}