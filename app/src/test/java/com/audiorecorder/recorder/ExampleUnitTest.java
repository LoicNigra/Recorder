package com.audiorecorder.recorder;

import org.junit.Test;

import static com.audiorecorder.recorder.Methodes.Opnemen.*;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void timer_isCorrect() throws Exception {
        seconds = 60;
        minutes = 0;

        if (seconds == 60){
            assertEquals(60,0);
        }

    }
}