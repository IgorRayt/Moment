package com.moment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends FragmentActivity {

    private BottomNavigationView navBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        navBar = findViewById(R.id.nav_bar);

        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                openActivity(item);
                return true;
            }
        });
        callStartFragment();
    }

    @Override
    public void onBackPressed(){
        finish();
    }

    private void callStartFragment(){
        MainPageFragment mainPage = new MainPageFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_element, mainPage,mainPage.getClass().getSimpleName())
                .addToBackStack(null).commit();
    }

    private void openActivity(MenuItem item){
        FragmentManager fManager = getSupportFragmentManager();
        fManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        switch (item.getItemId()){
            case R.id.menu_home:
                MainPageFragment mainPage = new MainPageFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_element, mainPage,
                                mainPage.getClass().getSimpleName())
                                .addToBackStack(null).commit();
                break;
            case R.id.menu_stat:
                StatisticsPageFragment statisticsPage = new StatisticsPageFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_element, statisticsPage,
                                statisticsPage.getClass().getSimpleName())
                                .addToBackStack(null).commit();
                break;
            case R.id.menu_settings:
                SettingsPreviewPageFragment settingsPreviewPageFragment =
                        new SettingsPreviewPageFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_element, settingsPreviewPageFragment,
                                settingsPreviewPageFragment.getClass().getSimpleName())
                                .addToBackStack(null).commit();
                break;

        }

    }
}
