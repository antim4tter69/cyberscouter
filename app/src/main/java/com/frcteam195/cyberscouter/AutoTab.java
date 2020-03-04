package com.frcteam195.cyberscouter;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AutoTab extends Fragment {
    private Button button;
    private final int[] moveBonusButtons = {R.id.button_moveBonusNo, R.id.button_moveBonusYes};
    private final int[] pickupButtons = {R.id.button_PickupNo, R.id.button_PickupYes};
    private View _view;
    private int defaultButtonBackgroundColor = Color.LTGRAY;
    private final int SELECTED_BUTTON_TEXT_COLOR = Color.GREEN;
    private int NumberOfPreloadCount = 0;
    private int TypicalCellsStoredCount = 0;
    private String[] SpinnerItems = {"Starting Position", "1", "2", "3", "4", "5", "6"};
    private int currentTeam;
    //private Button findViewById(int button_PreloadCounter) {
    //  return null;
    //}
    private CyberScouterDbHelper mDbHelper;
    SQLiteDatabase _db;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auto, container, false);
        _view = view;

        Spinner dropdown = view.findViewById(R.id.spinner_StartPosition);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, SpinnerItems);
        dropdown.setAdapter(adapter);


        button = view.findViewById(R.id.button_moveBonusNo);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                moveBonusNo();
            }
        });

        button = view.findViewById(R.id.button_moveBonusYes);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                moveBonusYes();
            }
        });

        button = view.findViewById(R.id.button_PickupNo);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pickupNo();
            }
        });

        button = view.findViewById(R.id.button_PickupYes);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pickupYes();
            }
        });

        button = view.findViewById(R.id.button_PreloadMinus);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                numberOfPreloadMinus();
            }
        });

        button = view.findViewById(R.id.button_PreloadPlus);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                numberOfPreloadPlus();
            }
        });

        button = view.findViewById(R.id.button_TypicalCellsStoredMinus);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                typicalCellsStoredMinus();
            }
        });

        button = view.findViewById(R.id.button_TypicalCellsStoredPlus);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                typicalCellsStoredPlus();
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        populateScreen();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            populateScreen();
        }
    }

    @Override
    public void onDestroy() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
        super.onDestroy();
    }

    private void populateScreen() {
        if (null == getActivity()) {
            return;
        }
        mDbHelper = new CyberScouterDbHelper(getActivity());

        _db = mDbHelper.getWritableDatabase();
        currentTeam = PitScoutingActivity.getCurrentTeam();

        CyberScouterTeams cst = CyberScouterTeams.getCurrentTeam(_db, currentTeam);

        if (null != cst) {
            EditText et = _view.findViewById(R.id.editText3);
            et.setText(String.valueOf(cst.getAutoSummary()));

            button = _view.findViewById(R.id.button_PreloadCounter);
            button.setText(String.valueOf(cst.getNumPreload()));
            button = _view.findViewById(R.id.button_TypicalCellsStoredCounter);
            button.setText(String.valueOf(cst.getMaxBallCapacity()));

            FakeRadioGroup.buttonDisplay(getActivity(), _view, cst.getMoveBonus(), moveBonusButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
            FakeRadioGroup.buttonDisplay(getActivity(), _view, cst.getAutoPickUp(), pickupButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
        }
    }


    public void moveBonusNo() {
        FakeRadioGroup.buttonPressed(getActivity(), _view, 0, moveBonusButtons, CyberScouterContract.Teams.COLUMN_NAME_MOVE_BONUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
        try {
            CyberScouterTeams.updateTeamMetric(_db, CyberScouterContract.Teams.COLUMN_NAME_MOVE_BONUS, 0, currentTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void moveBonusYes() {
        FakeRadioGroup.buttonPressed(getActivity(), _view, 1, moveBonusButtons, CyberScouterContract.Teams.COLUMN_NAME_MOVE_BONUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
        try {
            CyberScouterTeams.updateTeamMetric(_db, CyberScouterContract.Teams.COLUMN_NAME_MOVE_BONUS, 1, currentTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pickupNo() {
        FakeRadioGroup.buttonPressed(getActivity(), _view, 0, pickupButtons, CyberScouterContract.Teams.COLUMN_NAME_AUTO_PICKUP, SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
        try {
            CyberScouterTeams.updateTeamMetric(_db, CyberScouterContract.Teams.COLUMN_NAME_AUTO_PICKUP, 0, currentTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pickupYes() {
        FakeRadioGroup.buttonPressed(getActivity(), _view, 1, pickupButtons, CyberScouterContract.Teams.COLUMN_NAME_AUTO_PICKUP, SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
        try {
            CyberScouterTeams.updateTeamMetric(_db, CyberScouterContract.Teams.COLUMN_NAME_AUTO_PICKUP, 1, currentTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void numberOfPreloadMinus() {
        button = _view.findViewById(R.id.button_PreloadCounter);
        if (NumberOfPreloadCount > 0)
            NumberOfPreloadCount--;
        button.setText(String.valueOf(NumberOfPreloadCount));
        try {
            CyberScouterTeams.updateTeamMetric(_db, CyberScouterContract.Teams.COLUMN_NAME_NUM_PRE_LOAD, NumberOfPreloadCount, currentTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void numberOfPreloadPlus() {
        button = _view.findViewById(R.id.button_PreloadCounter);
        NumberOfPreloadCount++;
        button.setText(String.valueOf(NumberOfPreloadCount));
        try {
            CyberScouterTeams.updateTeamMetric(_db, CyberScouterContract.Teams.COLUMN_NAME_NUM_PRE_LOAD, NumberOfPreloadCount, currentTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void typicalCellsStoredMinus() {
        button = _view.findViewById(R.id.button_TypicalCellsStoredCounter);
        if (TypicalCellsStoredCount > 0)
            TypicalCellsStoredCount--;
        button.setText(String.valueOf(TypicalCellsStoredCount));
        try {
            CyberScouterTeams.updateTeamMetric(_db, CyberScouterContract.Teams.COLUMN_NAME_MAX_BALL_CAPACITY, TypicalCellsStoredCount, currentTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void typicalCellsStoredPlus() {
        button = _view.findViewById(R.id.button_TypicalCellsStoredCounter);
        TypicalCellsStoredCount++;
        button.setText(String.valueOf(TypicalCellsStoredCount));
        try {
            CyberScouterTeams.updateTeamMetric(_db, CyberScouterContract.Teams.COLUMN_NAME_MAX_BALL_CAPACITY, TypicalCellsStoredCount, currentTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
