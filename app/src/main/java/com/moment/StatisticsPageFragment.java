package com.moment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class StatisticsPageFragment extends Fragment {


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
        return inflater.inflate(R.layout.fragment_statistics_page, container, false);
    }
}
