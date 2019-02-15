package com.frcteam195.cyberscouter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import java.util.Vector;

public class CyberScouterMatchScouting {

    private int matchScoutingID;
    private int eventID;
    private int matchID;
    private int computerID;
    private int scouterID;
    private int reviewerID;
    private String team;
    private int teamMatchNo;
    private int allianceStationID;
    private java.sql.Date startOfTeleop;
    private boolean matchEnded;
    private boolean questionsAnswered;
    private int scoutingStatus;
    private int areasToReview;
    private boolean complete;
    private int autoStartPos;
    private int autoPreload;
    private int autoDidNotShow;
    private int autoMoveBonus;
    private int autoCSCargo;
    private int autoCSHatch;
    private int autoRSCargoLow;
    private int autoRSCargoMed;
    private int autoRSCargoHigh;
    private int autoRSHatchFarLow;
    private int autoRSHatchFarMed;
    private int autoRSHatchFarHigh;
    private int autoRSHatchNearLow;
    private int autoRSHatchNearMed;
    private int autoRSHatchNearHigh;
    private int teleCSCargo;
    private int teleCSHatch;
    private int teleRSCargoLow;
    private int teleRSCargoMed;
    private int teleRSCargoHigh;
    private int teleRSHatchFarLow;
    private int teleRSHatchFarMed;
    private int teleRSHatchFarHigh;
    private int teleRSHatchNearLow;
    private int teleRSHatchNearMed;
    private int teleRSHatchNearHigh;
    private int climbScore;
    private int climbAssist;
    private int summHatchGrdPickup;
    private int summLostComm;
    private int summBroke;
    private int summTipOver;
    private int summSubsystemBroke;
    private int answer01;
    private int answer02;
    private int answer03;
    private int answer04;
    private int answer05;
    private int answer06;
    private int answer07;
    private int answer08;
    private int answer09;
    private int answer10;
    private int uploadStatus;


