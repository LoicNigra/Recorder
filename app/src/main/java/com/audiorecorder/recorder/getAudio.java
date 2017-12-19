
package com.audiorecorder.recorder;


import android.os.AsyncTask;

import java.io.File;
import java.util.ArrayList;

import static com.audiorecorder.recorder.Opnemen.*;

public class getAudio extends AsyncTask<String, Integer, ArrayList<String>> {

    static ArrayList<String> audioList;


    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        audioList = new ArrayList<String>();

        File f = new File(directory);
        File[] files = f.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (outFile.getName().endsWith(".3gpp")) {
                audioList.add(files[i].getName());
            }
        }
        return audioList;
    }





}

