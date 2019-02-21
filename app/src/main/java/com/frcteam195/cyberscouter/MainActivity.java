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
import android.widget.Toast;
import android.widget.ToggleButton;
import java.sql.Connection;
import java.sql.DriverManager;

public class MainActivity extends AppCompatActivity {
    private Button button;

    static final private String g_adminPassword = "HailRobotOverlords";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdmin1();

            }
        });

        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScouting();

            }
        });

        button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syncPictures();

            }
        });

        button = findViewById(R.id.button4);
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

    private void processConfig(final SQLiteDatabase db) {
        try {
            /* Read the config values from SQLite */
            final CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

            /* if there's no existing local configuration, we're going to assume the tablet
            is "online", meaning that it can talk to the SQL Server database.  If there is a
            configuration record, we'll use the offline setting from that to determine whether we
            should query the SQL Server database for the current event.
             */
            if ((null == cfg) || (!cfg.isOffline())) {
                getEventTask eventTask = new getEventTask(new IOnEventListener<CyberScouterEvent>() {
                    @Override
                    public void onSuccess(CyberScouterEvent result) {
                        CyberScouterConfig cfg2 = cfg;
                        if (null != cfg) {
                            if (null != result && (result.getEventID() != cfg.getEvent_id())) {
                                setEvent(result);
                                cfg2 = CyberScouterConfig.getConfig(db);
                            }
                            setFieldsFromConfig(cfg2);
                        } else {
                            setFieldsToDefaults(db, result.getEventName());
                        }

                    }

                    @Override
                    public void onFailure(Exception e) {
                        if (null == e) {
                            if (null == cfg || null == cfg.getEvent()) {
                                button = findViewById(R.id.button);
                                button.setEnabled(false);
                                button = findViewById(R.id.button2);
                                button.setEnabled(false);
                                button = findViewById(R.id.button3);
                                button.setEnabled(false);
                                button = findViewById(R.id.button4);
                                button.setEnabled(false);
                                MessageBox.showMessageBox(MainActivity.this, "Event Not Found Alert", "processConfig", "No current event found!  Cannot continue.");
                            }
                        } else {
                            setFieldsFromConfig(cfg);
                            MessageBox.showMessageBox(MainActivity.this, "Fetch Event Failed Alert", "getEventTask", "Fetch of Current Event information failed!\n\n" +
                                    "You may want to consider working offline.\n\n" + "The error is:\n" + e.getMessage());

                        }
                    }
                });

                eventTask.execute();
            } else {
                setFieldsFromConfig(cfg);
            }
        } catch (Exception e) {
            MessageBox.showMessageBox(this, "Exception Caught", "processConfig", "An exception occurred: \n" + e.getMessage());
            e.printStackTrace();
        }
    }

    void setFieldsFromConfig(CyberScouterConfig cfg) {
        TextView tv;
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
        tv.setText(cfg.getEvent());

        /* Make the offline toggle button reflect the last setting */
        ToggleButton tb = findViewById(R.id.SwitchButton);
        tb.setChecked(!cfg.isOffline());
    }

    void setFieldsToDefaults(SQLiteDatabase db, String eventName) {
        TextView tv;
        String tmp;
        ContentValues values = new ContentValues();
        tmp = CyberScouterConfig.UNKNOWN_ROLE;
        tv = findViewById(R.id.textView41);
        tv.setText(tmp);
        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_ROLE, tmp);
        if (null != eventName)
            tmp = eventName;
        else
            tmp = CyberScouterConfig.UNKNOWN_EVENT;
        tv = findViewById(R.id.textView4);
        tv.setText(tmp);
        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT, tmp);
        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_TABLET_NUM, 0);
        ToggleButton tb = findViewById(R.id.SwitchButton);
        tb.setChecked(true);
        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_OFFLINE, 0);
        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_FIELD_REDLEFT, 1);
        values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_USERID, CyberScouterConfig.UNKNOWN_USER_IDX);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(CyberScouterContract.ConfigEntry.TABLE_NAME, null, values);
    }

    public void openAdmin1() {
        LayoutInflater li = LayoutInflater.from(this);
        View pwdView = li.inflate(R.layout.dialog_password, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(pwdView);

        final EditText userInput = pwdView
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
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        if(null != cfg && -1 == TeamMap.getNumberForTeam(cfg.getRole())) {
            MessageBox.showMessageBox(this, "Unspecified Role Alert", "openScouting", "No scouting role is specified (\"Red 1\", \"Blue 1\", \"Red 2\", etc). " +
                    "You must go into the Admin page and specify a scouting role before you can continue.");
        } else {
            Intent intent = new Intent(this, ScoutingPage.class);
            startActivity(intent);
        }
    }

    public void syncPictures() {
        Toast t = Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT);
        t.show();
    }

    public void syncData() {
        try {
            CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
            final SQLiteDatabase db = mDbHelper.getWritableDatabase();

            final CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

            if (null != cfg && cfg.isOffline()) {
                MessageBox.showMessageBox(this, "Offline Alert", "syncData", "You are currently offline. If you want to sync, please get online!");
            } else {
                if (null != cfg && !cfg.isOffline()) {
                    getMatchScoutingTask scoutingTask = new getMatchScoutingTask(new IOnEventListener<CyberScouterMatchScouting[]>() {
                        @Override
                        public void onSuccess(CyberScouterMatchScouting[] result) {
                            try {
                                CyberScouterMatchScouting.deleteOldMatches(MainActivity.this, cfg.getEvent_id());
                                String tmp = CyberScouterMatchScouting.mergeMatches(MainActivity.this, result);

                                Toast t = Toast.makeText(MainActivity.this, "Data synced successfully! -- " + tmp, Toast.LENGTH_SHORT);
                                t.show();
                            } catch(Exception ee) {
                                MessageBox.showMessageBox(MainActivity.this, "Fetch Match Scouting Failed Alert", "syncData.getMatchScoutingTask.onSuccess",
                                        "Attempt to update local match information failed!\n\n" +
                                                "The error is:\n" + ee.getMessage());
                            }
                        }

                        @Override
                        public void onFailure(Exception e) {
                            if (null != e) {
                                MessageBox.showMessageBox(MainActivity.this, "Fetch Match Scouting Failed Alert", "getMatchScoutingTask",
                                        "Fetch of Match Scouting information failed!\n\n" +
                                                "You may want to consider working offline.\n\n" +
                                                "The error is:\n" + e.getMessage());
                            }
                        }
                    }, cfg.getEvent_id());

                    scoutingTask.execute();

                }

                CyberScouterUsers.deleteUsers(db);
                getUserNamesTask namesTask = new getUserNamesTask(new IOnEventListener<CyberScouterUsers[]>() {
                    @Override
                    public void onSuccess(CyberScouterUsers[] result) {
                        CyberScouterUsers.setUsers(db, result);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        if(null != e) {
                            MessageBox.showMessageBox(MainActivity.this, "Fetch Event Failed Alert", "getEventTask", "Fetch of Current Event information failed!\n\n" +
                                    "You may want to consider working offline.\n\n" + "The error is:\n" + e.getMessage());
                        }

                    }
                });

                namesTask.execute();

            }

        } catch (Exception e_m) {
            e_m.printStackTrace();
            MessageBox.showMessageBox(this, "Fetch Event Failed Alert", "syncData", "Sync with data source failed!\n\n" +
                    "You may want to consider working offline.\n\n" + "The error is:\n" + e_m.getMessage());
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

    private static class getEventTask extends AsyncTask<Void, Void, CyberScouterEvent> {
        private IOnEventListener<CyberScouterEvent> mCallBack;
        private Exception mException;

        getEventTask(IOnEventListener<CyberScouterEvent> mListener) {
            super();
            mCallBack = mListener;
        }

        @Override
        protected CyberScouterEvent doInBackground(Void... arg) {
            Connection conn = null;

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:jtds:sqlserver://"
                        + DbInfo.MSSQLServerAddress + "/" + DbInfo.MSSQLDbName, DbInfo.MSSQLUsername, DbInfo.MSSQLPassword);

                CyberScouterEvent cse = new CyberScouterEvent();
                CyberScouterEvent cse2 = cse.getCurrentEvent(conn);

                if (null != cse2)
                    return (cse2);

            } catch (Exception e) {
                e.printStackTrace();
                mException = e;
            } finally {
                if (null != conn) {
                    try {
                        if (!conn.isClosed())
                            conn.close();
                    } catch (Exception e2) {
                        // do nothing
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(CyberScouterEvent cse) {
            if (null != mCallBack) {
                if (null != mException || null == cse) {
                    mCallBack.onFailure(mException);
                } else {
                    mCallBack.onSuccess(cse);
                }
            }
        }
    }

    private class getUserNamesTask extends AsyncTask<Void, Void, CyberScouterUsers[]> {
        private IOnEventListener<CyberScouterUsers[]> mCallBack;
        private Exception mException;

        getUserNamesTask(IOnEventListener<CyberScouterUsers[]> mListener) {
            super();
            mCallBack = mListener;
        }

        @Override
        protected CyberScouterUsers[] doInBackground(Void... arg) {

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://"
                        + DbInfo.MSSQLServerAddress + "/" + DbInfo.MSSQLDbName, DbInfo.MSSQLUsername, DbInfo.MSSQLPassword);

                CyberScouterUsers[] csua = CyberScouterUsers.getUsers(conn);

                conn.close();

                if(null != csua)
                    return csua;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(CyberScouterUsers[] csua) {
            if (null != mCallBack) {
                if (null != mException || null == csua) {
                    mCallBack.onFailure(mException);
                } else {
                    mCallBack.onSuccess(csua);
                }
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

    private static class getMatchScoutingTask extends AsyncTask<Void, Void, CyberScouterMatchScouting[]> {
        private IOnEventListener<CyberScouterMatchScouting[]> mCallBack;
        private Exception mException;
        private CyberScouterMatchScouting[] l_matches;
        private int currentEventId;

        getMatchScoutingTask(IOnEventListener<CyberScouterMatchScouting[]> mListener, int l_currentEventId) {
            super();
            mCallBack = mListener;
            currentEventId = l_currentEventId;
        }

        @Override
        protected CyberScouterMatchScouting[] doInBackground(Void... arg) {

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://"
                        + DbInfo.MSSQLServerAddress + "/" + DbInfo.MSSQLDbName, DbInfo.MSSQLUsername, DbInfo.MSSQLPassword);

                l_matches = CyberScouterMatchScouting.getMatches(conn, currentEventId);

                conn.close();

            } catch (Exception e) {
                mException = e;
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(CyberScouterMatchScouting[] cse) {
            if (null != mCallBack) {
                if (null != mException || null == l_matches) {
                    mCallBack.onFailure(mException);
                } else {
                    mCallBack.onSuccess(l_matches);
                }
            }
        }

    }

}
