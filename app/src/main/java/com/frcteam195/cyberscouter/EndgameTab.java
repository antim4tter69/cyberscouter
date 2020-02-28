package com.frcteam195.cyberscouter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class EndgameTab extends Fragment {
    private final int[] canTheyClimb = {R.id.noCanTheyClimb, R.id.yesCanTheyClimb};
    private final int[] moveOnBar = {R.id.noMoveOnBar, R.id.yesMoveOnBar};
    private final int[] lockingMechanism = {R.id.noLockingMechanism, R.id.yesLockingMechanism};
    private final int[] centerClimb = {R.id.noCenterClimb, R.id.yesCenterClimb};
    private int defaultButtonTextColor;
    private final int SELECTED_BUTTON_TEXT_COLOR = Color.GREEN;
    private View _view;

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
        defaultButtonTextColor = button.getCurrentTextColor();

        button = view.findViewById(R.id.noCanTheyClimb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noCanTheyClimb();
            }
        });
        defaultButtonTextColor = button.getCurrentTextColor();

        button = view.findViewById(R.id.yesMoveOnBar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yesMoveOnBar();
            }
        });
        defaultButtonTextColor = button.getCurrentTextColor();

        button = view.findViewById(R.id.noMoveOnBar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noMoveOnBar();
            }
        });
        defaultButtonTextColor = button.getCurrentTextColor();

        button = view.findViewById(R.id.yesLockingMechanism);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yesLockingMechanism();
            }
        });
        defaultButtonTextColor = button.getCurrentTextColor();

        button = view.findViewById(R.id.noLockingMechanism);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noLockingMechanism();
            }
        });
        defaultButtonTextColor = button.getCurrentTextColor();

        button = view.findViewById(R.id.yesCenterClimb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yesCenterClimb();
            }
        });
        defaultButtonTextColor = button.getCurrentTextColor();

        button = view.findViewById(R.id.noCenterClimb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noCenterClimb();
            }
        });
        defaultButtonTextColor = button.getCurrentTextColor();

        return view;
    }

    private void yesCanTheyClimb() {
        FakeRadioGroup.buttonPressed(getActivity(),_view,1,canTheyClimb,
                CyberScouterContract.MatchScouting.COLUMN_NAME_CANTHEYCLIMB,
                SELECTED_BUTTON_TEXT_COLOR,defaultButtonTextColor);
    }

    private void noCanTheyClimb() {
        FakeRadioGroup.buttonPressed(getActivity(),_view,0,canTheyClimb,
                CyberScouterContract.MatchScouting.COLUMN_NAME_CANTHEYCLIMB,
                SELECTED_BUTTON_TEXT_COLOR,defaultButtonTextColor);
    }

    private void yesMoveOnBar() {
        FakeRadioGroup.buttonPressed(getActivity(),_view,1,moveOnBar,
                CyberScouterContract.MatchScouting.COLUMN_NAME_MOVEONBAR,
                SELECTED_BUTTON_TEXT_COLOR,defaultButtonTextColor);
    }

    private void noMoveOnBar() {
        FakeRadioGroup.buttonPressed(getActivity(),_view,0,moveOnBar,
                CyberScouterContract.MatchScouting.COLUMN_NAME_MOVEONBAR,
                SELECTED_BUTTON_TEXT_COLOR,defaultButtonTextColor);
    }

    private void yesLockingMechanism() {
        FakeRadioGroup.buttonPressed(getActivity(),_view,1,lockingMechanism,
                CyberScouterContract.MatchScouting.COLUMN_NAME_LOCKINGMECHANISM,
                SELECTED_BUTTON_TEXT_COLOR,defaultButtonTextColor);
    }

    private void noLockingMechanism() {
        FakeRadioGroup.buttonPressed(getActivity(),_view,0,lockingMechanism,
                CyberScouterContract.MatchScouting.COLUMN_NAME_LOCKINGMECHANISM,
                SELECTED_BUTTON_TEXT_COLOR,defaultButtonTextColor);
    }

    private void yesCenterClimb() {
        FakeRadioGroup.buttonPressed(getActivity(),_view,1,centerClimb,
                CyberScouterContract.MatchScouting.COLUMN_NAME_CENTERCLIMB,
                SELECTED_BUTTON_TEXT_COLOR,defaultButtonTextColor);
    }

    private void noCenterClimb() {
        FakeRadioGroup.buttonPressed(getActivity(),_view,0,centerClimb,
                CyberScouterContract.MatchScouting.COLUMN_NAME_CENTERCLIMB,
                SELECTED_BUTTON_TEXT_COLOR,defaultButtonTextColor);
    }
}
