package com.frcteam195.cyberscouter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CyberScouterDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CyberScouter.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CyberScouterContract.ConfigEntry.TABLE_NAME + " (" +
                    CyberScouterContract.ConfigEntry._ID + " INTEGER PRIMARY KEY," +
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_ROLE + " TEXT," +
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT + " TEXT," +
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_TABLET_NUM + " INTEGER," +
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_OFFLINE + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CyberScouterContract.ConfigEntry.TABLE_NAME;


    public CyberScouterDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
