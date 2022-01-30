package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

public class TelePage extends AppCompatActivity {
    private static final int SELECTED_BUTTON_TEXT_COLOR = Color.GREEN;
    private final int defaultButtonTextColor = Color.LTGRAY;

    Integer upperCounter, lowerCounter;

    private Button Reset_button;
    private Button StageTwoButton;
    private Button StageThreeButton;
    private ImageView imageView8;
    private ImageView imageView9;
    private int currentCommStatusColor;


    //private final int[] StageButtons = {R.id.StageTwoButton, R.id.StageThreeButton};

    private Integer[][] values = new Integer[5][4];

    String[] _lColumns = {CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE1,
            CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE1,
            CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLLOWZONE1,
            CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE2,
            CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE2,
            CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE3,
            CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE3,
            CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE4,
            CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE4,
            CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLINNERZONE5,
            CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLOUTERZONE5,
            CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE2ATTEMPTS,
            CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE2TIME,
            CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE2STATUS,
            CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE3ATTEMPTS,
            CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE3TIME,
            CyberScouterContract.MatchScouting.COLUMN_NAME_TELEWHEELSTAGE3STATUS
    };
    Integer[] _lValues = new Integer[_lColumns.length];

    private CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
    private SQLiteDatabase _db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_page);

        mDbHelper = new CyberScouterDbHelper(this);
        _db = mDbHelper.getWritableDatabase();

        Button button;

/*
        Button Reset_button = (Button) findViewById(R.id.Reset_button);
        Reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TelePage.this, Reset.class));
            }
        });
*/


        CyberScouterConfig cfg = CyberScouterConfig.getConfig(_db);
/*
        if(null != cfg) {
            field_orientation = cfg.isFieldRedLeft() ? FIELD_ORIENTATION_LEFT : FIELD_ORIENTATION_LEFT;
        }


        if (FIELD_ORIENTATION_RIGHT == field_orientation) {
            //iv.setImageResource(R.drawable.field_2020_flipped);
        }
*/

        ImageView iv = findViewById(R.id.imageView_teleBtIndicator);
        Intent intent = getIntent();
        currentCommStatusColor = intent.getIntExtra("commstatuscolor", Color.LTGRAY);
        updateStatusIndicator(currentCommStatusColor);


        TextView tv = findViewById(R.id.textView_roleTag);
        tv.setText(R.string.teleopTitle);

        button = findViewById(R.id.btnTelePrevious);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToAutoPage();
            }
        });

        button = findViewById(R.id.btnTeleNext);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EndGamePage();
            }
        });

        CyberScouterMatchScouting csm = CyberScouterMatchScouting.getCurrentMatch(_db, TeamMap.getNumberForTeam(cfg.getAlliance_station()));

        button = findViewById(R.id.btnTeleUpperAdd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperAddClicked();
            }
        });

        button = findViewById(R.id.btnTeleUpperSubtract);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperSubtractClicked();
            }
        });

        button = findViewById(R.id.btnTeleUpperCounter);
        upperCounter = 0;
        button.setText(upperCounter);

        button = findViewById(R.id.btnTeleLowerAdd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lowerAddClicked();
            }
        });

        button = findViewById(R.id.btnTeleLowerSubtract);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lowerSubtractClicked();
            }
        });

        button = findViewById(R.id.btnTeleLowerCounter);
        lowerCounter = 0;
        button.setText(lowerCounter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(_db);
        if (null != cfg) {
            CyberScouterMatchScouting csm = CyberScouterMatchScouting.getCurrentMatch(_db, TeamMap.getNumberForTeam(cfg.getAlliance_station()));

            if (null != csm) {
                TextView tv = findViewById(R.id.textView_teleMatch);
                tv.setText(getString(R.string.tagMatch, csm.getMatchNo()));
                tv = findViewById(R.id.textView_teamNumber);
                tv.setText(getString(R.string.tagTeam, csm.getTeam()));

                for(int i=0; i<5 ; ++i) {
                    values[i][0] = i+1;
                    switch(i) {
                        case 0:
                            values[i][1] = csm.getTeleBallInnerZone1();
                            values[i][2] = csm.getTeleBallOuterZone1();
                            values[i][3] = csm.getTeleBallLowZone1();
                            break;
                        case 1:
                            values[i][1] = csm.getTeleBallInnerZone2();
                            values[i][2] = csm.getTeleBallOuterZone2();
                            break;
                        case 2:
                            values[i][1] = csm.getTeleBallInnerZone3();
                            values[i][2] = csm.getTeleBallOuterZone3();
                            break;
                        case 3:
                            values[i][1] = csm.getTeleBallInnerZone4();
                            values[i][2] = csm.getTeleBallOuterZone4();
                            break;
                        case 4:
                            values[i][1] = csm.getTeleBallInnerZone5();
                            values[i][2] = csm.getTeleBallOuterZone5();
                            break;
                    }
                }
            }
        }
    }

    public void returnToAutoPage() {
        setMetricValues();
        this.finish();
    }

    public void EndGamePage() {
        setMetricValues();
        Intent intent = new Intent(this, EndPage.class);
        intent.putExtra("commstatuscolor", currentCommStatusColor);
        startActivity(intent);
    }

    void setMetricValues() {
        CyberScouterConfig cfg = CyberScouterConfig.getConfig(_db);

        _lValues[0] = values[0][1];
        _lValues[1] = values[0][2];
        _lValues[2] = values[0][3];
        _lValues[3] = values[1][1];
        _lValues[4] = values[1][2];
        _lValues[5] = values[2][1];
        _lValues[6] = values[2][2];
        _lValues[7] = values[3][1];
        _lValues[8] = values[3][2];
        _lValues[9] = values[4][1];
        _lValues[10] = values[4][2];

        if (null != cfg) {
            try {
                CyberScouterMatchScouting.updateMatchMetric(_db, _lColumns, _lValues, cfg);
            } catch (Exception e) {
                MessageBox.showMessageBox(this, "Metric Update Failed Alert", "setMetricValue", "An error occurred trying to update metric.\n\nError is:\n" + e.getMessage());
            }
        } else {
            MessageBox.showMessageBox(this, "Configuration Not Found Alert", "setMetricValue", "An error occurred trying to acquire current configuration.  Cannot continue.");
        }
    }

    private void updateStatusIndicator(int color) {
        ImageView iv = findViewById(R.id.imageView_teleBtIndicator);
        BluetoothComm.updateStatusIndicator(iv, color);
    }

    private void upperAddClicked() {
        upperCounter++;
        Button button = findViewById(R.id.btnTeleUpperCounter);
        button.setText(upperCounter.toString());
    }

    private void upperSubtractClicked() {
        if(0 < upperCounter) {
            upperCounter--;
            Button button = findViewById(R.id.btnTeleUpperCounter);
            button.setText(upperCounter.toString());
        }
    }

    private void lowerAddClicked() {
        lowerCounter++;
        Button button = findViewById(R.id.btnTeleLowerCounter);
        button.setText(lowerCounter.toString());
    }

    private void lowerSubtractClicked() {
        if(0 < lowerCounter) {
            lowerCounter--;
            Button button = findViewById(R.id.btnTeleLowerCounter);
            button.setText(lowerCounter.toString());
        }
    }

}
