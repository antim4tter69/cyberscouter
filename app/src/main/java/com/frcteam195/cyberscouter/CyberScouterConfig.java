package com.frcteam195.cyberscouter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class CyberScouterConfig {
    public final static String CONFIG_UPDATED_FILTER = "frcteam195_cyberscouterconfig_config_updated_intent_filter";

    final static String UNKNOWN_ROLE = "Unknown Role";
    final static String UNKNOWN_EVENT = "Unknown Event";
    final static int UNKNOWN_USER_IDX = -1;

    public static final int CONFIG_COMPUTER_TYPE_LEVEL_1_SCOUTER = 0;
    public static final int CONFIG_COMPUTER_TYPE_LEVEL_2_SCOUTER = 1;
    public static final int CONFIG_COMPUTER_TYPE_LEVEL_PIT_SCOUTER = 2;

    private String alliance_station;
    private int alliance_station_id;
    private String event;
    private int event_id;
    private int tablet_num;
    private boolean offline;
    private boolean field_red_left;
    private String username;
    private int user_id;
    private int computer_type_id;

    public CyberScouterConfig() {
    }

    public String getAlliance_station() {
        return alliance_station;
    }

    public int getAlliance_station_id() {
        return alliance_station_id;
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

    public boolean isFieldRedLeft() {
        return field_red_left;
    }

    public String getUsername() {
        return username;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getComputer_type_id() {
        return computer_type_id;
    }

    static public void getConfigRemote(AppCompatActivity activity) {

        try {
            BluetoothComm btcomm = new BluetoothComm();
            String response = btcomm.getConfig(activity);
            JSONObject jo = new JSONObject(response);
            String result = (String) jo.get("result");
            if (result.equalsIgnoreCase("failed")) {
                return;
            }
            JSONObject payload = jo.getJSONObject("payload");
            if (null != payload) {
                Intent i = new Intent(CONFIG_UPDATED_FILTER);
                i.putExtra("cyberscouterconfig", payload.toString());
                activity.sendBroadcast(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return;
    }

    static public CyberScouterConfig getConfig(SQLiteDatabase db) {
        CyberScouterConfig ret = null;
        Cursor cursor = null;
        try {
            String[] projection = {
                    CyberScouterContract.ConfigEntry._ID,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_ALLIANCE_STATIOM,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_ALLIANCE_STATION_ID,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT_ID,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_TABLET_NUM,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_OFFLINE,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_FIELD_REDLEFT,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_USERNAME,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_USERID,
                    CyberScouterContract.ConfigEntry.COLUMN_NAME_COMPUTER_TYPE_ID
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

            Integer i;
            if (0 < cursor.getCount() && cursor.moveToFirst()) {
                ret = new CyberScouterConfig();
                /* Read the config values from SQLite */
                ret.alliance_station = cursor.getString(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_ALLIANCE_STATIOM));
                ret.alliance_station_id = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_ALLIANCE_STATION_ID));
                ret.event = cursor.getString(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT));
                ret.event_id = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT_ID));
                ret.tablet_num = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_TABLET_NUM));
                ret.offline = (cursor.getInt(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_OFFLINE)) == 1);
                ret.field_red_left = (cursor.getInt(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_FIELD_REDLEFT)) == 1);
                ret.username = cursor.getString(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_USERNAME));
                ret.user_id = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_USERID));
                ret.computer_type_id = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.ConfigEntry.COLUMN_NAME_COMPUTER_TYPE_ID));

                /* If there's more than one config row -- we only want one */
                if (1 < cursor.getCount()) {
                    /* so delete all but the most recent config row */
                    i = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.ConfigEntry._ID));
                    String selection = CyberScouterContract.ConfigEntry._ID + " <> ?";
                    String[] selectionArgs = {i.toString()};
                    int deletedRows = db.delete(CyberScouterContract.ConfigEntry.TABLE_NAME, selection, selectionArgs);
                }

            }

            return ret;

        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        } finally {
            if (null != cursor && !cursor.isClosed())
                cursor.close();
        }
    }

    static public void getConfigWebService(final Activity activity, String computerName) {
        String ret = null;

        RequestQueue rq = Volley.newRequestQueue(activity);
        String url = String.format("%s/config?computerName=%s", FakeBluetoothServer.webServiceBaseUrl, computerName);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Intent i = new Intent(CONFIG_UPDATED_FILTER);
                            i.putExtra("cyberscouterconfig", response);
                            activity.sendBroadcast(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        rq.add(stringRequest);
        return;
    }

    public static void setConfigLocal(SQLiteDatabase db, JSONObject jo) throws Exception {
        ContentValues values = new ContentValues();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT, jo.getString("EventName"));
        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_ALLIANCE_STATIOM, jo.getString("AllianceStation"));
        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_COMPUTER_TYPE_ID, jo.getString("ComputerTypeID"));
        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_ALLIANCE_STATION_ID, jo.getInt("AllianceStationID"));

        if(null != cfg) {
            db.update(
                    CyberScouterContract.ConfigEntry.TABLE_NAME,
                    values,
                    null,
                    null);
        } else {
            values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT_ID, jo.getString("EventID"));
            values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_USERNAME, "");
            values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_USERID, UNKNOWN_USER_IDX);
            db.insert(CyberScouterContract.ConfigEntry.TABLE_NAME, null, values);
        }

    }
}