    CyberScouterMatchScouting[] getMatches(Connection conn, int eventId) {
        Vector<CyberScouterMatchScouting> csmv = new Vector<CyberScouterMatchScouting>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from " + CyberScouterContract.MatchScouting.TABLE_NAME +
                    " WHERE " +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_EVENTID + " = " + eventId +
                    " ORDER BY " + CyberScouterContract.MatchScouting.COLUMN_NAME_TEAMMATCHNO + " ASC");
            while (rs.next()) {
                CyberScouterMatchScouting csm = new CyberScouterMatchScouting();
                csm.matchScoutingID = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHSCOUTINGID));
                csm.eventID = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_EVENTID));
                csm.matchID = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHID));
                csm.computerID = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_COMPUTERID));
                csm.scouterID = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTERID));
                csm.reviewerID = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_REVIEWERID));
                csm.team = rs.getString(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_TEAM));
                csm.teamMatchNo = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_TEAMMATCHNO));
                csm.allianceStationID = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_ALLIANCESTATIONID));
                csm.startOfTeleop = rs.getDate(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_STARTOFTELEOP));
                csm.matchEnded = (rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHENDED)) == 1);
                csm.questionsAnswered = (rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_QUESTIONSANSWERED)) == 1);
                csm.scoutingStatus = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTINGSTATUS));
                csm.areasToReview = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AREASTOREVIEW));
                csm.complete = (rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_COMPLETE)) == 1);
                csm.autoStartPos = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS));
                csm.autoPreload = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOPRELOAD));
                csm.autoDidNotShow = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTODIDNOTSHOW));
                csm.autoMoveBonus = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS));
                csm.autoCSCargo = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOCSCARGO));
                csm.autoCSHatch = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOCSHATCH));
                csm.autoRSCargoLow = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSCARGOLOW));
                csm.autoRSCargoMed = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSCARGOMED));
                csm.autoRSCargoHigh = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSCARGOHIGH));
                csm.autoRSHatchFarLow = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHFARLOW));
                csm.autoRSHatchFarMed = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHFARMED));
                csm.autoRSHatchFarHigh = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHFARHIGH));
                csm.autoRSHatchNearLow = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHNEARLOW));
                csm.autoRSHatchNearMed = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHNEARMED));
                csm.autoRSHatchNearHigh = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHNEARHIGH));
                csm.teleCSCargo = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_TELECSCARGO));
                csm.teleCSHatch = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_TELECSHATCH));
                csm.teleRSCargoLow = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOLOW));
                csm.teleRSCargoMed = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOMED));
                csm.teleRSCargoHigh = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOHIGH));
                csm.teleRSHatchFarLow = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARLOW));
                csm.teleRSHatchFarMed = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARMED));
                csm.teleRSHatchFarHigh = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARHIGH));
                csm.teleRSHatchNearLow = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARLOW));
                csm.teleRSHatchNearMed = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARMED));
                csm.teleRSHatchNearHigh = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARHIGH));
                csm.climbScore = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSCORE));
                csm.climbAssist = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBASSIST));
                csm.summHatchGrdPickup = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMHATCHGRDPICKUP));
                csm.summLostComm = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM));
                csm.summBroke = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMBROKE));
                csm.summTipOver = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMTIPOVER));
                csm.summSubsystemBroke = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMSUBSYSTEMBROKE));
                csm.answer01 = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER01));
                csm.answer02 = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER02));
                csm.answer03 = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER03));
                csm.answer04 = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER04));
                csm.answer05 = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER05));
                csm.answer06 = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER06));
                csm.answer07 = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER07));
                csm.answer08 = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER08));
                csm.answer09 = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER09));
                csm.answer10 = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER10));

                csmv.add(csm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (csmv.size() > 0) {
            CyberScouterMatchScouting[] csmv2 = new CyberScouterMatchScouting[csmv.size()];
            return csmv.toArray(csmv2);
        } else {
            return null;
        }
    }

    CyberScouterMatchScouting getCurrentMatch(SQLiteDatabase db, int l_allianceStationID) {
        String selection = CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTINGSTATUS + " = ? AND " + CyberScouterContract.MatchScouting.COLUMN_NAME_ALLIANCESTATIONID + " = ?";
        String[] selectionArgs = {String.format(Locale.getDefault(), "%d", ScoutingStatus.UNSCOUTED), String.format(Locale.getDefault(), "%d", l_allianceStationID)};
        String sortOrder =
                CyberScouterContract.MatchScouting.COLUMN_NAME_TEAMMATCHNO + " ASC";

        CyberScouterMatchScouting[] csmv = getLocalMatches(db, selection, selectionArgs, sortOrder);
        if (null != csmv && 0 < csmv.length) {
            return (csmv[0]);
        } else
            return null;
    }

    CyberScouterMatchScouting[] getCurrentMatchAllTeams(SQLiteDatabase db, int l_teamMatchNo) {
        String selection = CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTINGSTATUS + " = ? AND " + CyberScouterContract.MatchScouting.COLUMN_NAME_TEAMMATCHNO + " = ?";
        String[] selectionArgs = {String.format(Locale.getDefault(), "%d", ScoutingStatus.UNSCOUTED), String.format(Locale.getDefault(), "%d", l_teamMatchNo)};
        String sortOrder =
                CyberScouterContract.MatchScouting.COLUMN_NAME_ALLIANCESTATIONID + " ASC";

        return (getLocalMatches(db, selection, selectionArgs, sortOrder));
    }

    private CyberScouterMatchScouting[] getLocalMatches(SQLiteDatabase db, String selection, String[] selectionArgs, String sortOrder) {
        CyberScouterMatchScouting csm = null;
        Vector<CyberScouterMatchScouting> csmv = new Vector<CyberScouterMatchScouting>();

        Cursor cursor = null;
        try {
            String[] projection = {
                    CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHSCOUTINGID,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_EVENTID,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHID,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_COMPUTERID,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTERID,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_REVIEWERID,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TEAM,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TEAMMATCHNO,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ALLIANCESTATIONID,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_STARTOFTELEOP,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHENDED,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_QUESTIONSANSWERED,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTINGSTATUS,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AREASTOREVIEW,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_COMPLETE,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOPRELOAD,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTODIDNOTSHOW,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOCSCARGO,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOCSHATCH,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSCARGOLOW,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSCARGOMED,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSCARGOHIGH,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHFARLOW,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHFARMED,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHFARHIGH,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHNEARLOW,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHNEARMED,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHNEARHIGH,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELECSCARGO,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELECSHATCH,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOLOW,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOMED,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOHIGH,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARLOW,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARMED,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARHIGH,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARLOW,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARMED,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARHIGH,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSCORE,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBASSIST,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMHATCHGRDPICKUP,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMBROKE,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMTIPOVER,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMSUBSYSTEMBROKE,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER01,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER02,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER03,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER04,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER05,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER06,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER07,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER08,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER09,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER10,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_UPLOADSTATUS};


            cursor = db.query(
                    CyberScouterContract.MatchScouting.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );

            if (0 < cursor.getCount()) {
                while (cursor.moveToNext()) {
                    csm = new CyberScouterMatchScouting();
                    /* Read the match information from SQLite */
                    csm.matchScoutingID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHSCOUTINGID));
                    csm.eventID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_EVENTID));
                    csm.matchID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHID));
                    csm.computerID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_COMPUTERID));
                    csm.scouterID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTERID));
                    csm.reviewerID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_REVIEWERID));
                    csm.team = cursor.getString(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TEAM));
                    csm.teamMatchNo = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TEAMMATCHNO));
                    csm.allianceStationID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_ALLIANCESTATIONID));
