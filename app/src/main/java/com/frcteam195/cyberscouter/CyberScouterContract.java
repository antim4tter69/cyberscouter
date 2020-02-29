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
        public static final String COLUMN_NAME_MATCH_NUMBER = "MatchNo";
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
        public static final String COLUMN_NAME_AUTOMOVEBONUS = "AutoMoveBonus";
        public static final String COLUMN_NAME_AUTOBALLLOW = "AutoBallLow";
        public static final String COLUMN_NAME_AUTOBALLOUTER = "AutoBallOuter";
        public static final String COLUMN_NAME_AUTOBALLINNER = "AutoBallInner";
        public static final String COLUMN_NAME_AUTOPENALTY = "AutoPenalty";
        public static final String COLUMN_NAME_TELEBALLLOWZONE1 = "TeleBallLowZone1";
        public static final String COLUMN_NAME_TELEBALLOUTERZONE1 = "TeleBallOuterZone1";
        public static final String COLUMN_NAME_TELEBALLINNERZONE1 = "TeleBallInnerZone1";
        public static final String COLUMN_NAME_TELEBALLOUTERZONE2 = "TeleBallOuterZone2";
        public static final String COLUMN_NAME_TELEBALLINNERZONE2 = "TeleBallInnerZone2";
        public static final String COLUMN_NAME_TELEBALLOUTERZONE3 = "TeleBallOuterZone3";
        public static final String COLUMN_NAME_TELEBALLINNERZONE3 = "TeleBallInnerZone3";
        public static final String COLUMN_NAME_TELEBALLOUTERZONE4 = "TeleBallOuterZone4";
        public static final String COLUMN_NAME_TELEBALLINNERZONE4 = "TeleBallInnerZone4";
        public static final String COLUMN_NAME_TELEBALLOUTERZONE5 = "TeleBallOuterZone5";
        public static final String COLUMN_NAME_TELEBALLINNERZONE5 = "TeleBallInnerZone5";
        public static final String COLUMN_NAME_TELEWHEELSTAGE2TIME = "TeleWheelStage2Time";
        public static final String COLUMN_NAME_TELEWHEELSTAGE2STATUS = "TeleWheelStage2Status";
        public static final String COLUMN_NAME_TELEWHEELSTAGE2ATTEMPTS = "TeleWheelStage2Attempts";
        public static final String COLUMN_NAME_TELEWHEELSTAGE3TIME = "TeleWheelStage3Time";
        public static final String COLUMN_NAME_TELEWHEELSTAGE3STATUS = "TeleWheelStage3Status";
        public static final String COLUMN_NAME_TELEWHEELSTAGE3ATTEMPTS = "TeleWheelStage3Attempts";
        public static final String COLUMN_NAME_CLIMBSTATUS = "ClimbStatus";
        public static final String COLUMN_NAME_CLIMBHEIGHT = "ClimbHeight";
        public static final String COLUMN_NAME_CLIMBPOSITION = "ClimbPosition";
        public static final String COLUMN_NAME_CLIMBMOVEONBAR = "ClimbMoveOnBar";
        public static final String COLUMN_NAME_CLIMBLEVELSTATUS = "ClimbLevelStatus";
        public static final String COLUMN_NAME_SUMMBROKEDOWN = "SummBrokeDown";
        public static final String COLUMN_NAME_SUMMLOSTCOMM = "SummLostComm";
        public static final String COLUMN_NAME_SUMMSUBSYSTEMBROKE = "SummSubSystemBroke";
        public static final String COLUMN_NAME_SUMMGROUNDPICKUP = "SummGroundPickup";
        public static final String COLUMN_NAME_SUMMHOPPERLOAD = "SummHopperLoad";
        public static final String COLUMN_NAME_SUMMPLAYEDDEFENSE = "SummPlayedDefense";
        public static final String COLUMN_NAME_SUMMDEFPLAYEDAGAINST = "SummDefPlayedAgainst";
        public static final String COLUMN_NAME_UPLOADSTATUS = "UploadStatus";
    }

    public static class Questions implements BaseColumns {
        public static final String TABLE_NAME = "Questions";
        public static final String COLUMN_NAME_QUESTIONID = "QuestionId";
        public static final String COLUMN_NAME_EVENTID = "EventID";
        public static final String COLUMN_NAME_QUESTIONNUMBER = "QuestionNumber";
        public static final String COLUMN_NAME_QUESTIONTEXT = "QuestionText";
        public static final String COLUMN_NAME_ANSWERS = "Answers";
    }

    public static class Teams implements BaseColumns {
        public static final String TABLE_NAME = "Teams";
        public static final String COLUMN_NAME_TEAM = "Team";
        public static final String COLUMN_NAME_TEAM_NAME = "TeamName";
        public static final String COLUMN_NAME_TEAM_LOCATION = "TeamLocation";
        public static final String COLUMN_NAME_NUM_WHEELS = "NumWheels";
        public static final String COLUMN_NAME_DRIVE_MOTORS = "NumDriveMotors";
        public static final String COLUMN_NAME_WHEEL_TYPE_ID = "WheelTypeID";
        public static final String COLUMN_NAME_DRIVE_TYPE_ID = "DriveTypeID";
        public static final String COLUMN_NAME_MOTOR_TYPE_ID = "MotorTypeID";
        public static final String COLUMN_NAME_LANGUAGE_ID = "LanguageID";
        public static final String COLUMN_NAME_SPEED = "Speed";
        public static final String COLUMN_NAME_GEAR_RATIO = "GearRatio";
        public static final String COLUMN_NAME_NUM_GEAR_SPEED = "NumGearSpeed";
        public static final String COLUMN_NAME_ROBOT_LENGTH = "RobotLength";
        public static final String COLUMN_NAME_ROBOT_WIDTH = "RobotWidth";
        public static final String COLUMN_NAME_ROBOT_HEIGHT = "RobotHeight";
        public static final String COLUMN_NAME_ROBOT_WEIGHT = "RobotWeight";
        public static final String COLUMN_NAME_PNEUMATICS = "Pneumatics";
        public static final String COLUMN_NAME_NUM_PRE_LOAD = "NumPreload";
        public static final String COLUMN_NAME_AUTO_BALLS_SCORED = "AutoBallsScored";
        public static final String COLUMN_NAME_MOVE_BONUS = "MoveBonus";
        public static final String COLUMN_NAME_AUTO_PICKUP = "AutoPickUp";
        public static final String COLUMN_NAME_AUTO_START_POS_ID = "AutoStartPosID";
        public static final String COLUMN_NAME_AUTO_SUMMARY = "AutoSummary";
        public static final String COLUMN_NAME_TELE_BALLS_SCORED = "TeleBallsScored";
        public static final String COLUMN_NAME_MAX_BALL_CAPACITY = "MaxBallCapacity";
        public static final String COLUMN_NAME_COLOR_WHEEL = "ColorWheel";
        public static final String COLUMN_NAME_TELE_DEFENSE = "TeleDefense";
        public static final String COLUMN_NAME_TELE_DEFENSE_EVADE = "TeleDefenseEvade";
        public static final String COLUMN_NAME_TELE_STRATEGY = "TeleStrategy";
        public static final String COLUMN_NAME_CAN_CLIMB = "CanClimb";
        public static final String COLUMN_NAME_CENTER_CLIMB = "CenterClimb";
        public static final String COLUMN_NAME_CAN_MOVE_ON_BAR = "CanMoveOnBar";
        public static final String COLUMN_NAME_LOCKING_MECHANISM = "LockingMechanism";
        public static final String COLUMN_NAME_CLIMB_HEIGHT_ID = "ClimbHeightID";
        public static final String COLUMN_NAME_COLORWHEEL = "ObamaPrism";
    }
}
