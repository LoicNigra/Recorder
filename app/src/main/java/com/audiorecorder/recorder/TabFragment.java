package com.audiorecorder.recorder;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

public class TabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.tab_fragment, container, false);
        return view;
    }


/*
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction){
        if (tab.getText().equals(getString(R.string.Main))){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return;
        } if (tab.getText().equals(getString(R.string.Files))){
            Intent intent = new Intent(this, AudioActivity.class);
            startActivity(intent);
            return;
        }
    }
    */


}
