package com.frcteam195.cyberscouter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import java.util.Vector;

public class CyberScouterQuestions {

    private int questionID;
    private int eventID;
    private int questionNumber;
    private String questionText;
    private String[] answers;

    static void setLocalQuestions(SQLiteDatabase db, CyberScouterQuestions[] scqa) {
        ContentValues values = new ContentValues();

        for (CyberScouterQuestions quest : scqa) {
            values.put(CyberScouterContract.Questions.COLUMN_NAME_QUESTIONID, quest.getQuestionID());
            values.put(CyberScouterContract.Questions.COLUMN_NAME_EVENTID, quest.getEventID());
            values.put(CyberScouterContract.Questions.COLUMN_NAME_QUESTIONNUMBER, quest.getQuestionNumber());
            values.put(CyberScouterContract.Questions.COLUMN_NAME_QUESTIONTEXT, quest.getQuestionText());
            values.put(CyberScouterContract.Questions.COLUMN_NAME_ANSWERS, String.join("|", quest.getAnswers()));

            long newRowId = db.insert(CyberScouterContract.Questions.TABLE_NAME, null, values);
        }
    }

    static CyberScouterQuestions[] getQuestions(Connection conn, int mCurrentEventID) {
        Vector<CyberScouterQuestions> csqv = new Vector<CyberScouterQuestions>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(Locale.getDefault(), "SELECT * from " +
                    CyberScouterContract.Questions.TABLE_NAME +
                    " WHERE " + CyberScouterContract.Questions.COLUMN_NAME_EVENTID +
                            " = %d " +
                    " order by " + CyberScouterContract.Questions.COLUMN_NAME_QUESTIONNUMBER, mCurrentEventID));
            while (rs.next()) {
                CyberScouterQuestions csq = new CyberScouterQuestions();
                csq.questionID = rs.getInt(rs.findColumn(CyberScouterContract.Questions.COLUMN_NAME_QUESTIONID));
                csq.eventID = rs.getInt(rs.findColumn(CyberScouterContract.Questions.COLUMN_NAME_EVENTID));
                csq.questionNumber = rs.getInt(rs.findColumn(CyberScouterContract.Questions.COLUMN_NAME_QUESTIONNUMBER));
                csq.questionText = rs.getString(rs.findColumn(CyberScouterContract.Questions.COLUMN_NAME_QUESTIONTEXT));
                csq.answers = rs.getString(rs.findColumn(CyberScouterContract.Questions.COLUMN_NAME_ANSWERS)).split("\\|");

                csqv.add(csq);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (csqv.size() > 0) {
            CyberScouterQuestions[] csqv2 = new CyberScouterQuestions[csqv.size()];
            return csqv.toArray(csqv2);
        } else {
            return null;
        }

    }

    static CyberScouterQuestions getLocalQuestion(SQLiteDatabase db, int lEventID, int lQuestionNumber) {
        Cursor cursor = null;

        CyberScouterQuestions csq = null;

        String[] projection = {
                CyberScouterContract.Questions.COLUMN_NAME_QUESTIONID,
                CyberScouterContract.Questions.COLUMN_NAME_EVENTID,
                CyberScouterContract.Questions.COLUMN_NAME_QUESTIONNUMBER,
                CyberScouterContract.Questions.COLUMN_NAME_QUESTIONTEXT,
                CyberScouterContract.Questions.COLUMN_NAME_ANSWERS
        };

        String selection = CyberScouterContract.Questions.COLUMN_NAME_QUESTIONNUMBER + " = ? AND " +
                CyberScouterContract.Questions.COLUMN_NAME_EVENTID + " = ?";
        String[] selectionArgs = {String.format(Locale.getDefault(), "%d", lQuestionNumber) , String.format(Locale.getDefault(), "%d", lEventID)};

        cursor = db.query(
                CyberScouterContract.Questions.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        if (0 < cursor.getCount()) {
            if(cursor.moveToNext()) {
                csq = new CyberScouterQuestions();

                csq.questionID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Questions.COLUMN_NAME_QUESTIONID));
                csq.eventID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Questions.COLUMN_NAME_EVENTID));
                csq.questionNumber = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Questions.COLUMN_NAME_QUESTIONNUMBER));
                csq.questionText = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Questions.COLUMN_NAME_QUESTIONTEXT));
                csq.answers = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Questions.COLUMN_NAME_ANSWERS)).split("\\|");
                return (csq);
            }
        }

        return null;
    }

    static void deleteQuestions(SQLiteDatabase db) {
        db.execSQL("DELETE from " + CyberScouterContract.Questions.TABLE_NAME);
    }

    public static int getQuestionCount(SQLiteDatabase db) {
        String countQuery = "SELECT  * FROM " + CyberScouterContract.Questions.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getQuestionID() {
        return questionID;
    }

    public int getEventID() {
        return eventID;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getAnswers() {
        return answers;
    }
}
