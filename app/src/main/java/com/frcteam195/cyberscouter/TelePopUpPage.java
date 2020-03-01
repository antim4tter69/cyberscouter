package com.frcteam195.cyberscouter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TelePopUpPage extends DialogFragment implements View.OnClickListener{
    private Integer[] values = new Integer[4];

    private OnFragmentInteractionListener mListener;

    public TelePopUpPage() {
        // Required empty public constructor
    }

    public void setValues(Integer[] _vals){
        for(int i=0 ; i < _vals.length ; ++i) {
            this.values[i] = _vals[i];
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tele_pop_up_page, container, false);

        Button upButton = view.findViewById(R.id.lowerPlusButton);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowerPlusButton();
            }
        });

        upButton =  view.findViewById(R.id.LowerMinusButton);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowerMinusButton();
            }
        });

        upButton =  view.findViewById(R.id.outerPlusButton);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outerPlusButton();
            }
        });

        upButton =  view.findViewById(R.id.outerMinusButton);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outerMinusButton();
            }
        });

        upButton =  view.findViewById(R.id.InnerPlusButton);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                innerPlusButton();
            }
        });

        upButton =  view.findViewById(R.id.InnerMinusButton);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                innerMinusButton();
            }
        });

        upButton =  view.findViewById(R.id.BackButton);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backButton();
            }
        });

        TextView tv = view.findViewById(R.id.textView_Zone);
        tv.setText(getString(R.string.zone_text, values[0]));
        Button button = view.findViewById(R.id.InnerScore);
        button.setText(values[1].toString());
        button = view.findViewById(R.id.outerScore);
        button.setText(values[2].toString());
        button = view.findViewById(R.id.lowerScore);
        button.setText(values[3].toString());

        return view;
    }

    private void backButton() {
        this.dismiss();
    }

    private void innerMinusButton() {
    }

    private void innerPlusButton() {
    }

    private void outerMinusButton() {
    }

    private void outerPlusButton() {
    }

    private void lowerMinusButton() {
    }

    private void lowerPlusButton() {
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction();
    }
}
