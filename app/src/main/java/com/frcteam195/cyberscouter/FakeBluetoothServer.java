package com.frcteam195.cyberscouter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONObject;

public class FakeBluetoothServer {

    final public static boolean bUseFakeBluetoothServer = true;
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
}
//6

