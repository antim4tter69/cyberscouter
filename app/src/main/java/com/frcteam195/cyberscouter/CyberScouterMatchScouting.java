package com.frcteam195.cyberscouter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import java.util.Vector;

class CyberScouterMatchScouting {

    private int matchScoutingID;
    private int eventID;
    private int matchID;
    private int computerID;
    private int scouterID;
    private int reviewerID;
    private String team;
    private int teamMatchNo;
    private int allianceStationID;
    private boolean matchEnded;
    private int scoutingStatus;
    private int areasToReview;
    private boolean complete;
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
    private int teleBallLowZone2;
    private int teleBallOuterZone2;
    private int teleBallInnerZone2;
    private int teleBallLowZone3;
    private int teleBallOuterZone3;
    private int teleBallInnerZone3;
    private int teleBallLowZone4;
    private int teleBallOuterZone4;
    private int teleBallInnerZone4;
    private int teleBallLowZone5;
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
    private int summHatchGrdPickup;
    private int summLostComm;
    private int summBrokeDown;
    private int summSubsystemBroke;
    private int summGroundPickup;
    private int summHopperLoad;
    private int summPlayedDefense;
    private int summDefPlayedAgainst;
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
        } catch(Exception e) {
            e.printStackTrace();
        }

        return json;
    }

    static CyberScouterMatchScouting[] getMatches(Connection conn, int eventId) {
        Vector<CyberScouterMatchScouting> csmv = new Vector<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from " + CyberScouterContract.MatchScouting.TABLE_NAME +
                    " WHERE " +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_EVENTID + " = " + eventId +
                    " ORDER BY " + CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHID + " ASC, " +
                    CyberScouterContract.MatchScouting.COLUMN_NAME_TEAMMATCHNO + " ASC");
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
                csm.matchEnded = (rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHENDED)) == 1);
                csm.scoutingStatus = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTINGSTATUS));
                csm.areasToReview = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AREASTOREVIEW));
                csm.complete = (rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_COMPLETE)) == 1);
                csm.autoStartPos = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS));
                if(rs.wasNull())
                    csm.autoStartPos = -1;
                csm.autoDidNotShow = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTODIDNOTSHOW));
                if(rs.wasNull())
                    csm.autoDidNotShow = 0;
                csm.autoMoveBonus = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS));
                if(rs.wasNull())
                    csm.autoMoveBonus = -1;
                csm.summLostComm = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM));
                if(rs.wasNull())
                    csm.summLostComm= -1;
                csm.summBrokeDown = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMBROKEDOWN));
                if(rs.wasNull())
                    csm.summBrokeDown = -1;
                csm.summSubsystemBroke = rs.getInt(rs.findColumn(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMSUBSYSTEMBROKE));
                if(rs.wasNull())
                    csm.summSubsystemBroke = -1;

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

        return(getLocalMatches(db, selection, selectionArgs, sortOrder));
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
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMBROKEDOWN,
                    CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMSUBSYSTEMBROKE,
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
                    csm.matchEnded = (cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHENDED)) == 1);
                    csm.scoutingStatus = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTINGSTATUS));
                    csm.areasToReview = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AREASTOREVIEW));
                    csm.complete = (cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_COMPLETE)) == 1);
                    csm.autoStartPos = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS));
                    csm.autoDidNotShow = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTODIDNOTSHOW));
                    csm.autoMoveBonus = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS));
                    csm.summLostComm = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM));
                    csm.summBrokeDown = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMBROKEDOWN));
                    csm.summSubsystemBroke = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMSUBSYSTEMBROKE));
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

    private static void setMatch(Context ctx, CyberScouterMatchScouting csm) {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(ctx);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHSCOUTINGID, csm.getMatchScoutingID());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_EVENTID, csm.getEventID());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHID, csm.getMatchID());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_COMPUTERID, csm.getComputerID());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTERID, csm.getScouterID());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_REVIEWERID, csm.getReviewerID());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TEAM, csm.getTeam());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TEAMMATCHNO, csm.getTeamMatchNo());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_ALLIANCESTATIONID, csm.getAllianceStationID());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHENDED, csm.getMatchEnded());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTINGSTATUS, csm.getScoutingStatus());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AREASTOREVIEW, csm.getAreasToReview());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_COMPLETE, csm.getComplete());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS, csm.getAutoStartPos());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTODIDNOTSHOW, csm.getAutoDidNotShow());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS, csm.getAutoMoveBonus());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM, csm.getSummLostComm());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMBROKEDOWN, csm.getSummBrokeDown());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMSUBSYSTEMBROKE, csm.getSummSubsystemBroke());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_UPLOADSTATUS, UploadStatus.NOT_UPLOADED);

        long newRowId = db.insert(CyberScouterContract.MatchScouting.TABLE_NAME, null, values);
    }

    static void deleteOldMatches(Context ctx, int l_eventID) {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(ctx);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String[] whereArgs = {String.format(Locale.getDefault(), "%d", l_eventID)};

        db.delete(CyberScouterContract.MatchScouting.TABLE_NAME, CyberScouterContract.MatchScouting.COLUMN_NAME_EVENTID + " <> ?", whereArgs);
    }

    private static void updateMatchTeamAndScoutingStatus(SQLiteDatabase db, CyberScouterMatchScouting rmatch, CyberScouterMatchScouting lmatch) throws Exception {

        // if this is a match that we've finished scouting but hasn't been uploaded yet...
        if(UploadStatus.READY_TO_UPLOAD == lmatch.getUploadStatus())
            return;

        ContentValues values = new ContentValues();
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TEAM, rmatch.team);
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTINGSTATUS, rmatch.scoutingStatus);

        String selection = CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHSCOUTINGID + " = ?";
        String[] selectionArgs = {String.format(Locale.getDefault(), "%d", lmatch.matchScoutingID)};

        if (1 > updateMatch(db, values, selection, selectionArgs))
            throw new Exception(String.format("An error occurred while updating the local match scouting table.\n\nNo rows were updated for MatchScoutingID=%d", lmatch.matchScoutingID));
    }

    static void updateMatchMetric(SQLiteDatabase db, String[] lColumns, Integer[] lValues, CyberScouterConfig cfg) throws Exception {
        CyberScouterMatchScouting csms = getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        if (null == csms)
            throw new Exception(String.format("No current unscouted match was found!  Attempt to update a match statistic failed!\n\nRole=%s", cfg.getRole()));
        if (null == lColumns || null == lValues || lColumns.length != lValues.length)
            throw new Exception(String.format("Bad request! Attempt to update a match statistic failed!\n\nRole=%s", cfg.getRole()));

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
    static String mergeMatches(Context ctx, CyberScouterMatchScouting[] remoteMatches) throws Exception {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(ctx);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int updated=0, inserted=0;

        for (CyberScouterMatchScouting rmatch : remoteMatches) {
            CyberScouterMatchScouting lmatch = getLocalMatch(db, rmatch.eventID, rmatch.matchID, rmatch.allianceStationID);
            if (null != lmatch) {
                updateMatchTeamAndScoutingStatus(db, rmatch, lmatch);
                updated++;
            } else {
                setMatch(ctx, rmatch);
                inserted++;
            }
        }

        return(String.format(Locale.getDefault(), "%d matches inserted, %d matches updated", inserted, updated));
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

    boolean getMatchEnded() {
        return matchEnded;
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

    int getAutoDidNotShow() {
        return autoDidNotShow;
    }

    int getAutoMoveBonus() {
        return autoMoveBonus;
    }

    int getSummHatchGrdPickup() {
        return summHatchGrdPickup;
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

    int getUploadStatus() {
        return uploadStatus;
    }

    public void setMatchScoutingID(int matchScoutingID) {
        this.matchScoutingID = matchScoutingID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public void setComputerID(int computerID) {
        this.computerID = computerID;
    }

    public void setScouterID(int scouterID) {
        this.scouterID = scouterID;
    }
}
