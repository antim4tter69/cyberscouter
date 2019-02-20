package com.frcteam195.cyberscouter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CyberScouterDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 5;
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
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_USERNAME + " TEXT," +
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_USERID + " INTEGER)"
            ;

    private static final String SQL_DELETE_CONFIG_ENTRIES =
            "DROP TABLE IF EXISTS " + CyberScouterContract.ConfigEntry.TABLE_NAME;

    private static final String SQL_CREATE_MATCHES =
            "CREATE TABLE " + CyberScouterContract.MatchScouting.TABLE_NAME + " (" +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHSCOUTINGID + " INTEGER PRIMARY KEY," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_EVENTID + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHID + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_COMPUTERID + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTERID + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_REVIEWERID + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TEAM + " TEXT," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TEAMMATCHNO + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ALLIANCESTATIONID + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_STARTOFTELEOP + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHENDED + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_QUESTIONSANSWERED + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTINGSTATUS + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AREASTOREVIEW + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_COMPLETE + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOPRELOAD + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTODIDNOTSHOW + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOCSCARGO + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOCSHATCH + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSCARGOLOW + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSCARGOMED + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSCARGOHIGH + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHFARLOW + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHFARMED + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHFARHIGH + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHNEARLOW + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHNEARMED + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHNEARHIGH + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELECSCARGO + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELECSHATCH + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOLOW + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOMED + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOHIGH + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARLOW + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARMED + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARHIGH + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARLOW + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARMED + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARHIGH + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSCORE + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBASSIST + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMHATCHGRDPICKUP + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMBROKE + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMTIPOVER + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMSUBSYSTEMBROKE + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER01 + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER02 + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER03 + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER04 + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER05 + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER06 + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER07 + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER08 + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER09 + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER10 + " INTEGER," +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_UPLOADSTATUS + " INTEGER)";

    private static final String SQL_DELETE_MATCHES =
            "DROP TABLE IF EXISTS " + CyberScouterContract.MatchScouting.TABLE_NAME;

    private static final String SQL_CREATE_USER_ENTRIES =
            "CREATE TABLE " + CyberScouterContract.Users.TABLE_NAME + " (" +
                    CyberScouterContract.Users.COLUMN_NAME_USERID + " INTEGER PRIMARY KEY," +
                    CyberScouterContract.Users.COLUMN_NAME_FIRSTNAME + " TEXT," +
                    CyberScouterContract.Users.COLUMN_NAME_LASTNAME + " TEXT," +
                    CyberScouterContract.Users.COLUMN_NAME_CELLPHONE + " TEXT," +
                    CyberScouterContract.Users.COLUMN_NAME_EMAIL + " TEXT)"
            ;

    private static final String SQL_DELETE_USER_ENTRIES =
            "DROP TABLE IF EXISTS " + CyberScouterContract.Users.TABLE_NAME;

    public CyberScouterDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CONFIG_ENTRIES);
        db.execSQL(SQL_CREATE_MATCHES);
        db.execSQL(SQL_CREATE_USER_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // simply to discard the data and start over
        db.execSQL(SQL_DELETE_CONFIG_ENTRIES);
        db.execSQL(SQL_DELETE_MATCHES);
        db.execSQL(SQL_DELETE_USER_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
