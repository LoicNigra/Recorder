package com.audiorecorder.recorder;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AudioActivity extends Activity {

    @Override
    protected void onCreate(Bundle audioInstanceState) {
        super.onCreate(audioInstanceState);
        setContentView(R.layout.activity_audio);
    }
}
