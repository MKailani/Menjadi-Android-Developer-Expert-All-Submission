package com.moviecatalogue.submission.myfavoriteapp.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.moviecatalogue.submission.myfavoriteapp.BuildConfig;
import com.moviecatalogue.submission.myfavoriteapp.R;

/**
 * Dicoding Academy
 *
 * Final Project Aplikasi Movie Catalogue
 * Menjadi Android Developer Expert (MADE)
 *
 * Created by kheys on 28/01/19.
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

}
