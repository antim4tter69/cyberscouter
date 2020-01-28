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
    private final int[] moveBonusButtons = {R.id.button_moveBonusNo, R.id.button_moveBonusYes};
    private final int[] preloadButtons = {R.id.button_none, R.id.button_panel, R.id.button_cargo};
    private final int[] startingPosButtons = {R.id.button_grndLeft, R.id.button_l1Left, R.id.button_l1Center, R.id.button_l1Right, R.id.button19, R.id.button_l2Right};
    private final int[] didnotshowButtons = {R.id.button_didnotshowYes, R.id.button_didnotshowNo};



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

        button = findViewById(R.id.button_didnotshowYes);

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

        button = findViewById(R.id.button_didnotshowYes);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                didnotshowYes();
            }
        });

        button = findViewById(R.id.button_didnotshowNo);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                didnotshowNo();
            }
        });

        didnotshowNo();

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

            FakeRadioGroup.buttonDisplay(this, csm.getAutoMoveBonus(), moveBonusButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);

            FakeRadioGroup.buttonDisplay(this, csm.getAutoStartPos(), startingPosButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
        }
    }

    public void StartMatch(){

            Intent intent = new Intent(this, SandstormPage.class);
            startActivity(intent);
    }


    public void ReturnToScoutingPage(){
        this.finish();
    }

    public void groundFar(){}
    public void groundNear(){}

    public void levelOneLeft(){
        FakeRadioGroup.buttonPressed(this,1, startingPosButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void levelOneCenter(){
        FakeRadioGroup.buttonPressed(this,2, startingPosButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void levelOneRight(){
        FakeRadioGroup.buttonPressed(this, 3, startingPosButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void levelTwoLeft(){
        FakeRadioGroup.buttonPressed(this,4, startingPosButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void levelTwoRight(){
        FakeRadioGroup.buttonPressed(this,5, startingPosButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
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
        FakeRadioGroup.buttonPressed(this, 1, moveBonusButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void moveBonusNo(){
        FakeRadioGroup.buttonPressed(this, 0, moveBonusButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }


    public void didnotshowYes(){
        FakeRadioGroup.buttonPressed(this, 0, didnotshowButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTODIDNOTSHOW, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
        Intent intent = new Intent(this, SubmitPage.class);
        startActivity(intent);
    }
    public void didnotshowNo(){
        FakeRadioGroup.buttonPressed(this, 1, didnotshowButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTODIDNOTSHOW, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
}
