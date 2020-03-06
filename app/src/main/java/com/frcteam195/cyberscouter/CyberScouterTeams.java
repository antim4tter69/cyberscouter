package com.frcteam195.cyberscouter;

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

import java.security.cert.PolicyNode;
import java.util.Locale;
import java.util.Vector;

class CyberScouterTeams {
    public final static String TEAMS_UPDATED_FILTER = "frcteam195_cyberscouterteams_teams_updated_intent_filter";

    private static String last_hash = null;

    private static String webResponse;
    static String getWebResponse() {
        return(webResponse);
    }


    private int teamID;
    private int team;
    private String TeamName;
    private String TeamLocation;
    private int NumWheels;
    private int NumDriveMotors;
    private int WheelTypeID;
    private int DriveTypeID;
    private int MotorTypeID;
    private int LanguageID;
    private int Speed;
    private String GearRatio;
    private int NumGearSpeed;
    private int RobotLength;
    private int RobotWidth;
    private int RobotHeight;
    private int RobotWeight;
    private int Pneumatics;
    private int NumPreload;
    private int AutoBallsScored;
    private int MoveBonus;
    private int AutoPickUp;
    private int AutoStartPosID;
    private String AutoSummary;
    private int TeleBallsScored;
    private int MaxBallCapacity;
    private int ColorWheel;
    private int TeleDefense;
    private int TeleDefenseEvade;
    private String TeleStrategy;
    private int CanClimb;
    private int CenterClimb;
    private int CanMoveOnBar;
    private int LockingMechanism;
    private int ClimbHeightID;
    private int DoneScouting;
    private int UploadStatus;

