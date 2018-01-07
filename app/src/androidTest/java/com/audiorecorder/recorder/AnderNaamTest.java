package com.audiorecorder.recorder;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;

import com.audiorecorder.recorder.Activities.MainActivity;
import com.audiorecorder.recorder.Methodes.Afspelen;
import com.audiorecorder.recorder.Methodes.Opnemen;
import com.audiorecorder.recorder.Methodes.Stoppen;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.audiorecorder.recorder.Methodes.Opnemen.opnemen;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AnderNaamTest {

    private Button opnemenView;
    private Button afspelenView;


    private MainActivity mTestActivity;


    @Before
    public void setUp() throws Exception {

        mTestActivity = MainActivity.mainActivity;
        opnemenView = mTestActivity.findViewById(R.id.opnemen);
        afspelenView =  mTestActivity.findViewById(R.id.afspelen);
    }

    @Test
    public void testPreconditions() {
        Assert.assertNotNull("mTestActivity is null", mTestActivity);
        Assert.assertNotNull("opnemenView is null", opnemenView);
        Assert.assertNotNull("afspelenView is null", afspelenView);
    }
    @Test
    public void Klik_True() throws Exception {
        if (opnemenView.getId() == R.id.opnemen){
            Opnemen.opnemen();
        }
        if (afspelenView.getId() == R.id.afspelen){
            Afspelen.afspelen();
        }
    }
}
