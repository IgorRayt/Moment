package com.moment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        fillInUserData();

    }

    private void fillInUserData(){
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


    }
}
