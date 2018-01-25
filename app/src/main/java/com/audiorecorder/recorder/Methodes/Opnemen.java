package com.audiorecorder.recorder.Methodes;

import android.media.MediaRecorder;
import android.os.Environment;
import android.widget.TextView;

import com.audiorecorder.recorder.R;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;


public class Opnemen {



     public static int teller = 0 ;
     public static String directory = Environment.getExternalStorageDirectory().getPath() + "/" ;

     private int _id;
     public static String _fileName = "audio" + teller + ".3gpp";

     public static String bestand = directory + _fileName;
     public static File outFile = new File(directory, _fileName);

     public int get_id(){
     return _id;
     }
    public  String get_fileName(){
    return  _fileName;
    }

    public static Timer timer;
    public static int seconds = 0;
    public static int minutes = 0;


     public  static void opnemen() throws Exception {

         Controle.StopMediarecorder();
         while(outFile.exists()){

             teller++;
             _fileName = "/audio" + teller + ".3gpp";
             outFile = new File(directory, _fileName);
             bestand = directory + _fileName;
         }

        Variabelen.mediaRecorder = new MediaRecorder();
        Variabelen.mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        Variabelen.mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        Variabelen.mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        Variabelen.mediaRecorder.setOutputFile(bestand);
        Variabelen.mediaRecorder.prepare();
        Variabelen.mediaRecorder.start();

    }
}
