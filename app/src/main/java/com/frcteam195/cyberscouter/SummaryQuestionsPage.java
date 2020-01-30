package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SummaryQuestionsPage extends AppCompatActivity {
    private Button button;
    private RadioButton rb;
    private RadioGroup rg;
    private final int[] radioButtonArray = {R.id.radioButton1, R.id.radioButton2, R.id.radioButton3, R.id.radioButton4, R.id.radioButton5};
    private int lastCheckedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_questions_page);


        button = findViewById(R.id.button_previous);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToEndGamePage();

            }
        });

        button = findViewById(R.id.button_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSubmitPage();

            }
        });

        button = findViewById(R.id.button_nextAnswer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextAnswer();

            }
        });

        button = findViewById(R.id.button_previousAnswer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousAnswer();

            }
        });

        rb = findViewById(R.id.radioButton1);
        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbClicked(v);
            }
        });
        rb = findViewById(R.id.radioButton2);
        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbClicked(v);
            }
        });
        rb = findViewById(R.id.radioButton3);
        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbClicked(v);
            }
        });
        rb = findViewById(R.id.radioButton4);
        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbClicked(v);
            }
        });
        rb = findViewById(R.id.radioButton5);
        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbClicked(v);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        rb = findViewById(R.id.radioButton1);
        rb.setVisibility(View.GONE);
        rb.setEnabled(false);
        rb = findViewById(R.id.radioButton2);
        rb.setVisibility(View.GONE);
        rb.setEnabled(false);
        rb = findViewById(R.id.radioButton3);
        rb.setVisibility(View.GONE);
        rb.setEnabled(false);
        rb = findViewById(R.id.radioButton4);
        rb.setVisibility(View.GONE);
        rb.setEnabled(false);
        rb = findViewById(R.id.radioButton5);
        rb.setVisibility(View.GONE);
        rb.setEnabled(false);

        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        if (null != cfg) {

            CyberScouterMatchScouting csm = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));

            if (null != csm) {
                TextView tv = findViewById(R.id.textView7);
                tv.setText(getString(R.string.tagMatch, csm.getTeamMatchNo()));
                tv = findViewById(R.id.textView9);
                tv.setText(getString(R.string.tagTeam, csm.getTeam()));
            }

        }

    }

    public void returnToEndGamePage() {
        updateAnswer();
        this.finish();
    }

    public void openSubmitPage() {
        updateAnswer();
        Intent intent = new Intent(this, SubmitPage.class);
        startActivity(intent);
    }

    public void nextAnswer() {
        // Update the Match Scouting record
        updateAnswer();

        // Get the next question, if any
        setNextQuestion(1);
        this.onResume();
    }

    public void previousAnswer() {
        updateAnswer();

        setNextQuestion(-1);
        this.onResume();
    }

    private void setNextQuestion(int val) {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

    }

    private void updateAnswer() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        if (null != cfg) {

            rg = findViewById(R.id.radioGroup1);
            int rbid = rg.getCheckedRadioButtonId();

            int ans;
            switch (rbid) {
                case (R.id.radioButton1):
                    ans = 0;
                    break;
                case (R.id.radioButton2):
                    ans = 1;
                    break;
                case (R.id.radioButton3):
                    ans = 2;
                    break;
                case (R.id.radioButton4):
                    ans = 3;
                    break;
                case (R.id.radioButton5):
                    ans = 4;
                    break;
                default:
                    ans = -1;
            }

        }

    }

    private void rbClicked(View v) {
        rg = findViewById(R.id.radioGroup1);
        int currentCheckedButton = rg.getCheckedRadioButtonId();
        if(lastCheckedButton == currentCheckedButton) {
            rg.clearCheck();
            updateAnswer();
        } else {
            rg.check(v.getId());
            updateAnswer();
        }
        this.onResume();
    }

}
