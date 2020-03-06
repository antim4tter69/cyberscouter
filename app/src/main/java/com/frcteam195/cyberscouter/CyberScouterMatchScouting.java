package com.frcteam195.cyberscouter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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

import java.util.Locale;
import java.util.Vector;

class CyberScouterMatchScouting {
    final static String MATCH_SCOUTING_UPDATED_FILTER = "frcteam195_cyberscoutermatchscouting_match_scouting_updated_intent_filter";

    private static boolean webQueryInProgress = false;
    private static String last_hash;

    private static String webResponse;
    static String getWebResponse() {
        return(webResponse);
    }

    private int matchScoutingID;
    private int eventID;
    private int matchID;
    private int matchNo;
    private int computerID;
    private int scouterID;
    private int reviewerID;
    private String team;
    private int teamMatchNo;
    private int allianceStationID;

    private int autoStartPos;
    private int autoDidNotShow;
    private int autoMoveBonus;
    private int autoBallLow;
    private int autoBallOuter;
    private int autoBallInner;
    private int autoPenalty;

    private int teleBallLowZone1;
    private int teleBallOuterZone1;
    private int teleBallInnerZone1;
    private int teleBallOuterZone2;
    private int teleBallInnerZone2;
    private int teleBallOuterZone3;
    private int teleBallInnerZone3;
    private int teleBallOuterZone4;
    private int teleBallInnerZone4;
    private int teleBallOuterZone5;
    private int teleBallInnerZone5;
    private int teleWheelStage2Time;
    private int teleWheelStage2Status;
    private int teleWheelStage2Attempts;
    private int teleWheelStage3Time;
    private int teleWheelStage3Status;
    private int teleWheelStage3Attempts;

    private int climbStatus;
    private int climbHeight;
    private int climbPosition;
    private int climbMoveOnBar;
    private int climbLevelStatus;

    private int summBrokeDown;
    private int summLostComm;
    private int summSubsystemBroke;
    private int summGroundPickup;
    private int summHopperLoad;
    private int summPlayedDefense;
    private int summDefPlayedAgainst;

    private boolean matchEnded;
    private int scoutingStatus;
    private boolean complete;
    private int uploadStatus;

