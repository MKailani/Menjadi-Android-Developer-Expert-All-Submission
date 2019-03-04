package com.appkamus.submission.appkamus.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Dicoding Academy
 *
 * Submisison 3 Aplikasi Kamus
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 13/01/19.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "kamus.db";
    private static final int DATABASE_VERSION = 1;

    private static DatabaseHelper dbHelper;
    private final QueryHelper mQueryHelper;


     private DatabaseHelper(Context mContext){
        super(mContext, DATABASE_NAME, null, DATABASE_VERSION);
        mQueryHelper = new QueryHelper();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Table
        mQueryHelper.getMstKamusEnglishToIndo().createTable(db);
        mQueryHelper.getMstKamusIndoToEnglish().createTable(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mQueryHelper.getMstKamusEnglishToIndo().dropTable(db);
        mQueryHelper.getMstKamusIndoToEnglish().dropTable(db);
        onCreate(db);
    }

    public synchronized static DatabaseHelper init(Context context) {
        if(dbHelper ==null){
            dbHelper = new DatabaseHelper(context);
        }
        return dbHelper;
    }

}
