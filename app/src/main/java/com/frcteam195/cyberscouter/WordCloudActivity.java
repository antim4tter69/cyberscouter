package com.frcteam195.cyberscouter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class WordCloudActivity extends AppCompatActivity {
    private String [] words = {"Fast", "Slow", "Efficient", "Efficient Ground Pickup", "NINO",
            "Good", "Bad", "Good Leveler", "Inefficient Ground Pickup", "Penalty Prone", "Strong",
    "Weak", "Unaffected by Defense", "Affected by Defense", "Fast Climb", "Bad Climb", "Accurate",
    "Accurate Longshot", "Good w/ Wheel", "Bad w/ Wheel"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_cloud);
    }

    protected void createWordBubbles() {
        for(int x = 0; x < words.length; ++x) {
            System.out.println(words[x]);

        }

    }
}
