package com.dicoding.kailani.submission.apppencarianfilm.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.kailani.submission.apppencarianfilm.R;

/**
 * Created by Khay on 03/01/19.
 */
public class Utils {

    public static void loadImage(Context context, String path, ImageView targetView){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.color.grey)
                .priority(Priority.HIGH);

        Glide.with(context)
             .load(path)
             .apply(options)
             .into(targetView);



    }
}
