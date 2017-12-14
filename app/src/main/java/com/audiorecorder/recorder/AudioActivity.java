package com.audiorecorder.recorder;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.audiorecorder.recorder.Afspelen.afspelen;
import static com.audiorecorder.recorder.Controle.StopMediaPlayer;
import static com.audiorecorder.recorder.Controle.StopMediarecorder;
import static com.audiorecorder.recorder.Variabelen.*;
import static com.audiorecorder.recorder.Opnemen.*;

public class AudioActivity extends AppCompatActivity {

    private static final String TAG = "AudioActivity";
    ArrayList<String> audioList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        Tabs();
        LijstTonen();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.MainMenu:
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setCheckable(true);
                }
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.FilesMenu:
                if(item.isChecked()){
                    item.setChecked(false);
                } else {
                    item.setCheckable(true);
                }
                intent = new Intent(this, AudioActivity.class);
                startActivity(intent);
                return true;

            case R.id.Quit:
                System.exit(0);

            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    public ArrayList<String> getAudio() {

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


    @SuppressLint("NewApi")
    public void LijstTonen() {

        Cursor musiccursor;


        getAudio();

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, audioList);
        final ListView listView = (ListView) findViewById(R.id.AudioLijst);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String audio = String.valueOf(parent.getItemAtPosition(position));
                        try {
                            StopMediaPlayer();
                            mediaPlayer = new MediaPlayer();

                            mediaPlayer.setDataSource(directory + audio);

                            mediaPlayer.prepare();

                            mediaPlayer.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(), "Aan het afspelen: " + audio, Toast.LENGTH_LONG).show();
                    }
                }


        );
    }


    public void Tabs() {


        final TabHost th = (TabHost) findViewById(R.id.tabhost);
        th.setup();


        TabHost.TabSpec specs = th.newTabSpec("Files");
        specs.setContent(R.id.Files);
        specs.setIndicator("Files");
        th.addTab(specs);

        TabHost.TabSpec specs2 = th.newTabSpec("Main");
        specs2.setContent(R.id.Main);
        specs2.setIndicator("Main");
        th.addTab(specs2);


        th.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            public void onTabChanged(String tabId) {

                switch (th.getCurrentTab()) {
                    case 0:
                        onClick2();
                        break;
                    case 1:
                        onClick();
                        break;

                }
            }
        });

    }

    public void onClick() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClick2() {
        Intent intent = new Intent(this, AudioActivity.class);
        startActivity(intent);
        finish();
    }


}

