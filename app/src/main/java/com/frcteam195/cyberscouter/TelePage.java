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
import android.widget.TextView;

public class TelePage extends AppCompatActivity {
    private Button button;
    private int defaultButtonTextColor;
    private final int SELECTED_BUTTON_TEXT_COLOR = Color.GREEN;
    private int FIELD_ORIENTATION_RIGHT=0;
    private int FIELD_ORIENTATION_LEFT=1;
    private int field_orientation=FIELD_ORIENTATION_RIGHT;
    private Chronometer Stage_2;
    private long pauseOffset;
    private boolean running;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_page);
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        intent.getIntExtra("field_orientation",field_orientation);

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

        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csm = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));

            if (null != csm) {
                TextView tv = findViewById(R.id.textView7);
                tv.setText(getString(R.string.tagMatch, csm.getTeamMatchNo()));
                tv = findViewById(R.id.textView9);
                tv.setText(getString(R.string.tagTeam, csm.getTeam()));

            }
        }


    }

    public void returnToAutoPage() {
        this.finish();
    }

    public void EndGamePage() {
        Intent intent = new Intent(this, EndPage.class);
        startActivity(intent);

    }

    public void cargoshipCargoMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void cargoshipCargoPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void cargoshipPanelMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void cargoshipPanelPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void farPanelHighShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void farPanelHighShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void farPanelMedShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void farPanelMedShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }

    }

    public void farPanelLowShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void farPanelLowShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void nearPanelHighShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void nearPanelHighShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void nearPanelMedShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void nearPanelMedShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void nearPanelLowShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void nearPanelLowShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void cargoLowShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void cargoLowShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void cargoMedShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void cargoMedShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void cargoHighShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
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

        if (null != cfg && null != cfg.getRole()) {
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
}
