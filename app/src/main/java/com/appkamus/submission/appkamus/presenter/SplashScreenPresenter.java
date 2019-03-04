package com.appkamus.submission.appkamus.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.annotation.RawRes;

import com.appkamus.submission.appkamus.App;
import com.appkamus.submission.appkamus.R;
import com.appkamus.submission.appkamus.database.PrefsConst;
import com.appkamus.submission.appkamus.database.ProgressInfo;
import com.appkamus.submission.appkamus.database.ProgressInterface;
import com.appkamus.submission.appkamus.database.SharedPrefs;
import com.appkamus.submission.appkamus.model.Kamus;
import com.appkamus.submission.appkamus.view.activity.SplashScreenView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Dicoding Academy
 *
 * Submisison 3 Aplikasi Kamus
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 13/01/19.
 */
public class SplashScreenPresenter {

    private final Context mContext;
    private final SplashScreenView mView;

    public SplashScreenPresenter(Context context, SplashScreenView view) {
        mContext = context;
        mView = view;
    }

    public void preparingDataLoad() {
        new LoadData().execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadData extends AsyncTask<Void, ProgressInfo, Void> {

        double progress = 0;
        final double maxprogress = 100;

        /*
        Proses background terjadi di method doInBackground
         */
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Boolean firstLunchApp = SharedPrefs.getBoolean(PrefsConst.PREFS_FIRST_LOGIN, true);

                if (firstLunchApp) {
                    // Load Kamus English to Indonesia
                    loadKamusEnglishToIndo();

                    publishProgress(new ProgressInfo(0, ""));

                    // Load Kamus Indonesia to English
                    loadKamusIndoToEnglish();

                    // Set First Load False
                    SharedPrefs.setBoolean(PrefsConst.PREFS_FIRST_LOGIN, false);

                    // Done
                    successLoad(true);
                } else {
                    // Done
                    successLoad(false);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return null;
        }

        //Update prosesnya
        @Override
        protected void onProgressUpdate(ProgressInfo... values) {
            mView.updateProgressBar(values[0].getProgressValue());
            mView.infoProgressBar(values[0].getInfoProgress());
        }

        @Override
        protected void onPostExecute(Void result) {
            mView.goToMainMenu();
        }


        private synchronized void loadKamusEnglishToIndo() throws Exception {
            publishProgress(ProgressInfo.preparingEnglishData(0));
            this.wait(2000);

            ArrayList<Kamus> listKamusEnglishToIndo = preLoadRaw(R.raw.english_indonesia);

            progress = 10;
            publishProgress(ProgressInfo.preparingEnglishData(progress));

            App.getDatabaseOpen()
                    .getMstKamusEnglishToIndo()
                    .insertBulk(listKamusEnglishToIndo, progress, new ProgressInterface() {
                        @Override
                        public void updateProgress(int value) {
                            publishProgress(ProgressInfo.preparingEnglishData(value));
                        }
                    });

            // Close helper ketika proses query sudah selesai
            App.getDatabaseClose();

            // Set Max Progress
            publishProgress(ProgressInfo.preparingEnglishData(maxprogress));


        }

        private synchronized void loadKamusIndoToEnglish() throws Exception {

            publishProgress(ProgressInfo.preparingIndonesiaData(0));
            this.wait(1000);

            ArrayList<Kamus> listKamusIndoToEnglish = preLoadRaw(R.raw.indonesia_english);

            progress = 10;
            publishProgress(ProgressInfo.preparingIndonesiaData(progress));

            // Open Connection Database SQLite
            App.getDatabaseOpen()
                    .getMstKamusIndoToEnglish()
                    .insertBulk(listKamusIndoToEnglish, progress, new ProgressInterface() {
                        @Override
                        public void updateProgress(int value) {

                            publishProgress(ProgressInfo.preparingIndonesiaData(value));
                        }
                    });

            // Close Connection Database SQLite
            App.getDatabaseClose();

            // Set Max Progress
            publishProgress(ProgressInfo.preparingIndonesiaData(maxprogress));

        }

        private synchronized void successLoad(boolean isFirstLoad) throws Exception {
            if(!isFirstLoad){
                publishProgress(new ProgressInfo(0, "preparing data..."));
                this.wait(1000);

                publishProgress(new ProgressInfo(50, "Loading..."));

                this.wait(1000);
            }

            publishProgress(new ProgressInfo(maxprogress, "Done"));
        }
    }

    private ArrayList<Kamus> preLoadRaw(@RawRes int resId) {
        ArrayList<Kamus> kamusOutput = new ArrayList<>();
        String line;
        BufferedReader reader;
        try {
            Resources res = mContext.getResources();
            InputStream raw_dict = res.openRawResource(resId);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;

            while ((line = reader.readLine()) != null) {
                String[] splitstr = line.split("\t");

                Kamus kamus = new Kamus((count + 1), splitstr[0], splitstr[1]);
                kamusOutput.add(kamus);
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kamusOutput;
    }


}
