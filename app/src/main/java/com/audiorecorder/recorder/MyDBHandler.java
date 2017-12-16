package com.audiorecorder.recorder;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.support.design.widget.TabLayout;


public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "EHBrecorder.db";
    private static final String TABLE_AUDIO = "Audiobestand";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_FILENAME = "FileName";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Createquery = "CREATE TABLE " + TABLE_AUDIO + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FILENAME + " TEXT " +
                ");";
        db.execSQL(Createquery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUDIO);
        onCreate(db);
    }

    //Add new row to the database
    public void addAudio(AudioBestand audioBestand){
        ContentValues values = new ContentValues();
        values.put(COLUMN_FILENAME, audioBestand.getTitle());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_AUDIO, null, values);
        db.close();
    }


    //Delete van database
    public void deleteAudio(String audioName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_AUDIO + " WHERE " + COLUMN_FILENAME + "=\""+audioName + "\";");
    }


    //Print db as string
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_AUDIO + "WHERE 1";

        //Cursor naar locatie
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("_fileName")) != null){
                dbString += c.getString(c.getColumnIndex("_fileName"));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;
    }

}
