package com.audiorecorder.recorder;

import android.Manifest;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.Toast;

// import static com.audiorecorder.recorder.Permissies.*;
import static com.audiorecorder.recorder.Opnemen.*;
import static com.audiorecorder.recorder.Stoppen.*;
import static com.audiorecorder.recorder.Afspelen.*;
import static com.audiorecorder.recorder.Variabelen.*;
import static com.audiorecorder.recorder.TabFragment_Main.*;
import android.widget.RelativeLayout;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Permissies();
        Tabs();

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


    public void Tabs() {

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.Hoofdscherm));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.Bestanden));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                switch (tab.getPosition()) {
                    case 0:
                        onClick();
                        break;
                    case 1:
                        onClick2();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view, menu);
        return true;
    }

    @Override
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


}


