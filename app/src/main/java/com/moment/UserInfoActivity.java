package com.moment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;


public class UserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

    }

    public void btn_start_application_press(View view){
        collectUserInformation();
        Intent user_info_intent = new Intent(this, MainActivity.class);
        startActivity(user_info_intent);

    }

    private String[] collectUserInformation(){
        String[] userData = {};
        EditText userName = (EditText)findViewById(R.id.txt_name);
        EditText userEmail = (EditText)findViewById(R.id.txt_email);
        SeekBar initialPhoneTime = (SeekBar)findViewById(R.id.seekbar_initial_time_for_phone);
        SeekBar finalPhoneTime = (SeekBar)findViewById(R.id.seekbar_final_phone_time);
        RadioGroup dataShareRadioGroup = (RadioGroup)findViewById(R.id.rad_group_data_share);
        RadioButton dataShare = (RadioButton)  dataShareRadioGroup.getChildAt(idx);
        String selectedtext = dataShare.getText().toString();

        userData = {userName.getText(), userEmail.getText(), initialPhoneTime.}
        return userData;

    }

}
