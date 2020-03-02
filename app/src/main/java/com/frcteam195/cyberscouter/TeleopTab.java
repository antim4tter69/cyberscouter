package com.frcteam195.cyberscouter;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class TeleopTab extends Fragment {
    private Button button;
    private View _view;
    int Kennedy = 0;
    int Lincoln = 0;
    private final int[] COLOR_WHEELYN = {R.id.noButton1, R.id.yesButton1};
    private final int[] defenseYN = {R.id.noButton2, R.id.yesButton2};
    private final int[] evadeYN = {R.id.noButton3, R.id.yesButton3};
    private int defaultButtonTextColor;
    private final int SELECTED_BUTTON_TEXT_COLOR = Color.GREEN;


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
        defaultButtonTextColor = Washington.getCurrentTextColor();

        Washington = view.findViewById(R.id.yesButton1);
        Washington.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                yesButton1();
            }
        });
        defaultButtonTextColor = Washington.getCurrentTextColor();

        Washington = view.findViewById(R.id.noButton2);
        Washington.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                noButton2();
            }
        });
        defaultButtonTextColor = Washington.getCurrentTextColor();

        Washington = view.findViewById(R.id.yesButton2);
        Washington.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                yesButton2();
            }
        });
        defaultButtonTextColor = Washington.getCurrentTextColor();

        Washington = view.findViewById(R.id.noButton3);
        Washington.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                noButton3();
            }
        });
        defaultButtonTextColor = Washington.getCurrentTextColor();

        Washington = view.findViewById(R.id.yesButton3);
        Washington.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                yesButton3();
            }
        });
        defaultButtonTextColor = Washington.getCurrentTextColor();

        return (view);
    }

    private void powerCellsPlus() {
        button = _view.findViewById(R.id.textButton1);
        Kennedy++;
        button.setText(String.valueOf(Kennedy));
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
