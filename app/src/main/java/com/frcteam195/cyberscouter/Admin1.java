package com.frcteam195.cyberscouter;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ToggleButton;

public class Admin1 extends AppCompatActivity {
    private Button button;
    private AutoCompleteTextView actv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin1);

        final Spinner spnr = findViewById(R.id.spinner);
        String[] spnr_items = {"--please select--", "Red 1", "Red 2", "Red 3", "Blue 1", "Blue 2", "Blue 3", "Pit", "Review"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_text_items, spnr_items);

        spnr.setAdapter(adapter);
        spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (spnr.getSelectedItemPosition() != 0) {
                    String item = spnr.getSelectedItem().toString();
                    spinnerChanged(item);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        button = (Button) findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setTestMode();
            }
        });

        button = (Button) findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateCode();
            }
        });

        button = (Button) findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setUpSync();
            }
        });

        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, DbInfo.initalEndpointArray);
        actv.setThreshold(1);
        actv.setAdapter(adapter);

        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, DbInfo.initalDatabaseArray);
        actv.setThreshold(1);
        actv.setAdapter(adapter);

        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView3);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, DbInfo.initalUsernameArray);
        actv.setThreshold(1);
        actv.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        if (null != cfg) {
            Spinner spnr = findViewById(R.id.spinner);
            ArrayAdapter adapter = (ArrayAdapter) spnr.getAdapter();
            int spinnerPosition = adapter.getPosition(cfg.getRole());
            if (-1 != spinnerPosition)
                spnr.setSelection(spinnerPosition);
        }

    }

    public void openMainActivity() {
        this.finish();
    }


    public void setTestMode() {
    }

    public void updateCode() {
    }

    public void setUpSync() {
    }

    public void spinnerChanged(String val) {
        try {
            CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(CyberScouterContract.ConfigEntry.COLUMN_NAME_ROLE, val);
            int count = db.update(
                    CyberScouterContract.ConfigEntry.TABLE_NAME,
                    values,
                    null,
                    null);
        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        }

    }

}
