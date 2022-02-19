package com.frcteam195.cyberscouter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LevelTwoScoutingTwo extends AppCompatActivity {
    private Spinner primaryDefense = (Spinner) findViewById(R.id.spinner_PrimaryDefense);
    private Spinner secondaryDefense = (Spinner) findViewById(R.id.spinner_SecondayDefense);
    private Spinner howLong = (Spinner) findViewById(R.id.spinner_HowLong);
    private final int[] MultiRobotDefenseButtons = {R.id.button_MRDYes, R.id.button_MRDNo};
    private final String[] DefenseOptions = {"Pinning", "Shoving", "Steal Cargo", "Blocking", "Counter-Defense", "None"};
    private final String[] HowLongOptions = {"One Quarter of the Match", "Half of the Match", "Three Quarters of the Match", "The Entire Match"};
    private ArrayAdapter<String> pd = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, DefenseOptions);
    private ArrayAdapter<String> sd = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, DefenseOptions);
    private ArrayAdapter<String> hl = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, HowLongOptions);

    String[] _lColumns = {CyberScouterContract.MatchScoutingL2.COLUMN_NAME_T1MULTIROBOTDEFENSE,
    };

    private int multiRobotDefense;

    private int currentCommStatusColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_two_scouting_two);

        primaryDefense.setAdapter(pd);
        pd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        primaryDefense.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        secondaryDefense.setAdapter(sd);
        sd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secondaryDefense.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        howLong.setAdapter(hl);
        hl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        howLong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}