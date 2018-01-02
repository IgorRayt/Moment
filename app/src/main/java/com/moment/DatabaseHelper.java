package com.moment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by irayt1063 on 10/14/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
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

    public static final String USER_DATA_TABLE_NAME = "user_data";
    public static final String ID_COL_USER_DATA = "ID";
    public static final String USER_NAME_USER_DATA = "user_name";
    public static final String USER_EMAIL_USER_DATA = "user_email";
    public static final String PHONE_TIME_SPENT_USER_DATA = "time_spent";
    public static final String PHONE_TIME_GOAL_USER_DATA = "time_goal";
    public static final String SQL_CREATE_USER_DATA_TB = "CREATE TABLE IF NOT EXISTS " +
            USER_DATA_TABLE_NAME + " (" + ID_COL_USER_DATA +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_NAME_USER_DATA + " VARCHAR(50), " +
            USER_EMAIL_USER_DATA + " VARCHAR(50), " + PHONE_TIME_SPENT_USER_DATA +
            " INTEGER, " + PHONE_TIME_GOAL_USER_DATA + " INTEGER);";
    public static final String SQL_GET_ALL_DATA_USER_DATA_TB = "SELECT * FROM " +
            USER_DATA_TABLE_NAME + ";";
    public static final String SQL_GET_USER_NAME_USER_DATA_TB = "SELECT " + USER_NAME_USER_DATA +
            " FROM " + USER_DATA_TABLE_NAME + ";";
    public static final String SQL_GET_USER_GOAL_HOURS_USER_DATA_TB = "SELECT" +
            PHONE_TIME_GOAL_USER_DATA + " FROM " + USER_DATA_TABLE_NAME + ";";


    //useless table!!!!! Delete after marking the assignment 2!!!!!!!!!!!!
    public static final String USELESS_TABLE_NAME = "useless_table";
    public static final String ID_COL_USELESS_TABLE = "ID";
    public static final String USELESS_COL_USELESS_TABLE = "useless_col";
    public static final String SQL_CREATE_USELESS_TABLE = "CREATE TABLE IF NOT EXISTS " +
            USELESS_TABLE_NAME  + " (" + ID_COL_USELESS_TABLE +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + USELESS_COL_USELESS_TABLE +
            " VARCHAR(100));";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_SCREEN_TIME_TB);
        db.execSQL(SQL_CREATE_USER_DATA_TB);
        db.execSQL(SQL_CREATE_USELESS_TABLE);
        fill_in_useless_table(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS " + SCREEN_TIME_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_DATA_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USELESS_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(Integer timeOn, Integer timeOff, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL_CREATE_SCREEN_TIME_TB);
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

    public Cursor getDateData(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("y-D");
        String SQL_GET_DATE_ALL_DATA = "SELECT * FROM " +
                SCREEN_TIME_TABLE_NAME + " WHERE Date = '" + dateFormat.format(date) +  "';";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery(SQL_GET_DATE_ALL_DATA, null);
        return data;
    }

    public Cursor getDataFromDate(Long dateLong){
        String SQL_GET_DATA_FROM_DATE = "SELECT * FROM " +
                SCREEN_TIME_TABLE_NAME + " WHERE TimeOn > '" + dateLong +  "';";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery(SCREEN_TIME_TABLE_NAME, null);
        return data;
    }

    public Cursor getAllPhonePickUpsData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery(SQL_GET_ALL_PHONE_PICK_UPS, null);
        return data;
    }

    public Cursor getDatePhonePickUps(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("y-D");
        String SQL_GET_DATE_PHONE_PICK_UPS = "SELECT COUNT(*) FROM " +
                SCREEN_TIME_TABLE_NAME + " WHERE Date = '" + dateFormat.format(date) +  "';";
        //String SQL_GET_TODAY_PHONE_PICK_UPS = "SELECT COUNT(*) FROM " + SCREEN_TIME_TABLE_NAME + " ;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery(SQL_GET_DATE_PHONE_PICK_UPS, null);
        return data;
    }

    private void fill_in_useless_table(SQLiteDatabase db){
        int rows_amount = 10;
        String SQL_FILL_IN_USELESS_TABLE = "";
        String uuid = "";
        for (int i = 0; i < rows_amount; i++) {
            uuid = UUID.randomUUID().toString();
            SQL_FILL_IN_USELESS_TABLE = "INSERT INTO " + USELESS_TABLE_NAME +
                    " (" + USELESS_COL_USELESS_TABLE +") VALUES ('" + uuid + "');";
            db.execSQL(SQL_FILL_IN_USELESS_TABLE);
        }
    }

    public Cursor getUserData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery(SQL_GET_ALL_DATA_USER_DATA_TB, null);
        return data;
    }

    public Boolean insertUserData(String userName, String userEmail, int phoneTimeSpent,
                                  int phoneTimeGoal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME_USER_DATA, userName);
        contentValues.put(USER_EMAIL_USER_DATA, userEmail);
        contentValues.put(PHONE_TIME_SPENT_USER_DATA, phoneTimeSpent);
        contentValues.put(PHONE_TIME_GOAL_USER_DATA, phoneTimeGoal);

        long insertResult = db.insert(USER_DATA_TABLE_NAME, null, contentValues);
        if (insertResult == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getUserDailyHoursLimit(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery(SQL_GET_USER_GOAL_HOURS_USER_DATA_TB, null);
        return data;
    }

}
