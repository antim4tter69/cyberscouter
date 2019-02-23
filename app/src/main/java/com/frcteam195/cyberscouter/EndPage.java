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

            int[] buttons = {R.id.button_lostCommNo, R.id.button_lostCommYes};
            fakeRadioButtonDisplay(csm.getSummLostComm(), buttons);

            int[] buttons2 = {R.id.button68, R.id.button69};
            fakeRadioButtonDisplay(csm.getSummBroke(), buttons2);

            int[] buttons3 = {R.id.button70, R.id.button71};
            fakeRadioButtonDisplay(csm.getSummTipOver(), buttons3);

            int[] buttons4 = {R.id.button72, R.id.button73};
            fakeRadioButtonDisplay(csm.getSummTipOver(), buttons4);

            int[] buttons5 = {R.id.button_noClimb, R.id.button7, R.id.button7, R.id.button_climbLvl1, R.id.button7,
                    R.id.button7, R.id.button_climbLvl2, R.id.button7, R.id.button7, R.id.button7, R.id.button7,
                    R.id.button7, R.id.button_climbLvl3};
            fakeRadioButtonDisplay(csm.getClimbScore(), buttons5);

            int[] buttons6 = {R.id.button_noHelp, R.id.button_helped, R.id.button_wasHelped};
            fakeRadioButtonDisplay(csm.getClimbAssist(), buttons6);
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

    public void returnToTelePage(){
        this.finish();
    }

    public void summaryQuestionsPage(){
        Intent intent = new Intent(this, SummaryQuestionsPage.class);
        startActivity(intent);

    }

    public void lostCommNo() {
        int[] buttons = {R.id.button_lostCommNo, R.id.button_lostCommYes};
        fakeRadioButtonPressed(0, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM);
    }
    public void lostCommYes() {
        int[] buttons = {R.id.button_lostCommNo, R.id.button_lostCommYes};
        fakeRadioButtonPressed(1, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMLOSTCOMM);
    }

    public void brokeDownNo() {
        int[] buttons = {R.id.button68, R.id.button69};
        fakeRadioButtonPressed(0, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMBROKE);
    }
    public void brokeDownYes() {
        int[] buttons = {R.id.button68, R.id.button69};
        fakeRadioButtonPressed(1, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMBROKE);
    }

    public void tippedOverNo() {
        int[] buttons = {R.id.button70, R.id.button71};
        fakeRadioButtonPressed(0, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMTIPOVER);
    }
    public void tippedOverYes() {
        int[] buttons = {R.id.button70, R.id.button71};
        fakeRadioButtonPressed(1, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMTIPOVER);
    }

    public void hatchGroundPickupNo() {
        int[] buttons = {R.id.button72, R.id.button73};
        fakeRadioButtonPressed(0, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMHATCHGRDPICKUP);
    }
    public void hatchGroundPickupYes() {
        int[] buttons = {R.id.button72, R.id.button73};
        fakeRadioButtonPressed(1, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_SUMMHATCHGRDPICKUP);
    }

    public void noClimb() {
        int[] buttons = {R.id.button_noClimb, R.id.button7, R.id.button7, R.id.button_climbLvl1, R.id.button7,
                R.id.button7, R.id.button_climbLvl2, R.id.button7, R.id.button7, R.id.button7, R.id.button7,
                R.id.button7, R.id.button_climbLvl3};
        fakeRadioButtonPressed(0, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSCORE);
    }
    public void climbLvl1() {
        int[] buttons = {R.id.button_noClimb, R.id.button7, R.id.button7, R.id.button_climbLvl1, R.id.button7,
                R.id.button7, R.id.button_climbLvl2, R.id.button7, R.id.button7, R.id.button7, R.id.button7,
                R.id.button7, R.id.button_climbLvl3};
        fakeRadioButtonPressed(3, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSCORE);
    }
    public void climbLvl2() {
        int[] buttons = {R.id.button_noClimb, R.id.button7, R.id.button7, R.id.button_climbLvl1, R.id.button7,
                R.id.button7, R.id.button_climbLvl2, R.id.button7, R.id.button7, R.id.button7, R.id.button7,
                R.id.button7, R.id.button_climbLvl3};
        fakeRadioButtonPressed(6, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSCORE);
    }
    public void climbLvl3() {
        int[] buttons = {R.id.button_noClimb, R.id.button7, R.id.button7, R.id.button_climbLvl1, R.id.button7,
                R.id.button7, R.id.button_climbLvl2, R.id.button7, R.id.button7, R.id.button7, R.id.button7,
                R.id.button7, R.id.button_climbLvl3};
        fakeRadioButtonPressed(12, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBSCORE);
    }

    public void noHelp() {
        int[] buttons = {R.id.button_noHelp, R.id.button_helped, R.id.button_wasHelped};
        fakeRadioButtonPressed(0, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBASSIST);
    }
    public void helped() {
        int[] buttons = {R.id.button_noHelp, R.id.button_helped, R.id.button_wasHelped};
        fakeRadioButtonPressed(1, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBASSIST);
    }
    public void wasHelped() {
        int[] buttons = {R.id.button_noHelp, R.id.button_helped, R.id.button_wasHelped};
        fakeRadioButtonPressed(2, buttons, CyberScouterContract.MatchScouting.COLUMN_NAME_CLIMBASSIST);
    }

}