package com.audiorecorder.recorder.Methodes;


public class Controle {

    public static void StopMediaPlayer() {
        if (Variabelen.mediaPlayer != null) {
            try {
                Variabelen.mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void StopMediarecorder() {
        if (Variabelen.mediaRecorder != null) {
            Variabelen.mediaRecorder.release();
        }
    }


}
