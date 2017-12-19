package com.audiorecorder.recorder;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.audiorecorder.recorder.AudioActivity.audioActivity;
import static com.audiorecorder.recorder.AudioActivity.getAudioContext;
import static com.audiorecorder.recorder.Permissies.*;
import static com.audiorecorder.recorder.Opnemen.*;
import static com.audiorecorder.recorder.Stoppen.*;
import static com.audiorecorder.recorder.Afspelen.*;

import static com.audiorecorder.recorder.Variabelen.*;
// import static com.audiorecorder.recorder.TabClick.*;



import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static Context Maincontext;
    static MainActivity mainActivity;

    static MyDBHandler dbHandler;
    static NotificationCompat.Builder notification;
    private static final int uniqueID = 69;

    public static Context getMainContext(){
        return  MainActivity.Maincontext;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.mainActivity = this;
        MainActivity.Maincontext = getApplicationContext();
        Permissies();
        Tabs();



       dbHandler = new MyDBHandler(this, null, null, 1);
       notification = new NotificationCompat.Builder(this);
        //notification.setAutoCancel(true);  ==> NIET, Notification enkel weg wanneer opnemen gestopt
/*
        Client myClient = new Client(serverAdress, serverPort, response);
        myClient.execute();
*/
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


    public void Tabs() {

        TabLayout tabLayout =  findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.Hoofdscherm));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.Bestanden));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
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
                        TabClick();
                        break;
                    case 1:
                        TabClick2();
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
        finish();
    }

    public void TabClick2() {
        Intent intent = new Intent(this, AudioActivity.class);
        startActivity(intent);
        finish();
    }

    public void Klik(View view) {
        switch (view.getId()) {
            case R.id.opnemen:
                try {
                    opnemen();
                    // Opslaan in DB
                    AudioBestand audioBestand = new AudioBestand();
                    MainActivity.dbHandler.addAudio(audioBestand);

                    notification.setSmallIcon(R.drawable.logo);
                    notification.setTicker("Recorder is recording");
                    notification.setWhen(System.currentTimeMillis());
                    notification.setContentTitle("Recording");
                    notification.setContentText("Recorder is recording");

                    //Intent vanuit een andere 'APP' naar deze te gaan
                    Intent i = new Intent(MainActivity.getMainContext(), MainActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(mainActivity, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                    notification.setContentIntent(pendingIntent);
                    //Build Notification
                    NotificationManager nm = (NotificationManager) MainActivity.getMainContext().getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(uniqueID, notification.build());

                    Toast.makeText(MainActivity.getMainContext(), "Bezig met opnemen van opname " + teller, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.getMainContext(), "Er is een probleem opgetreden met het opnemen", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                break;
            case R.id.stoppen:
                try {
                    OpnemenStoppen();
                    NotificationManager nm = (NotificationManager) MainActivity.getMainContext().getSystemService(NOTIFICATION_SERVICE);
                    nm.cancel(uniqueID);
                    Toast.makeText(MainActivity.getMainContext(), "Opname is gestopt van opname " + teller, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.getMainContext(), "Er is een probleem opgetreden het stoppen", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                break;
            case R.id.afspelen:
                try {

                    afspelen();
                    Toast.makeText(MainActivity.getMainContext(), "Opname " + teller + " is aan het afspelen", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.stopAfspelen:
                try {
                    AfspelenStoppen();
                    Toast.makeText(MainActivity.getMainContext(), "Het afspelen van opname " + teller + " is gestopt", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.getMainContext(), "Er is een probleem opgetreden het afspelen te stoppen", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                break;
        }
    }


}


