package com.frcteam195.cyberscouter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class CyberScouterMatches {
    private int matchId;
    private int eventId;
    private int levelIiiScouterId;
    private int matchNo;
    private int matchNo_Tag;
    private String redTeam1;
    private String redTeam2;
    private String redTeam3;
    private String redTeam1Tag;
    private String redTeam2Tag;
    private String redTeam3Tag;
    private String blueTeam1;
    private String blueTeam2;
    private String blueTeam3;
    private String blueTeam1Tag;
    private String blueTeam2Tag;
    private String blueTeam3Tag;
    private String matchVideo;
    private String comments;
    private boolean eliminationMatch;
    private java.sql.Date startOfTeleop;
    private boolean matchEnded;

    public CyberScouterMatches[] getMatches(Connection conn, int eventId) {
        CyberScouterMatches[] csms =  null;
        Vector<CyberScouterMatches> csmv = new Vector<CyberScouterMatches>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from "+CyberScouterContract.Matches.TABLE_NAME+
                    " WHERE " +
                    CyberScouterContract.Matches.COLUMN_NAME_EVENTID + " = " + eventId +
                    " ORDER BY " + CyberScouterContract.Matches.COLUMN_NAME_MATCHNO + " ASC");
            while(rs.next()) {
                CyberScouterMatches csm = new CyberScouterMatches();
                csm.matchId = rs.getInt(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_MATCHID));
                csm.eventId = rs.getInt(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_EVENTID));
                csm.levelIiiScouterId = rs.getInt(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_LEVELIIISCOUTERID));
                csm.matchNo = rs.getInt(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_MATCHNO));
                csm.matchNo_Tag = rs.getInt(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_MATCHNOTAG));
                csm.redTeam1 = rs.getString(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_REDTEAM1));
                csm.redTeam2 = rs.getString(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_REDTEAM2));
                csm.redTeam3 = rs.getString(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_REDTEAM3));
                csm.redTeam1Tag = rs.getString(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_REDTEAM1TAG));
                csm.redTeam2Tag = rs.getString(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_REDTEAM2TAG));
                csm.redTeam3Tag = rs.getString(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_REDTEAM3TAG));
                csm.blueTeam1 = rs.getString(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM1));
                csm.blueTeam2 = rs.getString(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM2));
                csm.blueTeam3 = rs.getString(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM3));
                csm.blueTeam1Tag = rs.getString(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM1TAG));
                csm.blueTeam2Tag = rs.getString(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM2TAG));
                csm.blueTeam3Tag = rs.getString(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM3TAG));
                csm.matchVideo = null;
                csm.comments = rs.getString(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_COMMENTS));
                csm.eliminationMatch = (rs.getInt(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_ELIMINATIONMATCH)) == 1);
                csm.startOfTeleop = rs.getDate(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_STARTOFTELEOP));
                csm.matchEnded = (rs.getInt(rs.findColumn(CyberScouterContract.Matches.COLUMN_NAME_MATCHENDED)) == 1);

                csmv.add(csm);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        if(csmv.size() > 0) {
            CyberScouterMatches[] csmv2 = new CyberScouterMatches[csmv.size()];
            return csmv.toArray(csmv2);
        } else {
            return null;
        }
    }

    public CyberScouterMatches getCurrentMatch(SQLiteDatabase db) {

        CyberScouterMatches csm = null;
        Cursor cursor = null;
        try {
            String[] projection = {
                    CyberScouterContract.Matches.COLUMN_NAME_MATCHID,
                    CyberScouterContract.Matches.COLUMN_NAME_EVENTID,
                    CyberScouterContract.Matches.COLUMN_NAME_LEVELIIISCOUTERID,
                    CyberScouterContract.Matches.COLUMN_NAME_MATCHNO,
                    CyberScouterContract.Matches.COLUMN_NAME_MATCHNOTAG,
                    CyberScouterContract.Matches.COLUMN_NAME_REDTEAM1,
                    CyberScouterContract.Matches.COLUMN_NAME_REDTEAM2,
                    CyberScouterContract.Matches.COLUMN_NAME_REDTEAM3,
                    CyberScouterContract.Matches.COLUMN_NAME_REDTEAM1TAG,
                    CyberScouterContract.Matches.COLUMN_NAME_REDTEAM2TAG,
                    CyberScouterContract.Matches.COLUMN_NAME_REDTEAM3TAG,
                    CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM1,
                    CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM2,
                    CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM3,
                    CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM1TAG,
                    CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM2TAG,
                    CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM3TAG,
                    CyberScouterContract.Matches.COLUMN_NAME_MATCHVIDEO,
                    CyberScouterContract.Matches.COLUMN_NAME_COMMENTS,
                    CyberScouterContract.Matches.COLUMN_NAME_ELIMINATIONMATCH,
                    CyberScouterContract.Matches.COLUMN_NAME_STARTOFTELEOP,
                    CyberScouterContract.Matches.COLUMN_NAME_MATCHENDED
            };

            String sortOrder =
                    CyberScouterContract.Matches.COLUMN_NAME_MATCHNO + " ASC";

            String selection = CyberScouterContract.Matches.COLUMN_NAME_MATCHENDED + " = ?";
            String[] selectionArgs = { "0" };


            cursor = db.query(
                    CyberScouterContract.Matches.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );

            if( 0 < cursor.getCount() && cursor.moveToFirst()) {
                csm = new CyberScouterMatches();
                /* Read the match information from SQLite */
                csm.matchId = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_MATCHID));
                csm.eventId = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_EVENTID));
                csm.levelIiiScouterId = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_LEVELIIISCOUTERID));
                csm.matchNo = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_MATCHNO));
                csm.matchNo_Tag = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_MATCHNOTAG));
                csm.redTeam1 = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_REDTEAM1));
                csm.redTeam2 = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_REDTEAM2));
                csm.redTeam3 = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_REDTEAM3));
                csm.redTeam1Tag = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_REDTEAM1TAG));
                csm.redTeam2Tag = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_REDTEAM2TAG));
                csm.redTeam3Tag = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_REDTEAM3TAG));
                csm.blueTeam1 = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM1));
                csm.blueTeam2 = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM2));
                csm.blueTeam3 = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM3));
                csm.blueTeam1Tag = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM1TAG));
                csm.blueTeam2Tag = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM2TAG));
                csm.blueTeam3Tag = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_BLUETEAM3TAG));
                csm.matchVideo = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_MATCHVIDEO));
                csm.comments = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_COMMENTS));
                csm.eliminationMatch = (cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_ELIMINATIONMATCH)) == 1);
