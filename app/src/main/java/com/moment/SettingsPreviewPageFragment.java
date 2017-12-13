package com.moment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class SettingsPreviewPageFragment extends Fragment {


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
        return inflater.inflate(R.layout.fragment_settings_preview_page, container, false);
    }
}
