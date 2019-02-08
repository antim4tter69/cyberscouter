package com.frcteam195.cyberscouter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NamePickerPageActivity extends AppCompatActivity {
private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_picker_page);

        button = (Button) findViewById(R.id.button48);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateName();

            }
        });

        button = (Button) findViewById(R.id.button50);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToScoutingPage();

            }
        });




    }
    public void updateName(){
        Intent intent = new Intent(this, ScoutingPage.class);
        startActivity(intent);

    }

    public void returnToScoutingPage(){
        Intent intent = new Intent(this, ScoutingPage.class);
        startActivity(intent);
    }
}
