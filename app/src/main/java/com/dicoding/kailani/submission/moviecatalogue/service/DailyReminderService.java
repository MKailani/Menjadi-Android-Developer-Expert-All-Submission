package com.dicoding.kailani.submission.moviecatalogue.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.dicoding.kailani.submission.moviecatalogue.R;
import com.dicoding.kailani.submission.moviecatalogue.utils.Contants;
import com.dicoding.kailani.submission.moviecatalogue.utils.Utils;


/**
 * Dicoding Academy
 *
 * Final Project Aplikasi Movie Catalogue
 * Menjadi Android Developer Expert (MADE)
 *
 * Created by kheys on 28/01/19.
 */
public class DailyReminderService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String applicationNameTitle = context.getString(R.string.app_name);
        String dailyReminderContent = context.getString(R.string.daily_reminder_content);

        // Create Push Notification
        Utils.createNotification(context,
                applicationNameTitle,dailyReminderContent,
                Contants.NOTIFICATION_DAILY_REMINDER);
    }

}
