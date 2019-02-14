package com.frcteam195.cyberscouter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CyberScouterDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "CyberScouter.db";

    private static final String SQL_CREATE_CONFIG_ENTRIES =
            "CREATE TABLE " + CyberScouterContract.ConfigEntry.TABLE_NAME + " (" +
                    CyberScouterContract.ConfigEntry._ID + " INTEGER PRIMARY KEY," +
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_ROLE + " TEXT," +
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_ROLE_COL + " TEXT," +
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT + " TEXT," +
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT_ID + " INTEGER," +
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_TABLET_NUM + " INTEGER," +
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_OFFLINE + " INTEGER," +
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_FIELD_REDLEFT + " INTEGER," +
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_USERNAME + " TEXT)";

    private static final String SQL_DELETE_CONFIG_ENTRIES =
            "DROP TABLE IF EXISTS " + CyberScouterContract.ConfigEntry.TABLE_NAME;

    private static final String SQL_CREATE_MATCHES =
            "CREATE TABLE " + CyberScouterContract.Matches.TABLE_NAME + " (" +
                    CyberScouterContract.Matches.COLUMN_NAME_MATCHID + " INTEGER PRIMARY KEY," +
                    CyberScouterContract.Matches.COLUMN_NAME_EVENTID + " INTEGER," +
                    CyberScouterContract.Matches.COLUMN_NAME_LEVELIIISCOUTERID + " INTEGER," +
                    CyberScouterContract.Matches.COLUMN_NAME_MATCHNO + " INTEGER," +
                    CyberScouterContract.Matches.COLUMN_NAME_MATCHNOTAG + " INTEGER," +
                    CyberScouterContract.Matches.COLUMN_NAME_REDTEAM1 + " TEXT," +
                    CyberScouterContract.Matches.COLUMN_NAME_REDTEAM2 + " TEXT," +
                    CyberScouterContract.Matches.COLUMN_NAME_REDTEAM3 + " TEXT," +
                    CyberScouterContract.Matches.COLUMN_NAME_REDTEAM1TAG + " TEXT," +
                    CyberScouterContract.Matches.COLUMN_NAME_REDTEAM2TAG + " TEXT," +
                    CyberScouterContract.Matches.COLUMN_NAME_REDTEAM3TAG + " TEXT," +
                    CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM1 + " TEXT," +
                    CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM2 + " TEXT," +
                    CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM3 + " TEXT," +
                    CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM1TAG + " TEXT," +
                    CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM2TAG + " TEXT," +
                    CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM3TAG + " TEXT," +
                    CyberScouterContract.Matches.COLUMN_NAME_MATCHVIDEO + " TEXT," +
                    CyberScouterContract.Matches.COLUMN_NAME_COMMENTS + " TEXT," +
                    CyberScouterContract.Matches.COLUMN_NAME_ELIMINATIONMATCH + " INTEGER," +
                    CyberScouterContract.Matches.COLUMN_NAME_STARTOFTELEOP + " TEXT," +
                    CyberScouterContract.Matches.COLUMN_NAME_MATCHENDED + " INTEGER)";

    private static final String SQL_DELETE_MATCHES =
            "DROP TABLE IF EXISTS " + CyberScouterContract.Matches.TABLE_NAME;


    public CyberScouterDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CONFIG_ENTRIES);
        db.execSQL(SQL_CREATE_MATCHES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // simply to discard the data and start over
        db.execSQL(SQL_DELETE_CONFIG_ENTRIES);
        db.execSQL(SQL_DELETE_MATCHES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
