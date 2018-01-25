package com.audiorecorder.recorder.Methodes;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.audiorecorder.recorder.R;

import java.io.IOException;

import static com.audiorecorder.recorder.Activities.AudioActivity.*;


public class LijstTonen {

    @SuppressLint("NewApi")
    public static void LijstTonen() {

        getAudio getaudio = new getAudio();
        getaudio.doInBackground();

        ListAdapter adapter = new ArrayAdapter<String>(getAudioContext(), android.R.layout.simple_list_item_1, getAudio.audioList);
        final ListView listView = (ListView) audioActivity.findViewById(R.id.AudioLijst);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String audio = String.valueOf(parent.getItemAtPosition(position));
                        try {
                            Controle.StopMediaPlayer();
                            Variabelen.mediaPlayer = new MediaPlayer();

                            Variabelen.mediaPlayer.setDataSource(Opnemen.directory + audio);

                            Variabelen.mediaPlayer.prepare();

                            Variabelen.mediaPlayer.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getAudioContext(), audio, Toast.LENGTH_LONG).show();
                    }
                }
        );
    }



}
