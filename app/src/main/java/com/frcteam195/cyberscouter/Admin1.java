package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

public class Admin1 extends AppCompatActivity {
    private Button button;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin1);

        Spinner spnr = findViewById(R.id.spinner);
        String[] spnr_items = {"Red 1", "Red 2", "Red 3", "Blue 1", "Blue 2", "Blue 3", "Pit", "Review"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_text_items , spnr_items);

        spnr.setAdapter(adapter);


        button = (Button) findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setTestMode();
            }
        });

        button = (Button) findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateCode();
            }
        });

        button = (Button) findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setUpSync();
            }
        });

        imageButton = (ImageButton) findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            }
        });

        imageButton = (ImageButton) findViewById(R.id.imageButton2);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            }
        });


    }
public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
}


    public void setTestMode(){}
    public void updateCode(){}
    public void setUpSync(){}
}
