package com.frcteam195.cyberscouter;

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
import android.widget.Spinner;

public class AutoTab extends Fragment {
    private Button button;
    private final int[] moveBonusButtons = {R.id.button_moveBonusNo, R.id.button_moveBonusYes};
    private final int[] pickupButtons = {R.id.button_PickupNo, R.id.button_PickupYes};
    private View _view;
    private int defaultButtonTextColor;
    private final int SELECTED_BUTTON_TEXT_COLOR = Color.GREEN;
    private int NumberOfPreloadCount = 0;
    private int TypicalCellsStoredCount = 0;
    private String[] SpinnerItems = {"Starting Position", "1", "2", "3", "4", "5", "6"};
    //private Button findViewById(int button_PreloadCounter) {
      //  return null;
    //}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
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
        defaultButtonTextColor = button.getCurrentTextColor();

        button = view.findViewById(R.id.button_moveBonusYes);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                moveBonusYes();
            }
        });
        defaultButtonTextColor = button.getCurrentTextColor();

        button = view.findViewById(R.id.button_PickupNo);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pickupNo();
            }
        });
        defaultButtonTextColor = button.getCurrentTextColor();

        button = view.findViewById(R.id.button_PickupYes);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pickupYes();
            }
        });
        defaultButtonTextColor = button.getCurrentTextColor();

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
        button = _view.findViewById(R.id.button_PreloadCounter);
        if (NumberOfPreloadCount > 0)
            NumberOfPreloadCount --;
        button.setText(String.valueOf(NumberOfPreloadCount));
    }
    public void numberOfPreloadPlus() {
        button = _view.findViewById(R.id.button_PreloadCounter);
        NumberOfPreloadCount ++;
        button.setText(String.valueOf(NumberOfPreloadCount));
    }
    public void typicalCellsStoredMinus() {
        button = _view.findViewById(R.id.button_TypicalCellsStoredCounter);
        if (TypicalCellsStoredCount > 0)
            TypicalCellsStoredCount --;
        button.setText(String.valueOf(TypicalCellsStoredCount));
    }

    public void typicalCellsStoredPlus() {
        button = _view.findViewById(R.id.button_TypicalCellsStoredCounter);
        TypicalCellsStoredCount ++;
        button.setText(String.valueOf(TypicalCellsStoredCount));
    }


}
