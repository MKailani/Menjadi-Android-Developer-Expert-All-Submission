package com.dicoding.kailani.submission.moviecatalogue;

import android.app.Application;
import android.database.sqlite.SQLiteException;

import com.dicoding.kailani.submission.moviecatalogue.database.DatabaseHelper;
import com.dicoding.kailani.submission.moviecatalogue.database.QueryHelper;

/**
 * Dicoding Academy
 *
 * Submisison 4 Aplikasi Movie Catalogue UI/UX DATABASE
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 21/01/19.
 */
public class App extends Application {

    private static DatabaseHelper mDatabaseHelper;
    private static QueryHelper mQueryHelper;
    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        initInstance();

        // Initial Database
        DatabaseHelper.init(this);

    }

    private synchronized void initInstance(){
        if(mInstance == null)
            mInstance  = new App();
    }

    public static QueryHelper getDatabaseOpen() {
        if(mDatabaseHelper == null)
            mDatabaseHelper = DatabaseHelper.init(mInstance);

        if(mQueryHelper == null)
            mQueryHelper = new QueryHelper(mDatabaseHelper.getWritableDatabase());

        return mQueryHelper;
    }

    public static void getDatabaseClose()throws SQLiteException{
        if(mDatabaseHelper != null)
            mDatabaseHelper.close();

        if(mQueryHelper !=null)
            mQueryHelper = null;
    }
}
