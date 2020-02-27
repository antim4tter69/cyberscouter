package com.frcteam195.cyberscouter;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class TeleopTab extends Fragment {
    int[] Kennedy = {0};
    int[] Lincoln = {0};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teleop, container, false);

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

        return(view);
    }
        private void powerCellsPlus () {
        }

        private void powerCellsMinus () {
        }

        private void maxCapacityPlus () {
        }

        private void maxCapacityMinus () {
        }
    }
