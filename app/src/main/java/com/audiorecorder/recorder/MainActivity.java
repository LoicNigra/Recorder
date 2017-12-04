package com.audiorecorder.recorder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.system.Os.mkdir;


public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private MediaRecorder mediaRecorder;
    private String outputfile;
    private int teller = 0;
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Permissies();

        // Wordt uiteindelijk mounted/recorders.3gpp

        outputfile = "/mnt/sdcard/record"+teller+".3gpp";


    }

    public void Klik(View view){
        switch(view.getId()){
            case R.id.opnemen:
                try{

                    Opnemen();
                }catch (Exception e){
                //    Toast.makeText(getApplicationContext(), "Er is een probleem opgetreden met het opnemen", Toast.LENGTH_LONG).show();
                e.printStackTrace();
                }
                break;
            case R.id.stoppen:
                try{
                    Stoppen();
                }catch (Exception e) {
                  //  Toast.makeText(getApplicationContext(), "Er is een probleem opgetreden het stoppen", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                break;
            case R.id.afspelen:
                try{
                    Afspelen();
                }catch (Exception e) {
                  //  Toast.makeText(getApplicationContext(), "Er is een probleem opgetreden het afspelen", Toast.LENGTH_LONG).show();
                e.printStackTrace();
                }
                break;
            case R.id.stopAfspelen:
                try{
                    AfspelenStoppen();
                }catch (Exception e) {
                   // Toast.makeText(getApplicationContext(), "Er is een probleem opgetreden het afspelen te stoppen", Toast.LENGTH_LONG).show();
               e.printStackTrace();
                }
                break;
        }
    }


    private void Opnemen() throws Exception {



         // Moest er al een recorder opstaan, stoppen we die
        StopMediarecorder();

        //aanmaken van file
        File outFile = new File(outputfile);
        outFile.mkdirs();

            //Als deze file al bestaat => Verwijderen, we doen een overwrite
            if(outFile.exists()) {
                outFile.delete();
            }
            // Mediarecorder opzetten
            mediaRecorder = new MediaRecorder();
            // De source opzetten van waar de audio in komt => Dit is de microfoon
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            // De extension
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            // De encoder => Later dan versie 2.3.3, kan AMR_WB => Voor compatibilty doen we de NB, NarrowBand
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            // Waar opslaan
            mediaRecorder.setOutputFile(outputfile);
            // Klaarzetten
          mediaRecorder.prepare();
            //Starten
            mediaRecorder.start();

    }


    private void Stoppen() {
        // als mediaRecorder afspeelt/bestaat, stoppen we deze
        if(mediaRecorder != null){
            mediaRecorder.stop();
        }

    }


    private void Afspelen() throws IOException {



            // Checken als er recorder aanstaat, zoja dan stoppen
        StopMediaPlayer();

        mediaPlayer=new MediaPlayer();
        // De gegevens van de outputfile nemen
        mediaPlayer.setDataSource(outputfile);
        // mediaPlayer Klaarzetten
        mediaPlayer.prepare();
        //media afspelen
        mediaPlayer.start();
    }

    private void AfspelenStoppen() {
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
    }

    private void StopMediarecorder() {
        if(mediaRecorder != null){
                mediaPlayer.release();
        }
    }


    private void StopMediaPlayer(){
        if(mediaPlayer !=null)
        {
            try{
                mediaPlayer.release();
            }catch (Exception e){
               // Toast.makeText(getApplicationContext(), "Er is een probleem opgetreden.", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }


    /* ALLES ZIT IN FUNCTIE Permissies!

    private boolean CheckPermissieWrite() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED
                ) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE }, 1);
                return false;
            }
        } else {
            return true;
        }
    }


    private boolean CheckPermissieRead() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE }, 2);

                return false;
            }
        } else {
            return true;
        }
    }


    private boolean CheckPermissieMic() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO }, 3);

                return false;
            }
        } else {
            return true;
        }
    }


*/



    private  boolean Permissies() {
        int permissionRecord = ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO);
        int permissionWrite = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionRead =  ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionRecord != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECORD_AUDIO);
        }
        if (permissionWrite != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (permissionRecord != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }

        return true;
    }
}
