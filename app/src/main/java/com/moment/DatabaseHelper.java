package com.moment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by irayt1063 on 10/14/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Tempus.db";
    public static final String SCREEN_TIME_TABLE_NAME = "screen_time";
    public static final String ID_COL_SCREEN_TIME = "ID";
    public static final String TIME_ON_COL_SCREEN_TIME= "TimeOn";
    public static final String TIME_OFF_COL_SCREEN_TIME = "TimeOff";
    public static final String DATE_COL_SCREEN_TIME = "Date";
    public static final String SQL_CREATE_SCREEN_TIME_TB =  "CREATE TABLE IF NOT EXISTS " +
            SCREEN_TIME_TABLE_NAME + " (" + ID_COL_SCREEN_TIME +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + TIME_ON_COL_SCREEN_TIME + " INTEGER, " +
            TIME_OFF_COL_SCREEN_TIME + " INTEGER, " + DATE_COL_SCREEN_TIME  +" VARCHAR(100));";
    public static final String SQL_GET_ALL_DATA_SCREEN_TIME_TB = "SELECT * FROM " +
            SCREEN_TIME_TABLE_NAME + ";";
    public static final String SQL_GET_ALL_PHONE_PICK_UPS = "SELECT COUNT(*) FROM " +
            SCREEN_TIME_TABLE_NAME  + ";";


    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_SCREEN_TIME_TB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS " + SCREEN_TIME_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(Integer timeOn, Integer timeOff, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TIME_ON_COL_SCREEN_TIME, timeOn);
        contentValues.put(TIME_OFF_COL_SCREEN_TIME, timeOff);
        contentValues.put(DATE_COL_SCREEN_TIME, date);
        long insertResult = db.insert(SCREEN_TIME_TABLE_NAME, null, contentValues);
        if (insertResult == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery(SQL_GET_ALL_DATA_SCREEN_TIME_TB, null);
        return  data;
    }

    public Cursor getAllPhonePickUpsData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery(SQL_GET_ALL_PHONE_PICK_UPS, null);
        return data;
    }

    public Cursor getTodayPhonePickUps(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String SQL_GET_TODAY_PHONE_PICK_UPS = "SELECT COUNT(*) FROM " +
                SCREEN_TIME_TABLE_NAME + " WHERE Date = '" + dateFormat.format(date) +  "';";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery(SQL_GET_TODAY_PHONE_PICK_UPS, null);
        return data;
    }



}
