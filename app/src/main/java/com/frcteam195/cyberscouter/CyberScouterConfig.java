package com.frcteam195.cyberscouter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

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

    static public CyberScouterConfig getConfig(AppCompatActivity activity) throws Exception {
        CyberScouterConfig ret = null;

        final BluetoothManager bluetoothManager = (BluetoothManager) activity.getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter _bluetoothAdapter = bluetoothManager.getAdapter();
        BluetoothComm btcomm = new BluetoothComm();
        String response = btcomm.getConfig(_bluetoothAdapter, Settings.Secure.getString(activity.getContentResolver(), "bluetooth_name"));
        JSONObject jo = new JSONObject(response);
        String result = (String)jo.get("result");
        if(result.equalsIgnoreCase("failed")){
            return ret;
        }
        JSONObject payload = jo.getJSONObject("payload");
        if(null != payload) {
            ret = new CyberScouterConfig();
            ret.event = payload.getString("event");
            ret.role = payload.getString("role");
            ret.event_id = payload.getInt("event_id");
        }

        return ret;
    }

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
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_USERID
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

    public void setConfig(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT_ID, event_id);
        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT, event);
        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_ROLE, role);
        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_ROLE_COL, role_col);
        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_FIELD_REDLEFT, field_red_left);
        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_OFFLINE, offline);
        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_TABLET_NUM, tablet_num);
        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_USERID, user_id);
        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_USERNAME, username);

        long newRowId = db.insert(CyberScouterContract.Users.TABLE_NAME, null, values);

    }
}
