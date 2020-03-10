package com.frcteam195.cyberscouter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

public class CyberScouterTimeCode {

    public static int getLast_update(SQLiteDatabase db) {
        Cursor cursor = null;
        int ret = 0;


        String[] projection = {
                CyberScouterContract.TimeCode.COLUMN_NAME_LAST_UPDATE
        };

        cursor = db.query(
                CyberScouterContract.TimeCode.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        if (0 < cursor.getCount()) {
            cursor.moveToNext();
            ret = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.TimeCode.COLUMN_NAME_LAST_UPDATE));
        }

        return ret;
    }

    public static void setLast_update(SQLiteDatabase db, int lastUpdate) {
        try {
            ContentValues values = new ContentValues();

            values.put(CyberScouterContract.TimeCode.COLUMN_NAME_LAST_UPDATE, lastUpdate);

            long newRowId = db.insert(CyberScouterContract.TimeCode.TABLE_NAME,
                    null, values);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
