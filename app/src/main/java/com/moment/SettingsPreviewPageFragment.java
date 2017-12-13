package com.moment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SettingsPreviewPageFragment extends Fragment {

    Context activityContext;

    public SettingsPreviewPageFragment() {
        // Required empty public constructor
    }


    public static SettingsPreviewPageFragment newInstance() {
        SettingsPreviewPageFragment fragment = new SettingsPreviewPageFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_preview_page, container, false);

        Button btn_open_settings = (Button) view.findViewById(R.id.btn_open_settings);

        btn_open_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_open_settings_click(v);
            }
        });

        return view;
    }

    public void btn_open_settings_click(View view){
        activityContext = getActivity();
        Intent intent = new Intent(activityContext, SettingsActivity.class);
        startActivity(intent);
    }


}