//                csm.startOfTeleop = cursor.getString(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_STARTOFTELEOP));
                    csm.matchEnded = (cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHENDED)) == 1);
                    csm.questionsAnswered = (cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_QUESTIONSANSWERED)) == 1);
                    csm.scoutingStatus = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTINGSTATUS));
                    csm.areasToReview = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AREASTOREVIEW));
                    csm.complete = (cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_COMPLETE)) == 1);
                    csm.autoStartPos = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS));
                    csm.autoPreload = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOPRELOAD));
                    csm.autoDidNotShow = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTODIDNOTSHOW));
                    csm.autoMoveBonus = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS));
                    csm.autoCSCargo = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOCSCARGO));
                    csm.autoCSHatch = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOCSHATCH));
                    csm.autoRSCargoLow = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSCARGOLOW));
                    csm.autoRSCargoMed = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSCARGOMED));
                    csm.autoRSCargoHigh = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSCARGOHIGH));
                    csm.autoRSHatchFarLow = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHFARLOW));
                    csm.autoRSHatchFarMed = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHFARMED));
                    csm.autoRSHatchFarHigh = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHFARHIGH));
                    csm.autoRSHatchNearLow = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHNEARLOW));
                    csm.autoRSHatchNearMed = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHNEARMED));
                    csm.autoRSHatchNearHigh = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHNEARHIGH));
                    csm.teleCSCargo = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELECSCARGO));
                    csm.teleCSHatch = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELECSHATCH));
                    csm.teleRSCargoLow = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOLOW));
                    csm.teleRSCargoMed = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOMED));
                    csm.teleRSCargoHigh = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOHIGH));
                    csm.teleRSHatchFarLow = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARLOW));
                    csm.teleRSHatchFarMed = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARMED));
                    csm.teleRSHatchFarHigh = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARHIGH));
                    csm.teleRSHatchNearLow = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARLOW));
                    csm.teleRSHatchNearMed = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARMED));
                    csm.teleRSHatchNearHigh = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARHIGH));
                    csm.climbScore = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSCORE));
                    csm.climbAssist = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBASSIST));
                    csm.summHatchGrdPickup = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMHATCHGRDPICKUP));
                    csm.summLostComm = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM));
                    csm.summBroke = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMBROKE));
                    csm.summTipOver = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMTIPOVER));
                    csm.summSubsystemBroke = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMSUBSYSTEMBROKE));
                    csm.answer01 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER01));
                    csm.answer02 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER02));
                    csm.answer03 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER03));
                    csm.answer04 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER04));
                    csm.answer05 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER05));
                    csm.answer06 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER06));
                    csm.answer07 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER07));
                    csm.answer08 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER08));
                    csm.answer09 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER09));
                    csm.answer10 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER10));
                    csm.uploadStatus = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_UPLOADSTATUS));

                    csmv.add(csm);
                }
            }

            if (0 < csmv.size()) {
                CyberScouterMatchScouting[] csma = new CyberScouterMatchScouting[csmv.size()];
                CyberScouterMatchScouting[] csmaa = csmv.toArray(csma);
                return csmaa;
            } else
                return null;

        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        } finally {
            if (null != cursor && !cursor.isClosed())
                cursor.close();
        }
    }

    int getMatchScoutingID() {
        return matchScoutingID;
    }

    int getEventID() {
        return eventID;
    }

    int getMatchID() {
        return matchID;
    }

    int getComputerID() {
        return computerID;
    }

    int getScouterID() {
        return scouterID;
    }

    int getReviewerID() {
        return reviewerID;
    }

    String getTeam() {
        return team;
    }

    int getTeamMatchNo() {
        return teamMatchNo;
    }

    int getAllianceStationID() {
        return allianceStationID;
    }

    Date getStartOfTeleop() {
        return startOfTeleop;
    }

    boolean getMatchEnded() {
        return matchEnded;
    }

    boolean getQuestionsAnswered() {
        return questionsAnswered;
    }

    int getScoutingStatus() {
        return scoutingStatus;
    }

    int getAreasToReview() {
        return areasToReview;
    }

    boolean getComplete() {
        return complete;
    }

    int getAutoStartPos() {
        return autoStartPos;
    }

    int getAutoPreload() {
        return autoPreload;
    }

    int getAutoDidNotShow() {
        return autoDidNotShow;
    }

    int getAutoMoveBonus() {
        return autoMoveBonus;
    }

    int getAutoCSCargo() {
        return autoCSCargo;
    }

    int getAutoCSHatch() {
        return autoCSHatch;
    }

    int getAutoRSCargoLow() {
        return autoRSCargoLow;
    }

    int getAutoRSCargoMed() {
        return autoRSCargoMed;
    }

    int getAutoRSCargoHigh() {
        return autoRSCargoHigh;
    }

    int getAutoRSHatchFarLow() {
        return autoRSHatchFarLow;
    }

    int getAutoRSHatchFarMed() {
        return autoRSHatchFarMed;
    }

    int getAutoRSHatchFarHigh() {
        return autoRSHatchFarHigh;
    }

    int getAutoRSHatchNearLow() {
        return autoRSHatchNearLow;
    }

    int getAutoRSHatchNearMed() {
        return autoRSHatchNearMed;
    }

    int getAutoRSHatchNearHigh() {
        return autoRSHatchNearHigh;
    }

    int getTeleCSCargo() {
        return teleCSCargo;
    }

    int getTeleCSHatch() {
        return teleCSHatch;
    }

    int getTeleRSCargoLow() {
        return teleRSCargoLow;
    }

    int getTeleRSCargoMed() {
        return teleRSCargoMed;
    }

    int getTeleRSCargoHigh() {
        return teleRSCargoHigh;
    }

    int getTeleRSHatchFarLow() {
        return teleRSHatchFarLow;
    }

    int getTeleRSHatchFarMed() {
        return teleRSHatchFarMed;
    }

    int getTeleRSHatchFarHigh() {
        return teleRSHatchFarHigh;
    }

    int getTeleRSHatchNearLow() {
        return teleRSHatchNearLow;
    }

    int getTeleRSHatchNearMed() {
        return teleRSHatchNearMed;
    }

    int getTeleRSHatchNearHigh() {
        return teleRSHatchNearHigh;
    }

    int getClimbScore() {
        return climbScore;
    }

    int getClimbAssist() {
        return climbAssist;
    }

    int getSummHatchGrdPickup() {
        return summHatchGrdPickup;
    }

    int getSummLostComm() {
        return summLostComm;
    }

    int getSummBroke() {
        return summBroke;
    }

    int getSummTipOver() {
        return summTipOver;
    }

    int getSummSubsystemBroke() {
        return summSubsystemBroke;
    }

    int getAnswer01() {
        return answer01;
    }

    int getAnswer02() {
        return answer02;
    }

    int getAnswer03() {
        return answer03;
    }

    int getAnswer04() {
        return answer04;
    }

    int getAnswer05() {
        return answer05;
    }

    int getAnswer06() {
        return answer06;
    }

    int getAnswer07() {
        return answer07;
    }

    int getAnswer08() {
        return answer08;
    }

    int getAnswer09() {
        return answer09;
    }

    int getAnswer10() {
        return answer10;
    }

    int getUploadStatus() {
        return uploadStatus;
    }
}
