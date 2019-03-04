package com.appkamus.submission.appkamus.view.activity;

/**
 * Dicoding Academy
 *
 * Submisison 3 Aplikasi Kamus
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 13/01/19.
 */
public interface SplashScreenView {
    void loadData();
    void updateProgressBar(int value);
    void infoProgressBar(String text);
    void goToMainMenu();
}
