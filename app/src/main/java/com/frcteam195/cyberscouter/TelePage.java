package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

public class TelePage extends AppCompatActivity {
    private Button button;
    private Button zone_1L;
    private Button zone_2L;
    private Button zone_3L;
    private Button zone_4L;
    private Button zone_5L;
    private Button zone_1R;
    private Button zone_2R;
    private Button zone_3R;
    private Button zone_4R;
    private Button zone_5R;
    private ImageView imageView8;
    private ImageView imageView9;
    private int defaultButtonTextColor;
    private final int SELECTED_BUTTON_TEXT_COLOR = Color.GREEN;
    private int FIELD_ORIENTATION_RIGHT=0;
    private int FIELD_ORIENTATION_LEFT=1;
    private int field_orientation;
    private Chronometer Stage_2;
    private long pauseOffset;
    private boolean running;
    private int currentCommStatusColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_page);

        Intent intent = getIntent();
        field_orientation = intent.getIntExtra("field_orientation",field_orientation);
        ImageView iv = findViewById(R.id.imageView8);
        if(FIELD_ORIENTATION_RIGHT == field_orientation) {
            iv.setImageResource(R.drawable.field_2020_flipped);
        }
        currentCommStatusColor = intent.getIntExtra("commstatuscolor", Color.LTGRAY);
        updateStatusIndicator(currentCommStatusColor);


        TextView tv = findViewById(R.id.textView_roleTag);
        tv.setText(R.string.teleopTitle);

        button = findViewById(R.id.button_Previous);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToAutoPage();

            }
        });

        button = findViewById(R.id.button_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EndGamePage();

            }
        });

        Stage_2 = findViewById(R.id.Stage_2);
        Stage_2 . setFormat("Time: %s");
        Stage_2 . setBase(SystemClock.elapsedRealtime());

        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        CyberScouterMatchScouting csm = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getAlliance_station()));

        if(csm.getAllianceStationID() < 4) {
            if(field_orientation==FIELD_ORIENTATION_LEFT)
            {
                button = findViewById(R.id.zone_1L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_2L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_3L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_4L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_5L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_1R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_2R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_3R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_4R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_5R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);
            }
            else
            {
                button = findViewById(R.id.zone_1L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_2L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_3L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_4L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_5L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_1R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_2R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_3R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_4R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_5R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);
            }
        } else{
            if(field_orientation==FIELD_ORIENTATION_LEFT)
            {
                button = findViewById(R.id.zone_1L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_2L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_3L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_4L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_5L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_1R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_2R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_3R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_4R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_5R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);
            }
            else
            {
                button = findViewById(R.id.zone_1L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_2L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_3L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_4L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_5L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_1R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_2R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_3R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_4R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_5R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);
            }
        }

    }
    public void startStage_2 (View V){
        if (!running){
            Stage_2.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            Stage_2.start();
            running = true;
        }
    }
    public void pauseStage_2 (View V){
        if (running){
            Stage_2.stop();
            pauseOffset = SystemClock.elapsedRealtime() - Stage_2.getBase();
            running = false;
        }
    }
    public void resetStage_2 (View V){
        Stage_2.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }
    @Override
    protected void onResume() {
        super.onResume();

        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

//        if (null != cfg && null != cfg.getAlliance_station()) {
//            CyberScouterMatchScouting csm = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getAlliance_station()));
//
//            if (null != csm) {
//                TextView tv = findViewById(R.id.textView7);
//                tv.setText(getString(R.string.tagMatch, csm.getTeamMatchNo()));
//                tv = findViewById(R.id.textView9);
//                tv.setText(getString(R.string.tagTeam, csm.getTeam()));
//
//            }
//        }


    }

    public void returnToAutoPage() {
        this.finish();
    }

    public void EndGamePage() {
        Intent intent = new Intent(this, EndPage.class);
        intent.putExtra("commstatuscolor", currentCommStatusColor);
        startActivity(intent);

    }

    public void cargoshipCargoMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getAlliance_station()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getAlliance_station()));
        }
    }

    void setMetricValue(String col, int val) {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        if (0 > val)
            val = 0;

        String[] cols = {col};
        Integer[] vals = {val};

        if (null != cfg && null != cfg.getAlliance_station()) {
            try {
                CyberScouterMatchScouting.updateMatchMetric(db, cols, vals, cfg);
            } catch (Exception e) {
                MessageBox.showMessageBox(this, "Metric Update Failed Alert", "setMetricValue", "An error occurred trying to update metric.\n\nError is:\n" + e.getMessage());
            }
        } else {
            MessageBox.showMessageBox(this, "Configuration Not Found Alert", "setMetricValue", "An error occurred trying to acquire current configuration.  Cannot continue.");
        }
        this.onResume();
    }

    private void updateStatusIndicator(int color) {
        ImageView iv = findViewById(R.id.imageView_btIndicator);
        BluetoothComm.updateStatusIndicator(iv, color);
    }
}
