package com.moment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends FragmentActivity {

    private BottomNavigationView navBar;
    private int selectedMenu;
    DatabaseHelper myDb;
    DatabaseController dbController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        showPhonePickUps();
        showPhoneUseTime();
        popNavigationBar();
    }

    private void popNavigationBar(){
        NavigationBarFragment nav_bar = new NavigationBarFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_bar_fragment, nav_bar, nav_bar.getClass().getSimpleName()).addToBackStack(null).commit();

    }

    private void showPhoneUseTime(){
        dbController = new DatabaseController(this);
        String totalPhoneUseTime = Long.toString(dbController.getTotalPhoneUseTime());
        TextView phoneUseTimeLbl = (TextView)findViewById(R.id.lbl_phone_use_time);
        phoneUseTimeLbl.setText(totalPhoneUseTime);
    }


    private void showPhonePickUps(){
        TextView phonePickUpsLbl = (TextView)findViewById(R.id.lbl_phone_pick_ups);
        dbController = new DatabaseController(this);
        String totalPhonePickUps = Long.toString(dbController.getTodayPhonePickUps());

        phonePickUpsLbl.setText(totalPhonePickUps);
    }

}
