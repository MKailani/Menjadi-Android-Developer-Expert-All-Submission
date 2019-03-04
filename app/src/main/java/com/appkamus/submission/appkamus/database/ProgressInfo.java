package com.appkamus.submission.appkamus.database;

/**
 * Dicoding Academy
 *
 * Submisison 3 Aplikasi Kamus
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 13/01/19.
 */
public class ProgressInfo {
    private final int progressValue;
    private final String infoProgress;

    public ProgressInfo(double progressValue, String infoProgress) {
        this.progressValue = (int)progressValue;
        this.infoProgress = infoProgress;
    }

    public String getInfoProgress() {
        return infoProgress;
    }

    public Integer getProgressValue() {
        return progressValue;
    }


    public static ProgressInfo preparingIndonesiaData(double progressValue){
        return new ProgressInfo(progressValue, "Preparing Indonesia Data");
    }

    public static ProgressInfo preparingEnglishData(double progressValue){
        return new ProgressInfo(progressValue, "Preparing English Data");
    }


}
