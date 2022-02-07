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
import android.view.View;

/* + or - block briefly turns alliance color when tapped
Word background turns light gray, red, yellow, green, blue, or purple depending on how many times the + or - button has been tapped
White=0
Light Gray=-3
Red=-2
Yellow=-1
Green=+1
Blue=+2
Purple=+3
*/

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

    private Integer fastCount, driverCount, aggressiveCount, blockCount, evasiveCount, sturdyCount, powerfulCount;
    //String[] _lColumns = {CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLHIGH,
            //CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLLOW,
            //CyberScouterContract.MatchScouting.COLUMN_NAME_TELEBALLMISS};


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

        //mPager = findViewById(R.id.viewPager_wcPager);

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

      /* button = findViewById(R.id.FastMinusTeam1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FastMinusTeam1Clicked();
            }
        });
        button = findViewById(R.id.fastMinusTeam2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FastMinusTeam2Clicked();
            }
        });
        button = findViewById(R.id.fastMinusTeam3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FastMinusTeam3Clicked();
            }
        });
        button = findViewById(R.id.fastPlusTeam);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fastPlusTeamClicked();
            }
        });
        button = findViewById(R.id.fastPlusTeam2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fastPlusTeam2Clicked();
            }
        });
        button = findViewById(R.id.FastPlusTeam3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FastPlusTeam3Clicked();
            }
        }); */

        /* button = findViewById(R.id.GoodDriverMinusTeam1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodDriverMinusTeam1Clicked();
            }
        });
        button = findViewById(R.id.goodDriverMinusTeam2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodDriverMinusTeam2Clicked();
            }
        });
        button = findViewById(R.id.goodDriverMinusTeam3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodDriverMinusTeam3Clicked();
            }
        });
        button = findViewById(R.id.goodDriverPlusTeam);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodDriverPlusTeamClicked();
            }
        });
        button = findViewById(R.id.goodDriverPlusTeam2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodDriverPlusTeam2Clicked();
            }
        });
        button = findViewById(R.id.GoodDriverPlusTeam3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodDriverPlusTeam3Clicked();
            }
        }); */

        /* button = findViewById(R.id.AggressiveMinusTeam1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AggressiveMinusTeam1Clicked();
            }
        });
        button = findViewById(R.id.aggressiveMinusTeam2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aggressiveMinusTeam2Clicked();
            }
        });
        button = findViewById(R.id.aggressiveMinusTeam3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aggressiveMinusTeam3Clicked();
            }
        });
        button = findViewById(R.id.aggressivePlusTeam);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aggressivePlusTeamClicked();
            }
        });
        button = findViewById(R.id.aggressivePlusTeam2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aggressivePlusTeam2Clicked();
            }
        });
        button = findViewById(R.id.AggressivePlusTeam3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AggressivePlusTeam3Clicked();
            }
        }); */

        /* button = findViewById(R.id.BlockMinusTeam1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlockMinusTeam1Clicked();
            }
        });
        button = findViewById(R.id.blockMinusTeam2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blockMinusTeam2Clicked();
            }
        });
        button = findViewById(R.id.blockMinusTeam3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blockMinusTeam3Clicked();
            }
        });
        button = findViewById(R.id.blockPlusTeam);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blockPlusTeamClicked();
            }
        });
        button = findViewById(R.id.blockPlusTeam2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blockPlusTeam2Clicked();
            }
        });
        button = findViewById(R.id.BlockPlusTeam3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlockPlusTeam3Clicked();
            }
        }); */

        /* button = findViewById(R.id.EvasiveMinusTeam1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EvasiveMinusTeam1Clicked();
            }
        });
        button = findViewById(R.id.evasiveMinusTeam2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evasiveMinusTeam2Clicked();
            }
        });
        button = findViewById(R.id.evasiveMinusTeam3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evasiveMinusTeam3Clicked();
            }
        });
        button = findViewById(R.id.evasivePlusTeam);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evasivePlusTeamClicked();
            }
        });
        button = findViewById(R.id.evasivePlusTeam2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evasivePlusTeam2Clicked();
            }
        });
        button = findViewById(R.id.EvasivePlusTeam3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EvasivePlusTeam3Clicked();
            }
        }); */

        /* button = findViewById(R.id.SturdyMinusTeam1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SturdyMinusTeam1Clicked();
            }
        });
        button = findViewById(R.id.sturdyMinusTeam2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sturdyMinusTeam2Clicked();
            }
        });
        button = findViewById(R.id.sturdyMinusTeam3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sturdyMinusTeam3Clicked();
            }
        });
        button = findViewById(R.id.sturdyPlusTeam);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sturdyPlusTeamClicked();
            }
        });
        button = findViewById(R.id.sturdyPlusTeam2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sturdyPlusTeam2Clicked();
            }
        });
        button = findViewById(R.id.SturdyPlusTeam3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SturdyPlusTeam3Clicked();
            }
        }); */

        /* button = findViewById(R.id.PowerfulMinusTeam1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PowerfulMinusTeam1Clicked();
            }
        });
        button = findViewById(R.id.powerfulMinusTeam2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                powerfulMinusTeam2Clicked();
            }
        });
        button = findViewById(R.id.powerfulMinusTeam3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                powerfulMinusTeam3Clicked();
            }
        });
        button = findViewById(R.id.powerfulPlusTeam);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                powerfulPlusTeamClicked();
            }
        });
        button = findViewById(R.id.powerfulPlusTeam2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                powerfulPlusTeam2Clicked();
            }
        });
        button = findViewById(R.id.powerfulPlusTeam3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PowerfulPlusTeam3Clicked();
            }
        }); */
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
