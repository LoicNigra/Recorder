package com.audiorecorder.recorder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

// import static com.audiorecorder.recorder.Permissies.*;
import static com.audiorecorder.recorder.Opnemen.*;
import static com.audiorecorder.recorder.Stoppen.*;
import static com.audiorecorder.recorder.Afspelen.*;
import static com.audiorecorder.recorder.Variabelen.*;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


        Permissies();

    }

    public void Klik(View view) {
        switch (view.getId()) {
            case R.id.opnemen:
                try {
                    opnemen();
                    Toast.makeText(getApplicationContext(), "Bezig met opnemen van opname " + teller, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Er is een probleem opgetreden met het opnemen", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                break;
            case R.id.stoppen:
                try {
                    OpnemenStoppen();
                    Toast.makeText(getApplicationContext(), "Opname is gestopt van opname " + teller, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Er is een probleem opgetreden het stoppen", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                break;
            case R.id.afspelen:
                try {

                    afspelen();
                    Toast.makeText(getApplicationContext(), "Opname " + teller + " is aan het afspelen", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.stopAfspelen:
                try {
                    AfspelenStoppen();
                    Toast.makeText(getApplicationContext(), "Het afspelen van opname " + teller + " is gestopt", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Er is een probleem opgetreden het afspelen te stoppen", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                break;
        }
    }



    public boolean Permissies() {

        int permissionRecord = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        int permissionWrite = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionRead = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionRecord != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECORD_AUDIO);
        }
        if (permissionWrite != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (permissionRead != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }

        return true;
    }




}
