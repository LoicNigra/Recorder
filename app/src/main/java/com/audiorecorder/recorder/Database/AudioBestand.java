package com.audiorecorder.recorder.Database;


import static com.audiorecorder.recorder.Methodes.Opnemen.*;

public class AudioBestand {

    public String _title = _fileName;
    public int _id;

    public AudioBestand(){
    }


    public AudioBestand(String title){
        this._title = title;
    }

    public  void setTitle(String title) {
        this._title = title;
    }
    public String getTitle() {
        return _title;
    }

    public int get_id() {
        return _id;
    }
    public void set_id(int _id) {
        this._id = _id;
    }
}
