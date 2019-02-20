package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TelePage extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_page);

        button = (Button) findViewById(R.id.button11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToAutoPage();

            }
        });

        button = (Button) findViewById(R.id.button14);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EndGamePage();

            }
        });

        button = (Button) findViewById(R.id.button40);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                farPanelHighShotMinus();
            }
        });

        button = (Button) findViewById(R.id.button39);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                farPanelHighShotPlus();
            }
        });

        button = (Button) findViewById(R.id.button60);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                farPanelMedShotMinus();
            }
        });

        button = (Button) findViewById(R.id.button64);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                farPanelMedShotPlus();
            }
        });

        button = (Button) findViewById(R.id.button87);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                farPanelLowShotMinus();
            }
        });

        button = (Button) findViewById(R.id.button91);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                farPanelLowShotPlus();
            }
        });

        button = (Button) findViewById(R.id.button41);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoHighShotMinus();
            }
        });

        button = (Button) findViewById(R.id.button42);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoHighShotPlus();
            }
        });

        button = (Button) findViewById(R.id.button59);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoMedShotMinus();
            }
        });

        button = (Button) findViewById(R.id.button62);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoMedShotPlus();
            }
        });

        button = (Button) findViewById(R.id.button86);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoLowShotMinus();
            }
        });

        button = (Button) findViewById(R.id.button84);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoLowShotPlus();
            }
        });

        button = (Button) findViewById(R.id.button55);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nearPanelHighShotMinus();
            }
        });

        button = (Button) findViewById(R.id.button53);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nearPanelHighShotPlus();
            }
        });

        button = (Button) findViewById(R.id.button58);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nearPanelMedShotMinus();
            }
        });

        button = (Button) findViewById(R.id.button56);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nearPanelMedShotPlus();
            }
        });

        button = (Button) findViewById(R.id.button85);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nearPanelLowShotMinus();
            }
        });

        button = (Button) findViewById(R.id.button83);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nearPanelLowShotPlus();
            }
        });

        button = (Button) findViewById(R.id.button17);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoshipCargoMinus();
            }
        });

        button = (Button) findViewById(R.id.button19);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoshipCargoPlus();
            }
        });

        button = (Button) findViewById(R.id.button29);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoshipPanelMinus();
            }
        });

        button = (Button) findViewById(R.id.button31);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoshipPanelPlus();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        if(null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csm = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));

            if (null != csm) {
                TextView tv = (TextView) findViewById(R.id.textView7);
                tv.setText(getString(R.string.tagMatch, csm.getTeamMatchNo()));
                tv = (TextView) findViewById(R.id.textView9);
                tv.setText(getString(R.string.tagTeam, csm.getTeam()));
            }
        }


    }

    public void returnToAutoPage(){
        this.finish();
    }

    public void EndGamePage(){
        Intent intent = new Intent(this, EndPage.class);
        startActivity(intent);

    }
    public void cargoshipCargoMinus(){}
    public void cargoshipCargoPlus(){}
    public void cargoshipPanelMinus(){}
    public void cargoshipPanelPlus(){}
    public void farPanelHighShotMinus(){}
    public void farPanelHighShotPlus(){}
    public void farPanelMedShotMinus(){}
    public void farPanelMedShotPlus(){}
    public void farPanelLowShotMinus(){}
    public void farPanelLowShotPlus(){}
    public void nearPanelHighShotMinus(){}
    public void nearPanelHighShotPlus(){}
    public void nearPanelMedShotMinus(){}
    public void nearPanelMedShotPlus(){}
    public void nearPanelLowShotMinus(){}
    public void nearPanelLowShotPlus(){}
    public void cargoLowShotPlus(){}
    public void cargoHighShotMinus(){}
    public void cargoHighShotPlus(){}
    public void cargoMedShotMinus(){}
    public void cargoMedShotPlus(){}
    public void cargoLowShotMinus(){}

}
