package com.moment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;




public class StatisticsPageFragment extends Fragment {

    Context activityContext;
    DatabaseController dbController;

    public StatisticsPageFragment() {
        // Required empty public constructor
    }

    public static StatisticsPageFragment newInstance() {
        StatisticsPageFragment fragment = new StatisticsPageFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.fragment_statistics_page, container, false);
        showStatistics(view);
        return view;
    }

    private void showStatistics(View view){
        activityContext = getActivity();
        dbController = new DatabaseController(activityContext);
        ArrayList<DateObjectStatistics> statistics = new ArrayList<>();
        ArrayList<String> statisicsConverted = new ArrayList<>();
        statistics = dbController.getStatisticsPhoneUseTime();
        ListView lstStatistics = (ListView)view.findViewById(R.id.lst_date_hours);
        statisicsConverted = converArratListToString(statistics);
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(activityContext, android.R.layout.simple_list_item_1,
                        statisicsConverted);
        lstStatistics.setAdapter(arrayAdapter);
    }

    private ArrayList<String> converArratListToString(ArrayList<DateObjectStatistics> rawArayList){
        ArrayList<String> convertedList = new ArrayList<>();
        String fullString = "";
        for (int i=0; i<rawArayList.size(); ++i) {
            fullString = convertDate(rawArayList.get(i).getDate());
            fullString += ", ";
            fullString += converToTime(rawArayList.get(i).getUseTime());
            convertedList.add(fullString);
        }
        return convertedList;
    }

    private String convertDate (String rawDate){
        SimpleDateFormat dateTableFormat = new SimpleDateFormat("y-D");
        SimpleDateFormat dateFormatToDisplay = new SimpleDateFormat("E, M d");
        String dateString;
        Date bufferDate =  new Date();
        try{
            bufferDate = dateTableFormat.parse(rawDate);
        }
        catch (Exception e){
            ;
        }
        dateString = dateFormatToDisplay.format(bufferDate);

        return dateString;
    }

    private String converToTime(Long time){
        Long minutes  = TimeUnit.MILLISECONDS.toMinutes(time);
        Long hours = TimeUnit.MILLISECONDS.toHours(time);
        String timeStr = "";
        if (hours.equals(0L)){
            timeStr = Long.toString(minutes) + "m";
        }
        else{
            timeStr = Long.toString(hours) + "H " + Long.toString(minutes) + "m";
        }


        return timeStr;
    }
}
