package com.frcteam195.cyberscouter;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

public class WordCloudActivity extends AppCompatActivity {
    private String[] words = {"Fast", "Slow", "Efficient", "Efficient Ground Pickup", "NINO",
            "Good", "Bad", "Good Leveler", "Inefficient Ground Pickup", "Penalty Prone", "Strong",
            "Weak", "Unaffected by Defense", "Affected by Defense", "Fast Climb", "Bad Climb", "Accurate",
            "Accurate Longshot", "Good w/ Wheel", "Bad w/ Wheel"};
    Button[] myButtons = new Button[20];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_cloud);
        wordLayout();
        wordLayout2();
        wordLayout3();
        wordLayout4();
    }

    protected void wordLayout() {

        for (int Obama = 0; Obama < 5; Obama++) {

            myButtons[Obama] = new Button(this);
            myButtons[Obama].setTextSize(40);
            myButtons[Obama].setText(words[Obama]);

            LinearLayout ll = findViewById(R.id.layoutWordButtonCloud);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.
                    WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myButtons[Obama], lp);

        }

    }

    protected void wordLayout2() {

        for (int Bush = 5; Bush < 10; Bush++) {

            myButtons[Bush] = new Button(this);
            myButtons[Bush].setTextSize(40);
            myButtons[Bush].setText(words[Bush]);

            LinearLayout ll = findViewById(R.id.layoutWordButtonCloud2);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.
                    WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myButtons[Bush], lp);
        }


    }

    protected void wordLayout3() {

        for (int Taft = 10; Taft < 15; Taft++) {

            myButtons[Taft] = new Button(this);
            myButtons[Taft].setTextSize(40);
            myButtons[Taft].setText(words[Taft]);

            LinearLayout ll = findViewById(R.id.layoutWordButtonCloud3);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.
                    WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myButtons[Taft], lp);


        }
    }

    protected void wordLayout4() {

        for (int Adams = 15; Adams < 20; Adams++) {

            myButtons[Adams] = new Button(this);
            myButtons[Adams].setTextSize(40);
            myButtons[Adams].setText(words[Adams]);

            LinearLayout ll = findViewById(R.id.layoutWordButtonCloud4);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.
                    WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myButtons[Adams], lp);

        }

    }
}





