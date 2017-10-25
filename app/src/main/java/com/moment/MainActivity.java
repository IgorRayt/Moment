package com.moment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navBar;
    private int selectedMenu;
    DatabaseHelper myDb;


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
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        myDb = new DatabaseHelper(this);
        Cursor data = myDb.getAllData();
        Date timeOn;
        Date timeOff;
        Date timeDiff;
        Integer timeOnInt;
        Integer timeOffInt;
        TimeUnit timeUnit;

        TextView phoneTimeUselbl = (TextView)findViewById(R.id.lbl_phone_use_time);
        long phoneTimeUseCount = 0;
        String phoneTimeUse = "";
        while(data.moveToNext()){
            timeOnInt = data.getInt(data.getColumnIndex("TimeOn"));
            timeOn = new Date(timeOnInt);
            timeOffInt = data.getInt(data.getColumnIndex("TimeOff"));
            timeOff = new Date(timeOffInt);
            phoneTimeUseCount += timeOff.getTime() - timeOn.getTime();

            //timeOn = originalFormat.parse(Integer.toString(data.getInt(data.getColumnIndex("TimeOn"))));
            //phoneTimeUseCount += originalFormat.format(data.getInt(data.getColumnIndex("TimeOn"))). -
            //          data.getInt(data.getColumnIndex("TimeOff"));
        }
        //phoneTimeUse = timeUnit.convert(phoneTimeUseCount, TimeUnit.MILLISECONDS);
         //   originalFormat.format(phoneTimeUseCount);

        //phoneTimeUselbl.setText(originalFormat.format(phoneTimeUseCount));
        phoneTimeUseCount = phoneTimeUseCount / 1000;
        phoneTimeUselbl.setText(Long.toString(phoneTimeUseCount));
    }

    private void showPhonePickUps(){
        myDb = new DatabaseHelper(this);
        Cursor data = myDb.getTodayPhonePickUps();
        String countDat = "";
        if (data.getCount() == 0){
            //error
            return;
        }
        TextView phonePickUpsLbl = (TextView)findViewById(R.id.lbl_phone_pick_ups);
        while (data.moveToNext()){
            countDat = data.getString(data.getColumnIndex("COUNT(*)"));
        }


        phonePickUpsLbl.setText(countDat);
    }

    /*private void fillInUserData(){
        Bundle extras = getIntent().getExtras();

        TextView userNameLbl = (TextView)findViewById(R.id.lbl_user_name);
        TextView userEmailLbl = (TextView)findViewById(R.id.lbl_user_email);
        TextView usePhoneUseLbl = (TextView)findViewById(R.id.lbl_phone_use);
        TextView userDataShareLbl = (TextView)findViewById(R.id.lbl_data_share);
        String userNameLblText;
        String userEmailLblText;
        String phoneUseLblText;
        String userDataShareLblText;

        if (extras != null) {
            String[] userData = extras.getStringArray("userDataArray");
            String userName = userData[0];
            String userEmail = userData[1];
            String intialPhoneTime = userData[2];
            String finalPhoneTime = userData[3];
            String data_share_agreement = userData[4];

            userNameLblText = String.format("Hello, %s, welcome to Moment Application",
                    userName);
            userEmailLblText = String.format("Your Email is %s", userEmail);
            phoneUseLblText = String.format("Your current phone user is %s hours " +
                    "per day. You target is %s hours per day", intialPhoneTime, finalPhoneTime);
            if (data_share_agreement.equals("Yes")) {
                userDataShareLblText = "Thank you for sharing your data!";
            }
            else{
                userDataShareLblText = "You declined the data share!";
            }
        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "Error!. No User Data has been passed! Restart app.";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            userNameLblText = "Error! User Name hasn't been provided!";
            userEmailLblText = "Error! User Email hasn't been provided!";
            phoneUseLblText = "Error! Phone use time hasn't been provided";
            userDataShareLblText = "Error! Data share confirmation hasn't been provided!";
        }

        userNameLbl.setText(userNameLblText);
        userEmailLbl.setText(userEmailLblText);
        usePhoneUseLbl.setText(phoneUseLblText);
        userDataShareLbl.setText(userDataShareLblText);


    }*/
}
