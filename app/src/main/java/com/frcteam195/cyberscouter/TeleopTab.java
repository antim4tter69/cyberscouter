package com.frcteam195.cyberscouter;


import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class TeleopTab extends Fragment {
    private Button button;
    private View _view;
    int Kennedy = 0;
    int Lincoln = 0;
    private final int[] COLOR_WHEELYN = {R.id.noButton1, R.id.yesButton1};
    private final int[] defenseYN = {R.id.noButton2, R.id.yesButton2};
    private final int[] evadeYN = {R.id.noButton3, R.id.yesButton3};
    private int defaultButtonTextColor = Color.LTGRAY;
    private final int SELECTED_BUTTON_TEXT_COLOR = Color.GREEN;

    private int currentTeam;
    private CyberScouterDbHelper mDbHelper;
    SQLiteDatabase _db;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teleop, container, false);
        _view = view;

        Button Washington = view.findViewById(R.id.button_powerCellsPlus);
        Washington.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                powerCellsPlus();
            }
        });

        Washington = view.findViewById(R.id.button_powerCellsMinus);
        Washington.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                powerCellsMinus();
            }
        });

        Washington = view.findViewById(R.id.button_maxCapacityPlus);
        Washington.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                maxCapacityPlus();
            }
        });

        Washington = view.findViewById(R.id.button_maxCapacityMinus);
        Washington.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                maxCapacityMinus();
            }
        });

        Washington = view.findViewById(R.id.noButton1);
        Washington.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                noButton1();
            }
        });

        Washington = view.findViewById(R.id.yesButton1);
        Washington.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                yesButton1();
            }
        });

        Washington = view.findViewById(R.id.noButton2);
        Washington.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                noButton2();
            }
        });

        Washington = view.findViewById(R.id.yesButton2);
        Washington.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                yesButton2();
            }
        });

        Washington = view.findViewById(R.id.noButton3);
        Washington.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                noButton3();
            }
        });

        Washington = view.findViewById(R.id.yesButton3);
        Washington.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                yesButton3();
            }
        });

        return (view);
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
//            EditText et = _view.findViewById(R.id.editText3);
//            et.setText(String.valueOf(cst.getAutoSummary()));
//
//            button = _view.findViewById(R.id.button_PreloadCounter);
//            button.setText(String.valueOf(cst.getNumPreload()));
//            button = _view.findViewById(R.id.button_TypicalCellsStoredCounter);
//            button.setText(String.valueOf(cst.getMaxBallCapacity()));

//            FakeRadioGroup.buttonDisplay(getActivity(), _view, cst.getMoveBonus(), moveBonusButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
//            FakeRadioGroup.buttonDisplay(getActivity(), _view, cst.getAutoPickUp(), pickupButtons, SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
        }
    }

    private void powerCellsPlus() {
        button = _view.findViewById(R.id.textButton1);
        Kennedy++;
        button.setText(String.valueOf(Kennedy));
        try {
            CyberScouterTeams.updateTeamMetric(_db, CyberScouterContract.Teams.COLUMN_NAME_MOVE_BONUS, 0, currentTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void powerCellsMinus() {
        button = _view.findViewById(R.id.textButton1);
        if (Kennedy > 0)
            Kennedy--;
        button.setText(String.valueOf(Kennedy));
    }

    private void maxCapacityPlus() {
        button = _view.findViewById(R.id.textButton2);
        Lincoln++;
        button.setText(String.valueOf(Lincoln));
    }

    private void maxCapacityMinus() {
        button = _view.findViewById(R.id.textButton2);
        if (Lincoln > 0)
            Lincoln--;
        button.setText(String.valueOf(Lincoln));
    }

    private void noButton1() {
        FakeRadioGroup.buttonPressed(getActivity(), _view, 0, COLOR_WHEELYN,
                CyberScouterContract.Teams.COLUMN_NAME_COLOR_WHEEL, SELECTED_BUTTON_TEXT_COLOR,
                defaultButtonTextColor);
    }

    private void yesButton1() {
        FakeRadioGroup.buttonPressed(getActivity(), _view, 1, COLOR_WHEELYN,
                CyberScouterContract.Teams.COLUMN_NAME_COLOR_WHEEL, SELECTED_BUTTON_TEXT_COLOR,
                defaultButtonTextColor);
    }

    private void noButton2() {
        FakeRadioGroup.buttonPressed(getActivity(), _view, 0, defenseYN,
                CyberScouterContract.Teams.COLUMN_NAME_COLOR_WHEEL, SELECTED_BUTTON_TEXT_COLOR,
                defaultButtonTextColor);
    }

    private void yesButton2() {
        FakeRadioGroup.buttonPressed(getActivity(), _view, 1, defenseYN,
                CyberScouterContract.Teams.COLUMN_NAME_COLOR_WHEEL, SELECTED_BUTTON_TEXT_COLOR,
                defaultButtonTextColor);
    }

    private void noButton3() {
        FakeRadioGroup.buttonPressed(getActivity(), _view, 0, evadeYN,
                CyberScouterContract.Teams.COLUMN_NAME_COLOR_WHEEL, SELECTED_BUTTON_TEXT_COLOR,
                defaultButtonTextColor);
    }

    private void yesButton3() {
        FakeRadioGroup.buttonPressed(getActivity(), _view, 1, evadeYN,
                CyberScouterContract.Teams.COLUMN_NAME_COLOR_WHEEL, SELECTED_BUTTON_TEXT_COLOR,
                defaultButtonTextColor);
    }
}
