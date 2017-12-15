package com.audiorecorder.recorder;

import android.graphics.pdf.PdfDocument;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class PagerAdapter extends FragmentStatePagerAdapter {

    int aantTabs;

    public PagerAdapter(FragmentManager fm, int tabs){
        super(fm);
        this.aantTabs = tabs;
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
