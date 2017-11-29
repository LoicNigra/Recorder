package com.audiorecorder.recorder;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private MediaRecorder mediaRecorder;
    private String outputfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Wordt uiteindelij mounted/recorders.3gpp
        outputfile = Environment.getExternalStorageState()+"/recorders.3gpp";
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
        //Waar gaan we het opslaan
        File outFile = new File(outputfile);
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

}
