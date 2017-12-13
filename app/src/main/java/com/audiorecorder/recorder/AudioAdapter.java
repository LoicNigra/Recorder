package com.audiorecorder.recorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.ArrayList;

public class AudioAdapter extends BaseAdapter {
    Context c;
    ArrayList<AudioBestand> audiobestanden;

    public AudioAdapter(Context c, ArrayList<AudioBestand> audiobestanden) {
        this.c = c;
        this.audiobestanden = audiobestanden;
    }


    @Override
    public int getCount() {
        return audiobestanden.size();
    }

    @Override
    public Object getItem(int i) {
        return audiobestanden.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(c).inflate(R.layout.activity_audio, viewGroup, false);
        }
        final AudioBestand audioBestand = (AudioBestand) this.getItem(i);
        TextView title = (TextView) view.findViewById(R.id.AudioLijst);

        title.setText(audioBestand.getTitle());

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //LOGICA VOOR AFSPELEN
            }
        });
        return view;
    }
}
