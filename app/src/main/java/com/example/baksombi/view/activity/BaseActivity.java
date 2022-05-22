package com.example.baksombi.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baksombi.R;
import com.example.baksombi.helper.LocaleHelper;
import com.example.baksombi.helper.ThemeHelper;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {
    private String oldLanguage;
    private int oldTheme;
    @Override
    protected void onCreate(Bundle savedInstance){
        SharedPreferences preferences = getBaseContext().getSharedPreferences(MainActivity.PREFERENCE, Context.MODE_PRIVATE);
        oldTheme = preferences.getInt(MainActivity.THEME, R.id.img_btn_blue_theme);
        setTheme(ThemeHelper.getThemeAccordingToButton(oldTheme));
        super.onCreate(savedInstance);
        this.oldLanguage = preferences
                .getString(MainActivity.LANGUAGE, "en");
        LocaleHelper.updateLocaleConfig(getBaseContext(),this.oldLanguage);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        SharedPreferences preferences = getBaseContext().getSharedPreferences(MainActivity.PREFERENCE, Context.MODE_PRIVATE);
        this.oldLanguage = preferences
                .getString(MainActivity.LANGUAGE, "en");
        LocaleHelper.updateLocaleConfig(getBaseContext(),this.oldLanguage);
    }
    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences preferences = getBaseContext().getSharedPreferences(MainActivity.PREFERENCE, Context.MODE_PRIVATE);
        String language =  preferences
                .getString(MainActivity.LANGUAGE, "en");
        int theme = preferences.getInt(MainActivity.THEME, R.id.img_btn_blue_theme);
        if(!this.oldLanguage.equals(language)|| this.oldTheme!=theme){
            recreate();
        }
    }
}
