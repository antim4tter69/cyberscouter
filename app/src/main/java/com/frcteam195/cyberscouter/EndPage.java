package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndPage extends AppCompatActivity {
    private Button button;
    private int defaultButtonTextColor;
    private final int SELECTED_BUTTON_TEXT_COLOR = Color.GREEN;
    private final int[] lostCommButtons = {R.id.button_lostCommNo, R.id.button_lostCommYes};
    private final int[] brokeButtons = {R.id.button68, R.id.button69};
    private final int[] tipoverButtons = {R.id.button70, R.id.button71};
    private final int[] grndPickupButtons = {R.id.button72, R.id.button73};
    private final int[] climbScoreButtons = {R.id.button_noClimb, R.id.button7, R.id.button7, R.id.button_climbLvl1, R.id.button7,
            R.id.button7, R.id.button_climbLvl2, R.id.button7, R.id.button7, R.id.button7, R.id.button7,
            R.id.button7, R.id.button_climbLvl3};
    private final int[] climbAssistBbuttons = {R.id.button_noHelp, R.id.button_helped, R.id.button_wasHelped};
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_page);

        button = findViewById(R.id.button_previous);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToTelePage();

            }
        });
        defaultButtonTextColor = button.getCurrentTextColor();

        button = findViewById(R.id.button67);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                summaryQuestionsPage();

            }
        });

        button = findViewById(R.id.button_lostCommYes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lostCommYes();

            }
        });
        button = findViewById(R.id.button_lostCommNo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lostCommNo();

            }
        });

        button = findViewById(R.id.button69);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brokeDownYes();

            }
        });
        button = findViewById(R.id.button68);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brokeDownNo();

            }
        });

        button = findViewById(R.id.button71);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tippedOverYes();

            }
        });
        button = findViewById(R.id.button70);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tippedOverNo();

            }
        });

        button = findViewById(R.id.button73);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hatchGroundPickupYes();

            }
        });
        button = findViewById(R.id.button72);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hatchGroundPickupNo();

            }
        });

        button = findViewById(R.id.button_climbLvl1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                climbLvl1();

            }
        });
        button = findViewById(R.id.button_climbLvl2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                climbLvl2();

            }
        });
        button = findViewById(R.id.button_climbLvl3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                climbLvl3();

            }
        });
        button = findViewById(R.id.button_noClimb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noClimb();

            }
        });

        button = findViewById(R.id.button_noHelp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noHelp();

            }
        });
        button = findViewById(R.id.button_helped);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helped();

            }
        });
        button = findViewById(R.id.button_wasHelped);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wasHelped();

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

            FakeRadioGroup.buttonDisplay(this, csm.getSummLostComm(), lostCommButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);

            FakeRadioGroup.buttonDisplay(this, csm.getSummBroke(), brokeButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);

            FakeRadioGroup.buttonDisplay(this, csm.getSummTipOver(), tipoverButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);

            FakeRadioGroup.buttonDisplay(this, csm.getSummHatchGrdPickup(), grndPickupButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);

            FakeRadioGroup.buttonDisplay(this, csm.getClimbScore(), climbScoreButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);

            FakeRadioGroup.buttonDisplay(this, csm.getClimbAssist(), climbAssistBbuttons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
        }
    }

    public void returnToTelePage(){
        this.finish();
    }

    public void summaryQuestionsPage(){
        Intent intent = new Intent(this, SummaryQuestionsPage.class);
        startActivity(intent);

    }

    public void lostCommNo() {
        FakeRadioGroup.buttonPressed(this, 0, lostCommButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void lostCommYes() {
        FakeRadioGroup.buttonPressed(this, 1, lostCommButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }

    public void brokeDownNo() {
        FakeRadioGroup.buttonPressed(this, 0, brokeButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMBROKE, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void brokeDownYes() {
        FakeRadioGroup.buttonPressed(this, 1, brokeButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMBROKE, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }

    public void tippedOverNo() {
        FakeRadioGroup.buttonPressed(this, 0, tipoverButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMTIPOVER, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void tippedOverYes() {
        FakeRadioGroup.buttonPressed(this, 1, tipoverButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMTIPOVER, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }

    public void hatchGroundPickupNo() {
        FakeRadioGroup.buttonPressed(this, 0, grndPickupButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMHATCHGRDPICKUP, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void hatchGroundPickupYes() {
        FakeRadioGroup.buttonPressed(this, 1, grndPickupButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMHATCHGRDPICKUP, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }

    public void noClimb() {
        FakeRadioGroup.buttonPressed(this,0, climbScoreButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSCORE, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void climbLvl1() {
        FakeRadioGroup.buttonPressed(this, 3, climbScoreButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSCORE, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void climbLvl2() {
        FakeRadioGroup.buttonPressed(this, 6, climbScoreButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSCORE, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void climbLvl3() {
        FakeRadioGroup.buttonPressed(this, 12, climbScoreButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSCORE, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }

    public void noHelp() {
        FakeRadioGroup.buttonPressed(this, 0, climbAssistBbuttons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBASSIST, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void helped() {
        FakeRadioGroup.buttonPressed(this, 1, climbAssistBbuttons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBASSIST, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void wasHelped() {
        FakeRadioGroup.buttonPressed(this, 2, climbAssistBbuttons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBASSIST, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }

}