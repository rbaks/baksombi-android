package com.example.baksombi.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.baksombi.R;
import com.example.baksombi.view.fragment.HomeFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.example.baksombi.view.fragment.ProfileFragment;
import com.example.baksombi.view.fragment.QuizFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

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
        initializeSharedPreferences();
        navbar = findViewById(R.id.navbar_bottom);
        System.out.println("*****Main Activity On created called*******");
        navbar.setItemSelected(fragmentNavigation, true);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_content, savedFragment, fragmentTag)
                .addToBackStack(fragmentTag)
                .commit();
        this.bottomMenuNavigation();
    }
    private void initializeSharedPreferences(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(!sharedPreferences.contains(THEME)){
            editor.putInt(THEME, R.style.Theme_Baksombi);
        }
        if(!sharedPreferences.contains(LANGUAGE)){
            editor.putString(LANGUAGE, Locale.getDefault().getDisplayLanguage());
        }
        editor.commit();
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


}