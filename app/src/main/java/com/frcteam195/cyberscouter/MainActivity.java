package com.frcteam195.cyberscouter;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.sql.Connection;
import java.sql.DriverManager;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private Button button;
    static private Connection conn = null;
    static private String g_event = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            if (conn == null) {

                (new DBConnectionTask()).execute(null, null, null);

            }
            while (g_event == null)
                sleep(10);
        } catch(Exception e) {
            e.printStackTrace();
        }


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

            TextView tv = null;
            if( null != cfg) {
                /* Enable the scouting button */
                button = (Button) findViewById(R.id.button2);
                button.setEnabled(true);

                /* Read the config values from SQLite */
                tv = findViewById(R.id.textView41);
                String tmp = cfg.getRole();
                if(tmp.startsWith("Blu"))
                    tv.setTextColor(Color.BLUE);
                else if(tmp.startsWith("Red"))
                    tv.setTextColor(Color.RED);
                else
                    tv.setTextColor(Color.BLACK);
                tv.setText(cfg.getRole());

                tv = findViewById(R.id.textView4);
                tv.setText(cfg.getEvent());

                /* Make the offline toggle button reflect the last setting */
                ToggleButton tb = findViewById(R.id.SwitchButton);
                tb.setChecked(cfg.isOffline());

            } else {
                String tmp = null;
                button = (Button) findViewById(R.id.button2);
                button.setEnabled(false);
                ContentValues values = new ContentValues();
                tmp = "Unknown Role";
                tv = findViewById(R.id.textView41);
                tv.setText(tmp);
                values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_ROLE, tmp);
                if(null != g_event)
                    tmp = g_event;
                else
                    tmp = "Unknown Event";
                tv = findViewById(R.id.textView4);
                tv.setText(tmp);
                values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT, tmp);
                values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_TABLET_NUM, 0);
                ToggleButton tb = findViewById(R.id.SwitchButton);
                tb.setChecked(true);
                values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_OFFLINE, 1);
                values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_FIELD_REDLEFT, 1);

// Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(CyberScouterContract.ConfigEntry.TABLE_NAME, null, values);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw(e);
        }
    }

    public void openAdmin1(){
    Intent intent = new Intent(this, Admin1.class);
    startActivity(intent);
    }

    public void openScouting(){
        Intent intent = new Intent(this, ScoutingPage.class);
        startActivity(intent);
    }

    public void syncPictures(){
    }

    public void syncData(){
    }

    public void setOffline(ToggleButton tb) {
        try {
            int chkd = 0;
            if (tb.isChecked())
                chkd = 1;

            CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_OFFLINE, chkd);
            int count = db.update(
                    CyberScouterContract.ConfigEntry.TABLE_NAME,
                    values,
                    null,
                    null);
        } catch(Exception e) {
            e.printStackTrace();
            throw(e);
        }
    }

    private class DBConnectionTask extends AsyncTask<Void, Void, Void> {
        final private static String serverAddress = "frcteam195test.cmdlvflptajw.us-east-1.rds.amazonaws.com";
        final private static String dbName = "CyberScouter";
        final private static String username = "admin";
        final private static String password = "Einstein195";

        @Override
        protected Void doInBackground(Void... arg) {

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:jtds:sqlserver://"
                        + serverAddress + "/" + dbName, username, password);

                CyberScouterEvent cse = new CyberScouterEvent();
                CyberScouterEvent cse2 = cse.getCurrentEvent(conn);

                if(null != cse2) {
                    g_event = cse2.getEventName();
                    setEvent(g_event);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    private void setEvent(String val) {
        try {
            CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_EVENT, val);
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
}
