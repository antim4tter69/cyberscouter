package com.frcteam195.cyberscouter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class WordCloudActivity extends AppCompatActivity {
//    private String[] words = {"Fast", "Slow", "Efficient", "Efficient Ground Pickup", "NINO",
//            "Good", "Bad", "Good Leveler", "Inefficient Ground Pickup", "Penalty Prone", "Strong",
//            "Weak", "Unaffected by Defense", "Affected by Defense", "Fast Climb", "Bad Climb", "Accurate",
//            "Accurate Longshot", "Good w/ Wheel", "Bad w/ Wheel"};

    private String[] _words;
    private Integer[] _wordIDs;

    private CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
    private SQLiteDatabase _db = null;

    private int currentCommStatusColor = Color.LTGRAY;

    BroadcastReceiver mOnlineStatusReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int color = intent.getIntExtra("onlinestatus", Color.RED);
            updateStatusIndicator(color);
        }
    };

    BroadcastReceiver mWordsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String ret = intent.getStringExtra("cyberscouterwords");
            updateWords(ret);
        }
    };

    BroadcastReceiver mMatchesL2Receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String ret = intent.getStringExtra("cyberscoutermatchesl2");
            updateMatchesL2Local(ret);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_cloud);

        registerReceiver(mOnlineStatusReceiver, new IntentFilter(BluetoothComm.ONLINE_STATUS_UPDATED_FILTER));
        registerReceiver(mWordsReceiver, new IntentFilter(CyberScouterWords.WORDS_UPDATED_FILTER));
        registerReceiver(mMatchesL2Receiver, new IntentFilter(CyberScouterMatchScoutingL2.MATCH_SCOUTING_L2_UPDATED_FILTER));

        _db = mDbHelper.getWritableDatabase();

        Button button = findViewById(R.id.button_wcSubmit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CyberScouterWordCloud.setDoneScouting(_db);
                view.setEnabled(false);
            }
        });
        button.setEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String csw_str = CyberScouterWords.getWordsRemote(this);
        if (null != csw_str) {
            updateWords(csw_str);
        }

        CyberScouterConfig cfg = CyberScouterConfig.getConfig(_db);
        if (null != cfg) {
            String csms_str = CyberScouterMatchScoutingL2.getMatchesL2Remote(this, cfg.getEvent_id());
            if (null != csms_str) {
                updateMatchesL2Local(csms_str);
            }
        }

    }

    @Override
    protected void onDestroy() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        mDbHelper.close();
        unregisterReceiver(mOnlineStatusReceiver);
        unregisterReceiver(mWordsReceiver);
        unregisterReceiver(mMatchesL2Receiver);
        super.onDestroy();
    }

    protected void updateMatchesL2Local(String json) {
        try {
            CyberScouterConfig cfg = CyberScouterConfig.getConfig(_db);
            if (!json.equalsIgnoreCase("skip")) {
                if (json.equalsIgnoreCase("fetch")) {
                    json = CyberScouterMatchScoutingL2.getWebResponse();
                }
                CyberScouterMatchScoutingL2.deleteOldMatches(_db, cfg.getEvent_id());
                CyberScouterMatchScoutingL2.mergeMatches(_db, json);
            }
            populatePage();

        } catch (Exception e) {
            MessageBox.showMessageBox(this, "Fetch Match Information Failed", "updateMatchesLocal",
                    String.format("Attempt to fetch match info and merge locally failed!\n%s", e.getMessage()));
            e.printStackTrace();
        }
    }

    private void populatePage() {
        CyberScouterConfig cfg = CyberScouterConfig.getConfig(_db);
        CyberScouterMatchScoutingL2 csm = CyberScouterMatchScoutingL2.getCurrentMatch(_db, cfg.getAlliance_station_id());
        if (null != csm) {
            TextView tv = findViewById(R.id.textView_wcMatch);
            tv.setText(getString(R.string.tagMatch, csm.getMatchNo()));
            tv = findViewById(R.id.textView_wcTeam);
            tv.setText(getString(R.string.tagTeam, csm.getTeamRed()));
        }

        if (null != _words) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.word_cloud_text_items, _words);
            GridView gv = findViewById(R.id.gridView_words);
            gv.setNumColumns(4);
            gv.setAdapter(adapter);
            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    CyberScouterConfig cfg = CyberScouterConfig.getConfig(_db);
                    if (null != cfg) {
                        CyberScouterMatchScoutingL2 csm = CyberScouterMatchScoutingL2.getCurrentMatch(_db, TeamMap.getNumberForTeam(cfg.getAlliance_station()));
                        if (null != csm) {
                            CyberScouterWordCloud cswc = new CyberScouterWordCloud();
                            cswc.setEventID(cfg.getEvent_id());
                            cswc.setMatchID(csm.getMatchID());
                            cswc.setMatchScoutingID(csm.getMatchScoutingL2ID());
                            cswc.setSeq(0);
                            cswc.setTeam(csm.getTeamRed());
                            cswc.setWordID(_wordIDs[i]);
                            cswc.putWordCloud(_db);
                        }
                    }
                    view.setBackgroundColor(Color.GREEN);
                }
            });
        }
    }

    private void updateStatusIndicator(int color) {
        ImageView iv = findViewById(R.id.imageView_btIndicator);
        BluetoothComm.updateStatusIndicator(iv, color);
    }

    private void updateWords(String json) {
        try {
            if (2 < json.length()) {
                JSONArray ja = new JSONArray(json);
                _words = new String[ja.length()];
                _wordIDs = new Integer[ja.length()];
                for (int i = 0; i < ja.length(); ++i) {
                    JSONObject jo = ja.getJSONObject(i);
                    _words[i] = jo.getString("Word");
                    _wordIDs[i] = jo.getInt("WordID");
                }
                Button button = findViewById(R.id.button_wcSubmit);
                button.setEnabled(true);
                populatePage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
