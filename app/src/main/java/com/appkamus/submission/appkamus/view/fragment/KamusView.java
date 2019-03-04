package com.appkamus.submission.appkamus.view.fragment;

import com.appkamus.submission.appkamus.model.Kamus;

import java.util.List;

/**
 * Dicoding Academy
 *
 * Submisison 3 Aplikasi Kamus
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 13/01/19.
 */
public interface KamusView {
    void setupRecyclerview();
    void setupToolbar();
    void loadData(List<Kamus> kamus);
    void goToActivity(Kamus kamus);
    void searchKamus(String text);
}
