package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EndPage extends AppCompatActivity {
    private Button button;
    private int defaultButtonTextColor = Color.LTGRAY;
    private final int SELECTED_BUTTON_TEXT_COLOR = Color.GREEN;
    private final int[] defenseButtons = {R.id.button_DefenseNone, R.id.button_DefenseWeak, R.id.button_DefenseStrong};
    private final int[] levelButtons = {R.id.button_LevelNo, R.id.button_LevelYes};
    private final int[] movedOnBarButtons = {R.id.button_MovedOnBarNo, R.id.button_MovedOnBarYes};
    private final int[] playedDefenseButtons = {R.id.button_PlayedDefenseNo, R.id.button_PlayedDefenseYes};
    private final int[] climbStatusButtons = {R.id.button_noAttemptClimb,R.id.button_Traversal, R.id.button_High, R.id.button_Middle, R.id.button_Low};

    String[] _lColumns = {CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM,
            CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMBROKEDOWN,
            CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMSUBSYSTEMBROKE,
            CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMGROUNDPICKUP,
            CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMHOPPERLOAD,
            CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMDEFPLAYEDAGAINST,
            CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBLEVELSTATUS,
            CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBMOVEONBAR,
            CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMPLAYEDDEFENSE,
            CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSTATUS
    };

    private int lostComm, brokeDown, subSystemBroke, groudPickup, hopperLoad, defense, level, movedOnBar, playedDefense, climbStatus = 0;

    private int currentCommStatusColor;

    private CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
    private SQLiteDatabase _db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_page);

        Intent intent = getIntent();
