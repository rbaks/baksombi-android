package com.example.baksombi.view.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baksombi.R;
import com.example.baksombi.helper.HttpHelper;
import com.example.baksombi.helper.LocaleHelper;
import com.example.baksombi.helper.ThemeHelper;
import com.example.baksombi.model.Token;
import com.example.baksombi.receiver.AppReminderAlarm;

import java.util.Calendar;
import java.util.Locale;

public class BaseActivity extends AppCompatActivity {
    private String oldLanguage;
    private int oldTheme;
    @Override
    protected void onCreate(Bundle savedInstance){
        SharedPreferences preferences = getBaseContext().getSharedPreferences(MainActivity.PREFERENCE, Context.MODE_PRIVATE);
        oldTheme = preferences.getInt(MainActivity.THEME, R.id.img_btn_blue_theme);
        setTheme(ThemeHelper.getThemeAccordingToButton(oldTheme));
        setHttpParameter(getBaseContext());
        this.oldLanguage = preferences
                .getString(MainActivity.LANGUAGE, getBaseContext().getResources().getConfiguration().locale.getDisplayLanguage());
        LocaleHelper.updateLocaleConfig(getBaseContext(),this.oldLanguage);
        super.onCreate(savedInstance);
    }
    @Override
    protected void attachBaseContext(Context base) {
        SharedPreferences preferences = base.getSharedPreferences(MainActivity.PREFERENCE, Context.MODE_PRIVATE);
        this.oldLanguage = preferences
                .getString(MainActivity.LANGUAGE, "en");
        LocaleHelper.updateLocaleConfig(base,this.oldLanguage);
        setHttpParameter(base);
        super.attachBaseContext(base);
    }
    @Override
    protected void onResume(){
        super.onResume();
        cancelNotification();
        SharedPreferences preferences = getBaseContext().getSharedPreferences(MainActivity.PREFERENCE, Context.MODE_PRIVATE);
        String language =  preferences
                .getString(MainActivity.LANGUAGE, getBaseContext().getResources().getConfiguration().locale.getDisplayLanguage());
        int theme = preferences.getInt(MainActivity.THEME, R.id.img_btn_blue_theme);
        if(!this.oldLanguage.equals(language)|| this.oldTheme!=theme){
            recreate();
        }
    }

    protected void setHttpParameter(Context context){
        SharedPreferences preferences = context.getSharedPreferences(MainActivity.PREFERENCE, Context.MODE_PRIVATE);
        HttpHelper.getInstance().setToken(preferences.getString(Token.PREF_TOKEN,null));
    }
    @Override
    protected void onPause(){
        initNotification();
        super.onPause();
    }
    private void initNotification(){
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
