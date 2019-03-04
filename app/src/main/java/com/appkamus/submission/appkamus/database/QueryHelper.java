package com.appkamus.submission.appkamus.database;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;
import android.util.Log;

import com.appkamus.submission.appkamus.App;
import com.appkamus.submission.appkamus.model.Kamus;

import java.util.ArrayList;
import java.util.List;

/**
 * Dicoding Academy
 *
 * Submisison 3 Aplikasi Kamus
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 13/01/19.
 */
public class QueryHelper {

    private final MasterKamusIndoToEnglish mstKamusIndoToEnglish;
    private final MasterKamusEnglishToIndo mstKamusEnglishToIndo;
    private SQLiteDatabase mSQLiteDatabase;
    private final Double progressMaxInsert = 100.0;


    QueryHelper() {
        mstKamusIndoToEnglish = new MasterKamusIndoToEnglish();
        mstKamusEnglishToIndo = new MasterKamusEnglishToIndo();
    }

    public QueryHelper(SQLiteDatabase sqLiteDatabase) {
        this();
        this.mSQLiteDatabase = sqLiteDatabase;
    }

    public class MasterKamusIndoToEnglish implements BaseColumns {

        private final String TAG = MasterKamusIndoToEnglish.class.getSimpleName();

        static final String TABLE_NAME = "mst_in_to_en";
        static final String WORD = "word";
        static final String DESC = "description";

        private final String TABLE_SCHEMA = TABLE_NAME + "(" +
                _ID + " integer primary key autoincrement, " +
                WORD + " TEXT not null, " +
                DESC + " TEXT not null " +
                ");";

        /**
         * Create Table Data Kamus Indo to English
         */
        void createTable(SQLiteDatabase db) {
            try {
                db.execSQL("CREATE TABLE " + TABLE_SCHEMA);
            }catch (SQLException ex){
                Log.e(TAG, "Create Table : " + ex);
            }
        }

        /**
         * Drop Table Data Kamus Indo to English
         */
        void dropTable(SQLiteDatabase db) {
            try {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            }catch (SQLException ex){
                Log.e(TAG, "Drop Table : " + ex);
            }
        }


        /**
         * Insert Data Kamus Indo to English
         */
        void insert(Kamus kamusModel) {
            if (mSQLiteDatabase != null) {
                String sql = "INSERT INTO " + TABLE_NAME + " (" + WORD + ", " + DESC
                        + ") VALUES (?, ?)";
                SQLiteStatement stmt = mSQLiteDatabase.compileStatement(sql);
                stmt.bindString(1, kamusModel.getword());
                stmt.bindString(2, kamusModel.getContent());
                stmt.execute();
                stmt.clearBindings();
            }
        }

        /**
         * Get All Data Kamus Indo to English
         */

        public ArrayList<Kamus> getAllData() {
            ArrayList<Kamus> output = new ArrayList<>();
            if(mSQLiteDatabase !=null){
                Cursor cursor = mSQLiteDatabase.query(TABLE_NAME, null, null, null, null, null, _ID + " ASC", null);
                cursor.moveToFirst();
                Kamus kamus;
                if (cursor.getCount() > 0) {
                    do {
                        kamus = new Kamus();
                        kamus.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                        kamus.setword(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                        kamus.setContent(cursor.getString(cursor.getColumnIndexOrThrow(DESC)));


                        output.add(kamus);
                        cursor.moveToNext();


                    } while (!cursor.isAfterLast());
                }
                cursor.close();
                App.getDatabaseClose();
            }

            return output;
        }


        /**
         * Insert All Data Kamus Indo to English
         */

        public void insertBulk(List<Kamus> listKamus, double progress, ProgressInterface progressInterface) {
            if (mSQLiteDatabase != null) {
                Double progressDiff = (progressMaxInsert - progress) / listKamus.size();

                beginTransaction();
                try {
                    for (Kamus kamus : listKamus) {
                        insert(kamus);
                        progress += progressDiff;
                        progressInterface.updateProgress((int) progress);
                    }
                } catch (Exception ex) {
                    Log.e(TAG, "insertBulk : " + ex);
                }
                setTransactionSuccess();
                endTransaction();
            }
        }

    }

