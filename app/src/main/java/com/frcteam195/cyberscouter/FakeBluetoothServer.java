package com.frcteam195.cyberscouter;

public class FakeBluetoothServer {
    final public static boolean bUseFakeBluetoothServer = true;

    public static String getResponse(String cmd) {
        String response = "";
        if(cmd == "get-config") {
            response = "{'result':'success', 'payload': {'event_id': 1, 'event':'Winter Nationals', 'role':'Blue 2'}}";
        }

        return(response);
    }
}
