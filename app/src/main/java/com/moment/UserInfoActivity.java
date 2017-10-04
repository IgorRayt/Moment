package com.moment;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class UserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        SeekBar initialPhoneTimeSeekBar =
                (SeekBar)findViewById(R.id.seekbar_initial_time_for_phone);
        SeekBar finalPhoneTimeSeekBar = (SeekBar)findViewById(R.id.seekbar_final_phone_time);
        final TextView lbl_initialPhoneTimeSeekBar =
                (TextView)findViewById(R.id.lbl_seekbar_initial_time_for_phone);
        final TextView lbl_finalPhoneTimeSeekBar =
                (TextView)findViewById(R.id.lbl_seekbar_final_phone_time);
        initialPhoneTimeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                String text;
                if (progress == 1){
                    text = "1 hour";
                }
                else{
                    text = String.format("%s hours", Integer.toString(progress));
                }
                lbl_initialPhoneTimeSeekBar.setText(text);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                String text;
                if (progress == 1){
                    text = "1 hour";
                }
                else{
                    text = String.format("%s hours", Integer.toString(progress));
                }
                lbl_initialPhoneTimeSeekBar.setText(text);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        finalPhoneTimeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                String text;
                if (progress == 1){
                    text = "1 hour";
                }
                else{
                    text = String.format("%s hours", Integer.toString(progress));
                }
                lbl_finalPhoneTimeSeekBar.setText(text);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                String text;
                if (progress == 1){
                    text = "1 hour";
                }
                else{
                    text = String.format("%s hours", Integer.toString(progress));
                }
                lbl_finalPhoneTimeSeekBar.setText(text);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener
                (new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        // Note that system bars will only be "visible" if none of the
                        // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
                        if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                            // TODO: The system bars are visible. Make any desired
                            // adjustments to your UI, such as showing the action bar or
                            // other navigational controls.
                        } else {
                            // TODO: The system bars are NOT visible. Make any desired
                            // adjustments to your UI, such as hiding the action bar or
                            // other navigational controls.
                        }
                    }
                });



        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


    }

    public void btn_start_application_press(View view){

        checkUserInformation();
    }

    private void checkUserInformation(){
        EditText userNameTextField = (EditText)findViewById(R.id.txt_name);
        EditText userEmailTextField = (EditText)findViewById(R.id.txt_email);
        TextView lblUserEmailError = (TextView)findViewById(R.id.lbl_user_email_error);
        TextView lblUserNameError = (TextView)findViewById(R.id.lbl_user_name_error);
        Boolean userNameGood = false;
        Boolean userEmailGood = false;
        if(userEmailTextField.getText().toString().equals("")||
                userEmailTextField.getText().toString().equals(" ")){
            Context context = getApplicationContext();
            CharSequence text = "The toast message just for the assignment requirements.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            lblUserEmailError.setVisibility(View.VISIBLE);
        }
        else{
            userEmailGood = true;
            lblUserEmailError.setVisibility(View.GONE);
        }
        if(userNameTextField.getText().toString().equals("") ||
                userNameTextField.getText().toString().equals(" ")){
            Context context = getApplicationContext();
            CharSequence text = "The toast message just for the assignment requirements.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            lblUserNameError.setVisibility(View.VISIBLE);
        }
        else{
            userNameGood = true;
            lblUserNameError.setVisibility(View.GONE);
        }

        if(userEmailGood && userNameGood){
            lblUserEmailError.setVisibility(View.GONE);
            lblUserNameError.setVisibility(View.GONE);
            collectUserInformation();
            Intent user_info_intent = new Intent(this, MainActivity.class);
            user_info_intent.putExtra("userDataArray", collectUserInformation());
            startActivity(user_info_intent);
        }
    }

    private String[] collectUserInformation(){
        String[] userData;
        EditText userNameTextField = (EditText)findViewById(R.id.txt_name);
        EditText userEmailTextField = (EditText)findViewById(R.id.txt_email);
        SeekBar initialPhoneTimeSeekBar =
                (SeekBar)findViewById(R.id.seekbar_initial_time_for_phone);
        SeekBar finalPhoneTimeSeekBar = (SeekBar)findViewById(R.id.seekbar_final_phone_time);
        RadioGroup dataShareRadioGroup = (RadioGroup)findViewById(R.id.rad_group_data_share);
        String data_share_agreement =
                ((RadioButton)findViewById(dataShareRadioGroup.getCheckedRadioButtonId()))
                        .getText().toString();

        int initialPhoneTime = initialPhoneTimeSeekBar.getProgress();
        int finalPhoneTime = finalPhoneTimeSeekBar.getProgress();
        String userName = userNameTextField.getText().toString();

        userData = new String[]{userNameTextField.getText().toString(),
                userEmailTextField.getText().toString(), Integer.toString(initialPhoneTime),
                Integer.toString(finalPhoneTime), data_share_agreement};
        return userData;

    }


}