    static String getTeamsRemote(AppCompatActivity activity) {
        String ret = null;

        try {
            BluetoothComm btcomm = new BluetoothComm();
            String response = btcomm.getTeams(activity, last_hash);
            if (null != response) {
                JSONObject jo = new JSONObject(response);
                String result = jo.getString("result");
                if ("failure" != result) {
                    if(result.equalsIgnoreCase("skip")) {
                        ret = "skip";
                    } else {
                        JSONArray payload = jo.getJSONArray("payload");
                        ret = payload.toString();
                        last_hash = jo.getString("hash");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (ret);
    }

    public String setTeamsRemote(AppCompatActivity activity) {
        String ret = "failed";
        try {
            JSONObject jo = new JSONObject();
            jo.put("cmd", "put-teams");
            jo.put("key", team);
            JSONObject payload = new JSONObject();
            payload.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS, AutoStartPosID);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_NUM_WHEELS, NumWheels);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_DRIVE_MOTORS, NumDriveMotors);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_WHEEL_TYPE_ID, WheelTypeID);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_DRIVE_TYPE_ID, DriveTypeID);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_MOTOR_TYPE_ID, MotorTypeID);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_LANGUAGE_ID, LanguageID);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_SPEED, Speed);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_GEAR_RATIO, GearRatio);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_NUM_GEAR_SPEED, NumGearSpeed);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_ROBOT_LENGTH, RobotLength);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_ROBOT_WIDTH, RobotWidth);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_ROBOT_HEIGHT, RobotHeight);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_ROBOT_WEIGHT, RobotWeight);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_PNEUMATICS, Pneumatics);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_NUM_PRE_LOAD, NumPreload);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_AUTO_BALLS_SCORED, AutoBallsScored);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_MOVE_BONUS, MoveBonus);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_AUTO_PICKUP, AutoPickUp);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_AUTO_START_POS_ID, AutoStartPosID);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_AUTO_SUMMARY, AutoSummary);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_TELE_BALLS_SCORED, TeleBallsScored);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_MAX_BALL_CAPACITY, MaxBallCapacity);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_COLOR_WHEEL, ColorWheel);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_TELE_DEFENSE, TeleDefense);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_TELE_DEFENSE_EVADE, TeleDefenseEvade);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_TELE_STRATEGY, TeleStrategy);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_CAN_CLIMB, CanClimb);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_CENTER_CLIMB, CenterClimb);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_CAN_MOVE_ON_BAR, CanMoveOnBar);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_LOCKING_MECHANISM, LockingMechanism);
            payload.put(CyberScouterContract.Teams.COLUMN_NAME_CLIMB_HEIGHT_ID, ClimbHeightID);

            jo.put("payload", payload);

            BluetoothComm btcomm = new BluetoothComm();
            String response = btcomm.sendSetCommand(activity, jo);
            if (null != response) {
                response = response.replace("x03", "");
                JSONObject jresp = new JSONObject(response);
                ret = jresp.getString("result");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return(ret);
    }


    static public void getTeamsWebService(final AppCompatActivity activity) {
        RequestQueue rq = Volley.newRequestQueue(activity);
        String url = String.format("%s/teams", FakeBluetoothServer.webServiceBaseUrl);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Intent i = new Intent(TEAMS_UPDATED_FILTER);
                            webResponse = response;
                            i.putExtra("cyberscouterteams", "fetch");
                            activity.sendBroadcast(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        rq.add(stringRequest);
        return;

    }

    static String[] getTeamNumbers(SQLiteDatabase db) {
        Vector<String> teamVector = new Vector<>();
        Cursor cursor;
        try {
            String[] projection = {
                    CyberScouterContract.Teams.COLUMN_NAME_TEAM,
            };

            cursor = db.query(
                    CyberScouterContract.Teams.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    null,              // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    CyberScouterContract.Teams.COLUMN_NAME_TEAM + " ASC"               // The sort order
            );

            if (0 < cursor.getCount()) {
                while (cursor.moveToNext()) {
                    String team_no = String.valueOf(cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_TEAM)));
                    teamVector.add(team_no);
                }
            }



            } catch (Exception e) {
            e.printStackTrace();
        }

        if (0 < teamVector.size()) {
            String[] nv2 = new String[teamVector.size()];
            return teamVector.toArray(nv2);
        } else {
            return(null);
        }
    }

    static CyberScouterTeams getCurrentTeam(SQLiteDatabase db, int team) {
        CyberScouterTeams cst = null;

        Cursor cursor = null;
        try {
            String selection = CyberScouterContract.Teams.COLUMN_NAME_TEAM + " = ?";
            String[] selectionArgs = {
                    String.format(Locale.getDefault(), "%d", team)
            };


            String[] projection = {
                    CyberScouterContract.Teams.COLUMN_NAME_TEAM,
                    CyberScouterContract.Teams.COLUMN_NAME_NUM_WHEELS,
                    CyberScouterContract.Teams.COLUMN_NAME_DRIVE_MOTORS,
                    CyberScouterContract.Teams.COLUMN_NAME_WHEEL_TYPE_ID,
                    CyberScouterContract.Teams.COLUMN_NAME_DRIVE_TYPE_ID,
                    CyberScouterContract.Teams.COLUMN_NAME_MOTOR_TYPE_ID,
                    CyberScouterContract.Teams.COLUMN_NAME_LANGUAGE_ID,
                    CyberScouterContract.Teams.COLUMN_NAME_SPEED,
                    CyberScouterContract.Teams.COLUMN_NAME_GEAR_RATIO,
                    CyberScouterContract.Teams.COLUMN_NAME_NUM_GEAR_SPEED,
                    CyberScouterContract.Teams.COLUMN_NAME_ROBOT_LENGTH,
                    CyberScouterContract.Teams.COLUMN_NAME_ROBOT_WIDTH,
                    CyberScouterContract.Teams.COLUMN_NAME_ROBOT_HEIGHT,
                    CyberScouterContract.Teams.COLUMN_NAME_ROBOT_WEIGHT,
                    CyberScouterContract.Teams.COLUMN_NAME_PNEUMATICS,
                    CyberScouterContract.Teams.COLUMN_NAME_NUM_PRE_LOAD,
                    CyberScouterContract.Teams.COLUMN_NAME_AUTO_BALLS_SCORED,
                    CyberScouterContract.Teams.COLUMN_NAME_MOVE_BONUS,
                    CyberScouterContract.Teams.COLUMN_NAME_AUTO_PICKUP,
                    CyberScouterContract.Teams.COLUMN_NAME_AUTO_START_POS_ID,
                    CyberScouterContract.Teams.COLUMN_NAME_AUTO_SUMMARY,
                    CyberScouterContract.Teams.COLUMN_NAME_TELE_BALLS_SCORED,
                    CyberScouterContract.Teams.COLUMN_NAME_MAX_BALL_CAPACITY,
                    CyberScouterContract.Teams.COLUMN_NAME_COLOR_WHEEL,
                    CyberScouterContract.Teams.COLUMN_NAME_TELE_DEFENSE,
                    CyberScouterContract.Teams.COLUMN_NAME_TELE_DEFENSE_EVADE,
                    CyberScouterContract.Teams.COLUMN_NAME_TELE_STRATEGY,
                    CyberScouterContract.Teams.COLUMN_NAME_CAN_CLIMB,
                    CyberScouterContract.Teams.COLUMN_NAME_CENTER_CLIMB,
                    CyberScouterContract.Teams.COLUMN_NAME_CAN_MOVE_ON_BAR,
                    CyberScouterContract.Teams.COLUMN_NAME_LOCKING_MECHANISM,
                    CyberScouterContract.Teams.COLUMN_NAME_CLIMB_HEIGHT_ID,
                    CyberScouterContract.Teams.COLUMN_NAME_DONE_SCOUTING,
                    CyberScouterContract.Teams.COLUMN_NAME_UPLOAD_STATUS
            };

            cursor = db.query(
                    CyberScouterContract.Teams.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );

            if (0 < cursor.getCount()) {
                while (cursor.moveToNext()) {
                    cst = new CyberScouterTeams();
                    cst.team = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_TEAM));
                    cst.NumWheels = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_NUM_WHEELS));
                    cst.NumDriveMotors = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_DRIVE_MOTORS));
                    cst.WheelTypeID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_WHEEL_TYPE_ID));
                    cst.DriveTypeID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_DRIVE_TYPE_ID));
                    cst.MotorTypeID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_MOTOR_TYPE_ID));
                    cst.LanguageID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_LANGUAGE_ID));
                    cst.Speed = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_SPEED));
                    cst.GearRatio = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_GEAR_RATIO));
                    cst.NumGearSpeed = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_NUM_GEAR_SPEED));
                    cst.RobotLength = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_ROBOT_LENGTH));
                    cst.RobotWidth = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_ROBOT_WIDTH));
                    cst.RobotHeight = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_ROBOT_HEIGHT));
                    cst.RobotWeight = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_ROBOT_WEIGHT));
                    cst.Pneumatics = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_PNEUMATICS));
                    cst.NumPreload = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_NUM_PRE_LOAD));
                    cst.AutoBallsScored = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_AUTO_BALLS_SCORED));
                    cst.MoveBonus = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_MOVE_BONUS));
                    cst.AutoPickUp = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_AUTO_PICKUP));
                    cst.AutoStartPosID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_AUTO_START_POS_ID));
                    cst.AutoSummary = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_AUTO_SUMMARY));
                    cst.TeleBallsScored = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_TELE_BALLS_SCORED));
                    cst.MaxBallCapacity = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_MAX_BALL_CAPACITY));
                    cst.ColorWheel = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_COLOR_WHEEL));
                    cst.TeleDefense = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_TELE_DEFENSE));
                    cst.TeleDefenseEvade = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_TELE_DEFENSE_EVADE));
                    cst.TeleStrategy = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_TELE_STRATEGY));
                    cst.CanClimb = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_CAN_CLIMB));
                    cst.CenterClimb = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_CENTER_CLIMB));
                    cst.CanMoveOnBar = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_CAN_MOVE_ON_BAR));
                    cst.LockingMechanism = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_LOCKING_MECHANISM));
                    cst.ClimbHeightID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_CLIMB_HEIGHT_ID));
                    cst.DoneScouting = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_DONE_SCOUTING));
                    cst.UploadStatus = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Teams.COLUMN_NAME_UPLOAD_STATUS));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cst;
    }

    static void setTeams(SQLiteDatabase db, String json) {

        try {

            JSONArray ja = new JSONArray(json);
            for (int i = 0; i < ja.length(); ++i) {
                JSONObject jo = ja.getJSONObject(i);
                ContentValues values = new ContentValues();

                values.put(CyberScouterContract.Teams.COLUMN_NAME_TEAM, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_TEAM));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_TEAM_NAME, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_TEAM_NAME));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_TEAM_LOCATION, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_TEAM_LOCATION));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_NUM_WHEELS, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_NUM_WHEELS));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_DRIVE_MOTORS, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_DRIVE_MOTORS));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_WHEEL_TYPE_ID, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_WHEEL_TYPE_ID));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_DRIVE_TYPE_ID, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_DRIVE_TYPE_ID));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_MOTOR_TYPE_ID, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_MOTOR_TYPE_ID));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_LANGUAGE_ID, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_LANGUAGE_ID));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_SPEED, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_SPEED));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_GEAR_RATIO, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_GEAR_RATIO));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_NUM_GEAR_SPEED, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_NUM_GEAR_SPEED));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_ROBOT_LENGTH, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_ROBOT_LENGTH));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_ROBOT_WIDTH, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_ROBOT_WIDTH));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_ROBOT_HEIGHT, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_ROBOT_HEIGHT));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_ROBOT_WEIGHT, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_ROBOT_WEIGHT));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_PNEUMATICS, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_PNEUMATICS));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_NUM_PRE_LOAD, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_NUM_PRE_LOAD));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_AUTO_BALLS_SCORED, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_AUTO_BALLS_SCORED));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_MOVE_BONUS, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_MOVE_BONUS));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_AUTO_PICKUP, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_AUTO_PICKUP));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_AUTO_START_POS_ID, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_AUTO_START_POS_ID));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_AUTO_SUMMARY, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_AUTO_SUMMARY));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_TELE_BALLS_SCORED, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_TELE_BALLS_SCORED));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_MAX_BALL_CAPACITY, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_MAX_BALL_CAPACITY));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_COLOR_WHEEL, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_COLOR_WHEEL));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_TELE_DEFENSE, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_TELE_DEFENSE));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_TELE_DEFENSE_EVADE, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_TELE_DEFENSE_EVADE));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_TELE_STRATEGY, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_TELE_STRATEGY));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_CAN_CLIMB, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_CAN_CLIMB));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_CENTER_CLIMB, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_CENTER_CLIMB));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_CAN_MOVE_ON_BAR, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_CAN_MOVE_ON_BAR));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_LOCKING_MECHANISM, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_LOCKING_MECHANISM));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_CLIMB_HEIGHT_ID, jo.getString(CyberScouterContract.Teams.COLUMN_NAME_CLIMB_HEIGHT_ID));
                values.put(CyberScouterContract.Teams.COLUMN_NAME_DONE_SCOUTING, 0);
                values.put(CyberScouterContract.Teams.COLUMN_NAME_UPLOAD_STATUS, 0);

                long newRowId = db.insertWithOnConflict(CyberScouterContract.Teams.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void deleteTeams(SQLiteDatabase db) {
        db.execSQL("DELETE from " + CyberScouterContract.Teams.TABLE_NAME);
    }

    static void updateTeamMetric(SQLiteDatabase db, String lcolumn, Integer lvalue, Integer currentTeam) throws Exception {
        ContentValues values = new ContentValues();
        values.put(lcolumn, lvalue);
        updateTeamMetric(db, values, currentTeam);
    }

    static void updateTeamMetric(SQLiteDatabase db, String lcolumn, String lvalue, Integer currentTeam) throws Exception {
        ContentValues values = new ContentValues();
        values.put(lcolumn, lvalue);
        updateTeamMetric(db, values, currentTeam);
    }

    static private void updateTeamMetric(SQLiteDatabase db, ContentValues cv, Integer currentTeam) throws Exception {
        String selection = CyberScouterContract.Teams.COLUMN_NAME_TEAM + " = ?";
        String[] selectionArgs = {
                String.format(Locale.getDefault(), "%d", currentTeam)
        };

        if (1 > updateTeam(db, cv, selection, selectionArgs))
            throw new Exception(String.format("An error occurred while updating the local teams table.\n\nNo rows were updated for Team=%d", currentTeam));
    }

    private static int updateTeam(SQLiteDatabase db, ContentValues values, String selection, String[] selectionArgs) {
        return db.update(
                CyberScouterContract.Teams.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    static CyberScouterTeams[] getTeamsReadyToUpload(SQLiteDatabase db) {

        String selection =
                CyberScouterContract.Teams.COLUMN_NAME_DONE_SCOUTING + " = ? AND " +
                CyberScouterContract.Teams.COLUMN_NAME_UPLOAD_STATUS + " = ?";

        String[] selectionArgs = {
                String.format(Locale.getDefault(), "%d", 1),
                String.format(Locale.getDefault(), "%d", 0)
        };

        String sortOrder =
                CyberScouterContract.Teams.COLUMN_NAME_TEAM + " ASC";

        return (getLocalTeams(db, selection, selectionArgs, sortOrder));
    }

    static CyberScouterTeams[] getLocalTeams(SQLiteDatabase db, String selection, String[] selectionArgs, String sortOrder) {
        return(null);
    }

    int getTeam() {
        return team;
    }

    String getTeamName() {
        return TeamName;
    }

    String getTeamLocation() {
        return TeamLocation;
    }

    int getNumWheels() {
        return NumWheels;
    }

    int getNumDriveMotors() {
        return NumDriveMotors;
    }

    int getWheelTypeID() {
        return WheelTypeID;
    }

    int getDriveTypeID() {
        return DriveTypeID;
    }

    int getMotorTypeID() {
        return MotorTypeID;
    }

    int getLanguageID() {
        return LanguageID;
    }

    int getSpeed() {
        return Speed;
    }

    String getGearRatio() {
        return GearRatio;
    }

    int getNumGearSpeed() {
        return NumGearSpeed;
    }

    int getRobotLength() {
        return RobotLength;
    }

    int getRobotWidth() {
        return RobotWidth;
    }

    int getRobotHeight() {
        return RobotHeight;
    }

    int getRobotWeight() {
        return RobotWeight;
    }

    int getPneumatics() {
        return Pneumatics;
    }

    int getNumPreload() {
        return NumPreload;
    }

    int getAutoBallsScored() {
        return AutoBallsScored;
    }

    int getMoveBonus() {
        return MoveBonus;
    }

    int getAutoPickUp() {
        return AutoPickUp;
    }

    int getAutoStartPosID() {
        return AutoStartPosID;
    }

    String getAutoSummary() {
        return AutoSummary;
    }

    int getTeleBallsScored() {
        return TeleBallsScored;
    }

    int getMaxBallCapacity() {
        return MaxBallCapacity;
    }

    int getColorWheel() {
        return ColorWheel;
    }

    int getTeleDefense() {
        return TeleDefense;
    }

    int getTeleDefenseEvade() {
        return TeleDefenseEvade;
    }

    String getTeleStrategy() {
        return TeleStrategy;
    }

    int getCanClimb() {
        return CanClimb;
    }

    int getCenterClimb() {
        return CenterClimb;
    }

    int getCanMoveOnBar() {
        return CanMoveOnBar;
    }

    int getLockingMechanism() {
        return LockingMechanism;
    }

    int getClimbHeightID() {
        return ClimbHeightID;
    }

    int getDoneScouting() { return DoneScouting; }
}
