package com.appkamus.submission.appkamus.view.adapter.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.appkamus.submission.appkamus.R;
import com.appkamus.submission.appkamus.model.Kamus;
import com.appkamus.submission.appkamus.view.fragment.KamusView;

import butterknife.BindView;


/**
 * Dicoding Academy
 *
 * Submisison 3 Aplikasi Kamus
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 13/01/19.
 */
public class KamusViewHolder extends BaseViewHolder{

    @BindView(R.id.row_item_title)
    public TextView tvTitle;
    private Kamus mKamus;
    private KamusView mView;

    public KamusViewHolder(@NonNull final View itemView) {
        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getKamus() !=null){
                    mView.goToActivity(getKamus());
                }
            }
        });
    }

    public void setKamus(Kamus kamus) {
        this.mKamus = kamus;
    }

    public void setView(KamusView view) {
        this.mView = view;
    }

    private Kamus getKamus() {
        return mKamus;
    }
}
