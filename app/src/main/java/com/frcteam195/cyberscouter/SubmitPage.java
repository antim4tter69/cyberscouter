package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubmitPage extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_page);

        button = (Button) findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToSummaryQuestions();

            }
        });

        button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitMatch();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        CyberScouterMatchScouting csm = new CyberScouterMatchScouting();
        CyberScouterMatchScouting csm2 = csm.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));

        if (null != csm2) {
            TextView tv = (TextView) findViewById(R.id.textView7);
            tv.setText(getString(R.string.tagMatch, csm2.getTeamMatchNo()));
            tv = (TextView) findViewById(R.id.textView9);
            tv.setText(getString(R.string.tagTeam, csm2.getTeam()));
        }
    }

    public void returnToSummaryQuestions(){
        this.finish();
    }

    public void submitMatch(){
        Intent intent = new Intent(this, ScoutingPage.class);
        startActivity(intent);

    }
}
