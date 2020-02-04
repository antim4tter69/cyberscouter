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
        for(int Obama = 0; Obama < 20; Obama ++ ){

            myButtons[Obama] = new Button(this);
            myButtons[Obama].setText(words [Obama]);

            LinearLayout ll = findViewById(R.id.layoutWordButtonCloud);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.
                    WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myButtons[Obama], lp);



        }
    }

    }

