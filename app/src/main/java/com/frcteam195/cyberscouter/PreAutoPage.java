package com.frcteam195.cyberscouter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PreAutoPage extends AppCompatActivity {
    private Button button;
    private final int[] startPositionButtons = {R.id.startbutton1, R.id.startbutton2, R.id.startbutton3, R.id.startbutton4, R.id.startbutton5, R.id.startbutton6};
    private final int[] didNotShowButtons = {R.id.didntshowyesbutton, R.id.didntshownobutton};
    private int defaultButtonTextColor;
    private final int SELECTED_BUTTON_TEXT_COLOR = Color.GREEN;

    private int field_orientation;
    private int currentCommStatusColor;
    private final CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
    private SQLiteDatabase _db;

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

        Intent intent = getIntent();
        field_orientation = intent.getIntExtra("field_orientation", 0);
        currentCommStatusColor = intent.getIntExtra("commstatuscolor", Color.LTGRAY);
        updateStatusIndicator(currentCommStatusColor);

        _db = mDbHelper.getWritableDatabase();

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

        button = findViewById(R.id.didntshownobutton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                didntShow(0);
            }
        });

        button = findViewById(R.id.didntshowyesbutton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                didntShow(1);
            }
        });

        button = findViewById(R.id.AutoContinueButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openAutoPage();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(mOnlineStatusReceiver, new IntentFilter(BluetoothComm.ONLINE_STATUS_UPDATED_FILTER));

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(_db);

        CyberScouterMatchScouting csm = null;

        if (null != cfg)
            csm = CyberScouterMatchScouting.getCurrentMatch(_db, TeamMap.getNumberForTeam(cfg.getAlliance_station()));

        if (null != csm) {
            TextView tv = findViewById(R.id.textView_preAutoMatch);
            tv.setText(getString(R.string.tagMatch, csm.getMatchNo()));
            tv = findViewById(R.id.textView_preAutoTeam);
            tv.setText(getString(R.string.tagTeam, csm.getTeam()));

        }

    }

    public void didntShow(int val) {
        FakeRadioGroup.buttonPressed(this, 0, didNotShowButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }

    public void startPosition(int val) {
        FakeRadioGroup.buttonPressed(this, val, startPositionButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOSTARTPOS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }

    private void updateStatusIndicator(int color) {
        ImageView iv = findViewById(R.id.imageView_btIndicator);
        BluetoothComm.updateStatusIndicator(iv, color);
    }

    public void openAutoPage() {
        CyberScouterConfig cfg = CyberScouterConfig.getConfig(_db);

        Intent intent = new Intent(this, AutoPage.class);
        intent.putExtra("field_orientation", field_orientation);
        intent.putExtra("commstatuscolor", currentCommStatusColor);
        startActivity(intent);

    }

}
