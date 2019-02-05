package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ScoutingPage extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting_page);

        button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAuto();

            }
        });

        button = (Button) findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNamePickerPage();

            }
        });
        button = (Button) findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMainMenu();

            }
        });
    }



    public void openAuto(){
        Intent intent = new Intent(this, AutoPage.class);
        startActivity(intent);


    }
    public void openNamePickerPage(){
        Intent intent = new Intent(this, NamePickerPageActivity.class);
        startActivity(intent);
    }
    public void returnToMainMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
