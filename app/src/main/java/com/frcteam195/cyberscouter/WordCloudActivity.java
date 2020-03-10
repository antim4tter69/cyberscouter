package com.frcteam195.cyberscouter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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
    static private AppCompatActivity _activity;
    static protected AppCompatActivity getActivity() { return(_activity);}

    private String[] _words;
    private Integer[] _wordIDs;

    private CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(WordCloudActivity.getActivity());
    private SQLiteDatabase _db = null;

    private ViewPager mPager;
    private PagerAdapter _pagerAdapter;
    private WordCloudFragmentAdapter _wcfAdapter;

    private int _currentPos;

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

        _activity = this;

        registerReceiver(mOnlineStatusReceiver, new IntentFilter(BluetoothComm.ONLINE_STATUS_UPDATED_FILTER));
        registerReceiver(mWordsReceiver, new IntentFilter(CyberScouterWords.WORDS_UPDATED_FILTER));
        registerReceiver(mMatchesL2Receiver, new IntentFilter(CyberScouterMatchScoutingL2.MATCH_SCOUTING_L2_UPDATED_FILTER));

        mPager = (ViewPager) findViewById(R.id.viewPager_wcPager);
    }

    @Override
    protected void onResume() {
        super.onResume();

        _wcfAdapter = new WordCloudFragmentAdapter(getSupportFragmentManager());
        _wcfAdapter.setItems(null);
        _pagerAdapter = _wcfAdapter;
        mPager.setAdapter(_pagerAdapter);
        mPager.setCurrentItem(_currentPos);
    }

    @Override
    protected void onDestroy() {
        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(WordCloudActivity.getActivity());
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

        } catch (Exception e) {
            MessageBox.showMessageBox(WordCloudActivity.getActivity(), "Fetch Match Information Failed", "updateMatchesLocal",
                    String.format("Attempt to fetch match info and merge locally failed!\n%s", e.getMessage()));
            e.printStackTrace();
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
