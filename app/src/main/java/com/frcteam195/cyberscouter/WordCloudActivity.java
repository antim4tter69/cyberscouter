package com.frcteam195.cyberscouter;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

public class WordCloudActivity extends AppCompatActivity {
    private String [] words = {"Fast", "Slow", "Efficient", "Efficient Ground Pickup", "NINO",
            "Good", "Bad", "Good Leveler", "Inefficient Ground Pickup", "Penalty Prone", "Strong",
    "Weak", "Unaffected by Defense", "Affected by Defense", "Fast Climb", "Bad Climb", "Accurate",
    "Accurate Longshot", "Good w/ Wheel", "Bad w/ Wheel"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_cloud);
        wordLayout();

    }

    protected void wordLayout() {
        Button [] myButtons = new Button[20];
        for(int Obama = 0; Obama < 5; Obama ++ ){

            myButtons[Obama] = new Button(this);
            myButtons[Obama].setText(words [Obama]);

            LinearLayout ll = findViewById(R.id.layoutWordButtonCloud);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.
                    WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myButtons[Obama], lp);



        }

        for(int Obama2 = 5; Obama2 < 10; Obama2 ++ ){

            myButtons[Obama2] = new Button(this);
            myButtons[Obama2].setText(words [Obama2]);

            LinearLayout ll = findViewById(R.id.layoutWordButtonCloud2);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.
                    WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myButtons[Obama2], lp);



        }

        for(int Obama3 = 10; Obama3 < 15; Obama3 ++ ){

            myButtons[Obama3] = new Button(this);
            myButtons[Obama3].setText(words [Obama3]);

            LinearLayout ll = findViewById(R.id.layoutWordButtonCloud3);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.
                    WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myButtons[Obama3], lp);



        }

        for(int Obama4 = 15; Obama4 < 20; Obama4 ++ ){

            myButtons[Obama4] = new Button(this);
            myButtons[Obama4].setText(words [Obama4]);

            LinearLayout ll = findViewById(R.id.layoutWordButtonCloud4);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.
                    WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myButtons[Obama4], lp);



        }


    }
    }

