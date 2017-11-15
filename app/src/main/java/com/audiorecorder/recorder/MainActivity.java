package com.audiorecorder.recorder;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button afspelen, opnemen, stoppen;
    private MediaRecorder myAudioRecorder;
    private String outputFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        afspelen = (Button) findViewById(R.id.afspelen);
        stoppen = (Button) findViewById(R.id.stoppen);
        opnemen = (Button) findViewById(R.id.opnemen);

        stoppen.setEnabled(false);
        opnemen.setEnabled(true);

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/record.3gp";

        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);

        opnemen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)  {
                try {
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                } catch (IllegalStateException e) {
                    Toast.makeText(getApplicationContext(), "Er is een probleem opgetreden", Toast.LENGTH_LONG).show();
                    System.exit(0);
            } catch (IOException IOe) {
                    Toast.makeText(getApplicationContext(), "Er is een probleem opgetreden", Toast.LENGTH_LONG).show();
                    System.exit(0);

            }

                opnemen.setEnabled(false);
                stoppen.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Opnemen is begonnen", Toast.LENGTH_LONG).show();
                }
        });

        stoppen.setOnClickListener((new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;
                stoppen.setEnabled(false);
                opnemen.setEnabled(true);
                afspelen.setEnabled(true);
                Toast.makeText(getApplicationContext(), "De opname is succesvol afgerond", Toast.LENGTH_LONG).show();
            }
        }));

        afspelen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               MediaPlayer mediaPlayer =  new MediaPlayer();

               try {
                   mediaPlayer.setDataSource(outputFile);
                   mediaPlayer.prepare();
                   mediaPlayer.start();

                   Toast.makeText(getApplicationContext(), "Audio is nu aan het afspelen", Toast.LENGTH_LONG).show();

               }catch (IOException IOe) {
                   Toast.makeText(getApplicationContext(), "Er is een probleem opgetreden, audio wordt niet afgespeeld", Toast.LENGTH_LONG).show();
                   System.exit(0);
               }
            }
        });
    }
}
