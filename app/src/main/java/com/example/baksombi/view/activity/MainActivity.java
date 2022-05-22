package com.example.baksombi.view.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.baksombi.R;
import com.example.baksombi.receiver.AppReminderAlarm;
import com.example.baksombi.view.fragment.HomeFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.example.baksombi.view.fragment.ProfileFragment;
import com.example.baksombi.view.fragment.QuizFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.Calendar;
import java.util.Locale;

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
                        case R.id.navigation_quiz:
                            fragment = new QuizFragment();
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
    @Override
    protected void onPause(){
        initNotification();
        super.onPause();
    }
    private void initNotification(){
        System.out.println("***************notification init****************");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 30 );
        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(getBaseContext().ALARM_SERVICE);
        Intent intent = new Intent(this, AppReminderAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 500, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    public void cancelNotification(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(), 500, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
    }
}