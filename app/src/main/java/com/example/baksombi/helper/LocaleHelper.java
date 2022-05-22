package com.example.baksombi.helper;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import com.example.baksombi.view.activity.MainActivity;

import java.util.Locale;

public class LocaleHelper {

    public static void updateLocaleConfig(Context context, String language){

            context.getSharedPreferences(MainActivity.PREFERENCE, Context.MODE_PRIVATE)
                .edit()
                .putString(MainActivity.LANGUAGE, language).commit();
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = context.getResources().getConfiguration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
    }
}
