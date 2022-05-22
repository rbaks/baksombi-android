package com.example.baksombi.view.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baksombi.helper.LocaleHelper;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {
    private String oldLanguage;
    @Override
    protected void onCreate(Bundle savedInstance){
        System.out.println("**********************On created called*************");
        super.onCreate(savedInstance);
        this.oldLanguage = getBaseContext().getSharedPreferences(MainActivity.PREFERENCE, Context.MODE_PRIVATE)
                .getString(MainActivity.LANGUAGE, "en");
        LocaleHelper.updateLocaleConfig(getBaseContext(),this.oldLanguage);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        this.oldLanguage = getBaseContext().getSharedPreferences(MainActivity.PREFERENCE, Context.MODE_PRIVATE)
                .getString(MainActivity.LANGUAGE, "en");
        LocaleHelper.updateLocaleConfig(base,this.oldLanguage);
    }
    @Override
    protected void onResume(){
        super.onResume();
        String language =  getBaseContext().getSharedPreferences(MainActivity.PREFERENCE, Context.MODE_PRIVATE)
                .getString(MainActivity.LANGUAGE, "en");
        if(this.oldLanguage != language){
            recreate();
            this.oldLanguage = language;
        }
    }
}
