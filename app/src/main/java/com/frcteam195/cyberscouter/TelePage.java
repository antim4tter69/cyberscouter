package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class TelePage extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_page);

        button = findViewById(R.id.button_previous);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToAutoPage();

            }
        });

        button = findViewById(R.id.button14);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EndGamePage();

            }
        });

        button = findViewById(R.id.button85);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                farPanelHighShotMinus();
            }
        });

        button = findViewById(R.id.button83);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                farPanelHighShotPlus();
            }
        });

        button = findViewById(R.id.button58);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                farPanelMedShotMinus();
            }
        });

        button = findViewById(R.id.button56);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                farPanelMedShotPlus();
            }
        });

        button = findViewById(R.id.button55);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                farPanelLowShotMinus();
            }
        });

        button = findViewById(R.id.button53);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                farPanelLowShotPlus();
            }
        });

        button = findViewById(R.id.button86);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoHighShotMinus();
            }
        });

        button = findViewById(R.id.button89);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoHighShotPlus();
            }
        });

        button = findViewById(R.id.button59);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoMedShotMinus();
            }
        });

        button = findViewById(R.id.button62);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoMedShotPlus();
            }
        });

        button = findViewById(R.id.button41);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoLowShotMinus();
            }
        });

        button = findViewById(R.id.button42);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoLowShotPlus();
            }
        });

        button = findViewById(R.id.button87);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nearPanelHighShotMinus();
            }
        });

        button = findViewById(R.id.button91);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nearPanelHighShotPlus();
            }
        });

        button = findViewById(R.id.button60);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nearPanelMedShotMinus();
            }
        });

        button = findViewById(R.id.button64);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nearPanelMedShotPlus();
            }
        });

        button = findViewById(R.id.button40);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nearPanelLowShotMinus();
            }
        });

        button = findViewById(R.id.button39);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nearPanelLowShotPlus();
            }
        });

        button = findViewById(R.id.button17);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoshipPanelMinus();
            }
        });

        button = findViewById(R.id.button19);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoshipPanelPlus();
            }
        });

        button = findViewById(R.id.button29);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cargoshipCargoMinus();
            }
        });

        button = findViewById(R.id.button31);
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

                button = findViewById(R.id.button18);
                button.setText(String.format(Locale.getDefault(), "%d", csm.getTeleCSHatch()));
                button = findViewById(R.id.button30);
                button.setText(String.format(Locale.getDefault(), "%d", csm.getTeleCSCargo()));
                button = findViewById(R.id.button43);
                button.setText(String.format(Locale.getDefault(), "%d", csm.getTeleRSHatchNearLow()));
                button = findViewById(R.id.button63);
                button.setText(String.format(Locale.getDefault(), "%d", csm.getTeleRSHatchNearMed()));
                button = findViewById(R.id.button90);
                button.setText(String.format(Locale.getDefault(), "%d", csm.getTeleRSHatchNearHigh()));
                button = findViewById(R.id.button38);
                button.setText(String.format(Locale.getDefault(), "%d", csm.getTeleRSCargoLow()));
                button = findViewById(R.id.button57);
                button.setText(String.format(Locale.getDefault(), "%d", csm.getTeleRSCargoMed()));
                button = findViewById(R.id.button84);
                button.setText(String.format(Locale.getDefault(), "%d", csm.getTeleRSCargoHigh()));
                button = findViewById(R.id.button54);
                button.setText(String.format(Locale.getDefault(), "%d", csm.getTeleRSHatchFarLow()));
                button = findViewById(R.id.button61);
                button.setText(String.format(Locale.getDefault(), "%d", csm.getTeleRSHatchFarMed()));
                button = findViewById(R.id.button88);
                button.setText(String.format(Locale.getDefault(), "%d", csm.getTeleRSHatchFarHigh()));
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
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELECSCARGO, csms.getTeleCSCargo() - 1);
        }
    }

    public void cargoshipCargoPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELECSCARGO, csms.getTeleCSCargo() + 1);
        }
    }

    public void cargoshipPanelMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELECSHATCH, csms.getTeleCSHatch() - 1);
        }
    }

    public void cargoshipPanelPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELECSHATCH, csms.getTeleCSHatch() + 1);
        }
    }

    public void farPanelHighShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARHIGH, csms.getTeleRSHatchFarHigh() - 1);
        }
    }

    public void farPanelHighShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARHIGH, csms.getTeleRSHatchFarHigh() + 1);
        }
    }

    public void farPanelMedShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARMED, csms.getTeleRSHatchFarMed() - 1);
        }
    }

    public void farPanelMedShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARMED, csms.getTeleRSHatchFarMed() + 1);
        }

    }

    public void farPanelLowShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARLOW, csms.getTeleRSHatchFarLow() - 1);
        }
    }

    public void farPanelLowShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHFARLOW, csms.getTeleRSHatchFarLow() + 1);
        }
    }

    public void nearPanelHighShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARHIGH, csms.getTeleRSHatchNearHigh() - 1);
        }
    }

    public void nearPanelHighShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARHIGH, csms.getTeleRSHatchNearHigh() + 1);
        }
    }

    public void nearPanelMedShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARMED, csms.getTeleRSHatchNearMed() - 1);
        }
    }

    public void nearPanelMedShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARMED, csms.getTeleRSHatchNearMed() + 1);
        }
    }

    public void nearPanelLowShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARLOW, csms.getTeleRSHatchNearLow() - 1);
        }
    }

    public void nearPanelLowShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSHATCHNEARLOW, csms.getTeleRSHatchNearLow() + 1);
        }
    }

    public void cargoLowShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOLOW, csms.getTeleRSCargoLow() - 1);
        }
    }

    public void cargoLowShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOLOW, csms.getTeleRSCargoLow() + 1);
        }
    }

    public void cargoMedShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOMED, csms.getTeleRSCargoMed() - 1);
        }
    }

    public void cargoMedShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOMED, csms.getTeleRSCargoMed() + 1);
        }
    }

    public void cargoHighShotMinus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOHIGH, csms.getTeleRSCargoHigh() - 1);
        }
    }

    public void cargoHighShotPlus() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (null != cfg && null != cfg.getRole()) {
            CyberScouterMatchScouting csms = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getRole()));
            if (null != csms)
                setMetricValue(CyberScouterContract.MatchScouting.COLUMN_NAME_TELERSCARGOHIGH, csms.getTeleRSCargoHigh() + 1);
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
