package com.frcteam195.cyberscouter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class WordCloudFragmentAdapter extends FragmentStatePagerAdapter {
    private CyberScouterWordCloud[] wordCloudTeams;

    public WordCloudFragmentAdapter(FragmentManager fm) { super(fm);}

    public void setItems(CyberScouterWordCloud[] _wct) {
        this.wordCloudTeams = _wct;
    }

    @Override
    public Fragment getItem(int i) {
        return new WordCloudFragment();
    }

    @Override
    public int getCount( ) {
        return wordCloudTeams.length;
    }
}
