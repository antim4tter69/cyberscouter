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

import org.w3c.dom.Text;

public class TelePopUpPage extends DialogFragment implements View.OnClickListener{
    private Integer[] values = new Integer[4];
    private View view;

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
        view = inflater.inflate(R.layout.fragment_tele_pop_up_page, container, false);

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
        TextView et = view.findViewById(R.id.InnerScore);
        et.setText(values[1].toString());
        et = view.findViewById(R.id.outerScore);
        et.setText(values[2].toString());
        if(1 == values[0]) {
            et = view.findViewById(R.id.lowerScore);
            et.setText(values[3].toString());
            et.setVisibility(View.VISIBLE);
            Button button = view.findViewById(R.id.LowerMinusButton);
            button.setVisibility(View.VISIBLE);
            button.setEnabled(true);
            button = view.findViewById(R.id.lowerPlusButton);
            button.setVisibility(View.VISIBLE);
            button.setEnabled(true);
            tv = view.findViewById(R.id.LowerBox);
            tv.setVisibility(View.VISIBLE);
        } else {
            et = view.findViewById(R.id.lowerScore);
            et.setVisibility(View.INVISIBLE);
            Button button = view.findViewById(R.id.LowerMinusButton);
            button.setVisibility(View.INVISIBLE);
            button.setEnabled(false);
            button = view.findViewById(R.id.lowerPlusButton);
            button.setVisibility(View.INVISIBLE);
            button.setEnabled(false);
            tv = view.findViewById(R.id.LowerBox);
            tv.setVisibility(View.INVISIBLE);
        }

        return view;
    }

    private void backButton() {
        if (mListener != null) {
            mListener.onFragmentInteraction(values);
        }
        this.dismiss();
    }

    private void innerMinusButton() {
        if(0 < values[1]) {
            values[1]--;
            TextView et = view.findViewById(R.id.InnerScore);
            et.setText(values[1].toString());
        }
    }

    private void innerPlusButton() {
        values[1]++;
        TextView et = view.findViewById(R.id.InnerScore);
        et.setText(values[1].toString());
    }

    private void outerMinusButton() {
        if(0 < values[2]) {
            values[2]--;
            TextView et = view.findViewById(R.id.outerScore);
            et.setText(values[2].toString());
        }
    }

    private void outerPlusButton() {
        values[2]++;
        TextView et = view.findViewById(R.id.outerScore);
        et.setText(values[2].toString());
    }

    private void lowerMinusButton() {
        if(0 < values[3]) {
            values[3]--;
            TextView et = view.findViewById(R.id.lowerScore);
            et.setText(values[3].toString());
        }
    }

    private void lowerPlusButton() {
        values[3]++;
        TextView et = view.findViewById(R.id.lowerScore);
        et.setText(values[3].toString());
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
        void onFragmentInteraction(Integer[] values);
    }
}
