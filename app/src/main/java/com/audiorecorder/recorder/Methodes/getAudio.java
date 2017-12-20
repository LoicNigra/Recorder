
package com.audiorecorder.recorder.Methodes;


import android.os.AsyncTask;

import java.io.File;
import java.util.ArrayList;

public class getAudio extends AsyncTask<String, Integer, ArrayList<String>> {

    static ArrayList<String> audioList;


    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        audioList = new ArrayList<String>();

        File f = new File(Opnemen.directory);
        File[] files = f.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().endsWith(".3gpp")) {
                audioList.add(files[i].getName());
            }
        }
        return audioList;
    }





}

