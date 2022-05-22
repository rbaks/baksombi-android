package com.example.baksombi.helper;

import android.content.Context;

import com.example.baksombi.R;
import com.example.baksombi.view.activity.MainActivity;

public class ThemeHelper {
    public static void updateLocalThemeConfig(Context context, int theme){
        context.getSharedPreferences(MainActivity.PREFERENCE, Context.MODE_PRIVATE)
                .edit()
                .putInt(MainActivity.THEME, theme).commit();

    }

    public static int getThemeAccordingToButton(int id){
        switch(id){
            case R.id.img_btn_blue_theme:
                return R.style.Theme_Blue;
            case R.id.img_btn_pink_theme:
                return R.style.Theme_Pink;
            case R.id.img_btn_orange_theme:
                return R.style.Theme_Orange;
            default:
                break;
        }
        return R.style.Theme_Blue;
    }

}