    public class MasterKamusEnglishToIndo implements BaseColumns {

        private final String TAG = MasterKamusEnglishToIndo.class.getSimpleName();

        static final String TABLE_NAME = "mst_en_to_in";
        static final String WORD = "word";
        static final String DESC = "description";

        private final String TABLE_SCHEMA = TABLE_NAME + "(" +
                _ID + " integer primary key autoincrement, " +
                WORD + " TEXT not null, " +
                DESC + " TEXT not null " +
                ");";

        /**
         * Create Table Data Kamus English to Indonesia
         */
        void createTable(SQLiteDatabase db) {
            try {
                db.execSQL("CREATE TABLE " + TABLE_SCHEMA);
            }catch (SQLException ex){
                Log.e(TAG, "Create Table : " + ex);
            }
        }

        /**
         * Drop Table Data Kamus English to Indonesia
         */
        void dropTable(SQLiteDatabase db) {
            try {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            }catch (SQLException ex){
                Log.e(TAG, "Drop Table : " + ex);
            }
        }

        /**
         * Get All Data Kamus English to Indonesia
         */
        public ArrayList<Kamus> getAllData() {
            ArrayList<Kamus> output = new ArrayList<>();
            if(mSQLiteDatabase !=null){
                Cursor cursor = mSQLiteDatabase.query(TABLE_NAME, null, null, null, null, null, _ID + " ASC", null);
                cursor.moveToFirst();
                Kamus kamus;
                if (cursor.getCount() > 0) {
                    do {
                        kamus = new Kamus();
                        kamus.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                        kamus.setword(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                        kamus.setContent(cursor.getString(cursor.getColumnIndexOrThrow(DESC)));

                        output.add(kamus);
                        cursor.moveToNext();


                    } while (!cursor.isAfterLast());
                }
                cursor.close();
                App.getDatabaseClose();
            }

            return output;
        }

        /**
         * Insert Data Kamus English to Indonesia
         */
        void insert(Kamus kamusModel) {
            if (mSQLiteDatabase != null) {
                String sql = "INSERT INTO " + TABLE_NAME + " (" + WORD + ", " + DESC
                        + ") VALUES (?, ?)";
                SQLiteStatement stmt = mSQLiteDatabase.compileStatement(sql);
                stmt.bindString(1, kamusModel.getword());
                stmt.bindString(2, kamusModel.getContent());
                stmt.execute();
                stmt.clearBindings();
            }
        }

        /**
         * Insert All Data Kamus English to Indonesia
         */
        public void insertBulk(List<Kamus> listKamus, double progress, ProgressInterface progressInterface) {
            if (mSQLiteDatabase != null) {
                Double progressDiff = (progressMaxInsert - progress) / listKamus.size();

                beginTransaction();
                try {
                    for (Kamus kamus : listKamus) {
                        insert(kamus);
                        progress += progressDiff;
                        progressInterface.updateProgress((int) progress);
                    }
                } catch (Exception ex) {
                    Log.e(TAG, "insertBulk: Exception : " + ex);
                }
                setTransactionSuccess();
                endTransaction();
            }
        }

    }


    /**
     * Transaction Database SQlite
     */

    private void beginTransaction() {
        mSQLiteDatabase.beginTransaction();
    }

    private void setTransactionSuccess() {
        mSQLiteDatabase.setTransactionSuccessful();
    }

    private void endTransaction() {
        mSQLiteDatabase.endTransaction();
    }


    /**
     * Call Define Database Helper
     *
     */

    public MasterKamusEnglishToIndo getMstKamusEnglishToIndo() {
        return mstKamusEnglishToIndo;
    }

    public MasterKamusIndoToEnglish getMstKamusIndoToEnglish() {
        return mstKamusIndoToEnglish;
    }
}
