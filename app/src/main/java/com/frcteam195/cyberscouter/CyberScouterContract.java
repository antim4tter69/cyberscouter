package com.frcteam195.cyberscouter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class CyberScouterContract {

    private CyberScouterContract(){}

    public static class ConfigEntry implements BaseColumns {
        public static final String TABLE_NAME = "config";
        public static final String COLUMN_NAME_ROLE = "role";
        public static final String COLUMN_NAME_ROLE_COL = "role_col";
        public static final String COLUMN_NAME_EVENT = "event";
        public static final String COLUMN_NAME_EVENT_ID = "event_id";
        public static final String COLUMN_NAME_TABLET_NUM = "tablet_num";
        public static final String COLUMN_NAME_OFFLINE = "offline";
        public static final String COLUMN_NAME_FIELD_REDLEFT = "field_red_left";
        public static final String COLUMN_NAME_USERNAME = "username";
    }

    public static class Events implements BaseColumns {
        public static final String TABLE_NAME = "Events";
        public static final String COLUMN_NAME_EVENTID = "EventId";
        public static final String COLUMN_NAME_EVENTNAME = "EventName";
        public static final String COLUMN_NAME_EVENTLOCATION = "EventLocation";
        public static final String COLUMN_NAME_STARTDATE = "StartDate";
        public static final String COLUMN_NAME_ENDDATE = "EndDate";
        public static final String COLUMN_NAME_CURRENTEVENT = "CurrentEvent";
    }

    public static class Matches implements BaseColumns {
        public static final String TABLE_NAME = "Matches";
        public static final String COLUMN_NAME_MATCHID = "MatchID";
        public static final String COLUMN_NAME_EVENTID = "EventID";
        public static final String COLUMN_NAME_LEVELIIISCOUTERID = "LevelIIIScouterID";
        public static final String COLUMN_NAME_MATCHNO = "MatchNo";
        public static final String COLUMN_NAME_MATCHNOTAG = "MatchNoTag";
        public static final String COLUMN_NAME_REDTEAM1 = "RedTeam1";
        public static final String COLUMN_NAME_REDTEAM2 = "RedTeam2";
        public static final String COLUMN_NAME_REDTEAM3 = "RedTeam3";
        public static final String COLUMN_NAME_REDTEAM1TAG = "RedTeam1Tag";
        public static final String COLUMN_NAME_REDTEAM2TAG = "RedTeam2Tag";
        public static final String COLUMN_NAME_REDTEAM3TAG = "RedTeam3Tag";
        public static final String COLUMN_NAME_BLUETEAM1 = "BlueTeam1";
        public static final String COLUMN_NAME_BLUETEAM2 = "BlueTeam2";
        public static final String COLUMN_NAME_BLUETEAM3 = "BlueTeam3";
        public static final String COLUMN_NAME_BLUETEAM1TAG = "BlueTeam1Tag";
        public static final String COLUMN_NAME_BLUETEAM2TAG = "BlueTeam2Tag";
        public static final String COLUMN_NAME_BLUETEAM3TAG = "BlueTeam3Tag";
        public static final String COLUMN_NAME_MATCHVIDEO = "MatchVideo";
        public static final String COLUMN_NAME_COMMENTS = "Comments";
        public static final String COLUMN_NAME_ELIMINATIONMATCH = "EliminationMatch";
        public static final String COLUMN_NAME_STARTOFTELEOP = "StartOfTeleop";
        public static final String COLUMN_NAME_MATCHENDED = "MatchEnded";
    }

}
