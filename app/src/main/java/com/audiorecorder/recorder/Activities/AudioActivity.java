package com.audiorecorder.recorder.Activities;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.os.Process;

import com.audiorecorder.recorder.Adapters.PagerAdapter;
import com.audiorecorder.recorder.R;

import static com.audiorecorder.recorder.Activities.MainActivity.*;
import static com.audiorecorder.recorder.Methodes.LijstTonen.*;

public class AudioActivity extends AppCompatActivity {

    private static final String TAG = "AudioActivity";
    private static Context audioContext;
    public static AudioActivity audioActivity;


    public static Context getAudioContext(){
        return  AudioActivity.audioActivity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        Tabs();
        AudioActivity.audioActivity = this;
        AudioActivity.audioContext = getApplicationContext();
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
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setCheckable(true);
                }
                intent = new Intent(this, AudioActivity.class);
                startActivity(intent);
                return true;

            case R.id.Quit:
                NotificationManager nm = (NotificationManager) getMainContext().getSystemService(NOTIFICATION_SERVICE);
                nm.cancel(MainActivity.uniqueID);
                mainActivity.finish();
                this.finish();


            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void onBackPressed(){
        RelativeLayout mainRelative = (RelativeLayout) findViewById(R.id.main_view);
        RelativeLayout audioRelative = (RelativeLayout) findViewById(R.id.audio_view);
        if(audioRelative != null && audioRelative.getVisibility() == View.VISIBLE){
            audioRelative.setVisibility(View.GONE);
            finish();
        } else if (mainRelative != null && mainRelative.getVisibility() == View.VISIBLE) {
            mainRelative.setVisibility(View.GONE);
            finish();
        }
        else {
            super.onBackPressed();
        }
    }

    public void Tabs() {

        TabLayout tabLayout =  findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.Bestanden));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.Hoofdscherm));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());


// Wanneer switch weg doen => Dan werkt tabs, maar veranderd activity niet
                switch (tab.getPosition()) {
                    case 0:
                        TabClick2();
                        Log.i("MainActivity", "Main Activity clicked");
                        break;
                    case 1:
                          TabClick();
                        Log.i("AudioActivity", "Audio Activity clicked");
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

    public void TabClick() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void TabClick2() {
        Intent intent = new Intent(this, AudioActivity.class);
        startActivity(intent);

    }



}

