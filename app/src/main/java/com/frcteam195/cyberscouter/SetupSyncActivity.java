package com.frcteam195.cyberscouter;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

public class SetupSyncActivity extends AppCompatActivity {
    AutoCompleteTextView actv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_sync);



    }

    @Override
    public void onResume() {
        super.onResume();

        CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

        if (null != cfg) {
            Spinner spnr = findViewById(R.id.spinner);
            ArrayAdapter<String> adapter = (ArrayAdapter<String>)spnr.getAdapter();
            int spinnerPosition = adapter.getPosition(cfg.getRole());
            if (-1 != spinnerPosition)
                spnr.setSelection(spinnerPosition);

            actv = findViewById(R.id.autoCompleteTextView);
            adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line, DbInfo.initalEndpointArray);
            actv.setThreshold(1);
            actv.setAdapter(adapter);

            actv = findViewById(R.id.autoCompleteTextView2);
            adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line, DbInfo.initalDatabaseArray);
            actv.setThreshold(1);
            actv.setAdapter(adapter);

            actv = findViewById(R.id.autoCompleteTextView3);
            adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line, DbInfo.initalUsernameArray);
            actv.setThreshold(1);
            actv.setAdapter(adapter);


        }

    }
}
