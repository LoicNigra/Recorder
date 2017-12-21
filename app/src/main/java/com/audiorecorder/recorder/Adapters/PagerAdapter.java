package com.audiorecorder.recorder.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.audiorecorder.recorder.Fragments.TabFragment_Files;
import com.audiorecorder.recorder.Fragments.TabFragment_Main;


public class PagerAdapter extends FragmentStatePagerAdapter {

    int aantTabs = 2;

    public PagerAdapter(FragmentManager fm, int tabs){
        super(fm);
        this.aantTabs = tabs;
    }

    public PagerAdapter(FragmentManager fm){
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TabFragment_Main main = new TabFragment_Main();
                return main;
            case 1:
                TabFragment_Files files = new TabFragment_Files();
                return files;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return aantTabs;
    }
}
