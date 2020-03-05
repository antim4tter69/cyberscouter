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

public class EndgameTab extends Fragment implements IOnEditTextSaveListener {
    private final int[] canTheyClimb = {R.id.noCanTheyClimb, R.id.yesCanTheyClimb};
    private final int[] moveOnBar = {R.id.noMoveOnBar, R.id.yesMoveOnBar};
    private final int[] lockingMechanism = {R.id.noLockingMechanism, R.id.yesLockingMechanism};
    private final int[] centerClimb = {R.id.noCenterClimb, R.id.yesCenterClimb};
    private int defaultButtonBackgroundColor = Color.LTGRAY;
    private final int SELECTED_BUTTON_TEXT_COLOR = Color.GREEN;
    private View _view;

    private int currentTeam;
    private CyberScouterDbHelper mDbHelper;
    SQLiteDatabase _db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_endgame,container,false);
        _view = view;

        Button button = view.findViewById(R.id.yesCanTheyClimb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yesCanTheyClimb();
            }
        });

        button = view.findViewById(R.id.noCanTheyClimb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noCanTheyClimb();
            }
        });

        button = view.findViewById(R.id.yesMoveOnBar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yesMoveOnBar();
            }
        });

        button = view.findViewById(R.id.noMoveOnBar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noMoveOnBar();
            }
        });

        button = view.findViewById(R.id.yesLockingMechanism);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yesLockingMechanism();
            }
        });

        button = view.findViewById(R.id.noLockingMechanism);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noLockingMechanism();
            }
        });

        button = view.findViewById(R.id.yesCenterClimb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yesCenterClimb();
            }
        });

        button = view.findViewById(R.id.noCenterClimb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noCenterClimb();
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
            EditText et = _view.findViewById(R.id.editText_climbHeight);
            et.setText(String.valueOf(cst.getClimbHeightID()));
            et.setSelectAllOnFocus(true);

            FakeRadioGroup.buttonDisplay(getActivity(), _view, cst.getCanClimb(), canTheyClimb, SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
            FakeRadioGroup.buttonDisplay(getActivity(), _view, cst.getCanMoveOnBar(), moveOnBar, SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
            FakeRadioGroup.buttonDisplay(getActivity(), _view, cst.getLockingMechanism(), lockingMechanism, SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
            FakeRadioGroup.buttonDisplay(getActivity(), _view, cst.getCenterClimb(), centerClimb, SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
        }
    }

    private void yesCanTheyClimb() {
        FakeRadioGroup.buttonPressed(getActivity(),_view,1,canTheyClimb,
                CyberScouterContract.Teams.COLUMN_NAME_CAN_CLIMB,
                SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
        updateFakeRadioButton(CyberScouterContract.Teams.COLUMN_NAME_CAN_CLIMB, 1);
    }

    private void noCanTheyClimb() {
        FakeRadioGroup.buttonPressed(getActivity(),_view,0,canTheyClimb,
                CyberScouterContract.Teams.COLUMN_NAME_CAN_CLIMB,
                SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
        updateFakeRadioButton(CyberScouterContract.Teams.COLUMN_NAME_CAN_CLIMB, 0);
    }

    private void yesMoveOnBar() {
        FakeRadioGroup.buttonPressed(getActivity(),_view,1,moveOnBar,
                CyberScouterContract.Teams.COLUMN_NAME_CAN_MOVE_ON_BAR,
                SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
        updateFakeRadioButton(CyberScouterContract.Teams.COLUMN_NAME_CAN_MOVE_ON_BAR, 1);
    }

    private void noMoveOnBar() {
        FakeRadioGroup.buttonPressed(getActivity(),_view,0,moveOnBar,
                CyberScouterContract.Teams.COLUMN_NAME_CAN_MOVE_ON_BAR,
                SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
        updateFakeRadioButton(CyberScouterContract.Teams.COLUMN_NAME_CAN_MOVE_ON_BAR, 0);
    }

    private void yesLockingMechanism() {
        FakeRadioGroup.buttonPressed(getActivity(),_view,1,lockingMechanism,
                CyberScouterContract.Teams.COLUMN_NAME_LOCKING_MECHANISM,
                SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
        updateFakeRadioButton(CyberScouterContract.Teams.COLUMN_NAME_LOCKING_MECHANISM, 1);
    }

    private void noLockingMechanism() {
        FakeRadioGroup.buttonPressed(getActivity(),_view,0,lockingMechanism,
                CyberScouterContract.Teams.COLUMN_NAME_LOCKING_MECHANISM,
                SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
        updateFakeRadioButton(CyberScouterContract.Teams.COLUMN_NAME_LOCKING_MECHANISM, 0);
    }

    private void yesCenterClimb() {
        FakeRadioGroup.buttonPressed(getActivity(),_view,1,centerClimb,
                CyberScouterContract.Teams.COLUMN_NAME_CENTER_CLIMB,
                SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
        updateFakeRadioButton(CyberScouterContract.Teams.COLUMN_NAME_CENTER_CLIMB, 1);
    }

    private void noCenterClimb() {
        FakeRadioGroup.buttonPressed(getActivity(),_view,0,centerClimb,
                CyberScouterContract.Teams.COLUMN_NAME_CENTER_CLIMB,
                SELECTED_BUTTON_TEXT_COLOR, defaultButtonBackgroundColor);
        updateFakeRadioButton(CyberScouterContract.Teams.COLUMN_NAME_CENTER_CLIMB, 0);
    }

    public void saveTextValues() {
        try {
            EditText et = _view.findViewById(R.id.editText_climbHeight);
            CyberScouterTeams.updateTeamMetric(_db, CyberScouterContract.Teams.COLUMN_NAME_CLIMB_HEIGHT_ID,
                    Integer.parseInt(et.getText().toString()), currentTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateFakeRadioButton(String col, int val) {
        try {
            CyberScouterTeams.updateTeamMetric(_db, col, val, currentTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
