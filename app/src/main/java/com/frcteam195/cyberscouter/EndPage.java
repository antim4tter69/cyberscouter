package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndPage extends AppCompatActivity {
    private Button button;
    private int defaultButtonTextColor;
    private final int SELECTED_BUTTON_TEXT_COLOR = Color.GREEN;
    private final int[] lostCommButtons = {R.id.button_LostCommNo, R.id.button_LostCommYes};
    private final int[] brokeDownButtons = {R.id.button_BrokeDownNo, R.id.button_BrokeDownYes};
    private final int[] subSystemBrokeButtons = {R.id.button_SubSystemBrokeNo, R.id.button_SubSystemBrokeYes};
    private final int[] groundPickupButtons = {R.id.button_GroundPickupNo, R.id.button_GroundPickupYes};
    private final int[] hopperLoadButtons = {R.id.button_HopperLoadNo, R.id.button_HopperLoadYes};
    private final int[] defenseButtons = {R.id.button_DefenseNone, R.id.button_DefenseWeak, R.id.button_DefenseStrong};
    private final int[] levelButtons = {R.id.button_LevelNo, R.id.button_LevelYes};
    private final int[] movedOnBarButtons = {R.id.button_MovedOnBarNo, R.id.button_MovedOnBarYes};
    private final int[] playedDefenseButtons = {R.id.button_PlayedDefenseNo, R.id.button_PlayedDefenseYes};
    private final int[] climbStatusButtons = {R.id.button_Climbed, R.id.button_AttemptedClimb, R.id.button_BusyClimb, R.id.button_ParkedClimb};

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_page);

        button = findViewById(R.id.button_Previous);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToTelePage();

            }
        });
        defaultButtonTextColor = button.getCurrentTextColor();

        button = findViewById(R.id.button_Next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                summaryQuestionsPage();

            }
        });

        button = findViewById(R.id.button_LostCommNo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lostCommNo();

            }
        });
        button = findViewById(R.id.button_LostCommYes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lostCommYes();

            }
        });
        button = findViewById(R.id.button_BrokeDownNo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brokeDownNo();

            }
        });
        button = findViewById(R.id.button_BrokeDownYes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brokeDownYes();

            }
        });
        button = findViewById(R.id.button_SubSystemBrokeNo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subSystemBrokeNo();

            }
        });
        button = findViewById(R.id.button_SubSystemBrokeYes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subSystemBrokeYes();

            }
        });
        button = findViewById(R.id.button_GroundPickupNo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groundPickupNo();

            }
        });
        button = findViewById(R.id.button_GroundPickupYes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groundPickupYes();

            }
        });
        button = findViewById(R.id.button_HopperLoadNo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hopperLoadNo();

            }
        });
        button = findViewById(R.id.button_HopperLoadYes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hopperLoadYes();

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
        button = findViewById(R.id.button_Climbed);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                climbed();

            }
        });
        button = findViewById(R.id.button_AttemptedClimb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptedClimb();

            }
        });
        button = findViewById(R.id.button_BusyClimb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                busyClimb();

            }
        });
        button = findViewById(R.id.button_ParkedClimb);
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

        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

//        CyberScouterMatchScouting csm = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getAlliance_station()));
//
//        if (null != csm) {
//            TextView tv = findViewById(R.id.textView7);
//            tv.setText(getString(R.string.tagMatch, csm.getTeamMatchNo()));
//            tv = findViewById(R.id.textView9);
//            tv.setText(getString(R.string.tagTeam, csm.getTeam()));
//
//            FakeRadioGroup.buttonDisplay(this, csm.getSummLostComm(), lostCommButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
//
//            FakeRadioGroup.buttonDisplay(this, csm.getSummHatchGrdPickup(), groundPickupButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
//        }
    }

    public void returnToTelePage(){
        this.finish();
    }

    public void summaryQuestionsPage(){
        Intent intent = new Intent(this, SubmitPage.class);
        startActivity(intent);

    }

    public void lostCommNo() {
        FakeRadioGroup.buttonPressed(this, 0, lostCommButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void lostCommYes() {
        FakeRadioGroup.buttonPressed(this, 1, lostCommButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void brokeDownNo() {
        FakeRadioGroup.buttonPressed(this, 0, brokeDownButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_BROKEDOWN, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void brokeDownYes() {
        FakeRadioGroup.buttonPressed(this, 1, brokeDownButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_BROKEDOWN, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void subSystemBrokeNo() {
        FakeRadioGroup.buttonPressed(this, 0, subSystemBrokeButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUBSYSTEMBROKEDOWN, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void subSystemBrokeYes() {
        FakeRadioGroup.buttonPressed(this, 1, subSystemBrokeButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUBSYSTEMBROKEDOWN, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void groundPickupNo() {
        FakeRadioGroup.buttonPressed(this, 0, groundPickupButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_GROUNDPICKUP, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void groundPickupYes() {
        FakeRadioGroup.buttonPressed(this, 1, groundPickupButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_GROUNDPICKUP, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void hopperLoadNo() {
        FakeRadioGroup.buttonPressed(this, 0, hopperLoadButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_HOPPERLOAD, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void hopperLoadYes() {
        FakeRadioGroup.buttonPressed(this, 1, hopperLoadButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_HOPPERLOAD, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void defenseNone() {
        FakeRadioGroup.buttonPressed(this, 0, defenseButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_DEFENSEPLAYEDAGAINST, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void defenseWeak() {
        FakeRadioGroup.buttonPressed(this, 1, defenseButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_DEFENSEPLAYEDAGAINST, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void defenseStrong() {
        FakeRadioGroup.buttonPressed(this, 2, defenseButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_DEFENSEPLAYEDAGAINST, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void levelNo() {
        FakeRadioGroup.buttonPressed(this, 0, levelButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_LEVEL, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void levelYes() {
        FakeRadioGroup.buttonPressed(this, 1, levelButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_LEVEL, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void movedOnBarNo() {
        FakeRadioGroup.buttonPressed(this, 0, movedOnBarButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_MOVEDONBAR, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void movedOnBarYes() {
        FakeRadioGroup.buttonPressed(this, 1, movedOnBarButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_MOVEDONBAR, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void playedDefenseNo() {
        FakeRadioGroup.buttonPressed(this, 0, playedDefenseButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_PLAYEDDEFENSE, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void playedDefenseYes() {
        FakeRadioGroup.buttonPressed(this, 1, playedDefenseButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_PLAYEDDEFENSE, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void climbed() {
        FakeRadioGroup.buttonPressed(this, 0, climbStatusButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSTATUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void attemptedClimb() {
        FakeRadioGroup.buttonPressed(this, 1, climbStatusButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSTATUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void busyClimb() {
        FakeRadioGroup.buttonPressed(this, 2, climbStatusButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSTATUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void parkedClimb() {
        FakeRadioGroup.buttonPressed(this, 3, climbStatusButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSTATUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }



}