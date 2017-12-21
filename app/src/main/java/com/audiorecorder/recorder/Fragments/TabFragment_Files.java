package com.audiorecorder.recorder.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.audiorecorder.recorder.Activities.AudioActivity;
import com.audiorecorder.recorder.R;

import static com.audiorecorder.recorder.Activities.AudioActivity.getAudioContext;

public class TabFragment_Files extends Fragment {

    public static final String FRAGMENT_TAG = "Files";



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.tab_fragment_files, container, false);
        return view;

    }


/*


*/

}
