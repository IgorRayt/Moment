package com.moment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class UserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

    }

    public void btn_start_application_press(View view){
        Intent user_info_intent = new Intent(this, MainActivity.class);
        startActivity(user_info_intent);

    }

}
