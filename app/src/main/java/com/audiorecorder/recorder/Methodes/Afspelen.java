package com.audiorecorder.recorder.Methodes;

import android.media.MediaPlayer;
import java.io.IOException;
import static com.audiorecorder.recorder.Methodes.Controle.*;
import static com.audiorecorder.recorder.Methodes.Opnemen.*;


public class Afspelen {

   public static void afspelen() throws IOException {

        StopMediaPlayer();
        Variabelen.mediaPlayer = new MediaPlayer();
        Variabelen.mediaPlayer.setDataSource(bestand);
        Variabelen.mediaPlayer.prepare();
        Variabelen.mediaPlayer.start();
    }
}