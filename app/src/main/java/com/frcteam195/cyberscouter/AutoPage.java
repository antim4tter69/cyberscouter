package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AutoPage extends AppCompatActivity {
    private Button button;
    private int defaultButtonTextColor;
    private final int SELECTED_BUTTON_TEXT_COLOR = Color.GREEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_page);

        button = findViewById(R.id.button_startMatch);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                StartMatch();
            }
        });

        button = findViewById(R.id.button_previous);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ReturnToScoutingPage();
            }
        });
        defaultButtonTextColor = button.getCurrentTextColor();

        button = findViewById(R.id.button_grndLeft);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                groundFar();
            }
        });

        button = findViewById(R.id.button_l1Left);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                levelOneLeft();
            }
        });

        button = findViewById(R.id.button_l1Center);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                levelOneCenter();
            }
        });

        button = findViewById(R.id.button_l1Right);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                levelOneRight();
            }
        });

        button = findViewById(R.id.button19);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                levelTwoLeft();
            }
        });

        button = findViewById(R.id.button_l2Right);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                levelTwoRight();
            }
        });

        button = findViewById(R.id.button_grndRight);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                groundNear();
            }
        });

        button = findViewById(R.id.button_skipMatch);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                skipMatch();
            }
        });

        button = findViewById(R.id.button_moveBonusYes);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                moveBonusYes();
            }
        });

        button = findViewById(R.id.button_moveBonusNo);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                moveBonusNo();
            }
        });

        button = findViewById(R.id.button_cargo);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                preloadCargo();
            }
        });

        button = findViewById(R.id.button_panel);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                preloadPanel();
            }
        });

        button = findViewById(R.id.button_none);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                preloadNone();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        CyberScouterMatchScouting csm = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));

        if (null != csm) {
            TextView tv = findViewById(R.id.textView7);
            tv.setText(getString(R.string.tagMatch, csm.getTeamMatchNo()));
            tv = findViewById(R.id.textView9);
            tv.setText(getString(R.string.tagTeam, csm.getTeam()));

            int[] buttons = {R.id.button_moveBonusNo, R.id.button_moveBonusYes};
            fakeRadioButtonDisplay(csm.getAutoMoveBonus(), buttons);

            int[] buttons2 = {R.id.button_none, R.id.button_panel, R.id.button_cargo};
            fakeRadioButtonDisplay(csm.getAutoPreload(), buttons2);

            int[] buttons3 = {R.id.button_grndLeft, R.id.button_l1Left, R.id.button_l1Center, R.id.button_l1Right, R.id.button19, R.id.button_l2Right};
            fakeRadioButtonDisplay(csm.getAutoStartPos(), buttons3);
        }
    }

    void fakeRadioButtonDisplay(int val, int[] bs) {

        for(int i = 0 ; i<bs.length ; ++i){
            button = findViewById(bs[i]);
            if(-1 != val && i == val) {
                button.setTextColor(SELECTED_BUTTON_TEXT_COLOR);
            } else {
                button.setTextColor(defaultButtonTextColor);
            }
        }
    }

    void fakeRadioButtonPressed(int val, int[] bs, String col){
        for(int i = 0 ; i<bs.length ; ++i){
            button = findViewById(bs[i]);
            if(-1 != val && i == val) {
                if(SELECTED_BUTTON_TEXT_COLOR == button.getCurrentTextColor()) {
                    button.setTextColor(defaultButtonTextColor);
                    updateMetric(col, -1);
                } else {
                    button.setTextColor(SELECTED_BUTTON_TEXT_COLOR);
                    updateMetric(col, val);
                }
            } else {
                button.setTextColor(defaultButtonTextColor);
            }
        }
    }

    void updateMetric(String col, int val) {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        CyberScouterMatchScouting csm = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));

        if (null != csm) {
            try {
                String[] cols = {col};
                Integer[] vals = {val};
                CyberScouterMatchScouting.updateMatchMetric(db, cols, vals, cfg);
            } catch(Exception e) {
                MessageBox.showMessageBox(this, "Update Metric Failed Alert", "updateMoveBonus",
                        "Update of " + col + " failed!\n\n" +
                                "The error is:\n" + e.getMessage());
            }
        }
    }


    public void StartMatch(){

            Intent intent = new Intent(this, TelePage.class);
            startActivity(intent);
    }


    public void ReturnToScoutingPage(){
        this.finish();
    }

    public void groundFar(){}
    public void groundNear(){}

    public void levelOneLeft(){
        int[] buttons = {R.id.button_grndLeft, R.id.button_l1Left, R.id.button_l1Center, R.id.button_l1Right, R.id.button19, R.id.button_l2Right};
        fakeRadioButtonPressed(1, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS);
    }
    public void levelOneCenter(){
        int[] buttons = {R.id.button_grndLeft, R.id.button_l1Left, R.id.button_l1Center, R.id.button_l1Right, R.id.button19, R.id.button_l2Right};
        fakeRadioButtonPressed(2, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS);
    }
    public void levelOneRight(){
        int[] buttons = {R.id.button_grndLeft, R.id.button_l1Left, R.id.button_l1Center, R.id.button_l1Right, R.id.button19, R.id.button_l2Right};
        fakeRadioButtonPressed(3, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS);
    }
    public void levelTwoLeft(){
        int[] buttons = {R.id.button_grndLeft, R.id.button_l1Left, R.id.button_l1Center, R.id.button_l1Right, R.id.button19, R.id.button_l2Right};
        fakeRadioButtonPressed(4, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS);
    }
    public void levelTwoRight(){
        int[] buttons = {R.id.button_grndLeft, R.id.button_l1Left, R.id.button_l1Center, R.id.button_l1Right, R.id.button19, R.id.button_l2Right};
        fakeRadioButtonPressed(5, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS);
    }

    public void skipMatch(){
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        CyberScouterMatchScouting csm = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));

        if (null != csm) {
            try {
                CyberScouterMatchScouting.skipMatch(db, csm.getMatchScoutingID());
                this.onResume();
            } catch(Exception e) {
                MessageBox.showMessageBox(this, "Skip Match Failed Alert", "skipMatch",
                        "Update of UploadStatus failed!\n\n" +
                                "The error is:\n" + e.getMessage());
            }
        }
    }


    public void moveBonusYes(){
        int[] buttons = {R.id.button_moveBonusNo, R.id.button_moveBonusYes};
        fakeRadioButtonPressed(1, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS);
    }
    public void moveBonusNo(){
        int[] buttons = {R.id.button_moveBonusNo, R.id.button_moveBonusYes};
        fakeRadioButtonPressed(0, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS);
    }


    public void preloadCargo(){
        int[] buttons = {R.id.button_none, R.id.button_panel, R.id.button_cargo};
        fakeRadioButtonPressed(2, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOPRELOAD);
    }
    public void preloadPanel(){
        int[] buttons = {R.id.button_none, R.id.button_panel, R.id.button_cargo};
        fakeRadioButtonPressed(1, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOPRELOAD);
    }
    public void preloadNone() {
        int[] buttons = {R.id.button_none, R.id.button_panel, R.id.button_cargo};
        fakeRadioButtonPressed(0, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOPRELOAD);
    }

}
