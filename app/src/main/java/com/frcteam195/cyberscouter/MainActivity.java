package com.frcteam195.cyberscouter;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.sql.Connection;
import java.sql.DriverManager;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private Button button;
    static private CyberScouterEvent g_event = null;
    private int g_current_event_id;
    private CyberScouterMatchScouting[] g_matches  = null;

    static final private String g_adminPassword = "HailRobotOverlords";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdmin1();

            }
        });

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScouting();

            }
        });

        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syncPictures();

            }
        });

        button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syncData();

            }
        });

        final ToggleButton tb = findViewById(R.id.SwitchButton);
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOffline(tb);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        processConfig(db);
    }

    @Override
    protected void onDestroy() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        mDbHelper.close();
        super.onDestroy();
    }

    private void processConfig(SQLiteDatabase db) {
        try {
            CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

            /* if there's no existing configuration, we're going to assume the tablet
            is "online", meaning that it can talk to the SQL Server database.  If there is a
            configuration record, we'll use the offline setting from that to determine whether we
            should query the SQL Server database for the current event.
             */
            if ((null == cfg) || (null != cfg && !cfg.isOffline())) {
                (new getEventTask()).execute(null, null, null);
                while (g_event == null)
                    sleep(10);
            }

            TextView tv = null;
            if (null != cfg) {
                /* Read the config values from SQLite */
                tv = findViewById(R.id.textView41);
                String tmp = cfg.getRole();
                if (tmp.startsWith("Blu"))
                    tv.setTextColor(Color.BLUE);
                else if (tmp.startsWith("Red"))
                    tv.setTextColor(Color.RED);
                else
                    tv.setTextColor(Color.BLACK);
                tv.setText(cfg.getRole());

                if(g_event.getEventID() != cfg.getEvent_id()) {
                    setEvent(g_event);
                }
                tv = findViewById(R.id.textView4);
                tv.setText(g_event.getEventName());

                /* Make the offline toggle button reflect the last setting */
                ToggleButton tb = findViewById(R.id.SwitchButton);
                tb.setChecked(!cfg.isOffline());
            } else {
                String tmp = null;
                ContentValues values = new ContentValues();
                tmp = "Unknown Role";
                tv = findViewById(R.id.textView41);
                tv.setText(tmp);
                values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_ROLE, tmp);
                if (null != g_event)
                    tmp = g_event.getEventName();
                else
                    tmp = "Unknown Event";
                tv = findViewById(R.id.textView4);
                tv.setText(tmp);
                values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT, tmp);
                values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_TABLET_NUM, 0);
                ToggleButton tb = findViewById(R.id.SwitchButton);
                tb.setChecked(true);
                values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_OFFLINE, 0);
                values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_FIELD_REDLEFT, 1);

// Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(CyberScouterContract.ConfigEntry.TABLE_NAME, null, values);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openAdmin1() {
        LayoutInflater li = LayoutInflater.from(this);
        View pwdView = li.inflate(R.layout.dialog_password, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(pwdView);

        final EditText userInput = (EditText) pwdView
                .findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                if(g_adminPassword.matches(userInput.getText().toString())) {
                                    Intent intent = new Intent(getApplicationContext(), Admin1.class);
                                    startActivity(intent);
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void openScouting() {
        Intent intent = new Intent(this, ScoutingPage.class);
        startActivity(intent);
    }

    public void syncPictures() {
    }

    public void syncData() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        if(null != cfg && cfg.isOffline()) {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Offline Alert");
            alertDialog.setMessage("You are currently offline.  If you want to sync, please get online!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        } else {
            try {

                if (null != cfg && !cfg.isOffline()) {
                    g_current_event_id = cfg.getEvent_id();
                    (new getMatchScoutingTask()).execute(null, null, null);
                    int i = 0;
                    while (null == g_matches) {
                        sleep(10);
                        if(++i > 30)
                            break;
                    }
                    if(g_matches != null) {
                        deleteMatches();
                        for (i = 0; i < g_matches.length; ++i) {

                            setMatch(g_matches[i]);

                        }
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void setOffline(ToggleButton tb) {
        try {
            int chkd = 1;
            if (tb.isChecked())
                chkd = 0;

            CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_OFFLINE, chkd);
            int count = db.update(
                    CyberScouterContract.ConfigEntry.TABLE_NAME,
                    values,
                    null,
                    null);
        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        }
    }

    private class getEventTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg) {

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://"
                        + DbInfo.MSSQLServerAddress + "/" + DbInfo.MSSQLDbName, DbInfo.MSSQLUsername, DbInfo.MSSQLPassword);

                CyberScouterEvent cse = new CyberScouterEvent();
                CyberScouterEvent cse2 = cse.getCurrentEvent(conn);

                if (null != cse2) {
                    g_event = cse2;
                    setEvent(cse2);
                }

                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    private void setEvent(CyberScouterEvent cse) {
        try {
            CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT, cse.getEventName());
            values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT_ID, cse.getEventID());
            int count = db.update(
                    CyberScouterContract.ConfigEntry.TABLE_NAME,
                    values,
                    null,
                    null);
        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        }

    }

    private class getMatchScoutingTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg) {

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://"
                        + DbInfo.MSSQLServerAddress + "/" + DbInfo.MSSQLDbName, DbInfo.MSSQLUsername, DbInfo.MSSQLPassword);

                CyberScouterMatchScouting csm = new CyberScouterMatchScouting();
                g_matches = csm.getMatches(conn, g_current_event_id);

                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }


    private void setMatch(CyberScouterMatchScouting csm) {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
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
//                values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_STARTOFTELEOP, csm.getStartOfTeleop());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_MATCHENDED, csm.getMatchEnded());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_QUESTIONSANSWERED, csm.getQuestionsAnswered());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SCOUTINGSTATUS, csm.getScoutingStatus());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AREASTOREVIEW, csm.getAreasToReview());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_COMPLETE, csm.getComplete());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS, csm.getAutoStartPos());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOPRELOAD, csm.getAutoPreload());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTODIDNOTSHOW, csm.getAutoDidNotShow());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS, csm.getAutoMoveBonus());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOCSCARGO, csm.getAutoCSCargo());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOCSHATCH, csm.getAutoCSHatch());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSCARGOLOW, csm.getAutoRSCargoLow());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSCARGOMED, csm.getAutoRSCargoMed());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSCARGOHIGH, csm.getAutoRSCargoHigh());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHFARLOW, csm.getAutoRSHatchFarLow());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHFARMED, csm.getAutoRSHatchFarMed());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHFARHIGH, csm.getAutoRSHatchFarHigh());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHNEARLOW, csm.getAutoRSHatchNearLow());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHNEARMED, csm.getAutoRSHatchNearMed());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_AUTORSHATCHNEARHIGH, csm.getAutoRSHatchNearHigh());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELECSCARGO, csm.getTeleCSCargo());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELECSHATCH, csm.getTeleCSHatch());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOLOW, csm.getTeleRSCargoLow());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOMED, csm.getTeleRSCargoMed());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOHIGH, csm.getTeleRSCargoHigh());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARLOW, csm.getTeleRSHatchFarLow());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARMED, csm.getTeleRSHatchFarMed());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARHIGH, csm.getTeleRSHatchFarHigh());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARLOW, csm.getTeleRSHatchNearLow());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARMED, csm.getTeleRSHatchNearMed());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARHIGH, csm.getTeleRSHatchNearHigh());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSCORE, csm.getClimbScore());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBASSIST, csm.getClimbAssist());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMHATCHGRDPICKUP, csm.getSummHatchGrdPickup());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM, csm.getSummLostComm());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMBROKE, csm.getSummBroke());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMTIPOVER, csm.getSummTipOver());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMSUBSYSTEMBROKE, csm.getSummSubsystemBroke());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER01, csm.getAnswer01());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER02, csm.getAnswer02());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER03, csm.getAnswer03());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER04, csm.getAnswer04());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER05, csm.getAnswer05());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER06, csm.getAnswer06());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER07, csm.getAnswer07());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER08, csm.getAnswer08());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER09, csm.getAnswer09());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_ANSWER10, csm.getAnswer10());
        values.put(CyberScouterContract.MatchScouting.COLUMN_NAME_UPLOADSTATUS, UploadStatus.NOT_UPLOADED);

        long newRowId = db.insert(CyberScouterContract.MatchScouting.TABLE_NAME, null, values);
    }

    private void deleteMatches() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        db.delete(CyberScouterContract.MatchScouting.TABLE_NAME, null, null);
    }
}
