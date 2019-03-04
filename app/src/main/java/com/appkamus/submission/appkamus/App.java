package com.appkamus.submission.appkamus;

import android.app.Application;

import com.appkamus.submission.appkamus.database.DatabaseHelper;
import com.appkamus.submission.appkamus.database.QueryHelper;
import com.appkamus.submission.appkamus.database.SharedPrefs;

/**
 * Dicoding Academy
 *
 * Submisison 3 Aplikasi Kamus
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 13/01/19.
 */
public class App extends Application {

    private static DatabaseHelper mDatabaseHelper;
    private static QueryHelper mQueryHelper;
    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        initInstance();

        // Initial Prefs
        SharedPrefs.init(this);

        // Initial Database
        DatabaseHelper.init(this);

    }

    private synchronized void initInstance(){
        if(mInstance == null)
            mInstance  = new App();
    }

    public static QueryHelper getDatabaseOpen(){
        if(mDatabaseHelper == null)
            mDatabaseHelper = DatabaseHelper.init(mInstance);

        if(mQueryHelper == null)
            mQueryHelper = new QueryHelper(mDatabaseHelper.getWritableDatabase());

        return mQueryHelper;
    }

    public static void getDatabaseClose(){
        if(mDatabaseHelper != null)
            mDatabaseHelper.close();

        if(mQueryHelper !=null)
            mQueryHelper = null;
    }
}
