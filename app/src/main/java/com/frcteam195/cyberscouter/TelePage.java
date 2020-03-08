package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TelePage extends AppCompatActivity implements TelePopUpPage.OnFragmentInteractionListener {
    private Button button;
    private Button zone_1L;
    private Button zone_2L;
    private Button zone_3L;
    private Button zone_4L;
    private Button zone_5L;
    private Button zone_1R;
    private Button zone_2R;
    private Button zone_3R;
    private Button zone_4R;
    private Button zone_5R;
    private ImageView imageView8;
    private ImageView imageView9;
    private int FIELD_ORIENTATION_RIGHT = 0;
    private int FIELD_ORIENTATION_LEFT = 1;
    private int field_orientation;
    private Chronometer Stage_2;
    private long pauseOffset;
    private boolean running;
    private int currentCommStatusColor;

    private Integer[][] values = new Integer[5][4];
    private int _stage2Attempts = 0, _stage2Time = 0, _stage2Status = 0, _stage3Attempts = 0, _stage3Time = 0, _stage3Status = 0;

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

        Intent intent = getIntent();
        field_orientation = intent.getIntExtra("field_orientation", field_orientation);
        ImageView iv = findViewById(R.id.imageView8);
        if (FIELD_ORIENTATION_RIGHT == field_orientation) {
            iv.setImageResource(R.drawable.field_2020_flipped);
        }
//        currentCommStatusColor = intent.getIntExtra("commstatuscolor", Color.LTGRAY);
//        updateStatusIndicator(currentCommStatusColor);


        TextView tv = findViewById(R.id.textView_roleTag);
        tv.setText(R.string.teleopTitle);

        button = findViewById(R.id.button_Previous);
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

        button = findViewById(R.id.Reset_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();

            }
        });

        Stage_2 = findViewById(R.id.Stage_2);
        Stage_2.setFormat("Time: %s");
        Stage_2.setBase(SystemClock.elapsedRealtime());

        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        CyberScouterMatchScouting csm = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getAlliance_station()));

        button = findViewById(R.id.zone_1L);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zone_Clicked(1);

            }
        });

        button = findViewById(R.id.zone_1R);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zone_Clicked(1);

            }
        });

        button = findViewById(R.id.zone_2L);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zone_Clicked(2);

            }
        });

        button = findViewById(R.id.zone_2R);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zone_Clicked(2);

            }
        });

        button = findViewById(R.id.zone_3L);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zone_Clicked(3);

            }
        });

        button = findViewById(R.id.zone_3R);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zone_Clicked(3);

            }
        });

        button = findViewById(R.id.zone_4L);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zone_Clicked(4);

            }
        });

        button = findViewById(R.id.zone_4R);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zone_Clicked(4);

            }
        });

        button = findViewById(R.id.zone_5L);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zone_Clicked(5);

            }
        });

        button = findViewById(R.id.zone_5R);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zone_Clicked(5);

            }
        });

        if (csm.getAllianceStationID() < 4) {
            if (field_orientation == FIELD_ORIENTATION_LEFT) {
                button = findViewById(R.id.zone_1L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_2L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_3L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_4L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_5L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_1R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_2R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_3R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_4R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_5R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);
            } else {
                button = findViewById(R.id.zone_1L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_2L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_3L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_4L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_5L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_1R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_2R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_3R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_4R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_5R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);
            }
        } else {
            if (field_orientation == FIELD_ORIENTATION_LEFT) {
                button = findViewById(R.id.zone_1L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_2L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_3L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_4L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_5L);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_1R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_2R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_3R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_4R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_5R);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);
            } else {
                button = findViewById(R.id.zone_1L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_2L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_3L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_4L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_5L);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);

                button = findViewById(R.id.zone_1R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_2R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_3R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_4R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);

                button = findViewById(R.id.zone_5R);
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);
            }
        }

        button = findViewById(R.id.button_teleStartChron);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStage_2(v);
            }
        });
        button = findViewById(R.id.button_teleSuccessChron);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetStage_2(v);
            }
        });
        button = findViewById(R.id.button_teleFailureChron);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseStage_2(v);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        _db = mDbHelper.getWritableDatabase();
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
                _stage2Status = csm.getTeleWheelStage2Status();
                _stage2Time = csm.getTeleWheelStage2Time();
                _stage2Attempts = csm.getTeleWheelStage2Attempts();
                _stage3Time = csm.getTeleWheelStage3Time();
                _stage3Attempts = csm.getTeleWheelStage3Attempts();
                _stage3Status = csm.getTeleWheelStage3Status();
                setTimer();
            }
        }
    }

    private void zone_Clicked(int i) {
        if (running){
            return;
        }
        FragmentManager fm = getSupportFragmentManager();
        TelePopUpPage tpp = new TelePopUpPage();
        tpp.setValues(values[i-1]);
        tpp.show(fm, "telepopup");
    }

    public void startStage_2(View V) {
        if (!running) {
            Stage_2.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            Stage_2.start();
            running = true;
        }
    }

    private void pauseStage_2(View V) {
        if (running) {
            Stage_2.stop();
            pauseOffset = SystemClock.elapsedRealtime() - Stage_2.getBase();
            running = false;
            if(0 == _stage2Time) {
                _stage2Attempts++;
            } else {
                _stage3Attempts++;
            }
        }
    }

    private void resetStage_2(View V) {
        if(running) {
            Stage_2.stop();
            running = false;
        }
        int ltime = (int)(SystemClock.elapsedRealtime() - Stage_2.getBase()) / 1000;
        if(0 == _stage2Time) {
            _stage2Time = ltime;
            _stage2Status = 1;
            TextView tv = findViewById(R.id.textView_stage);
            tv.setText(getString(R.string.teleStage3));
        } else {
            _stage3Time = ltime;
            _stage3Status = 1;
        }
        pauseOffset = 0;
        Stage_2.setBase(SystemClock.elapsedRealtime());
        setTimer();
    }

    public void returnToAutoPage() {
        setMetricValues();
        this.finish();
    }

    public void EndGamePage() {
        setMetricValues();
        Intent intent = new Intent(this, EndPage.class);
//        intent.putExtra("commstatuscolor", currentCommStatusColor);
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
        _lValues[11] = _stage2Attempts;
        _lValues[12] = _stage2Time;
        _lValues[13] = _stage2Status;
        _lValues[14] = _stage3Attempts;
        _lValues[15] = _stage3Time;
        _lValues[16] = _stage3Status;

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

    private void setTimer(){
        TextView tv = findViewById(R.id.textView_stage);
        tv = findViewById(R.id.textView_stage);
        if(0 == _stage2Time) {
            tv.setText(getString(R.string.teleStage2));
        } else if(0 == _stage3Time){
            tv.setText(getString(R.string.teleStage3));
        } else {
            tv.setText(getString(R.string.completed));
            button = findViewById(R.id.button_teleStartChron);
            button.setEnabled(false);
            button = findViewById(R.id.button_teleFailureChron);
            button.setEnabled(false);
            button = findViewById(R.id.button_teleSuccessChron);
            button.setEnabled(false);
        }
    }

    private void resetTimer(){
        _stage2Time = 0 ;
        _stage2Attempts = 0;
        _stage2Status = 0;
        _stage3Time = 0 ;
        _stage3Attempts = 0;
        _stage3Status = 0;
        TextView tv = findViewById(R.id.textView_stage);
        tv.setText(getString(R.string.teleStage2));
        button = findViewById(R.id.button_teleStartChron);
        button.setEnabled(true);
        button = findViewById(R.id.button_teleFailureChron);
        button.setEnabled(true);
        button = findViewById(R.id.button_teleSuccessChron);
        button.setEnabled(true);
    }

    private void updateStatusIndicator(int color) {
        ImageView iv = findViewById(R.id.imageView_btIndicator);
        BluetoothComm.updateStatusIndicator(iv, color);
    }

    public void onFragmentInteraction(Integer[] fragmentValues) {
        for(int i = 1; i<fragmentValues.length ; ++i) {
            values[fragmentValues[0] - 1][i] = fragmentValues[i];
        }
    }
}
