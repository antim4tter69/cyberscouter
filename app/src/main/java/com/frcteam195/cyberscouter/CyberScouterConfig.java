package com.frcteam195.cyberscouter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

public class CyberScouterConfig {
    final static String UNKNOWN_ROLE = "Unknown Role";
    final static String UNKNOWN_EVENT = "Unknown Event";
    final static int UNKNOWN_USER_IDX = -1;

    private String role;
    private String role_col;
    private String event;
    private int event_id;
    private int tablet_num;
    private boolean offline;
    private boolean field_red_left;
    private String username;
    private int user_id;
    private int last_question;

    public CyberScouterConfig(){}

    public String getRole() {
        return role;
    }

    public String getRole_col() {
        return role_col;
    }

    public String getEvent() {
        return event;
    }

    public int getEvent_id() {
        return event_id;
    }

    public int getTablet_num() {
        return tablet_num;
    }

    public boolean isOffline() {
        return offline;
    }

    public boolean isFieldRedLeft() {return field_red_left;}

    public String getUsername() {return username;}

    public int getUser_id() {return user_id;}

    public int getLast_question() { return last_question; }

    static public CyberScouterConfig getConfig(SQLiteDatabase db) {
        CyberScouterConfig ret = null;
        Cursor cursor = null;
        try {
            String[] projection = {
                    CyberScouterContract.ConfigEntry._ID,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_ROLE,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_ROLE_COL,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT_ID,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_TABLET_NUM,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_OFFLINE,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_FIELD_REDLEFT,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_USERNAME,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_USERID,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_LASTQUESTION
            };

            String sortOrder =
                    CyberScouterContract.ConfigEntry._ID + " ASC";

            cursor = db.query(
                    CyberScouterContract.ConfigEntry.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    null,              // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );

            Integer i;
            if( 0 < cursor.getCount() && cursor.moveToFirst()) {
                ret = new CyberScouterConfig();
                /* Read the config values from SQLite */
                ret.role = cursor.getString(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_ROLE));
                ret.role_col = cursor.getString(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_ROLE_COL));
                ret.event = cursor.getString(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT));
                ret.event_id = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT_ID));
                ret.tablet_num = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_TABLET_NUM));
                ret.offline = (cursor.getInt(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_OFFLINE))==1);
                ret.field_red_left = (cursor.getInt(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_FIELD_REDLEFT))==1);
                ret.username = cursor.getString(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_USERNAME));
                ret.user_id = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_USERID));
                ret.last_question = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_LASTQUESTION));

                /* If there's more than one config row -- we only want one */
                if(1 < cursor.getCount()) {
                    /* so delete all but the most recent config row */
                    i = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.ConfigEntry._ID));
                    String selection = CyberScouterContract.ConfigEntry._ID + " <> ?";
                    String[] selectionArgs = { i.toString() };
                    int deletedRows = db.delete(CyberScouterContract.ConfigEntry.TABLE_NAME, selection, selectionArgs);
                }

            }

            return ret;

        } catch (Exception e) {
            e.printStackTrace();
            throw(e);
        } finally {
            if(null != cursor && !cursor.isClosed())
                cursor.close();
        }
    }

    static void setLastQuestion(SQLiteDatabase db, int nextQuestion) {
        ContentValues values = new ContentValues();
        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_LASTQUESTION, nextQuestion);
        int count = db.update(
                CyberScouterContract.ConfigEntry.TABLE_NAME,
                values,
                null,
                null);
    }

}
