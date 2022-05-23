package com.example.baksombi.view.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.baksombi.R;
import com.example.baksombi.view.fragment.SearchFragment;
import com.example.baksombi.receiver.AppReminderAlarm;
import com.example.baksombi.view.fragment.HomeFragment;

import androidx.fragment.app.Fragment;


import com.example.baksombi.view.fragment.ProfileFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.Calendar;

public class MainActivity extends BaseActivity {
    public static final String PREFERENCE = "preference";
    public static final String THEME = "theme";
    public static final String LANGUAGE = "language";
    private ChipNavigationBar navbar;
    private int fragmentNavigation;
    private String fragmentTag;
    private Fragment savedFragment;
    private TextView title;

    public MainActivity(){
        this.fragmentNavigation = R.id.navigation_home;
        this.fragmentTag = new Integer(this.fragmentNavigation).toString();
        this.savedFragment = new HomeFragment();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navbar = findViewById(R.id.navbar_bottom);
        navbar.setItemSelected(fragmentNavigation, true);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_content, savedFragment, fragmentTag)
                .addToBackStack(fragmentTag)
                .commit();
        this.bottomMenuNavigation();
    }

    private void bottomMenuNavigation(){
        navbar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                String tag = new Integer(i).toString();
                fragmentTag = tag;
                fragmentNavigation = i;
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if(fragment == null){
                    switch(i){
                        case R.id.navigation_home:
                            fragment = new HomeFragment();
                            break;
                        case R.id.navigation_search:
                            fragment = new SearchFragment();
                            break;
                        case R.id.navigation_profile:
                            fragment = new ProfileFragment();
                            break;
                        default:
                            break;
                    }
                }
                if(fragment!=null){
                    savedFragment = fragment;
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fl_content, fragment, tag)
                            .addToBackStack(tag)
                            .commit();
                }

            }
        });
    }
}