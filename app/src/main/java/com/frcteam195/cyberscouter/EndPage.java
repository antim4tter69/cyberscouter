package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class EndPage extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_page);

        button = (Button) findViewById(R.id.button11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToTelePage();

            }
        });

        button = (Button) findViewById(R.id.button67);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                summaryQuestionsPage();

            }
        });
    }
    public void returnToTelePage(){
        Intent intent = new Intent(this, TelePage.class);
        startActivity(intent);

    }

    public void summaryQuestionsPage(){
        Intent intent = new Intent(this, SummaryQuestionsPage.class);
        startActivity(intent);

    }
}