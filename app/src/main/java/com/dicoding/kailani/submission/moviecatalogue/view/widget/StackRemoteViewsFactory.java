package com.dicoding.kailani.submission.moviecatalogue.view.widget;

import android.content.Context;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;


import com.dicoding.kailani.submission.moviecatalogue.App;
import com.dicoding.kailani.submission.moviecatalogue.R;
import com.dicoding.kailani.submission.moviecatalogue.model.Movie;
import com.dicoding.kailani.submission.moviecatalogue.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Dicoding Academy
 *
 * Final Project Aplikasi Movie Catalogue
 * Menjadi Android Developer Expert (MADE)
 *
 * Created by kheys on 28/01/19.
 */
public class StackRemoteViewsFactory implements
        RemoteViewsService.RemoteViewsFactory {

    private final List<Movie> mWidgetItems = new ArrayList<>();
    private final Context mContext;

    public StackRemoteViewsFactory(Context context) {
        mContext = context;
    }

    public void onCreate() {}

    @Override
    public void onDataSetChanged() {
        if(mWidgetItems.size() > 0){
            mWidgetItems.clear();
        }
        mWidgetItems.addAll(App.getDatabaseOpen().getFavoriteMovie().getAllData(20,0));
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mWidgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item_movie);
        Movie movie = mWidgetItems.size() > 0 ? mWidgetItems.get(position) : null;

        if(movie !=null){
            rv.setViewVisibility(R.id.tv_title_movie, View.VISIBLE);
            rv.setViewVisibility(R.id.imageView, View.VISIBLE);

            Utils.loadImage(mContext,rv,movie.getPosterPath());
            rv.setTextViewText(R.id.tv_title_movie, movie.getTitle());
        }else{
            rv.setViewVisibility(R.id.tv_title_movie, View.INVISIBLE);
            rv.setViewVisibility(R.id.imageView, View.INVISIBLE);
        }
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

}