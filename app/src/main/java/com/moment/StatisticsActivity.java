package com.moment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        popNavigationBar();
    }
    private void popNavigationBar(){
        NavigationBarFragment nav_bar = new NavigationBarFragment();
        Bundle arg = new Bundle();
        arg.putInt("page", 1);
        nav_bar.setArguments(arg);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_bar_fragment, nav_bar, nav_bar.getClass().getSimpleName()).addToBackStack(null).commit();

    }
}
