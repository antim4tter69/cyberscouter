package com.frcteam195.cyberscouter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class PhysicalPropertiesTab extends Fragment {
    private Button button;
    private final int[] gearSpeedButtons = {R.id.gearSpeed1,R.id.gearSpeed2,R.id.gearSpeed3};
    private final int[] pneumaticsYNButtons = {R.id.pneumaticsYes,R.id.pneumaticsNo};
    private View _view;
    private int defaultButtonTextColor;
    private final int SELCTED_BUTTON_TEXT_COLOR = Color.GREEN;
    private int numberOfMotors = 0;
    private int numberOfWheels = 0;
    private String[] driveTypes = {"Kiwi", "Other"};
    private String[] motorTypes = {"Falcon", "Other"};
    private String[] wheelTypes = {"Colson", "Other"};
    private String[] progLangTypes = {"Java", "Other"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_physical_properties,container,false);
        _view = view;

        Spinner driveType = view.findViewById(R.id.driveTypePicker);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,driveTypes);
        driveType.setAdapter(adapter1);

        Spinner motorType = view.findViewById(R.id.motorTypePicker);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,motorTypes);
        motorType.setAdapter(adapter2);

        Spinner wheelType = view.findViewById(R.id.wheelTypePicker);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,wheelTypes);
        wheelType.setAdapter(adapter3);

        Spinner progLang = view.findViewById(R.id.progLangPicker);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,progLangTypes);
        progLang.setAdapter(adapter4);

        Button button = view.findViewById(R.id.motorMinusButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                motorMinusButton();
            }
        });

        button = view.findViewById(R.id.motorPlusButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                motorPlusButton();
            }
        });

        button = view.findViewById(R.id.wheelsMinusButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wheelsMinusButton();
            }
        });

        button = view.findViewById(R.id.wheelsPlusButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wheelsPlusButton();
            }
        });

        button = view.findViewById(R.id.gearSpeed1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gearSpeed1();
            }
        });
        defaultButtonTextColor = button.getCurrentTextColor();

        button = view.findViewById(R.id.gearSpeed2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gearSpeed2();
            }
        });
        defaultButtonTextColor = button.getCurrentTextColor();

        button = view.findViewById(R.id.gearSpeed3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gearSpeed3();
            }
        });
        defaultButtonTextColor = button.getCurrentTextColor();

        button = view.findViewById(R.id.pneumaticsYes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pneumaticsYes();
            }
        });
        defaultButtonTextColor = button.getCurrentTextColor();

        button = view.findViewById(R.id.pneumaticsNo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pneumaticsNo();
            }
        });
        defaultButtonTextColor = button.getCurrentTextColor();

        return view;
    }

    private void motorMinusButton(){
        button = _view.findViewById(R.id.button_numberOfMotor);
        if (numberOfMotors > 0)
            numberOfMotors --;
        button.setText(String.valueOf(numberOfMotors));
    }

    private void motorPlusButton(){
        button = _view.findViewById(R.id.button_numberOfMotor);
            numberOfMotors ++;
        button.setText(String.valueOf(numberOfMotors));
    }

    private void wheelsMinusButton(){
        button = _view.findViewById(R.id.button_numberOfWheels);
        if (numberOfWheels > 0)
            numberOfWheels --;
        button.setText(String.valueOf(numberOfWheels));
    }

    private void wheelsPlusButton(){
        button = _view.findViewById(R.id.button_numberOfWheels);
            numberOfWheels ++;
        button.setText(String.valueOf(numberOfWheels));
    }

    private void gearSpeed1(){
        FakeRadioGroup.buttonPressed(getActivity(),_view,0,gearSpeedButtons,
                CyberScouterContract.MatchScouting.COLUMN_NAME_GEARSPEED,SELCTED_BUTTON_TEXT_COLOR,
                defaultButtonTextColor);
    }

    private void gearSpeed2(){
        FakeRadioGroup.buttonPressed(getActivity(),_view,1,gearSpeedButtons,
                CyberScouterContract.MatchScouting.COLUMN_NAME_GEARSPEED,SELCTED_BUTTON_TEXT_COLOR,
                defaultButtonTextColor);
    }

    private void gearSpeed3(){
        FakeRadioGroup.buttonPressed(getActivity(),_view,2,gearSpeedButtons,
                CyberScouterContract.MatchScouting.COLUMN_NAME_GEARSPEED,SELCTED_BUTTON_TEXT_COLOR,
                defaultButtonTextColor);
    }

    private void pneumaticsYes(){
        FakeRadioGroup.buttonPressed(getActivity(),_view,0,pneumaticsYNButtons,
                CyberScouterContract.MatchScouting.COLUMN_NAME_PNEUMATICS,SELCTED_BUTTON_TEXT_COLOR,
                defaultButtonTextColor);
    }

    private void pneumaticsNo(){
        FakeRadioGroup.buttonPressed(getActivity(),_view,1,pneumaticsYNButtons,
                CyberScouterContract.MatchScouting.COLUMN_NAME_PNEUMATICS,SELCTED_BUTTON_TEXT_COLOR,
                defaultButtonTextColor);
    }
}
