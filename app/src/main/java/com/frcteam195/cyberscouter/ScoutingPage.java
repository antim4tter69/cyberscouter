package com.frcteam195.cyberscouter;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ScoutingPage extends AppCompatActivity implements NamePickerDialog.NamePickerDialogListener {
    private int FIELD_ORIENTATION_RIGHT = 0;
    private int FIELD_ORIENTATION_LEFT = 1;
    private int field_orientation = FIELD_ORIENTATION_LEFT;

    private SQLiteDatabase db = null;

    BroadcastReceiver mOnlineStatusReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int color = intent.getIntExtra("onlinestatus", Color.RED);
            updateStatusIndicator(color);
        }
    };

    private int currentCommStatusColor = Color.LTGRAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button button;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting_page);

        button = findViewById(R.id.Button_Start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAuto();
            }
        });

        Button npbutton = findViewById(R.id.Button_NamePicker);
        npbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNamePickerPage();

            }
        });

        button = findViewById(R.id.Button_Return);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMainMenu();
            }
        });

        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        db = mDbHelper.getWritableDatabase();

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
        if (CyberScouterConfig.UNKNOWN_USER_IDX != cfg.getUser_id()) {
            npbutton.setText(cfg.getUsername());
        } else {
            npbutton.setText("Select your name");
        }

        ImageView iv = findViewById(R.id.imageView2);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipFieldOrientation();
            }
        });
        iv.setEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        currentCommStatusColor = intent.getIntExtra("commstatuscolor", Color.LTGRAY);
        updateStatusIndicator(currentCommStatusColor);

        registerReceiver(mOnlineStatusReceiver, new IntentFilter(BluetoothComm.ONLINE_STATUS_UPDATED_FILTER));

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        CyberScouterMatchScouting csm = null;

        if (null != cfg)
            csm = CyberScouterMatchScouting.getCurrentMatch(db, TeamMap.getNumberForTeam(cfg.getAlliance_station()));

        if (null != csm) {
            TextView tv = findViewById(R.id.textView7);
            tv.setText(getString(R.string.tagMatch, csm.getMatchNo()));
            TextView tvtn = findViewById(R.id.textView_teamNumber);
            String currentMatchTeam = csm.getTeam();
            tvtn.setText(getString(R.string.tagTeam, currentMatchTeam));
            CyberScouterMatchScouting[] csma = CyberScouterMatchScouting.getCurrentMatchAllTeams(db, csm.getTeamMatchNo(), csm.getMatchID());
            if (null != csma && 6 == csma.length) {
                String tmpTeam = null;
                tv = findViewById(R.id.textView20);
                tv.setText(csma[0].getTeam());
                if (csma[0].getTeam().equals(currentMatchTeam)) {
                    tvtn.setTextColor(Color.RED);
                }
                tv = findViewById(R.id.textView21);
                tv.setText(csma[1].getTeam());
                if (csma[1].getTeam().equals(currentMatchTeam)) {
                    tvtn.setTextColor(Color.RED);
                }
                tv = findViewById(R.id.textView22);
                tv.setText(csma[2].getTeam());
                if (csma[2].getTeam().equals(currentMatchTeam)) {
                    tvtn.setTextColor(Color.RED);
                }
                tv = findViewById(R.id.textView35);
                tv.setText(csma[3].getTeam());
                if (csma[3].getTeam().equals(currentMatchTeam)) {
                    tvtn.setTextColor(Color.BLUE);
                }
                tv = findViewById(R.id.textView27);
                tv.setText(csma[4].getTeam());
                if (csma[4].getTeam().equals(currentMatchTeam)) {
                    tvtn.setTextColor(Color.BLUE);
                }
                tv = findViewById(R.id.textView26);
                tv.setText(csma[5].getTeam());
                if (csma[5].getTeam().equals(currentMatchTeam)) {
                    tvtn.setTextColor(Color.BLUE);
                }
            }
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        mDbHelper.close();
        unregisterReceiver(mOnlineStatusReceiver);
        super.onDestroy();
    }


    public void openAuto() {
        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        if (null == cfg || (CyberScouterConfig.UNKNOWN_USER_IDX == cfg.getUser_id())) {
            FragmentManager fm = getSupportFragmentManager();
            NamePickerDialog npd = new NamePickerDialog();
            npd.show(fm, "namepicker");
        } else {
            Intent intent = new Intent(this, AutoPage.class);
            intent.putExtra("field_orientation", field_orientation);
            startActivity(intent);

        }
    }

    public void openNamePickerPage() {
        FragmentManager fm = getSupportFragmentManager();
        NamePickerDialog npd = new NamePickerDialog();
        npd.show(fm, "namepicker");
    }

    public void returnToMainMenu() {
        Intent intent = new Intent(this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        finish();
    }

    public void setUsername(String val, int idx) {
        try {
            ContentValues values = new ContentValues();
            values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_USERNAME, val);
            values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_USERID, idx);
            int count = db.update(
                    CyberScouterContract.ConfigEntry.TABLE_NAME,
                    values,
                    null,
                    null);
        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        }
    }

    private void flipFieldOrientation() {
        ImageButton im1, im2;
        im1 = findViewById(R.id.imageButton);
        im2 = findViewById(R.id.imageButton2);

        Drawable d1 = im1.getDrawable();
        Drawable d2 = im2.getDrawable();

        im1.setImageDrawable(d2);
        im2.setImageDrawable(d1);

        ImageView iv = findViewById(R.id.imageView2);

        field_orientation = field_orientation == FIELD_ORIENTATION_LEFT ? FIELD_ORIENTATION_RIGHT : FIELD_ORIENTATION_LEFT;

        if(FIELD_ORIENTATION_LEFT == field_orientation) {
            iv.setImageResource(R.drawable.field_2020);
        } else {
            iv.setImageResource(R.drawable.field_2020_flipped);
        }

//        try {
//            ContentValues values = new ContentValues();
//            values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_FIELD_REDLEFT, val);
//            int count = db.update(
//                    CyberScouterContract.ConfigEntry.TABLE_NAME,
//                    values,
//                    null,
//                    null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw (e);
//        }

    }

    @Override
    public void onNameSelected(String name, int idx) {
        Button button = findViewById(R.id.Button_NamePicker);
        button.setText(name);
        setUsername(name, idx);
    }

    private void updateStatusIndicator(int color) {
        ImageView iv = findViewById(R.id.imageView_btIndicator);
        BluetoothComm.updateStatusIndicator(iv, color);
    }
}
