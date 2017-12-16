package com.audiorecorder.recorder;


import static com.audiorecorder.recorder.Opnemen.*;

public class AudioBestand {

    String _title = _fileName;
    int _id;

    public AudioBestand(){
    }


    public AudioBestand(String title){
        this._title = title;
    }

    void setTitle(String title) {
        this._title = title;
    }
    String getTitle() {
        return _title;
    }

    public int get_id() {
        return _id;
    }
    public void set_id(int _id) {
        this._id = _id;
    }
}
