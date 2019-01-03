package com.dicoding.kailani.submission.apppencarianfilm.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dicoding.kailani.submission.apppencarianfilm.BuildConfig;
import com.dicoding.kailani.submission.apppencarianfilm.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Khay on 03/01/19.
 */
public class Utils {

    public static void loadImage(ImageView targetView,String path){

        Glide.with(targetView.getContext())
                .load(BuildConfig.IMAGE_LOAD_URL+path)
                .crossFade()
                .placeholder(R.color.grey)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(targetView);
    }

    public static void loadImage(CircleImageView targetView, String path){
        Glide.with(targetView.getContext())
                .load(BuildConfig.IMAGE_LOAD_URL+path)
                .crossFade()
                .placeholder(R.color.grey)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(targetView);
    }

    public static String getMethodName(){
        return new Exception().getStackTrace()[0].getMethodName();
    }
}
