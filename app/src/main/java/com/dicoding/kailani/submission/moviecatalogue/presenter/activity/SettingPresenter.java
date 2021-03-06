package com.dicoding.kailani.submission.moviecatalogue.presenter.activity;

import android.app.PendingIntent;
import android.content.Context;

import com.dicoding.kailani.submission.moviecatalogue.R;
import com.dicoding.kailani.submission.moviecatalogue.database.SharedPrefs;
import com.dicoding.kailani.submission.moviecatalogue.service.DailyReminderService;
import com.dicoding.kailani.submission.moviecatalogue.service.ReleaseTodayService;
import com.dicoding.kailani.submission.moviecatalogue.utils.Contants;
import com.dicoding.kailani.submission.moviecatalogue.utils.PrefsConst;
import com.dicoding.kailani.submission.moviecatalogue.utils.Utils;
import com.dicoding.kailani.submission.moviecatalogue.view.activity.iview.SettingView;

import java.util.Calendar;

/**
 * Dicoding Academy
 *
 * Final Project Aplikasi Movie Catalogue
 * Menjadi Android Developer Expert (MADE)
 *
 * Created by kheys on 28/01/19.
 */
public class SettingPresenter {
    private final Context mContext;
    private SettingView mSettingView;

    public SettingPresenter(Context context, SettingView settingView) {
        this.mContext = context;
        this.mSettingView = settingView;
    }

    public SettingPresenter(Context context) {
        this.mContext = context;
    }

    public void setAlarm(TypeAlarm type, boolean isChecked) {

        if (type == TypeAlarm.DAILY_REMINDER) {
            // Set Alarm 8:00 AM Daily Reminder
            Calendar calendar = Utils.getCalender(8, 0, 0);

            PendingIntent pendingIntent = Utils.getPendingIntent(mContext,
                    DailyReminderService.class,
                    Contants.NOTIFICATION_DAILY_REMINDER);

            Utils.setRepeatingAlarm(mContext, calendar, pendingIntent);

            // Save Shared Preference
            SharedPrefs.setBoolean(PrefsConst.PREFS_REMEMBER_APP, isChecked);

            if (mSettingView != null)
                mSettingView.showMessage(mContext.getString(R.string.daily_reminder_on_text));

        } else if (type == TypeAlarm.RELEASE_MOVIE) {
            // Set Alarm 7:00: AM Release Movie
            Calendar calendar = Utils.getCalender(7, 0, 0);

            PendingIntent pendingIntent = Utils.getPendingIntent(mContext,
                    ReleaseTodayService.class,
                    Contants.NOTIFICATION_RELEASE_TODAY_REMINDER);

            Utils.setRepeatingAlarm(mContext, calendar, pendingIntent);

            // Save Shared Preference
            SharedPrefs.setBoolean(PrefsConst.PREFS_REMEMBER_RELEASE_MOVIE, isChecked);

            if (mSettingView != null)
                mSettingView.showMessage(mContext.getString(R.string.release_movie_reminder_on_text));
        }
    }

    public void cancelAlarm(TypeAlarm type, boolean isChecked) {
        Utils.cancelAlarm(mContext, getPendingIntent(type));

        // Save Shared Preference
        SharedPrefs.setBoolean(type == TypeAlarm.DAILY_REMINDER ?
                        PrefsConst.PREFS_REMEMBER_APP :
                        PrefsConst.PREFS_REMEMBER_RELEASE_MOVIE,
                isChecked);

        if (mSettingView != null)
            mSettingView.showMessage(type == TypeAlarm.DAILY_REMINDER ?
                    mContext.getString(R.string.daily_reminder_on_text)
                    : mContext.getString(R.string.release_movie_reminder_on_text));
    }

    private PendingIntent getPendingIntent(TypeAlarm type) {
        if (type == TypeAlarm.DAILY_REMINDER)
            return Utils.getPendingIntent(mContext,
                    DailyReminderService.class,
                    Contants.NOTIFICATION_DAILY_REMINDER);
        else
            return Utils.getPendingIntent(mContext,
                    ReleaseTodayService.class,
                    Contants.NOTIFICATION_RELEASE_TODAY_REMINDER);
    }


    public enum TypeAlarm {
        DAILY_REMINDER,
        RELEASE_MOVIE
    }
}