//                csm.startOfTeleop = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_STARTOFTELEOP));
                csm.matchEnded = (cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Matches.COLUMN_NAME_MATCHENDED)) == 1);

            }

            return csm;

        } catch (Exception e) {
            e.printStackTrace();
            throw(e);
        } finally {
            if(null != cursor && !cursor.isClosed())
                cursor.close();
        }
    }

    public int getMatchId() {
        return matchId;
    }

    public int getEventId() {
        return eventId;
    }

    public int getLevelIiiScouterId() {
        return levelIiiScouterId;
    }

    public int getMatchNo() {
        return matchNo;
    }

    public int getMatchNo_Tag() {
        return matchNo_Tag;
    }

    public String getRedTeam1() {
        return redTeam1;
    }

    public String getRedTeam2() {
        return redTeam2;
    }

    public String getRedTeam3() {
        return redTeam3;
    }

    public String getRedTeam1Tag() {
        return redTeam1Tag;
    }

    public String getRedTeam2Tag() {
        return redTeam2Tag;
    }

    public String getRedTeam3Tag() {
        return redTeam3Tag;
    }

    public String getBlueTeam1() {
        return blueTeam1;
    }

    public String getBlueTeam2() {
        return blueTeam2;
    }

    public String getBlueTeam3() {
        return blueTeam3;
    }

    public String getBlueTeam1Tag() {
        return blueTeam1Tag;
    }

    public String getBlueTeam2Tag() {
        return blueTeam2Tag;
    }

    public String getBlueTeam3Tag() {
        return blueTeam3Tag;
    }

    public String getComments() {
        return comments;
    }

    public boolean isEliminationMatch() {
        return eliminationMatch;
    }

    public java.sql.Date getStartOfTeleop() {
        return startOfTeleop;
    }

    public boolean isMatchEnded() {
        return matchEnded;
    }
}
