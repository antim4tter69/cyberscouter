package com.frcteam195.cyberscouter;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoutingTab extends Fragment {
    private View _view;

    private CyberScouterDbHelper mDbHelper;
    SQLiteDatabase _db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scouting,container,false);
        _view = view;

        mDbHelper = new CyberScouterDbHelper(getActivity());
        _db = mDbHelper.getWritableDatabase();

        String[] user_array = CyberScouterUsers.getUserNames(_db);
        String[] team_array = CyberScouterTeams.getTeamNumbers(_db);

        Spinner teams = view.findViewById(R.id.spinner_teams);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(),
                R.layout.spinner_text_items, team_array);
        teams.setAdapter(adapter1);

        Spinner names = view.findViewById(R.id.spinner_fragmentScoutingNames);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(),
                R.layout.spinner_text_items, user_array);
        names.setAdapter(adapter2);

        return view;
    }
}
