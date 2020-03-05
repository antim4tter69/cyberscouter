package com.frcteam195.cyberscouter;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.Locale;

public class ScoutingTab extends Fragment {
    private View _view;
//    private String[] user_array;
    private String[] team_array;

    private CyberScouterDbHelper mDbHelper;
    SQLiteDatabase _db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scouting, container, false);
        _view = view;

        mDbHelper = new CyberScouterDbHelper(getActivity());
        _db = mDbHelper.getWritableDatabase();

        Button button = _view.findViewById(R.id.button_scoutingTabCommit);
        //        user_array = CyberScouterUsers.getUserNames(_db);

        team_array = CyberScouterTeams.getTeamNumbers(_db);
        if (null != team_array) {
            populateView();
        } else {
            button.setEnabled(false);
        }

        return view;
    }

    @Override
    public void onDestroy() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
        super.onDestroy();
    }


    private void commitTeam(int _val) {
        try {
            CyberScouterTeams.updateTeamMetric(_db, CyberScouterContract.Teams.COLUMN_NAME_DONE_SCOUTING, _val, PitScoutingActivity.getCurrentTeam());
        } catch (Exception e) {
            MessageBox.showMessageBox(getActivity(), "Pit Scouting Commit Failed", "ScoutingTab.commitTeam",
                    String.format(Locale.getDefault(), "Attempt to commit pit scouting statistics for team %d failed!", PitScoutingActivity.getCurrentTeam()));
            e.printStackTrace();
        }

        Button button = _view.findViewById(R.id.button_scoutingTabCommit);
        switch (_val) {
            case 0:
                button.setEnabled(true);
                Activity acty = getActivity();
                if(null != acty) {
                    TabLayout tabs = acty.findViewById(R.id.tabs);
                    LinearLayout tabstrip = ((LinearLayout) tabs.getChildAt(0));
                    if (tabstrip != null) {
                        for (int i = 1; i < tabstrip.getChildCount(); ++i) {
                            tabstrip.getChildAt(i).setEnabled(true);
                        }
                    }
                }
                break;
            case 1:
                button.setEnabled(false);
                acty = getActivity();
                if(null != acty) {
                    TabLayout tabs = acty.findViewById(R.id.tabs);
                    LinearLayout tabstrip = ((LinearLayout) tabs.getChildAt(0));
                    if (tabstrip != null) {
                        for (int i = 1; i < tabstrip.getChildCount(); ++i) {
                            tabstrip.getChildAt(i).setEnabled(false);
                        }
                    }
                }
                break;
            default:
                button.setEnabled(true);
        }
    }

    public void teamFetchCompleted() {
        team_array = CyberScouterTeams.getTeamNumbers(_db);
        if (null != team_array) {
            populateView();
        }
    }

    private void populateView() {
        Button button = _view.findViewById(R.id.button_scoutingTabCommit);
        Spinner teams_spinner = _view.findViewById(R.id.spinner_teams);
        Activity acty = getActivity();
        if(null != acty) {
            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(acty,
                    R.layout.spinner_text_items, team_array);
            teams_spinner.setAdapter(adapter1);
            teams_spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    PitScoutingActivity.setCurrentTeam(Integer.parseInt(team_array[position]));
                    CyberScouterTeams cst = CyberScouterTeams.getCurrentTeam(_db, PitScoutingActivity.getCurrentTeam());
                    Button button = _view.findViewById(R.id.button_scoutingTabCommit);
                    if (0 == cst.getDoneScouting()) {
                        button.setEnabled(true);
                        PitScoutingActivity.setCommitDisabled(false);
                    } else {
                        button.setEnabled(false);
                        PitScoutingActivity.setCommitDisabled(true);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
            teams_spinner.setOnLongClickListener(new Spinner.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    commitTeam(0);
                    return true;
                }
            });
            PitScoutingActivity.setCurrentTeam(Integer.parseInt(team_array[teams_spinner.getSelectedItemPosition()]));

//        Spinner names = view.findViewById(R.id.spinner_fragmentScoutingNames);
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(),
//                R.layout.spinner_text_items, user_array);
//        names.setAdapter(adapter2);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    commitTeam(1);
                }
            });
        }
    }
}
