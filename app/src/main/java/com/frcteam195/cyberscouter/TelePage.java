package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class TelePage extends AppCompatActivity {
    private Button button;
    private int defaultButtonTextColor;
    private final int SELECTED_BUTTON_TEXT_COLOR = Color.GREEN;
    private int FIELD_ORIENTATION_RIGHT=0;
    private int FIELD_ORIENTATION_LEFT=1;
    private int field_orientation=FIELD_ORIENTATION_RIGHT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_page);
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        intent.getIntExtra("field_orientation",field_orientation);

        TextView tv = findViewById(R.id.textView_roleTag);
        tv.setText(R.string.teleopTitle);

        button = findViewById(R.id.button_previous);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToAutoPage();

            }
        });

        button = findViewById(R.id.button_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EndGamePage();

            }
        });




    }
    @Override
    protected void onResume() {
        super.onResume();

        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csm = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));

            if (null != csm) {
                TextView tv = findViewById(R.id.textView7);
                tv.setText(getString(R.string.tagMatch, csm.getTeamMatchNo()));
                tv = findViewById(R.id.textView9);
                tv.setText(getString(R.string.tagTeam, csm.getTeam()));



                button = findViewById(R.id.button18);
                button.setText(String.format(Locale.getDefault(), "%d", csm.getTeleCSHatch()));
                button = findViewById(R.id.button30);
                button.setText(String.format(Locale.getDefault(), "%d", csm.getTeleCSCargo()));
                button = findViewById(R.id.button43);
                button.setText(String.format(Locale.getDefault(), "%d", csm.getTeleRSHatchNearLow()));

            }
        }


    }

    public void returnToAutoPage() {
        this.finish();
    }

    public void EndGamePage() {
        Intent intent = new Intent(this, EndPage.class);

        startActivity(intent);

    }


    

    void setMetricValue(String col, int val) {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        if (0 > val)
            val = 0;

        String[] cols = {col};
        Integer[] vals = {val};

        if (null != cfg && null != cfg.getRole()) {
            try {
                CyberScouterMatchScouting.updateMatchMetric(db, cols, vals, cfg);
            } catch (Exception e) {
                MessageBox.showMessageBox(this, "Metric Update Failed Alert", "setMetricValue", "An error occurred trying to update metric.\n\nError is:\n" + e.getMessage());
            }
        } else {
            MessageBox.showMessageBox(this, "Configuration Not Found Alert", "setMetricValue", "An error occurred trying to acquire current configuration.  Cannot continue.");
        }
        this.onResume();
    }
}
