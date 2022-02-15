package com.frcteam195.cyberscouter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LevelTwoScoutingTwo extends AppCompatActivity {
    private final int[] MultiRobotDefenseButtons = {R.id.button_MRDYes, R.id.button_MRDNo};

    //String[] _lColumns = {CyberScouterContract.MatchScouting.COLUMN_NAME_MULTIROBOTDEFENSE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_two_scouting_two);
    }
}