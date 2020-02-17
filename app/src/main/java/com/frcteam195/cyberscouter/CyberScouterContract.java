package com.frcteam195.cyberscouter;

import android.provider.BaseColumns;

public class CyberScouterContract {

    private CyberScouterContract(){}

    public static class ConfigEntry implements BaseColumns {
        public static final String TABLE_NAME = "config";
        public static final String COLUMN_NAME_ALLIANCE_STATIOM = "alliance_station";
        public static final String COLUMN_NAME_ALLIANCE_STATION_ID = "alliance_station_id";
        public static final String COLUMN_NAME_EVENT = "event";
        public static final String COLUMN_NAME_EVENT_ID = "event_id";
        public static final String COLUMN_NAME_TABLET_NUM = "tablet_num";
        public static final String COLUMN_NAME_OFFLINE = "offline";
        public static final String COLUMN_NAME_FIELD_REDLEFT = "field_red_left";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_USERID = "user_id";
        public static final String COLUMN_NAME_COMPUTER_TYPE_ID = "computer_type_id";
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

    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "Users";
        public static final String COLUMN_NAME_USERID = "UserId";
        public static final String COLUMN_NAME_FIRSTNAME = "FirstName";
        public static final String COLUMN_NAME_LASTNAME = "LastName";
        public static final String COLUMN_NAME_CELLPHONE = "CellPhone";
        public static final String COLUMN_NAME_EMAIL = "Email";
    }

