package com.moment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
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
        //fillInUserData();

        /*
        navBar = (BottomNavigationView) findViewById(R.id.nav_bar);

        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                openActivity(item);
                return true;
            }
        });
        */
    }

    private void openActivity(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_home:

                break;
            case R.id.menu_stat:

                break;
            case R.id.menu_settings:
                Intent settings_intent = new Intent(this, SettingsActivity.class);
                startActivity(settings_intent);
                break;

        }
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
