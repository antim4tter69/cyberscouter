package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdmin1();

            }
        });

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScouting();

            }
        });

        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syncPictures();

            }
        });

        button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syncData();

            }
        });

        button = (Button) findViewById(R.id.SwitchButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOffline();

            }
        });

    }
    public void openAdmin1(){
    Intent intent = new Intent(this, Admin1.class);
    startActivity(intent);
    }

    public void openScouting(){
        Intent intent = new Intent(this, ScoutingPage.class);
        startActivity(intent);
    }

    public void syncPictures(){
    }

    public void syncData(){
    }

    public void setOffline(){
    }
}
