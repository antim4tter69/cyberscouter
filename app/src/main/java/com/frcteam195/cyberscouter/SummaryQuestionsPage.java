package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SummaryQuestionsPage extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_questions_page);


        button = (Button) findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToEndGamePage();

            }
        });

        button = (Button) findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSubmitPage();

            }
        });

        button = (Button) findViewById(R.id.button10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionOne();

            }
        });

        button = (Button) findViewById(R.id.button11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionTwo();

            }
        });

        button = (Button) findViewById(R.id.button12);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionThree();

            }
        });

        button = (Button) findViewById(R.id.button13);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionFour();

            }
        });

        button = (Button) findViewById(R.id.button14);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionFive();

            }
        });

    }
    public void returnToEndGamePage(){
        Intent intent = new Intent(this, EndPage.class);
        startActivity(intent);

    }

    public void openSubmitPage(){
        Intent intent = new Intent(this, SubmitPage.class);
        startActivity(intent);

    }

    public void questionOne(){}
    public void questionTwo(){}
    public void questionThree(){}
    public void questionFour(){}
    public void questionFive(){}

}
