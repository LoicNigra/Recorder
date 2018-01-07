package com.audiorecorder.recorder;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.view.View;

import com.audiorecorder.recorder.Activities.MainActivity;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

import static com.audiorecorder.recorder.Activities.MainActivity.*;
import static com.audiorecorder.recorder.Methodes.Opnemen.*;
import static org.junit.Assert.*;


public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void timer_isCorrect() throws Exception {

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (seconds == 60){
                    assertEquals(60,0);
                }

            }
        }, 0, 1000);


    }
    @Test
    public void notification() throws Exception {
        NotificationManager nm = (NotificationManager) MainActivity.getMainContext().getSystemService(NOTIFICATION_SERVICE);

      if(nm != null) {
          nm.notify(uniqueID, notification.build());
          assertEquals(69, 0);
      }
    }

}