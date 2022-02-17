package com.frcteam195.cyberscouter;

import android.support.v7.app.AppCompatActivity;
import org.json.JSONObject;
import java.net.InetSocketAddress;
import java.net.Socket;

public class FakeBluetoothServer {

    final public static boolean bUseFakeBluetoothServer = true;
    final private static String _webHost = "8zaof0vuah.execute-api.us-east-1.amazonaws.com";
    final public static String webServiceBaseUrl = String.format("https://%s", _webHost);

    final public static String fakeBluetoothComputerName = "Team 195 Scout 10";
    //Change the scout number to change which tablet you are emulating, the numbers correspond as follows
    //Scout 1-3 Red, 4-6 Blue, 7-9 Level 2 Scouting, 10 Pit

    public FakeBluetoothServer() {
    }

    public void getResponse(AppCompatActivity activity, JSONObject obj) {

        try {
            String cmd = obj.getString("cmd");
            switch (cmd) {
                case "get-config":
                    CyberScouterConfig.getConfigWebService(activity, fakeBluetoothComputerName);
                    break;
                case "get-users":
                    CyberScouterUsers.getUsersWebService(activity);
                    break;
                case "get-matches":
                    JSONObject payload = obj.getJSONObject("payload");
                    int eventId = payload.getInt("eventId");
                    CyberScouterMatchScouting.getMatchesWebService(activity, eventId);
                    break;
                case "get-matches-l2":
                    payload = obj.getJSONObject("payload");
                    eventId = payload.getInt("eventId");
                    CyberScouterMatchScoutingL2.getMatchesL2WebService(activity, eventId);
                    break;
                case "get-teams":
                    CyberScouterTeams.getTeamsWebService(activity);
                    break;
                case "get-words":
                    CyberScouterWords.getWordsWebService(activity);
                    break;
                case "put-match-scouting":
                case "put-teams":
                    break;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean pingWebHost() {
        try {
            Socket sock = new Socket();
            sock.connect(new InetSocketAddress(_webHost, 443), 900);
            if(sock.isConnected()) {
                sock.close();
                return (true);
            } else {
//                return(false);
                return(true);
            }
        } catch(Exception e) {
//            return(false);
            return(true);
        }
    }

}

