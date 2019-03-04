package com.appkamus.submission.appkamus.presenter;

import com.appkamus.submission.appkamus.App;
import com.appkamus.submission.appkamus.model.Kamus;
import com.appkamus.submission.appkamus.view.fragment.KamusView;

import java.util.List;

/**
 * Dicoding Academy
 *
 * Submisison 3 Aplikasi Kamus
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 13/01/19.
 */
public class KamusPresenter  {

    private final KamusView mKamusView;

    public KamusPresenter(KamusView kamusView) {
        mKamusView = kamusView;
    }

    public void loadDataEnglishToIndo(){
        List<Kamus> data = App.getDatabaseOpen().getMstKamusEnglishToIndo().getAllData();
        mKamusView.loadData(data);
    }

    public void loadDataIndoToEnglish(){
        List<Kamus> data = App.getDatabaseOpen().getMstKamusIndoToEnglish().getAllData();
        mKamusView.loadData(data);
    }
}
