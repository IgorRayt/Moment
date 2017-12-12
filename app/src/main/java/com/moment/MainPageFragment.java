package com.moment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainPageFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    Context activityContext;
    DatabaseController dbController;
    public MainPageFragment() {
        // Required empty public constructor
    }


    public static MainPageFragment newInstance(String param1, String param2) {
        MainPageFragment fragment = new MainPageFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);
        showPhonePickUps(view);
        showPhoneUseTime(view);
        return view;
    }

    private void showPhoneUseTime(View view){
        activityContext = getActivity();
        dbController = new DatabaseController(activityContext);
        String totalPhoneUseTime = Long.toString(dbController.getTotalPhoneUseTime());
        TextView phoneUseTimeLbl = (TextView) view.findViewById(R.id.lbl_phone_use_time);
        phoneUseTimeLbl.setText(totalPhoneUseTime);
    }


    private void showPhonePickUps(View view){
        activityContext = getActivity();
        TextView phonePickUpsLbl = (TextView) view.findViewById(R.id.lbl_phone_pick_ups);
        dbController = new DatabaseController(activityContext);
        String totalPhonePickUps = Long.toString(dbController.getTodayPhonePickUps());

        phonePickUpsLbl.setText(totalPhonePickUps);
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
