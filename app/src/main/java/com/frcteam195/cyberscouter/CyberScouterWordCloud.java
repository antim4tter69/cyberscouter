package com.frcteam195.cyberscouter;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class CyberScouterWordCloud {
    final static String WORD_CLOUD_UPDATED_FILTER = "frcteam195_cyberscouterwordcloud_word_cloud_updated_intent_filter";

    private int EventID;
    private int MatchID;
    private int MatchScoutingID;
    private int Seq;
    private String Team;
    private int WordID;

    void putWordCloud(SQLiteDatabase db) {
        try {
            ContentValues values = new ContentValues();

            values.put(CyberScouterContract.WordCloud.COLUMN_NAME_EVENT_ID, EventID);
            values.put(CyberScouterContract.WordCloud.COLUMN_NAME_MATCH_ID, MatchID);
            values.put(CyberScouterContract.WordCloud.COLUMN_NAME_MATCH_SCOUTING_ID, MatchScoutingID);
            values.put(CyberScouterContract.WordCloud.COLUMN_NAME_SEQ, Seq);
            values.put(CyberScouterContract.WordCloud.COLUMN_NAME_TEAM, Team);
            values.put(CyberScouterContract.WordCloud.COLUMN_NAME_WORD_ID, WordID);
            values.put(CyberScouterContract.WordCloud.COLUMN_NAME_DONE_SCOUTING, 0);
            values.put(CyberScouterContract.WordCloud.COLUMN_NAME_UPLOAD_STATUS, UploadStatus.NOT_UPLOADED);

            long newRowId = db.insertWithOnConflict(CyberScouterContract.WordCloud.TABLE_NAME,
                    null, values, SQLiteDatabase.CONFLICT_REPLACE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String getWordCloudRemote(AppCompatActivity activity) {
        String ret = null;

        try {
            BluetoothComm btcomm = new BluetoothComm();
            String response = btcomm.getWordCloud(activity, 0);
            if (null != response) {
                JSONObject jo = new JSONObject(response);
                String result = jo.getString("result");
                if ("failure" != result) {
                    if(result.equalsIgnoreCase("skip")) {
                        ret = "skip";
                    } else {
                        JSONArray payload = jo.getJSONArray("payload");
                        ret = payload.toString();
                    }
                } else {
                    ret = "skip";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (ret);
    }

    static public void getWordCloudWebService(final AppCompatActivity activity) {
        RequestQueue rq = Volley.newRequestQueue(activity);
        String url = String.format("%s/word-cloud", FakeBluetoothServer.webServiceBaseUrl);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Intent i = new Intent(WORD_CLOUD_UPDATED_FILTER);
                            i.putExtra("cyberscouterwords", response);
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

    public static void setDoneScouting(SQLiteDatabase db) {
        try {
            ContentValues values = new ContentValues();

            values.put(CyberScouterContract.WordCloud.COLUMN_NAME_DONE_SCOUTING, 1);
            values.put(CyberScouterContract.WordCloud.COLUMN_NAME_UPLOAD_STATUS, UploadStatus.READY_TO_UPLOAD);

            long newRowId = db.update(CyberScouterContract.WordCloud.TABLE_NAME, values, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getEventID() {
        return EventID;
    }

    public int getMatchID() {
        return MatchID;
    }

    public int getMatchScoutingID() {
        return MatchScoutingID;
    }

    public int getSeq() {
        return Seq;
    }

    public String getTeam() {
        return Team;
    }

    public int getWordID() {
        return WordID;
    }

    public void setEventID(int eventID) {
        EventID = eventID;
    }

    public void setMatchID(int matchID) {
        MatchID = matchID;
    }

    public void setMatchScoutingID(int matchScoutingID) {
        MatchScoutingID = matchScoutingID;
    }

    public void setSeq(int seq) {
        Seq = seq;
    }

    public void setTeam(String team) {
        Team = team;
    }

    public void setWordID(int wordID) {
        WordID = wordID;
    }
}
