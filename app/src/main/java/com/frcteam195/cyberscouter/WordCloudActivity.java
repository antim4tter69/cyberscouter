package com.frcteam195.cyberscouter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class WordCloudActivity extends AppCompatActivity {
    private String[] words = {"Fast", "Slow", "Efficient", "Efficient Ground Pickup", "NINO",
            "Good", "Bad", "Good Leveler", "Inefficient Ground Pickup", "Penalty Prone", "Strong",
            "Weak", "Unaffected by Defense", "Affected by Defense", "Fast Climb", "Bad Climb", "Accurate",
            "Accurate Longshot", "Good w/ Wheel", "Bad w/ Wheel"};
    //    private String[] words;
    private Integer[] wordIDs;
    Button[] myButtons = new Button[20];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_cloud);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.word_cloud_text_items, words);
        GridView gv = findViewById(R.id.gridView_words);
        gv.setNumColumns(4);

        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                      @Override
                                      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                          Toast.makeText(getApplicationContext(),
                                                  ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                                      }
                                  });
        }

}





