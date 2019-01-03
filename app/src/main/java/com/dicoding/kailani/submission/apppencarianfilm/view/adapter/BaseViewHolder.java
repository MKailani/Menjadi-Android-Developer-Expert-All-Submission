package com.dicoding.kailani.submission.apppencarianfilm.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Khay on 03/01/19.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    private Unbinder unbinder;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        unbinder = ButterKnife.bind(this,itemView);
    }

    public void clearView(){
        if(unbinder !=null) unbinder.unbind();
    }
}
