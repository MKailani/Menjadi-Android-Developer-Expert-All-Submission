package com.dicoding.kailani.submission.moviecatalogue.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Dicoding Academy
 *
 * Final Project Aplikasi Movie Catalogue
 * Menjadi Android Developer Expert (MADE)
 *
 * Created by kheys on 28/01/19.
 */
public class SharedPrefs {
    private static SharedPreferences prefs;

    public static void init(Context context) {
        if(prefs == null){
            prefs = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    public static void setBoolean(String key,Boolean value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return prefs.getBoolean(key,defaultValue);
    }
}
