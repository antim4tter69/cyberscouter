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

public class AutoTab extends Fragment {
    private Button button;
    private final int[] moveBonusButtons = {R.id.button_moveBonusNo, R.id.button_moveBonusYes};
    private final int[] pickupButtons = {R.id.button_PickupNo, R.id.button_PickupYes};
    private View _view;
    private int defaultButtonTextColor;
    private final int SELECTED_BUTTON_TEXT_COLOR = Color.GREEN;
    private int NumberOfPreloadCount = 0;
    private int TypicalCellsStoredCount = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auto, container, false);
        _view = view;



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





        return view;
    }












    public void moveBonusNo()  {
        FakeRadioGroup.buttonPressed(getActivity(), _view, 0, moveBonusButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void moveBonusYes()  {
        FakeRadioGroup.buttonPressed(getActivity(), _view, 1, moveBonusButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }

    public void pickupNo()  {
        FakeRadioGroup.buttonPressed(getActivity(), _view, 0, pickupButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_PICKUP, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void pickupYes()  {
        FakeRadioGroup.buttonPressed(getActivity(), _view, 1, pickupButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_PICKUP, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }

    public void numberOfPreloadMinus() {
        
    }
}
