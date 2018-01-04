package com.moment;

/**
 * Created by irayt1063 on 1/2/2018.
 */

public class DateObjectStatistics {
    public String date;
    public Long useTime;
    public DateObjectStatistics(Long useTime, String date){
        this.date = date;
        this.useTime = useTime;
    }

    public Long getUseTime(){
        return useTime;
    }

    public String getDate(){
        return date;
    }
}
