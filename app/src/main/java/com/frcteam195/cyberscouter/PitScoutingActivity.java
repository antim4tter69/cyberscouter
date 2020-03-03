package com.frcteam195.cyberscouter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.frcteam195.cyberscouter.ui.main.SectionsPagerAdapter;

public class PitScoutingActivity extends AppCompatActivity {
    private CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(this);


    BroadcastReceiver mOnlineStatusReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int color = intent.getIntExtra("onlinestatus", Color.RED);
            updateStatusIndicator(color);
        }
    };

    BroadcastReceiver mTeamsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String ret = intent.getStringExtra("cyberscouterteams");
            updateTeams(ret);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scouting);

        registerReceiver(mTeamsReceiver, new IntentFilter(CyberScouterTeams.TEAMS_UPDATED_FILTER));
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);
    }

    @Override
    protected void onResume(){
        super.onResume();

        String teams_str = CyberScouterTeams.getTeamsRemote(this);
        if(null != teams_str && !teams_str.equalsIgnoreCase("skip")) {
            updateTeams(teams_str);
        }
    }

    @Override
    protected void onDestroy() {
        if(mDbHelper != null) {
            mDbHelper.close();
        }
        unregisterReceiver(mTeamsReceiver);


        super.onDestroy();
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PhysicalPropertiesTab();
            case 1:
                return new AutoTab();
            case 2:
                return new TeleopTab();
            case 3:
                return new EndgameTab();
            default:
                return null;
        }
    }

    public int getCount() {
        return 4;
    }


    private void updateStatusIndicator(int color) {
        ImageView iv = findViewById(R.id.imageView_btIndicator);
        BluetoothComm.updateStatusIndicator(iv, color);
    }

    private void updateTeams(String teams) {
        SQLiteDatabase _db =  mDbHelper.getWritableDatabase();
        CyberScouterTeams.deleteTeams(_db);
        CyberScouterTeams.setTeams(_db, teams);
    }
}