    public String toJSON() {
        String json = "";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("matchScoutingId", getMatchScoutingID());
            jsonObject.put("eventID", getEventID());
            jsonObject.put("matchID", getMatchID());
            jsonObject.put("computerID", getComplete());
            jsonObject.put("scouterID", getScouterID());

            json = jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

    static String getMatchesRemote(AppCompatActivity activity, int eventId) {
        String ret = null;
        try {
            BluetoothComm btcomm = new BluetoothComm();
            String response = btcomm.getMatches(activity, eventId, last_hash);
            if (null != response) {
                    JSONObject jo = new JSONObject(response);
                    String result = jo.getString("result");
                    if (!result.equalsIgnoreCase("failed")) {
                        if(result.equalsIgnoreCase("skip")) {
                            ret = "skip";
                        } else {
                            JSONArray payload = jo.getJSONArray("payload");
                            ret = payload.toString();
                            last_hash = jo.getString("hash");
                        }
                        return ret;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    public String setMatchesRemote(AppCompatActivity activity) {
        String ret = "failed";
        try {
            JSONObject jo = new JSONObject();
            jo.put("cmd", "put-match-scouting");
            jo.put("key", matchScoutingID);
            JSONObject payload = new JSONObject();
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS, autoStartPos);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTODIDNOTSHOW, autoDidNotShow);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS, autoMoveBonus);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOBALLLOW, autoBallLow);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOBALLOUTER, autoBallOuter);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOBALLINNER, autoBallInner);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOPENALTY, autoPenalty);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLLOWZONE1, teleBallLowZone1);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE1, teleBallOuterZone1);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE1, teleBallInnerZone1);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE2, teleBallOuterZone2);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE2, teleBallInnerZone2);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE3, teleBallOuterZone3);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE3, teleBallInnerZone3);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE4, teleBallOuterZone4);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE4, teleBallInnerZone4);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE5, teleBallOuterZone5);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE5, teleBallInnerZone5);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE2TIME, teleWheelStage2Time);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE2STATUS, teleWheelStage2Status);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE2ATTEMPTS, teleWheelStage2Attempts);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE3TIME, teleWheelStage3Time);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE3STATUS, teleWheelStage3Status);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE3ATTEMPTS, teleWheelStage3Attempts);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSTATUS, climbStatus);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBHEIGHT, climbHeight);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBPOSITION, climbPosition);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBMOVEONBAR, climbMoveOnBar);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBLEVELSTATUS, climbLevelStatus);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMBROKEDOWN, summBrokeDown);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM, summLostComm);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMSUBSYSTEMBROKE, summSubsystemBroke);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMGROUNDPICKUP, summGroundPickup);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMHOPPERLOAD, summHopperLoad);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMPLAYEDDEFENSE, summPlayedDefense);
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMDEFPLAYEDAGAINST, summDefPlayedAgainst);
            jo.put("payload", payload);

            BluetoothComm btcomm = new BluetoothComm();
            String response = btcomm.sendSetCommand(activity, jo);
            if (null != response) {
                JSONObject jresp = new JSONObject(response);
                ret = jresp.getString("result");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    // Gets the next sequential un-scouted match for the current scouter station
    static CyberScouterMatchScouting getCurrentMatch(SQLiteDatabase db, int l_allianceStationID) {
        String selection = CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTINGSTATUS + " = ? AND " +
                CyberScouterContract.MatchScouting.COLUMN_NAME_ALLIANCESTATIONID + " = ? AND " +
                CyberScouterContract.MatchScouting.COLUMN_NAME_UPLOADSTATUS + " = ?";
        String[] selectionArgs = {
                String.format(Locale.getDefault(), "%d", ScoutingStatus.UNSCOUTED),
                String.format(Locale.getDefault(), "%d", l_allianceStationID),
                String.format(Locale.getDefault(), "%d", UploadStatus.NOT_UPLOADED)
        };
        String sortOrder =
                CyberScouterContract.MatchScouting.COLUMN_NAME_TEAMMATCHNO + " ASC";

        CyberScouterMatchScouting[] csmv = getLocalMatches(db, selection, selectionArgs, sortOrder);
        if (null != csmv && 0 < csmv.length) {
            return (csmv[0]);
        } else
            return null;
    }

    // Gets the next sequential unscouted match for all scouter stations
    static CyberScouterMatchScouting[] getCurrentMatchAllTeams(SQLiteDatabase db, int l_teamMatchNo, int l_matchID) {
        String selection = CyberScouterContract.MatchScouting.COLUMN_NAME_TEAMMATCHNO + " = ? AND " + CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHID + " = ?";
        String[] selectionArgs = {
                String.format(Locale.getDefault(), "%d", l_teamMatchNo),
                String.format(Locale.getDefault(), "%d", l_matchID)
        };
        String sortOrder =
                CyberScouterContract.MatchScouting.COLUMN_NAME_ALLIANCESTATIONID + " ASC";

        return (getLocalMatches(db, selection, selectionArgs, sortOrder));
    }

    // Returns only the matches that are unscouted
    private static CyberScouterMatchScouting getLocalMatch(SQLiteDatabase db, int l_eventID, int l_matchID, int l_allianceStationID) throws Exception {
        String selection =
                CyberScouterContract.MatchScouting.COLUMN_NAME_EVENTID + " = ? AND "
                        + CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHID + " = ? AND "
                        + CyberScouterContract.MatchScouting.COLUMN_NAME_ALLIANCESTATIONID + " = ?";
        String[] selectionArgs = {
                String.format(Locale.getDefault(), "%d", l_eventID),
                String.format(Locale.getDefault(), "%d", l_matchID),
                String.format(Locale.getDefault(), "%d", l_allianceStationID)
        };

        CyberScouterMatchScouting[] csmv = getLocalMatches(db, selection, selectionArgs, null);

        if (null != csmv) {
            if (1 < csmv.length) {
                throw new Exception(String.format(Locale.getDefault(), "Too many match scouting rows found.  Wanted %d, found %d!\n\nEventID=%d, MatchID=%d, AllianceStationID=%d",
                        1, csmv.length, l_eventID, l_matchID, l_allianceStationID));
            } else
                return (csmv[0]);
        } else
            return null;
    }

    static CyberScouterMatchScouting[] getMatchesReadyToUpload(SQLiteDatabase db, int l_eventID, int l_allianceStationID) {

        String selection =
                CyberScouterContract.MatchScouting.COLUMN_NAME_EVENTID + " = ? AND "
                        + CyberScouterContract.MatchScouting.COLUMN_NAME_ALLIANCESTATIONID + " = ? AND "
                        + CyberScouterContract.MatchScouting.COLUMN_NAME_UPLOADSTATUS + " = ?";

        String[] selectionArgs = {
                String.format(Locale.getDefault(), "%d", l_eventID),
                String.format(Locale.getDefault(), "%d", l_allianceStationID),
                String.format(Locale.getDefault(), "%d", UploadStatus.READY_TO_UPLOAD)
        };

        String sortOrder =
                CyberScouterContract.MatchScouting.COLUMN_NAME_TEAMMATCHNO + " ASC";

        return (getLocalMatches(db, selection, selectionArgs, sortOrder));
    }

    private static CyberScouterMatchScouting[] getLocalMatches(SQLiteDatabase db, String selection, String[] selectionArgs, String sortOrder) {
        CyberScouterMatchScouting csm;
        Vector<CyberScouterMatchScouting> csmv = new Vector<>();

        Cursor cursor = null;
        try {
            String[] projection = {
                    CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHSCOUTINGID,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_EVENTID,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHID,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_MATCH_NUMBER,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_COMPUTERID,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTERID,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_REVIEWERID,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TEAM,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TEAMMATCHNO,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_ALLIANCESTATIONID,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHENDED,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTINGSTATUS,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AREASTOREVIEW,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_COMPLETE,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTODIDNOTSHOW,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOBALLLOW,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOBALLOUTER,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOBALLINNER,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOPENALTY,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLLOWZONE1,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE1,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE1,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE2,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE2,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE3,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE3,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE4,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE4,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE5,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE5,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE2TIME,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE2STATUS,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE2ATTEMPTS,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE3TIME,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE3STATUS,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE3ATTEMPTS,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSTATUS,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBHEIGHT,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBPOSITION,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBMOVEONBAR,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBLEVELSTATUS,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMBROKEDOWN,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMSUBSYSTEMBROKE,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMGROUNDPICKUP,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMHOPPERLOAD,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMPLAYEDDEFENSE,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMDEFPLAYEDAGAINST,
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
                    csm.matchNo = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCH_NUMBER));
                    csm.computerID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_COMPUTERID));
                    csm.scouterID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTERID));
                    csm.reviewerID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_REVIEWERID));
                    csm.team = cursor.getString(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TEAM));
                    csm.teamMatchNo = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TEAMMATCHNO));
                    csm.allianceStationID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_ALLIANCESTATIONID));
                    csm.matchEnded = (cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHENDED)) == 1);
                    csm.scoutingStatus = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTINGSTATUS));
                    csm.complete = (cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_COMPLETE)) == 1);
                    csm.autoStartPos = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS));
                    csm.autoDidNotShow = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTODIDNOTSHOW));
                    csm.autoMoveBonus = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS));
                    csm.autoBallLow = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOBALLLOW));
                    csm.autoBallOuter = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOBALLOUTER));
                    csm.autoBallInner = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOBALLINNER));
                    csm.autoPenalty = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOPENALTY));
                    csm.teleBallLowZone1 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLLOWZONE1));
                    csm.teleBallOuterZone1 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE1));
                    csm.teleBallInnerZone1 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE1));
                    csm.teleBallOuterZone2 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE2));
                    csm.teleBallInnerZone2 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE2));
                    csm.teleBallOuterZone3 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE3));
                    csm.teleBallInnerZone3 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE3));
                    csm.teleBallOuterZone4 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE4));
                    csm.teleBallInnerZone4 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE4));
                    csm.teleBallOuterZone5 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE5));
                    csm.teleBallInnerZone5 = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE5));
                    csm.teleWheelStage2Time = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE2TIME));
                    csm.teleWheelStage2Status = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE2STATUS));
                    csm.teleWheelStage2Attempts = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE2ATTEMPTS));
                    csm.teleWheelStage3Time = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE3TIME));
                    csm.teleWheelStage3Status = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE3STATUS));
                    csm.teleWheelStage3Attempts = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE3ATTEMPTS));
                    csm.climbStatus = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSTATUS));
                    csm.climbHeight = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBHEIGHT));
                    csm.climbPosition = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBPOSITION));
                    csm.climbMoveOnBar = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBMOVEONBAR));
                    csm.climbLevelStatus = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBLEVELSTATUS));
                    csm.summLostComm = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM));
                    csm.summBrokeDown = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMBROKEDOWN));
                    csm.summSubsystemBroke = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMSUBSYSTEMBROKE));
                    csm.summGroundPickup = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMGROUNDPICKUP));
                    csm.summHopperLoad = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMHOPPERLOAD));
                    csm.summPlayedDefense = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMPLAYEDDEFENSE));
                    csm.summDefPlayedAgainst = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMDEFPLAYEDAGAINST));
                    csm.uploadStatus = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_UPLOADSTATUS));

                    csmv.add(csm);
                }
            }

            if (0 < csmv.size()) {
                CyberScouterMatchScouting[] csma = new CyberScouterMatchScouting[csmv.size()];
                return csmv.toArray(csma);
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

    private static void setMatch(SQLiteDatabase db, JSONObject jo) throws Exception {

        ContentValues values = new ContentValues();
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHSCOUTINGID, jo.getInt(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHSCOUTINGID));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_EVENTID, jo.getInt(CyberScouterContract.MatchScouting.COLUMN_NAME_EVENTID));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHID, jo.getInt(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHID));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCH_NUMBER, jo.getInt(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCH_NUMBER));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_COMPUTERID, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_COMPUTERID));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTERID, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTERID));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_REVIEWERID, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_REVIEWERID));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTINGSTATUS, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTINGSTATUS));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TEAM, jo.getString(CyberScouterContract.MatchScouting.COLUMN_NAME_TEAM));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TEAMMATCHNO, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_TEAMMATCHNO));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_ALLIANCESTATIONID, jo.getInt(CyberScouterContract.MatchScouting.COLUMN_NAME_ALLIANCESTATIONID));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTODIDNOTSHOW, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTODIDNOTSHOW));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOBALLLOW, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOBALLLOW));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOBALLOUTER, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOBALLOUTER));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOBALLINNER, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOBALLINNER));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOPENALTY, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOPENALTY));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLLOWZONE1, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLLOWZONE1));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE1, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE1));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE1, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE1));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE2, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE2));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE2, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE2));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE3, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE3));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE3, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE3));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE4, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE4));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE4, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE4));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE5, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE5));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE5, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE5));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE2TIME, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE2TIME));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE2STATUS, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE3STATUS));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE2ATTEMPTS, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE2ATTEMPTS));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE3TIME, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE3TIME));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE3STATUS, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE3STATUS));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE3ATTEMPTS, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE3ATTEMPTS));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSTATUS, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSTATUS));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBHEIGHT, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBHEIGHT));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBPOSITION, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBPOSITION));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBMOVEONBAR, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBMOVEONBAR));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBLEVELSTATUS, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBLEVELSTATUS));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMBROKEDOWN, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMBROKEDOWN));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMSUBSYSTEMBROKE, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMSUBSYSTEMBROKE));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMGROUNDPICKUP, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMGROUNDPICKUP));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMHOPPERLOAD, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMHOPPERLOAD));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMPLAYEDDEFENSE, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMPLAYEDDEFENSE));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMDEFPLAYEDAGAINST, jo.optInt(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMDEFPLAYEDAGAINST));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_UPLOADSTATUS, UploadStatus.NOT_UPLOADED);

        long newRowId = db.insert(CyberScouterContract.MatchScouting.TABLE_NAME, null, values);
        if (-1 == newRowId) {
            throw (new Exception("An invalid row id was generated by the SQLite database insert command"));
        }
    }

    static void deleteOldMatches(SQLiteDatabase db, int l_eventID) {

        String[] whereArgs = {String.format(Locale.getDefault(), "%d", l_eventID)};

        db.delete(CyberScouterContract.MatchScouting.TABLE_NAME, CyberScouterContract.MatchScouting.COLUMN_NAME_EVENTID + " <> ?", whereArgs);
    }

    private static void updateMatchTeamAndScoutingStatus(SQLiteDatabase db, JSONObject rmatch, CyberScouterMatchScouting lmatch) throws Exception {

        // if this is a match that we've finished scouting but hasn't been uploaded yet...
        if (UploadStatus.READY_TO_UPLOAD == lmatch.getUploadStatus())
            return;

        ContentValues values = new ContentValues();
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TEAM, rmatch.getString("Team"));
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTINGSTATUS, rmatch.optInt("ScoutingStatus"));

        String selection = CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHSCOUTINGID + " = ?";
        String[] selectionArgs = {String.format(Locale.getDefault(), "%d", lmatch.matchScoutingID)};

        if (1 > updateMatch(db, values, selection, selectionArgs))
            throw new Exception(String.format("An error occurred while updating the local match scouting table.\n\nNo rows were updated for MatchScoutingID=%d", lmatch.matchScoutingID));
    }

    static void updateMatchMetric(SQLiteDatabase db, String[] lColumns, Integer[] lValues, CyberScouterConfig cfg) throws Exception {
        CyberScouterMatchScouting csms = getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getAlliance_station()));
        if (null == csms)
            throw new Exception(String.format("No current unscouted match was found!  Attempt to update a match statistic failed!\n\nRole=%s", cfg.getAlliance_station()));
        if (null == lColumns || null == lValues || lColumns.length != lValues.length)
            throw new Exception(String.format("Bad request! Attempt to update a match statistic failed!\n\nRole=%s", cfg.getAlliance_station()));

        ContentValues values = new ContentValues();
        for (int i = 0; i < lColumns.length; ++i) {
            values.put(lColumns[i], lValues[i]);
        }

        String selection = CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHSCOUTINGID + " = ?";
        String[] selectionArgs = {
                String.format(Locale.getDefault(), "%d", csms.matchScoutingID)
        };

        if (1 > updateMatch(db, values, selection, selectionArgs))
            throw new Exception(String.format("An error occurred while updating the local match scouting table.\n\nNo rows were updated for MatchScoutingID=%d", csms.matchScoutingID));
    }

    static void updateMatchUploadStatus(SQLiteDatabase db, int l_matchScoutingID, int newStatus) throws Exception {
        ContentValues values = new ContentValues();
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_UPLOADSTATUS, newStatus);

        String selection = CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHSCOUTINGID + " = ?";
        String[] selectionArgs = {
                String.format(Locale.getDefault(), "%d", l_matchScoutingID)
        };

        if (1 > updateMatch(db, values, selection, selectionArgs))
            throw new Exception(String.format("An error occurred while updating the local match scouting table.\n\nNo rows were updated for MatchScoutingID=%d", l_matchScoutingID));

    }

    static void skipMatch(SQLiteDatabase db, int l_matchScoutingID) throws Exception {
        ContentValues values = new ContentValues();
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_UPLOADSTATUS, UploadStatus.SKIPPED);
        String selection = CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHSCOUTINGID + " = ?";
        String[] selectionArgs = {
                String.format(Locale.getDefault(), "%d", l_matchScoutingID)
        };

        if (1 > updateMatch(db, values, selection, selectionArgs))
            throw new Exception(String.format("An error occurred while updating the local match scouting table.\n\nNo rows were updated for MatchScoutingID=%d", l_matchScoutingID));
    }

    static void submitMatch(SQLiteDatabase db, int l_matchScoutingID) throws Exception {
        ContentValues values = new ContentValues();
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_UPLOADSTATUS, UploadStatus.READY_TO_UPLOAD);
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTINGSTATUS, ScoutingStatus.FINISHED_SUCCESSFULLY);
        String selection = CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHSCOUTINGID + " = ?";
        String[] selectionArgs = {
                String.format(Locale.getDefault(), "%d", l_matchScoutingID)
        };

        if (1 > updateMatch(db, values, selection, selectionArgs))
            throw new Exception(String.format("An error occurred while updating the local match scouting table.\n\nNo rows were updated for MatchScoutingID=%d", l_matchScoutingID));
    }

    static void submitMatchForReview(SQLiteDatabase db, int l_matchScoutingID) throws Exception {
        ContentValues values = new ContentValues();
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_UPLOADSTATUS, UploadStatus.READY_TO_UPLOAD);
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTINGSTATUS, ScoutingStatus.NEEDS_REVIEW);
        String selection = CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHSCOUTINGID + " = ?";
        String[] selectionArgs = {
                String.format(Locale.getDefault(), "%d", l_matchScoutingID)
        };

        if (1 > updateMatch(db, values, selection, selectionArgs))
            throw new Exception(String.format("An error occurred while updating the local match scouting table.\n\nNo rows were updated for MatchScoutingID=%d", l_matchScoutingID));
    }

    private static int updateMatch(SQLiteDatabase db, ContentValues values, String selection, String[] selectionArgs) {
        return db.update(
                CyberScouterContract.MatchScouting.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    // If there are new match scouting records, insert them into the local database.
    // Otherwise, update the scouting status and the team for each match, to keep that info current
    static String mergeMatches(SQLiteDatabase db, String remoteMatchesJson) throws Exception {
        int updated = 0, inserted = 0;

        JSONArray ja = new JSONArray(remoteMatchesJson);

        for (int i = 0; i < ja.length(); ++i) {
            JSONObject jo = ja.getJSONObject(i);
            CyberScouterMatchScouting lmatch = getLocalMatch(db, jo.getInt("EventID"), jo.getInt("MatchID"), jo.getInt("AllianceStationID"));
            if (null != lmatch) {
                updateMatchTeamAndScoutingStatus(db, jo, lmatch);
                updated++;
            } else {
                setMatch(db, jo);
                inserted++;
            }
        }

        return (String.format(Locale.getDefault(), "%d matches inserted, %d matches updated", inserted, updated));
    }

    static void getMatchesWebService(final Activity activity, int eventId) {

        if (webQueryInProgress)
            return;

        webQueryInProgress = true;
        RequestQueue rq = Volley.newRequestQueue(activity);
        String url = String.format("%s/match-scouting?eventId=%s", FakeBluetoothServer.webServiceBaseUrl, eventId);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        webQueryInProgress = false;
                        try {
                            Intent i = new Intent(MATCH_SCOUTING_UPDATED_FILTER);
                            webResponse = response;
                            i.putExtra("cyberscoutermatches", "fetch");
                            activity.sendBroadcast(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                webQueryInProgress = false;
                String msg;
                if (null == error.networkResponse) {
                    msg = error.getMessage();
                } else {
                    msg = String.format("Status Code: %d\nMessage: %s", error.networkResponse.statusCode, new String(error.networkResponse.data));
                }

                MessageBox.showMessageBox(activity, "Fetch of Match Scouting Records Failed", "CyberScouterMatchScouting.getMatchScoutingWebService",
                        String.format("Can't get list of matches to scout.\nContact a scouting mentor right away\n\n%s\n", msg));
            }
        });

        rq.add(stringRequest);
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

    int getMatchNo() {
        return matchNo;
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

    int getAutoStartPos() {
        return autoStartPos;
    }

    int getAutoDidNotShow() {
        return autoDidNotShow;
    }

    int getAutoMoveBonus() {
        return autoMoveBonus;
    }

    int getAutoBallLow() {
        return autoBallLow;
    }

    int getAutoBallOuter() {
        return autoBallOuter;
    }

    int getAutoBallInner() {
        return autoBallInner;
    }

    int getAutoPenalty() {
        return autoPenalty;
    }

    int getTeleBallLowZone1() {
        return teleBallLowZone1;
    }

    int getTeleBallOuterZone1() {
        return teleBallOuterZone1;
    }

    int getTeleBallInnerZone1() {
        return teleBallInnerZone1;
    }

    int getTeleBallOuterZone2() {
        return teleBallOuterZone2;
    }

    int getTeleBallInnerZone2() {
        return teleBallInnerZone2;
    }

    int getTeleBallOuterZone3() {
        return teleBallOuterZone3;
    }

    int getTeleBallInnerZone3() {
        return teleBallInnerZone3;
    }

    int getTeleBallOuterZone4() {
        return teleBallOuterZone4;
    }

    int getTeleBallInnerZone4() {
        return teleBallInnerZone4;
    }

    int getTeleBallOuterZone5() {
        return teleBallOuterZone5;
    }

    int getTeleBallInnerZone5() {
        return teleBallInnerZone5;
    }

    int getTeleWheelStage2Time() {
        return teleWheelStage2Time;
    }

    int getTeleWheelStage2Status() {
        return teleWheelStage2Status;
    }

    int getTeleWheelStage2Attempts() {
        return teleWheelStage2Attempts;
    }

    int getTeleWheelStage3Time() {
        return teleWheelStage3Time;
    }

    int getTeleWheelStage3Status() {
        return teleWheelStage3Status;
    }

    int getTeleWheelStage3Attempts() {
        return teleWheelStage3Attempts;
    }

    int getClimbStatus() {
        return climbStatus;
    }

    int getClimbHeight() {
        return climbHeight;
    }

    int getClimbPosition() {
        return climbPosition;
    }

    int getClimbMoveOnBar() {
        return climbMoveOnBar;
    }

    int getClimbLevelStatus() {
        return climbLevelStatus;
    }

    int getSummLostComm() {
        return summLostComm;
    }

    int getSummBrokeDown() {
        return summBrokeDown;
    }

    int getSummSubsystemBroke() {
        return summSubsystemBroke;
    }

    int getSummGroundPickup() {
        return summGroundPickup;
    }

    int getSummHopperLoad() {
        return summHopperLoad;
    }

    int getSummPlayedDefense() {
        return summPlayedDefense;
    }

    int getSummDefPlayedAgainst() {
        return summDefPlayedAgainst;
    }

    int getUploadStatus() {
        return uploadStatus;
    }

    boolean getMatchEnded() {
        return matchEnded;
    }

    int getScoutingStatus() {
        return scoutingStatus;
    }

    boolean getComplete() {
        return complete;
    }


    void setMatchScoutingID(int matchScoutingID) {
        this.matchScoutingID = matchScoutingID;
    }

    void setEventID(int eventID) {
        this.eventID = eventID;
    }

    void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    void setComputerID(int computerID) {
        this.computerID = computerID;
    }

    void setScouterID(int scouterID) {
        this.scouterID = scouterID;
    }
}
