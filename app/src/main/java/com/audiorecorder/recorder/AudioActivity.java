package com.audiorecorder.recorder;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;

import static com.audiorecorder.recorder.Afspelen.afspelen;
import static com.audiorecorder.recorder.Variabelen.*;

public class AudioActivity extends Activity {

    @Override
    protected void onCreate(Bundle audioInstanceState) {
        super.onCreate(audioInstanceState);
        setContentView(R.layout.activity_audio);

        TabHost th = (TabHost) findViewById(R.id.tabhost);
        th.setup();

        TabHost.TabSpec specs = th.newTabSpec("tag1");
        specs.setContent(R.id.Main);
        specs.setIndicator("Main");
        th.addTab(specs);

        specs = th.newTabSpec("tag2");
        specs.setContent(R.id.Files);
        specs.setIndicator("Files");
        th.addTab(specs);

        getAudio();
        LijstTonen();
    }


    public void getAudio(){
        ContentResolver contentResolver = getContentResolver();
        Uri audioUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor audioCursor = contentResolver.query(audioUri, null,null,null,null );

        if (audioCursor != null && audioCursor.moveToFirst()){
            int audioTitle = audioCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);

            do {
                String Title = audioCursor.getString(audioTitle);
                arrayList.add(Title);

            } while (audioCursor.moveToNext());
        }
    }


    public void LijstTonen(){

        listView = listView.findViewById(R.id.AudioLijst);
        arrayList = new ArrayList<>();
        getAudio();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                try {

                    afspelen();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }




}
