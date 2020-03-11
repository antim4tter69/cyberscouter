package com.frcteam195.cyberscouter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WordCloudActivity extends AppCompatActivity {
    static private AppCompatActivity _activity;

    static protected AppCompatActivity getActivity() {
        return (_activity);
    }

    private CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
    private SQLiteDatabase _db = null;

    private ViewPager mPager;

    private Handler mFetchHandler;
    private Thread fetcherThread;
    private final int START_PROGRESS = 0;
    private final int FETCH_WORDS = 1;
    private final int FETCH_MATCHES = 2;


    private int _currentPos;

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

        registerReceiver(mWordsReceiver, new IntentFilter(CyberScouterWords.WORDS_UPDATED_FILTER));
        registerReceiver(mMatchesL2Receiver, new IntentFilter(CyberScouterMatchScoutingL2.MATCH_SCOUTING_L2_UPDATED_FILTER));

        mPager = findViewById(R.id.viewPager_wcPager);

        _db = mDbHelper.getWritableDatabase();

        fetcherThread = new Thread(new RemoteFetcher());


        mFetchHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case START_PROGRESS:
                        showProgress();
                        break;
                    case FETCH_WORDS:
                        fetchWords();
                        break;
                    case FETCH_MATCHES:
                        fetchMatches();
                        break;
                }
            }
        };

        if (null == fetcherThread) {
            fetcherThread = new Thread(new RemoteFetcher());
        }
        if (!fetcherThread.isAlive()) {
            fetcherThread.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private class RemoteFetcher implements Runnable {

        @Override
        public void run() {
            Message msg = new Message();
            msg.what = START_PROGRESS;
            mFetchHandler.sendMessage(msg);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
            Message msg2 = new Message();
            msg2.what = FETCH_WORDS;
            mFetchHandler.sendMessage(msg2);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
            Message msg3 = new Message();
            msg3.what = FETCH_MATCHES;
            mFetchHandler.sendMessage(msg3);
        }
    }

    private void showProgress() {
//        ProgressBar pb = findViewById(R.id.progressBar_scoutingDataAccess);
//        pb.setVisibility(View.VISIBLE);
    }

    private void fetchWords() {
        String csw_str = CyberScouterWords.getWordsRemote(this);
        if (null != csw_str) {
            updateWords(csw_str);
        }
    }

    private void fetchMatches() {
        CyberScouterConfig cfg = CyberScouterConfig.getConfig(_db);
        if (null != cfg) {
            String csms_str = CyberScouterMatchScoutingL2.getMatchesL2Remote(this, _db, cfg.getEvent_id());
            if (null != csms_str) {
                updateMatchesL2Local(csms_str);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        fetcherThread = null;
    }

    @Override
    protected void onDestroy() {
        if(null != mDbHelper) {
            mDbHelper.close();
        }
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
                WordCloudFragmentAdapter _wcfAdapter = new WordCloudFragmentAdapter(getSupportFragmentManager());
                PagerAdapter _pagerAdapter = _wcfAdapter;
                mPager.setAdapter(_pagerAdapter);
                mPager.setCurrentItem(_currentPos);
            }

        } catch (Exception e) {
            MessageBox.showMessageBox(WordCloudActivity.getActivity(), "Fetch Match Information Failed", "updateMatchesLocal",
                    String.format("Attempt to fetch match info and merge locally failed!\n%s", e.getMessage()));
            e.printStackTrace();
        }
    }

    private void updateWords(String json) {
        try {
            if (2 < json.length()) {
                CyberScouterWords.setWordsLocal(_db, json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
