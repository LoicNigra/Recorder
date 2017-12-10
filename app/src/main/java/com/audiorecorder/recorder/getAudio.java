/*

package com.audiorecorder.recorder;


import android.content.ContentResolver;
import android.database.Cursor;
import android.media.MediaRecorder;
import android.net.Uri;
import android.provider.MediaStore;

import static com.audiorecorder.recorder.AudioActivity.*;
import static com.audiorecorder.recorder.Opnemen.*;

public class getAudio {

    public void getAudio(){
        ContentResolver contentResolver = AudioActivity.getContentResolver();
        Uri audioUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor audioCursor = contentResolver.query(audioUri, null,null,null,null );

        if (audioCursor != null && audioCursor.moveToFirst()){
            int audioTitle = audioCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);

            do {
                String Title = audioCursor.getString(audioTitle);
                arrayList.add(Title);

            } while (audioCursor.moveToNext());
        }
    }

}

*/