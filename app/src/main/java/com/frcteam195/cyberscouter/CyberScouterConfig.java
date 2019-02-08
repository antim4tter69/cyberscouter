package com.frcteam195.cyberscouter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class CyberScouterConfig {
    private String role;
    private String event;
    private int tablet_num;
    private boolean offline;

    public CyberScouterConfig(){}

    public String getRole() {
        return role;
    }

    public String getEvent() {
        return event;
    }

    public int getTablet_num() {
        return tablet_num;
    }

    public boolean isOffline() {
        return offline;
    }

    static public CyberScouterConfig getConfig(SQLiteDatabase db) {
        CyberScouterConfig ret = null;
        Cursor cursor = null;
        try {
            String[] projection = {
                    CyberScouterContract.ConfigEntry._ID,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_ROLE,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_TABLET_NUM,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_OFFLINE
            };

            String sortOrder =
                    CyberScouterContract.ConfigEntry._ID + " DESC";

            cursor = db.query(
                    CyberScouterContract.ConfigEntry.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    null,              // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );

            TextView tv = null;
            String tmp = null;
            Integer i = 0;
            if( 0 < cursor.getCount() && cursor.moveToFirst()) {
                ret = new CyberScouterConfig();
                /* Read the config values from SQLite */
                ret.role = cursor.getString(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_ROLE));
                ret.event = cursor.getString(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT));
                ret.tablet_num = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_TABLET_NUM));
                ret.offline = (cursor.getInt(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_OFFLINE))==1);

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

}
