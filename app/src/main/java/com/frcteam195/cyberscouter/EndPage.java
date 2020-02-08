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
    private final int[] grndPickupButtons = {R.id.button_GroundPickupYes, R.id.button_GroundPickupNo};

    

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

        button = findViewById(R.id.button_LostCommYes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lostCommYes();

            }
        });
        button = findViewById(R.id.button_LostCommNo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lostCommNo();

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

            FakeRadioGroup.buttonDisplay(this, csm.getSummHatchGrdPickup(), grndPickupButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
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

}