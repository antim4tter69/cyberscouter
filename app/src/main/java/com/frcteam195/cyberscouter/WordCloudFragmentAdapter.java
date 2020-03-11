package com.frcteam195.cyberscouter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class WordCloudFragmentAdapter extends FragmentStatePagerAdapter {

    public WordCloudFragmentAdapter(FragmentManager fm) { super(fm);}

    @Override
    public Fragment getItem(int i) {
        WordCloudFragment wcf = new WordCloudFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", i);
        wcf.setArguments(bundle);

        return(wcf);
    }

    @Override
    public int getCount( ) {
        return 2;
    }
}
