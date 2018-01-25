package com.audiorecorder.recorder.Methodes;

public class Stoppen {

    public static void AfspelenStoppen() {
        if (Variabelen.mediaPlayer != null) {
            Variabelen.mediaPlayer.stop();
        }
    }


    public static void OpnemenStoppen() {

        if (Variabelen.mediaRecorder != null) {
            Variabelen.mediaRecorder.stop();
        }
    }

}
