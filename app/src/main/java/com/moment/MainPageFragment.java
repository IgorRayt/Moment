package com.moment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


public class MainPageFragment extends Fragment {

    Context activityContext;
    DatabaseController dbController;
    public MainPageFragment() {
        // Required empty public constructor
    }


    public static MainPageFragment newInstance() {
        MainPageFragment fragment = new MainPageFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);
        showPhoneUseTime(view);
        showPickUps(view);
        return view;
    }

    private void showPhoneUseTime(View view){
        activityContext = getActivity();
        dbController = new DatabaseController(activityContext);
        String phoneUseTime = converToTime(dbController.getTodayPhoneUse());
        //String totalPhoneUseTime = Long.toString);
        //totalPhoneUseTime = formatter.format(totalPhoneUseTime);
        TextView phoneUseTimeLbl = (TextView) view.findViewById(R.id.lbl_phone_use_time);
        phoneUseTimeLbl.setText(phoneUseTime);
    }

    private void showPickUps(View view){
        activityContext = getActivity();
        dbController = new DatabaseController(activityContext);
        String pickUps = Integer.toString(dbController.getTodayPhonePickUps());
        TextView phonePickUps = (TextView) view.findViewById(R.id.lbl_phone_pick_ups);
        phonePickUps.setText(pickUps);
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
