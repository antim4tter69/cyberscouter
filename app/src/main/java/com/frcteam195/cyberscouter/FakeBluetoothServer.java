package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class FakeBluetoothServer {

    final public static boolean bUseFakeBluetoothServer = false;
    final public static String webServiceBaseUrl = "https://8zaof0vuah.execute-api.us-east-1.amazonaws.com";

    final public static String fakeBluetoothComputerName = "Team 195 Scout 1";

    public FakeBluetoothServer() {
    }

    public void getResponse(AppCompatActivity activity, JSONObject obj) {

        try {

            String cmd = obj.getString("cmd");
            if (cmd == "get-config") {
                CyberScouterConfig.getConfigWebService(activity, fakeBluetoothComputerName);
            } else if (cmd == "get-users") {
                CyberScouterUsers.getUsersWebService(activity);
            } else if (cmd == "get-matches") {
                JSONObject payload = obj.getJSONObject("payload");
                int eventId = payload.getInt("eventId");
                CyberScouterMatchScouting.getMatchesWebService(activity, eventId);
            } else if (cmd == "get-teams") {
                CyberScouterTeams.getTeamsWebService(activity);
            } else if( cmd == "get-words") {
                CyberScouterWordCloud.getWordsWebService(activity);
            } else if( cmd == "get-word-cloud") {

            } else if( cmd == "put-match-scouting") {

            } else if( cmd == "put-teams") {

            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return;
    }

    public void pingServer(AppCompatActivity activity) {
        RequestQueue rq = Volley.newRequestQueue(activity);
        String url = String.format("%s/ping", FakeBluetoothServer.webServiceBaseUrl);

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
}

