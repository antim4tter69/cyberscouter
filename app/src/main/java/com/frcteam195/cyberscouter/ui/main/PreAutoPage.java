package com.frcteam195.cyberscouter.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.frcteam195.cyberscouter.CyberScouterContract;
import com.frcteam195.cyberscouter.FakeRadioGroup;
import com.frcteam195.cyberscouter.R;

public class PreAutoPage {
    private Button button;
    private final int[] startPositionButtons = {R.id.startbutton1, R.id.startbutton2, R.id.startbutton3, R.id.startbutton4, R.id.startbutton5, R.id.startbutton6};
    private final int[] didNotShowButtons = {R.id.didntshowyesbutton, R.id.didntshownobutton};
    private int defaultButtonTextColor;
    private final int SELECTED_BUTTON_TEXT_COLOR = Color.GREEN;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auto, container, false);



        button = view.findViewById(R.id.startbutton1);
        button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    ();
                }
            });

        button = view.findViewById(R.id.startbutton2);
        button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    ();
                }
            });

        button = view.findViewById(R.id.startbutton3);
        button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    ();
                }
            });

        button = view.findViewById(R.id.startbutton4);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ();
            }
        });

        button = view.findViewById(R.id.startbutton5);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ();
            }
        });

        button = view.findViewById(R.id.startbutton6);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ();
            }
        });

        button = view.findViewById(R.id.didntshownobutton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ();
            }
        });

        button = view.findViewById(R.id.didntshowyesbutton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ();
            }
        }); {
                @Override
                public void onClick(View) {
                    ();
                }
            });





        return view;
    }












    public void didntShowNo()  {
        FakeRadioGroup.buttonPressed(this, 0, didNotShowButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }
    public void didntShowYes()  {
        FakeRadioGroup.buttonPressed(this, 1, didNotShowButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_AUTOMOVEBONUS, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }

    public void startPositions()  {
        FakeRadioGroup.buttonPressed( this, 0, startPositionButtons, CyberScouterContract.MatchScouting.COLUMN_NAME_PICKUP, SELECTED_BUTTON_TEXT_COLOR, defaultButtonTextColor);
    }

    }

}
