package com.dicoding.kailani.submission.moviecatalogue.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.kailani.submission.moviecatalogue.BuildConfig;
import com.dicoding.kailani.submission.moviecatalogue.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Dicoding Academy
 *
 * Submisison 4 Aplikasi Movie Catalogue UI/UX DATABASE
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 21/01/19.
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


    public static void menuIconColor(Context context, MenuItem menuItem, int color) {
        Drawable drawable = menuItem.getIcon();
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(
                    ContextCompat.getColor(context,
                            color),
                    PorterDuff.Mode.SRC_ATOP);

        }
    }
}
