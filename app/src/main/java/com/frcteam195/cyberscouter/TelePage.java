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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_page);

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

        button = findViewById(R.id.FPanelHSMinus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                farPanelHighShotMinus();
            }
        });

        button = findViewById(R.id.FPanelHSPlus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                farPanelHighShotPlus();
            }
        });

        button = findViewById(R.id.FPanelMSMinus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                farPanelMedShotMinus();
            }
        });

        button = findViewById(R.id.FPanelMSPlus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                farPanelMedShotPlus();
            }
        });

        button = findViewById(R.id.FPanelLSMinus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                farPanelLowShotMinus();
            }
        });

        button = findViewById(R.id.FPanelLSPlus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                farPanelLowShotPlus();
            }
        });

        button = findViewById(R.id.CargoHSMinus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoHighShotMinus();
            }
        });

        button = findViewById(R.id.CargoHSPlus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoHighShotPlus();
            }
        });

        button = findViewById(R.id.CargoMSMinus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoMedShotMinus();
            }
        });

        button = findViewById(R.id.CargoMSPlus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoMedShotPlus();
            }
        });

        button = findViewById(R.id.CargoLSMinus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoLowShotMinus();
            }
        });

        button = findViewById(R.id.CargoLSPlus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoLowShotPlus();
            }
        });

        button = findViewById(R.id.NPanelHSMinus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nearPanelHighShotMinus();
            }
        });

        button = findViewById(R.id.NPanelHSPlus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nearPanelHighShotPlus();
            }
        });

        button = findViewById(R.id.NPanelMSMinus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nearPanelMedShotMinus();
            }
        });

        button = findViewById(R.id.NPanelMSPlus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nearPanelMedShotPlus();
            }
        });

        button = findViewById(R.id.NPanelLSMinus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nearPanelLowShotMinus();
            }
        });

        button = findViewById(R.id.NPanelLSPlus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nearPanelLowShotPlus();
            }
        });

        button = findViewById(R.id.PanelMinus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoshipPanelMinus();
            }
        });

        button = findViewById(R.id.PanelPlus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoshipPanelPlus();
            }
        });

        button = findViewById(R.id.CargoMinus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoshipCargoMinus();
            }
        });

        button = findViewById(R.id.CargoPlus_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoshipCargoPlus();
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

    public void cargoshipCargoMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void cargoshipCargoPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void cargoshipPanelMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void cargoshipPanelPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void farPanelHighShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void farPanelHighShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void farPanelMedShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void farPanelMedShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }

    }

    public void farPanelLowShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void farPanelLowShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void nearPanelHighShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void nearPanelHighShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void nearPanelMedShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void nearPanelMedShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void nearPanelLowShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void nearPanelLowShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void cargoLowShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void cargoLowShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void cargoMedShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void cargoMedShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void cargoHighShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
    }

    public void cargoHighShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
        }
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
