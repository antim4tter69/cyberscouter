package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AutoPage extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_page);

        button = (Button) findViewById(R.id.button10);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                StartMatch();
            }
        });

        button = (Button) findViewById(R.id.button11);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ReturnToScoutingPage();
            }
        });

        button = (Button) findViewById(R.id.button51);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                groundFar();
            }
        });

        button = (Button) findViewById(R.id.button19);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                levelTwoFar();
            }
        });

        button = (Button) findViewById(R.id.button15);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                levelOneFar();
            }
        });

        button = (Button) findViewById(R.id.button12);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                levelOneMid();
            }
        });

        button = (Button) findViewById(R.id.button16);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                levelTwoNear();
            }
        });

        button = (Button) findViewById(R.id.button13);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                levelOneNear();
            }
        });

        button = (Button) findViewById(R.id.button52);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                groundNear();
            }
        });

        button = (Button) findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                skipMatch();
            }
        });

        button = (Button) findViewById(R.id.button20);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                moveBonusYes();
            }
        });

        button = (Button) findViewById(R.id.button21);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                moveBonusNo();
            }
        });

        button = (Button) findViewById(R.id.button22);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                preloadCargo();
            }
        });

        button = (Button) findViewById(R.id.button23);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                preloadPanel();
            }
        });

        button = (Button) findViewById(R.id.button24);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                preloadNone();
            }
        });



    }
    public void StartMatch(){

            Intent intent = new Intent(this, TelePage.class);
            startActivity(intent);
    }
    public void ReturnToScoutingPage(){
        Intent intent = new Intent(this, ScoutingPage.class);
        startActivity(intent);
    }

    public void groundFar(){}
    public void groundNear(){}
    public void levelOneFar(){}
    public void levelOneNear(){}
    public void levelOneMid(){}
    public void levelTwoFar(){}
    public void levelTwoNear(){}
    public void skipMatch(){}
    public void moveBonusYes(){}
    public void moveBonusNo(){}
    public void preloadCargo(){}
    public void preloadPanel(){}
    public void preloadNone(){}



}
