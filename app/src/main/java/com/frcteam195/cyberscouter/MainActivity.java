package com.frcteam195.cyberscouter;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
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

import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.DriverManager;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private Button button;
    static private CyberScouterEvent g_event = null;
    private int g_current_event_id;
    private CyberScouterMatchScouting[] g_matches = null;

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
            /* Read the config values from SQLite */
            CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

            /* if there's no existing configuration, we're going to assume the tablet
            is "online", meaning that it can talk to the SQL Server database.  If there is a
            configuration record, we'll use the offline setting from that to determine whether we
            should query the SQL Server database for the current event.
             */
            if ((null == cfg) || (!cfg.isOffline())) {
                (new getEventTask(this)).execute(null, null, null);
                int i = 0;
                while (g_event == null && i < DbInfo.SQL_TIMEOUT) {
                    sleep(10);
                    i += 10;
                }
            }

            // If there's no event record in the local config
            // and we can't get an event record from the remote
            // SQL Server database
            if (null == g_event && (null == cfg || null == cfg.getEvent())) {
                button = findViewById(R.id.button);
                button.setEnabled(false);
                button = findViewById(R.id.button2);
                button.setEnabled(false);
                button = findViewById(R.id.button3);
                button.setEnabled(false);
                button = findViewById(R.id.button4);
                button.setEnabled(false);
                MessageBox.showMessageBox(this, "Event Not Found Alert","processConfig", "No current event found!  Cannot continue.");
            } else {
                TextView tv = null;
                if (null != cfg) {
                    tv = findViewById(R.id.textView41);
                    String tmp = cfg.getRole();
                    if (tmp.startsWith("Blu"))
                        tv.setTextColor(Color.BLUE);
                    else if (tmp.startsWith("Red"))
                        tv.setTextColor(Color.RED);
                    else
                        tv.setTextColor(Color.BLACK);
                    tv.setText(cfg.getRole());

                    tv = findViewById(R.id.textView4);
                    if (null != g_event && (g_event.getEventID() != cfg.getEvent_id())) {
                        setEvent(g_event);
                        tv.setText(g_event.getEventName());
                    } else {
                        tv.setText(cfg.getEvent());
                    }

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
            }

        } catch (Exception e) {
            MessageBox.showMessageBox(this, "Exception Caught", "processConfig", "An exception occurred: \n" + e.getMessage());
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
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                if (g_adminPassword.matches(userInput.getText().toString())) {
                                    Intent intent = new Intent(getApplicationContext(), Admin1.class);
                                    startActivity(intent);
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
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

        if (null != cfg && cfg.isOffline()) {
            MessageBox.showMessageBox(this, "Offline Alert", "syncData", "You are currently offline. If you want to sync, please get online!");
        } else {
            try {

                if (null != cfg && !cfg.isOffline()) {
                    g_current_event_id = cfg.getEvent_id();
                    (new getMatchScoutingTask()).execute(null, null, null);
                    int i = 0;
                    while (null == g_matches) {
                        sleep(10);
                        if (++i > 30)
                            break;
                    }
                    if (g_matches != null) {
                        CyberScouterMatchScouting.deleteMatches(this);
                        for (i = 0; i < g_matches.length; ++i) {
                            CyberScouterMatchScouting.setMatch(this, g_matches[i]);
                        }
                    }
                }
            } catch (Exception e) {
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
        private WeakReference<Context> activityContext;
        private String exceptionText = null;

        public getEventTask(Context ctx) {
            super();
            activityContext = new WeakReference<>(ctx);
        }

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
                exceptionText = e.getMessage();
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            if(null != exceptionText) {
                MessageBox.showMessageBox(activityContext.get(), "Fetch Event Failed Alert", "getEventTask", "Fetch of Current Event failed!\n" +
                        "You may want to consider working offline.\n" + exceptionText);
            }
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

}
