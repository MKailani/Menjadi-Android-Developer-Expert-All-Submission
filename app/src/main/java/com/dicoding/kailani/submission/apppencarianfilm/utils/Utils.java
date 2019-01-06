package com.dicoding.kailani.submission.apppencarianfilm.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.kailani.submission.apppencarianfilm.BuildConfig;
import com.dicoding.kailani.submission.apppencarianfilm.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Dicoding Academy
 * Submission 2 - Aplikasi Pencarian Film UI-UX
 *
 * Created by Kailani on 06/01/19 -> Submssion 2
 */
public class Utils {

    public static void loadImage(ImageView targetView, String path) {
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.color.grey);

        Glide.with(targetView.getContext())
                .load(BuildConfig.IMAGE_LOAD_URL + path)
                .apply(requestOptions)
                .into(targetView);
    }

    public static void loadImage(CircleImageView targetView, String path) {
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .override(100, 100);

        Glide.with(targetView.getContext())
                .load(BuildConfig.IMAGE_LOAD_URL + path)
                .apply(requestOptions)
                .into(targetView);
    }

    public static String getMethodName() {
        return new Exception().getStackTrace()[1].getMethodName();
    }
}
