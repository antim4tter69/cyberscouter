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

    BroadcastReceiver mUsersReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String ret = intent.getStringExtra("cyberscouterusers");
            updateUsers(ret);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scouting);

        registerReceiver(mTeamsReceiver, new IntentFilter(CyberScouterTeams.TEAMS_UPDATED_FILTER));
        registerReceiver(mUsersReceiver, new IntentFilter(CyberScouterUsers.USERS_UPDATED_FILTER));

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

        String users_str = CyberScouterUsers.getUsersRemote(this);
        if(null != users_str && !users_str.equalsIgnoreCase("skip")) {
            updateUsers(users_str);
        }
    }

    @Override
    protected void onDestroy() {
        if(mDbHelper != null) {
            mDbHelper.close();
        }
        unregisterReceiver(mTeamsReceiver);
        unregisterReceiver(mUsersReceiver);

        super.onDestroy();
    }

    private void updateStatusIndicator(int color) {
        ImageView iv = findViewById(R.id.imageView_btIndicator);
        BluetoothComm.updateStatusIndicator(iv, color);
    }

    private void updateTeams(String teams) {
        SQLiteDatabase _db =  mDbHelper.getWritableDatabase();
        CyberScouterTeams.setTeams(_db, teams);
    }

    private void updateUsers(String json){
        SQLiteDatabase _db =  mDbHelper.getWritableDatabase();
        if(!json.equalsIgnoreCase("skip")) {
            CyberScouterUsers.setUsers(_db, json);
        }
    }
}