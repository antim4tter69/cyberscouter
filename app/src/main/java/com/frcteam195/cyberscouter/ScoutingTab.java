package com.frcteam195.cyberscouter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoutingTab extends Fragment {
    private View _view;
    private String[] user_array;
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

    private void commitTeam(int _val) {
        try {
            CyberScouterTeams.updateTeamMetric(_db, CyberScouterContract.Teams.COLUMN_NAME_DONE_SCOUTING, _val, PitScoutingActivity.getCurrentTeam());
            Button button = _view.findViewById(R.id.button_scoutingTabCommit);
            switch(_val) {
                case 0:
                    button.setEnabled(true);
                    break;
                case 1:
                    button.setEnabled(false);
                    break;
                default:
                    button.setEnabled(true);
            }
        } catch (Exception e) {
            MessageBox.showMessageBox(getActivity(), "Pit Scouting Commit Failed", "ScoutingTab.commitTeam",
                    String.format("Attempt to commit pit scouting statistics for team %d failed!", PitScoutingActivity.getCurrentTeam()));
        }
    }

    public void teamFetchCompleted() {
        team_array = CyberScouterTeams.getTeamNumbers(_db);
        if(null != team_array) {
            populateView();
        }
    }

    private void populateView() {
        Button button = _view.findViewById(R.id.button_scoutingTabCommit);
        Spinner teams_spinner = _view.findViewById(R.id.spinner_teams);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(),
                R.layout.spinner_text_items, team_array);
        teams_spinner.setAdapter(adapter1);
        teams_spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                PitScoutingActivity.setCurrentTeam(Integer.valueOf(team_array[position]));
                CyberScouterTeams cst = CyberScouterTeams.getCurrentTeam(_db, PitScoutingActivity.getCurrentTeam());
                Button button = _view.findViewById(R.id.button_scoutingTabCommit);
                if (0 == cst.getDoneScouting()) {
                    button.setEnabled(true);
                } else {
                    button.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

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
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                commitTeam(0);
                return true;
            }
        });
    }
}
