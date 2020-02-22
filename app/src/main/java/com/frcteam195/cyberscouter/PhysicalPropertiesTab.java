package com.frcteam195.cyberscouter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PhysicalPropertiesTab extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_physical_properties,container,false);

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

        button = view.findViewById(R.id.gearSpeed2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gearSpeed2();
            }
        });

        button = view.findViewById(R.id.gearSpeed3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gearSpeed3();
            }
        });

        button = view.findViewById(R.id.pneumaticsYes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pneumaticsYes();
            }
        });

        button = view.findViewById(R.id.pneumaticsNo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pneumaticsNo();
            }
        });

        return view;
    }

    private void motorMinusButton(){
    }

    private void motorPlusButton(){
    }

    private void wheelsMinusButton(){
    }

    private void wheelsPlusButton(){
    }

    private void gearSpeed1(){
    }

    private void gearSpeed2(){
    }

    private void gearSpeed3(){
    }

    private void pneumaticsYes(){
    }

    private void pneumaticsNo(){
    }
}
