package com.moment;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by igor.rayt on 2017-10-26.
 */

public class DatabaseController {
    DatabaseHelper myDb;
    private Context appContext;

    DatabaseController(Context context){
        appContext = context;
    }

    public Integer getTodayPhonePickUps(){
        Date todayDate = new Date();
        Integer countDat = getDatePhonePickUps(todayDate);
        return countDat;
    }

    public Integer getDatePhonePickUps(Date date){
        myDb = new DatabaseHelper(appContext);
        Cursor data = myDb.getDatePhonePickUps(date);

        return getDataFromCountCursor(data);
    }

    public Integer getTotalPhonePickUps(){
        myDb = new DatabaseHelper(appContext);
        Cursor data = myDb.getAllPhonePickUpsData();

        return getDataFromCountCursor(data);
    }


    private Integer getDataFromCountCursor(Cursor data) {
        Integer countDat = 0;

        if (data.getCount() == 0) {
            //return "Error, could not retrieve Phone Pick Ups Data";
            return 0;
        }

        while (data.moveToNext()) {
            countDat = Integer.parseInt(data.getString(data.getColumnIndex("COUNT(*)")));
        }

        return countDat;
    }


    public Long getTodayPhoneUse(){
        Date todayDate = new Date();
        Long todayPhoneUseTime = getDatePhoneTimeUse(todayDate);
        return todayPhoneUseTime;
    }

    public Long getDatePhoneTimeUse(Date date){
        myDb = new DatabaseHelper(appContext);
        Cursor data = myDb.getDateData(date);

        return getPhoneUseTimeFromCursor(data);
    }

    public Long getTotalPhoneUseTime(){
        myDb = new DatabaseHelper(appContext);
        Cursor data = myDb.getAllData();

        return getPhoneUseTimeFromCursor(data);
    }

    private Long getPhoneUseTimeFromCursor(Cursor data){

        if (data.getCount() == 0){
            //return "Error, could not retrieve Phone Pick Ups Data";
            return 0L;
        }

        Date timeOn;
        Date timeOff;
        Integer timeOnInt;
        Integer timeOffInt;
        long phoneTimeUseCount = 0;

        while(data.moveToNext()) {
            timeOnInt = data.getInt(data.getColumnIndex("TimeOn"));
            timeOn = new Date(timeOnInt);
            timeOffInt = data.getInt(data.getColumnIndex("TimeOff"));
            timeOff = new Date(timeOffInt);
            phoneTimeUseCount += timeOff.getTime() - timeOn.getTime();
        }

        return phoneTimeUseCount;

    }

    public ArrayList getLastMonthPhoneUseTimeList(){
        ArrayList<String> lastMonthAvailablePhoneUseTimeList = new ArrayList<String>();
        Date todayDate = new Date();
        Date lastDate = new Date();

        return lastMonthAvailablePhoneUseTimeList;
    }

    /*public ArrayList getThisMonthPhoneUseTimeList(){
        ArrayList<String> thisMonthAvailablePhoneUseTimeList = new ArrayList<String>();
        Long lastDateLong;
        Long dateInRecord;
        Calendar lastDate = Calendar.getInstance();

        lastDate.set(Calendar.DAY_OF_MONTH, lastDate.getActualMinimum(Calendar.DAY_OF_MONTH));
        lastDateLong = lastDate.getTime().getTime();

        myDb = new DatabaseHelper(appContext);
        Cursor data = myDb.getDataFromDate(lastDateLong);

        while(data.moveToNext()) {

            thisMonthAvailablePhoneUseTimeList.add(
                    getPhoneUseTimeFromAllData()
            );
        }


        return thisMonthAvailablePhoneUseTimeList;
    }*/

    public ArrayList getLastThirtyDaysPhoneUseTimeList(){
        ArrayList<String> lastThirtyDaysAvailablePhoneUseTimeList = new ArrayList<String>();
        Date todayDate = new Date();
        Date lastDate = new Date();

        return lastThirtyDaysAvailablePhoneUseTimeList;
    }

    public ArrayList getLastWeekPhoneUseTimeList(){
        ArrayList<String> lastWeekPhoneUseTimeList = new ArrayList<String>();
        Date todayDate = new Date();
        Date lastDate = new Date();

        return lastWeekPhoneUseTimeList;
    }

    public ArrayList getLastSevenDaysPhoneUseTimeList(){
        ArrayList<String> lastSevenDaysPhoneUseTimeList = new ArrayList<String>();
        Date todayDate = new Date();
        Date lastDate = new Date();

        return lastSevenDaysPhoneUseTimeList;
    }

    public ArrayList getThisWeekPhoneUseTimeList(){
        ArrayList<String> thisWeekPhoneUseTimeList = new ArrayList<String>();
        Date todayDate = new Date();
        Date lastDate = new Date();

        return thisWeekPhoneUseTimeList;
    }

    public ArrayList getAllAvailableDataDates(){
        myDb = new DatabaseHelper(appContext);
        Cursor data = myDb.getAllData();
        String availableDate = "";
        ArrayList<String> availableDates = new ArrayList<String>();


        while(data.moveToNext()) {
            availableDate = data.getString(data.getColumnIndex("Date"));
            availableDates.add(availableDate);
        }

        return availableDates;
    }

    private ArrayList gettAvailableDataDAfterDate(Long date){
        ArrayList<String> availableDates = new ArrayList<String>();
        return availableDates;
    }

    public Boolean isUserExists(){
        myDb = new DatabaseHelper(appContext);
        Cursor data = myDb.getUserData();
        if(data != null && data.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean insertUserData(String userName, String userEmail, int phoneTimeSpent,
                               int phoneTimeGoal){
        myDb = new DatabaseHelper(appContext);
        if (myDb.insertUserData(userName, userEmail,
                phoneTimeSpent, phoneTimeGoal)) {
            return true;
        }
        else{
            return false;
        }
    }

    public Integer getUserDailyHoursLimit(){
        myDb = new DatabaseHelper(appContext);
        Cursor data = myDb.getUserDailyHoursLimit();
        Integer hours = 0;
        if(data != null && data.getCount()>0){
            while(data.moveToNext()){
                hours = data.getInt(data.getColumnIndex("time_goal"));
            }
        }
        else{
            //error as no data exists
        }
        return hours;
    }
}
