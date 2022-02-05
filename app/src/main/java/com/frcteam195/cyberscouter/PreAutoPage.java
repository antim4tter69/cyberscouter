package com.frcteam195.cyberscouter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PreAutoPage extends AppCompatActivity {
    private Button button;
    private final int[] startPositionButtons = {R.id.startbutton1, R.id.startbutton2, R.id.startbutton3, R.id.startbutton4, R.id.startbutton5, R.id.startbutton6};
    private int defaultButtonTextColor = Color.LTGRAY;
    private final int SELECTED_BUTTON_TEXT_COLOR = Color.GREEN;
    private Button didnotshowyesbutton;
    private int currentCommStatusColor;

    private final CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
    private SQLiteDatabase _db;

    private String[] _lColumns = {CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS,
            CyberScouterContract.MatchScouting.COLUMN_NAME_AUTODIDNOTSHOW};
    private int[] _lValues;
    private int _didNotShow = 0;
    private int _startPos = 0;

    BroadcastReceiver mOnlineStatusReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int color = intent.getIntExtra("onlinestatus", Color.RED);
            updateStatusIndicator(color);
        }
    };



    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_auto_page);

        _db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(_db);

        ImageView iv = findViewById(R.id.imageView6);
        if (null != cfg && !cfg.isFieldRedLeft()) {
            iv.setImageResource(R.drawable.bluefield);
            iv.setRotation(180);
        }

        button = findViewById(R.id.startbutton1);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startPosition(1);
            }
        });

        button = findViewById(R.id.startbutton2);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startPosition(2);
            }
        });

        button = findViewById(R.id.startbutton3);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startPosition(3);
            }
        });

        button = findViewById(R.id.startbutton4);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startPosition(4);
            }
        });

        button = findViewById(R.id.startbutton5);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startPosition(5);
            }
        });

        button = findViewById(R.id.startbutton6);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startPosition(6);
            }
        });

        button = findViewById(R.id.didntshowyesbutton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                didNotShow();
            }
        });

        button = findViewById(R.id.PreAutoContinueButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openAutoPage();
            }
        });

        button = findViewById(R.id.button_preAutoPrevious);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                previous();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(mOnlineStatusReceiver, new IntentFilter(BluetoothComm.ONLINE_STATUS_UPDATED_FILTER));

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(_db);
        CyberScouterMatchScouting csm = null;
        if (null != cfg) {
            csm = CyberScouterMatchScouting.getCurrentMatch(_db, TeamMap.getNumberForTeam(cfg.getAlliance_station()));
        }

        if (null != csm) {
            TextView tv = findViewById(R.id.textView_preAutoMatch);
            tv.setText(getString(R.string.tagMatch, csm.getMatchNo()));
            tv = findViewById(R.id.textView_preAutoTeam);
            tv.setText(getString(R.string.tagTeam, csm.getTeam()));

            String[] lColumns = {CyberScouterContract.MatchScouting.COLUMN_NAME_AUTODIDNOTSHOW};
            Integer[] lval = {0};
            try {
                CyberScouterMatchScouting.updateMatchMetric(_db, lColumns, lval, cfg);
                csm = CyberScouterMatchScouting.getCurrentMatch(_db, TeamMap.getNumberForTeam(cfg.getAlliance_station()));
            } catch(Exception e) {
                e.printStackTrace();
            }
            _startPos = csm.getAutoStartPos();
            button = findViewById(R.id.PreAutoContinueButton);
            if(0 < _startPos) {
                FakeRadioGroup.buttonDisplay(this, _startPos - 1, startPositionButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
                button.setEnabled(true);
            } else {
                button.setEnabled(false);
            }
        }
    }

    @Override
    protected void onDestroy(){
        unregisterReceiver(mOnlineStatusReceiver);
        super.onDestroy();
    }

    private void didNotShow() {
        _didNotShow = 1;
        updatePreAutoData();
        Intent intent = new Intent(this, SubmitPage.class);
        startActivity(intent);
    }

    private void updatePreAutoData() {
        CyberScouterConfig cfg = CyberScouterConfig.getConfig(_db);
        try {
            Integer[] _lValues = {_startPos, _didNotShow};
            CyberScouterMatchScouting.updateMatchMetric(_db, _lColumns, _lValues, cfg);
        } catch(Exception e) {
            e.printStackTrace();
            MessageBox.showMessageBox(this, "Update Error",
                    "PreAutoPage.updatePreAutoData", "SQLite update failed!\n "+e.getMessage());
        }
    }

    public void startPosition(int val) {
        FakeRadioGroup.buttonPressed(this, val - 1, startPositionButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
        _startPos = val;
        button = findViewById(R.id.PreAutoContinueButton);
        button.setEnabled(true);
    }

    private void updateStatusIndicator(int color) {
        ImageView iv = findViewById(R.id.imageView_btIndicator);
        BluetoothComm.updateStatusIndicator(iv, color);
        currentCommStatusColor = color;
    }

    private void openAutoPage() {
        updatePreAutoData();

        Intent intent = new Intent(this, AutoPage.class);
        intent.putExtra("commstatuscolor", currentCommStatusColor);
        startActivity(intent);
    }

    private void previous(){
        updatePreAutoData();
        this.finish();
    }

}