    public static class MatchScouting implements BaseColumns {
        public static final String TABLE_NAME = "[Match Scouting]";
        public static final String COLUMN_NAME_MATCHSCOUTINGID = "MatchScoutingID";
        public static final String COLUMN_NAME_EVENTID = "EventID";
        public static final String COLUMN_NAME_MATCHID = "MatchID";
        public static final String COLUMN_NAME_COMPUTERID = "ComputerID";
        public static final String COLUMN_NAME_SCOUTERID = "ScouterID";
        public static final String COLUMN_NAME_REVIEWERID = "ReviewerID";
        public static final String COLUMN_NAME_TEAM = "Team";
        public static final String COLUMN_NAME_TEAMMATCHNO = "TeamMatchNo";
        public static final String COLUMN_NAME_ALLIANCESTATIONID = "AllianceStationID";
        public static final String COLUMN_NAME_MATCHENDED = "MatchEnded";
        public static final String COLUMN_NAME_SCOUTINGSTATUS = "ScoutingStatus";
        public static final String COLUMN_NAME_AREASTOREVIEW = "AreasToReview";
        public static final String COLUMN_NAME_COMPLETE = "Complete";
        public static final String COLUMN_NAME_AUTOSTARTPOS = "AutoStartPos";
        public static final String COLUMN_NAME_AUTODIDNOTSHOW = "AutoDidNotShow";
        public static final String COLUMN_NAME_AUTOPENALTIES = "AutoPenalties";
        public static final String COLUMN_NAME_AUTOMOVEBONUS = "AutoMoveBonus";
        public static final String COLUMN_NAME_AUTOBALLLOW = "AutoBallLow";
        public static final String COLUMN_NAME_AUTOBALLOUTER = "AutoBallOuter";
        public static final String COLUMN_NAME_AUTOBALLINNER = "AutoBallInner";
        public static final String COLUMN_NAME_AUTOPENALTY = "AutoPenalty";
        public static final String COLUMN_NAME_TELEBALLLOWZONE1 = "TeleBallLowZone1";
        public static final String COLUMN_NAME_TELEBALLOUTERZONE1 = "TeleBallOuterZone1";
        public static final String COLUMN_NAME_TELEBALLINNERZONE1 = "TeleBallInnerZone1";
        public static final String COLUMN_NAME_TELEBALLLOWZONE2 = "TeleBallLowZone2";
        public static final String COLUMN_NAME_TELEBALLOUTERZONE2 = "TeleBallOuterZone2";
        public static final String COLUMN_NAME_TELEBALLINNERZONE2 = "TeleBallInnerZone2";
        public static final String COLUMN_NAME_TELEBALLLOWZONE3 = "TeleBallLowZone3";
        public static final String COLUMN_NAME_TELEBALLOUTERZONE3 = "TeleBallOuterZone3";
        public static final String COLUMN_NAME_TELEBALLINNERZONE3 = "TeleBallInnerZone3";
        public static final String COLUMN_NAME_TELEBALLLOWZONE4 = "TeleBallLowZone4";
        public static final String COLUMN_NAME_TELEBALLOUTERZONE4 = "TeleBallOuterZone4";
        public static final String COLUMN_NAME_TELEBALLINNERZONE4 = "TeleBallInnerZone4";
        public static final String COLUMN_NAME_TELEBALLLOWZONE5 = "TeleBallLowZone5";
        public static final String COLUMN_NAME_TELEBALLOUTERZONE5 = "TeleBallOuterZone5";
        public static final String COLUMN_NAME_TELEBALLINNERZONE5 = "TeleBallInnerZone5";
        public static final String COLUMN_NAME_TELEWHEELSTAGE2TIME = "TeleWheelStage2Time";
        public static final String COLUMN_NAME_TELEWHEELSTAGE2STATUS = "TeleWheelStage2Status";
        public static final String COLUMN_NAME_TELEWHEELSTAGE2ATTEMPTS = "TeleWheelStage2Attempts";
        public static final String COLUMN_NAME_TELEWHEELSTAGE3TIME = "TeleWheelStage3Time";
        public static final String COLUMN_NAME_TELEWHEELSTAGE3STATUS = "TeleWheelStage3Status";
        public static final String COLUMN_NAME_TELEWHEELSTAGE3ATTEMPTS = "TeleWheelStage3Attempts";
        public static final String COLUMN_NAME_CLIMBHEIGHT = "ClimbHeight";
        public static final String COLUMN_NAME_CLIMBPOSITION = "ClimbPosition";
        public static final String COLUMN_NAME_CLIMBMOVEONBAR = "ClimbMoveOnBar";
        public static final String COLUMN_NAME_CLIMBLEVELSTATUS = "ClimbLevelStatus";
        public static final String COLUMN_NAME_SUMMLOSTCOMM = "SummLostComm";
        public static final String COLUMN_NAME_SUMMBROKEDOWN = "SummBrokeDown";
        public static final String COLUMN_NAME_SUMMGROUNDPICKUP = "SummGroundPickup";
        public static final String COLUMN_NAME_SUMMSUBSYSTEMBROKE = "SummSubSystemBroke";
        public static final String COLUMN_NAME_SUMMHOPPERLOAD = "SummHopperLoad";
        public static final String COLUMN_NAME_SUMMPLAYEDDEFENSE = "SummPlayedDefense";
        public static final String COLUMN_NAME_SUMMDEFPLAYEDAGAINST = "SummDefPlayedAgainst";
        public static final String COLUMN_NAME_UPLOADSTATUS = "UploadStatus";
        public static final String COLUMN_NAME_BROKEDOWN = "BrokeDown";
        public static final String COLUMN_NAME_SUBSYSTEMBROKEDOWN = "SubSystemBrokeDown";
        public static final String COLUMN_NAME_GROUNDPICKUP = "GroundPickup";
        public static final String COLUMN_NAME_HOPPERLOAD =  "HopperLoad";
        public static final String COLUMN_NAME_DEFENSEPLAYEDAGAINST = "DefensePlayedAgainst";
        public static final String COLUMN_NAME_LEVEL = "Level";
        public static final String COLUMN_NAME_MOVEDONBAR = "MovedOnBar";
        public static final String COLUMN_NAME_PLAYEDDEFENSE = "PlayedDefense";
        public static final String COLUMN_NAME_CLIMBSTATUS = "ClimbStatus";

    }

    public static class Questions implements BaseColumns {
        public static final String TABLE_NAME = "Questions";
        public static final String COLUMN_NAME_QUESTIONID = "QuestionId";
        public static final String COLUMN_NAME_EVENTID = "EventID";
        public static final String COLUMN_NAME_QUESTIONNUMBER = "QuestionNumber";
        public static final String COLUMN_NAME_QUESTIONTEXT = "QuestionText";
        public static final String COLUMN_NAME_ANSWERS = "Answers";
    }
}
