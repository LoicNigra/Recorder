package com.audiorecorder.recorder.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.audiorecorder.recorder.Activities.MainActivity;
import com.audiorecorder.recorder.R;

import static com.audiorecorder.recorder.Activities.MainActivity.getMainContext;

public class TabFragment_Main extends Fragment {

    public static final String FRAGMENT_TAG = "Main";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_main, container, false);
        return view;


    }
}