//        currentCommStatusColor = intent.getIntExtra("commstatuscolor", Color.LTGRAY);
//        updateStatusIndicator(currentCommStatusColor);


        button = findViewById(R.id.button_Previous);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToTelePage();

            }
        });

        button = findViewById(R.id.button_Next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPage();

            }
        });

        button = findViewById(R.id.button_DefenseNone);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defenseNone();

            }
        });
        button = findViewById(R.id.button_DefenseWeak);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defenseWeak();

            }
        });
        button = findViewById(R.id.button_DefenseStrong);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defenseStrong();

            }
        });
        button = findViewById(R.id.button_LevelNo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levelNo();

            }
        });
        button = findViewById(R.id.button_LevelYes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levelYes();

            }
        });
        button = findViewById(R.id.button_MovedOnBarNo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movedOnBarNo();

            }
        });
        button = findViewById(R.id.button_MovedOnBarYes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movedOnBarYes();

            }
        });
        button = findViewById(R.id.button_PlayedDefenseNo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playedDefenseNo();

            }
        });
        button = findViewById(R.id.button_PlayedDefenseYes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { playedDefenseYes();

            }
        });
        button = findViewById(R.id.button_noAttemptClimb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noAttemptClimb();
            }
        });
        button = findViewById(R.id.button_Traversal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                climbed();

            }
        });
        button = findViewById(R.id.button_High);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptedClimb();

            }
        });
        button = findViewById(R.id.button_Middle);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                busyClimb();

            }
        });
        button = findViewById(R.id.button_Low);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parkedClimb();

            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();

        _db = mDbHelper.getWritableDatabase();
        CyberScouterConfig cfg = CyberScouterConfig.getConfig(_db);
        CyberScouterMatchScouting csm = CyberScouterMatchScouting.getCurrentMatch(_db, TeamMap.getNumberForTeam(cfg.getAlliance_station()));

        if (null != csm) {
            TextView tv = findViewById(R.id.textView_endgameMatch);
            tv.setText(getString(R.string.tagMatch, csm.getMatchNo()));
            tv = findViewById(R.id.textView_endgameTeam);
            tv.setText(getString(R.string.tagTeam, csm.getTeam()));

            FakeRadioGroup.buttonDisplay(this, csm.getSummDefPlayedAgainst(), defenseButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
            defense = csm.getSummDefPlayedAgainst();
            FakeRadioGroup.buttonDisplay(this, csm.getClimbLevelStatus(), levelButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
            level = csm.getClimbLevelStatus();
            FakeRadioGroup.buttonDisplay(this, csm.getClimbMoveOnBar(), movedOnBarButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
            movedOnBar = csm.getClimbMoveOnBar();
            FakeRadioGroup.buttonDisplay(this, csm.getSummPlayedDefense(), playedDefenseButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
            playedDefense = csm.getSummPlayedDefense();
            FakeRadioGroup.buttonDisplay(this, csm.getClimbStatus() - 1, climbStatusButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
            climbStatus = csm.getClimbStatus();
        }
    }

    public void returnToTelePage(){
        updateEndPageData();
        this.finish();
    }

    public void submitPage(){
        updateEndPageData();
        Intent intent = new Intent(this, SubmitPage.class);
//        intent.putExtra("commstatuscolor", currentCommStatusColor);
        startActivity(intent);

    }

    public void defenseNone() {
        FakeRadioGroup.buttonPressed(this, 0, defenseButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMDEFPLAYEDAGAINST, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
        defense = 0;
    }
    public void defenseWeak() {
        FakeRadioGroup.buttonPressed(this, 1, defenseButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMDEFPLAYEDAGAINST, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
        defense = 1;
    }
    public void defenseStrong() {
        FakeRadioGroup.buttonPressed(this, 2, defenseButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMDEFPLAYEDAGAINST, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
        defense = 2;
    }
    public void levelNo() {
        FakeRadioGroup.buttonPressed(this, 0, levelButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBLEVELSTATUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
        level = 0;
    }
    public void levelYes() {
        FakeRadioGroup.buttonPressed(this, 1, levelButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBLEVELSTATUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
        level = 1;
    }
    public void movedOnBarNo() {
        FakeRadioGroup.buttonPressed(this, 0, movedOnBarButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBMOVEONBAR, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
        movedOnBar = 0;
    }
    public void movedOnBarYes() {
        FakeRadioGroup.buttonPressed(this, 1, movedOnBarButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBMOVEONBAR, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
        movedOnBar = 1;
    }
    public void playedDefenseNo() {
        FakeRadioGroup.buttonPressed(this, 0, playedDefenseButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMPLAYEDDEFENSE, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
        playedDefense = 0;
    }
    public void playedDefenseYes() {
        FakeRadioGroup.buttonPressed(this, 1, playedDefenseButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMPLAYEDDEFENSE, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
        playedDefense = 1;
    }
    public void noAttemptClimb() {
        FakeRadioGroup.buttonPressed(this, 0, climbStatusButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSTATUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
        climbStatus = 1;
    }
    public void climbed() {
        FakeRadioGroup.buttonPressed(this, 1, climbStatusButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSTATUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
        climbStatus = 2;
    }
    public void attemptedClimb() {
        FakeRadioGroup.buttonPressed(this, 2, climbStatusButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSTATUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
        climbStatus = 3;
    }
    public void busyClimb() {
        FakeRadioGroup.buttonPressed(this, 3, climbStatusButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSTATUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
        climbStatus = 4;
    }
    public void parkedClimb() {
        FakeRadioGroup.buttonPressed(this, 4, climbStatusButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSTATUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
        climbStatus = 5;
    }

    private void updateStatusIndicator(int color) {
        ImageView iv = findViewById(R.id.imageView_btIndicator);
        BluetoothComm.updateStatusIndicator(iv, color);
    }

    private void updateEndPageData() {
        CyberScouterConfig cfg = CyberScouterConfig.getConfig(_db);
        try {
            Integer[] _lValues = {lostComm, brokeDown, subSystemBroke, groudPickup, hopperLoad, defense, level, movedOnBar, playedDefense, climbStatus};
            CyberScouterMatchScouting.updateMatchMetric(_db, _lColumns, _lValues, cfg);
        } catch(Exception e) {
            e.printStackTrace();
            MessageBox.showMessageBox(this, "Update Error",
                    "EndPage.updateEndPageData", "SQLite update failed!\n "+e.getMessage());
        }
    }
}