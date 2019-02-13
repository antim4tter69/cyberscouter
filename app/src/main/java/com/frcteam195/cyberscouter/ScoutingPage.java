package com.frcteam195.cyberscouter;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;

import static java.lang.Thread.sleep;

public class ScoutingPage extends AppCompatActivity implements NamePickerDialog.NamePickerDialogListener {
    private Button button;
    private ImageButton imageButton;

    private CyberScouterMatches[] g_matches  = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting_page);

        button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAuto();

            }
        });

        button = (Button) findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNamePickerPage();

            }
        });
        button = (Button) findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMainMenu();

            }
        });

        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setFieldRedLeft(1);
                setFieldImage(R.drawable.red_left);
            }
        });

        imageButton = (ImageButton) findViewById(R.id.imageButton2);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setFieldRedLeft(0);
                setFieldImage(R.drawable.red_right);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        if(null != cfg && null != cfg.getUsername()) {
            TextView tv = (TextView) findViewById(R.id.textView9);
            tv.setText(cfg.getUsername());
        }

        try {

            if (null != cfg && !cfg.isOffline()) {
                (new getMatchsTask()).execute(null, null, null);
                while (null == g_matches) {
                    sleep(10);
                }
                for(int i=0 ; i<g_matches.length ; ++i) {
                    if(!g_matches[i].isMatchEnded()) {

                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        if (cfg.isFieldRedLeft())
            setFieldImage(R.drawable.red_left);
        else
            setFieldImage(R.drawable.red_right);
    }

    @Override
    public void onNameSelected(String val) {
        setUsername(val);
        TextView tv = (TextView) findViewById(R.id.textView9);
        tv.setText(val);
    }


    public void openAuto(){
        Intent intent = new Intent(this, AutoPage.class);
        startActivity(intent);


    }
    public void openNamePickerPage(){
//        Intent intent = new Intent(this, NamePickerPageActivity.class);
//        startActivity(intent);

        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        if(!cfg.isOffline()) {
            FragmentManager fm = getSupportFragmentManager();
            NamePickerDialog npd = new NamePickerDialog();
            npd.show(fm, "namepicker");
        }
    }

    public void returnToMainMenu(){
        this.finish();
    }

    public void setUsername(String val) {
        try {
            CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_USERNAME, val);
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

    private class getMatchsTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg) {

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://"
                        + DbInfo.MSSQLServerAddress + "/" + DbInfo.MSSQLDbName, DbInfo.MSSQLUsername, DbInfo.MSSQLPassword);

                CyberScouterMatches csm = new CyberScouterMatches();
                g_matches = csm.getMatches(conn, 7);

                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    private void setFieldRedLeft(int val) {
        try {
            CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_FIELD_REDLEFT, val);
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

    private void setFieldImage(int img) {
        ImageView imageView = findViewById(R.id.imageView2);
        imageView.setImageResource(img);
    }

}
