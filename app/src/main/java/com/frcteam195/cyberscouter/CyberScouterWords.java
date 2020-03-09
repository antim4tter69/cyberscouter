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

public class CyberScouterWords {
    final static String WORDS_UPDATED_FILTER = "frcteam195_cyberscouterwords_words_updated_intent_filter";

    private int WordID;
    private String Word;
    private int DisplayWordOrder;

    void putWord(SQLiteDatabase db) {
        try {
            ContentValues values = new ContentValues();

            values.put(CyberScouterContract.Words.COLUMN_NAME_WORD_ID, WordID);
            values.put(CyberScouterContract.Words.COLUMN_NAME_WORD, Word);
            values.put(CyberScouterContract.Words.COLUMN_NAME_DISPLAY_WORD_ORDER, DisplayWordOrder);

            long newRowId = db.insert(CyberScouterContract.Words.TABLE_NAME,
                    null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setWordsLocal(SQLiteDatabase db, String words_json) {
        try {
            JSONArray ja = new JSONArray(words_json);
            for(int i=0 ; i<ja.length(); ++i) {
                JSONObject jo = ja.getJSONObject(i);
                ContentValues values = new ContentValues();

                values.put(CyberScouterContract.Words.COLUMN_NAME_WORD_ID, WordID);
                values.put(CyberScouterContract.Words.COLUMN_NAME_WORD, Word);
                values.put(CyberScouterContract.Words.COLUMN_NAME_DISPLAY_WORD_ORDER, DisplayWordOrder);

                long newRowId = db.insertWithOnConflict(CyberScouterContract.Words.TABLE_NAME,
                        null, values, SQLiteDatabase.CONFLICT_REPLACE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String getWordsRemote(AppCompatActivity activity) {
        String ret = null;

        try {
            BluetoothComm btcomm = new BluetoothComm();
            String response = btcomm.getWords(activity, null);
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

    static public void getWordsWebService(final AppCompatActivity activity) {
        RequestQueue rq = Volley.newRequestQueue(activity);
        String url = String.format("%s/words", FakeBluetoothServer.webServiceBaseUrl);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Intent i = new Intent(WORDS_UPDATED_FILTER);
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

    public int getWordID() {
        return WordID;
    }

    public String getWord() {
        return Word;
    }

    public int getDisplayWordOrder() {
        return DisplayWordOrder;
    }
}
