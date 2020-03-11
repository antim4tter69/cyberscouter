package com.frcteam195.cyberscouter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class WordCloudFragment extends Fragment {
//    private String[] words = {"Fast", "Slow", "Efficient", "Efficient Ground Pickup", "NINO",
//            "Good", "Bad", "Good Leveler", "Inefficient Ground Pickup", "Penalty Prone", "Strong",
//            "Weak", "Unaffected by Defense", "Affected by Defense", "Fast Climb", "Bad Climb", "Accurate",
//            "Accurate Longshot", "Good w/ Wheel", "Bad w/ Wheel"};

    private String[] _words;
    private Integer[] _wordIDs;
    private boolean[] _wordSelected;

    private CyberScouterMatchScoutingL2 _csmsl2;
    private int _pagePosition;
    private String _team;

    private CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(WordCloudActivity.getActivity());
    private SQLiteDatabase _db = null;

    private View _view;

    BroadcastReceiver mOnlineStatusReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int color = intent.getIntExtra("onlinestatus", Color.RED);
            updateStatusIndicator(color);
        }
    };

    public WordCloudFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_word_cloud, container, false);
        _view = rootView;

        Bundle bundle = this.getArguments();
        _pagePosition = bundle.getInt("position");

        _db = mDbHelper.getWritableDatabase();

        Button button = _view.findViewById(R.id.button_wcSubmit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CyberScouterWordCloud.setDoneScouting(_db);
                view.setEnabled(false);
            }
        });
        button.setEnabled(false);

        getActivity().registerReceiver(mOnlineStatusReceiver, new IntentFilter(BluetoothComm.ONLINE_STATUS_UPDATED_FILTER));

        CyberScouterWords[] cswa = CyberScouterWords.getLocalWords(_db);
        if (null != cswa) {
            updateWords(cswa);
        }

        return (rootView);
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(mOnlineStatusReceiver);
        if (null != mDbHelper) {
            mDbHelper.close();
        }
        super.onDestroy();
    }

    private void populatePage() {
        CyberScouterConfig cfg = CyberScouterConfig.getConfig(_db);
        CyberScouterMatchScoutingL2 csm = CyberScouterMatchScoutingL2.getCurrentMatch(_db, cfg.getAlliance_station_id());
        if (null != csm) {
            TextView tv = _view.findViewById(R.id.textView_wcMatch);
            tv.setText(getString(R.string.tagMatch, csm.getMatchNo()));
            tv = _view.findViewById(R.id.textView_wcTeam);
            switch (_pagePosition) {
                case 0:
                    _team = csm.getTeamRed();
                    tv.setTextColor(Color.RED);
                    break;
                case 1:
                    _team = csm.getTeamBlue();
                    tv.setTextColor(Color.BLUE);
            }
            tv.setText(getString(R.string.tagTeam, _team));
        }

        if (null != _words) {
            for (int i = 0; i < _wordIDs.length; ++i) {
                if (null != CyberScouterWordCloud.getLocalWordCloud(_db, csm.getMatchScoutingL2ID(), _team, _wordIDs[i])) {
                    _wordSelected[i] = true;
                } else {
                    _wordSelected[i] = false;
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(WordCloudActivity.getActivity(), R.layout.word_cloud_text_items, _words) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    if(_wordSelected[position]) {
                        view.setBackgroundColor(Color.GREEN);
                    } else {
                        view.setBackgroundColor(Color.LTGRAY);
                    }
                    return(view);
                }
            };
            GridView gv = _view.findViewById(R.id.gridView_words);
            gv.setNumColumns(4);
            gv.setAdapter(adapter);
            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    CyberScouterConfig cfg = CyberScouterConfig.getConfig(_db);
                    if (null != cfg) {
                        CyberScouterMatchScoutingL2 csm = CyberScouterMatchScoutingL2.getCurrentMatch(_db, cfg.getAlliance_station_id());
                        if (_wordSelected[i]) {
                            int deleted = CyberScouterWordCloud.deleteLocalWordCloud(_db, csm.getMatchScoutingL2ID(), _team, _wordIDs[i]);
                            System.out.println(String.format("WordCloud rows deleted %d", deleted));
                            _wordSelected[i] = false;
                            view.setBackgroundColor(Color.LTGRAY);
                        } else {
                            CyberScouterWordCloud cswc = new CyberScouterWordCloud();
                            cswc.setEventID(cfg.getEvent_id());
                            cswc.setMatchID(csm.getMatchID());
                            cswc.setMatchScoutingID(csm.getMatchScoutingL2ID());
                            cswc.setSeq(0);
                            cswc.setTeam(_team);
                            cswc.setWordID(_wordIDs[i]);
                            cswc.putWordCloud(_db);
                            _wordSelected[i] = true;
                            view.setBackgroundColor(Color.GREEN);
                        }
                    }
                }
            });
        }

    }

    private void updateWords(CyberScouterWords[] cswa) {
        try {
            if (null != cswa) {
                _words = new String[cswa.length];
                _wordIDs = new Integer[cswa.length];
                _wordSelected = new boolean[cswa.length];
                for (int i = 0; i < cswa.length; ++i) {
                    _words[i] = cswa[i].getWord();
                    _wordIDs[i] = cswa[i].getWordID();
                    _wordSelected[i] = false;
                }
                Button button = _view.findViewById(R.id.button_wcSubmit);
                button.setEnabled(true);
                populatePage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStatusIndicator(int color) {
        ImageView iv = _view.findViewById(R.id.imageView_btIndicator);
        BluetoothComm.updateStatusIndicator(iv, color);
    }

}
