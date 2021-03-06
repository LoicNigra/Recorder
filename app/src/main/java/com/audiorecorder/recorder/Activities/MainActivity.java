package com.audiorecorder.recorder.Activities;


import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.audiorecorder.recorder.Database.AudioBestand;
import com.audiorecorder.recorder.Database.MyDBHandler;
import com.audiorecorder.recorder.Adapters.PagerAdapter;
import com.audiorecorder.recorder.R;
import com.audiorecorder.recorder.Server.Client;


import java.util.Timer;
import java.util.TimerTask;

import static com.audiorecorder.recorder.Activities.AudioActivity.*;
import static com.audiorecorder.recorder.Methodes.Permissies.*;
import static com.audiorecorder.recorder.Methodes.Opnemen.*;
import static com.audiorecorder.recorder.Methodes.Stoppen.*;
import static com.audiorecorder.recorder.Methodes.Afspelen.*;
import static com.audiorecorder.recorder.Methodes.Stoppen.OpnemenStoppen;
import static com.audiorecorder.recorder.Server.Client.*;


public class MainActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    public static Context Maincontext;
    @SuppressLint("StaticFieldLeak")
    public static MainActivity mainActivity;

    public static MyDBHandler dbHandler;
    public static NotificationCompat.Builder notification;
    public static final int uniqueID = 69;

    public Timer timer;

    public static Context getMainContext() {
        return MainActivity.Maincontext;
    }

    private TextView tv;


    public Intent i;
    private Button opnemenKnop;
    private Button afspelenKnop;


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


        tv = findViewById(R.id.timerView);
        tv.setVisibility(View.INVISIBLE);


        opnemenKnop =  findViewById(R.id.opnemen);
        opnemenKnop.setTag(1);

        afspelenKnop =  findViewById(R.id.afspelen);
        afspelenKnop.setTag(1);

        Client myClient = new Client(serverAdress, serverPort, response);
        myClient.execute();


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
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setCheckable(true);
                }
                intent = new Intent(this, AudioActivity.class);
                startActivity(intent);
                return true;

            case R.id.Quit:
                NotificationManager nm = (NotificationManager) MainActivity.getMainContext().getSystemService(NOTIFICATION_SERVICE);
                if (nm != null) {
                    nm.cancel(MainActivity.uniqueID);
                }
                this.finishAffinity();

            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public void onBackPressed() {
        RelativeLayout mainRelative =  findViewById(R.id.main_view);
        RelativeLayout audioRelative = findViewById(R.id.audio_view);
        if (audioRelative != null && audioRelative.getVisibility() == View.VISIBLE) {
            audioRelative.setVisibility(View.INVISIBLE);
            finish();
        } else if (mainRelative != null && mainRelative.getVisibility() == View.VISIBLE) {
            mainRelative.setVisibility(View.INVISIBLE);
            finish();
        } else {
            super.onBackPressed();
        }
    }

    public void Tabs() {


        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.Hoofdscherm));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.Bestanden));
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

                    final int statusOpnemen = (Integer) view.getTag();

                    if (statusOpnemen == 1) {
                        opnemen();
                        opnemenKnop.setText(R.string.Stoppen);
                        afspelenKnop.setEnabled(false);
                        view.setTag(0);

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
                        if (nm != null) {
                            nm.notify(uniqueID, notification.build());
                        }

                        timer = new Timer();
                        timer.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {

                                    @SuppressLint("SetTextI18n")
                                    @Override
                                    public void run() {
                                        tv.setVisibility(View.VISIBLE);
                                        tv.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));
                                        seconds++;

                                        if (seconds == 60) {
                                            tv.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));
                                            seconds = 0;
                                            minutes = minutes + 1;
                                        }
                                    }
                                });
                            }
                        }, 0, 1000);
                        Toast.makeText(MainActivity.getMainContext(), "Bezig met opnemen van opname " + teller, Toast.LENGTH_LONG).show();


                    } else {
                        OpnemenStoppen();
                        tv.setVisibility(View.INVISIBLE);
                        opnemenKnop.setText(R.string.Opnemen);
                        afspelenKnop.setEnabled(true);
                        view.setTag(1);
                        NotificationManager nm = (NotificationManager) MainActivity.getMainContext().getSystemService(NOTIFICATION_SERVICE);
                        if (nm != null) {
                            nm.cancel(uniqueID);
                        }

                        if (timer != null) {
                            timer.cancel();
                            minutes = 0;
                            seconds = 0;
                        }
                        Toast.makeText(MainActivity.getMainContext(), "Opname is gestopt van opname " + teller, Toast.LENGTH_LONG).show();

                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.getMainContext(), "Er is een probleem opgetreden met het opnemen", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                break;


            case R.id.afspelen:
                try {

                    final int statusAfspelen = (Integer) view.getTag();

                    if (statusAfspelen == 1) {

                        afspelen();
                        afspelenKnop.setText(R.string.StopAfspelen);
                        opnemenKnop.setEnabled(false);
                        view.setTag(0);
                        Toast.makeText(MainActivity.getMainContext(), "Opname " + teller + " is aan het afspelen", Toast.LENGTH_LONG).show();
                    } else {

                        AfspelenStoppen();
                        afspelenKnop.setText(R.string.Afspelen);
                        opnemenKnop.setEnabled(true);
                        view.setTag(1);
                        Toast.makeText(MainActivity.getMainContext(), "Het afspelen van opname " + teller + " is gestopt", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            break;


        }
    }
}




