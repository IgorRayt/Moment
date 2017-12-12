package com.moment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

        navBar = findViewById(R.id.nav_bar);

        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                openActivity(item);
                return true;
            }
        });
        //popNavigationBar();
    }

    private void openActivity(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_home:
                MainPageFragment main_page = new MainPageFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_page_fragment, main_page, main_page.getClass().getSimpleName()).addToBackStack(null).commit();

                break;
            case R.id.menu_stat:

                break;
            case R.id.menu_settings:
                break;

        }
    }


    private void popNavigationBar(){
        NavigationBarFragment nav_bar = new NavigationBarFragment();
        Bundle arg = new Bundle();
        arg.putInt("page", 0);
        nav_bar.setArguments(arg);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_bar_fragment, nav_bar, nav_bar.getClass().getSimpleName()).addToBackStack(null).commit();

    }
}
