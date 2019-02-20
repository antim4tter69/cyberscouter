package com.frcteam195.cyberscouter;

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
        public static final String COLUMN_NAME_USERID = "user_id";
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
        public static final String COLUMN_NAME_STARTOFTELEOP = "StartOfTeleop";
        public static final String COLUMN_NAME_MATCHENDED = "MatchEnded";
        public static final String COLUMN_NAME_QUESTIONSANSWERED = "QuestionsAnswered";
        public static final String COLUMN_NAME_SCOUTINGSTATUS = "ScoutingStatus";
        public static final String COLUMN_NAME_AREASTOREVIEW = "AreasToReview";
        public static final String COLUMN_NAME_COMPLETE = "Complete";
        public static final String COLUMN_NAME_AUTOSTARTPOS = "AutoStartPos";
        public static final String COLUMN_NAME_AUTOPRELOAD = "AutoPreload";
        public static final String COLUMN_NAME_AUTODIDNOTSHOW = "AutoDidNotShow";
        public static final String COLUMN_NAME_AUTOMOVEBONUS = "AutoMoveBonus";
        public static final String COLUMN_NAME_AUTOCSCARGO = "AutoCSCargo";
        public static final String COLUMN_NAME_AUTOCSHATCH = "AutoCSHatch";
        public static final String COLUMN_NAME_AUTORSCARGOLOW = "AutoRSCargoLow";
        public static final String COLUMN_NAME_AUTORSCARGOMED = "AutoRSCargoMed";
        public static final String COLUMN_NAME_AUTORSCARGOHIGH = "AutoRSCargoHigh";
        public static final String COLUMN_NAME_AUTORSHATCHFARLOW = "AutoRSHatchFarLow";
        public static final String COLUMN_NAME_AUTORSHATCHFARMED = "AutoRSHatchFarMed";
        public static final String COLUMN_NAME_AUTORSHATCHFARHIGH = "AutoRSHatchFarHigh";
        public static final String COLUMN_NAME_AUTORSHATCHNEARLOW = "AutoRSHatchNearLow";
        public static final String COLUMN_NAME_AUTORSHATCHNEARMED = "AutoRSHatchNearMed";
        public static final String COLUMN_NAME_AUTORSHATCHNEARHIGH = "AutoRSHatchNearHigh";
        public static final String COLUMN_NAME_TELECSCARGO = "TeleCSCargo";
        public static final String COLUMN_NAME_TELECSHATCH = "TeleCSHatch";
        public static final String COLUMN_NAME_TELERSCARGOLOW = "TeleRSCargoLow";
        public static final String COLUMN_NAME_TELERSCARGOMED = "TeleRSCargoMed";
        public static final String COLUMN_NAME_TELERSCARGOHIGH = "TeleRSCargoHigh";
        public static final String COLUMN_NAME_TELERSHATCHFARLOW = "TeleRSHatchFarLow";
        public static final String COLUMN_NAME_TELERSHATCHFARMED = "TeleRSHatchFarMed";
        public static final String COLUMN_NAME_TELERSHATCHFARHIGH = "TeleRSHatchFarHigh";
        public static final String COLUMN_NAME_TELERSHATCHNEARLOW = "TeleRSHatchNearLow";
        public static final String COLUMN_NAME_TELERSHATCHNEARMED = "TeleRSHatchNearMed";
        public static final String COLUMN_NAME_TELERSHATCHNEARHIGH = "TeleRSHatchNearHigh";
        public static final String COLUMN_NAME_CLIMBSCORE = "ClimbScore";
        public static final String COLUMN_NAME_CLIMBASSIST = "ClimbAssist";
        public static final String COLUMN_NAME_SUMMHATCHGRDPICKUP = "SummHatchGrdPickup";
        public static final String COLUMN_NAME_SUMMLOSTCOMM = "SummLostComm";
        public static final String COLUMN_NAME_SUMMBROKE = "SummBroke";
        public static final String COLUMN_NAME_SUMMTIPOVER = "SummTipOver";
        public static final String COLUMN_NAME_SUMMSUBSYSTEMBROKE = "SummSubsystemBroke";
        public static final String COLUMN_NAME_ANSWER01 = "Answer01";
        public static final String COLUMN_NAME_ANSWER02 = "Answer02";
        public static final String COLUMN_NAME_ANSWER03 = "Answer03";
        public static final String COLUMN_NAME_ANSWER04 = "Answer04";
        public static final String COLUMN_NAME_ANSWER05 = "Answer05";
        public static final String COLUMN_NAME_ANSWER06 = "Answer06";
        public static final String COLUMN_NAME_ANSWER07 = "Answer07";
        public static final String COLUMN_NAME_ANSWER08 = "Answer08";
        public static final String COLUMN_NAME_ANSWER09 = "Answer09";
        public static final String COLUMN_NAME_ANSWER10 = "Answer10";
        public static final String COLUMN_NAME_UPLOADSTATUS = "UploadStatus";
    }

}
