package com.appkamus.submission.appkamus.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Dicoding Academy
 *
 * Submisison 3 Aplikasi Kamus
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 13/01/19.
 */
public class Kamus implements Parcelable {
    private int id;
    private String word;
    private String content;

    public Kamus(int id, String word, String content) {
        this.id = id;
        this.word = word;
        this.content = content;
    }

    public Kamus(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getword() {
        return word;
    }

    public void setword(String word) {
        this.word = word;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.word);
        dest.writeString(this.content);
    }

    private Kamus(Parcel in) {
        this.id = in.readInt();
        this.word = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<Kamus> CREATOR = new Parcelable.Creator<Kamus>() {
        @Override
        public Kamus createFromParcel(Parcel source) {
            return new Kamus(source);
        }

        @Override
        public Kamus[] newArray(int size) {
            return new Kamus[size];
        }
    };
}
