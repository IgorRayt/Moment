package com.moment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


public class NavigationBarFragment extends Fragment {
    private BottomNavigationView navBar;

    private OnFragmentInteractionListener mListener;

    public NavigationBarFragment() {

    }

    public static NavigationBarFragment newInstance(String param1, String param2) {
        NavigationBarFragment fragment = new NavigationBarFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_navigation_bar, container, false);

        final View view =  inflater.inflate(R.layout.fragment_navigation_bar, container, false);


        navBar = view.findViewById(R.id.nav_bar);

        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                openActivity(item, view);
                return true;
            }
        });
        return view;
    }

    private void openActivity(MenuItem item, View view){
        switch (item.getItemId()){
            case R.id.menu_home:/*
                NavigationBarFragment nav_bar = new NavigationBarFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.container_layout, fragment).commit();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_bar_fragment, nav_bar, nav_bar.getClass().getSimpleName()).addToBackStack(null).commit();
*/
                break;
            case R.id.menu_stat:
                Intent stat_intent = new Intent(view.getContext(), StatisticsActivity.class);
                startActivity(stat_intent);

                break;
            case R.id.menu_settings:
                Intent settings_intent = new Intent(view.getContext(), SettingsPreviewActivity.class);
                startActivity(settings_intent);
                break;

        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
